package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcArticleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Article save(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("articles").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("title", article.getTitle());
        parameters.put("author", article.getAuthor());
        parameters.put("contents", article.getContents());
        parameters.put("writtentime", LocalDateTime.now());
        int key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)).intValue();
        article.setId(key);

        return article;
    }

    @Override
    public Optional<Article> findByid(int id) {
        String sql = "select * from articles where id = ?";
        List<Article> articles = jdbcTemplate.query(sql, new RowMapper<Article>() {
            @Override
            public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
                Article article = new Article(rs.getString("title"), rs.getString("author"), rs.getString("contents"));
                article.setId(rs.getInt("id"));
                article.setWrittenTime(rs.getTimestamp("writtentime").toLocalDateTime());
                return article;
            }
            ;
        }, id);
        return articles.stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        String sql = "select * from articles";
        List<Article> articles = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Article article = new Article(rs.getString("title"), rs.getString("author"), rs.getString("contents"));
            article.setId(rs.getInt("id"));
            article.setWrittenTime(rs.getTimestamp("writtentime").toLocalDateTime());
            return article;
        });
        return articles;
    }
}

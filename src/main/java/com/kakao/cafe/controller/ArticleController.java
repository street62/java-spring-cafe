package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/questions")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @ExceptionHandler(IllegalStateException.class)
    public Object illegalStateHandler(Exception e) {
        System.err.println(e.getMessage());
        return "redirect:/";
    }

    @GetMapping
    public String createForm() {
        return "../templates/form";
    }

    @PostMapping
    public String create(ArticleForm form) {
        Article article = new Article(form.getTitle(), form.getContents(), form.getAuthor());
        articleService.create(article);
        return "redirect:/";
    }

    @GetMapping("/{articleId}")
    public String viewArticle(@PathVariable("articleId") int articleId, Model model) {
        Article selectedArticle = articleService.findById(articleId);
        model.addAttribute("article", selectedArticle);
        return "/qna/show";
    }


}

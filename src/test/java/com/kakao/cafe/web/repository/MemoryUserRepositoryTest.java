package com.kakao.cafe.web.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.web.domain.user.User;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemoryUserRepositoryTest {

	static UserRepository repository;
	static User user;

	@BeforeAll
	static void setUp() {
		repository = new MemoryUserRepository();
		user = new User();
		user.setUserId("jeremy0405");
		user.setPassword("qwer1234");
		user.setName("Jerry");
		user.setEmail("jeremy0405@naver.com");
		repository.save(user);
	}

	@Test
	@DisplayName("유저가 회원가입하면 유저의 정보가 저장된다.")
	void save() {
		//given

		//when
		User result = repository.findByEmail("jeremy0405@naver.com").get();

		//then
		assertThat(result).hasToString(String.valueOf(user));
	}

	@Test
	@DisplayName("유저의 id를 조회하면 id에 해당하는 유저를 찾는다.")
	void findUserById() {
		//given

		//when
		User result = repository.findById(user.getId()).get();

		//then
		assertThat(result).hasToString(String.valueOf(user));
	}

	@Test
	@DisplayName("유저의 email를 조회하면 email에 해당하는 유저를 찾는다.")
	void findUserByEmail() {
		//given

		//when
		User result = repository.findByEmail(user.getEmail()).get();

		//then
		assertThat(result).hasToString(String.valueOf(user));
	}

	@Test
	@DisplayName("모든 유저의 정보를 조회하면 모든 유저목록을 반환한다.")
	void findAllUser() {
		//given
		User user1 = new User();
		user1.setUserId("anotherId");
		user1.setPassword("qwer1234");
		user1.setName("user1");
		user1.setEmail("hsjang0405@gmail.com");
		repository.save(user1);

		//when
		List<User> result = repository.findAll();

		//then
		assertThat(result.get(0)).hasToString(String.valueOf(user));
		assertThat(result.get(1)).hasToString(String.valueOf(user1));
		assertThat(result).hasSize(2);
	}

}

package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
//JPA에서는 Repository라고 부르며 인터페이스로 생서
//Entity 클래스와 기본 ENtity repository는 함께 도메인에 위치해야한다.
//상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
public interface PostsRepository extends JpaRepository<Posts, Long>{

}

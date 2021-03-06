package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 설정 없이 사용하면 H2 DB를 실행함.
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // Junit에서 단위 테스트가 끝날 떄마다 수행되는 메소드를 지정, 테스트간 독립성을 위해 사용
    public void cleanup(){
        postsRepository.deleteAll();

    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = " 테스트 게시글 ";
        String content = " 테스트 본문 ";

        postsRepository.save(Posts.builder() // 테이블 posts에 insert/update 쿼리를 실행함, id 값이 있다면 update가, 없다면 insert 쿼리가 실행됨
                .title(title)
                .content(content)
                .author("organicferrum@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo("organicferrum@gmail.com");
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate ="+posts.getCreatedDate()
        + ", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }


}

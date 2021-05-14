package com.jojoldu.book.springboot.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor // 기본 생성자 자동 추가 = public Posts(){}
@Entity // 테이블과 링크될 클래스임을 나타낸다. 보통 이런 클래스를 Entity 클래스라고 부른다.
public class Posts {

    @Id // 헤당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙을 나타냄 GenerationType.Identity 옵션을 추가해야만 auto_increment가 된다(스프링부터2.0). + Entity의 PK는 long 타입의 Auto_invrement를 추천
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 테이블의 컬럼을 나타냄
    private String content;

    private String author;

    @Builder // 해당클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
// 자바빈 규약과 반하게 Setter 메소드가 없는 이유 -> 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수가 ㅇ벗어 차후 기능 변경시 복잡해짐
// 그래서 Entity 클래스에서는 절대 Setter 메소드를 만들지 않고 대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야만 합니다.
// Setter 없이 값을 체워넣는 방법 1. 생성자를 이용해 최종값을 체운 후 DB에 삽입 2. 해당 이벤트에 맞는 public 메소드를 호출하여 변경
// 3. 빌더패턴을 이용

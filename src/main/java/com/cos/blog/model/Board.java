package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity //user  클래스가 테이블로 생성 된다 .Java object => DB 테이블 매핑
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content;//섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨
    private int count;

    @ManyToOne(fetch = FetchType.EAGER) //Many = board , User = one  환명 유저는 여러개 개시글을 쓴다
    @JoinColumn(name ="userId")
    private User user;//DB 는 오브젝트를 지정할 수없지만 자바는 가능하다 .

    //연관관계의 주인이 아니다 = 난 fk가 아니니 디비에 컬럼을 만들지 마세요 .
    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)
    private List<Reply> reply;


    @CreationTimestamp
    private Timestamp createDate;
}

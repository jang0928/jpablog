package com.cos.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity //user  클래스가 테이블로 생성 된다 .
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB 넘버링 전략을 따라간다.
    private int id;

    @Column(nullable = false, length = 100 , unique = true)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'")
    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum 을 쓰는게 좋아유

    @CreationTimestamp
    private Timestamp createDate;

    private String oauth;//kakao, google
}

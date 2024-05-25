package springPractice.demo.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name= "users")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 접근 제어자가 protected인 기본 생성자를 별도 코드 ㅇ벗이 생성

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;


    //private LocalDateTime createdAt;
    //private LocalDateTime modifiedAt;

    // Getters and setters...
}

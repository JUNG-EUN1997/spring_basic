package com.beyond.basic.domain.member;

import com.beyond.basic.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
//import javax.persistence.Id;

//@Data
@Getter
/*
* @Entity 어노테이션을 통해 엔티티매니저에게 객체관리를 위임
* 해당 클래스명으로 테이블 및 컬럼을 자동생성하고 각종 설정정보 위임
* */
@Entity // JPA에게 엔티티매니저가 관리해야할 요소다! 라고 설정해놓은 것.
// 실행 시, 연결해놓은 DB에 해당 테이블이 존재하지 않으면 엔티티매니저가 DB에 테이블을 생성해준다

@NoArgsConstructor //필수⭐ -> JPA에서는 기본생성자가 런타임 시, 필수
public class Member {
    @Id //pk 설정
//    identity : auto_increment 설정
//    auto : jpa 자동으로 적절한 전략을 선택하도록 맡기는 것
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //long은 bigint로 변환
//    String은 Varchar(255)로 기본으로 변환, name변수명이 name컬럼명으로 변환
    private String name;

//    @Column을 통해 해당 컬럼의 추가 설정을 진행할 수 있다.
//    nullable = false  : notnull 제약조건
//    unique = true : unique 제약조건
    @Column(unique = true, length = 50)
    private String email;


//    @Column(name="pw") 실제 DB에 들어가는 name을 변경할 수 있다.
//    하지만 권장하지 않는다. DB의 컬럼명과 변수명을 일치시켜야 혼선을 줄일 수 있다.
    private String password;

//    어떤 관계성을 갖고있는지 mappedBy에 작성
    @OneToMany(mappedBy = "member") // "domain/post/Post"에 member 변수명 작성
//    @OneToMany는 optional한 값
    private List<Post> posts;



//        camelCase를 사용하면, DB에는 _(언더바)로 들어간다
//    ex) JAVA에는 createdTime 일 때 DB에는 created_time 로 들어간다.
    @CreationTimestamp //DB에는 current_timestamp가 생성되지 않음.
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;


    public Member(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

//    password 상단에 @Setter를 통해 특정 변수만 setter 사용이 가능하나,
//    일반적으로 의도를 명확하게한 메서드를 별도로 만들어 사용하는 것을 권장.
    public void updatePw(String password){
        this.password = password;
    }

    public MemberDetailResDto detFromEntity(){ // db -> client
        String dateFormat = this.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초"));
        return new MemberDetailResDto(this.id,this.name,this.email,this.password,dateFormat);
    }

    public MemberResDto listFromEntity(){ // db -> client
        return new MemberResDto(this.id, this.name, this.email,this.createdTime);
    }
}

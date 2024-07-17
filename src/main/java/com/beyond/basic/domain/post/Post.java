package com.beyond.basic.domain.post;

import com.beyond.basic.domain.BaseEntity;
import com.beyond.basic.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter // Data는 권장하지 않음
@Entity
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne //자신이 "앞" , 상대가 "뒤" :  나To상대 의 관계
//    @ManyToOne은 필수적인 값
    @JoinColumn(name = "member_id")
//    JPA의 영속성(Persistence)컨텍스트에 의해 Membr가 관리
    private Member member; //관계된 id가 아닌, 객체를 갖고옴

    public PostResDto fromEntity(){
        return new PostResDto(this.id, this.title);
    }
}

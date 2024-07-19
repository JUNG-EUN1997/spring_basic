package com.beyond.basic.domain;

import lombok.*;

//@Setter
//@Getter
@Data // getter, setter, toString 등을 포함
//@NoArgsConstructor : 기본생성자를 만드는 어노테이션
//@AllArgsConstructor : 모든 매개변수를 사용한 생성자를 만드는 어노테이션
@NoArgsConstructor
@AllArgsConstructor // 이걸 넣으면 보통 @NoArgsConstructor 도 함께 넣는다 > 기본 생성자가 없어져버리기 때문
//                     되도록 NoArgsConstructor는 함께 넣도록 하자. ResponseBody가 데이터바인딩을 하기 위해 기본생성자가 반드시 필요
public class Hello {
    private String name;
    private String email;
    private String password;
    
//    @Override
//    public String toString(){
//        return "이름은 : " + this.name + " 메일은 : " + this.email + " 비번은 : " + this.password ;
//    }

    public Hello(HelloBulder helloBulder){
        this.name = helloBulder.name;
        this.email = helloBulder.email;
        this.password = helloBulder.password;
    }

    public static HelloBulder builder(){
        return new HelloBulder();
    }

    public static class HelloBulder{
        private String name;
        private String email;
        private String password;

        public HelloBulder name(String name){
            this.name = name;
            return this;
        }

        public HelloBulder email(String email){
            this.email = email;
            return this;
        }

        public HelloBulder password(String password){
            this.password = password;
            return this;
        }

        public Hello bulid(){
            return new Hello(this);
        }
    }
}

//package com.beyond.basic.repository;
//
//import com.beyond.basic.domain.member.Member;
//import org.apache.ibatis.annotations.Mapper; // ibatis 다음에 mybatis가 나왔다!
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//// 해당 레파지토리는 mybatis 기술을 쓰겠다는 표현
//@Repository
//@Mapper // 사용하려면, mybatis 관련된 것 외부 라이브러리를 import 해야함
////mybatis를 쓰겠다 라는 선언
//public interface MemberMybatisRepository extends MemberRepository{
////    List<Member> findAll();
////    Member save();
////    Member findById(Long id);
//
////    위 3개 기능은, MemberRepository에서 extends를 하기 때문에
////    그대로 상속관계로 인해 선언하지 않아도 된다.
////    작성 시, 오버라이딩이 되어버린다!
//
//}

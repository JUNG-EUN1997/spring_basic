package com.beyond.basic.repository;

import com.beyond.basic.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository //싱글톤 사용
public class MemberJdbcRepositoy implements MemberRepository{
    @Autowired
    private DataSource dataSource;

    @Override
    public Member save(Member member) { // 회원가입 시, 리턴타입이 어찌될지 모르기 때문에
        try{
//            Connection connection = dataSource.getConnection();
//            String sql = "insert into member(name, email, password) values(?, ?, ?);";
////            ? 는 변수로 대신하겠다 라는 의미
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.setString(1, member.getName()); // 1번 ?(파라미터자리)자리 에다가 넣겠다.
//            preparedStatement.setString(2, member.getEmail());
//            preparedStatement.setString(3, member.getPassword());
//
//            preparedStatement.executeUpdate(sql); // 추가, 수정의 경우 executeUpdate
//            조회 시 .executeQuery 진행

//            Jpa에서는 return 값인 조회를 알아서 해준다!

            System.out.println(member);



            Connection connection = dataSource.getConnection();
            String sql = "insert into member(name, email, password)values(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getEmail());
            preparedStatement.setString(3, member.getPassword());
            preparedStatement.executeUpdate(); //


        }catch (SQLException e){
            e.printStackTrace();
        }

//        return member;
        return null;
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }

    @Override
    public Member findById(Long id) {
        return null;
    }
}

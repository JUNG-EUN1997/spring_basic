package com.beyond.basic.repository;

import com.beyond.basic.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository //싱글톤 사용
public class MemberJdbcRepositoy implements MemberRepository{
//    Datasource DB와 JDBC에서 사용하는 DB연결 드라이버 객체
//    application.yml에서 설정한 DB정보가 자동으로 주입

    @Autowired
    private DataSource dataSource;

    @Override
    public Optional<Member> save(Member member) { // 회원가입 시, 리턴타입이 어찌될지 모르기 때문에
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

    // Jdbc 사용하면서 불편함이 무엇인지 느끼는 것이 목표
    @Override
    public List<Member> findAll() {
        List<Member> memberList = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM member";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(); // ResultSet에서는 구조화된 데이터가 들어간다.


//            단건 조회 시,resultSet.next() 만 진행하면 된다.
            while (resultSet.next()){ //curser
                Long id = resultSet.getLong("id"); // 구조화가 되어있어 id라는 컬럼명으로 꺼낼 수 있다.
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
//                String password = resultSet.getString("password");

                Member member = new Member();
                member.setId(id);
                member.setName(name);
                member.setEmail(email);
//                member.setPassword(password);

                memberList.add(member);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return memberList;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = new Member();

        try {
            Connection connection = dataSource.getConnection();
            String sql = "SELECT * FROM member WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next(); // 단건조회라 next만

            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            member = new Member();
            member.setId(id);
            member.setName(name);
            member.setEmail(email);
            member.setPassword(password);


        }catch (SQLException e){
            e.printStackTrace();
        }


        return null;
    }
}

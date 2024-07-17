package com.beyond.basic.servletjsp;

import com.beyond.basic.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello/servlet/jsp/get")
public class HelloServletJspGet extends HttpServlet { // 사용자가 서버에게 화면 줘! 하는 것
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("myData","hello world java");
        req.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req, resp);
//        폴더 경로 : src > main > webapp(resources 와 같은 레벨) > ... : 이렇게 찾아가기로 약속 되어있음
    }
}

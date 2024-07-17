<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>hello</title>
</head>
<body>
    <p>data(EL문법) : ${myData}</p>
    <p>data(jstl문법 - java코드) : <%
        String getData = (String)request.getAttribute("myData");
        out.print(getData);
     %></p>
     <form action="/hello/servlet/jsp/post" enctype="multipart/form-data" method="post">
        name : <input name="name" /> <br>
        email : <input name="email" /> <br>
        password : <input name="password" />
        <input type="submit" value="확인" />
     </form>
</body>
</html>
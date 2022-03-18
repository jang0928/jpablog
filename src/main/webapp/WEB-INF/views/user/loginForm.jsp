<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file ="../layout/header.jsp"%>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <form action ="/auth/loginProc" method="post">
      <div class="form-group">
        <label for="username">username:</label>
        <input type="username" name = "username" class="form-control" placeholder="Enter username" id="username">
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password"  name = "password" class="form-control" placeholder="Enter password" id="password">
      </div>
      <button type ="submit" id="btn-login" class="btn btn-primary">로그인</button>
      <a href="https://kauth.kakao.com/oauth/authorize?client_id=5a637510f0c02d730241a661e1af20ba&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img src="/image/kakao_login_button.png"/></a>
    </form>
</div>
</div>

<script src = "/js/user/user.js"></script>
<%@ include file ="../layout/footer.jsp"%>
</body>
</html>



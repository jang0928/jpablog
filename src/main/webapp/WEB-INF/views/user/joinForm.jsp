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
    <form>
      <div class="form-group">
        <label for="username">username:</label>
        <input type="username" class="form-control" placeholder="Enter username" id="username">
      </div>

      <div class="form-group">
         <label for="email">Email address:</label>
         <input type="email" class="form-control" placeholder="Enter email" id="email">
       </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" placeholder="Enter password" id="password">
      </div>

      <div class="form-group form-check">
        <label class="form-check-label">
          <input class="form-check-input" type="checkbox"> Remember me
        </label>
      </div>
    </form>
      <button id="btn-save" class="btn btn-primary">가입완료</button>

</div>
</div>
<script src = "/js/user/user.js"></script>
<%@ include file ="../layout/footer.jsp"%>
</body>
</html>



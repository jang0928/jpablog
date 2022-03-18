<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../layout/header.jsp" %>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<form>
    <input type="hidden" id="id" value="${board.id}">
    <div class="container">
        <div class="form-group">
            <input type="text" value = "${board.title}"name="title" class="form-control" placeholder="Enter title" id="title">
        </div>
        <div class="form-group">
            <textarea rows="5" id ="content" class="form-control summernote">${board.content}</textarea>
        </div>
    </div>
</form>
<button id="btn-update" class="btn btn-primary">수정 완료</button>

<script>
    $('.summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 100
    });
</script>
<script src = "/js/user/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
</body>
</html>



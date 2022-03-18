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
<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning" id="btn-update">수정</a>
        <button class="btn btn-danger" id="btn-delete">삭제</button>
    </c:if>
    <br><br>
    <div>
        글 번호 : <span id="id"><i>${board.id}</i></span>
        작성자 : <span><i>${board.user.username}</i></span>
    </div>
    <div class="form-group">
        <h3>${board.title}</h3>
    </div>
    <hr/>
    <div class="form-group">
        <div>
            ${board.content}
        </div>
    </div>
    <hr/>
</div>
</hr>

</script>
<script src="/js/user/board.js"></script>
<%@ include file="../layout/footer.jsp" %>
</body>
</html>



/*

*/
// arrow 사용 이유ㅠ ? this 를 바인딩하기 위해서
let index = {
    init: function () {
        $('#btn-save').on('click', () => {
            this.save();
        });
        $('#btn-update').on('click', () => {
            this.updateById();
        });
        $('#btn-delete').on('click', () => {
            this.deleteById();
        });
        $('#btn-reply-save').on('click', () => {
            this.replySave();
        });

    },
    save: function () {
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        }
        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset =utf-8",
            dataType: "json"
        })
            .done(function (resp) {
                alert("글쓰기 완료 ");
                location.href = "/"
            }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },
    updateById: function () {
        let id = $('#id').val();
        let data = {
            title: $('#title').val(),
            content: $('#content').val(),
        }
        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset =utf-8",
            dataType: "json"
        })
            .done(function (resp) {
                alert("수정 완료 ");
                location.href = "/"
            }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },
    deleteById: function () {
        let id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id
        })
            .done(function (resp) {
                alert("삭제 완료 ");
                location.href = "/"
            }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },
    replySave: function () {
        let data = {
            content: $('#reply-content').val(),
            boardId: $('#boardId').val(),
            userId: $('#userId').val()
        }

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset =utf-8",
            dataType: "json"
        })
            .done(function (resp) {
                alert("댓글 완료 ");
                location.href = `/board/${data.boardId}`
            }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },
    replyDelete: function (boardId,replyId) {
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        })
            .done(function (resp) {
                alert("댓글 삭제 완료  ");
                location.href = `/board/${boardId}`
            }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    }

}
index.init();

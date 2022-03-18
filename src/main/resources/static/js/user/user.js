
/*

*/
// arrow 사용 이유ㅠ ? this 를 바인딩하기 위해서
let index = {
    init:function(){
        $('#btn-save').on('click',()=>{
            this.save();
        });
        $('#btn-update').on('click',()=>{
            this.update();
        });
    },
    save: function(){
        let data = {
            username : $('#username').val(),
            password : $('#password').val(),
            email : $('#email').val()
        }
        //ajax 를 이용해서 Json으로 변경
        $.ajax({
            type : "POST",
            url : "/auth/joinProc",
            data : JSON.stringify(data),
            contentType : "application/json; charset =utf-8",
            dataType : "json"
        })
        .done(function(resp) {
            alert("회원가입완료");
            location.href= "/"
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    },
    update: function(){
        let data = {
            id: $('#id').val(),
            username : $('#username').val(),
            password : $('#password').val(),
            email : $('#email').val()
        }
        //ajax 를 이용해서 Json으로 변경
        $.ajax({
            type : "PUT",
            url : "/user",
            data : JSON.stringify(data),
            contentType : "application/json; charset =utf-8",
            dataType : "json"
        })
            .done(function(resp) {
                alert("회원 수정 완료 ");
                location.href= "/"
            }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    },

}
index.init();

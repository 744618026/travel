$(document).ready(function (){
    $("body").css("height",$(window).height());
    $(window).resize(function (){
        if(document.documentElement.clientHeight >600){
            $("body").css("height",document.documentElement.clientHeight );
        }
    });
    $(".img").click(function (){
        $("img").attr("src","/travel/code/getCode?"+Math.random());
    });
    let username = false;
    let password = false;
    let code = false;
    $("#exampleInputEmail1").focus(function (){
        username = focus("#exampleInputEmail1")
    })
    $("#exampleInputEmail1").blur(function (){
        username = blur("#exampleInputEmail1");
    });
    $("#exampleInputPassword").focus(function (){
        password = focus("#exampleInputPassword");
    });
    $("#exampleInputPassword").blur(function (){
        password = blur("#exampleInputPassword");
    });
    $("#exampleInputPassword1").focus(function (){
        code = focus("#exampleInputPassword1");
    });
    $("#exampleInputPassword1").blur(function (){
        code = blur("#exampleInputPassword1");
    });
    $("#log-btn").click(function (){
        $("#csrf").val($.cookie("XSRF-TOKEN"))
        if(username&&password&&code){
            $.ajax({
                url:"/travel/login",
                type:"post",
                data:$("#form").serialize(),
                success:function (data){
                   if(data.code == 1){
                       $("#error-text").css("display","block");
                       $("#error-text").html(data.message);
                   }
                },
                complete:function (xhr,data){
                    localStorage.removeItem("token");
                    localStorage.setItem("token",xhr.getResponseHeader("token"));
                    location.href ="/travel/admin/models/index.html";
                }
            })
        }else{
            $("#error-text").css("display","block");
            $("#error-text").html("账户、密码或者验证码不能为空！")
        }
    });
});
function focus(el){
        $("#error-text").css("display","none");
        let value = $(el).val();
        if(value == "" || value == null){
            return false;
        }else{
            return true;
        }
}
function blur(el){
        let value = $(el).val();
        if(value == "" || value == null){
            return false;
        }else{
            return  true;
        }
}
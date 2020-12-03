$(document).ready(function (){
    let flgUsername = false;
    let flgPassword = false;
    let flgCode = false;
    //
    flgUsername = readConfirm("#username");
    flgPassword = readConfirm("#password");
    flgCode = readConfirm("#code");
    $("#username").blur(function () {
        flgUsername = usernameBlur(flgUsername);
    })
    $("#username").focus(function () {
        usernameFocus()
    })

    $("#password").blur(function () {
        flgPassword = passwordBlur(flgPassword);
    })
    $("#password").focus(function () {
        passwordFocus();
    })

    $("#code").blur(function () {
        flgCode = codeBlur(flgCode);
    })
    $("#code").focus(function () {
        codeFocus();
    })
    $("#username").change(function (){
        flgUsername = readConfirm("#username");
    });
    $("#username").change(function () {
        flgPassword = readConfirm("#password");
    })
    $("#btn-log").click(function () {
        if(flgUsername&&flgPassword&&flgCode){
            $.ajax({
                url:"/travel/login",
                type:"post",
                data: $("#form").serialize(),
                success:function (data){
                    if(data.code==1){
                        $("#d").text(data.message);
                        $("#d").css("display","block");
                    }else if(data.code==0){
                        //TODO token用于记录登录状态，需要去header里面获取token
                    }
                }
            })
        }
        else{
            flgUsername = usernameBlur(flgUsername);
            flgPassword = passwordBlur(flgPassword);
            flgCode = codeBlur(flgCode);
        }
    });

//    验证码点击刷新
    $(".pcode").click(function () {
        console.log(1);
        $(".pcode").attr("src","/travel/code/getCode" +"?" + new Date().getTime());
    })
});
function readConfirm(name){
    var el = $(name).val();
    //console.log(el)
    if(el == ''){
        return false;
    }else{
        return true;
    }
}
function usernameBlur(flgUsername){
    var username = document.getElementById("username").value;
    if(username == ""){
        document.getElementById("u").innerHTML = "用户名不能为空";
        document.getElementById("u").style.display="block";
    }else{
        flgUsername = true;
    }
    return flgUsername;
}
function usernameFocus(){
    document.getElementById("u").style.display="none";
}
function passwordBlur(flgPassword){
    var password =document.getElementById("password").value;
    if(password == ""){
        document.getElementById("m").innerHTML = "密码不能为空";
        document.getElementById("m").style.display="block";
    }else{
        flgPassword = true;
    }
    return flgPassword;
}
function passwordFocus() {
    document.getElementById("m").style.display = "none";
}
function codeBlur(flgCode){
    var code = document.getElementById("code").value;
    if(code == ""){
        document.getElementById("c").innerHTML = "验证码为空";
        document.getElementById("c").style.display="block";
    }else{
        flgCode = true;
    }
    return flgCode;
}
function  codeFocus(){
    document.getElementById("c").style.display = "none";
}

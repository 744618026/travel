window.onload=function(){
    let body = document.querySelector("body");
    let width = document.documentElement.clientWidth;
    let height =  document.documentElement.clientHeight;
    body.style.width = width +'px';
    body.style.height = height + "px";
}
window.onresize = function(){
    let body = document.querySelector("body");
    let width = document.documentElement.clientWidth;
    let height =  document.documentElement.clientHeight;
    body.style.width = width +'px';
    body.style.height = height + "px";
}
$(document).ready(function () {
    flgUsername = false;
    $("#username").blur(function (){
        flgUsername = blur("flgUsername","#username",".username_tip");
    })
    $("#username").focus(function () {
        focus(".username_tip");
    })

    flgPassword = false;
    $("#password").blur(function (){
        flgPassword = blur("flgPassword","#password",".password_tip");
    })
    $("#password").focus(function (){
        focus(".password_tip");
    })
    flgConfirmPassword = false;
    $("#confirmpassword").blur(function (){
        flgConfirmPassword = blur("flgConfirmPassword","#confirmpassword",".confirmPassword_tip");
    })
    $("#confirmpassword").focus(function (){
        focus(".confirmPassword_tip");
    })
    flgEmail = false;
    $("#email").blur(function (){
        flgEmail = blur("flgEmail","#email",".email_tip");
    })
    $("#email").focus(function (){
        focus(".email_tip");
    })
    flgTelphone = false;
    $("#telphone").blur(function (){
        flgTelphone = blur("flgTelphone","#telphone",".telphone_tip");
    })
    $("#telphone").focus(function (){
        focus(".telphone_tip");
    })

    var email =false;
    $("#RegistNow").click(function (){
        if(flgUsername&&flgPassword&&flgConfirmPassword&&flgEmail&&flgEmail&&flgTelphone){
            $.ajax({
                url:"/travel/checkEmail",
                method:"get",
                data: $("#form-email").serialize(),
                success: function (data){
                    if(data==1){
                        $("#z").text(data.message)
                        $("#z").css("display","block");
                    }else{
                        email = true;
                        $("#z").text(data.message)
                        $("#z").css("display","block");
                    }
                }
            })
        }
    })

});

function blur(flg,id1,id2){
    let e = $(id1).val();
    if(e==""){
        $(id2).css("display","block");
        flg = false;
    }else{
        flg = true;
    }
    return flg;
}
function focus(id){
    $(id).css("display","none");
}
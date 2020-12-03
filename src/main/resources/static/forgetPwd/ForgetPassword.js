$(document).ready(function (){
    let flgUsername = false;
    let flgCode = false;

    $(".input_find_username").blur(function (){
        flgUsername = blur(flgUsername,"#input_find_username","#tipdiv-error-text");
    })
    $(".input_find_username").focus(function (){
        focus("#tipdiv-error-text");
    })
    $(".input_find_code").blur(function (){
        flgCode = blur(flgCode,"#input_find_code",".code-position");
    })
    $(".input_find_code").focus(function (){
        focus(".code-position");
    })
    //第一个页面不能为空

    var trueUsername = false;
    var email_username = null;
    var code_username = null;
    $("#btn-certain").click(function (){
        if(flgUsername&&flgCode){
            //console.log(11111)
            $.ajax({
                url:"/travel/checkUser",
                type:"get",
                data:$("#form_username").serialize(),
                success:function (data){
                    if(data.code == 1){
                        $("#w").text(data.message);
                        $("#w").css("display","block");
                    }else{
                        email_username = $("#input_find_username").val();
                        code_username = $("#input_find_username").val();
                        $("#a1").addClass("next");
                        $("#w").css("display","none");
                        $(".first").css("display","none");
                        $(".second").css("display","block");
                        trueUsername = true;
                    }
                }
            })
        }else {
            flgUsername = blur(flgUsername,"#input_find_username","#tipdiv-error-text");
            flgCode = blur(flgCode,"#input_find_code",".code-position");
        }
    })
    //第一个页面的提交验证

    let flgEmail = false;
    $(".input_email").blur(function () {
        flgEmail = blur(flgEmail,"#input_email",".tip-error-email");
    })
    $(".input_email").focus(function () {
        focus(".tip-error-email");
    })

    $("#btn-certain-email").click(function () {

        if(flgEmail&&trueUsername){
            $("#email_username").val(email_username);
            $.ajax({
                url: "/travel/password/forget",
                type: "get",
                data: $("#form_email").serialize(),
                success: function (data){
                    if(data.code==1){
                        $("#w").text(data.message);
                        $("#w").css("display","block");
                    }else{
                        $("#a2").addClass("next");
                        $("#w").css("display","none");
                        $(".second").css("display","none");
                        $(".third").css("display","block");
                    }
                }
            })
        }
        else{
            flgEmail = blur(flgEmail,"#input_email",".tip-error-email");
        }
    })
    //第二次验证

    let flgPassword = false;
    let flgCertainPassword = false;

    $("#input_newCode").blur(function () {
        flgPassword = blur("flgPassword","#input_newCode","#tip_newCode");
    })
    $("#input_newCode").focus(function () {
        focus("#tip_newCode");
    })
    $("#input_newCodeAgain").blur(function () {
        flgCertainPassword = blur("flgCertainPassword","#input_newCodeAgain","#tip_newCodeAgain");
    })
    $("#input_newCodeAgain").focus(function () {
        focus("#tip_newCodeAgain");
    })

    var certaninCode = false;
    $("#btn-newCode").click(function (){
        certaninCode = validate("certainCode");
        if(certaninCode==true){
            $("#code_username").val(code_username);
            $.ajax({
                url:"/travel/updatePassword",
                type : "post",
                data: $("#changePW").serialize(),
                success: function (data){
                    if(data==1){
                        $("#w").text(data.message);
                        $("#w").css("display","block");
                    }else{
                        $("#a3").addClass("next");
                        $("#w").css("display","none");
                        $(".third").css("display","none");
                        $(".fourth").css("display","block");

                    }
                }
            })
        }
        else{
            $("#w").text("两次密码不相同");
            $("#w").css("display","block");
        }
    })

    //    验证码点击刷新

    $(".pcode").click(function () {
        console.log(1);
        $(".pcode").attr("src","/travel/code/getCode" +"?" + new Date().getTime());
    })
})

/**
 *
 * @param flg 判断账号为空的结果
 * @param id1 定位input
 * @param id2 定位提醒框
 */
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

function validate(flg) {
    var flgNewPasssword = $("#input_newCode").val();
    var flgNewPasswordAgain = $("#input_newCodeAgain").val();
    if(flgNewPasssword != flgNewPasswordAgain){
        // $("#w").html("两次密码不相同");
        flg = false;
    }
    else{
        flg = true;
    }
    return flg;
}
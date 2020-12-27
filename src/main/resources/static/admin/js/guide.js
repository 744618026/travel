$(document).ready(function (){
    let token = localStorage.getItem("token");
    let body = $("body");
    let form = $("<form></form>");
    let input = $("<input/>")
    let csrf = $.cookie("XSRF-TOKEN");
    input.attr("name","_csrf");
    input.val(csrf);
    input.attr("type","hidden");
    let f_input = $("<input/>")
    f_input.val(token);
    f_input.attr("name","token");
    f_input.attr("type","hidden");
    form.append(input);
    form.append(f_input);
    body.append(form);
    $.ajax({
        url:"/travel/getUserInfo",
        type: "POST",
        dataType:"json",
        data:form.serialize(),
        success:function (data){
            if(data.code==0){

            }else{
                location.href="/travel/adminlogin/adminlog.html";
            }
        },
        error:function (){
              location.href="/travel/adminlogin/adminlog.html";
        }
    });
    let content =  document.querySelector(".content");
    content.style.height = window.innerHeight -70 +"px";
    let table = document.querySelector(".table");
    table.style.width = window.innerWidth - 170 +"px";
    table.style.height = window.innerHeight - 70 + "px";
    let list = document.querySelector(".list");
    list.style.height = window.innerHeight -70 - 70 +"px";
    $(window).resize(function (){
        let body =  document.querySelector("body");
        body.style.height = window.innerHeight +"px";
        let content =  document.querySelector(".content");
        content.style.height = window.innerHeight -70 +"px";
        if($(window).width()>1430){
            $(".table").css("width",$(window).width()-170);
        }else{
            $(".table").css("width","1260px");
        }
        $(".table").css("height",$(window).height()-70);
        let list = document.querySelector(".list");
        list.style.height = window.innerHeight -70 - 70 +"px";
    });
    $(".item").click(function (){
        let caret = $(this).find("a>caret");
        let div = $(this).find("span").html();
        if(caret.hasClass("rotate")){
            caret.removeClass("rotate");
            $(this).css("border-bottom","1px solid #888888")
            $(div).hide();
        }else{
            caret.addClass("rotate");
            $(this).css("border","none");
            $(div).show();
        }

    });
    $.ajax({
        url:"/travel/region/getRegions",
        type:"get",
        success:function (data){
            if(data.code==0){
                let regions = data.data;
                if(localStorage.getItem("current-region")==null || localStorage.getItem("current-region-id")==null){
                    localStorage.setItem("current-region",regions[0].name)
                    localStorage.setItem("current-region-id",regions[0].regionId);
                }
                for(let i=0;i<Object.keys(regions).length;i++){
                    let div = $("<div></div>");
                    div.addClass("item");
                    div.html(regions[i].name);
                    div.click(function (){
                        localStorage.removeItem("current-region");
                        localStorage.removeItem("current-region-id");
                        localStorage.setItem("current-region",regions[i].name);
                        localStorage.setItem("current-region-id",regions[i].regionId);
                        location.reload();
                    });
                    $(".region").append(div);
                }
                localStorage.setItem("regionNum",Object.keys(regions).length.toString());
            }
        }
    })
    $("#current-region").html(localStorage.getItem("current-region"));
    $("#current-re").html(localStorage.getItem("current-region"));
    $(".active").click(function (){
        location.reload();
    });
})
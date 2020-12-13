$(document).ready(function (){
    let el = document.querySelector(".nav-ul");
    el.style.height = window.innerHeight - 70 + "px";
    let table = document.querySelector(".table");
    table.style.width = window.innerWidth - 170 +"px";
    table.style.height = window.innerHeight - 70 + "px";;
    let list = document.querySelector(".list");
    list.style.height = window.innerHeight -140 + "px";
    $(window).resize(function (){
        if($(window).width()>1430){
            $(".table").css("width",$(window).width()-170);
        }else{
            $(".table").css("width","1260px");
        }
        $(".nav-ul").css("height",$(window).height()-70);
        $(".table").css("height",$(window).height()-70);
        $(".list").css("height",$(".nav-ul").css("height")-70)
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
    $("#current-re").html(localStorage.getItem("current-region"))
})
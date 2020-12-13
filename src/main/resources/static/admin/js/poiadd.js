$(document).ready(function (){
    $("#region-name").html(localStorage.getItem("current-region"));
    $("#sub-btn").click(function (){
        $("#region-id").val(localStorage.getItem("current-region-id"));
        let name = $("#poi-name").val();
        let price = $("#price").val();
        let stock = $("#stock").val();
        let regionId =  $("#region-id").val();
        if(name!=""&&price!=""&&stock!=""&&regionId!=""){
            let page = localStorage.getItem("poiTotalPage")==0? 1:localStorage.getItem("poiTotalPage");
            localStorage.removeItem("poiTotalPage");
            $("#page-input").val(page);
            $.ajax({
                url:"/travel/admin/poi/add",
                type:"post",
                data:$("#form").serialize(),
                success:function (data) {
                    if(data.code==0){
                        $("#error-tip").html("添加成功！");
                        $("#error-tip").css("display","block");
                        setTimeout(function (){
                            location.href="/travel/admin/poi/list";
                        },1000)
                    }else{
                        $("#error-tip").html(data.message);
                        $("#error-tip").css("display","block");
                    }
                }
            })
        }else{
            $("#error-tip").html("所填信息除景点描述外都不能为空！");
            $("#error-tip").css("display","block");
        }
    });
    if(localStorage.getItem("poiTotalPage")==null){
        $.ajax({
            url:"/travel/poi/totalPage",
            type: "get",
            data: {"region":localStorage.getItem("current-region-id")},
            success:function (data){
                if(data.code==0){
                    localStorage.setItem("poiTotalPage",data.data);
                }
            }
        })
    }
})
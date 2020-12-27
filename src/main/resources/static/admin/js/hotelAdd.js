$(document).ready(function (){
    $("#region-name").html(localStorage.getItem("current-region"));
    $("#sub-btn").click(function (){
        $("#region-id").val(localStorage.getItem("current-region-id"));
        $("#csrf").val($.cookie("XSRF-TOKEN"));
        $.ajax({
            url:"/travel/admin/hotel/add",
            type:"post",
            headers:{"Authorization":localStorage.getItem("token")},
            data:$("#form").serialize(),
            success:function (data){
                if(data.code==0){
                    alert("添加成功！");
                }else{
                    alert("添加失败："+data.message);
                }
            }
        })
    });
});
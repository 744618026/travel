$(document).ready(function (){
    var size = 1;
    var total = 1;
    load(1);
    new Pagination({
        element: '#pages', // 元素
        type: 1, // 样式类型，可选[1,2]
        pageIndex: 1, // 初始页码
        pageSize: size, // 每页数量
        pageCount:5, // 页码数量
        total: size*total, // 数据总条数
        jumper: false, // 显示输入框跳转
        singlePageHide: false, // 只有一页时不显示分页
        prevText: '上一页', // 上一页文字
        nextText: '下一页', // 下一页文字
        disabled: true, // 是否显示禁用
        currentChange: function(index) {
            // 页码改变时回调
            load(index);
        }
    });
    function load(page){
        $.ajax({
            url:"/travel/admin/hotel/list",
            type: "post",
            data:{"page":page,"regionId":localStorage.getItem("current-region-id"),"_csrf":$.cookie("XSRF-TOKEN")},
            headers:{"Authorization":localStorage.getItem("token")},
            success:function (data){
                if(data.code==0){
                    let dats = data.data.data;
                    for(let i=0;i<Object.keys(dats).length;i++){
                        let body = $("<tr></tr>");
                        let td1 = $("<td></td>");
                        td1.html(dats[i].hotelId);
                        body.append(td1);
                        let td2 = $("<td></td>");
                        td2.html(dats[i].hotelName);
                        body.append(td2);
                        let td3 = $("<td></td>");
                        let textArea = $("<textarea></textarea>")
                        textArea.html(dats[i].hotelDescribe);
                        textArea.attr("readonly",true);
                        td3.append(textArea)
                        body.append(td3);
                        let td4 = $("<td></td>");
                        let textArea1 = $("<textarea></textarea>")
                        textArea1.html(dats[i].address);
                        textArea1.attr("readonly",true);
                        td4.append(textArea1)
                        body.append(td4);
                        let td8 = $("<td></td>");
                        let a = $("<a></a>");
                        a.html("信息管理");
                        a.click(function (){
                            $("#table-data").css("display","none");
                            $(".modify").css("display","block");
                            manage(dats[i].hotelId,dats[i].hotelName,dats[i].hotelDescribe,dats[i].address,dats[i].phone
                            ,dats[i].info,dats[i].policy);
                        });
                        td8.append(a);
                        let td5 = $("<td></td>");
                        let a2 = $("<a></a>");
                        a2.html("商品管理");
                        a2.click(function (){

                        });
                        td5.append(a2);
                        body.append(td8);
                        body.append(td5);
                        $("#data").append(body);
                    }
                    size = data.data.size;
                    total = data.data.total;
                }
            }
        })
    }
    function manage(id,name,des,address,phone,info,policy){
        let span = $("<span></span>")
        span.html(">");
        let a = $("<a></a>");
        a.html("管理");
        $(".top").append(span);
        $(".top").append(a);
        $("#hotel-id").val(id);
        $("#hotel-name").val(name);
        $("#hotel-describe").val(des);
        $("#hotel-address").val(address);
        $("#hotel-phone").val(phone);
        $("#hotel-info").val(info);
        $("#hotel-policy").val(policy);
        $("#pic-sub").click(function (){
            $("#img-hotel-id").val(id);
            let file=$("#img-input").val();
            if(file ==""){
                alert("请添加图片！");
            }else{
                let point = file.lastIndexOf(".");
                let type = file.substr(point);
                if(type==".jpg"||type==".png"||type==".JPG"||type==".PNG"||type==".jpeg"||type==".JPEG"){
                    $.ajax({
                        url:"/travel/admin/poi/add/image",
                        type:"POST",
                        data: new FormData($("#img-form")[0]),
                        headers:{"Authorization":localStorage.getItem("token")},
                        processData:false,   //  告诉jquery不要处理发送的数据
                        contentType:false,
                        success:function (data){
                            if(data.code==0){
                                alert("上传成功！");
                            }else{
                                alert("上传失败！");
                            }
                        }
                    })
                }else{
                    alert("请上传jpg、png或者jpeg格式的图片！");
                }
            }
        });
        $("#poi-modify").click(function (){
            $("#hotel-region").val(localStorage.getItem("current-region-id"));
            $("#hotel-f-id").val(id);
            if($("#hotel-name").val()!=name|| $("#hotel-describe").val()!=des||$("#hotel-info").val()!=info||$("#hotel-address").val()!=address
                ||$("#hotel-phone").val()!=phone||$("#hotel-policy").val()!=policy){
                $.ajax({
                    url:"/travel/admin/hotel/update",
                    type:"post",
                    data:$("#poi-form").serialize(),
                    headers:{"Authorization":localStorage.getItem("token")},
                    success:function (data){
                        if(data.code==0){
                            alert("修改成功！");
                            location.reload();
                        }else{
                            alert("修改失败："+data.message);
                        }
                    }
                })
            }else{
                alert("信息暂未修改！");
            }
        });
        loadImages(id);
    }
    function loadImages(id){
        $(".zoomImgBox").html("");
        $.ajax({
            url:"/travel/hotel/images",
            type:"get",
            data:{"hotelId":id},
            success:function (data){
                if(data.code==0){
                    let datas = data.data;
                    let length = Object.keys(datas).length;
                    for(let i=0;i<length;i++){
                        let img = $("<img>");
                        img.attr("src",datas[i].url);
                        img.addClass("zoomImg");
                        img.attr("data-caption",datas[i].url);
                        $(".zoomImgBox").append(img);
                    }
                    showZoomImg(".zoomImg","img");
                }
            }
        })
    }
});
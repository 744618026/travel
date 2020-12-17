class poi{
    constructor(id,name,describe,price,stock) {
        this.id = id;
        this.name =name;
        this.describe = describe;
        this.price = price;
        this.stock = stock;
    }
}
$(document).ready(function (){
    var page = 1;
    let datasum = 1;
    load(1);
    new Pagination({
        element: '#pages', // 元素
        type: 1, // 样式类型，可选[1,2]
        pageIndex: 1, // 初始页码
        pageSize: 15, // 每页数量
        pageCount:5, // 页码数量
        total: datasum, // 数据总条数
        jumper: false, // 显示输入框跳转
        singlePageHide: false, // 只有一页时不显示分页
        prevText: '上一页', // 上一页文字
        nextText: '下一页', // 下一页文字
        disabled: true, // 是否显示禁用
        currentChange: function(index) {
            // 页码改变时回调
            load(index);
            page = index;
        }
    });
    function load(page){
        $.ajax({
            url:"/travel/admin/poi/getPOI",
            type: "get",
            data:{"page":page,"regionId":localStorage.getItem("current-region-id")},
            success:function (data){
                if(data.code==0){
                    let dats = data.data;
                    for(let i=0;i<Object.keys(dats).length;i++){
                        let body = $("<tr></tr>");
                        let td1 = $("<td></td>");
                        td1.html(dats[i].poiId);
                        body.append(td1);
                        let td2 = $("<td></td>");
                        td2.html(dats[i].name);
                        body.append(td2);
                        let td3 = $("<td></td>");
                        let textArea = $("<textarea></textarea>");
                        textArea.html(dats[i].describe);
                        textArea.attr("readonly",true);
                        td3.append(textArea)
                        body.append(td3);
                        let td4 = $("<td></td>");
                        td4.html(dats[i].ticketPrice);
                        body.append(td4);
                        let td5 = $("<td></td>");
                        td5.html(dats[i].ticketStock);
                        body.append(td5);
                        let td7 = $("<td></td>");
                        td7.html(dats[i].createTime);
                        body.append(td7);
                        let td6 = $("<td></td>");
                        td6.html(dats[i].updateTime);
                        body.append(td6);
                        let td8 = $("<td></td>");
                        let a = $("<a></a>");
                        a.html("管理");
                        a.click(function (){
                            manage(new poi(dats[i].poiId,dats[i].name,dats[i].describe,dats[i].ticketPrice,dats[i].ticketStock));
                        });
                        td8.append(a);
                        body.append(td8);
                        $("#data").append(body);
                    }
                }
            }
        })
    }
    function manage(poi){
        $("#table-data").css("display","none");
        $(".modify").css("display","block");
        let span = $("<span></span>")
        span.html(">");
        let a = $("<a></a>");
        a.html("管理");
        $(".top").append(span);
        $(".top").append(a);
        $("#poi-id").val(poi.id);
        $("#poi-name").val(poi.name);
        $("#poi-describe").val(poi.describe);
        $("#poi-price").val(poi.price);
        $("#poi-stock").val(poi.stock);
        $("#img-input").change(function (){
            $("#img").attr("src",URL.createObjectURL($(this)[0].files[0]));
        });
        $("#poi-modify").click(function (){
            $("#poi-region").val(localStorage.getItem("current-region-id"));
            $("#poiId").val(poi.id);
            if($("#poi-name").val()!=poi.name|| $("#poi-describe").val()!=poi.describe||$("#poi-stock").val()!=poi.stock||$("#poi-price").val()!=poi.price){
                $.ajax({
                        url:"/travel/admin/poi/update",
                        type:"post",
                        data:$("#poi-form").serialize(),
                        success:function (data){
                            if(data.code==0){
                                alert("修改成功！");
                            }else{
                                alert("修改失败："+data.message);
                            }
                        }
                    })
            }else{
                alert("信息暂未修改！");
            }
        });
        loadImages(poi.id);
        $("#pic-sub").click(function (){
            $("#img-poi-id").val(poi.id);
            let file=$("#img-input").val();
            if(file ==""){
                alert("请添加图片！");
            }else{
                let point = file.lastIndexOf(".");
                let type = file.substr(point);
                if(type==".jpg"||type==".png"||type==".JPG"||type==".PNG"||type==".jpeg"||type==".JPEG"){
                    $.ajax({
                        url:"/travel/admin/poi/image/upload",
                        type:"POST",
                        data: new FormData($("#img-form")[0]),
                        processData:false,   //  告诉jquery不要处理发送的数据
                        contentType:false,
                        success:function (data){
                            if(data.code==0){
                                alert("上传成功！");
                                loadImages(poi.id);
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
    }
    function loadImages(id){
        $(".zoomImgBox").html("");
        $.ajax({
            url:"/travel/poi/images",
            type:"get",
            data:{"poiId":id},
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
})
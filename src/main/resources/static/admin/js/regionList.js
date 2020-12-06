$(document).ready(function (){
    $("#add-btn").click(function (){
        if($("#region-input").val()!==""){
            $.ajax({
                url:"/travel/admin/region/add",
                type:"post",
                data:$("#add-form").serialize(),
                success:function (data){
                    if(data.code == 0){
                        location.reload();
                    }else{
                        $(".error").css("display","block");
                        $("#error-tip").html(data.message);
                    }
                }
            })
        }
    });
    let datasum = parseInt(localStorage.getItem("regionNum"));
    new Pagination({
       element: '#pages', // 元素
       type: 1, // 样式类型，可选[1,2]
       pageIndex: 1, // 初始页码
       pageSize: 15, // 每页数量
       pageCount:Math.ceil(datasum/15), // 页码数量
       total: datasum, // 数据总条数
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
    load(1);
    $("#totalPage").val(Math.ceil(datasum/15));
    function load(page) {
        $.ajax({
            url:"/travel/admin/region",
            type:"get",
            data: {"page":page},
            success:function (data){
                if(data.code==0){
                    let datas = data.data;
                    let body = $("#data");
                    body.html("");
                    for(let i=0;i<Object.keys(datas).length;i++){
                        let tr = $("<tr></tr>");
                        let td1 = $("<td></td>");
                        td1.html(datas[i].regionId);
                        let td2 = $("<td></td>")
                        td2.html(datas[i].name);
                        let td3 = $("<td></td>");
                        td3.html(datas[i].province);
                        let td4 = $("<td></td>");
                        td4.html("修改");
                        tr.append(td1);
                        tr.append(td2);
                        tr.append(td3);
                        tr.append(td4);
                        body.append(tr);
                    }
                }else{
                    console.log(data.message);
                }
            }
        })
    }
    $.ajax({
        url:"/travel/admin/js/province.json",
        type:"get",
        success:function (data){
            $("#province-input").val(data[0].name);
            for(let i=0;i<Object.keys(data).length;i++){
                let op = $("<option></option>")
                op.html(data[i].name);
                $("#province").append(op);
            }
            let options = $("#province").find("option");
            options.click(function (){
                $("#province-input").val($(this).html());
            });
        }
    });

})
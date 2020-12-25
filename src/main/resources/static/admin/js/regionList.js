$(document).ready(function (){
    let datasum = parseInt(localStorage.getItem("regionNum"));
    var page = 1;
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
    function load(page) {
        $.ajax({
            url:"/travel/admin/region",
            type:"get",
            data: {"page":page},
            success:function (data){
                if(data.code==0){
                    let datas = data.data.data;
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
                        td4.addClass("modify-btn")
                        td4.click(function (){
                            modify(datas[i].regionId,datas[i].name,datas[i].province);
                        });
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
                let op2 = $("<option></option>")
                op2.html(data[i].name);
                $(".province").append(op2);
            }
            let options = $("#province").find("option");
            options.click(function (){
                $("#province-input").val($(this).html());
            });
            let ops = $(".province").find("option");
            ops.click(function (){
                $("#province-m-input").val($(this).html());
            });
        }
    });
    $("#add-btn").click(function (){
        if($("#region-input").val()!==""){
            $.ajax({
                url:"/travel/admin/region/add",
                type:"post",
                data:$("#add-form").serialize(),
                success:function (data){
                    if(data.code == 0){
                        $(".error").css("display","block");
                        $("#error-tip").html("添加成功！");
                        setTimeout(function (){
                           location.reload();
                        },2000);
                    }else{
                        $(".error").css("display","block");
                        $("#error-tip").html(data.message);
                    }
                }
            })
        }
    });
    $("#search-btn").click(function (){
        let search = $(".search-input").val();
        if(search !=""){
            urlAdd("搜索");
            $("table").remove("tbody[class='search-body']");
            $.ajax({
                url:"/travel/admin/region/search",
                type:"post",
                data:{"search":search},
                success:function (data){
                    if(data.code==0){
                        let body = $("<tbody></tbody>");
                        body.addClass("search-body")
                        $("#data").css("display","none");
                        $("#pages").css("display","none");
                        let datas = data.data;
                        if(Object.keys(datas).length==0){
                            body.html("搜索结果为空");
                        }else{
                            for(let i=0;i<Object.keys(datas).length;i++){
                                let tr = $("<tr></tr>");
                                let td1 = $("<td></td>");
                                td1.html(datas[i].regionId);
                                let td2 = $("<td></td>")
                                td2.html(datas[i].name);
                                let td3 = $("<td></td>");
                                td3.html(datas[i].province);
                                let td4 = $("<td></td>");
                                td4.addClass("modify-btn");
                                td4.html("修改");
                                td4.click(function (){
                                    modify(datas[i].regionId,datas[i].name,datas[i].province);
                                });
                                tr.append(td1);
                                tr.append(td2);
                                tr.append(td3);
                                tr.append(td4);
                                body.append(tr);
                            }
                        }
                        $("table").append(body);
                    }else {
                        console.log(data.message);
                    }
                }
            })
        }

    });
    var names,provinces;
    function modify(id,name,province){
        names = name;provinces = province;
        $(".table-content").css("display","none");
        $(".modify").css("display","block");
        urlAdd("修改");
        $("#region-id").html(id);
        $("#region-id-input").val(id);
        $("#region-name").val(name);
        $("#province-m-input").val(province);
        let ops = $(".province").find("option:contains('"+province+"')");
        ops.attr("selected",true);
    }
    $("#modify-btn").click(function (){
        if($("#region-name").val()==names && $("#province-m-input").val()==provinces){
            $(".table-content").css("display","block");
            $(".modify").css("display","none");
        }else{
            $("#modify-page").val(page);
            $.ajax({
                url:"/travel/admin/region/update",
                type:"post",
                data:$("#modify-form").serialize(),
                success:function (data){
                    if(data.code==0){
                        $("#modify-error").css("display","block");
                        $("#modify-error").html("修改成功！");
                        setTimeout(function (){
                            location.reload();
                        },2000);
                    }else{
                        $("#modify-error").css("display","block");
                        $("#modify-error").html(data.message);
                    }
                }
            })
        }
    })
    function urlAdd(name){
        let a = $("<a></a>");
        a.html(name);
        let span = $("<span></span>")
        span.html(">");
        $(".top").append(span);
        $(".top").append(a);
    }
})
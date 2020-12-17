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
            url:"/travel/admin/hotel/list",
            type: "post",
            data:{"page":page,"regionId":localStorage.getItem("current-region-id")},
            success:function (data){
                if(data.code==0){
                    let dats = data.data;
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
                        td3.append(textArea)
                        body.append(td3);
                        let td8 = $("<td></td>");
                        let a = $("<a></a>");
                        a.html("管理");
                        a.click(function (){
                            //manage(new poi(dats[i].poiId,dats[i].name,dats[i].describe,dats[i].ticketPrice,dats[i].ticketStock));
                        });
                        td8.append(a);
                        body.append(td8);
                        $("#data").append(body);
                    }
                }
            }
        })
    }
})
$(document).ready(function (){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    if(month<10){
        month = "0"+month;
    }
    if(day<10){
        day="0"+day;
    }
    $("#indate1").val(year+"-"+month+"-"+day);
    $(".input_btnselect").click(function (){
        //计算入住天数
        var Indate = $(".indate1").val();
        var y1 = Indate.split("-")[0];
        var m1 = Indate.split("-")[1];
        var d1 = Indate.split("-")[2];
        var time1 = new Date(y1,m1,d1);
        var Outdate = $(".outdate1").val();
        var y2 = Outdate.split("-")[0];
        var m2 = Outdate.split("-")[1];
        var d2 = Outdate.split("-")[2];
        var time2 = new Date(y2,m2,d2);
        var days = (time2-time1)/1000/60/60/24;
        if(days > 0){
            $("#night").text(function (){
                return days+"晚";
            })
            $("#night").css("display","block");
        }
        else{
            $("#warn").text(function (){
                return "退房时间要在入住时间之前!";
            })
        }


        //入住城市不为空

    })

})

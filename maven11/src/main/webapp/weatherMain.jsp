<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="js/Json2.js"></script>

</head>
<body>
<script type="text/javascript">


    $.ajax({
        type: 'GET',
        url: 'https://api.openweathermap.org/data/2.5/onecall?',
        data: 'lat=35.6895&lon=139.6917&lang=ja&appid=d9d4bfdee6a7073f43776c1a95f4c36b&units=metric',
        dataType: 'JSON',
        error: function () {
            alert('网络错误');
        },
        success: function (res) {


                for (var i=0;i<res.daily.length-1;i++){

                    var p=res.daily[i];

                    for (var j=0;j<p.weather.length;j++) {
                        var w = p.weather[j];
                        //将温度取整
                        var o = parseInt(p.temp.day);
                        var t = new Date();
                        //本月最大日数
                        var maxDay=new Date(t.getFullYear(),(t.getMonth()+1),0).getDate();
                        //var  nowday =  (t.getMonth() + 1) + "月" + (t.getDate()+1)+"日" ;
                        var month = (t.getMonth() + 1) + "月";
                        var day = (t.getDate() + i) + "日";

                        if (day>maxDay){
                            var month2=(t.getMonth() + 2) + "月";
                            var day2=0;
                            day2++;
                            $('div').append('<img src="http://openweathermap.org/img/wn/'+w.icon+'@2x.png" width="32px">'+
                                "   " + month2 + day2 + "   " + '東京' + "   " + w.description + "   " + o + "℃" + '<br>');
                        }

                     /*   //判断日期是否大于最大数
                        if (day>maxDay){
                            //说明日期不合法 要重新new 时间 求出当月
                            var t2= new Date();
                            var month2= (t.getMonth() + 1) + "月";
                            var day2= (t.getDate() + i) + "日";

                            $('div').append('<img src="http://openweathermap.org/img/wn/'+w.icon+'@2x.png" width="32px">'+
                                "   " + month2 + day2 + "   " + '東京' + "   " + w.description + "   " + o + "℃" + '<br>');
                     }*/



                       /* $('div').append(
                           "   " + month + day + "   " + '東京' + "   " + w.description + "   " + o + "℃" + '<br>');*/
                        $('div').append('<img src="http://openweathermap.org/img/wn/'+w.icon+'@2x.png" width="32px">'+
                            "   " + month + day + "   " + '東京' + "   " + w.description + "   " + o + "℃" + '<br>');
                        }
                    }
            }
    });
</script>
<div></div>
</body>
</html>
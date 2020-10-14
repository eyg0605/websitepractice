<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Simple Map Sample</title>
    <meta charset="utf-8">
    <style>
        /* マップを表示する div 要素の高さを必ず明示的に指定します。 */
        #map {
            height: 400px;
            width: 600px;
        }
    </style>
</head>

<body>
<div id="map"></div><!-- 地図を表示する div 要素（id="map"）-->
<script>
    var map;
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -34.397, lng: 150.644},
            zoom: 8
        });
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCe5aZKCIbKBBTUiCbV16cnAsmUS2k57qY&callback=initMap" async defer></script>
</body>
<form action="${pageContext.request.contextPath}/ServletDemo1">
    <input type="text" placeholder="请输入查询地址" name="place"/>
    <input type="submit" value="搜索"/>
</form>
</html>

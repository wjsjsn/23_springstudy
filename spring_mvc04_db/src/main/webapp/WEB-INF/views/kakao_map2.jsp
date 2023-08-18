<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
}

#header{
z-index:90;
position:absolute;
top:50%;
left:50%;
 transform: translate(-50%, -50%);

}

#map{
z-index:10;
position:absolute;
}


</style>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3f374eeb5cabc376e5c41798ac75f178"></script>
    <script type="text/javascript">
        navigator.geolocation.getCurrentPosition((position) => {
            var lat = position.coords.latitude;
            var lon = position.coords.longitude;
            
            document.getElementById("lat").innerHTML = lat;
            document.getElementById("lon").innerHTML = lon;
            geo_map(lat,lon);    
        });
    </script>
</head>
<body>
    <div id="header" style="text-align:center;">
        <h1>kakao jido (nae wi chi)</h1>
        <h2><span id="lat">위도 : </span><span id="lon"> 경도 : </span></h2>
    </div>

    <!-- 지도를 표시할 div 입니다 -->
    <div id="map" style="width:100%;height:100vh;"></div>
    
    <script>
    function geo_map(lat, lon) {
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = { 
                center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };
        
        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        var map = new kakao.maps.Map(mapContainer, mapOption); 
    }
    
    </script>
</body>
</html>
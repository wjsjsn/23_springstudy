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
top:10%;
left:80%;
 transform: translate(-50%, -50%);
background-image: linear-gradient(lavender,snow);
  animation: mymove 3s;
  animation-iteration-count: 8;
}


@keyframes mymove {
  from {top: 0px;}
  to {top: 100%;}
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
		<h1>kakao jido </h1>
		<h1>(nae wi chi: mako, een pho euw)</h1>
		<h2>위도 :<span id="lat"> </span> 경도 :<span id="lon"> </span></h2>
		<button onclick="history.go(-1)"> GO BACK</button>
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

	var map = new kakao.maps.Map(mapContainer, mapOption);

	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(lat, lon); 

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);

	var iwContent = '<div style="padding:5px;"><a href="http://ictedu.co.kr">한국 ICT 인재개발원</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	    iwPosition = new kakao.maps.LatLng(lat, lon); //인포윈도우 표시 위치입니다

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
	});
	  
	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	infowindow.open(map, marker);  
	}
	
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>销售店查询</title>
<%@ include file="/common/pre.jsp"%>
<!--  script type="text/javascript" src='http://api.map.baidu.com/api?type=quick&ak=goT4KEnxiuN4FEQ4mF3XUdcv&v=2.0'></script-->

<!-- script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=goT4KEnxiuN4FEQ4mF3XUdcv"></script -->
<body>
<div id='allmap' style='width:600px;height:400px;'>


</div>



<script>
	var point = {
			"latitude": 39.9251311,
			"longitude": 116.61289719999999
	};
	var geo = new Util.Geo();
	//geo.getLocalPoint(function(p){
	// 	console.log(p);
	//});
	//geo.getCityByPoint(point, function(p){
	//	alert(p.address);
	//});
	window.addMarker = function (map, point){
	  	var marker = new BMap.Marker(point);
  		map.addOverlay(marker);
	};
	geo.initBMap(function(){
		
 		//window.map = new BMap.Map("allmap");
 		//var point = new BMap.Point(116.404, 39.915); 
 		//map.centerAndZoom(point,15);  
 		//map.enableScrollWheelZoom(); 	
		//geo.bindMap('allmap');
		
		var bdmap = geo.createMap('allmap', [point.longitude, point.latitude]);
		bdmap.addMark([[116.404, 39.915]]);
 		
		// 随机向地图添加25个标注
		var bounds = bdmap.bdmap.getBounds();
		var sw = bounds.getSouthWest();
		var ne = bounds.getNorthEast();
		var lngSpan = Math.abs(sw.lng - ne.lng);
		var latSpan = Math.abs(ne.lat - sw.lat);
		for (var i = 0; i < 25; i ++) {
		  //var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
		  //addMarker(point);
		  //bdmap.addMark([{
		  //	  "a":sw.lng + lngSpan * (Math.random() * 0.7), 
		  //	  "b":ne.lat - latSpan * (Math.random() * 0.7),
		  //}], 'a', 'b');
		}
		bdmap.navigate("草房", "成都");
		
		
		//window.bdmap = new BMap.Map('allmap');;
		//window.map = new BMap.Map("allmap");
	});
	
// 	var map1 = new BMap.Map("allmap");
// 	var point = new BMap.Point(116.404, 39.915); 
// 	map1.centerAndZoom(point,15);  
// 	map1.enableScrollWheelZoom();  
</script>
</body>
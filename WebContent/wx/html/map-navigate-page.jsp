<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>

<head>
<title>地图导航</title>
<meta name="viewport"
	content="user-scalable=no, width=device-width, initial-scale=1.0" />
<meta charset="utf-8" />
<link rel="stylesheet"
	href="${ctx }/scripts/bdmap/css/jquery.mobile.min.css" />
<link rel="stylesheet"
	href="${ctx }/scripts/bdmap/css/theme/theme.min.css" />
<link rel="stylesheet" href="${ctx }/scripts/bdmap/css/styles.css" />
<%@ include file="/common/pre.jsp"%>
<script src="${ctx }/scripts/bdmap/js/jquery.min.js"></script>
<script src="${ctx }/scripts/bdmap/js/jquery.mobile.min.js"></script>
<script src="${ctx}/scripts/carinfo/iscroll.js" type="text/javascript"></script>
<link href="${ctx }/scripts/carinfo/news.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/scripts/carinfo/jquery.mobile.css" rel="stylesheet"
	type="text/css">

</head>

<body>

	<script type="text/javascript">
	var wx = new Wx.Param();
	var openid = wx.getOPENID();
	var gzhHash = wx.getGZHHASH("##GZHHASH##");

	var url = wx.getURL("##URL##", [{
	    targetId: "列表",
	    src: "http://www.baidu.com"
	},
	{
	    targetId: "地图",
	    src: "http://www.google.com"
	}]);
	
	
	var data1 ={datalist: [ {
		"address" : "河西区友谊路58号（友谊路和黑牛城道交口-新业广场对面）",
		"city" : "天津市",
		"title" : "天津河西津好医院",
		"src" : "${ctx}/uploads/b4aef679-cae9-4a85-b506-8b24106390bc.jpg",
		"tel" : "400-0221136",
		"desc" : ""
	},
	{
		"address" : "天津市河西区黑牛城道125号(友谊路与黑牛城道交口)",
		"city" : "天津市",
		"title" : "友谊路新业广场2",
		"src" : "${ctx}/uploads/b4aef679-cae9-4a85-b506-8b24106390bc.jpg",
		"tel" : "022-88311666",
		"desc" : ""
	}]};
	
	
		var markerArr2 = {
			datalist : [ {
				title : "xxx",
				point : "114.078869,22.555521",
				address : "xxx ",
				tel : "xxx）"
			}, {
				title : "xxx",
				point : "113.90259,22.568833",
				address : "xxx ",
				tel : "xxx）"
			}, {
				title : "xxx",
				point : "114.151715,22.55261",
				address : "xxx ",
				tel : "xxx）"
			}, ]
		};
		var markerArr = markerArr2.datalist;
		var point1;
		var map;
		
		
		//map.clearOverlays(); //清除地图上所有的标记，

		function map_init() {

			 map = new BMap.Map("map_dealer"); // 创建Map实例
	
			/*
			var point;
			var util2 = new Util.Geo();
			util2.getLocalPoint(function(point) {
				var x = point.latitude;
				var y = point.longitude;
				point = new BMap.Point(y, x); // 创建点坐标:为当前我的位置
				point1 = point;
				alert(x);
				alert(y)
				console.log(x);
				console.log(y);
			});
			*/


			/**/
			var point = new BMap.Point(114.151715,22.55261); // 创建点坐标:为当前我的位置
			point1 = point;
			
			
			console.log(point);
			map.centerAndZoom(point, 12);// 初始化地图,设置中心点坐标和地图级别。

			map.enableScrollWheelZoom(true); //启用滚轮放大缩小

			//向地图中添加缩放控件

			var ctrl_nav = new BMap.NavigationControl({

				anchor : BMAP_ANCHOR_TOP_LEFT,

				type : BMAP_NAVIGATION_CONTROL_LARGE

			});

			map.addControl(ctrl_nav);

			//向地图中添加缩略图控件

			var ctrl_ove = new BMap.OverviewMapControl({

				anchor : BMAP_ANCHOR_BOTTOM_RIGHT,

				isOpen : 1

			});

			map.addControl(ctrl_ove);

			//向地图中添加比例尺控件

			var ctrl_sca = new BMap.ScaleControl({

				anchor : BMAP_ANCHOR_BOTTOM_LEFT

			});

			map.addControl(ctrl_sca);

			var point = new Array(); //存放标注点经纬信息的数组

			var marker = new Array(); //存放标注点对象的数组

			var info = new Array(); //存放提示信息窗口对象的数组

			for (var i = 0; i < markerArr.length; i++) {

				p0 = markerArr[i].point.split(",")[0]; //

				p1 = markerArr[i].point.split(",")[1]; //按照原数组的point格式将地图点坐标的经纬度分别提出来

				point[i] = new BMap.Point(p0, p1); //循环生成新的地图点

				marker[i] = new BMap.Marker(point[i]); //按照地图点坐标生成标记

				map.addOverlay(marker[i]);

				//marker[i].setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

				var label = new BMap.Label('公司名称：' + markerArr[i].title, {
					offset : new BMap.Size(20, -10)
				});

				marker[i].setLabel(label);

				/*add显示信息*/
				var content = '<div style="margin:0;line-height:20px;padding:2px;">'
						+ '<img src="' +  markerArr[i].title + '" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>'
						+ '地址：'
						+ markerArr[i].address
						+ '<br/>电话：<a href="tel:'+markerArr[i].tel+'">'
						+ markerArr[i].tel
						+ '</a><br/>简介：'
						+ markerArr[i].tel
						+ '</div>'
						+ '<div class="iw_poi_content"><a href="javascript:;" id="carLine" onclick="showMessage(1,'
						+ markerArr[i].point
						+ ')">驾车到项目</a>&nbsp;&nbsp;&nbsp;<a href="javascript:;" id="stepLine" onclick="showMessage(2,'
						+ markerArr[i].point + ')">步行到项目</a></div>';

				info[i] = new BMap.InfoWindow(content.toString());

			}

			marker[0].addEventListener("mouseover", function() {

				this.openInfoWindow(info[0]);

			});

			marker[1].addEventListener("mouseover", function() {

				this.openInfoWindow(info[1]);

			});

			marker[2].addEventListener("mouseover", function() {

				this.openInfoWindow(info[2]);

			});

		}

		function map_load() {//异步调用

			var load = document.createElement("script");

			//load.src = "http://api.map.baidu.com/api?v=2.0&callback=map_init";

			load.src = "http://api.map.baidu.com/api?v=2.0&ak=goT4KEnxiuN4FEQ4mF3XUdcv&callback=map_init";

			document.body.appendChild(load);

		}

		window.onload = map_load;

		//add导航路径信息
		function showMessage(type, lng, lat) {

			//map_init();
			//map.clearOverlays();
			var geolocation = new BMap.Geolocation();
			geolocation.getCurrentPosition(function(r) {

				//关于状态码
				//BMAP_STATUS_SUCCESS	检索成功。对应数值“0”。
				//BMAP_STATUS_CITY_LIST	城市列表。对应数值“1”。
				//BMAP_STATUS_UNKNOWN_LOCATION	位置结果未知。对应数值“2”。
				//BMAP_STATUS_UNKNOWN_ROUTE	导航结果未知。对应数值“3”。
				//BMAP_STATUS_INVALID_KEY	非法密钥。对应数值“4”。
				//BMAP_STATUS_INVALID_REQUEST	非法请求。对应数值“5”。
				//BMAP_STATUS_PERMISSION_DENIED	没有权限。对应数值“6”。(自 1.1 新增)
				//BMAP_STATUS_SERVICE_UNAVAILABLE	服务不可用。对应数值“7”。(自 1.1 新增)
				//BMAP_STATUS_TIMEOUT	超时。对应数值“8”。(自 1.1 新增)
				if (this.getStatus() == BMAP_STATUS_SUCCESS) {
					//当前位置
					var currPoint = new BMap.Point(r.point.lng, r.point.lat);
					//目标位置
					//alert(lng);
					var targetPoint = new BMap.Point(lng, lat);

					$("path").remove();

					if (type == 1) {
						var driving = new BMap.DrivingRoute(map, {
							renderOptions : {
								map : map,
								autoViewport : true
							}
						});
						driving.search(currPoint, targetPoint);
					} else {
						var walking = new BMap.WalkingRoute(map, {
							renderOptions : {
								map : map,
								autoViewport : true
							}
						});
						walking.search(currPoint, targetPoint);
					}
				}

			}, {
				enableHighAccuracy : true
			});
		};
	</script>
	<div id="map_dealer"
		style="width: 100%; height: 500px; background-color: navy;"></div>
</body>

</html>
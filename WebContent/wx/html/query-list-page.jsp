<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>销售店查询</title>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/carinfo/iscroll.js" type="text/javascript"></script>
<link href="${ctx }/scripts/carinfo/news.css" rel="stylesheet"
	type="text/css">
<link href="${ctx}/scripts/carinfo/jquery.mobile.css" rel="stylesheet"
	type="text/css">
<script src="${ctx }/scripts/bdmap/js/map.js"></script>
<link rel="stylesheet" href="${ctx }/scripts/bdmap/css/styles.css" />
<style> 

//
prov city dealer 下拉列表样式
 
.mapdiv {
	display: none;
}
/* body,html, */
#bdmap {
	height: 100%;
	width: 100%;
	float: left;
	border-right: 2px solid #bcbcbc;
	/* overflow: hidden; */
	margin: 0;
} 
 
.selectshop{ 
	padding:10px;
	height:50px;
	border-bottom:1px solid #ddd;
	box-shadow:0 1px 0 rgba(255,255,255,.9);
}
.mylocation{
	display:block;
	width:100%;
	padding:10px;
	padding-left:6px;
	position:relative;
	border-bottom:1px solid #ddd;
	box-shadow:0 1px 0 rgba(255,255,255,.9);
	text-shadow:1px 1px 0 rgba(255,255,255,1); 
	background-size:20px 20px;
}
.mylocation:before{
	content:"";
	width:25px;
	height:30px;
	background: url("/gtmc_wx/scripts/image/icon_lbs.png") no-repeat 0px 0px;
	background-size: 25px 25px;
	padding-left: 20px;
}
.info-line{
	padding:10px;
	line-height:25px;
	border-bottom:1px solid #ddd;
	background:#fff;
	color:#666;
	position:relative;
} 
.info-line:active{
	background-color:#106dbb;
	color:#fff;
}
.shopname {
	color:#106dbb;
	font-size: 16px;
	line-height: 30px;
	margin-bottom: 5px;
	text-weight: bold;

}
  
.distance {
	border-radius: 1em;
	padding: 3px 10px;
	color: #000;
	display: inline-block;
	float: right;
	text-align: middle; 
	position:absolute;
	right:10px;
	top:20px;
	
	font-size: 12px;
	font-weight: bold;
}
.tab{ 
	border-bottom:1px solid #ddd;
	box-shadow:0 1px 0 rgba(255,255,255,.9);
} 
 

img
{
max-width:inherit!important;
}
.btn_refresh{
	cursor:pointer;
	color:rgb(40,128,201);
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=goT4KEnxiuN4FEQ4mF3XUdcv&v=1.0"></script>
</head>
<body>
<div> 
	<div class="mylocation">
			<span>我的位置：</span> <span id="locationSpan" style="width:50%;">正在获取位置...</span>
			<!-- <div id="btn-ref" class="btn_refresh" onclick="getLocal()">刷新</div> -->
			<button id="btn-map" class="btn_refresh" onclick="changetoMap()" style='width:60px;float:right;'>地图</button>
			<button id="btn-lst" class="btn_refresh" onclick="changeToShopList()" style='width:60px;float:right;display:none;'>列表</button>
	</div>
	<div class="tab" style='display:none;'>
		<ul>
			<li onclick="changeToShopList();" id="btnlist" class="on">列表</li>
			<li onclick="changetoMap();" id="btnmap" >地图</li>
		</ul> 
	</div>
	<div id='ShopList' >
		<div id='top' class="selectshop">
			<div>
				<select style="width: 32.0%; float: left;margin-top: 2px; margin-bottom: 2px;margin-right:5px" id='provinceid' name='province'>
				</select>
				<select style=" width: 33.0%; float: left; margin-top: 2px; margin-bottom: 2px;margin-right:2px" id='cityid' name='city'>
				</select>
				<select style=" width: 32.0%; float: right; margin-top: 2px; margin-bottom: 2px;margin-right:0px" id='dealershipid' name='dealership'>
				</select>
			</div>
		</div>
		
		<div id='nearshoplist'></div> 
	</div>
	<div id="bdmapdiv" style="height: 100%; width: 100%">
		<div id='bdmap'></div>
	</div>
</div>
	
	<input type="hidden" targetId="list" urlType="url" title="列表  跳转至" />

	<script type="text/javascript">
	var wx = new Wx.Param();
	var openid = wx.getOPENID();
	var gzhHash = wx.getGZHHASH("##GZHHASH##");

	var url = wx.getURL('##URL##', [{
	    targetId: "列表",
	    src: "query-detail-page.jsp"
	}]);

	//业务相关：获取省份+城市。
	//业务相关：获取经销商信息
	var data1 = {
	    shopname: "店1",
	    address: "地址1",
	    distance: "距离1",
	};

	var x;
	var y;
	var mapdata;

	$(getProCityDealerList());

	//请求定位，获取附近销售店列表
	var util = new Util.Geo();
	util.getLocalPoint(function(point) {
	    x = point.latitude;
	    y = point.longitude;
	    util.getCityByPoint(point,
	    function(location) {
	        $('#locationSpan').text(location.address);
	        $.ajax({
	            type: 'POST',
	            url: "${ctx}/dealerquery/queryDealerList?projectGuid=all",
	            //+ $("#dealershipid").val(),
	            data: {
	                x: x,
	                y: y
	            },
	            dataType: "json",
	            success: function(data) {
	                if (data.success) {
	                    console.log(data);
	                    render(data.result);
	                } else {
	                    $('#nearshoplist.info').html("查询失败");
	                }
	            }
	        });

	    });
	});

	function render(data) {
	    mapdata = data;
	    //渲染页面，附近销售店
	    var listdisplay = "";
	    //var nearshoplistitem = data.nearshoplist;
	    for (var i = 0; i < data.length; i++) {
	        listdisplay += 
	        	'<div class="info-line"  onclick="li_click(\'' + data[i].projectGuid + '\')">' + 
	        		'<div>'+
	        			'<div class="shopname">' + data[i].shopname.replace("广汽丰田","") + '</div>' + 
	        			'<div class="address">' + data[i].address + '  </div>'+
	        		'</div>' + 
	        		'<div class="distance" style="margin-bottom:0;">' + trim(data[i].distance) + '</div>'+
	        	'</div>';
	    }
	    $('#nearshoplist').html(listdisplay);

	}
	function li_click(index) {
		var entrance = Util.Browser.getEntrance();		
	    var param = "?projectGuid=" + index+"&from="+entrance;
	    //alert(index);
	    //window.location = "${ctx}/wx/html/query-detail-page.jsp?projectGuid=" + index;
	    Util.Browser.jump(url[0].src + param, "cp-2-2", index);
	}

	//处理距离
	function trim(num) {
	    num = parseFloat(num);
	    if (num < 1000) {
	        return Math.round(num) + "m";
	    } else if (num < 1000000) {
	        var dis = num / 1000;
	        return dis.toFixed(2) + "km";
	    } else {
	        return "超过1000km";
	    }
	}

	/*获取省市经销商列表*/
	function getProCityDealerList() {
	    $.get('${ctx}/dealer/getAllDealerAddressProvinces',
	    function(res) {
	        var data = res.rows;
	        $("#provinceid").empty();
	        $("#provinceid").append("<option value=''><b>省份</b></option>");
	        $("#cityid").append("<option value=''>城市</option>");
	        $("#dealershipid").append("<option value='all'>经销商</option>");
	        for (var i = 0; i < data.length; i++) {
	            if (data[i].provCode == "${param.provCode}") {
	                $("#provinceid").append("<option value='" + data[i].provinceCode + "' selected='selected'>" + data[i].provinceName.replace("广汽丰田","") + "</option>");
	                $("#provinceid").change();
	            } else {
	                $("#provinceid").append("<option value='" + data[i].provinceCode + "'>" + data[i].provinceName.replace("广汽丰田","") + "</option>");
	            }
	        }
	    });

	    $("#provinceid").change(function() {
	        if ($(this).val() == '') {
	            $("#cityid").empty();
	            $("#dealershipid").empty();
	            $("#cityid").append("<option value=''>城市</option>");
	            $("#dealershipid").append("<option value='all'>经销商</option>");
	            return;
	        }
	        $.get('${ctx}/dealer/getAllDealerAddressCitiesByProvince?province_code=' + $(this).val(),
	        function(res) {
	            var data = res.rows;
	            $("#cityid").empty();
	            $("#dealershipid").empty();
	            $("#cityid").append("<option value=''>城市</option>");
	            $("#dealershipid").append("<option value='all'>经销商</option>");
	            for (var i = 0; i < data.length; i++) {
	                if (data[i].cityCode == "${param.cityCode}") {
	                    $("#cityid").append("<option value='" + data[i].cityCode + "' selected='selected'>" + data[i].cityName + "</option>");
	                } else {
	                    $("#cityid").append("<option value='" + data[i].cityCode + "'>" + data[i].cityName + "</option>");
	                };
	            };
	        });
	    });

	    $("#cityid").change(function() {
	        if ($(this).val() == '') {
	            $("#dealershipid").empty();
	            $("#dealershipid").append("<option value=''>经销商</option>");
	            return;
	        }
	        $.get('${ctx}/dealer/getAllDealersByCity?cityCode=' + $(this).val(),
	        function(res) {
	            var data = res.rows;
	            $("#dealershipid").empty();
	            $("#dealershipid").append("<option value=''>经销商</option>");
	            for (var i = 0; i < data.length; i++) {
	                if (data[i].projectGuid == "${param.projectGuid}") {
	                    $("#dealershipid").append("<option value='" + data[i].projectGuid + "' selected='selected'>" + data[i].projectName.replace("广汽丰田","") + "</option>");
	                } else {
	                    $("#dealershipid").append("<option value='" + data[i].projectGuid + "'>" + data[i].projectName.replace("广汽丰田","") + "</option>");
	                }
	            }
	        });
	    });

	    $("#dealershipid").change(function() {
	        if ($(this).val() == '') {
	            return;
	        }
	        $.ajax({
	            type: 'POST',
	            url: "${ctx}/dealerquery/queryDealerList?projectGuid=" + $("#dealershipid").val(),
	            //+ $("#dealershipid").val(),
	            data: {
	                x: x,
	                y: y
	            },
	            dataType: "json",
	            success: function(data) {
	                if (data.success) {
	                    console.log(data);
	                    render(data.result);
	                } else {
	                    $('#nearby .info').html("查询失败");
	                }
	            }
	        });

	    });

	};
	//刷新定位
	function getLocal() {
	    //请求定位，获取附近销售店列表
	    var util = new Util.Geo();
	    util.getLocalPoint(function(point) {
	        x = point.latitude;
	        y = point.longitude;
	        util.getCityByPoint(point,
	        function(location) {
	            $('#locationSpan').text(location.address);
	            $.ajax({
	                type: 'POST',
	                url: "${ctx}/dealerquery/queryDealerList?projectGuid=" + $("#dealershipid").val(),
	                //+ $("#dealershipid").val(),
	                data: {
	                    x: x,
	                    y: y
	                },
	                dataType: "json",
	                success: function(data) {
	                    if (data.success) {
	                        console.log(data);
	                        render(data.result);
	                    } else {
	                        $('#nearby .info').html("查询失败");
	                    }
	                }
	            });

	        });
	    });

	}

	function changeToShopList() {
 			
		$('#btn-lst').css('display', 'none');
		$('#btn-map').css('display', 'block');
		
	    $("#bdmapdiv").attr("style", "display:none;");
	    $("#ShopList").show();
	    $("#btnlist").addClass("on");
	    $("#btnmap").removeClass("on");
	    
	    
	}
	function changetoMap() {
		$('#btn-lst').css('display', 'block');
		$('#btn-map').css('display', 'none');
		
	    $("#top").removeClass("top");
	    $("#ShopList").attr("style", "display:none;");
	    $("#btnmap").addClass("on");
	    $("#btnlist").removeClass("on");
	    var winHeight = document.documentElement.clientHeight - 35;
	    $("#bdmapdiv").attr("style", "height:" + winHeight + "px;");
	    //$("#bdmap").show();
	    $("#bdmapdiv").show();
	    $("#bdmapdiv").html("");
	    $("#bdmapdiv").append("<div id='bdmap' ></div>");
	    console.log(mapdata);
	    rendmap(mapdata); //渲染地图
	}
	function rendmap(mapdata) {

	    // 百度地图API功能
	    var point = {
	        "latitude": 36.9,
	        "longitude": 116.9
	    };
	    var geo = new Util.Geo();
	    window.addMarker = function(map, point) {
	        var marker = new BMap.Marker(point);
	        map.addOverlay(marker);
	    };
	    //alert(y,x);
	    geo.initBMap(function() {
	        //alert(JSON.stringify(point));
	        window.bdmap = geo.createMap('bdmap', [y, x]);
	        for (var i = 0; i < mapdata.length; i++) {
	            var showinfo = "";
	            showinfo = '<div style="margin:0;line-height:20px;padding:2px;">' 
	            	+ '<img src="' + mapdata[i].src + '" alt="无图片" style="display:none;float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' 
	            	+ '销售店：'+ mapdata[i].shopname
	            	+ '<br/>地址：'+ mapdata[i].address 
	            	+ '<br/>电话：<a href="tel:' + mapdata[i].tel + '">' + mapdata[i].tel 
	            	+ '</a><br/>简介：' + (mapdata[i].desc ? mapdata[i].desc : "") + '</div>' 
	            	+ '<div class="iw_poi_content"><a href="javascript:;" id="carLine" onclick="showMessage(1,' + mapdata[i].Longitude + ',' + mapdata[i].Latitude + ')">驾车到项目</a>&nbsp;&nbsp;&nbsp;<a href="javascript:;" id="stepLine" onclick="showMessage(2,' + mapdata[i].Longitude + ',' + mapdata[i].Latitude + ')">步行到项目</a></div>';
	            bdmap.addMark([[mapdata[i].Longitude, mapdata[i].Latitude, showinfo]]);
	        };
	    });
	    /*
							 var map = new BMap.Map("bdmap");
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
							 //map.addControl(ctrl_ove);
							 //向地图中添加比例尺控件
							 var ctrl_sca = new BMap.ScaleControl({
							 anchor : BMAP_ANCHOR_BOTTOM_LEFT
							 });
							 //map.addControl(ctrl_sca);

							 map.centerAndZoom(new BMap.Point(y, x), 15);
							 //return;
							 // 编写自定义函数,创建标注
							 for (var i = 0; i < mapdata.length; i++) {
							 //alert(mapdata[i].Longitude + '_' + mapdata[i].Latitude);
							 var point = new BMap.Point(mapdata[i].Longitude,
							 mapdata[i].Latitude);
							 var marker = new BMap.Marker(point);
							 map.addOverlay(marker);
							 }
				 */

	}

	function showMessage(type, lng, lat) {
	    //$('path').remove();
	    changetoMap();
	    if (type = 1) {
	        console.log([y, x], [lng, lat]);
	        window.bdmap.drivingSearch([y, x], [lng, lat]);
	        //window.bdmap.drivingSearch(start, end);
	    } else {
	        window.bdmap.walkingSearch([y, x], [lng, lat]);

	    }
	}

	var stat = new Wx.Stat();
	stat.report('6-1', "查经销商", gzhHash, openid, {});
	</script>
<script src="${ctx}/template/dist/js/js_general.js"></script>
</body>
</html>
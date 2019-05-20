<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>销售店查询</title>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/carinfo/iscroll.js" type="text/javascript"></script>
<link href="${ctx }/scripts/carinfo/news.css" rel="stylesheet" type="text/css">
<link href="${ctx}/scripts/carinfo/jquery.mobile.css" rel="stylesheet" type="text/css">
<style>
body{
	min-width:300px;
}
.title{
	text-align:left;
	margin:0;
	font-size:20px;
	line-height:40px;
	font-weight:bold;
}
.blockbtn{
	text-align:center;
	padding:10px auto; 
	box-sizing:border-box; 
	width:32%; 
	display:inline-block;
	float:left;
	padding-top:10px;
}
.blockbtn:active{
	background:#106dbb;
	color:#fff;
}
.address{
	position:relative;
	padding-left:30px;
	height:35px;
}
.address:before{
	content:"";
	width:25px;
	height:25px;
	background:url("${ctx}/scripts/image/icon_lbs.png") no-repeat;
	background-size:25px 25px;
	position:absolute;
	top:0;
	left:0;
} 
.distance {
	background: #999;
	border-radius: 1em;
	padding: 3px 10px;
	color: #fff;
	display: inline-block;
	margin-bottom: 5px;
}
.weixin{ 
	padding:5px;
	padding-left:70px;
	position:relative;
	height:70px;
}
.weixin:before{
	content:"";
	position:absolute;
	top:15px;
	left:10px;
	width:35px;
	height:35px;
	background:url("${ctx}/scripts/image/icon_wx.png") no-repeat 50% 50%;
	background-size:35px 35px;
} 
.content {
margin-top: 5px;
padding-top: 5px;
}

body, html,
#map {width: 100%;height: 100%;overflow: hidden;margin:0;}
#map{height:100%;width:100%;float:left;border-right:2px solid #bcbcbc;}


img
{
	max-width:inherit!important;
}
#lable_hotline,#post_hotline{
	position:relative;
}
#lable_hotline:before,#post_hotline:before{
	content:"";
	width:24px;
	height:24px;
	left:10px;
	top:5px;
	position:absolute;
	background:url("${ctx}/scripts/image/ico-dial-w.png") no-repeat;
	background-size:24px 24px;
}
</style>
</head>
<body>
	
<div id="details" > 
	<div class="content">
		<div>
			<h3 id='shopname' class="title" style='float:left;'>销售店名称 </h3>  
			<span style='float:right;padding-top:10px;display:inline-block;padding-right:10px;text-decoration:underline;cursor:pointer; color:#113859' onclick='showmap()'>地图导航</span>
		</div>
		<div style='clear:both'></div>
		<div id="address" class="address" style="margin-top:10px">销售店地址</div>
		<div style="width:100%;margin-bottom:10px;display:block;height:95px;">
			<div id='btnyuyueshijia' onclick="btn_click(0)"   class="list blockbtn" style="margin-right:5px;"> 
				<div><img src="${ctx}/scripts/image/icon_drive.png"></div>
				<div>预约试驾</div> 
			</div> 
			<div id='btnershouche' onclick="btn_click(1)" class="list blockbtn" style="margin-right:5px;"> 
				<div><img src="${ctx}/scripts/image/icon_car.png"></div>
				<div>二手车评估</div> 
			</div> 
			<div  id="btn_service"  onclick="btn_click(2)" class="list blockbtn">
				<div><img src="${ctx}/scripts/image/icon_mend.png"></div>
				<div>维修保养</div>
			</div>
		</div>
		<Button id="lable_hotline" class="btn_main" style="margin-bottom:10px;">销售热线：10086</Button>
		<Button id="post_hotline" class="btn_main" style="margin-bottom:10px;">服务热线：10010</Button> 
		
		<div class="list weixin" > 
				<div style="color:#999">微信号</div>
				<div id='gzhname'></div>
				<a  style="display:none; ">关注微信号 </a>
		</div>
		<!-- Button type="button" class="btn_def" onclick="showmap()">路线导航 </Button--> 
	</div>
		
		
</div>   

<div id="projinfo"></div>

<div id="map" class="mapdiv"></div>
	<script type="text/javascript">
	
	/*--------------------------------------------------------------------------*/
	var testurls = [
            {src:"${ctx}/wx/html/yuyue-shijia-page.jsp", targetId: "yuyueshijia_url"},
            {src:"${ctx}/wx/html/used-car-value.jsp" , targetId: "ershouchepinggu_url"},
            {src:"${ctx}/wx/html/yuyue-baoyang-page.jsp" , targetId: "yuyueweixiubaoyang_url"}
	];
	/*--------------------------------------------------------------------------*/
	
	var param = new Wx.Param();
	var openid = param.getOPENID();
	var gzhHash = param.getGZHHASH("##GZHHASH##");
	var urls = param.getURL('##URL##', testurls);
	var projectGuid = '${param.projectGuid}';
	var from = '${param.from}';
	var provinceCode="";
	var cityCode="";
	//alert(projectGuid);
	//业务相关：获取省份+城市。
	//业务相关：获取经销商信息
	var x, y;
	var mapdata;
	var data = {
	    projectName: "销售店1",
	    address: "销售店地址1",
	    mobile: "18688888888",
	    asTel: "18688888889",
	};

	$(function() {
		
		if(from=="10-2")
			{
			$("#btnyuyueshijia").removeClass("list blockbtn left w_50").addClass("list blockbtn");
				$("#btnershouche").attr("style", "display:none;");
				$('#btnyuyueshijia').css('width', '48%');
				$('#btn_service').css('width', '48%');
				$('#btn_service').css('float', 'right');
			}
		
		
	    $.ajax({
	        type: 'POST',
	        url: "${ctx}/dealerquery/queryDealerInfo?projectGuid=" + projectGuid,
	        dataType: "json",
	        success: function(res) {
	            var result = eval(res);
	            if (result.message == "success") {
	                var result = eval(res); 
	                console.log(result);
	                data = result.result;	                
	                mapdata = data[0];
	                //dataRead(data[0]);
	                render(data); 

	            } else {
	                alert("连接服务器失败，请重试！");
	            }
	        }

	    });

	});
	function render(data){

        $('#shopname').html(data[0].projectName.replace("广汽丰田",""));
        $('#address').html(data[0].address);
        $('#lable_hotline').html("销售热线："+ data[0].mobile);
        $('#post_hotline').html("服务热线："+data[0].asTel);
        $('#gzhname').html(data[0].gzhname);
        $('#lable_hotline').attr("onclick","dial('" + data[0].mobile + "')");
    	$('#post_hotline').attr("onclick","dial('" + data[0].asTel + "')");
    	provinceCode= data[0].provinceCode ;
    	cityCode= data[0].cityCode ;
    	
	}

	function dataRead(data) {
	    var projinfodisplay = "";
	    projinfodisplay += 
	    	'<div class="item">' + 
	    		'<div class="projectName">' + data.projectName.replace("广汽丰田","") + '</div>' + 
	    		'<div class="address">' + data.address + '  </div>' + 
	    		'<div class="mobile">' + data.mobile + ' </div>' + 
	    		'<div class="asTel">' + data.asTel + ' </div>';
	    projinfodisplay += '</div>';
	    $('#projinfo').html(projinfodisplay);
	    rendmap(data);
	}

	//拨号
	function dial(tel) {
	    window.location.href = "tel:" + tel;
	}
	function showmap() {
	    $("#details").attr("style", "display:none;");
	    $("#map").show();
	    rendmap(mapdata);
	};
	function rendmap(mapdata) {

	    var geo = new Util.Geo(); //实例化地图
	    //获取我的位置
	    geo.getLocalPoint(function(point) {
	        x = point.latitude;
	        y = point.longitude;
	    });
	    //alert(x+","+y);
	    //初始化地图，并显示驾车导航路线
	    geo.initBMap(function() {
	        console.log(mapdata);
	        window.bdmap = geo.createMap('map', [y, x]);
	        // alert(mapdata.Longitude+"+"+mapdata.Latitude);
	        var showinfo = 	        
	        '<div style="margin:0;line-height:20px;padding:2px;">' 
        	+ '<img src="' + mapdata.src + '" alt="无图片" style="display:none;float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' 
        	+ '经销店：'+ mapdata.shopname
        	+ '<br/>地址：'+ mapdata.address 
        	+ '<br/>电话：<a href="tel:' + mapdata.mobile + '">' + mapdata.mobile 
        	+ '</a><br/>简介：' + (mapdata.desc ? mapdata.desc : "") + '</div>' 
        	;//+ '<div class="iw_poi_content"><a href="javascript:;" id="carLine" onclick="showMessage(1,' + mapdata.Longitude + ',' + mapdata.Latitude + ')">驾车到项目</a>&nbsp;&nbsp;&nbsp;<a href="javascript:;" id="stepLine" onclick="showMessage(2,' + mapdata.Longitude + ',' + mapdata.Latitude + ')">步行到项目</a></div>';
	        
	        //bdmap.addMark([[mapdata.Longitude, mapdata.Latitude, showinfo]]);
	        //bdmap.addMark([[mapdata.Longitude, mapdata.Latitude, showinfo]]);
	        showMessage(1, mapdata.Longitude, mapdata.Latitude);
	    });

	};
	function showMessage(type, lng, lat) {
	    $('path').remove();
	    if (type = 1) {
	        console.log([y, x], [lng, lat]);
	        window.bdmap.drivingSearch([y, x], [lng, lat]);
	    } else {
	        window.bdmap.walkingSearch([y, x], [lng, lat]);

	    }
	}

	function btn_click(i){	
		
	    var param = "?provinceCode=" + provinceCode+"&cityCode="+cityCode+"&dealerGuid="+projectGuid;
		Util.Browser.jump(urls[i].src + param, '6-2-b', null);		
	}
	
	
	var stat = new Wx.Stat();
	stat.report('6-2-b', "经销商详细", gzhHash, openid, {});
	</script>
	<script src="${ctx}/template/dist/js/js_general.js"></script>
  	<input type="hidden" targetId="yuyueshijia_url" onclick="btn_click(0)" urlType="url" title="预约试驾" />
  	<input type="hidden" targetId="ershouchepinggu_url" onclick="btn_click(1)"  urlType="url" title="二手车评估" />
  	<input type="hidden" targetId="yuyueweixiubaoyang_url" onclick="btn_click(2)"  urlType="url" title="预约维修保养" />
</body>
</html>
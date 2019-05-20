<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>一键救援</title>
<%@ include file="/common/pre_general.jsp"%>
<style>
body { 
	margin: 0;
	padding: 0; 
}
.title{
	padding:10px;
	line-height:30px;
	box-shadow:0 1px 0 rgba(255,255,255,.9) inset;
	background: url("/gtmc_wx/scripts/image/icon_lbs.png") no-repeat 5px 12px;
	background-size: 25px 25px;
	padding-left:30px;
}
.subtitle {
	font-size: 14px;
	text-shadow: 1px 1px 1px rgba(255, 255, 255, 1);
	color:#000;
	padding: 5px;
	padding-left:10px;
	margin: 0; 
	line-height: 30px;
	border-top:1px solid #ccc;
	box-shadow:0 1px 0 rgba(255,255,255,.9) inset;
	background:rgb(233,243,250);
}
/*color: #106dbb;*/
.item {
	border-bottom: 1px solid #ccc;
	padding: 10px;
	background: #fff;
	min-height:50px;
	clear:both;
}
 
.shopname {
	color: #106dbb;
	font-size: 16px;
	line-height: 30px;
	margin-bottom: 5px;
	text-weight: bold;
	float:left;
	max-width:90%;
	overflow:hidden;
}

.distance {
	border-radius: 1em;
	padding: 3px 10px;
	color: #fff;
	display: inline-block;
	margin-bottom: 5px;
	float:right;
	text-align: center;
	font-size: 12px;
	font-weight: bold;
	color:rgb(7,24,38);
}  

.btn_refresh {
	background: url(${ctx}/scripts/image/btn_refr.png) no-repeat 50% 50% #e60024;
	width: 40px;
	height: 40px;
	display: inline;  
	float:right;
}

.btn_refresh:active {
	background-color: #c6001f;
	cursor: pointer;
}

#pos {
	margin-right:50px;
}
.info{
	padding:5px;
	
}
</style>

<!-- 百度地图 -->
<script>
  
</script>
</head>
<body>
	<div> 
		<div class="title">
			<span>我的位置：</span>
			<span id="pos">正在获取位置...</span>
			<!--<Button type="button" name="btn-ref" class="btn " onclick="getLocal()">刷新</Button> -->
		</div>
	</div>
	<h3 class="subtitle">购车的销售店</h3>
	<div id="usershop">
		<div class="info">正在加载...</div>
	</div>
	<h3 class="subtitle">附近的销售店</h3>
	<div id="nearby">
		<div class="info">正在加载...</div>
	</div>
</div>
</body>

<script type="text/javascript">
var wx  = new Wx.Param();
var gzhHash = wx.getGZHHASH('##GZHHASH##'); 
var openid = wx.getOPENID();
 
var url = wx.getURL('##URL##',[]);
var x = 0;
var y = 0;
/**
 * var data = {
			usershop:[
			          {
			        	  	shopname:"广汽丰田长凯白云店",	
			        	  	distance:"300",
			        	  	helptel:"110"
			          },{
							shopname:"广汽丰田元丰凤凰店",	
							distance:"1200",
							helptel:"01083237766"
						}
			],
			nearby:[
				{
					shopname:"广汽丰田1号店",	
					distance:"1200",
					helptel:"01083237766"
				},{
					shopname:"广汽丰田店",	
					distance:"1300",
					helptel:"01083237766"
				} 
			]
	};
 */



//请求定位，获取附近销售店列表
var util = new Util.Geo();

util.getLocalPoint(function(point){
	x = point.latitude;
	y = point.longitude; 
	util.getCityByPoint(point, function(location){
		$('#pos').html(location.address);
		console.log(x,y);
		// 获取附近销售店列表
		$.ajax({
			  type: 'POST',
			  url: "${ctx}/ajax/getNear", 
			  data: {x:x,y:y},
			  dataType: "json",
			  success:function(data){
				  if(data.success) {
					  render2(data.result); 
					  console.log(data.result); 
				  }else{
					  $('#nearby .info').html("查询失败");
				  } 
			  }
		});
		
		//获取购车销售店 
		$.ajax({
			  type: 'POST',
			  url: "${ctx}/ajax/getUsershop",
			  data: {openid:openid},
			  dataType: "json",
			  success:function(data){ 
				  if(data.success) {
					  render1(data.result);
					  console.log(data.result);
				  }else{ 
					  $('#usershop .info').html("未查询到结果"); 
				  }
			  } 
		});
		
	}); 
});
 
 
//渲染页面，客户购车销售店
function render1(data){ 
	var list = "";
	for(var i=0;i<data.length;i++){
		 list += '<div class="item">'+
							'<div class="shopname">'+data[i].shopname.replace("广汽丰田","")+'</div>'+
							'<div class="distance">'+trim(data[i].distance)+'</div>';
							if(data[i].helptel && data[i].helptel.length>0){
							list+='<Button type="button" class="btn_main" onclick="dial('+ data[i].helptel +')">'+data[i].helptel+'</Button>';
							}
						list+='</div>';
		$('#usershop').html(list);
	} 
}

//渲染页面，附近销售店
function render2(data){
	var list = "";
	for(var i=0;i<data.length;i++){ 
		list += '<div class="item">'+
		'<div class="shopname">'+ data[i].shopname.replace("广汽丰田","") +'</div>'+
		'<span class="distance">'+ trim(data[i].distance) +'</span>';
		if(data[i].helptel && data[i].helptel.length>0){
			list += '<Button type="button" class="btn_main mobile_btn" onclick="dial('+ data[i].helptel +')">'+ data[i].helptel +'</Button>'
		}
		list += '</div>';	
	}
	$('#nearby').html(list);
}

//处理距离
function trim(num){
	num = parseFloat(num); 
	if(num<1000){
		return Math.round(num)+"m";
	}else if(num<1000000){
		var dis = num/1000;
		return dis.toFixed(2)+"km";
	}else{
		return "超过1000km";
	} 
}
//拨号
function dial(tel){  
		window.location.href = "tel:"+tel;
}
//刷新定位
function getLocal(){ 
	var util = new Util.Geo();
	 util.getLocalPoint(function(point){
	 	x = point.longitude;
	 	y = point.latitude;
	 	util.getCityByPoint(point, function(location){
	 		$('#pos').html(location.address); 
	 		
	 		//获取附近销售店信息
	 		$.ajax({
	 			  type: 'POST',
	 			  url: "${ctx}/ajax/getNear",
	 			  data: {x:x,y:y},
	 			  dataType: "json",
	 			  success:function(data){ 
	 				 if(data.success) {
						  render2(data.result); 
					  }else{
						  $('#nearby .info').html("查询失败");
					  } 
	 			  } 
	 		});
	 		
	 		//获取购车销售店 
			$.ajax({
				  type: 'POST',
				  url: "${ctx}/ajax/getUsershop",
				  data: {openid:openid},
				  dataType: "json",
				  success:function(data){ 
					  if(data.success) {
						  render1(data.result); 
					  }else{ 
						  $('#usershop .info').html("未查询到结果"); 
					  }
				  } 
			});
	 	}); 
	 }); 
}

var stat = new Wx.Stat();
stat.report('12-1', "一键救援", gzhHash, openid, {});
</script>


</html>
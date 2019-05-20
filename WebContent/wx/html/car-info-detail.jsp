<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<%@ include file="/common/pre_general.jsp"%>

<title>车型配置</title>
<link href="${ctx}/scripts/carinfo/jquery.mobile.css" rel="stylesheet" type="text/css">
<script src="${ctx}/scripts/carinfo/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/carinfo/jquery.mobile-1.3.2.min.js" type="text/javascript"></script>
<script src="${ctx}/scripts/jquery.scrollto.js"></script>

<style>
body{
	min-width:300px; 
}
#listview{
	margin-bottom:60px;
}
.sub-head{
	width:100%;
	background:#eee;
	text-shadow:1px 1px rgba(255,255,255,.9);
	color:#666;
	line-height:30px;
	padding:5px 10px;
	font-weight:bold;
	font-family:"微软雅黑",Arial!important;
	font-size:16px; 
}
.list-param{
	width:100%;
	background:#ddd;
	font-family:"微软雅黑"!important;
	min-width:300px;
}
.list-param td{
	line-height:25px;
	padding:5px;
	background:#fff;
}
.list-param .td-label{ 
	background:#f8f8f8; 
}
.nav-bg{
	background:rgba(0,0,0,.4);
	color:#fff;
	text-shadow:none;
	padding:10px;
	position:fixed; 
}
a,a:visited,a:hover{
	color:inherit!important;
}

.btn-nav{ 
	position: fixed;
	right: 3px;
	top: 100px;
	width: 45px;
	height: 80px;
	overflow: hidden;
	z-index: 9999;
	background:rgba(230,0,36,.6);
	padding:15px;
	color:#fff;
	text-shadow:none;
	font-family:"微软雅黑",Arial!important;
	border-radius:3px;
}
.btn-top{
	position: fixed;
	right: 3px;
	top: 190px;
	width: 45px;
	height: 45px;
	line-height: 45px;
	overflow: hidden;
	z-index: 9999;
	background:rgba(0,0,0,.5); 
	color:#fff;
	text-shadow:none;
	font-family:"微软雅黑",Arial!important;
	border-radius:3px;
	text-align:center;
}
.list-nav li{
	padding:5px;
}
.list-nav li:active{
	background:rgba(230,0,36,.6);
}
.btn{
	height:40px; 
	text-shadow:none;
}
.footer{
	background:#eee;
	height:60px;
	padding:5px;
}
</style>
</head>
<body>
	<div onclick="hideNavi();"> 
		<ul id="listview"></ul> 
		<div class="footer"> 		
			<div style="width: 49%; float:left;">
				<div onclick="span_click(0)" class="btn btn_main">贷款计算器</div>
			</div>
			<div  style="width: 49%;float:right;">
				<div onclick="span_click(1)" class="btn btn_main">预约试驾</div>
			</div> 
		</div>
	</div>
	
		<!-- defaultpanel -->
		<div data-role="panel" id="defaultpanel" data-position="right" data-tap-toggle="true" data-theme="z" class=" ui-panel-animate ui-panel-closed nav-bg"> 
			 <ul id="rt" class="list-nav"> </ul> 
			<!-- /content wrapper for padding -->
		</div>
		<!-- /defaultpanel -->
		
		<div id="showNaviBtn" onclick="showNavi()" class="btn-nav">导航 </div> 
		<div id="topBtn" onclick="goElement(1)" class="btn-top" >Top</div> 
		
	<div class="ui-loader ui-corner-all ui-body-a ui-loader-default">
		<span class="ui-icon ui-icon-loading"></span>
		<h1>loading</h1>
	</div>
	<input type="hidden" targetId="calc_url" urlType="url" title="贷款计算器跳转至" />
  	<input type="hidden" targetId="yuyue_shijia_url" urlType="url" title="预约试驾跳转至" />
  
  <script type="text/javascript">

		var wx = new Wx.Param();
		
		var data = [{type:"基本参数",subType:[
		                                  {carParaName:"生产时间",carParaValue:"2012"},
		                                  {carParaName:"发动机位置",carParaValue:"前置"},
		                                  {carParaName:"驱动形式",carParaValue:"前驱"},
		                                  {carParaName:"综合油耗(L/100km)",carParaValue:"1"},
		                                  {carParaName:"最高车速(km/h)",carParaValue:"180"}
					]},
					{type:"车身",subType:[
					                    {carParaName:"生产时间",carParaValue:"2012"},
					                    {carParaName:"发动机位置",carParaValue:"前置"},
					                    {carParaName:"驱动形式",carParaValue:"前驱"},
					                    {carParaName:"综合油耗(L/100km)",carParaValue:"1"},
					                    {carParaName:"最高车速(km/h)",carParaValue:"180"}
					]},
					{type:"发动机",subType:[
					                     {carParaName:"生产时间",carParaValue:"2012"},
					                     {carParaName:"发动机位置",carParaValue:"前置"},
					                     {carParaName:"驱动形式",carParaValue:"前驱"},
					                     {carParaName:"综合油耗(L/100km)",carParaValue:"1"},
					                     {carParaName:"最高车速(km/h)",carParaValue:"180"}
					]},
					{type:"底盘",subType:[
					                    {carParaName:"生产时间",carParaValue:"2012"},
					                    {carParaName:"发动机位置",carParaValue:"前置"},
					                    {carParaName:"驱动形式",carParaValue:"前驱"},
					                    {carParaName:"综合油耗(L/100km)",carParaValue:"1"},
					                    {carParaName:"最高车速(km/h)",carParaValue:"180"}
						]},
					{type:"操控配置",subType:[
					                      {carParaName:"生产时间",carParaValue:"2012"},
					                      {carParaName:"发动机位置",carParaValue:"前置"},
					                      {carParaName:"驱动形式",carParaValue:"前驱"},
					                      {carParaName:"综合油耗(L/100km)",carParaValue:"1"},
					                      {carParaName:"最高车速(km/h)",carParaValue:"180"}
					                      ]}
		];
		//
		var url = wx.getURL('##URL##', [{
			targetId : "贷款计算器",
			src : "${ctx}/wx/html/loan-count.jsp"
		},{
			targetId : "预约试驾",
			src : "${ctx}/wx/html/yuyue-shijia-page.jsp"
		}]);
		
		    var carGuid = '${param.carGuid}';
	  	    var carTypeGuid='${param.carTypeGuid}';
	        $(function(){
	        	loadData();
	          function loadData()
	          {
	        	 
	        	  $.ajax({
					  type: 'POST',
					  url: "${ctx}/car/getCarInfoByCarGuid",
					  data: {carGuid1:carGuid},
					  dataType: "json",
					  success: function(res){
						  var result = eval(res);
						  if (result.message=="success") {
							  if(result.result.length==0){
								  dataRead(data);
							  }else{
								  data = result.result;
								  console.log(data);
								  dataRead(data);
							  }
						  } else {
							  alert("连接服务器失败，请重试！");
						  }
					  }
				  });	          
	          }
	          function dataRead(data){
	        	  var html_listview='';
		          var html_rt='';
		          $.each(data,function(n,b){
		          	html_listview +=
		          		'<li id="'+(n+1)+'">'+
		          			'<div class="sub-head">'+ b.type +'</div>'+
		          			'<table class="list-param" cellspacing="1" cellpadding="0">'; 
		          	$.each(b.subType,function(m,c){
		          		html_listview +=  
          					'<tr>'+
          						'<td width="35%" class="td-label">'+ c.carParaName + '</td>'+
          						'<td width="60%">'+ c.carParaValue + '</td>'+
          					'</tr>'; 
		          	});
		          			
		          	html_listview += '</table></li>';
		          	
		          	html_rt+= '<a href="javascript:void" onclick="hideNavi('+(n+1)+')" class="same-page-link ui-link" data-ajax="true" rel="external">'+
		          				'<li>'+ b.type +'</li>'+
		          				'</a>';
		          	
		          });
		          
	             $("#listview").append(html_listview);
	             $("#rt").append(html_rt);
	          }
	        });
		$(document).bind("mobileinit", function() {
	    //functions from below don't work here
		});
	
		$(document).ready(function(){
	    /*  $('a.same-page-link').bind('click', function(ev) {
	          var target = $( $(this).attr('href') ).get(0).offsetTop;
	          console.log(target);
	          $.mobile.silentScroll(target);
	          hideNavi();
	          return false;
	    }); */
	   /*  $('a.other-page-link').bind('click', function(ev) {
	        var href = $(this).attr('href');
	        var parts = href.split("-");
	        var page = parts[0];
	        var id = "#"+parts[1];
	        $.mobile.changePage($(page));
	         var target = $( id ).get(0).offsetTop;
	         alert(target); // TODO: make work without alert ....
	         $.mobile.silentScroll(target);
	         return false;
	    });  */ 
		 $(".ui-panel-inner").height($(window).height()-50);
	 });
		
	function span_click(idx) {
		var param = url[idx].src+"?carTypeGuid="+carTypeGuid+"&carModelGuid="+carGuid;
		Util.Browser.jump(param, "2-4");
	}
	
	function showNavi() {
		$("#defaultpanel").removeClass("ui-panel-closed");
		$("#defaultpanel").addClass("ui-panel-open");
		$("#showNaviBtn").hide();
		$("#topBtn").hide();
	}
	function hideNavi(id) {
		if (id) {
			goElement(id);	
		}
		$("#defaultpanel").addClass("ui-panel-closed");
		$("#defaultpanel").removeClass("ui-panel-open");
		$("#showNaviBtn").show();
		$("#topBtn").show();
	}
	console.log("document:", document.documentElement.scrollHeight);
	/* function goTop(){
		var obj=document.getElementById("topBtn");
		function getScrollTop(){
			return document.documentElement.scrollTop|document.body.scrollTop;
		}
		function setScrollTop(value){
		if(document.documentElement.scrollTop){
			document.documentElement.scrollTop=value;
			}else{
				document.body.scrollTop=value;
			}
		}
		window.onscroll=function(){getScrollTop()>200?obj.style.display="":obj.style.display="none";}
		obj.onclick=function(){
			var goTop=setInterval(scrollMove,10);
			function scrollMove(){
				setScrollTop(getScrollTop()/1.1);
				console.log(getScrollTop());
				if(getScrollTop()<1)clearInterval(goTop);
				}
			}
		} */
	
	function goElement(id){
		var util = new Util.Scroller();
		util.scrollTo(id);
	}
		
	</script>
	<script type="text/javascript">
		var wx = new Wx.Param();
		var gzhHash = wx.getGZHHASH("##GZHHASH##");
		var openid = wx.getOPENID();
		var stat = new Wx.Stat();
		stat.report('2-4', "车型信息", gzhHash, openid, {});
	</script>

</body>
</html>
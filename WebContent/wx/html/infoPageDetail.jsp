<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<%@ include file="/common/pre.jsp"%> 
<style>
body { 
	overflow-x:hidden;
	padding:20px;
	font-family:"微软雅黑",Arial;
}
img {
	max-width: 100%;
	max-height:300px;
}
 
.title { 
	font-size: 20px;
	margin:0;
	line-height:40px;
}

.date {
	display: inline-block;  
	color: #8C8C8C;
	line-height:35px;
	margin-right:10px;
}
.header_pic{
	width:100%;
	text-align:center;
	max-height:300px;
	background:#ccc;
	overflow:hidden;
	margin-bottom:10px;
}
.text-ellipsis {
	display: inline-block;
	max-width: 104px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}
.theme-dropdown .dropdown-menu {
  display: block;
  position: static;
  margin-bottom: 20px;
}

.theme-showcase > p > .btn {
  margin: 5px 0;
}
</style>

</head>

<body>
 
	<div id="content_container"></div>
 
	<input type="hidden" name="gzhHash" id="gzhHash" value="##GZHHASH##" /> 
	<div id="openid"></div>
	<script type="text/javascript">
		var wx = new Wx.Param();
		var data = wx.getDATA('##DATA##',{title:"标题", date:"2014-02-14", author:"作者", picSrc:"/uploads/weichi.jpg", content:"There was the social butterfly who spent her days flitting from desk to desk; the workaholic who obsessed over every last detail; the malcontent who subtly belittled anyone who spoke up in a meeting; the passive-aggressive assistant who would only answer calls if you"});
		 var elementStr = '<div>'+
			'<div>'+
	         	'<h3 class="title">'+data.title+'</h3>'+ 
	      	'</div>'+
	      	'<div class="header_pic">'+
	      		'<img src="${ctx}'+data.picSrc+'" />'+
	      	'</div>'+
		  	'<div>'+ 
	         	 data.content + 
	     	'</div>'+
	    '</div>';
	    window.document.title = data.title;
		$("#content_container").html(elementStr);
		
		//	$("#post-user").click(function(){
        //    WeixinJSBridge.invoke('profile',{'username':'gh_4617a5b8f0da','scene':'57'});
        //  })
    </script>
	<script type="text/javascript">
		var wx = new Wx.Param();
		var gzhHash = wx.getGZHHASH("##GZHHASH##");
		var openid = wx.getOPENID();
		var stat = new Wx.Stat();
		stat.report('0-1', "图文详细", gzhHash, openid, {});
	</script>
</body>
</html>

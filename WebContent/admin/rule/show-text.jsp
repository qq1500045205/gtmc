<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    <title>文本消息预览</title>  
	<style>
	body {
	  padding: 10px;
	  font-size:13px;
	  background:#eee;
	  font-family:"微软雅黑",Arial;
	  min-width:300px;
	}
	.msg_txt{
		position:relative;
		padding:10px;
		border-radius:5px;
		border:1px solid #999;
		background:#a7d94f;
		box-shadow:0 1px 1px rgba(0,0,0,.2);
		max-width:70%;
		float:left; 
		color:#000; 
	}
	.msg_txt:before{
		content:" ";
		background:url("${ctx}/scripts/image/wx_msg_tri.png") no-repeat;
		width:7px;
		height:13px;
		position:absolute;
		left:-7px;
		top:8px; 
	}
	</style>
</head>

<body> 
   <div id="content" class="msg_txt">
   	亲，欢迎光临^_^<br> 
   </div>  
    
    <script type="text/javascript">
		var data =${jsonObject};
		setData();
		function setData(){
			$("#content").html(data.content);
		}
	
    </script> 
</body>
</html>

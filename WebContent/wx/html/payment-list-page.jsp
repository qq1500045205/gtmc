<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
   <%@ include file="/common/pre_general.jsp"%>
   <script src="${ctx}/scripts/wx.js"></script> 
	<title>支付记录</title>
	<style>  
		.info-line{
			padding:10px;
			line-height:25px;
			border-bottom:1px solid #eee;
			background:#fff; 
		} 
		.info-line:active{
			background-color:#106dbb;
			color:#fff;
		}
		.info-line .time{
			font-size:12px;  
			display:inline;
		} 
		.info-line .labelx{
			display: inline-block;
			width:120px; 
		}
	</style>
	
  </head>

  <body>
	<div id='info-box' > 
		<div id='info-box-body' style='padding:10px'> 
			您还没有支付记录。
		</div> 
	</div>
 
    <script src="${ctx}/template/dist/js/js_general.js"></script>
  	<input type="hidden" targetId="yuyue_detail_url" urlType="url" title="预约详细跳转至" />
  </body>
</html>

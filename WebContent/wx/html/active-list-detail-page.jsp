<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>活动报名详细</title>
<%@ include file="/common/pre.jsp"%>

<style>
body {
	padding: 20px;
	overflow-x: hidden;
	min-width: 320px;
	font-family: "微软雅黑", Arial;
} 
img {
	max-width: 100% !important;
}

.title {
	color: black;
	font-size: 20px;
	font-weight: bold;
	word-break: normal;
	word-wrap: break-word;
	margin:0;
	line-height:40px;
} 
 
.date {
	display: inline-block;
	line-height:30px;
	color: #8C8C8C;
	font-size: 13px;
}
 

.theme-showcase>p>.btn {
	margin: 5px 0;
}

.btn-all-width{
	width:100%;	
}
.hide{
	display: none;
}
.modal{
	width:270px;
	height:200px;
	overflow: visible;
	overflow-y: none;
}
.modal-body {
 	max-height: 100px;
 	overflow: visible;
	overflow-y: none;
}
</style>

</head>

<body>
	<div id="content_container"> 
	</div>
	
	<script type="text/javascript">
		var util = new Wx.Param();
		var gzhHash = util.getGZHHASH("##GZHHASH##");
		var openid = util.getOPENID();
		var utl = new Util.Param();
		var url = util.getURL('##URL##', [ {
			targetId : "item_0",
			src : "brand-active-sign-page.jsp"
		} ]);
		
		var storage = new Util.Storage();
		var activeData = storage.get('activeData');
		if(null == activeData){
			var utl = new Util.Param();
			$.ajax({
				type : 'POST',
				url : "${ctx}/activity/getOneActive",
				async : false,
				data : {
					actGuid : utl.get("actGuid")
				},
				dataType : "json",
				success : function(data) {
					if (data.success) {
						activeData = data.result;
					}
				}
			});
		}
		var data = activeData;
		
		var elementStr =  
			'<div>'+
	         	 '<h3 class="title">'+data.title+'</h3>'+
				 '<div class="date">'+data.date+'</div>'+
			  	 '<div>'+ 
		         	 data.content + 
		      	 '</div>'+
		     '</div>';
		$("#content_container").html(elementStr);
		
		var stat = new Wx.Stat();
    	stat.report('17-4-2', "品牌活动详情", gzhHash, openid, {});
    </script>

</body>
</html>

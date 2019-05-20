<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<%@ include file="/common/pre_general.jsp"%>
	<script src="${ctx}/scripts/wx.js"></script> 
	<title>活动报名记录</title>  
</head> 
<body> 
	<div id='info-box-body'> 
	<!--  
	<div class='info-item' onclick='div_click("+i+")'> 
		<h3 class='title'>凯美瑞新年促销</h3> 
		<span class='time'>2013-1-1</span>  
		<div> 
			<span style='margin-right:20px;'>参加人数：</span>
			<span>到场方式： </span>
		</div>
		<div>备注说明：</div>
	</div> 
	-->
	</div> 
    <script>
    	var testurls = [
    		{src:"${ctx}/wx/html/yuyue-list-detail-page.jsp", targetId: "yuyue_url"}
    	];
    	/*------------------------------------*/
    	var param = new Wx.Param();
    	var openid = param.getOPENID();
    	var urls = param.getURL('##URL##', {src:"active-list-detail-page.jsp", targetId: "item_0"});
    	var gzhHash = param.getGZHHASH('##GZHHASH##');
    	var entrance = Util.Browser.getEntrance();
    	var req = new Wx.Request();
    	/*------------------------------------*/
    	var data = [];
    	$(function(){
    		req.postData('/activity/getOwnListActive', {openid: openid}, function(ret){
    			var html = "";
    			if( ret.result.length == 0 ){
    			//$('#info-box-body').html('<div style="padding:10px;">还没有您的活动报名记录。</div>');
    				return ;
    			}
    			data = ret.result;
    			for(var i=0;i<data.length;i++){
    				item = data[i];
    				html += "<div class='info-item' onclick='div_click("+i+")'>";
     				html += "<h3 class='title'>"+item.title+"</h3>";
    				html += "<span class='time'>"+ item.date +"</span>"; 
    				html += "<div>";
    				if(item.number){
    					html += "<span style='margin-right:20px;'>参加人数："+item.number +"</span>";	
    				}
    				if(item.arriveBy){
    					html += "<span>到场方式："+item.arriveBy+"</span>";
    				}
    				html += "</div>";
    				if(item.remark){
    					html += "<div>备注说明："+ item.remark +"</div>";
    				}
    				html += "</div>";
    			}
    			$('#info-box-body').html(html);
    		});
    	});
    	function div_click(i){
    		var item = data[i];
    		Util.Browser.jump(urls[0].src, '17-4-1', item);
    	} 
    	var stat = new Wx.Stat();
    	stat.report('17-4-1', "活动报名记录", gzhHash, openid, {});
    </script>
    <script src="${ctx}/template/dist/js/js_general.js"></script>
  	<input type="hidden" targetId="item_0" urlType="url" title="点击跳转至" />
</body>
</html>

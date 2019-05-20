<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
   <%@ include file="/common/pre_general.jsp"%>
   <script src="${ctx}/scripts/wx.js"></script> 
	<title>预约记录</title>
	<style>  
		.list{
			padding:10px;
			margin-bottom:10px;
			clear:both;
			height: 55px;
		} 
		.list:active{
			background-color:#106dbb;
			color:#fff;
		}
		.list .time{
			font-size:14px;  
			display:inline;
			float:left;
		} 
		.list .labelx{
			display: inline-block;
			width:120px; 
			float:right;
		}
	</style>
	
  </head>

  <body>
	<div id='info-box' class="content"> 
		<div id='info-box-body'>
			<!--  <div class='list'  onclick='div_click(1)'>
    			<div class='time'>2014-2-2</div>
    			<div><span class='labelx'>type</span><span></span></div>
    		</div>
    		<div class='list'  onclick='div_click(1)'>
    			<div class='time'>2014-2-2</div>
    			<div><span class='labelx'>type</span><span></span></div>
    		</div>
    		-->
		</div> 
	</div>
	 <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <script>
    	var testurls = [
    		{src:"${ctx}/wx/html/yuyue-list-detail-page.jsp", targetId: "yuyue_url"}
    	];
    	/*------------------------------------*/
    	var param = new Wx.Param();
    	var openid = param.getOPENID();
    	var urls = param.getURL('##URL##', testurls);
    	var gzhHash = param.getGZHHASH('##GZHHASH##');
    	var entrance = Util.Browser.getEntrance();
    	var req = new Wx.Request();
    	/*------------------------------------*/
    	
    
    	$(function(){
    		req.postData('/yuyue/getAllYuyueOwnList', {open_id:openid}, function(ret){
    			if( ret.total == 0 ){
    				$('#info-box-body').html('<div>没有预约记录</div>');
    				return ;
    			}
    			var html = "";
    			retData = ret.rows;
    			for(var i=0;i<ret.rows.length;i++){
    				item = ret.rows[i];
    				var type = '';
    				if( item.yuyueType == '1' ){
    					type = '预约试驾';
    				}
    				if( item.yuyueType == '2' ){
    					type = '预约维修保养';
    				}
    				if( item.yuyueType == '3' ){
    					type = '预约年审';
    				}
    				
    				html += "<div class='list'  onclick='div_click("+i+")'>";
    				html += "<div class='time'>"+ item.createdOnTime +"</div>";
    				html += "<div><span class='labelx'>"+ type +"</span><span>"+ '' +"</span></div>";
    				html += "</div>";
    			}
    			$('#info-box-body').html(html);
    			
    		});
    	});
    	function div_click(i){
    		var item = retData[i];
    		Util.Browser.jump(urls[0].src, '17-5-1', item);
    	}
    	
    	var stat = new Wx.Stat();
    	stat.report('17-5-1', "预约列表", gzhHash, openid, {});
    </script>
    <script src="${ctx}/template/dist/js/js_general.js"></script>
  	<input type="hidden" targetId="yuyue_detail_url" urlType="url" title="预约详细跳转至" />
  </body>
</html>

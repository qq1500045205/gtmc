<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/wx.js"></script>
<%@ include file="/common/datepick.jsp"%>
<title>信息推送测试</title>
<style>
	.red{
		color:red;
		font-size: 13px;
	}
</style>
</head>
<body>
	<div class="page_main">
     <div class="content">
       <form action="" method='post' name='testSend' class='m-form'>
		  <div class="m-form-item" style='text-align:center;'>
		  	<Button type='button' class='btn_main' id='reserve'style='width:30%;' >提交</Button>
		  </div>
       </form>
     </div>
    </div>
	<script type="text/javascript">
   		var testurls = [
   		    {src:"${ctx}/wx/html/liuzi-page.jsp", targetId: "liuzi_url"},
   		    {src:"${ctx}/wx/html/#", targetId: "liuzi_url"}
   		];
   		
   		var form = new Util.Form("testSend");
   		var param = new Wx.Param();
   		var openid = param.getOPENID();
   		var gzhHash = param.getGZHHASH('##GZHHASH##');
   		window.GZHTYPE = '##GZHTYPE##';
   		var urls = param.getURL('##URL##', testurls);
   		var gzhType = param.getGZHTYPE();
   		var req = new Wx.Request();
   		var isOkCheck = false;
   		
   		function liuzi_post(){
   			var formdata = form.getValues();
    		formdata['gzhHash'] = gzhHash;
    		formdata['openid'] = openid;
    		//调后台方法，调用CR接口
    		req.postData("/artificial/testSendMsg",formdata,function(res){
    			if(res.message = "success"){
    				Util.Browser.jump(urls[0].src, '18-1');
    			}
    		});
   		}
   		
   		$(function(){
   			$('#reserve').bind('click',function(){
   				liuzi_post();
   			});
   		});
        
   </script>
</body>
</html>
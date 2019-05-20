	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%> 

<!DOCTYPE html>
<html>
<head> 
	<%@ include file="/common/pre_general.jsp"%>
    <title>个人信息</title> 
	<style type="text/css">
		.m-form-item span{
			height:30px;
			text-align:center;
			display:inline-block;
			padding-top:4px;
		}
	</style>
</head>
<body> 

	<div class="content"> 
		<form class="m-form" method="POST" id='regForm' >
			<div class="m-form-item">
		    	<div class="m-label">姓 名:</div >
		    	<div class="m-input-box">
		    		<span type="text" id="userName" name="userName"></span>
		    	</div>
			</div>
			<div class="m-form-item">
		    	<div class="m-label">性 别:</div>
		    	<div class="m-input-box">
					<span type="text" id="userSex" name="userSex"></span>
				</div>
			</div>
			<div class="m-form-item">
		    	<div class="m-label">手机号:</div>
		    	<div class="m-input-box">
		    		<span type="text" id="userTel" name="userTel"></span>
		    	</div>
			</div>
			 <div class="m-form-item">
		    	<div class="m-label">车牌号:</div>
		    	<div class="m-input-box">
		    		<span type="text" id="carNumber" name="carNumber"></span>
		    	</div>
			</div> 
			<div class="m-form-item">
		    	<div class="m-label">VIN:</div>
		    	<div class="m-input-box">
		    		<span type="text" id="carVin" name="carVin"></span>
		    	</div> 
			</div> 
			<Button type="button" id="btn_edit" class="btn_main" style="margin-top:10px;">编辑</Button>
		 </form>
	</div>
	<input type="hidden" targetId="edit" urlType="url" title="编辑 跳转至" />
<script type="text/javascript">
	var param = new Wx.Param();
	var form = new Util.Form('reg');
	var openid = param.getOPENID();
	var gzhHash = param.getGZHHASH('##GZHHASH##');
	var req = new Wx.Request();
	var url = param.getURL('##URL##', [{
		targetId : "item_0",
		src : "register-without-validate.jsp"
	}]);
	
	$('#btn_edit').click(function(){
		Util.Browser.jump(url[0].src, "17-2");
	});
	 
	//验证码等
	$(function(){
		console.log(openid);
		var wxUser = new Wx.User();
		wxUser.getUserInfo(function(result){
			console.log(result);
			form.setValues({"userName": result.userName});
			form.disableInputs(["userName"]);
			form.setValues({"userSex": result.userSex});
			form.disableInputs(["userSex"]);
			form.setValues({"userTel": result.userTel});
			form.disableInputs(["userTel"]);
			if (result.carNumber) {
				//start modify by lyn 2014/5/27
				//form.setValues({"carNumber": result.carNumberPfx+"-"+result.carNumber});
				form.setValues({"carNumber": result.carNumberPfx + result.carNumber.substring(0,1)+"-"+result.carNumber.substring(1,result.carNumber.length)});
				//end modify by lyn 2014/5/27
			}
			form.disableInputs(["carNumber"]);
			form.setValues({"carVin": result.carVin});
			form.disableInputs(["carVin"]);
		});
	});
	
</script>

<script type="text/javascript">
	var wx = new Wx.Param();
	var gzhHash = wx.getGZHHASH("##GZHHASH##");
	var openid = wx.getOPENID();
	var stat = new Wx.Stat();
	stat.report('17-2', "用户个人信息", gzhHash, openid, {});
</script>
</body>
</html>
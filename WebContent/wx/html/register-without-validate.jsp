	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%> 
<%@ include file="/common/pre_general.jsp"%>
<!DOCTYPE html>
<html>
<head> 
  <title>注册申请</title>
  <style> 
    .list{
  		padding:10px;
  	}
  	.list h3{
  		margin:0;
  		line-height:40px; 
  	}
  	#CarNumberPfx{
  		width:60px;
  		height: 32px;
  		float:left;
		z-index:1000;
		padding:5px;
		border:1px solid #ccc;
		border-radius:3px;
		box-shadow: 0 1px 1px rgba(255,255,255,1),0 1px 2px rgba(0,0,0,.1) inset;
		font-size:14px;
		line-height:20px;
		margin-top: 2px;
  	}
  	#carNumber{
  		float:left;
  		display:inline;
  		width: auto;
  		margin-left:5px;
  	}
  	
 .red{
	color:red;
	font-size: 13px;
}
  </style>
</head>
<body> 
<div class="page_main"> 
	<div class="content">
		<form id="form_reg" method="POST" name='reg' class="m-form">
			<div class="m-form-item">
		    	<div class="m-label"><span class="red">*</span>姓 名:</div >
		    	<div class="m-input-box">
		    		<input type="text" id="userName" style='width:60%' />
		    	
		    	
			    	<select id='userSex' style='width:38%'>
							<option value='先生' selected>先生</option>
							<option value='女士'>女士</option>
					</select>
				</div>
			</div>
			 
			<div class="m-form-item">
		    	<div class="m-label"><span class="red">*</span>手机号:</div>
		    	<div class="m-input-box">
		    		<input type="text" id="userTel"/>
		    		 
		    	</div> 
			</div>
			<div class="m-form-item" id='valiCodeContainer'>
				<div class="m-label">验证码:</div>
				<div class="m-input-box">
					<input type='text' id='validatorCode' style='width:35%;inline-block'name='validatorCode'  />
					<button class='m-msg-button' type='button'  id='msgcode'  style='width:63%;inline-block'>获取验证码 </button>
				</div>
			</div> 
			 <div class="m-form-item">
		    	<div class="m-label">车牌号:</div> 
		    	<div class="m-input-box">
			    	<select name="carNumberPfx" id="carNumberPfx" >
						<option value="京">京</option>
						<option value="沪">沪</option>
						<option value="港">港</option>
						<option value="吉">吉</option>
						<option value="鲁">鲁</option>
						<option value="冀">冀</option>
						<option value="湘">湘</option>
						<option value="青">青</option>
						<option value="苏">苏</option>
						<option value="浙">浙</option>
						<option value="粤">粤</option>
						<option value="台">台</option>
						<option value="甘">甘</option>
						<option value="川">川</option>
						<option value="黑">黑</option>
						<option value="蒙">蒙</option>
						<option value="新">新</option>
						<option value="津">津</option>
						<option value="渝">渝</option>
						<option value="澳">澳</option>
						<option value="辽">辽</option>
						<option value="豫">豫</option>
						<option value="鄂">鄂</option>
						<option value="晋">晋</option>
						<option value="皖">皖</option>
						<option value="赣">赣</option>
						<option value="闽">闽</option>
						<option value="琼">琼</option>
						<option value="陕">陕</option>
						<option value="云">云</option>
						<option value="贵">贵</option>
						<option value="藏">藏</option>
						<option value="宁">宁</option>
						<option value="桂">桂</option>
					</select>
			    	<input id='carNumber' type='text' style='float:right;width:65%' />
		    	</div>
			</div> 
			<div class="m-form-item"> 
				<div style=''>
					<span  id='agreement' style='color:#113859;padding:2px 3px;text-decoration: underline'>※ 用户服务协议</span> 
					<span>同意：</span>
					<span><input type="checkbox" id='agree' class='m-checkbox' checked/><label for="agree"></label></span> 
				</div>
			</div>			
			<div class="m-form-item" style='height:30px;'> 
				<div>
					<span class="red">*为必填项</span>
				</div>
			</div>
			<div class="m-form-item" style='height:30px;'> 
				<div>
					<span style="font-size:12px;line-height:14px;">※ 如您是广汽TOYOTA车主，请填写车牌号或VIN完善个人信息，享受更全面的服务。</span>
				</div>
			</div>
			<div style='clear:both'></div>
			<Button type="button" class="btn_main button" id='regbtn'>提 交</Button>
		 </form>
	</div> 
	</div>
<input type="hidden" targetId="submit" urlType="url" title="提交  跳转至" />
<script type="text/javascript">
	var param = new Wx.Param();
	var form = new Util.Form('reg');
	var formdata;
	var openid = param.getOPENID();
	var gzhHash = param.getGZHHASH('##GZHHASH##');
	var req = new Wx.Request();
	var vali = 1;// 是否需要填写验证码      1为需要    0为不需要
	var url = param.getURL('##URL##', [ {
		targetId : "item_0",
		src : "person-info.jsp"
	}]);
	 
	//验证码等
	$(function(){
		var wxUser = new Wx.User();
		wxUser.getUserInfo(function(result){
			console.log(result);
			if (result) {
				form.setValues({"userName": result.userName});
				form.setValues({"userSex": result.userSex});
				$(".radio").each(function(){
					if($(this).parent().attr("for")=="userSex"){
						if ($(this).attr("value")== result.userSex){
							$(this).removeClass("radio_off");
							$(this).addClass("radio_on");
						} 
						else{
							$(this).removeClass("radio_on");
							$(this).addClass("radio_off");
						}
					}
				});
				$("input[type=radio][name=userSex][value="+result.userSex+"]").attr("checked", "checked");
				
				form.setValues({"userTel": result.userTel});
				form.setValues({"carNumberPfx": result.carNumberPfx});
				form.setValues({"carNumber": result.carNumber});
				
				if (result.userTel) {
					$("#valiCodeContainer").hide();
					$("#msgcode").hide();
					vali = 0;
					$("#userTel").change(function(){
						if ($(this).val()==result.userTel) {
							$("#valiCodeContainer").hide();
							$("#msgcode").hide();
							vali = 0;
						} else {
							$("#valiCodeContainer").show();
							$("#msgcode").show();
							vali = 1;
						}
					});
				}
			}
		});
		
		
		$('#msgcode').bind('click', function(){
			form['vali'] = 1;
			req.postData("/user/sendUserSMSValidatorCode", form.getValues(), function(ret){
				Util.Timer.play(1000, 60, function(count, remains){
					if(remains){
						form.setValues({
							"msgcode": remains + '秒后重试'
						});
						form.disableInputs(['msgcode']);
					}else{
						form.setValues({
							"msgcode": '重新发送'
						});
						form.enableInputs(['msgcode']);
					}
				});
			});
		});
		
		$('#regbtn').bind('click', function(){
			
			formdata = form.getValues();
			if ($("#userName").val().length == 0) {
				alert("请输入姓名");
				return;
			}
			if ($("#userTel").val().length == 0) {
				alert("请输入手机号");
				return;
			}
			if(formdata.agree != true){
				alert('请认真阅读用户服务协议, 同意后方可进行下一步.');
				return false;
			}

			formdata['gzhHash'] = gzhHash;
			formdata['openId'] = openid;
			formdata['vali'] = vali;
			formdata['registerFrom'] = "17-2-a 会员中心-个人注册";
			console.log(formdata);
			req.postData("/user/addUserInfo", formdata, function(res){
				 console.log(res);
				 if (res.success) {
					 if (formdata['carNumber']) {
						 req.postData("/user/addUserCar", formdata, function(res){
							 console.log(res);
							 if (res.success) {
								 Util.Browser.jump(url[0].src, "17-2-a");
							 }
						});
					 } else {
						 Util.Browser.jump(url[0].src, "17-2-a");
					 }
				 }
			});
			
		});
		
		var data = param.getDATA('##DATA##', [{"agreement":"用户须知..."}]);
		$('#agreement').bind('click', function(){
			$(".page_main").css("display","none");
			$('#info_reg').css("display","block"); 
		});
		$('#btn_known').bind('click', function(){
			$('#info_reg').css("display","none");
			$(".page_main").css("display","block");
		});
		$('#agreementtxt').html(data[0].agreement);
		$('#agreement_hidden').val(data[0].agreement);
	});
	/*--------------------------------------------------------------------------*/
	
	/*--------------------------------------------------------------------------*/
</script>
	<div id="info_reg" style="display:none"> 
    	<div class="content" >
    		<form class='m-form'>
					<div class='m-form-item-header'>用户服务协议</div>
					<div id='agreementtxt'></div>  
				<Button type="button" id="btn_known" class="m-msg-button">我知道了</Button>
			</form>
		</div>
	</div>
	<div style="display:none;" id="data">[{"agreement":"##agreement_0#"}]</div>
	
    <input id='agreement_hidden' type="hidden" targetId="##agreement_0#" dataType="value" inputType="textarea" title="条款" value="" >
<script type="text/javascript">
	var wx = new Wx.Param();
	var gzhHash = wx.getGZHHASH("##GZHHASH##");
	var openid = wx.getOPENID();
	var stat = new Wx.Stat();
	stat.report('17-2-a', "用户注册/编辑", gzhHash, openid, {});
</script>
</body>
</html>
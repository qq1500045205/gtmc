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
  	.hidden{
  		display:none; 
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
 
  </style>
</head>
<body> 
	<div class="content">  
		<form class="m-form" id="form_reg" method="POST" name='reg'>
			<div class="m-form-item">
		    	<div class="m-label">姓 名:</div >
		    	<div class="m-input-box">
		    		<input type="text" id="userName" style='width:60%' />
		    	
		    	
			    	<select id='userSex' style='width:38%'>
							<option value='先生' selected>先生</option>
							<option value='女士'>女士</option>
					</select>
				</div>
			</div>
			
			<div class="m-form-item">
		    	<div class="m-label">手机号:</div>
		    	<div class="m-input-box">
		    		<input type="text" id="userTel"/>
		    		<Button type="button" class="btn_def" id='msgcode'>发送验证码</Button>
		    	</div> 
			</div>
			<div class="m-form-item" id="valiCodeContainer" style='padding: 4px 0px;'>
		    	<div  class="m-label">输入验证码</div>
		    	<div class="m-input-box">
		    		<input id='validatorCode' type='text' name='validatorCode'/>
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
		    		<input id='carNumber' type='text' style='float:right;width:72%;;' />
		    	</div>
			</div> 
			<div class="m-form-item">
		    	<div class="m-label"></div>
		    	<div class="m-input-box">
					<span  id='btn_info' style='color:#113859;padding:2px 3px;text-decoration: underline;'>※ 注册须知</span>
				</div>
			</div>
			<Button type="button" class="btn_main" id='regbtn'>提 交</Button>
		 </form>
	</div> 
 <!-- 注册须知 -->
<div id="info_reg" class="hidden"> 
	<div class="content">
		<div class="list">
			<h3>注册须知</h3>
			1.提供真实的车主信息，更方便服务<br>
			2.
		</div>
	<Button type="button" id="btn_known" class="btn_main" >我知道了</Button>
	</div>
</div>
<input type="hidden" targetId="submit" urlType="url" title="提交  跳转至" />
<script type="text/javascript">

	var param = new Wx.Param();
	var form = new Util.Form('reg');
	 var util = new Util.Param();
	var formdata;
	var openid = util.get('openid');
	var gzhHash = util.get('gzhHash');
	var gzhHash_1= util.get('gzhHash');
	var actGuid=util.get('actGuid');

	var req = new Wx.Request();
	var vali = 1;// 是否需要填写验证码      1为需要    0为不需要
	var url = param.getURL('##URL##', [ {
		targetId : "item_0",
		src : "#"
	}, {
		targetId : "item_1",
		src : "#"
	} ]);
	var url1 = param.getURL('##URL##', [{src: "${ctx}/wx/html/integralActivities.jsp"}]) ;
	
	$("#regForm").submit(function() {
		saveRegister();
		return false;
	});
	
	$('#btn_info').click(function(){
		$('#info_reg').css("display","block");
		$('#form_reg').css("display","none");
	});
	
	$('#btn_known').click(function(){
		$('#info_reg').css("display","none");
		$('#form_reg').css("display","block");
	});
	 
	//验证码等
	$(function(){
		$('#msgcode').bind('click', function(){
			form['vali'] =1;
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
			
			if ($("#userName").val().length == 0) {
				alert("请输入姓名");
				return;
			}
			if ($("#userTel").val().length == 0) {
				alert("请输入手机号");
				return;
			}

			formdata = form.getValues();
			formdata['gzhHash'] = gzhHash;
			formdata['openId'] = openid;
			formdata['vali'] = vali;
			console.log(formdata);
			req.postData("/user/addUserInfo", formdata, function(res){
				 console.log(res);
				 if (res.success) {
					 alert("恭喜您，注册成功！");
					 Util.Browser.jump("${ctx}/wx/html/integralActivities.jsp?actGuid="+actGuid+"&gzhHash_1="+gzhHash_1+"&openid="+openid, "");
					 getAndSaveUser2car(formdata);
				 }
			});
			
		});
	});
	
	function getAndSaveUser2car(data){
		console.log("getAndSaveUser2car")
		var upgrade = false;
		getTactCarInfo(function(res, status){
			formdata['carStatus'] = status;
			switch (status) {
			case 'OK':
				formdata['carVin'] = res.vinno;
				formdata['carNumberPfx'] = res.registerno.substring(0,1);
				formdata['carNumber'] = res.registerno.substring(1,res.length).split('-').join('');
				formdata['carBelongProject'] = res.dealercode;
				upgrade = true;
				break;
			case 'Tel_And_Car':
			case 'No_Tel_And_Car':
				if (confirm('系统中与您手机号匹配的车辆信息和您填写的不一致。是否使用系统中车辆信息替代您填的车辆信息?')) {
					formdata['carVin'] = res.vinno;
					formdata['carNumberPfx'] = res.registerno.substring(0,1);
					formdata['carNumber'] = res.registerno.substring(1,res.length).split('-').join('');
					formdata['carBelongProject'] = res.dealercode;
					upgrade = true;
				}
				break;
			case 'Tel_No_Car':
			case 'No_Tel_No_Car':
				alert('恭喜您已注册为广汽丰田微信普通会员。\n系统中尚未发现与您手机号匹配的车辆信息。如您是车主，请及时与销售店联系变更手机号，完善个人信息以便我们为您提供后续服务。');
				break;
			default: 
				Util.Browser.jump(url[0].src, "17-2-a");
				break;
			}
			req.postData("/user/addUserCar", formdata, function(res){
				 console.log(res);
				 if (res.success) {
					 if (upgrade) {
						req.postData("/user/upgradeUser", formdata, function(res){
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
	}
	
	function getTactCarInfo(callback) {
		req.postData("/user/getCarInfoFromTact", formdata, function(res){
			callback(res.result, res.message);
		});
		/* 
		var result = {
			buyercode: "0000000001",
			buyername: "云南华联投资开发有限公司(马)",
			color: "水晶银",
			colorcode: "1D4 ",
			dealercode: "53A20",
			engine: "2AZC060040",
			exhaust: "2.4L",
			grade: "240G LA40",
			mission: "5AT",
			nomineecode: "0000000001",
			nomineename: "云南华联投资开发有限公司(马)",
			registerno: "云A-HP771 ",
			srvcustomercode: "0000000002",
			srvcustomname: "云南华联投资开发有限公司(马",
			vhccode: "CAMRY",
			vhcname: "凯美瑞",
			vhcsalesdate: "2006-06-22 00:00:00",
			vinno: "LVGBE42K86G001149 "
		};
		var status = "No_Tel_No_Car"; */
		
	}
	
	// alert(confirm("save?"));
	// alert("你好aaa".substring(1,"你好aaa".length));
	// alert('恭喜您已注册为广汽丰田微信普通会员。\n但是系统中没有发现与您的手机号匹配的车辆信息。如果您是车主，请及时与销售店联系变更手机号，完善个人信息以便我们为您提供后续服务。');
	
</script>
<script src="${ctx}/template/dist/js/js_general.js"></script>
<script type="text/javascript">
	var wx = new Wx.Param();
	var gzhHash = wx.getGZHHASH("##GZHHASH##");
	var openid = wx.getOPENID();
	var stat = new Wx.Stat();
	stat.report('17-2-a', "用户注册/编辑", gzhHash, openid, {});
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>活动报名</title>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/wx.js"></script>
<%@ include file="/common/datepick.jsp"%> 
<style type="text/css">
.red{
	color:red;
	font-size: 13px;
}
</style>
</head>
<body>
<div class="page_main"> 
	<div class="content">
	<form action="${ctx }/activity/signActive" method="post" id="signForm" name="signForm" class="m-form">  
		<input type="hidden" name="userGuid"  id="userGuid">
		 
			<div class="m-form-item"> 
				<div class="m-label">活动名称：</div>
				<div class="m-input-box">
					<input type="text" name="actName" id="actName" disabled="disabled">
				</div>
			</div>
			<div class="m-form-item"> 
				<div class="m-label"><span class="red">*</span>姓名：</div>
				<div class="m-input-box">
					<input type="text" name="userName" id="userName">
				</div>
			</div>
			<div class="m-form-item"> 
				<div class="m-label"><span class="red">*</span>手机号：</div>
				<div class="m-input-box">
					<input type="text" name="userMobile" id="userMobile">
				</div>
			</div>
			<div class="m-form-item" id="userCarNoTr"> 
				<div class="m-label"><span class="red">*</span>车牌号：</div>
				<div class="m-input-box">
					<input type="text" name="userCarNo" id="userCarNo">
				</div>
			</div>
			<div class="m-form-item" id="attendOnTr"> 
				<div class="m-label"><span class="red">*</span>参加时间：</div>
				<div class="m-input-box">
					<input type="text" name="attendOn" id="attendOn">
				</div>
			</div>
			<div class="m-form-item" id="attendNumTr"> 
				<div class="m-label"><span class="red">*</span>参加人数：</div>
				<div class="m-input-box">
					 <div class="m-label" style='width:50px;'>成人</div>
					<select style='width:40%;inline-block' name="attendNum" id="attendNum"></select> 人
					 
				</div>
			</div>
			<div>
				<div class="m-form-item" id="attendNumTr2"> 
					<div class="m-label"></div>
					<div class="m-input-box">
					<div class="m-label" style='width:50px;'>儿童</div>
						<select style='width:40%;inline-block' name="attendChild" id="attendChild"></select> 人
					</div>
				</div>
			</div>
			<div class="m-form-item" id="arriveByTr" > 
				<div class="m-label"><span class="red">*</span>到场方式：</div>
				<div class="m-input-box">
					<select style='width:100%;inline-block'  name="arriveBy" id="arriveBy"></select>
				</div>
			</div>
			<div class="m-form-item" id="arriveLineTr"> 
				<div class="m-label"><span class="red">*</span>班车路线：</div>
				<div class="m-input-box">
					<select style='width:100%;inline-block' name="arriveLine" id="arriveLine"></select>
				</div>
			</div>
			<div class="m-form-item" id="remarkTr"> 
				<div class="m-label">备注：</div>
				<div class="m-input-box">
					 <textarea id="remark" name="remark"> </textarea><br/>
					 <span id="remarkInfo"></span>
				</div>
			</div>
			<div class="m-form-item"  id="sendValTr">
				<div class="m-label">验证码：</div>
				<div class="m-input-box">
					<input type='text' id='validatorCode' style='width:35%;inline-block'  />
					<button class='m-msg-button' type='button'  id='msgcode'  style='width:60%;inline-block'>获取验证码 </button>
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
			
			<Button type='button' class='btn_main' id="submitt">提交</Button>
	</form>
	</div>
	</div> 
	<input type="hidden" targetId="submitt" urlType="url" title="提交跳转至" />
	
	<script type="text/javascript">
		var util = new Wx.Param();
		var req = new Wx.Request();
		var openid = util.getOPENID();
		var gzhHash = util.getGZHHASH("##GZHHASH##");
		var user = new Wx.User();
		var param = new Util.Param();
		var storage = new Util.Storage();
		var form = new Util.Form("signForm");
		var registered = false;
		var type2 =  param.get("type2"); //是否为针对车主
		var hasCar = param.get("hasCar");
		var result = {}; //表单设置
		var wxuser = {};
		
		var urls = util.getURL('##URL##', [ {
			targetId : "item_0",
			src : "feedback-page.jsp"
		} ]);
		
		init();
		datepick("attendOn");
		
		var activeData = {};
			$.ajax({
				type : 'POST',
				url : "${ctx}/activity/getOneActive",
				async : false,
				data : {
					actGuid : param.get("actGuid")
				},
				dataType : "json",
				success : function(data) {
					if (data.success) {
						activeData = data.result;
						form.setValues({"actName": activeData.title});
					}
				}
			});
		
		
		
		user.getUserInfo(function(info){
			if(info){
				wxuser = info;
				registered = true;
				form.setValues({"userName": info.userName});
				form.setValues({"userMobile": info.userTel});
				form.setValues({"userGuid": info.userGuid});
			}			
		});
		
		$('#msgcode').bind('click', function(){
			var formdata = form.getValues();
			if(formdata.agree != true){
				alert('请认真阅读用户服务协议, 同意后方可进行下一步.');
				return;
			}
			form.disableInputs(['msgcode']);
			
			formdata["userTel"]=formdata.userMobile;
			if(result.sendVal.form_field_show  == 1 ){
				formdata["vali"]=1; //需要验证
			}else{
				formdata["vali"]=0; //不需要验证
			}
			
			req.postData("/user/sendUserSMSValidatorCode", formdata, function(ret){
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
		
		//活动报名
    	window.active_sign = function(){
    		var formdata = form.getValues();
    		formdata['gzhHash'] = gzhHash;
    		formdata['openid'] = openid;
    		formdata['actGuid'] = param.get("actGuid");
    		formdata['type'] = result.sendVal.form_field_show;
    		formdata['actName'] = activeData.title;
    		
    		req.postData("/activity/signActive", formdata, function(ret){
   				if(formdata["vali"] == 1 && ret.message=='codefailure'){
   					alert('验证码出错');
   					return;
   				}
				
				if(ret.success){
					Util.Browser.jump(urls[0].src, '3-3');
				} else {
					alert('您还没有关注，请先关注！');
				}
			});
    	};
		
		$('#submitt').bind('click', function(){
			var formdata = form.getValues();
		
			if(formdata.userName == ''){
				alert('请填写姓名.');
				return;
			}
			if(formdata.userMobile == ''){
				alert('请填写手机号.');
				return;
			}
			var str = check();
			if(str != "success"){
				alert(str);
				return;
			}
			if(formdata.agree != true){
				alert('请认真阅读用户服务协议, 同意后方可进行下一步.');
				return false;
			}
			//如果是针对车主的，则车牌号必填
			if(type2 == 1){
				if(formdata.userCarNo != ''){
					alert('请填写车牌号.');
					return;
				}
				formdata['memberLevel'] = 2;
			}
			console.log(registered);
			//注册时处理车牌号
			if(registered == false){
				//unregistered
				//post reg
				formdata['gzhHash'] = gzhHash;
				formdata['openId'] = openid;
				form.disableInputs(['msgcode']);
	    		req.postData("/user/addUserInfo", formdata, function(){
	    			active_sign();
	    		});
			} else{
				active_sign();
			}
		});
		
		//如果显示则必输的项目校验
		function check(){
			/* if(result.userCarNo.form_field_show  == 1){ 
				if($("#userCarNo").val() == ''){
					return false;
				}
			} */
			if(result.attendOn.form_field_show  == 1){
				if($("#attendOn").val() == ''){
					return "请填写参加时间";
				}
			}
			if(result.attendNum.form_field_show  == 0){
				if($("#attendNum").val() == ''){
					return "请填写参加成人数";
				}
			}
			if(result.attendChild.form_field_show  == 0){
				if($("#attendChild").val() == ''){
					return "请填写参加儿童数";
				}
			}
			if(result.arriveBy.form_field_show  == 0 ){
				if($("#arriveBy").val() == ''){
					return "请选择到场方式";
				}
			}
			if(result.arriveLine.form_field_show  == 0 ){
				if($("#arriveLine").val() == ''){
					return "请选择乘车路线";
				}
			}
			return  "success";
		}
		
		function init(){
			//获取表单设置
			$.ajax({
				type : 'POST',
				url : "${ctx}/activity/getFormActive",
				data : {
					actGuid : param.get("actGuid")
				},
				dataType : "json",
				success : function(data) {
					if(data.success){
						result = JSON.parse(data.result);
						//设置是否显示
						if(type2 != 1 && result.userCarNo.form_field_show  == 0){ //如果是针对车主的报名，则该字段不能隐藏
							$("#userCarNoTr").hide();
						}
						if(result.attendOn.form_field_show  == 0){
							$("#attendOnTr").hide();
						}
						if(result.attendNum.form_field_show  == 0 && result.attendChild.form_field_show  == 0 ){
							$("#attendNumTr").hide();
							$("#attendNumTr2").hide();
						}
						if(result.attendNum.form_field_show  == 0){
							$("#attendNum").hide();
						}
						if(result.attendChild.form_field_show  == 0){
							$("#attendChild").hide();
						}
						if(result.arriveBy.form_field_show  == 0 ){
							$("#arriveByTr").hide();
						}
						if(result.arriveLine.form_field_show  == 0 ){
							$("#arriveLineTr").hide();
						}
						if(result.remark.form_field_show  == 0 ){
							$("#remarkTr").hide();
						}
						if(result.sendVal.form_field_show  == 0 ){
							$("#sendValTr").hide();
						}
						
						//如果显示则设置数据
						if(result.attendNum.form_field_show  == 1 && result.attendNum.form_select_max > 0){
							for(var i=1;i<result.attendNum.form_select_max;i++){
								$("#attendNum").append("<option value="+i+">"+i+"</option>");
							}
						}else {
							$("#attendNum").append("<option value=0>1</option>");
						}
						if(1 == result.attendChild.form_field_show){
							var html = "";
							if( result.attendChild.form_select_max > 0){
								for(var i=1;i<=result.attendChild.form_select_max;i++){
									html += "<option value="+i+">"+i+"</option>";
								}
							}
							else{
								html = "<option value='0'>0</option>";
							}
							$("#attendChild").html(html);
						}
						var total = parseInt(result.attendNum.form_select_max) + parseInt(result.attendChild.form_select_max);
						if(total > 0){
							for(var i=1;i<=total;i++){
								$("#attendPerson").append("<option value="+i+">"+i+"</option>");
							}
						}
						
						if(result.arriveBy.form_field_show  == 1 && result.arriveBy.form_select){
							var ary = result.arriveBy.form_select;
							for(var i=0;i<ary.length;i++){
								$("#arriveBy").append("<option value="+ary[i].value+">"+ary[i].text+"</option>");
							}
						}
						if(result.arriveLine.form_field_show  == 1 && result.arriveLine.form_select){
							var ary = result.arriveLine.form_select;
							for(var i=0;i<ary.length;i++){
								$("#arriveLine").append("<option value="+ary[i].value+">"+ary[i].value+"</option>");
							}
						}
						
						if(result.remark.form_field_show  == 1 &&result.remark.form_remark_num > 0 ){
							$("#remarkInfo").text("内容不能多于"+result.remark.form_remark_num+"个字");
						}
					}
				}
			});
		}
		
		/*--------------------------------------------------------------------------*/
    	var data = util.getDATA('##DATA##', [{"agreement":"用户须知..."}]);
    	$(function(){ 
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
    	
		var stat = new Wx.Stat();
    	stat.report('3-3', "活动报名", gzhHash, openid, {});
	</script>
	<script src="${ctx}/template/dist/js/js_general.js"></script>
	
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
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/wx.js"></script>
<%@ include file="/common/datepick.jsp"%> 
<title>预约维修保养</title>
<style>
	.list{
		padding:10px;
	}
	.list h3{
		margin:0;
		line-height:40px;  
	}
	.btn_msg{
		float:right;
		width:170px;
		height:30px;
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
		<form action="" method='post' name='yuyue' class='m-form'>
		
			<div class='m-form-item-header'>个人信息</div>
			<div class="m-form-item"> 
				<div class="m-label request">姓名:</div>
				<div class="m-input-box">
					<input id='userName' type='text' style='width:60%' />
					
					<select id='userSex' style='width:30%;display:inline;float:right;'>
						<option value='先生' selected>先生</option>
						<option value='女士'>女士</option>
					</select>
				</div>
			</div>
			
			<div class="m-form-item"> 
				<div class="m-label request">电话:</div>
				<div class="m-input-box">
					<input id='userTel' type='text' />
				</div>
			</div> 
			<div class='clear'></div>
			
			
			 
			<div class='m-form-item-header'>预约信息</div>
			<div class="m-form-item" id='carTypeGuidBox'>
				<div class="m-label request">车牌号:</div>
				<div class="m-input-box">
					<select name="carNumberPfx" id="carNumberPfx" style='width:25%;display:inline;'>
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
					<input id='carNumber' type='text' style='width:60%;display:inline;float:right;'/>
				</div>
			</div>
			
		 
			<!--  -->
			<div class="m-form-item" id='dealerProvinceBox'>
				<div class="m-label request">省份:</div>
				<div class="m-input-box">
					<select id='dealerProvince' name='dealerProvince' ></select>
					<input type='hidden' id='dealerProvinceName' name='dealerProvinceName' />
				</div>
			</div>
			<div class="m-form-item" id='dealerCityBox'>
				<div class="m-label request">城市:</div>
				<div class="m-input-box">
					<select id='dealerCity' name='dealerCity' ></select>
					<input type='hidden' id='dealerCityName' name='dealerCityName' />
				</div>
			</div>
			<div class="m-form-item" id='dealerNameBox'>
				<div class="m-label request">销售店:</div>
				<div class="m-input-box">
					<select id='dealerGuid' name='dealerGuid' ></select>
					<input type='hidden' id='mediaCode' name='mediaCode'>
					<input type='hidden' id='dealerCode' name='dealerCode'>	
				</div>					
			</div>
			<div class="form_item"  style='padding:10px 4px;' ><div class="m-label"></div><div id='address'></div></div>
			
			
		
			<div class="m-form-item" id='validatorBox'>
				<div class="m-label">验证码:</div>
				<div class="m-input-box">
					<input type='text' id='validatorCode' style='width:35%;display:inline-block;'  />
					<button class='m-msg-button' type='button'  id='msgcode'  style='width:55%;display:inline-block;'>获取验证码 </button>
				</div>
			</div> 
			 
			 
			<div class="m-form-item" id='ruleBox'>
				<div style=''>
					<span  id='agreement' style='color:#113859;padding:2px 3px;text-decoration: underline'>※ 用户服务协议</span>
					<span>同意: </span>
					<span><input type="checkbox" id='agree' class='m-checkbox' checked/><label for="agree"></label></span> 
				</div>
			</div>
			
			<div class="m-form-item" style='height:30px;'> 
				<div>
					<span class="red">*为必填项</span>
				</div>
			</div>
			
			<Button type='button' class='btn_main' id='reserve' >预约维修保养</Button> 
		</form>
	</div>
</div>
	 <!-- /container -->

    <!-- 
    ================================================== -->
    <script>
    	/*--------------------------------------------------------------------------*/
		var testurls = [
			{src:"${ctx}/wx/html/feedback-page.jsp", targetId: "yuyue_url"},
			{src:"${ctx}/wx/html/agreement-page.jsp", targetId: "yuyue_url"}
		];
		/*--------------------------------------------------------------------------*/
		var form = new Util.Form('yuyue');
		var storage = new Util.Storage();
    	var param = new Wx.Param();
    	var openid = param.getOPENID();
    	var gzhHash = param.getGZHHASH('##GZHHASH##');
    	window.GZHTYPE = '##GZHTYPE##';
    	var urls = param.getURL('##URL##', testurls);
    	var gzhType = param.getGZHTYPE();
    	var entrance = Util.Browser.getEntrance();
    	var registred = false;
    	var req = new Wx.Request();
    	var urlparam = new Util.Param();
    	/*--------------------------------------------------------------------------*/
    	// --- TEST CASE ---
    	/*--------------------------------------------------------------------------*/
    	//DLR
    	/* gzhType = 'DIST';
   		
   		if(1){
   			//入口A
	   		entrance = '2-4';
	   		storage.set("carTypeGuid", "da07704b-d5e5-4fb2-a14d-7573ae244e19");
   		}else{
   			//入口B
   			storage.set("dealerGuid", "4");
	     	storage.set("cityCode", "22");
	     	storage.set("provinceCode", "1");
   		} */
    	/*--------------------------------------------------------------------------*/
    	$(function(){
    		if(gzhType=='DLR'){
    			//DLR
    			form.hideControls(['dealerProvinceBox', 'dealerCityBox', 'dealerNameBox']);
    			req.postData("/dealerquery/queryDealerInfo?gzhHash="+gzhHash, {}, function(d){
    				var dealerGuid = d.result.projectGuid;
					var provinceCode = d.result.provinceCode;
					var cityCode = d.result.cityCode;
    				req.postData("/dealer/getAllDealerAddressProvinces", {}, function(data){
    					
    					window.provinces = data.rows;
    					
            			form.setOptions('dealerProvince', data.rows, 'provinceCode', 'provinceName');
            			$('#dealerProvince').on('change', function(){
            				var idx = this.selectedIndex;
            				var provinceName = provinces[idx].provinceName;
            				form.setValues({
    							"dealerProvinceName": provinceName
    						});
            				req.postData("/dealer/getAllDealerAddressCitiesByProvince?province_code=" + $('#dealerProvince').val(), {}, function(ret){
            					form.setOptions('dealerCity', ret.rows, 'cityCode', 'cityName');
            					//
            					window.cities = ret.rows;
            					
            					$('#dealerCity').on('change', function(){
            						
            						
            						var idx = this.selectedIndex;
            						var cityName = cities[idx].cityName;
            						var mediaCode = cities[idx].mediaCode;
            						form.setValues({
            							"dealerCityName": cityName
            						});
            						
            						req.postData("/dealer/getAllDealersByCity?cityCode=" + $('#dealerCity').val(), {}, function(ret){
    	        						form.setOptions('dealerGuid', ret.rows, 'projectGuid', 'projectName');
    	        						//
    	        						//window.cities = ret.rows;
            					
    	        		    			/*testing---------*/
    	        		    			/*----------------*/
    	        		    			form.setValues({
    	        		    				"provinceCode": provinceCode, 
    	        		    				"cityCode": cityCode, 
    	        		    				"dealerGuid": dealerGuid
    	        		    			});
    	        						
    	        						window.addresses = ret.rows; 
    	        		    			$('#dealerGuid').on('change', function(){
    	        		    				var idx = this.selectedIndex;   
    	        		    				var addr = addresses[idx].address;
    	        		    				var dealerCode = addresses[idx].dealerCode;
    	        		    				form.setValues({
    	        		    					"address":addr,
    	        		    					"mediaCode": mediaCode,
    	        		    					"dealerProvinceName": $('#dealerProvinceName').val(),
    	        		    					"dealerCityName": $('#dealerCityName').val(),
    	        		    					"dealerCode": dealerCode
    	        		    				});
    	        		    			});
    	        		    			
    	        		    			$('#dealerGuid').trigger('change');
            						});
            					});
            					//--------
            					form.setValues({
            	    				"cityCode": cityCode,
            	    				"dealerCity": cityCode
                    			});
            					$('#dealerCity').trigger('change');
            				});
            				//$('#dealerProvince').trigger('change');
                		});
            			//--------
            			if(provinceCode!=''){
            				form.setValues({
        	    				"provinceCode": provinceCode,
        	    				"dealerProvince": provinceCode	    				
                			});
            				$('#dealerProvince').trigger('change');
            			}else{
            				form.setValues({
        	    				"provinceCode": "",
        	    				"dealerProvince": provinceCode	    				
                			});
            			}
            			
            			 
            		});
    			});
    		}else if(gzhType=='DIST'){
    			//DIST
    			req.postData("/dealer/getAllDealerAddressProvinces", {}, function(data){
					window.provinces = data.rows;
					
					var dealerGuid = urlparam.get('dealerGuid');
					var provinceCode = urlparam.get('provinceCode');
					var cityCode = urlparam.get('cityCode');
					
        			form.setOptions('dealerProvince', data.rows, 'provinceCode', 'provinceName');
        			$('#dealerProvince').on('change', function(){
        				var idx = this.selectedIndex;
						var provinceName = provinces[idx].provinceName;
						form.setValues({
							"dealerProvinceName": provinceName
						});
						
        				req.postData("/dealer/getAllDealerAddressCitiesByProvince?province_code=" + $('#dealerProvince').val(), {}, function(ret){
        					form.setOptions('dealerCity', ret.rows, 'cityCode', 'cityName');
        					window.cities = ret.rows;
        					
        					//
        					$('#dealerCity').on('change', function(){
        						var idx = this.selectedIndex;
        						var cityName = cities[idx].cityName;
        						var mediaCode = cities[idx].mediaCode;
        						form.setValues({
        							"dealerCityName": cityName
        						});
        						req.postData("/dealer/getAllDealersByCity?cityCode=" + $('#dealerCity').val(), {}, function(ret){
	        						form.setOptions('dealerGuid', ret.rows, 'projectGuid', 'projectName');
	        						//
	        						
	        		    			/*testing---------*/
	        		    			/*----------------*/
	        		    			form.setValues({
	        		    				"provinceCode": provinceCode, 
	        		    				"cityCode": cityCode, 
	        		    				"dealerGuid": dealerGuid,
	        		    				
	        		    			});
									window.addresses = ret.rows;
	        						
	        		    			$('#dealerGuid').on('change', function(){
	        		    				var idx = this.selectedIndex;   
	        		    				var addr = addresses[idx].address;
	        		    				
	        		    				var dealerCode = addresses[idx].dealerCode;
	        		    				form.setValues({
	        		    					"address":addr,
	        		    					"mediaCode": mediaCode,
	        		    					"dealerProvinceName": $('#dealerProvinceName').val(),
	        		    					"dealerCityName": $('#dealerCityName').val(),
	        		    					"dealerCode": dealerCode
	        		    				});
	        		    			});
	        		    			
	        		    			$('#dealerGuid').trigger('change');
        						});
        					});
        					//--------
        					form.setValues({
        	    				"cityCode": cityCode,
        	    				"dealerCity": cityCode
                			});
        					$('#dealerCity').trigger('change');
        				});
        				//$('#dealerProvince').trigger('change');
            		});
        			//--------
        		 	if(provinceCode!=''){
        				form.setValues({
    	    				"provinceCode": provinceCode,
    	    				"dealerProvince": provinceCode,
    	    				"dealerCity": cityCode,
    	    				"dealerGuid": dealerGuid
            			});
        				$('#dealerProvince').trigger('change');
        		 	}else{
        		 		form.setValues({
    	    				"provinceCode": provinceCode,
    	    				"dealerProvince": provinceCode,
    	    				"dealerCity": cityCode,
    	    				"dealerGuid": dealerGuid
            			});
        		 	}
        			
        		});
    			//----------------------------
    		}
    		
    		//check user
    		var user = new Wx.User();
    		user.getUserInfo(function(info){
    			console.log("get user info is ",info);
    			if(info == null || info.memberLevel == null || info.memberLevel==0){
    				
    			}else{
					//regiestered
					registred = true;
					form.setValues({
						'userName': info.userName,
						'userSex': info.userSex,
						'userTel': info.userTel
					});

					form.setValues({"carNumberPfx": info.carNumberPfx});
					form.setValues({"carNumber": info.carNumber});
					form.disableInputs([ "userName","userTel", "yuyueCartypeGuid", "userSex"]);
					form.hideControls(['validatorBox', 'ruleBox']);
				}
			});
    	});
    	/*--------------------------------------------------------------------------*/
    	//预约试驾
    	window.yuyue_post = function(){
    		var formdata = form.getValues();
    		formdata['gzhHash'] = gzhHash;
    		formdata['openid'] = openid;
    		
    		req.postData("/yuyue/addYuyueWeiXiuBaoYang", formdata, function(ret){
    			if(ret.message=='codefailure'){
    				alert('验证码出错');
    			}else
    			if( ret.message=='success'){
    				Util.Browser.jump(urls[0].src, '11-1-a');
    			}
    		});
    	};
    	//点击事件
    	$(function(){
    		$('#reserve').bind('click', function(){
    			var formdata = form.getValues();
    			if(formdata.userName == ''){
    				alert('姓名为必填项.');
    				return;
    			}
    			if(formdata.userTel == ''){
    				alert('手机号为必填项.');
    				return;
    			}
    			if(formdata.carNumberPfx == '' || formdata.carNumber == ''){
    				alert('车牌号为必填项.');
    				return;
    			}
    			if(formdata.dealerProvince == ''){
    				alert('省份为必填项.');
    				return;
    			}
    			if(formdata.dealerCity == ''){
    				alert('城市为必填项.');
    				return;
    			}
    			if(formdata.dealerGuid == ''){
    				alert('销售店为必填项.');
    				return;
    			}
    			if(formdata.agree != true){
    				alert('请认真阅读用户服务协议, 同意后方可进行下一步.');
    				return;
    			}
    			
    			if(registred){
    				//registered
    				yuyue_post();
    			}else{
    				if(formdata.agree != true){
        				alert('请认真阅读用户服务协议, 同意后方可进行下一步.');
        				return;
        			}
    				//unregistered
    				//post reg
    				formdata['gzhHash'] = gzhHash;
    				formdata['openId'] = openid;
    				form.disableInputs(['msgcode']);
    	    		req.postData("/user/addUserInfo", formdata, function(){
    	    			yuyue_post();
    	    		});
    			}
    		});
    		
    		$('#msgcode').bind('click', function(){
    			var formdata = form.getValues();
    			if(formdata.userName == ''){
    				alert('姓名为必填项.');
    				return;
    			}
    			if(formdata.userTel == ''){
    				alert('手机号为必填项.');
    				return;
    			}
    			if(formdata.agree != true){
    				alert('请认真阅读用户服务协议, 同意后方可进行下一步.');
    				return;
    			}
    			form.disableInputs(['msgcode']);
    			formdata["vali"]=1;
    			req.postData("/user/sendUserSMSValidatorCode", formdata, function(ret){
    				
    			});
    			window.alert("短信已发送，请注意查收");
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
    	/*--------------------------------------------------------------------------*/
    	$(function(){
    		$('#yuyueTime').css('width', $('#yuyueCarmodulGuid').css('width'));
    		datepick("yuyueTime");
    	});
    	/*--------------------------------------------------------------------------*/
    	/*--------------------------------------------------------------------------*/
		var data = param.getDATA('##DATA##', [{"agreement":"用户须知....."}]);
		$(function(){ 
			$('#agreement').bind('click', function(){
				$('#info_reg').css("display","block");
				$('.page_main').css("display","none");
			});
			$('#btn_known').bind('click', function(){
				$('#info_reg').css("display","none");
				$('.page_main').css("display","block");  
			});
			$('#agreementtxt').html(data[0].agreement);
			$('#agreement_hidden').val(data[0].agreement);
		});
		/*--------------------------------------------------------------------------*/
    	var stat = new Wx.Stat();
    	stat.report('11-1-a', "预约维修保养", gzhHash, openid, {});
    </script>
    <script src="${ctx}/template/dist/js/js_general.js"></script>
    
    <div id="info_reg" style="display:none"> 
		<div class="content">
			<div class="list">
				<h3>用户服务协议</h3>
				<div id='agreementtxt'></div> 
			</div>
			<Button type="button" id="btn_known" class="btn_main">我知道了</Button>
		</div>
	</div>
	
    
    <div style="display:none;" id="data">[{"agreement":"##agreement_0#"}]</div>
    <input id='agreement_hidden' type="hidden" targetId="##agreement_0#" dataType="value" inputType="textarea" title="条款" value="'+data[0].name+'" >
    
    
    
    
  	<input type="hidden" targetId="yuyueshijia_do_url" urlType="url" title="提交预约成功跳转至" />
  	<div id="contentManager"><a style="display:none;" href="javascript:;" id="qushenghuo" title="${ctx}/admin/content/yuyue/index.jsp?type=shijia">预约管理</a></div>
  </body>
</html>

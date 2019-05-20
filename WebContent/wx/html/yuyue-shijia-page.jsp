<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/wx.js"></script>
<%@ include file="/common/datepick.jsp"%> 
<title>预约试驾</title>
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
		<form class='m-form' action="" method='post' name='yuyue'>
		
		
			<div class='m-form-item-header'>个人信息</div>
			<div class="m-form-item"> 
				<div class="m-label request">姓名:</div>
				<div class="m-input-box">
					<input id='userName' type='text' style='width:60%' />
					
					<select id='userSex' style='width:38%'>
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
				<div class="m-label request">车型:</div>
				<div class="m-input-box">
					<select id='carTypeGuid' name='carTypeGuid' ></select>
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
					<input type='text' id='validatorCode' style='width:35%;inline-block'  />
					<button class='m-msg-button' type='button'  id='msgcode'  style='width:63%;inline-block'>获取验证码 </button>
				</div>
			</div> 
			 
			 
			<div class="m-form-item" id='ruleBox'>
				<div class='m-label'></div>
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
			
			<Button type='button' class='btn_main' id='reserve' >预约试驾</Button>
		</form>
	</div>
	</div> 
 
    <script>
    	/*--------------------------------------------------------------------------*/
		var testurls = [
	            {src:"${ctx}/wx/html/feedback-page.jsp", targetId: "yuyue_url"},
	            {src:"${ctx}/wx/html/agreement-page.jsp", targetId: "yuyue_url"}
		];
		/*--------------------------------------------------------------------------*/
		var form = new Util.Form('yuyue');
		var storage = new Util.Storage();
		var urlparam = new Util.Param();
    	var param = new Wx.Param();
    	var openid = param.getOPENID();
    	var gzhHash = param.getGZHHASH('##GZHHASH##');
    	var urls = param.getURL('##URL##', testurls);
    	window.GZHTYPE = '##GZHTYPE##';
    	var gzhType = param.getGZHTYPE();
    	var entrance = Util.Browser.getEntrance();
    	var registred = false;
    	var req = new Wx.Request();
    	/*--------------------------------------------------------------------------*/
    	// --- TEST CASE ---
    	/*--------------------------------------------------------------------------*/
    	//DLR
    	/*
    	gzhType = 'DLR';
   		
   		if(1){
   			//入口A
	   		entrance = '2-4';
	   		storage.set("carTypeGuid", "da07704b-d5e5-4fb2-a14d-7573ae244e19");
	   		storage.set("dealerGuid", "4");
	     	storage.set("cityCode", "22");
	     	storage.set("provinceCode", "1");
   		}else{
   			//入口B
   			entrance = '';
	     	storage.set("dealerGuid", "4");
	     	storage.set("cityCode", "22");
	     	storage.set("provinceCode", "1");
   		}
   		*/
    	/*--------------------------------------------------------------------------*/
    	$(function(){
    		if(gzhType=='DLR'){
    			//DLR
    			// form.hideControls(['dealerProvinceBox', 'dealerCityBox', 'dealerNameBox']);
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
    		}else
    		if(gzhType=='DIST'){
    			//DIST
    			req.postData("/dealer/getAllDealerAddressProvinces", {}, function(data){
         			//var dealerGuid = storage.get('dealerGuid');
					//var provinceCode = storage.get('provinceCode');
					//var cityCode = storage.get('cityCode');
					
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
    		if(entrance=='2-4'||entrance=='2-5'||entrance=='4-2'||entrance=='8-2-a'){
    			//a type
    			//get all car type
    			req.postData('/car/getAllCarType?gzhHash='+gzhHash, {}, function(ret){
    				form.setOptions("carTypeGuid", ret.result, "carTypeGuid", "carTypeTitle");
    				
    				//var carTypeGuid = storage.get('carTypeGuid');
    				var carTypeGuid = urlparam.get('carTypeGuid');
        			form.setValues({
        				"carTypeGuid": carTypeGuid
        			});
    			});
    			
    		}else{
    			//b type
    			req.postData('/car/getAllCarType?gzhHash='+gzhHash, {}, function(ret){
    				form.setOptions("carTypeGuid", ret.result, "carTypeGuid", "carTypeTitle");
    			});
    		}
    		
    		//check user
    		var user = new Wx.User();
    		user.getUserInfo(function(info){
				if(info == null || info.memberLevel == null || info.memberLevel==0){
					
				}else{
					//regiestered
					registred = true;
					form.setValues({
						'userName': info.userName,
						'userSex': info.userSex,
						'userTel': info.userTel
					});
					form.disableInputs([ "userName","userTel", "yuyueCartypeGuid", "userSex"]);
					form.hideControls(['validatorBox', 'ruleBox']);
				}
			});
    		
    	});
    	
    	
    	/*--------------------------------------------------------------------------*/
    	
    	//预约试驾
    	window.yuyue_shijia_post = function(){
    		var formdata = form.getValues();
    		formdata['gzhHash'] = gzhHash;
    		formdata['openid'] = openid;
    		
    		req.postData("/yuyue/addYuyueShijia", formdata, function(ret){
    			if(ret.message=='failure'){
    				alert(ret.result);
    			}else
    			if( ret.message=='success'){
    				Util.Browser.jump(urls[0].src, '7-1-a');
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
    			if(formdata.carTypeGuid == ''){
    				alert('车型为必填项.');
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
    				yuyue_shijia_post();
    			}else{
    				//unregistered
    				//post reg 
    				if(formdata.agree != true){
        				alert('请认真阅读用户服务协议, 同意后方可进行下一步.');
        				return;
        			}
        			
    				formdata['gzhHash'] = gzhHash;
    				formdata['openId'] = openid;
    				form.disableInputs(['msgcode']);
    	    		req.postData("/user/addUserInfo", formdata, function(ret){
    	    			if(ret.message=='codefailure'){
    	    				alert('验证码出错');
    	    			}else{
    	    				yuyue_shijia_post();
    	    			}
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
    	var data = param.getDATA('##DATA##', [{"agreement":"广汽丰田关于顾客个人信息保护的基本方针 <br>【广汽丰田汽车有限公司】、【广汽丰田】经销商及【广汽丰田关联公司】，以下将【广汽丰田汽车有限公司】、【广汽丰田】经销商及【广汽丰田关联公司】统称为【广汽丰田】。【广汽丰田】认为严格遵守个人信息保护相关的中国法律法规，妥善处理顾客个人提供的姓名、地址、电话号码、邮箱地址等能够识别顾客个人及其家庭成员身份的信息（以下简称“个人信息”），是企业重要的社会责任。基于此，【广汽丰田】制定了如下的保护个人信息基本方针。<br><br>1.个人信息的取得<br>1)【广汽丰田】于以下情形取得个人信息：<br>①销售产品、提供服务时取得的个人信息；<br>② 为了提供问询对应等取得的个人信息（包括使用来电显示取得的联系方式）；<br>③ 实施各项调查（包括【广汽丰田】委托外部公司实施的）时取得的个人信息；<br>④ 【广汽丰田】取得的其他个人信息。<br>2) 【广汽丰田】将在取得顾客的同意后，取得个人信息。<br><br>2.个人信息的处理<br>1）关于个人信息的使用<br>【广汽丰田】根据前述第1.条规定取得的个人信息，将仅在【广汽丰田】内部根据需要进行共享，并且仅为以下目的或其他合法、正当的目的使用：<br>① 与客户进行的交易；<br>② 商品及服务的企划、开发、改善；<br>③ 发送与【广汽丰田】的产品、服务、宣传活动（包括但不限于汽车、保险等）相关的信息或通知；<br>  （但在未取得顾客同意的情况下，我们不会发送商业性目的的上述信息或通知）<br>④ 在产品企划、开发或提高服务质量及顾客满意度等方面，实施的各项调查；<br>⑤ 顾客问询、联系【广汽丰田】经销商及【广汽丰田顾客服务中心】时，进行迅速的对应；<br>⑥ 根据法律规定或政府机关、法院、调解机构、仲裁机构等的通知、指导等而采取的对应；<br>⑦ 其他取得个人信息时所明示的使用目的。<br><br>2）向第三方提供个人信息<br>【广汽丰田】根据前述第1.条规定取得的个人信息，在未取得顾客同意的情况下，不会向第三方提供或出售。但是，为了实现上述使用目的，在必要的范围内，会提供给业务受托方。于此情形下，【广汽丰田】会要求业务受托方妥当处理【广汽丰田】所提供的个人信息，并进行妥善管理。<br><br>3）妥善管理个人信息<br>为了对个人信息严格保密，防止不正当接触个人信息，防止个人信息丢失、损坏、被篡改、泄露等，【广汽丰田】采取了妥善的安全措施，并且将在因前述事由导致事故后采取救济措施。<br><br>3.问询等<br>关于个人信息的相关问询，请就近联系【广汽丰田】经销商。【广汽丰田】将严格遵守个人信息保护相关的中国法律法规，进行妥善处理。<br><br>4.守法与改善【广汽丰田】将严格遵守个人信息保护相关的中国法律法规，并为了妥善处理个人信息而进行持续性的改善，并会将改善内容随时体现在本基本方针中。"}]);
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
    	stat.report('7-1-a', "预约试驾", gzhHash, openid, {});
    </script>
    <script src="${ctx}/template/dist/js/js_general.js"></script>
    
    <div id="info_reg" style="display:none"> 
    	<div class="content" >
    		<form class='m-form'>
					<div class='m-form-item-header'>用户服务协议</div>
					<div id='agreementtxt'>
						
					</div>  
				<Button type="button" id="btn_known" class="btn_main">我知道了</Button>
			</form>
		</div>
	</div>
    <div style="display:none;" id="data">[{"agreement":"##agreement_0#"}]</div> 
    <input id='agreement_hidden' type="hidden" targetId="##agreement_0#" dataType="value" inputType="textarea" title="条款" value="'+data[0].name+'" >
    
  	<input type="hidden" targetId="yuyue_do_url" urlType="url" title="提交预约试驾跳转至" />
  	<div id="contentManager"><a style="display:none;" href="javascript:;" id="qushenghuo" title="${ctx}/admin/content/yuyue/index.jsp?type=shijia">预约管理</a></div>
  </body>
</html>

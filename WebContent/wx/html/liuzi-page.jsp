<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/wx.js"></script>
<%@ include file="/common/datepick.jsp"%>
<title>用户留资</title>
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
       <form action="" method='post' name='liuzi' class='m-form'>
         <div class='m-form-item-header'>个人联系信息</div>
         
         <div class="m-form-item"> 
			<div class="m-label request">姓&nbsp;&nbsp;&nbsp;名:</div>
			<div class="m-input-box">
				<input id='userName' type='text' maxlength='80' />
			</div>
		 </div>
		 
		 <div class="m-form-item">
			<div class="m-label request">性&nbsp;&nbsp;&nbsp;别:</div>
			<div class="m-input-box">
				<label class="radio">
					<input type="radio" name="sex" id="man" value="1" checked>
					男
				</label>
				<label class="radio">
					<input type="radio" name="sex" id="woman" value="2">
					女
				</label>
			</div>
		 </div>
		 
		 <div class="m-form-item"> 
			<div class="m-label request">电&nbsp;&nbsp;&nbsp;话:</div>
			<div class="m-input-box">
				<input id='userTel' type='text' maxlength='15' />
			</div>
		 </div>
		 
         <div id="divbuycarflag" class="m-form-item">
			<div class="m-label">是否购车:</div>
			<div class="m-input-box">
				<label class="radio">
					<input type="radio" name="buycarflag" id="isGou" value="1" checked>
					是
				</label>
				<label class="radio">
					<input type="radio" name="buycarflag" id="noGou" value="2">
					否
				</label>
				<!-- input id='buycarflag' type='text' /-->
				<!-- select id='isgouche' style='width:30%;display:inline;float:left;'>
					<option value='1' selected>是</option>
					<option value='0'>否</option>
				</select-->
			</div>
		  </div>
		  
		  <div id="divpurposecarmodel" class="m-form-item">
			<div class="m-label">意向车型:</div>
				<div class="m-input-box">
				<!-- <input id='purposecarmodel' type='text' maxlength='40' /> -->
				<select id='purposecarmodel' style='display:inline;float:left;'>
				</select>
				</div>
		  </div>
		  
		  <div id="divbuycarmodel" class="m-form-item">
			<div class="m-label">已购车型:</div>
				<div class="m-input-box">
				<!-- <input id='buycarmodel' type='text' maxlength='40' /> -->
				<select id='buycarmodel' style='display:inline;float:left;'>
				</select>
				</div>
		  </div>
		  
		  <div id="divbuydlrcode" class="m-form-item">
			<div class="m-label">购&nbsp;车&nbsp;店:</div>
			<div class="m-input-box">
				<!-- <input id='buydlrcode' type='text' maxlength='40' /> -->
				<select id='areaCode' style='width:35%;display:inline;float:left;'>
				</select>
				<select id='buydlrcode' style='width:60%;margin-left:5%;display:inline;float:left;'>
				</select>
			</div>
		  </div>
		  
		  <div class="m-form-item" style='height:15px;'> 
			<div class="m-label">
				<span class="red">*为必填项</span>
			</div>
			
		  </div>
		  
		  <div class="m-form-item">
			<div>
				<input id='ckboxprotect' type="checkbox" />
				<span>我已阅读并同意广汽丰田<a href="http://www.gac-toyota.com.cn/templets/tsai/tiaokuan.jpg">《保护消费者个人信息基本方针》</a></span>
			</div>
		  </div>
		  
		  <div class="m-form-item" style='text-align:center;'>
		  	<Button type='button' class='btn_main' id='reserve'style='width:30%;' >提交</Button>
		  </div>
			
       </form>
     </div>
   </div>
   
   <script type="text/javascript">
   		var testurls = [
   		    {src:"${ctx}/wx/html/liuzi-page.jsp", targetId: "liuzi_url"},
   		    {src:"${ctx}/wx/html/testSend.jsp", targetId: "testSend_url"}
   		];
   		
   		//--多次提交控制--
   		var onprocess="0";
   		//--禁止瞬间多次提交--
   		function cancelDbClick() {
   			if (onprocess=="0") {
   				onprocess="1";
   				return true;
   			}
   			return false;
   		}
   		
   		var form = new Util.Form("liuzi");
   		var param = new Wx.Param();
   		var openid = param.getOPENID();
   		var gzhHash = param.getGZHHASH('##GZHHASH##');
   		window.GZHTYPE = '##GZHTYPE##';
   		var urls = param.getURL('##URL##', testurls);
   		var gzhType = param.getGZHTYPE();
   		var req = new Wx.Request();
   		var isOkCheck = false;
   		
   		function GetRequest() {
   		   var url = location.search;//获取url中"?"符后的字串
   		   var theRequest = new Object();
   		   if (url.indexOf("?") != -1) {
   		      var str = url.substr(1);
   		      strs = str.split("&");
   		      for(var i = 0; i < strs.length; i ++) {
   		         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
   		      }
   		   }
   		   return theRequest;
   		}
   		
   		function liuzi_post(){
   			var valSex=$('input:radio[name="sex"]:checked').val();
   			var valBuycarflag=$('input:radio[name="buycarflag"]:checked').val();
   			var strPurpose=$('#purposecarmodel option:selected').val();
   			var strBuy=$('#buycarmodel option:selected').val();
   			var strdlrcode=$('#buydlrcode option:selected').val();
   			var formdata = form.getValues();       
   			var reqParam = new Object();

   			reqParam = GetRequest();
   			
   			if("undefined" == strPurpose){
				strPurpose = "";
			}
			if("undefined" == strBuy){
				strBuy = "";
			}
			if("undefined" == strdlrcode){
				strdlrcode = "";
			}
			//formdata['purposecarmodelcode']=strPurpose;
			formdata['purposecarmodel']=strPurpose;
			//formdata['buycarmodelcode']=strBuy;
			formdata['buycarmodel']=strBuy;
			//formdata['buydlrcodecode']=strdlrcode;
			formdata['buydlrcode']=strdlrcode;
    		formdata['gzhHash'] = reqParam['hashcode'];
    		formdata['openid'] = reqParam['wxopenid'];
    		formdata['sex'] = valSex;
    		formdata['buycarflag'] = valBuycarflag;
    		//调后台方法，调用CR接口
    		req.postData("artificial/getPersonalCheckHas",formdata,function(res){
    			if(res.success){
    				req.postData("artificial/getPersonalInfo",formdata,function(res0){
    	    			if(res0.success){
    	    				req.postData("artificial/sendPersonalHelp",formdata,function(res1){
    	    					//Util.Browser.jump(urls[0].src, '18-1');/gtmc_wx/
    	    				});
    	    				WeixinJSBridge.call('closeWindow');
    	    			}else{
    	    				req.postData("artificial/getPersonalSendMsg",formdata,function(res2){
    	    					//Util.Browser.jump(urls[1].src, '18-2');/gtmc_wx/
    	    				});
    	    				WeixinJSBridge.call('closeWindow');
    	    			}
    	    		});
    			}else{
    				alert("您已留过联系信息!");
    				WeixinJSBridge.call('closeWindow');
    				return;
    			}
    		});
   		}
   		
   		function len(s) {
   		 	var l = 0;
   		 	var a = s.split("");
   		 	for (var i=0;i<a.length;i++) {
   		  		if (a[i].charCodeAt(0)<299) {
   		   			l++;
   		  		} else {
   		   			l+=2;
   		  		}
   		 	}
   		 	return l;
   		}
   		
   		$(function(){
   			var formdata = form.getValues();
   			$('#reserve').bind('click',function(){
   				formdata = form.getValues();
   				var ckbox = $("#ckboxprotect").attr("checked");
   				var reg = new RegExp("^[0-9]*$");
   				if(formdata.userName == ''){
    				alert('姓名为必填项.');
    				return;
    			}
   				if(len(formdata.userName) > 70){
    				alert('姓名最多为35个汉字.');
    				return;
    			}
   				if(formdata.userTel == ''){
    				alert('手机号为必填项.');
    				return;
    			}else if(formdata.userTel != ''){
    				if(!reg.test(formdata.userTel)){
    					alert('请输入数字.');
    					return;
    				}
    				//if((formdata.userTel).length < 11){
    				if((formdata.userTel).length != 11){
    					alert('手机号必须为11位有效数字.');
    					return;
    				}
    			}
   				if(ckbox != "checked"){
   					alert('请同意条款！');
   					return;
   				}
   				if(!cancelDbClick()){
   					return;
   				}
   				
   				liuzi_post();
   			});
   			
   			var valflag=$('input:radio[name="buycarflag"]:checked').val();
   			formdata['buycarflag'] = valflag;
   			if(valflag == "1"){
   				$('#divpurposecarmodel').attr('style','display:none');
   				$('#divbuycarmodel,#divbuydlrcode').attr('style','display:visible');
   				$('#purposecarmodel option:selected').removeAttr("selected");
   			}else{
   				$('#divbuycarmodel,#divbuydlrcode').attr('style','display:none');
   				$('#divpurposecarmodel').attr('style','display:visible');
   				$('#buycarmodel option:selected').removeAttr("selected");
   				$('#buydlrcode option:selected').removeAttr("selected");
   			}
   			getCarModel(formdata);
   			
   			$('#divbuycarflag').find('input').bind('click',function(){
   				var valflag=$('input:radio[name="buycarflag"]:checked').val();
   				formdata['buycarflag'] = valflag;
   	   			if(valflag == "1"){
   	   				$('#divpurposecarmodel').attr('style','display:none');
   	   				$('#divbuycarmodel,#divbuydlrcode').attr('style','display:visible');
   	   				$('#purposecarmodel option:selected').removeAttr("selected");
   	   				getCarModel(formdata);
   	   			}else{
   	   				$('#divbuycarmodel,#divbuydlrcode').attr('style','display:none');
   	   				$('#divpurposecarmodel').attr('style','display:visible');
   	   				$('#buycarmodel option:selected').removeAttr("selected");
   	   				$('#buydlrcode option:selected').removeAttr("selected");
   	   				$('#areaCode').get(0).options[0].selected = true;
   	   				$('#buydlrcode').empty();
   	   				getCarModel(formdata);
   	   			}
   			});
   			
   			//$('#ckboxprotect').attr("checked","checked");
   			$('#ckboxprotect').bind('click',function(){
   				var iscked = $(this).attr("checked");
   				if(iscked == "checked"){
   					$(this).removeAttr("checked");
   				}else{
   					$(this).attr("checked","checked");
   				}
   			});
   			
   			$.get("${ctx}/artificial/getPersonalArea",function(rest){
   				//if(rest.success){
   					$('#areaCode').empty();
   					$("#areaCode").append("<option value=''><b>地区</b></option>");
   					var dataMap = rest.rows;
   					for (var i = 0; i < dataMap.length; i++) {
   						$('#areaCode').append("<option value='"+ dataMap[i].code +"'>"+ dataMap[i].name +"</option>");
   					}
   				//}
   			});
   			
   			$('#areaCode').change(function(){
   				var strArCode = $(this).val();
   				if(strArCode != ''){
   					$('#buydlrcode').empty();
   					$("#buydlrcode").append("<option value=''><b>==请选择车店==</b></option>");
   					formdata['areaCode'] = strArCode;
   					$.get("${ctx}/artificial/getPersonalCarOrg",formdata,function(restShop){
   						//if(restShop.success){
   							var data = restShop.rows;
   							for(var j=0;j<data.length;j++){
   								$('#buydlrcode').append("<option value='"+ data[j].code +"'>"+ data[j].name +"</option>");
   							}
   						//}
   					});
   				}else{
   					$('#buydlrcode').empty();
   				};
   			});
   		});
   		
   		function getCarModel(formdata){
   			$.get("${ctx}/artificial/getPersonalCarModel",formdata,function(restCar){
   				//if(restCar.success){
   					$('#purposecarmodel').empty();
   					$('#buycarmodel').empty();
   					$('#purposecarmodel').append("<option value=''><b>==请选择车型==</b></option>");
   					$('#buycarmodel').append("<option value=''><b>==请选择车型==</b></option>");
   					var valflag = formdata['buycarflag'];
   					var data = restCar.rows;
   					if(valflag == "1"){
   						for (var i = 0; i < data.length; i++) {
   	   						$('#buycarmodel').append("<option value='"+ data[i].code +"'>"+ data[i].name +"</option>");
   	   					}
   					}else{
   						for (var i = 0; i < data.length; i++) {
   	   						$('#purposecarmodel').append("<option value='"+ data[i].code +"'>"+ data[i].name +"</option>");
   	   					}
   					}
   					
   				//}
   			});
   		}
   </script>
</body>
</html>
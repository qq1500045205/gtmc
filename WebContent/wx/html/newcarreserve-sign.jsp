<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/wx.js"></script>
<%@ include file="/common/datepick.jsp"%>
<title>立即预定</title>
<style>
	.txtinfo{
		font-size: 10px;
	}
</style>
</head>
<body>
	<div class="page_main">
     <div class="content">
       <form action="" method='post' name='newcar' class='m-form'>
         <div class='m-form-item-header'>立即预定</div>
         
         <div class="m-form-item"> 
			<div class="m-label request">姓&nbsp;&nbsp;&nbsp;&nbsp;名:</div>
			<div class="m-input-box">
				<input id='userName' type='text' maxlength='80' />
			</div>
		 </div>
		 
		 <div class="m-form-item"> 
			<div class="m-label request">手&nbsp;机&nbsp;号:</div>
			<div class="m-input-box">
				<input id='userMobile' type='text' maxlength='15' />
			</div>
		 </div>
		 	  
		  <!-- <div class="m-form-item">
			<div class="m-label">配置:</div>
				<div class="m-input-box">
				<select id='carConf' style='display:inline;float:left;'>
					<option value='1' selected>测试1</option>
					<option value='0'>测试2</option>
				</select>
				</div>
		  </div> -->
		  
		  <div class="m-form-item">
			<div class="m-label request">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份:</div>
				<div class="m-input-box">
				<select id='userProvince' style='display:inline;float:left;'>
				</select>
				</div>
		  </div>
		  
		  <div class="m-form-item">
			<div class="m-label request">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:</div>
			<div class="m-input-box">
				<select id='userCity' style='display:inline;float:left;'>
				</select>
			</div>
		  </div>
		  
		  <div class="m-form-item">
			<div class="m-label request">销&nbsp;售&nbsp;店:</div>
			<div class="m-input-box">
				<select id='dealerCode' style='display:inline;float:left;'>
				</select>
			</div>
		  </div>
		  
		  <div class="m-form-item">
					<div class="m-label">&nbsp;&nbsp;地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</div>
			<div id="dlrAddress" class="m-input-box">
				<label id='dealerAddress'></label>
			</div>
		  </div>
		     <div class="m-form-item">
			<div class="m-label request">购买车型:</div>
			<div class="m-input-box">
				<select id='carModel' style='width:30%;display:inline;float:left;'>
					<option value='004014' selected>雷凌</option>
				</select>
			</div>
		  </div>
		  <div> 
			<div>
				<span class="txtinfo">温馨提示:为能方便准确对注册用户信息,</span><span class="txtinfo" style="color:red;">建议在注册时填写真实姓名、电话。</span>
			</div>
			<div>
				<input id='ckboxprotect' type="checkbox" checked="checked"/>
				<span class="txtinfo">我已阅读并同意广汽丰田<a href="http://www.gac-toyota.com.cn/templets/tsai/tiaokuan.jpg">《保护消费者个人信息基本方针》</a></span>
			</div>
		  </div>
		  
		  <div id="btnDiv" class="m-form-item" style='text-align:center;'>
		  	<Button type='button' class='btn_main' id='reserve' style='width:30%;' >提交</Button>
		  	<Button type='reset' class='btn_main' id='btnreset' style='width:30%;'>重新填写</Button>
		  </div>
		   <div id="returnDiv" class="m-form-item" style='text-align:center;display:none'>
		  	<Button type='button' class='btn_main' id='returnBtn' >返回</Button>
		  </div>
			
       </form>
     </div>
   </div>
   			<input type="hidden" targetId="newcarreserve-feedback" urlType="url" title="数据成功时 跳转至" />
   			<input type="hidden" targetId="newcarreserve-list" urlType="url" title="返回 跳转至" />
   			
   <script type="text/javascript">
   var form = new Util.Form("newcar");
   var param = new Wx.Param();
   var openid = param.getOPENID();
	if(openid==null||openid==''){
		openid=Util.Cookie.getCookie('tmpopenid');
	}
  // var gzhHash = param.getGZHHASH('##GZHHASH##');
   var gzhHash = '';
	$.ajax({
		type : 'POST',
		url : "${ctx}/newcarreserve/getGZHHashNewCarReserve",
		async : false,
		data : {
			openid : openid		
		},
		dataType : "json",
		success : function(ret) {
			gzhHash=ret.result.GZHHash;
		}
	});	
	if(gzhHash==''){//如果没找到，则默认用丰田的HashCode
		gzhHash='-2042484612';
	}
   var gzhtype = "";
   if(gzhHash=='-2042484612'){
	   gzhtype="DIST";
   }else{
	   gzhtype="DLR";
   }
//    alert("gzhHash:"+gzhHash);

//    alert("gzhtype:"+gzhtype);
   var objAddress;
   //var gzhtype = "DLR";
   var req = new Wx.Request();
	var urls = param.getURL('##URL##',[{src: "newcarreserve-feedback.jsp"},{src: "newcarreserve-list.jsp"}]);
	var utilParam = new Util.Param();

	var id=utilParam.get('id');	
	if(id>0){
		$(function(){
    		req.postData('/newcarreserve/getOneNewCarReserve', {id: id}, function(ret){
    			var infoMap=ret.result;
    			$("#userName").attr("disabled","disabled");
    			$("#userMobile").attr("disabled","disabled");
    			$("#carModel").attr("disabled","disabled");
    			$("#userProvince").attr("disabled","disabled");
    			$("#userCity").attr("disabled","disabled");
    			$("#dealerCode").attr("disabled","disabled");
    			$("#ckboxprotect").attr("disabled","disabled");
    			$("#btnDiv").css("display","none");
    			$("#returnDiv").css("display","block");


    			$("#userName").val(infoMap.userName);
    			$("#userMobile").val(infoMap.userMobile);
    			$("#userProvince").append("<option value='" + infoMap.userProvince + "'>" + infoMap.userProvince.replace("广汽丰田","") + "</option>");
				$("#userCity").append("<option value='" + infoMap.userCity + "'>" + infoMap.userCity.replace("广汽丰田","") + "</option>");
				$("#dealerCode").append("<option value='" + infoMap.dealerName + "'>" + infoMap.dealerName.replace("广汽丰田","") + "</option>");
				$("#dealerAddress").text(infoMap.dealerAddress);
				
				
    		});
    	});
	}else{
	
   $(function(){
	   $('#reserve').bind('click',function(){
		   var formdata = form.getValues();
		   var ckbox = $('#ckboxprotect').attr('checked');
		   var strPrVal=$('#userProvince option:selected').val();
		   var strCtVal=$('#userCity option:selected').val();
		   var strdlrVal=$('#dealerCode option:selected').val();
		   var strcarMdl=$('#carModel option:selected').val();

		   var reg = new RegExp("^[0-9]*$");
		   if(formdata.userName == ''){
			   alert('姓名为必填项.');
			   return;
		   }
		   if(formdata.userMobile == ''){
			   alert('手机号为必填项.');
			   return;
		   }else if(formdata.userTel != ''){
			   if(!reg.test(formdata.userMobile)){
				   alert('请输入数字.');
				   return;
			   }
			   if((formdata.userMobile).length < 11){
				   alert('手机号必须大于等于11位.');
				   return;
			   }
		   }
		   if(strPrVal == '' || strPrVal == undefined){
			   alert('省份不能为空.');
			   return;
		   }
		   if(strCtVal == '' || strCtVal == undefined){
			   alert('城市不能为空.');
			   return;
		   }
		   if(strdlrVal == '' || strdlrVal == undefined){
			   alert('经销店不能为空.');
			   return;
		   }
		   if(strcarMdl == '' || strcarMdl == undefined){
			   alert('购买车型不能为空.');
			   return;
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
	   $('#ckboxprotect').bind('click',function(){
			var iscked = $(this).attr("checked");
			if(iscked == "checked"){
				$(this).removeAttr("checked");
			}else{
				$(this).attr("checked","checked");
			}
		});
   });
   
   function liuzi_post(){
	   var strPr=$('#userProvince option:selected').text();
	   var strCt=$('#userCity option:selected').text();
	   var strdlr=$('#dealerCode option:selected').text();
	   var strPrCode=$('#userProvince option:selected').val();
	   var strCtCode=$('#userCity option:selected').val();
	   var strAddress=$('#dealerAddress').text();
	   var modleName=$('#carModel option:selected').text();
	   var modleCode=$('#carModel option:selected').val();
	   var formdata = form.getValues();
			
		formdata['gzhHash'] = gzhHash;
		formdata['openid'] = openid;
		formdata['userProvince'] = strPr;
		formdata['userCity'] = strCt;
		formdata['userProvinceCode'] = strPrCode;
		formdata['userCityCode'] = strCtCode;
		formdata['dealerName'] = strdlr;
		formdata['dealerAddress'] = strAddress;
		formdata['carModel'] = modleName;
		formdata['modleCode'] = modleCode;
		//alert(formdata['dealerAddress']);
		//调后台方法
		req.postData("/newcarreserve/saveNewCarReserve",formdata,function(res){
			if(res.success){
				//alert("添加成功！");
				Util.Browser.jump(urls[0].src, "22-1");
			}
		});
	}
   
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
   
    $(function(){
    	if(gzhtype!="DIST"){
        	$.ajax({
        		type : 'POST',
        		url : "${ctx}/newcarreserve/getDlrNameNewCarReserve",
        		async : false,
        		data : {
        			gzhHash : gzhHash
        		},
        		dataType : "json",
        		success : function(data) {
        			if (data.success) {
        				dataMap = data.result; 
        				$("#userProvince").empty();
        				$("#userCity").empty();
        				$("#dealerCode").empty();
        				$("#userProvince").attr("disabled","disabled");
            			$("#userCity").attr("disabled","disabled");
            			$("#dealerCode").attr("disabled","disabled");
        				$("#userProvince").append("<option value='" + dataMap.provinceCode + "'>" + dataMap.provinceName.replace("广汽丰田","") + "</option>");
        				$("#userCity").append("<option value='" + dataMap.cityCode + "'>" + dataMap.cityName.replace("广汽丰田","") + "</option>");
        				$("#dealerCode").append("<option value='" + dataMap.projectGuid + "'>" + dataMap.projectName.replace("广汽丰田","") + "</option>");
        				$("#dealerAddress").text(dataMap.address);
        			}
        		}
        	});
        }else{
        	getProCityDealerList();
        }
    });
   
    /*获取省市经销商列表*/
	function getProCityDealerList() {
	    $.get('${ctx}/dealer/getAllDealerAddressProvinces',
	    function(res) {
	        var data = res.rows;
	        $("#userProvince").empty();
	        $("#userProvince").append("<option value=''><b>==请选择省份==</b></option>");
	        $("#userCity").append("<option value=''>==请选择城市==</option>");
	        $("#dealerCode").append("<option value=''>==请选择经销商==</option>");
	        for (var i = 0; i < data.length; i++) {
	            /* if (data[i].provCode == "${param.provCode}") {
	                $("#userProvince").append("<option value='" + data[i].provinceCode + "' selected='selected'>" + data[i].provinceName.replace("广汽丰田","") + "</option>");
	                $("#userProvince").change();
	            } else { */
	                $("#userProvince").append("<option value='" + data[i].provinceCode + "'>" + data[i].provinceName + "</option>");
	            //}
	        }
	    });

	    $("#userProvince").change(function() {
	        if ($(this).val() == '') {
	            $("#userCity").empty();
	            $("#dealerCode").empty();
	            $("#userCity").append("<option value=''>==请选择城市==</option>");
	            $("#dealerCode").append("<option value=''>==请选择经销商==</option>");
	            return;
	        }
	        $.get('${ctx}/dealer/getAllDealerAddressCitiesByProvince?province_code=' + $(this).val(),
	        function(res) {
	            var data = res.rows;
	            $("#userCity").empty();
	            $("#dealerCode").empty();
	            $("#userCity").append("<option value=''>==请选择城市==</option>");
	            $("#dealerCode").append("<option value=''>==请选择经销商==</option>");
	            for (var i = 0; i < data.length; i++) {
	                /* if (data[i].cityCode == "${param.cityCode}") {
	                    $("#userCity").append("<option value='" + data[i].cityCode + "' selected='selected'>" + data[i].cityName + "</option>");
	                } else { */
	                    $("#userCity").append("<option value='" + data[i].cityCode + "'>" + data[i].cityName + "</option>");
	                //};
	            };
	        });
	    });

	    $("#userCity").change(function() {
	        if ($(this).val() == '') {
	            $("#dealerCode").empty();
	            $("#dealerCode").append("<option value=''>==请选择经销商==</option>");
	            return;
	        }
	        $.get('${ctx}/dealer/getAllDealersByCity?cityCode=' + $(this).val(),
	        function(res) {
	            var data = res.rows;
	            objAddress = data;
	            $("#dealerCode").empty();
	            $("#dealerCode").append("<option value=''>==请选择经销商==</option>");
	            for (var i = 0; i < data.length; i++) {
	                //if (data[i].projectGuid == "${param.projectGuid}") {
	                //    $("#dealerCode").append("<option value='" + data[i].projectGuid + "' selected='selected'>" + data[i].projectName.replace("广汽丰田","") + "<inupt type='hidden' value='" + data[i].address + "'/>" + "</option>");
	                //} else {
	                    $("#dealerCode").append("<option value='" + data[i].projectGuid + "'>" + data[i].projectName.replace("广汽丰田","") + "</option>");
	                //}
	                //$("#dlrAddress").append("<span sytle='display:none' id='" + data[i].projectGuid + "' + value='" + data[i].address + "'/>");
	            }
	        });
	    });
	    
	    $("#dealerCode").change(function() {
	    	var strdlrCode = $(this).val();
	    	if(objAddress!=null){
	    		for(var i = 0;i<objAddress.length;i++){
	    			if(strdlrCode==objAddress[i].projectGuid){
	    				$('#dealerAddress').text(objAddress[i].address);
	    				break;
	    			}
	    		}
	    	}else{
	    		$('#dealerAddress').text('');
	    	}
	    	//$("#dlrAddress").remove("input:hidden");
	    	//$('#dealerAddress').val('测试');
	    });
      };
	}
	   $('#returnBtn').bind('click',function(){
			Util.Browser.jump(urls[1].src, "22-2");

		});
   </script>
</body>
</html>
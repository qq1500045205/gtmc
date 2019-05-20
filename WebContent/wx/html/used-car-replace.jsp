<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <%@ include file="/common/pre_general.jsp"%>
	<title>车价评估结果</title>
	<script src="${ctx}/template/dist/js/js_general.js"></script>
	
<style>  
	.list{ 
		padding:10px; 
	}
	#oldCarPrice{
		font-size:20px;
		color:#e60024; 
		font-family:"微软雅黑";
		text-align:center;
	} 
	.subtitle{
		color:#999;
		font-size:11px; 
		line-height:18px;
	} 
</style>


</head>
<body>  
<div class="content">
		<div class="list1">
			<div  style='background:rgb(233,243,250);margin-top:10px;padding:5px;'>您的爱车估算价值为：</div>
			<h2 id="oldCarPrice">5.10万元</h2>
			<div class="subtitle">该价格仅供参考，具体请亲临广汽丰田置换认定销售店，咨询专业评估师</div>
		</div>
		<form class='m-form' style="display:none;">
			<div style='background:rgb(233,243,250);margin-top:20px;margin-bottom:20px;padding:5px;'>选择您想置换的广汽丰田车系：
			</div>
			<div class="m-form-item"> 
				 <div class="m-label" >意向车型</div>
				 <div class="m-input-box">
				 	<select name="inputCarType" id="inputCarType" > </select>
				 </div> 
			</div>
			<div class="m-form-item"> 
				<div class="m-label" for="inputCarModel">型号</div>
				<div class="m-input-box">
					<select name="inputCarModel" id="inputCarModel" > </select>
				</div> 
			</div>
			
		</form>
		<div style="margin-top:20px;"> 
				<button type="button" class="btn_main" id="submit">金融方案</button>
			</div>
	</div> 
	<!-- /container -->
	<input type="hidden" targetId="nominate1" urlType="url" title="联系销售店 跳转至" />
	<input type="hidden" targetId="nominate2" urlType="url" title="询价 跳转至" />
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<script type="text/javascript">
		var wx = new Wx.Param();
		var url = wx.getURL('##URL##',[{src: "query-list-page.jsp"},{src: "query-detail-page.jsp"}]);
		var gzhType = wx.getGZHTYPE();
		var gzhHash = wx.getGZHHASH("##GZHHASH##");
		var carType, carModel;
		var oldCarPay = "${param.oldCarPay}";

		if (gzhType == "DLR") {
			$("#submit").text("询价");
		} else {
			$("#submit").text("联系销售店");
		}
		console.log(gzhType);
		
		$("#oldCarPrice").text(oldCarPay+" 万元");
		$.ajax({
			type : 'POST',
			url : "${ctx}/car/getAllCarType",
			data : {gzhHash: gzhHash},
			dataType : "json",
			success : function(res) {
				carType = res.result;
				console.log("carType：",carType);
				renderCarType();
			}
		});
		
		function renderCarType() {
			var str = "";
			str += "<option value=''></option>";
			for (var i=0; i<carType.length; i++) {
				str += "<option value='"+carType[i].carTypeGuid+"'>"+carType[i].carTypeTitle+"</option>";
			}
			console.log(str);
			$("#inputCarType").html(str);
			$("#inputCarType").change(function(){
				$.ajax({
					type : 'POST',
					url : "${ctx}/car/getCarModels",
					data : {carTypeGuid: $("#inputCarType").val()},
					dataType : "json",
					success : function(res) {
						carModel = res.result;
						console.log("carModel:", carModel);
						renderCarModel();
					}
				});
			});
			
			$.ajax({
				type : 'POST',
				url : "${ctx}/car/getCarModels",
				data : {carTypeGuid: $("#inputCarType").val()},
				dataType : "json",
				success : function(res) {
					carModel = res.result;
					console.log("carModel:", carModel);
					renderCarModel();
				}
			});
		}
		
		function renderCarModel() {
			var str = "";
			str += "<option value=''></option>";
			for (var i=0; i<carModel.length; i++) {
				str += "<option value='"+carModel[i].carGuid+"'>"+carModel[i].carName+"</option>";
			}
			$("#inputCarModel").html(str);
		}
		
		$("#submit").click(function(){
			var carTypeGuid = $("#inputCarType").val();
			var carModelGuid = $("#inputCarModel").val();
			
			var param = "oldCarPay=" + oldCarPay + 
						"&carTypeGuid=" + carTypeGuid + 
						"&carModelGuid=" + carModelGuid;
			if (gzhType == "DLR") {
				Util.Browser.jump(url[1].src + "?" + param, "10-2");
			} else {
				Util.Browser.jump(url[0].src + "?" + param, "10-2");
			}
		});

	</script>
	
	<script type="text/javascript">
		var wx = new Wx.Param();
		var gzhHash = wx.getGZHHASH("##GZHHASH##");
		var openid = wx.getOPENID();
		var stat = new Wx.Stat();
		stat.report('10-2', "二手车置换结果", gzhHash, openid, {});
		
	</script>
</body>
</html>

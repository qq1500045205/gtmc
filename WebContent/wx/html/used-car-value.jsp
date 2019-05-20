<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head> 
<%@ include file="/common/pre_general.jsp"%>  
<title>车价评估</title>
<style>
.tab{ 
	border-bottom:1px solid #ddd;
	box-shadow:0 1px 0 rgba(255,255,255,.9);
} 
</style>
 
</head>
<body> 
	<div class="content"> 
		<div class="tab">
			<ul>
				<li id="tab1" onclick="activeTab('tab1')" class="on">传统估值</li>
				<li  id="tab2" onclick="activeTab('tab2')" class=" ">VIN估值</li>
			</ul> 
		</div> 
		<form class="m-form" id="tab1-form"> 
			<div class="m-form-item">
					<label class="radio">  
					  <input type="radio" name="country" id="china" value="0" checked>  
					  	国内 
					</label>  
					<label class="radio">  
					  <input type="radio" name="country" id="foreign" value="1">  
					  	进口 
					</label>
				</div> 
			<div class="m-form-item"> 
				<div class="m-label">品牌</div>
				<div class="m-input-box">
					<select name="inputCarMake" id="inputCarMake" ></select>
				</div>
			</div>
			
			<div class="m-form-item"> 
				<div class="m-label">车型</div>
				<div class="m-input-box">
					<select name="inputCarModel" id="inputCarModel" ></select>
				</div> 
			</div>
			<div class="m-form-item"> 
				<div class="m-label">年份</div>
				<div class="m-input-box">
					<select name="inputYear" id="inputYear" ></select>
				</div>
			</div>
			<div class="m-form-item"> 
				<div class="m-label">型号</div>
				<div class="m-input-box">
					<select name="inputCarStyle" id="inputCarStyle" ></select>
				</div>
			</div>
			<div class="m-form-item" id="carCityContainer"> 
				<div class="m-label">城市</div> 
				<div class="m-input-box">
					<select name="inputCarCity" id="inputCarCity" ></select>
				</div>
			</div>
			<button type="button" class="btn_main" id="submit" disabled="disabled">开始评估</button> 
		</form>
		
		<form class="form content" id="tab2-form" style="display:none;"> 
			<div class="m-form-item"> 
				<div class="m-label">VIN</div>
				<div class="m-input-box">
					<input name="inputVin" id="inputVin" type="text" />
				</div>
				<div id="inputVinError" style="color:red"></div>
			</div>
			<div class="m-form-item" id="carStyleContainer2"> 
				<div class="m-label">型号</div> 
				<div class="m-input-box">
					<select name="inputCarStyle2" id="inputCarStyle2" ></select>
				</div>
			</div>
			<div class="m-form-item" id="carCityContainer2"> 
				<div class="m-label">城市</div> 
				<div class="m-input-box">
					<select name="inputCarCity2" id="inputCarCity2" ></select>
				</div>
			</div>
			 
			<button type="button" class="btn_main" id="submit2" disabled="disabled">开始评估</button> 
		</form>
	</div> 

<!-- 

			<div class="info_title">车价评估</div>
			<div>
				<div id="tab1" onclick="activeTab('tab1')" class="little_title title_active">传统估值法</div>
				<div id="tab2" onclick="activeTab('tab2')" class="little_title">vin估值法</div>
			</div>
			<form id="tab1-form" class="form">
				<div class="control-group">
					<label class="radio">  
					  <input type="radio" name="country" id="china" value="0" checked>  
					  	国内 
					</label>  
					<label class="radio">  
					  <input type="radio" name="country" id="foreign" value="1">  
					  	进口 
					</label>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputCarMake">厂商</label>
					<div class="controls">
						<select name="inputCarMake" id="inputCarMake" >
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputCarModel">品牌</label>
					<div class="controls">
						<select name="inputCarModel" id="inputCarModel" >
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputYear">年份</label>
					<div class="controls">
						<select name="inputYear" id="inputYear" >
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputCarStyle">车型</label>
					<div class="controls">
						<select name="inputCarStyle" id="inputCarStyle" >
						</select>
					</div>
				</div>
				<div class="control-group" id="carCityContainer">
					<label class="control-label" for="inputCarCity">城市</label>
					<div class="controls">
						<select name="inputCarCity" id="inputCarCity" >
						</select>
					</div>
				</div>
				<div class="control-group">
					<div class="btn-controls">
						 <button type="button" class="btn btn-primary" id="submit">开始评估</button>
					</div>
				</div>
			</form>
			
			<form id="tab2-form" class="form" style="display:none;">
				<div class="control-group">
					<label class="control-label" for="inputVin">Vin</label>
					<div class="controls">
						<input name="inputVin" id="inputVin" type="text"/>
						<span id="inputVinError" style="color:red"></span>
					</div>
				</div>
				<div class="control-group" id="carStyleContainer2">
					<label class="control-label" for="inputCarStyle2">车型</label>
					<div class="controls">
						<select name="inputCarStyle2" id="inputCarStyle2" >
						</select>
					</div>
				</div>
				<div class="control-group" id="carCityContainer2">
					<label class="control-label" for="inputCarCity2">城市</label>
					<div class="controls">
						<select name="inputCarCity2" id="inputCarCity2" >
						</select>
					</div>
				</div>
				<div class="control-group">
					<div class="btn-controls">
						 <button type="button" class="btn btn-primary" id="submit2" disabled="disabled">开始评估</button>
					</div>
				</div>
			</form>

 -->

 
	<!-- /container -->
	<input type="hidden" targetId="nominate" urlType="url" title="开始评估 跳转至" />
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<script type="text/javascript">
		var wx = new Wx.Param();
		var url = wx.getURL('##URL##',[{src: "used-car-replace.jsp"}]);
		var openid = wx.getOPENID();
		var carInfo;
		
		//$("#multi-car").modal();
		
		function activeTab(target) {
			console.log(target);
			if (target=="tab1") {
				if (!$("#tab1").hasClass("on") ) {
					$("#tab1").addClass("on");
					$("#tab2").removeClass("on"); 
					$("#tab1-form").show();
					$("#tab2-form").hide();
				}
			} else {
				if (!$("#tab2").hasClass("on") ) {
					$("#tab2").addClass("on");
					$("#tab1").removeClass("on"); 
					$("#tab2-form").show();
					$("#tab1-form").hide();
				}
			}
		}
		var carData = [];
		$(function(){
			$.ajax({
				type : 'GET',
				url : "${ctx}/user/getUserInfo",
				data : {
					openId: openid,
				},
				dataType : "json",
				success : function(res) {
					console.log(openid, res);
					
					if (res.success ) {
						console.log(res);
						carData = res.result;
						if (carData.carVin && carData.carVin.length>0) {
							activeTab("tab2");
							$("#inputVin").val(carData.carVin);
							vinNext();
						}
						
						/* if (res.result.length>1 ) {
							var carStr = "";
							for (var i=0; i<res.result.length; i++) {
								carStr = "<div onclick=\"getVin(" + i + ")\">" + res.result[i].carVin + "</div>";
							}
							$("#multi-car-container").html(carStr);
							$("#multi-car").show();
						} else {
							$("#inputVin").val(res.result[0].carVin);
							vinNext();
						} */
					}
				},
			});
		});
		  
		
		function getVin(index) {
			$("#multi-car").dialog("close");
			$("#inputVin").val(carData[index].carVin);
			vinNext();
		}
		
		$("#china").click(function(){
			renderCarMake(0);
		});
		$("#foreign").click(function(){
			renderCarMake(1);
		});
		renderCarMake(0);
		// 根据国家id获取 国内或进口厂商信息
		function renderCarMake(country) {
			var params = [{
				key: "country",
				value: country
			}]; 
			$.ajax({
				type : 'GET',
				url : "${ctx}/car/getCarInfo",
				data : {
					key: "CarMake",
					params: JSON.stringify(params)
				},
				dataType : "json",
				success : function(res) {
					if (res.message=="success") {
						renderCarMakeOption(res.result);
					}
				},
			});
		}
		// 渲染厂商
		function renderCarMakeOption(carMakerStr) {
			var carMakers = JSON.parse(carMakerStr);
			console.log("厂商：",carMakers);
			var str = "";
			for (var i=0; i<carMakers.length; i++) {
				if (carMakers[i].Id==65) { 
					str += "<option value='"+carMakers[i].Id+"' selected>"+carMakers[i].IAutosName+"</option>";
					
				} else { 
					str += "<option value='"+carMakers[i].Id+"'>"+carMakers[i].IAutosName+"</option>";	
				}				
			} 
			$("#inputCarMake").html(str);  
			$("#inputCarMake").change(function(){
				renderCarModel();
			});
			renderCarModel();
		}
		// 获取品牌信息
		function renderCarModel() {
			var makeid = $("#inputCarMake").val();
			var params = [{
				key: "makeid",
				value: makeid
			}];
			$.ajax({
				type : 'GET',
				url : "${ctx}/car/getCarInfo",
				data : {
					key: "CarModel",
					params: JSON.stringify(params)
				},
				dataType : "json",
				success : function(res) {
					if (res.message=="success") {
						renderCarModelOption(res.result);
					}
				},
			});
		}
		// 渲染品牌信息
		function renderCarModelOption(carModelStr) {
			var carModels = JSON.parse(carModelStr);
			console.log(carModels);
			var str = "";
			str += "<option value=''></option>";
			for (var i=0; i<carModels.length; i++) {
				str += "<option value='"+carModels[i].ModelId+"'>"+carModels[i].Model+"</option>";
			} 
			$("#inputCarModel").html(str);
			$("#inputCarModel").change(function(){
				renderYear();
			});
			renderYear();
		}
		// 获取年份信息
		function renderYear() {
			var Nid = $("#inputCarModel").val();
			var params = [{
				key: "Nid",
				value: Nid
			}];
			$.ajax({
				type : 'GET',
				url : "${ctx}/car/getCarInfo",
				data : {
					key: "Year",
					params: JSON.stringify(params)
				},
				dataType : "json",
				success : function(res) {
					if (res.message=="success") {
						renderYearOption(res.result);
					}
				},
			});
		}
		// 渲染年份
		function renderYearOption(yearStr) {
			var years = JSON.parse(yearStr);
			console.log(years);
			var str = "";
			str += "<option value=''></option>";
			for (var i=0; i<years.length; i++) {
				str += "<option value='"+years[i].YearNum+"'>"+years[i].YearNum+"</option>";
			}
			$("#inputYear").html(str);
			$("#inputYear").change(function(){
				renderCarStyle();
			});
			renderCarStyle();
		}
		// 获取车型
		function renderCarStyle() {
			var Nid = $("#inputCarModel").val();
			var Ccsj = $("#inputYear").val();
			$.ajax({
				type : 'GET',
				url : "${ctx}/car/getCarAllInfosByNidAndCcsj",
				data : {
					Nid: Nid,
					Ccsj: Ccsj
				},
				dataType : "json",
				success : function(res) {
					if (res.message=="success") {
						console.log(JSON.parse(res.result));
						renderCarStyleOption(res.result);
					}
				},
			});
		}
		// 渲染车型
		function renderCarStyleOption(carInfoStr) {
			carInfo = JSON.parse(carInfoStr);
			console.log(carInfo);
			var str = "";
			str += "<option value=''></option>";
			for (var i=0; i<carInfo.length; i++) {
				str += "<option value='"+i+"'>"+carInfo[i].carStyles.fld_trim+"</option>";
			}
			$("#inputCarStyle").html(str);
			$("#inputCarStyle").change(function(){
				renderCarCityOption();
			});
			renderCarCityOption();
		}
		// 获取城市
		function renderCarCityOption() {
			var index = $("#inputCarStyle").val();
			console.log(index, carInfo[index]);
			var secondPriceList = carInfo[index].carSecondStylesPriceList;
			var str = "";
			if (secondPriceList.length==0) {
				$("#carCityContainer").hide();
				$("#submit").text("此车型暂无估值");
				$("#submit").attr("disabled", "disabled");
			} else {
				$("#carCityContainer").show();
				$("#submit").text("开始估值");
				$("#submit").removeAttr("disabled");
				str += "<option value=''></option>";
				for (var i=0; i<secondPriceList.length; i++) {
					str += "<option value='"+secondPriceList[i].fld_buyprice+"'>"+secondPriceList[i].fld_Area+"</option>";
				}
				$("#inputCarCity").html(str);
			}
		}
		
		$("#submit").click(function(){
			var price = $("#inputCarCity").val();
			var param = "?oldCarPay=" + price;
			Util.Browser.jump(url[0].src + param, "10-1-a");
		});
//------------------------------------------------------------------------vin码估值法
		$("#carStyleContainer2").hide();
		$("#carCityContainer2").hide();
		$("#inputVinError").text("请输入VIN码");
		$("#inputVin").change(function(){
			vinNext();
		});
		
		function vinNext() {
			var vin = $("#inputVin").val();
			if (vin.length==17) {
				getCarInfoByVin();
				$("#carStyleContainer2").show();
				$("#carCityContainer2").show();
				$("#inputVinError").text("");
			} else {
				$("#carStyleContainer2").hide();
				$("#carCityContainer2").hide();
				$("#inputVinError").text("请输入17位VIN码");
			}
		}
		// 渲染车型
		function getCarInfoByVin() {
			var Vin = $("#inputVin").val();
			$.ajax({
				type : 'GET',
				url : "${ctx}/car/getCarAllInfosByVin",
				data : {
					Vin: Vin
				},
				dataType : "json",
				success : function(res) {
					if (res.message=="success") {
						console.log(JSON.parse(res.result));
						renderCarStyleOption2(res.result);
					}
				},
			});
		}
		
		// 渲染车型
		function renderCarStyleOption2(carInfoStr) {
			carInfo = JSON.parse(carInfoStr);
			if (carInfo.length==0) {
				$("#carCityContainer2").hide();
				$("#carStyleContainer2").hide();
				$("#submit2").text("此车型暂无估值");
				$("#submit2").attr("disabled", "disabled");
			} else {
				$("#carStyleContainer2").show();
				$("#carCityContainer2").show();
				$("#submit2").text("开始估值");
				$("#submit2").removeAttr("disabled");
				var str = "";
				for (var i=0; i<carInfo.length; i++) {
					str += "<option value='"+i+"'>"+carInfo[i].carStyles.fld_trim+"</option>";
					console.log(JSON.stringify(carInfo[i].carStyles));
				}
				$("#inputCarStyle2").html(str);
				$("#inputCarStyle2").change(function(){
					renderCarCityOption2();
				});
				renderCarCityOption2();
			}
		}
		
		function renderCarCityOption2() {
			var index = $("#inputCarStyle2").val();
			console.log(index, carInfo[index]);
			var secondPriceList = carInfo[index].carSecondStylesPriceList;
			var str = "";
			if (secondPriceList.length==0) {
				$("#carCityContainer2").hide();
				$("#carStyleContainer2").hide();
				$("#submit2").text("此车型暂无估值");
				$("#submit2").attr("disabled", "disabled");
			} else {
				$("#carStyleContainer2").show();
				$("#carCityContainer2").show();
				$("#submit2").text("开始估值");
				$("#submit2").removeAttr("disabled");
				for (var i=0; i<secondPriceList.length; i++) {
					str += "<option value='"+secondPriceList[i].fld_buyprice+"'>"+secondPriceList[i].fld_Area+"</option>";
				}
				$("#inputCarCity2").html(str);
			}
		}
		
		$("#submit2").click(function(){
			var price = $("#inputCarCity2").val();
			var param = "?oldCarPay=" + price;
			Util.Browser.jump(url[0].src + param, "10-1-b");
		});
	</script>
	<script src="${ctx}/template/dist/js/js_general.js"></script>
	<script type="text/javascript">
		var wx = new Wx.Param();
		var gzhHash = wx.getGZHHASH("##GZHHASH##");
		var openid = wx.getOPENID();
		var stat = new Wx.Stat();
		stat.report('10-1', "二手车置换", gzhHash, openid, {});
	</script>
</body>
<!-- 多辆车选择dialog  TODO -->
<div class="modal hide fade" id="multi-car">
  <div class="modal-header">    
   <!--   <h3>请选择您要估值的车</h3>-->
  </div>
  <div class="modal-body" id="multi-car-container">
    
  </div>
</div>

</html>

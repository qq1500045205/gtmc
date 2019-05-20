<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/common/pre_general.jsp"%>
<title>贷款计算器</title>
<script src="${ctx}/template/dist/js/js_general.js"></script>
<style>
.note{ 
	padding:0px;
	border-radius:5px;
	line-height: 22px;
	margin-top:10px;
}
.note a {
	color: rgb(0,132,176);
	text-decoration:underline;
}
</style>
</head>
<body>  
    <div class="content">
    		<form class='m-form' action="" method='post' name='loan'>
    			<div class='m-form-item-header'>贷款计算器</div>
				<div class="m-form-item">
					<div class="m-label">选择车系</div> 
					<div class="m-input-box">
						<select name='inputCarType'  id="inputCarType" /></select>
					</div>
				</div>
					
				<div class="m-form-item">
					 <div class="m-label">选择型号</div>
					 <div class="m-input-box">
					 	<select name="inputCarModel" id="inputCarModel" ></select>
					 </div>
				</div>
				<div class="m-form-item">
					 <div class="m-label">车辆价格</div>
					 <div class="m-input-box">
					 	<input name="inputCarPrice" type="text" id="inputCarPrice"/><span style="margin-left:-20px;">元</span>
					 </div>
				</div>
				<div class="m-form-item">
					<div class="m-label">贷款方案</div>
					<div class="m-input-box">
						<select name="inputLoanScheme" id="inputLoanScheme" ></select>
					</div> 
				</div>
				<div class="m-form-item">
					<div class="m-label">首付比例</div>
					<div class="m-input-box">
						<select name="inputFirstPay" id="inputFirstPay" ></select>
					</div> 
				</div>
				<div class="m-form-item">
					<div class="m-label">贷款期限</div>
					<div class="m-input-box">
						<select name="inputMonthNum" id="inputMonthNum" ></select>
					</div>
				</div>
				<button type="button" class="btn_main" id="count-comfirm">贷款方案试算</button> 
			</form>
			<div style="margin-top:1.5em;">
				<ul class="list">
					<li class="item">
						<label>首付金额</label>
						<div id="firstPay" style="float:right"></div>
					</li>
					<li class="item">
						<label>贷款金额</label>
						<div id="loanPay" style="float:right"></div>
					</li> 
					<li class="item">
						<label>月供金额</label>
						<div id="monthPay" style="float:right"></div>
					</li>
					<li class="item">
						<label>尾款金额</label>
						<div id="lastPay" style="float:right"></div>
					</li>
				</ul>
				
			</div>
			<div id="note" class="note" style="display:none;">
				※ 该结果仅提供试算参考，<a href="javascript:void" onclick="goDealer();">请访问我们的授权销售店</a>，获得更多金融优惠资讯并订制专属金融购车方案
			</div>
		</div>
	<div id="contentManager">
		<a style="display: none;" href="javascript:;"
			title="${ctx}/admin/content/buycar/list.jsp">贷款计算管理</a>
	</div>
	<input type="hidden" targetId="nominate1" urlType="url" title="联系销售店 跳转至" />
	<input type="hidden" targetId="nominate2" urlType="url" title="询价 跳转至" />
	<!-- /container -->
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<script type="text/javascript">
	
		var wx = new Wx.Param();
		var gzhHash = wx.getGZHHASH("##GZHHASH##");
		var urls = wx.getURL('##URL##',[{src: "query-list-page.jsp"},{src: "query-detail-page.jsp"}]);
		var gzhType = wx.getGZHTYPE();
		
		var type = "${param.type}";
		var carModelGuid = "${param.carModelGuid}";
		if (carModelGuid.length == 0)
		{
			carModelGuid = "${param.carGuid}";
		}
		var carTypeGuid = "${param.carTypeGuid}";
		
		var loanScheme;
		var carType = [];
		var carModel = [];
		// 获取所有方案
		$.ajax({
			type : 'POST',
			url : "${ctx}/car/getAllLoanScheme",
			data : {},
			dataType : "json",
			success : function(res) {
				if (res.message=="success") {
					loanScheme = res.result;
					console.log(loanScheme);
					renderScheme();
				}
			}
		});
		
		// 获取 车类别 TODO 修改为不要权限控制的
		$.ajax({
			type : 'POST',
			url : "${ctx}/car/getAllCarType",
			data : {gzhHash: gzhHash},
			dataType : "json",
			success : function(res) {
				console.log("carType callback:", res);
				carType = res.result;
				renderCarType();
			}
		});
		// 选择车类别选项卡
		function renderCarType() {
			var str = "";
			str += "<option value=''></option>";
			console.log(carTypeGuid);
			for (var i=0; i<carType.length; i++) {
				if (carType[i].carTypeGuid==carTypeGuid) {
					str += "<option value='"+carType[i].carTypeGuid+"' selected>"+carType[i].carTypeTitle+"</option>";
				} else {
					str += "<option value='"+carType[i].carTypeGuid+"'>"+carType[i].carTypeTitle+"</option>";	
				}
			}
			console.log(str);
			$("#inputCarType").html(str);
			$("#inputCarType").change(function(){
				$.ajax({
					type : 'POST',
					url : "${ctx}/car/getCarSfxListByModel",
					data : {carTypeGuid: $("#inputCarType").val()},
					dataType : "json",
					success : function(res) {
						carModel = res;
						console.log(carModel);
						renderCarModel();
					}
				});
			});
			// 获取对应 车型号 
			console.log($("#inputCarType").val());
			
			$.ajax({
				type : 'POST',
				url : "${ctx}/car/getCarSfxListByModel",
				data : {carTypeGuid: $("#inputCarType").val()},
				dataType : "json",
				success : function(res) {
					carModel = res;
					console.log(carModel, typeof(carModel));
					renderCarModel();
				}
			});
		}
		// 渲染 车型号选项卡
		function renderCarModel() {
			var str = "";
			str += "<option value=''></option>";
			for (var i=0; i<carModel.rows.length; i++) {
				if (carModel.rows[i].carGuid==carModelGuid) {
					str += "<option value='"+carModel.rows[i].carPrice+"' selected>"+carModel.rows[i].carName+"</option>";
				} else {
					str += "<option value='"+carModel.rows[i].carPrice+"'>"+carModel.rows[i].carName+"</option>";	
				}
			}
			console.log(str);
			$("#inputCarModel").html(str);
			$("#inputCarModel").change(function(){
				$("#inputCarPrice").val(($("#inputCarModel").val()*10000).toFixed(0)==0 ? "": ($("#inputCarModel").val()*10000).toFixed(0));
				console.log("carPrice=", ($("#inputCarModel").val()*10000).toFixed(0));
			});
			$("#inputCarPrice").val(($("#inputCarModel").val()*10000).toFixed(0)==0 ? "": ($("#inputCarModel").val()*10000).toFixed(0));
		}
		// 渲染方案
		function renderScheme() {
			var loanSchemeName = [];
			for (var i=0; i<loanScheme.length; i++) {
				if (!inArr(loanScheme[i].loanSchemeName, loanSchemeName)) {
					loanSchemeName.push(loanScheme[i].loanSchemeName);
				}
			}
			
			var str = "";
			for (var i=0; i<loanSchemeName.length; i++) {
				str += "<option value='"+loanSchemeName[i]+"'>"+loanSchemeName[i]+"</option>";
			}
			console.log(str);
			$("#inputLoanScheme").html(str);
			$("#inputLoanScheme").change(function(){
				renderPayFirst();
			});
			renderPayFirst();
		}
		// 渲染首付比例
		function renderPayFirst(){
			var payFirsts = [];
			var loanSchemeName = $("#inputLoanScheme").val();
			for (var i=0; i<loanScheme.length; i++) {
				if (loanSchemeName==loanScheme[i].loanSchemeName && !inArr(loanScheme[i].firstPayPercent, payFirsts)) {
					payFirsts.push(loanScheme[i].firstPayPercent);
				}
			}
			
			var str = "";
			for (var i=0; i<payFirsts.length; i++) {
				str += "<option value='"+payFirsts[i]+"'>"+payFirsts[i]+"</option>";
			}
			console.log(str);
			$("#inputFirstPay").html(str);
			$("#inputFirstPay").change(function(){
				renderMonthNum();
			});
			renderMonthNum();
		}
		// 渲染 贷款期数
		function renderMonthNum() {
			var monthNum = [];
			var loanSchemeName = $("#inputLoanScheme").val();
			var firstPayPercent = $("#inputFirstPay").val();
			console.log(loanScheme, loanSchemeName, firstPayPercent);
			for (var i=0; i<loanScheme.length; i++) {
				if (loanSchemeName==loanScheme[i].loanSchemeName && firstPayPercent==loanScheme[i].firstPayPercent) {
					monthNum.push(loanScheme[i].monthNum);
				}
			}
			
			var str = "";
			for (var i=0; i<monthNum.length; i++) {
				str += "<option value='"+monthNum[i]+"'>"+monthNum[i]+"月</option>";
			}
			$("#inputMonthNum").html(str);
			
		}
		
		$("#count-comfirm").click(function(){
			var carPrice = $("#inputCarPrice").val();
			if (carPrice) {
				var loanSchemeName = $("#inputLoanScheme").val();
				var firstPayPercent = $("#inputFirstPay").val();
				var monthNum =$("#inputMonthNum").val();
				var loanSchemeNow;
				
				for (var i=0; i<loanScheme.length; i++) {
					if (loanSchemeName==loanScheme[i].loanSchemeName && firstPayPercent==loanScheme[i].firstPayPercent && monthNum==loanScheme[i].monthNum) {
						loanSchemeNow = loanScheme[i];
					}
				}
				
				loanSchemeNow.firstPay = (carPrice * parseFloat(loanSchemeNow.firstPayPercent) / 100).toFixed(0);
				loanSchemeNow.loanPay = (carPrice - parseFloat(loanSchemeNow.firstPay)).toFixed(0);
				loanSchemeNow.lastPay = (carPrice * parseFloat(loanSchemeNow.lastPayPercent) / 100).toFixed(0);
				loanSchemeNow.monthPay = (parseFloat(loanSchemeNow.loanPay) * parseFloat(loanSchemeNow.payPerMonthPercent) / 10000).toFixed(0);
				
				$("#firstPay").text(loanSchemeNow.firstPay + " 元");
				$("#loanPay").text(loanSchemeNow.loanPay + " 元");
				$("#monthPay").text(loanSchemeNow.monthPay + " 元");
				$("#lastPay").text(loanSchemeNow.lastPay + " 元");
				
				$("#note").show();
			} else {
				alert("请选择车型或者直接输入车价！");
			}
			
		});
		
		function goDealer() {
			if (gzhType == "DLR") {
				Util.Browser.jump(urls[1].src, "8-2-b");
			} else {
				Util.Browser.jump(urls[0].src, "8-2-b");
			}
		}
		
		function inArr(value, arr){
			for (var i=0; i<arr.length; i++) {
				if (value==arr[i]) return true;
			}
			return false;
		}
	</script>
	
	<script type="text/javascript">
		var wx = new Wx.Param();
		var gzhHash = wx.getGZHHASH("##GZHHASH##");
		var openid = wx.getOPENID();
		var stat = new Wx.Stat();
		stat.report('8-2-b', "贷款计算器", gzhHash, openid, {});
	</script>
</body>
</html>

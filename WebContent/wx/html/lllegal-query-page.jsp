<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html class="ui-mobile">
<head>
<title>违章查询</title>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/wx.js"></script>
<%@ include file="/common/datepick.jsp"%> 
<script src="${ctx }/scripts/jiaoguanju/city.js"></script>
<script src="${ctx }/scripts/jiaoguanju/provice.js"></script>
<style type="text/css">
.red{
	color:red;
	font-size: 13px;
}
.m-form-item-header{
	
}
</style>
</head>
<body>
	<div class="page_main">
		<div class="content">
			<form id="lllegalForm" method="post" class="m-form">
				<div class='m-form-item-header' style='margin-top:10px;'>请输入您的车辆信息</div>
				<div class="m-form-item" > 
					<div class="m-label">省份：</div>
					<div class="m-input-box">
						<select id="provice" name="provice" style='width:100%'></select>
					</div>
				</div>
				<div class="m-form-item"> 
					<div class="m-label">城市：</div>
					<div class="m-input-box">
						<select id="city" name="cityPinyin" style='width:100%'></select>
					</div>
				</div>
				<div class="m-form-item"> 
					<div class="m-label request">车牌号：</div>
					<div class="m-input-box">
						<select name="carPrefix" id="lsprefix" style='width:25%;display:inline-block;'></select>
						<input type="text" name="carNumber" id="lsnum" value=""  style='width:60%;display:inline-block;float:right;'>
					</div>
				</div>
				<div class="m-form-item"> 
					<div class="m-label request">发动机号：</div>
					<div class="m-input-box">
						<input type="text" name="engineNumber" id="engineno" value="" >
					</div>
				</div>
				<div class="m-form-item"> 
					<div class="m-label request">VIN：</div>
					<div class="m-input-box" >
						<input type="text" name="bodyNumber" id="bodynum" value="" >
					</div>
				</div>
				<div class="m-form-item" style='height:20px;'> 
					<div>
						<span class="red">*为必填项</span>
					</div>
				</div>
				<div class="m-form-item"> 
					 
					<Button type='submit' class='btn_main'>查询</Button>
					 
				</div>
			</form>
		</div>
	</div>

	<input type="hidden" targetId="item_0" urlType="url" title="跳转至结果页面" />

	<script>
		var util = new Wx.Param();
		var url = util.getURL('##URL##', [ {
			targetId : "item_0",
			src : "lllegal-query-result-page.jsp"
		}, {
			targetId : "item_1",
			src : "#"
		} ]);
		var defaultProvice = "广东省";
		var defaultProvicePinyin = "guangdong";
		$(function() {
			//setStyle();
			setProvice();
			setCity();
			change();
			changeItem();

			$("#provice").change(function() {
				setCity();
				change();
				changeItem();
			});

			$("#city").change(function() {
				changeItem()
			});

			$("#lllegalForm").submit(function() {
				lllegalQuery();
				return false;
			});

		});

		function changeItem() {
			var enginenum = $("#city").find("option:selected")
					.attr("enginenum");
			var bodynum = $("#city").find("option:selected").attr("bodynum");
			//-1 输入全部
			//0 不需要
			//> 0 需求位数
			if (enginenum == 0) {
				$("#engineno-span").text(" ");
			} else {
				$("#engineno-span").text("*");
			}

			if (bodynum == 0) {
				$("#bodynum-span").text(" ");
			} else {
				$("#bodynum-span").text("*");
			}
		}

		function change() {
			$("#lsprefix").find("option[title='" + $("#provice").val() + "']")
					.attr("selected", true);
			$("#lsprefix").parent().find("span").text(
					$("#provice").find("option:selected").attr("title"));
		}

		//设置省选项及车牌号前缀
		function setProvice() {
			$("#provice").html("");
			for ( var i = 0; i < proviceData.length; i++) {
				$("#provice").append(
						'<option value="'+proviceData[i].pinyin+'" title="'+proviceData[i].name+'">'
								+ proviceData[i].value + '</option>');
				$("#lsprefix").append(
						'<option value="'+proviceData[i].name+'" title="'+proviceData[i].pinyin+'">'
								+ proviceData[i].name + '</option>');
			}
			$("#provice").find("option[value='" + defaultProvicePinyin + "']")
					.attr("selected", true);
			$("#provice").parent().find("span").text(defaultProvice);

			/* $("#lsprefix").find("option[text='" + proviceData[0].name + "']")
					.attr("selected", true);
			$("#lsprefix").parent().find("span").text(proviceData[0].name); */
		}

		//设置城市选项
		function setCity() {
			$("#city").html("");
			$("#city").find("option:selected").attr("selected", false);
			$("#city").parent().find("span").text("-");
			for ( var i = 0; i < cityData.length; i++) {
				if ($("#provice").val() == cityData[i].key) {
					for ( var j = 0; j < cityData[i].list.length; j++) {
						$("#city")
								.append(
										'<option value="'+cityData[i].list[j].pinyin+'" bodynum="'+cityData[i].list[j].body_length+'"  enginenum="'+cityData[i].list[j].engine_length+'">'
												+ cityData[i].list[j].name
												+ '</option>');
					}
					$("#city").find(
							"option[text='" + cityData[i].list[0].name + "']")
							.attr("selected", true);
					$("#city").parent().find("span").text(
							cityData[i].list[0].name);
				}
			}
		}

		function setStyle() {
			$("#lsprefix").parent().parent().css("width", "28%");
			$("#lsprefix").parent().parent().css("float", "left");
			$("#lsnum").parent().css("width", "50%");
			$("#lsnum").parent().css("float", "left");
		}

		function lllegalQuery() {
			if ($("#lsprefix").val().length == 0) {
				alert("请选择车牌前缀！");
				return;
			}
			if ($("#lsnum").val().length == 0) {
				alert("请填写车牌号！");
				return;
			}
			if ($("#engineno-span").text() == "*") {
				if ($("#engineno").val().length == 0) {
					alert("请填写发动机号！");
					return;
				}
			}
			if ($("#bodynum-span").text() == "*") {
				if ($("#bodynum").val().length == 0) {
					alert("请填写VIN！");
					return;
				}
			}

			//$("#lllegalForm").attr("action","${ctx}/yjajax/queryLllegal");
			//$("#lllegalForm").submit();

			$.ajax({
				type : 'POST',
				url : "${ctx}/lllegal/query",
				data : {
					carPrefix : $("#lsprefix").val(),
					cityPinyin : $("#city").val(),
					carNumber : $("#lsnum").val(),
					engineNumber : $("#engineno").val(),
					bodyNumber : $("#bodynum").val()
				},
				success : function(result) {
					if (result.success) {
						var href = url[0].src + "?id="
								+ result.result + "&carString="
								+ result.message;
						Util.Browser.jump(href, '15-1', null);
					} else {
						alert(result.message);
						setStyle();
					}
				}
			});
		}

		var util = new Wx.Param();
		var gzhHash = util.getGZHHASH("##GZHHASH##");
		var openid = util.getOPENID();
		var stat = new Wx.Stat();
		stat.report('15-1', "违章查询", gzhHash, openid, {});
	</script>
</body>
</html>
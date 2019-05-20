<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/wx.js"></script>
<%@ include file="/common/datepick.jsp"%> 

<title>测试</title>
<style type="text/css">
.tab{ 
	border-bottom:1px solid #ddd;
	box-shadow:0 1px 0 rgba(255,255,255,.9);
}
#BaseInfo
{
	margin-top:5%;
}
</style>
<script type="text/javascript">
var maxCount = 0;
var util = new Wx.Param();
var openid = util.getOPENID();
$(function(){
	maxCount = <s:property value='#Product.productStock' />;
});
function activeTab(target) {
	console.log(target);
	if (target=="tab1") {
		if (!$("#tab1").hasClass("on") ) {
			$("#tab1").addClass("on");
			$("#tab2").removeClass("on"); 
			$("#BaseInfo").show();
			$("#ProductDetail").hide();
		}
	} else {
		if (!$("#tab2").hasClass("on") ) {
			$("#tab2").addClass("on");
			$("#tab1").removeClass("on"); 
			$("#ProductDetail").show();
			$("#BaseInfo").hide();
		}
	}
}

function submit(credit)
{
	alert(credit);
	var gzhHash = document.getElementById("gzhHash").value;
	var count = $("#count").val();
	var maxCount = 0;
	var productGuid = $("#productGuid").val();
	var url = "${ctx}/creditactivity/CheckCredit.action";
	$.ajax({
		type: 'post',
		url: url,
		dataType: 'json',
		async : false,
		data : {
			openid : openid,
			count : count,
			productGuid : productGuid
		},
		success: function(json)
		{
			if(json.success)
			{
				Util.Browser.jump("${ctx}/wx/html/orderConfirmation.jsp?credit="+credit+"&open_id="+openid+"&product_count="+count+"&product_guid="+productGuid+"&gzh_hash="+gzhHash);				
			}
			else
			{
				alert('您的积分余额不够.');
			}
		}
	});
	
}

function changeValue(val)
{
	var count = $("#count").val();
	if(val==0)
	{
		if(count>1)
		{
			$("#count").val(eval(count-1));
		}
	}
	else
	{
		
		if($("#count").val()>=maxCount)
		{
			alert("库存数量不够.");
			return;
		}
		$("#count").val(eval(eval(count)+1));
	}
}

function back()
{
	window.location.href = "${ctx}/wx/html/product-list-page.jsp";
}
</script>
</head>
<body>
	<div style="width:100%;height:30px;background-color:#F5F5F5;text-align:center;position:relative;">
		<div style="text-align:center;background-color:white;float:left;margin-top:1%;margin-left:5%;cursor:pointer;width:40px;height:25px;">
			<span onclick="back();">返回</span>
		</div>
		<span style="position:absolute;left:42%;top:10%;">
			商品简介
		</span>
	</div>
	<div class="tab">
		<ul>
			<li id="tab1" style="cursor:pointer;" onclick="activeTab('tab1')" class="on">基本信息</li>
			<li id="tab2" style="cursor:pointer;" onclick="activeTab('tab2')" class=" ">商品详情</li>
		</ul> 
	</div>
	
	<div id="BaseInfo">
		<input type="hidden" name="orderactive.productGuid" id="productGuid" value="<s:property value='#Product.productGuid' />" />
		<input type="hidden" id="gzhHash" value="<s:property value='#gzhHash' />" />
		
		<div style="width:100%;">
			<div id="ProductImage" style="float:left;width:50%;height:200px;padding-left:10%;">
				<img alt="" src="${ctx}/<s:property value='#Product.productLogo' />" style="height:120px;width:120px;"/>
			</div>
			<div style="float:left;width:auto;">
				<ul>
					<li><span><s:property value="#Product.productCredit" />积分</span></li>
					<li>市场价	￥<s:property value="#Product.productPrice" /></li>
					<li>分类：<s:property value="#Product.productTypeName" /></li>
					<li>
						<div style="width:100%; vertical-align:middle;">
							<img src="${ctx}/admin/content/creditactive/images/85.png" style="float:left;width:15px;height:15px;margin-top:8px;margin-right:5px;cursor:pointer;" onclick="changeValue(0)" />
							<input type="text" name="orderactive.productCount" id="count" value="1" readonly="readonly" style="float:left;width:40px;height:30px;text-align:center;vertical-align:middle;" />
							<img src="${ctx}/admin/content/creditactive/images/81.png" style="float:left;width:15px;height:15px;margin-top:8px;margin-left:8px;cursor:pointer;" onclick="changeValue(1)" />
							<span style="color:red">&nbsp;库存：<s:property value='#Product.productStock' /></span>
						</div>
					</li>
					<li>
						<div style="text-align:center;float:left;background-image:url('${ctx}/admin/content/creditactive/images/010.png');margin-top:10px;width:100%;height:100%;background-size:100%,100%;background-repeat:no-repeat;;cursor:pointer;" onclick="submit(<s:property value='#Product.productCredit' />)">
							<span>生成订单</span>
						</div>
					</li>
				</ul>
			</div>
		</div>
		
		<div id="ProductName" style="width:90%;float:left;text-align:center;border:1px solid #EFEEEE;margin-left:5%;margin-right:5%;margin-bottom:1%;margin-top:2%;">
			<s:property value="#Product.productName" />
		</div>
		
		<div id="ProductSpec" style="width:90%;height:150px;float:left;border:1px solid #EFEEEE;margin-left:5%;margin-right:5%;padding-left:2%;">
			商品规格：<s:property value="#Product.productSpec" />
		</div>
	</div>
	<div id="ProductDetail" style="display:none;">
		<s:property value="#Product.productDetail" />
	</div>
</body>
</html>
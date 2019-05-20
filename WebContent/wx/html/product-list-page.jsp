<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/wx.js"></script>
<%@ include file="/common/datepick.jsp"%> 
<title>积分商城</title>
<style type="text/css">
#product
{
	margin:5%;
	margin-left:10%;
}
img
{
	width:60%;
	height:100%;
}
#PageBtn
{
	text-align:center;float:left;
	background-image:url('${ctx}/admin/content/creditactive/images/010.png');
	margin-top:5px;width:100px;height:23px;background-size:100%,100%;
	margin-left:4%;
	cursor:pointer;
}
</style>

<script type="text/javascript">
var util = new Wx.Param();
var gzhHash = util.getGZHHASH("##GZHHASH##");
var openid = util.getOPENID();
var total = 0;
function next()
{
	var val = document.getElementById("types").value;
	var current = document.getElementById("currentPage").value;
	if(current!="")
	{
		if(current<total)
		{
			getData(val,++current);
		}
		else
		{
			alert("已经是最后一页");
		}
	}
	else
	{
		alert("获取当前页面值失败..跳转至第一页");
		getData(val,1);
	}
	
}

function pre()
{
	var val = document.getElementById("types").value;
	var current = document.getElementById("currentPage").value;
	if(current!="")
	{
		if(current>1)
		{
			getData(val,--current);
		}
		else
		{
			alert("已经是第一页");
		}
	}
	else
	{
		alert("获取当前页面值失败..跳转至第一页");
		getData(val,1);
	}
}
$(document).ready(function(){
	$("#types").bind('change',function(){
		getData(this.value,1);
	});
	
	
	$.ajax({
		type : 'POST',
		url : "${ctx }/creditactivity/listProductType.action",
		async : false,
		dataType : "json",
		success : function(json) {
			//alert(JSON.stringify(json));
			var type = document.getElementById("types");
			var option = new Option("全部",0);
			type.options.add(option);
			for(var i=0;i<json.length;i++)
			{
				var option = new Option(json[i].text,json[i].value);
				type.options.add(option);
			}
			//type.options[1].selected=true;
			//$("#types").onchange();
		}
	});
	
	getData(0,1);
	
});

function getData(typeid,current)
{
	var url = "${ctx}/creditactivity/GetProductByPage.action?typeid="+typeid;
	if(current!="")
	{
		url = "${ctx}/creditactivity/GetProductByPage.action?page.currentPage="+current+"&typeid="+typeid;
	}
	$.ajax({
		type : 'POST',
		url : url,
		async: false,
		dataType: 'json',
		success: function(json)
		{
			total = json.rows.totalPage;
			var list = json.rows.list;
			var content = "<div>【惊喜爆款】</div>";
			$("#currentPage").val(json.rows.currentPage);
			for(var i=0;i<list.length;i++)
			{
				content += "<div style='border:1px;float:left;width:45%;'>"
				+ "<ul id='product'>"
				+ "<li>"
				+ "<a href='${ctx}/creditactivity/GetProduct.action?productGuid="+list[i].productGuid+"&gzhHash="+gzhHash+"'><img style='width:150px;height:70px;' src='${ctx}/"+list[i].productLogo+"'></a>"
			 	+ "</li>"
				+ "<li>"+list[i].productName+"</li>"
				+ "<li>"
				+ "<div style='width:130px;float:left;'>"
				+ "<span>"+list[i].productCredit+"积分</span><br/>"
				+ "<span>市场价：￥"+list[i].productPrice+"</span>"
				+ "</div>"
				+ "<div style='float:left;'>"
				+ "</div>"
				+ "</li>"
				+ "</ul>"
				+ "</div>";
			}
			if(list.length>0)
			{
				content += "<center>"
				+ "<div id='PageDiv' style='width:100%;float:left;margin-left:6%;'>"
				+ "<div id='PageBtn' onclick='pre()'><span>上一页</span></div>"
				+ "<div id='PageBtn' onclick='next()'><span>下一页</span></div>"
				+ "</div>"
				+ "</center>";
			}
			
		$("#ProductDiv").html(content);
		}
	});
}
</script>
</head>
<body>
	<center>
		<div style="width:80%;height:50px;">
				<div>类型：<select id='types' style="width:60%;height:70%;" name="typeid" /></div>
				<input type="hidden" id="currentPage" name="page.currentPage" />
		</div>
	</center>
	
	
	<div id="ProductDiv" style="width:80%;height:auto;margin: 0 auto;">
		<div>【惊喜爆款】</div>
	</div>
</body>
</html>
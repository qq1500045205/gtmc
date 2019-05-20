<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<%@ include file="/common/pre_general.jsp"%>
<%@ include file="/common/admin_pre.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<script src="${ctx}/scripts/wx.js"></script>
<title>订单详情</title>
<style>
#a {
	height: 120px;
	float: left;
}
#b {
	height: 62px;
	line-height: 62px;
	float: left;
}
#c {
	height: 40px;
	line-height: 40px;
	float: left;
}
.list1 {
	padding: 10px;
	height: 80px;
}
</style>
</head>
<body>
	<div class="list" style="height:42px; ">
		<div style="width:160px; "id="c"><a href="javascript:;" onclick="li_click(0)"><input type="submit"
			id="sub" value="返回" ></a> </div>
			<div id="c"><font size="3">订单详情</font></div>
	</div>
		<div id='info-box-body'></div>
<!-- 			</br> -->
		<div   class="right itemtxt">
			<input type="submit" id="sub1" value="确认收货" >
		</div>
	<script type="text/javascript">
	var param = new Wx.Param();
	var openId = "${param.openId}";
	var gzhHash = "${param.hash}";
	var req = new Wx.Request();
	var data = [];
	var productOrderGuid = "${param.productOrderGuid}";
	var userGuid=null;
	$(document).ready(function() {
		if(productOrderGuid!=""){
			getData();
		}
	});
	

	function getData() {
		$.ajax({
			type : 'POST',
			url : "${ctx }/creditactivity/orderManage.action",
			async : false,
			data : {
				type : "getData",
				productOrderGuid:productOrderGuid
			},
			dataType : "json",
			success : function(json) {
			var html = "";
			obj = json.result;
			if(obj["orderStatusGuid"]!="2"){
		        $("#sub1").attr("disabled","disabled");
			}
			userGuid= obj["userGuid"];
			//var temp = "${ctx}"+ obj["productLogo"];
			html+="<div >基本信息</br>";
			html+="<div class=\"list\" style=\"BORDER-RIGHT: medium none; BORDER-TOP: medium none; BORDER-LEFT: medium none;";
			html+=" height:41px;border:1px solid  #cccccc;\"><div  id=\"c\"  >订单编号：</div>";
			html+="<div style=\" width:200px; font-weight:bold;color:red;\" id=\"c\">"+obj["productOrderNum"]+"</div>";
			html+="<div  style=\" font-weight:bold;color:red;\" id=\"c\">"+obj["orderStatusName"]+"</div></div>";
			html+="<div style=\"height:30px;border-bottom:1px solid  #cccccc;\"><span>下单时间："+obj["productOrderDate"]+"</span></div>";
			html+="<div class=\"list\" style=\"BORDER-RIGHT: medium none; BORDER-TOP: medium none; BORDER-LEFT: medium none;";
			html+=" height:124px;border-bottom:1px solid  #cccccc;\">";
			html+="<div id =\"a\" style=\" width:100px;\">收货地址</div>";
			html+="<div class=\"list\" id =\"a\" style=\" font-weight:bold;border:none;\">";
			html+="<div style=\" height:30px\">"+obj["userLocationName"]+"</div>";
			html+="<div  style=\" height:30px\">"+obj["userLocationProvice"]+"  "+obj["userLocationCity"]+"  "+obj["userLocationDistrict"]+"</div>";
			html+="<div  style=\" height:30px\">"+obj["userLocationStreet"]+"</div>";
			html+="<div  style=\" height:30px;\">"+obj["userLocationPhone"]+"</div>";
			html+="</div></div>";
			html+="<div  style=\" text-align:right; height:42px;font-weight:bold;color:red;border-bottom:1px solid  #cccccc;\" >"+obj["productCredit"];
			html+="</div>";
			html+="<div class=\"list1\" style=\" height:80px;border-bottom:1px solid  #cccccc;\"><div class=\"left itemimg\" id =\"b\">";
			html+="<img style=\"height:60px\" src=\"${ctx}"+ obj["productLogo"]+"\" /></div>";
			html+="<div style=\" text-align:center; width:160px; \"  id =\"b\"  >共"+obj["productCount"]+"件</div></div>";
			html+="</br>";
			html+="</div>";
			$('#info-box-body').html(html);
			}
		});		
	}
	function li_click(i) {
		var url =  "${ctx}/wx/html/product-order-manage.jsp?flag="+i+ "&openId=" + openId + "&hash="
		+ gzhHash;
		console.log(url);
  		Util.Browser.jump(url, "23-1");  	
	}
	
	$('#sub1').bind('click', function(){
		div_clickl();
	});
	function div_clickl(){
		$.ajax({
			type : 'POST',
			url :"${ctx }/integralmall/orderDetails.action",
			async : false,
			data : {
				type : "orderUpdate",
				productOrderGuid: productOrderGuid,
				userGuid: userGuid
			},
			dataType : "json",
			success : function(json) {
				if(json.success){
			        $.omMessageBox.alert({
			            type: 'success',
			            title: '成功',
			            content: '数据更新成功',
			            onClose: function(v) {
			            	li_click(1);
			            }
			        });
			        }else{
				        $.omMessageBox.alert({
				            type: 'error',
				            title: '失败',
				            content: json.message
			        });
			        }
		}, 
        error: function (XMLHttpRequest, textStatus, errorThrown) { 
        	alert(textStatus); alert(XMLHttpRequest.responseText); alert(errorThrown); 
     } 
	});
	}
	</script>
</body>
</html>

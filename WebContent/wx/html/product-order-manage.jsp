<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>商品订单管理</title>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/carinfo/iscroll.js" type="text/javascript"></script>

<style type="text/css">
.price {
	padding: 3px 0px;
	border-radius: 5px;
}

.header {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 45px;
	background: #eee;
	border-bottom: 1px solid #ccc;
	box-shadow: 0 1px 2px rgba(0, 0, 0, .1);
}

.tab-box {
	border-bottom: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	box-shadow: 0 1px 0 rgba(255, 255, 255, .8);
}

#title {
	margin: 5px;
	padding: 5px;
	font-size: 18px;
	height: 40px;
	text-shadow: 2px 2px 2px rgba(255, 255, 255, 1);
	font-weight: bold;
}

.list1 {
	padding: 10px;
	height: 80px;
	background: rgb(222, 228, 236) url('${ctx}/scripts/images/arrow-right.png')
		no-repeat 94% 50%;
	border: none;
}

#a {
	height: 60px;
	line-height: 60px;
	float: left;
}
#c {
	height: 40px;
	line-height: 40px;
	float: left;
}

.tab-on {
	background: rgb(222, 228, 236);
	border-radius: 3px;
}
</style>
</head>
<body>
	<div class="list">
			<div id="c"><font size="3">我的订单</font></div>
	</div>
	<div class="tab-box">
		<ul class="tab">
			<li onclick="changeOrderMerit();" id="tab-orderMerit" class="tab-on" >处理中</li>
			<li onclick="changeToOrderList();" id="tab-orderList" class="tab-on" >
				已完成</li>
		</ul>
	</div>

	<div id="orderList"></div>

	<div id="orderMerit"></div>



	<script type="text/javascript">
		var data = [];
		var data1 = [];

		var param= new Wx.Param();
		var openId = param.getOPENID();			
		var gzhHash =param.getGZHHASH('##GZHHASH##');
		var flag = "${param.flag}";

		$(function() {
			if(flag!=""){
				openId= "${param.openId}";
				gzhHash= "${param.hash}"; 
				if(flag=="1"){
					changeToOrderList();
				}else{
					changeOrderMerit();				
				}
			}else{
				changeOrderMerit();				
			}
			load();
			function load() {
				$.ajax({
					type : 'POST',
					url : "${ctx }/creditactivity/orderManage.action",
					async : false,
					data : {
						type : "getList",
						orderFlag: false,
						openId : openId,
						gzhHash : gzhHash
					},
					dataType : "json",
					success : function(res) {
						console.log("order manage", res);
						if (res.rows.length == 0) {
							loadData();
						} else {
							data = res.rows;
							loadData();
						}
					}
				});

			}
			function loadData() {
				var html_orderList = '';
				$
						.each(
								data,
								function(n, b) {
									var temp = "${ctx}"
											+ b.productLogo;
									html_orderList += '<div class="content" style=" height:140px">'
											+ '<div  class="list" style=" height:30px" ><div class="left itemimg">'
											+ '<span>'
											+ b.productOrderDate
											+ '</span>'
											+ '</div><div  class="right itemtxt" style="font-weight:bold;color:red;"><span>'
											+ b.productCredit
											+ '</span></div></div>'
											+ "<div class='list1' onclick=div_click('"+b.productOrderGuid+"','"+b.orderStatusName+"')>"
											+ '<div class="left itemimg" id ="a">'
											+ '<img style="height:60px" src="'+ temp+'" />'
											+ '</div>'
											+ '<div style=" text-align:center; width:160px;"  id ="a"  >'
											+ '<span>共'
											+ b.productCount
											+ '件</span></div></div>'
											+ '<div class="list" style=" height:40px; text-align:right; font-weight:bold;color:red;" ><span>'
											+ b.orderStatusName
											+ '</span></div></div></br>';
								});

				$("#orderList").html(html_orderList);
			}
			load1();
			function load1() {
				$.ajax({
					type : 'POST',
					url : "${ctx }/creditactivity/orderManage.action",
					async : false,
					data : {
						type : "getList",
						orderFlag: true,
						openId : openId,
						gzhHash : gzhHash
					},
					dataType : "json",
					success : function(res) {
						console.log("order manage", res);
						if (res.rows.length == 0) {
							loadData1();
						} else {
							data1 = res.rows;
							loadData1();
						}
					}
				});
			}
			function loadData1() {
				var html_orderMerit = '';
				$
						.each(
								data1,
								function(n, b) {
									var temp = "${ctx}"
											+ b.productLogo;
									html_orderMerit += '<div class="content" style=" height:140px">'
											+ '<div  class="list" style=" height:30px" ><div class="left itemimg">'
											+ '<span>'
											+ b.productOrderDate
											+ '</span>'
											+ '</div><div  class="right itemtxt" style="font-weight:bold;color:red;"><span>'
											+ b.productCredit
											+ '</span></div></div>'
											+ "<div class='list1' onclick=div_click('"+b.productOrderGuid+"','"+b.orderStatusName+"')>"
											+ '<div class="left itemimg" id ="a">'
											+ '<img style="height:60px" src="'+ temp+'" />'
											+ '</div>'
											+ '<div style=" text-align:center; width:160px;"  id ="a"  >'
											+ '<span>共'
											+ b.productCount
											+ '件</span></div></div>'
											+ '<div class="list" style=" height:40px; text-align:right; font-weight:bold;color:red;" ><span>'
											+ b.orderStatusName
											+ '</span></div></div></br>';
								});

				$("#orderMerit").html(html_orderMerit);
			}

		});
		function changeToOrderList() {
			$("#orderMerit").attr("style", "display:none;");
			$("#orderList").show();
			$("#tab-orderList").addClass("tab-on");
			$("#tab-orderMerit").removeClass("tab-on");
		}
		function changeOrderMerit() {
			$("#orderList").attr("style", "display:none;");
			$("#orderMerit").show();
			$("#tab-orderList").removeClass("tab-on");
			$("#tab-orderMerit").addClass("tab-on");
		}

		function li_click() {
			var url1;
			url1 = "${ctx}/wx/html/person-center.jsp";
			console.log(url1);
			Util.Browser.jump(url1, "23-1");

		}

		function div_click(productOrderGuid,status) {
			
			if(status=='待审核')
			{
				alert('该订单正在审核中.');
				return;
			}
			var url1;
			url1 = "${ctx}/wx/html/orderDetails.jsp?productOrderGuid="
					+ productOrderGuid  + "&openId=" + openId + "&hash="
					+ gzhHash;
			console.log(url1);
			Util.Browser.jump(url1, "23-2");
		}
		
		
	</script>
</body>

</html>
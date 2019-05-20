<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>

<title>车型鉴赏</title>
<%@ include file="/common/pre_general.jsp"%>
 <script src="${ctx}/scripts/carinfo/iscroll.js" type="text/javascript"></script>  
<style type="text/css">  
.price{
	padding:3px 5px;
	border-radius:5px;
}
.header{
	position:fixed;
	top:0;
	left:0;
	width:100%;
	height:45px;
	background:#eee;
	border-bottom:1px solid #ccc;
	box-shadow:0 1px 2px rgba(0,0,0,.1);
	
}
.tab-box{ 
	border-bottom:1px solid #ccc;
	border-bottom:1px solid #ccc;
	box-shadow:0 1px 0 rgba(255,255,255,.8);
}
#title{
	margin:5px;
	padding:5px;
	font-size:18px;
	height:40px; 
	text-shadow:2px 2px 2px rgba(255,255,255,1);
	font-weight:bold;
} 
.tab-on{
	background: rgb(222,228,236);
	border-radius: 3px;
}
</style>
</head>
<body>
 
	<div class="tab-box">
		<ul class="tab">
			<li onclick="changeCarMerit();" id="tab-carMerit">
				车型亮点
			</li>
			<li onclick="changeToCarList();" id="tab-carList" class="tab-on">
				所有型号
			</li>
		</ul>
	</div>
	<div id="carList">
		<div id="car1" class="info-item" onclick="li_click(1)">
				<h3 class="title">凯美瑞 豪华版 1.5L</h3>
				<span class="price">官方指导价：24.1万元</span> 
		</div>
		<div id="car1" class="info-item">  
			<h3 class="title">凯美瑞 豪华版 1.0L</h3>
			<span class="price">官方指导价：22.1万元</span> 
		</div> 
		<div id="car1" class="info-item">  
			<h3 class="title">凯美瑞 豪华版 2.5L</h3>
			<span class="price">官方指导价：23.1万元</span>
		</div> 
	</div>
	<div id="carMerit" style="display:none" class="content">
	</div>
<input type="hidden" targetId="calc_url" urlType="url" title="列表 跳转至" />
<div id="contentManager">
		<a style="display: none;" href="javascript:;" title="${ctx}/admin/content/carModel/index.jsp">车型管理</a>
</div>
<script type="text/javascript">
	var data = [];
	
	var wx = new Wx.Param();
	var url = wx.getURL('##URL##', [{
		targetId : "",
		src : "${ctx}/wx/html/car-info-detail.jsp"
	}]);
	
	var carTypeGuid = "${param.carTypeGuid}";
	var carTypeTitle="${param.carTypeTitle}";
	var firstPage = "${param.firstPage}";
	$(function() {
		//carTypeGuid='sadfads';
		// if (firstPage=="2-2") {
		//	changeToCarList();
		// } else {
			changeCarMerit();
		// }
		
		load();
		function load(){
			if (carTypeGuid) {
				$.ajax({
					  type: 'POST',
					  url: "${ctx}/car/getCarSfxListByModel",
					  data: {"carTypeGuid":carTypeGuid},
					  dataType: "json",
					  success: function(res){
						  console.log("car Sfx", res);
						  if(res.rows.length==0){
							  loadData();
						  }else{
						  	data=res.rows;
						  	loadData();
						  }
					  }
				});
			} else {
				data = [{	carCartypeGuid: "084e32e3-f744-489b-80d0-c57f440ba200",
							carColor: "",
							carGuid: "0ce850e4-5ca1-4109-9086-e3d67b4810aa",
							carName: "CAMRY HEV 凯美瑞 尊瑞-2.5HV至尊版",
							carPrice: "28.98",
							carSortid: "0",
							createdOn: "2014-03-01 22:46:23",
							deleteOn: "",
							deleteTag: 0
						},{
							carCartypeGuid: "084e32e3-f744-489b-80d0-c57f440ba200",
							carColor: "",
							carGuid: "0ce850e4-5ca1-4109-9086-e3d67b4810aa",
							carName: "CAMRY HEV 凯美瑞 尊瑞-2.5HV至尊版1",
							carPrice: "28.98",
							carSortid: "0",
							createdOn: "2014-03-01 22:46:23",
							deleteOn: "",
							deleteTag: 0
						},{
							carCartypeGuid: "084e32e3-f744-489b-80d0-c57f440ba200",
							carColor: "",
							carGuid: "0ce850e4-5ca1-4109-9086-e3d67b4810aa",
							carName: "CAMRY HEV 凯美瑞 尊瑞-2.5HV至尊版2",
							carPrice: "28.98",
							carSortid: "0",
							createdOn: "2014-03-01 22:46:23",
							deleteOn: "",
							deleteTag: 0}];
				loadData();
			}
		}
		function loadData() {
					var html_carlist = '';
					$.each(data,function(n, b) {
						html_carlist += '<div id="car' + (n + 1) + '" class="info-item" onclick="li_click('+n+')">'+
											'<h3 class="title">' + b.carName + '</h3>'+
											'<span class="price">官方指导价：<span style="color:red;font-weight:bold;">'+ (b.carPrice*1).toFixed(2)+ '万元</span></span>'+
										'</div>';
					});
			$("#carList").html('');
			$("#carList").append(html_carlist);
			if (!carTypeTitle) {
				$.ajax({
					  type: 'POST',
					  url: "${ctx}/car/getCarType",
					  data: {"carTypeGuid":carTypeGuid},
					  dataType: "json",
					  success: function(res){
						  console.log("car type:", res);
						  $("#title").append(res.result.carTypeTitle);
					  }
				});
			} else {
				$("#title").append(carTypeTitle);	
			}			
		}
		//var carTypeGuid = "${param.carTypeGuid}";
		data1={};
		data1.carTypeGuid=carTypeGuid;
		load1(data1);
		function load1(data1) {
			$.ajax({
				  type: 'POST',
				  url: "${ctx}/car/queryCarModel",
				  data: {carModel:JSON.stringify(data1)},
				  success: function(data){
					  console.log("carModel", data);
					  loadData1(data.result);
				  }
			});
		}
		function loadData1(data) {
			var html_carMerit = data.carTypeMerit;
			$("#carMerit").html(html_carMerit);
		}

	});
	var myScroll;
	function loaded() {
		myScroll = new iScroll(
				'wrapper',
				{
					snap : true,
					momentum : false,
					hScrollbar : false,
					onScrollEnd : function() {
						document.querySelector('#indicator > li.active').className = '';
						document.querySelector('#indicator > li:nth-child('
								+ (this.currPageX + 1) + ')').className = 'active';
					}
				});

		setInterval(function() {
			myScroll.scrollToPage('next', 0);
		}, 3500);
	}

	document.addEventListener('DOMContentLoaded', loaded, false);
	function li_click(index) {
		var param = "?carGuid="+data[index].carGuid+"&carTypeGuid="+carTypeGuid;
		Util.Browser.jump(url[0].src + param, "2-2", data[index]);
	}
	function changeToCarList(){
		$("#carMerit").attr("style", "display:none;"); 
		$("#carList").show();
		$("#tab-carList").addClass("tab-on"); 
		$("#tab-carMerit").removeClass("tab-on");  
	}
	function changeCarMerit(){
		$("#carList").attr("style", "display:none;");
		$("#carMerit").show();
		$("#tab-carList").removeClass("tab-on");
		$("#tab-carMerit").addClass("tab-on"); 
		
	}
	//----------------------------------页面统计------------------------------------------------------------------
	
	
</script>

<script type="text/javascript">
	var wx = new Wx.Param();
	var gzhHash = wx.getGZHHASH("##GZHHASH##");
	var openid = wx.getOPENID();
	var stat = new Wx.Stat();
	stat.report('2-2', "车型信息列表", gzhHash, openid, {});
</script>
</body>

</html>
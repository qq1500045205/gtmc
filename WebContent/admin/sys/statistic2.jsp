<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>系统参数设置</title>
<%@ include file="/common/admin_pre.jsp"%>
<script type="text/javascript" src="${ctx }/scripts/chart/Chart.min.js"></script>
<script type="text/javascript" src="${ctx }/scripts/chart/data.js"></script>
<script type="text/javascript" src="${ctx }/scripts/common.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/css/common.css" />

<style type="text/css">
.table {
	margin-top: 20px;
	margin-left: 20px;
	border: 1px solid #CCC;
	width: 70%;
	height: 200px;
	text-align: left;
	vertical-align: top;
}

.table_tr {
	height: 30px;
	border: 1px solid #CCC;
}

.table_tr_td {
	border: 0;
}
.input-combo{
	width:80px;
}
.qbtn{
	width:80px;
	height:28px;
}
</style>
</head>
<body style='padding:10px;width:95%;'>
	类型：
	<input class="input-combo" id="type"> 时间： <input class="input-combo" id="date"> 模块： <input class="input-combo" id="page"> 
	<input type="submit" value="查询" class='qbtn' onclick="query()">
	<br /> 图表：
	<div style='padding:10px;border:1px dashed #ccc;margin:10px 0;width:98%'>
		<canvas id="canvas" height="450" width="100%"></canvas>
	</div>
	<script type="text/javascript">
		
		$('#canvas').attr('width', (Util.Window.getWidth() * 0.90) + 'px');
		
		
		var labData = [];
		var pvData = [];
		var uvData = [];
		var pvFillColor = "rgba(220,220,220,0.5)";
		var pvStrokeColor = "rgba(220,220,220,1)";
		var uvFillColor = "rgba(151,187,205,0.5)";
		var uvStrokeColor = "rgba(151,187,205,1)";

		//选择类型
		$('#type').omCombo({
			dataSource : [ {
				text : '按小时统计',
				value : 'hour'
			}, {
				text : '按天统计',
				value : 'day'
			} ]
		});
		$('#date').omCalendar();
		//加载模块
		$('#page').omCombo({
			dataSource : '${ctx}/admin/getCodeList',
			editable : false
		});

		function query() {
			if (!$("#type").val()) {
				alert("请选择类型");
				return;
			}
			if (!$("#date").val()) {
				alert("请选择时间");
				return;
			}
			if (!$("#page").val()) {
				alert("请选择模块");
				return;
			}
			if ($("#type").val() == "hour") {
				loadDataByHour($("#date").val(), $("#page").val());
			} else if ($("#type").val() == "day") {
				loadDataByDay($("#date").val(), $("#page").val());
			}
			draw();
		}

		function loadDataByHour(date, name) {
			pvData = [];
			uvData = [];
			for ( var i = 0; i < 24; i++) {
				labData[i] = i;
				var url = "${ctx }/scripts/chart/stat-gtmc_wx-hour-" + date
						+ "-" + ((i<=9 || i==0)?("0"+i):i) + ".json";
				//if (isexists(url)) {
				$.ajax({
					url : url + "?" + new Date().getTime(),
					dataType : "json",
					async : false,
					success : function(data) {
						if (data) {
							for ( var j = 0; j < data.length; j++) {
								if (data[j].page == name) {
									pvData.push(data[j].pv);
									uvData.push(data[j].uv);
									break;
								}
							}
						} else {
							pvData.push(0);
							uvData.push(0);
						}
					},
					error : function() {
						pvData.push(0);
						uvData.push(0);
					}
				});
				//}
			}
		}

		function loadDataByDay(date, name) {
			pvData = [];
			uvData = [];
			var dt = date.split("-");
			var num = getMaxDay(dt[0],dt[1]);
			for ( var i = 0; i < num; i++) {
				labData[i] = (i+1);
				var url = "${ctx }/scripts/chart/stat-gtmc_wx-day-" + dt[0]+"-"+dt[1]+"-"+(i+1)
						+ ".json";
				//if (isexists(url)) {
				$.ajax({
					url : url + "?" + new Date().getTime(),
					dataType : "json",
					async : false,
					success : function(data) {
						if (data) {
							for ( var j = 0; j < data.length; j++) {
								if (data[j].page == name) {
									pvData.push(data[j].pv);
									uvData.push(data[j].uv);
									break;
								}
							}
						} else {
							pvData.push(0);
							uvData.push(0);
						}
					},
					error : function() {
						pvData.push(0);
						uvData.push(0);
					}
				});
				//}
			}
			console.log(pvData);
			console.log(uvData);
		}

		//判断文件是否存在
		function isexists(fileURL) {
			var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			xmlhttp.open("GET", fileURL, false);
			xmlhttp.send();
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200)
					return true;
			}
			return false;
		}

		//获取指定月有多少天
		function getMaxDay(Y, M) {
			return new Date(Y, M, 0).getDate();
		}

		function getData(pvData, page) {
			console.log(pvData, page);
			for ( var k = 0; k < pvData.length; k++) {
				if (pvData[k].page == page) {
					return k;
				}
			}
			return -1;
		}

		function draw() {
			var lineChartData = {
				labels : labData,
				datasets : [ {
					fillColor : pvFillColor,
					strokeColor : pvStrokeColor,
					data : pvData
				}, {
					fillColor : uvFillColor,
					strokeColor : uvStrokeColor,
					data : uvData
				} ]
			};
			
			new Chart(document.getElementById("canvas").getContext("2d")).Line(lineChartData);
			
		}
	</script>
</body>
</html>
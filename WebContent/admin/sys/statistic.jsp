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

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/css/common.css" />

<style type="text/css">
.table {
	margin-top: 20px;
	margin-left: 20px;
	border: 1px solid #CCC;
	width: 90%;
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
</style>
</head>
<body>

	<canvas id="canvas" height="450" width="800"></canvas>
	
	<canvas id="canvas2" height="450" width="800"></canvas>
	<script type="text/javascript">
		var labData= [];
		var pv = [];
		var uv = [];
		//一天的数据按小时统计
		var dataByHour = [];
		loadDataByHour("2014-03-01");
		function loadDataByHour(date){ //date到天：2014-03-01
			for(var i=0;i<24;i++){
		        $.ajax({ 
                    url: "${ctx }/scripts/chart/stat-gtmc_wx-hour-"+date+"-"+i+".json?"+new Date().getTime(), 
                    dataType: "json", 
                    async:false,
                    success: function (pvHourData) { 
                    	if(pvHourData){
		            		//console.log(i,pvHourData);
		            		var obj = {};
		            		obj[i] = pvHourData;
							dataByHour.push(obj);
						}else{
							var obj = {};
		            		obj[i] = [];
							dataByHour.push(obj);
						}
                    }, 
                    error: function (XMLHttpRequest, textStatus, errorThrown) { 
                           // alert(errorThrown); 
                    } 
            	});
			}
			//计算
			console.log("dataByHour",dataByHour);
			for(var i=0;i<dataByHour.length;i++){
				var item = dataByHour[i];
				if( typeof(item) != "undefined"){
					for(var it in item){
						var dt = item[it];
						for(var j=0;j<dt.length;j++){
							if(i == 0){
								labData[j] = dt[j].page;
								pv[j] = dt[j].pv;
								uv[j] = dt[j].uv;
							}else{
								pv[j] = parseInt(pv[j])+parseInt(dt[j].pv);
								uv[j] = parseInt(uv[j])+parseInt(dt[j].uv);
							}
						}
					}
				}
			}
			console.log(labData,pv,uv);

		}
			
		function getData(pvData,page){
			console.log(pvData,page);
			for(var k=0;k<pvData.length;k++){
				if(pvData[k].page == page ){
					return k;
				}
			}
			return -1;
		}
		
		var lineChartData = {
			labels : labData,
			datasets : [ {
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,1)",
				data : pv
			}, {
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,1)",
				data : uv
			} ]
		};
		var myLine = new Chart(document.getElementById("canvas").getContext(
				"2d")).Bar(lineChartData);
		var myLine = new Chart(document.getElementById("canvas2").getContext(
		"2d")).Line(lineChartData);
	</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预约管理</title>
<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-mouse.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-resizable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-draggable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-position.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-combo.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-messagebox.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-button.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-buttonbar.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-calendar.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-dialog.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-fileupload.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>
<script type="text/javascript" src="${ctx }/scripts/common.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-grid-rowexpander.css" />	

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/themes/default/om-all.css" />
<!-- view_source_begin -->
<style>
html,body {
	font-family: "Arial", "Helvetica", "Verdana", "sans-serif";
	font-size: 12px;
	width: 100%;
	height: 96%;
	margin: 0 auto;
	padding: 0
}

.item1_label {
	width: auto;
	display: inline;
	float: left;
	background-color: #eee;
	border-radius: 3px;
}

.td7 {
	line-height: 30px;
	float: left;
	padding-left: 10px;
	border-bottom: 1px solid #ccc;
}

li {
	list-style: none;
	margin: 0;
	padding: 0;
}

ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

.om-panel {
	margin-left: 0px;
	margin-top: 5px;
}

 #search-panel span.label{
      	margin-left: 10px;
}
.input-text {
    border: 1px solid #6D869E;
    height: 18px;
    vertical-align: middle;
    width: 200px;
}
#search-panel span.om-combo,#search-panel span.om-calendar{
	vertical-align: middle;
}
input#navSearch{
	width: 135px;;
	height: 16px;
	margin-left: 2px;
	background: url("${ctx}/scripts/image/nav-search.png") no-repeat scroll 0 0 #FFFFFF;
	padding-left: 20px;
	border: 1px solid #99A8BB;
	line-height: 16px;
}
 .srch_line {
	display: block;
	margin: 5px;
	width: auto;
}

 .srch_line ul {
	display: block;
}

.srch_line ul li {
	list-style-type: none;
	float: left;
	display: inline;
	margin: 3px;
}
.button-css{
	width: 120px;
	height: 35px;
	margin-top: 10px;
	cursor: pointer;
	float: right;
	background-color: #00A1CB;
	border-color: #007998;
	color: white;
	text-shadow: 0 -1px 1px rgba(0, 40, 50, 0.35);
	border-radius: 3px;
}
.element.style {
}
.om-dialog .om-dialog-title {
float: left;
margin: 0.7em 16px 0.1em 0.5em;
font-size: 16px;
color: #999;
}
.om-dialog .om-dialog-title {
	margin:0 0 0 10px;
}

.color_red{
	color:red;
}

</style>
<script type="text/javascript">
 	var carTypes;

	function getYuyueType(colValue){
		if(colValue=='1')
   			return '预约试驾';
		if(colValue=='2')
       		return '预约维修保养';
		if(colValue=='3')
       		return '预约年审';
	}
	function getYuyueItem(yuyueType){
		if(yuyueType=='1'){
			//预约试驾
			var obj = [{
				header : '车型',
				name : 'yuyueField1',
				align : 'left',
				width : '60px',
				renderer:function(colValue, rowData, rowIndex){
					for(var i=0; i<carTypes.length; i++) {
						if (carTypes[i].carTypeGuid==colValue) {
							return carTypes[i].carTypeTitle;
						}
					}
					return "";
	     		} 
			}];
			return obj;
		}
   		if(yuyueType=='2' || yuyueType=='3'){
   			//预约维修保养
   			//预约年审
   			var obj = [{
   				header : '车牌前缀',
   				name : 'yuyueField1',
   				align : 'left',
   				width : '60px'
   			},{
   				header : '车牌号码',
				name : 'yuyueField2',
				align : 'left',
				width : '60px'
   			}];
   			return obj;
   		}
   		return null;
	}
	var yuyueExtra = getYuyueItem('${param.yuyue_type}');
	
	$(document).ready(function() {
		$.ajax({
			  type: 'POST',
			  url: "${ctx}/car/getAllCarType",
			  async: false,
			  dataType: "json",
			  success: function(res){
				  carTypes = res.result;
				  $('#grid').omGrid({
						limit : 12,
						singleSelect : false,
						width : '100%',
						height:400,
						showIndex : false,
						dataSource : "${ctx}/yuyue/getAllYuyueList?yuyue_type=${param.yuyue_type}",
						colModel : array_merge([ {
							header : '预约类型',
							name : 'yuyueType',
							align : 'left',
							width : '60px',
							renderer:function(colValue, rowData, rowIndex){
								return getYuyueType(colValue);
				     		} 
						}, {
							header : '预约时间',
							name : 'createdOnString',
							align : 'left',
							width : '140px',
							sort : 'clientSide', 
						}],yuyueExtra, [{
							header : '用户名',
							name : 'userName',
							align : 'left',
							width : '60px'
						}, {
							header : '用户电话',
							name : 'userTel',
							align : 'left',
							width : '100px'
						}, {
							header : '销售店',
							name : 'projectName',
							align : 'left',
							width : '100px'
						}, {
							header : '期望时间',
							name : 'yuyueTime',
							align : 'left',
							width : '100px'
						}, {
							header : '备注',
							name : 'yuyueMemo',
							align : 'left',
							width : '200px',
							sort : 'clientSide'
						}]),
						autoFit : true
					});
			  }
		});
		

		$('#buttonbar').omButtonbar({
			btns : [ {
				label : "导出",
				id : "button-add",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					var param = '&';
					param += 'created_on_start='+$("#created_on_start").val();
					param += 'created_on_end='+$("#created_on_end").val();
					param += 'yuyue_time_start='+$("#yuyue_time_start").val();
					param += 'yuyue_time_end='+$("#yuyue_time_end").val();
					param += 'user_tel='+$("#user_tel").val();
					param += 'user_name='+$("#user_name").val();
					location.href = "${ctx}/download/exportYuyueFuwuList?yuyue_type=${param.yuyue_type}"+param;
				}
			}]
		});
 
		$("#search-type").omCombo({
			width : 100,
			value : '-',
			editable : false,
			dataSource : [ {
				text : '查询条件',
				value : ''
			}, {
				text : '预约试驾',
				value : '1'
			},{
				text : '预约维修保养',
				value : '2'
			},{
				text : '预约年审',
				value : '3'
			}
			]
		});
		//-------------------
		
		$('#created_on_start').omCalendar({
                showTime : true
        });
		$('#created_on_start').omCalendar({
            showTime : true
    	});
		$('#created_on_start').omCalendar({
            showTime : true
    	});
		$('#created_on_start').omCalendar({
            showTime : true
    	});
		//--------------------
		$("#search-panel").omPanel({
			title : "高级搜索",
			collapsed : false,
			collapsible : false
		});
		$('span#button-search').omButton({
			icons : {
				left : '${ctx}/scripts/image/search.png'
			},
			width : 70,
			onClick : function(event) {
				var param = '&';
				param += 'created_on_start='+$("#created_on_start").val();
				param += 'created_on_end='+$("#created_on_end").val();
				param += 'yuyue_time_start='+$("#yuyue_time_start").val();
				param += 'yuyue_time_end='+$("#yuyue_time_end").val();
				param += 'user_tel='+$("#user_tel").val();
				param += 'user_name='+$("#user_name").val();
				$('#grid').omGrid({
					dataSource : "${ctx}/ajax/getAllYuyueFuwuList?yuyue_type=${param.yuyue_type}"+param,
				});
			}
		});
	});
</script>
<!-- view_source_end -->
</head>
<body>

	<!-- view_source_begin -->
	<div id="search-panel">
		<div>
			<form action="#" id="search-form" method="post">
				<div class="srch_line">
					<ul>
						<li>电话号码:<input id="user_tel" name="oppQuery.status" style="width: 40px;"/></li>
					</ul>
					<ul>
						<li>姓名:<input id="user_name" name="oppQuery.status" style="width: 40px;"/></li>
					</ul>
					<ul>
						<li>预约时间:<input id="created_on_start" name="oppQuery.status" style="width: 80px;"/></li>
					</ul>
					<ul>
						<li>至 <input id="created_on_end" name="oppQuery.status" style="width: 80px;"/></li>
					</ul>
					<ul>
						<li>希望时间:<input id="yuyue_time_start" name="oppQuery.status" style="width: 80px;"/></li>
					</ul>
					<ul>
						<li>至 <input id="yuyue_time_end" name="oppQuery.status" style="width: 80px;"/></li>
					</ul>
				</div>

				<div class="srch_line">
					<span id="button-search">搜索</span>
				</div>

			</form>
		</div>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar"></div>
		<div style="width: 100%;">
			<table id="grid"></table>
		</div>
	</div>
	

</body>
</html>
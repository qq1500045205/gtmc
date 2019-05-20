<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>报名统计</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>

<link rel="stylesheet" type="text/css"  href="${ctx }/scripts/css/common.css" />
</head>
<body>
		<div id="search-panel">
		<div>
			<form action="#" id="search-form" method="post">
				<div class="srch_line">
					<ul>
						<li><input id="search-status" name="oppQuery.status" /></li>
						<li><input style="width: 100px;" /></li>
					</ul>
				</div>

				<div class="srch_line">
					<span id="button-search">搜索</span>
				</div>

			</form>
		</div>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin: 5px 0 5px 0;"></div>
		<div style="width: 100%;">
			<table id="grid"></table>
		</div>
	</div>
	
	<script type="text/javascript">
	$(document).ready(function() {
		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height:400,
			showIndex : false,
			dataSource : "${ctx}/ajax/griddata.action?type=activeSign&actGuid=${param.actGuid}",
			colModel : [ {
				header : '活动名称',
				name : 'actName',
				align : 'left',
				width : 100
			}, {
				header : '用户名称',
				name : 'userName',
				align : 'left',
				width : 200,
				sort : 'clientSide'
			}, {
				header : '手机号码',
				name : 'userMobile',
				align : 'left',
				width : 80
			}, {
				header : '车牌号',
				name : 'userCarNo',
				align : 'left',
				width : 80
			}, {
				header : '参加时间',
				name : 'attendOn',
				align : 'left',
				width : 80
			}, {
				header : '参加人数',
				name : 'attendNum',
				align : 'left',
				width : 80
			}, {
				header : '成人数',
				name : 'attendNum',
				align : 'left',
				width : 80
			},   {
				header : '儿童数',
				name : 'attendChild',
				align : 'left',
				width : 80
			},{
				header : '到场方式',
				name : 'arriveBy',
				align : 'left',
				width : 150
			},  {
				header : '班车路线',
				name : 'arriveLine',
				align : 'left',
				width : 150
			}, {
				header : '备注',
				name : 'remark',
				align : 'left',
				width : 120
			}],
			autoFit : true
		});

		$('#buttonbar').omButtonbar({
			btns : [ {
				label : "到处",
				id : "button-add",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					
				}
			}]
		});
		
		$("#search-panel").omPanel({
			title : "高级搜索",
			collapsed : false,
			collapsible : false
		});

		$("#search-status").omCombo({
			width : 100,
			value : '-',
			editable : false,
			dataSource : [ {
				text : '查询条件',
				value : '-'
			}, {
				text : '条件一',
				value : '1'
			}, {
				text : '条件二',
				value : '2'
			} ]
		});
		
		$('span#button-search').omButton({
			icons : {
				left : '${ctx}/scripts/image/search.png'
			},
			width : 70,
			onClick : function(event) {

			}
		});
	});

</script>
</body>
</html>
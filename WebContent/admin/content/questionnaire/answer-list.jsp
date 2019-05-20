<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>调查问卷-答题情况</title>
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
	var questionGuid = "${param.questionGuid}";
	$(document).ready(function() {
		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height: 400,
			showIndex : true,
			dataSource : "${ctx}/ajax/griddata.action?type=answer&questionGuid="+questionGuid,
			colModel : [ {
				header : '答题人',
				name : 'userName',
				align : 'left',
				width : 100
			},{
				header : '答题人电话',
				name : 'userTel',
				align : 'left',
				width : 100
			}, {
				header : '题目',
				name : 'questionValue',
				align : 'left',
				width : 400,
			}, {
				header : '答案',
				name : 'answer',
				align : 'left',
				width : 200,
			}, {
				header : '答题时间',
				name : 'answerTime',
				align : 'left',
				width : 150
			}],
			autoFit : true
		});
		
		$('#buttonbar').omButtonbar({
			btns : [ {
				label : "返回",
				id : "button-add",
				icons : {
					left : '${ctx}/scripts/image/back.png'
				},
				onClick : function() {
					history.back();
				}
			},{
				label : "导出",
				id : "button-export",
				icons : {
					left : '${ctx}/scripts/image/output.png'
				},
				onClick : function() {
					location.href = "${ctx}/question/exportAnswer?questionGuid=" + questionGuid;
				}
			}]
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
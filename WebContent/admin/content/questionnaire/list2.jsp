<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>调查问卷</title>
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

	var editQuestion = function(questionGuid, questionValue, type) {
		//console.log(questionGuid, questionValue, type);
		$("#dialog-edit").omDialog("open");
		$("#edit-question-frame").attr("src", "${ctx}/admin/content/questionnaire/edit-question.jsp?questionGuid="+questionGuid+"&questionValue="+questionValue+"&type="+type);
		
	};
	
	$(document).ready(function() {
		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height:400,
			showIndex : false,
			dataSource : "${ctx}/ajax/griddata.action?type=userAnswer",
			colModel : [ {
				header : '客户姓名',
				name : 'userName',
				align : 'left',
				width : '50px',
				
			}, {
				header : '手机号码',
				name : 'questionValue',
				align : 'left',
				width : '100px'
			}, {
				header : '答题时间',
				name : 'answerTime',
				align : 'left',
				width : '150px'
			}, {
				header : 'Q1',
				name : 'q1',
				align : 'left',
				width : 35
			}, {
				header : 'A1',
				name : 'a1',
				align : 'left',
				width : 100
			},  {
				header : 'Q2',
				name : 'q2',
				align : 'left',
				width : 35
			}, {
				header : 'A2',
				name : 'a2',
				align : 'left',
				width : 100
			},  {
				header : 'Q3',
				name : 'q3',
				align : 'left',
				width : 35
			}, {
				header : 'A3',
				name : 'a3',
				align : 'left',
				width : 100
			},  {
				header : 'Q4',
				name : 'q4',
				align : 'left',
				width : 35
			}, {
				header : 'A4',
				name : 'a4',
				align : 'left',
				width : 100
			},  {
				header : 'Q5',
				name : 'q5',
				align : 'left',
				width : 35
			}, {
				header : 'A5',
				name : 'a5',
				align : 'left',
				width : 100
			}],
			autoFit : true
		});
		
		$('#buttonbar').omButtonbar({
			btns : [ {
				label : "导出",
				id : "button-export",
				icons : {
					left : '${ctx}/scripts/image/output.png'
				},
				onClick : function() {
					location.href = "${ctx}/question/exportUserAnswer";
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
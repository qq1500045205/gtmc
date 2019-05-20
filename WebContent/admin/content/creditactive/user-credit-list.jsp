<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<title>积分活动</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/css/common.css" /> 
</head>

<body>
	<div id="search-panel">
		<div>
			<form action="#" id="search-form" method="post">
				<div class="srch_line new_line">
					<ul>
						<li><input id="search-condition" /></li>
						<li><input id="search-word" name="oppQuery.status" class="input-text" /></li>
						<li>&nbsp;&nbsp;<input id="search-credit" name="searchCredit" /></li>
						<li><input id="search-min-credit" name="oppQuery.status" class="input-text" /></li>
						<li>至 <input id="search-max-credit" name="oppQuery.status" class="input-text" /></li>
					</ul>
				</div>
				<div class="srch_line inline">
					<span id="button-search" onclick="search()">搜索</span>
				</div>

			</form>
		</div>
	</div>
	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin: 5px 0 5px 0;">
		</div>
		<div style="width: 100%;">
			<table id="grid">
			</table>
		</div>
	</div>
	
	
	
	
	<!-- 弹出框 -->
	<div id="dialog-show" title="积分活动记录">
		<table id="grid2"></table>
		<!-- 弹出框 -->
		<div id="dialog-active" title="活动详情" style="position:relative;">
			<iframe id="dialog-active-frame" src="" frameborder="0" width="550" height="370"></iframe>
		</div>
	</div>
	
	
	
	
	
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#grid').omGrid({
		        limit: 12,
		        singleSelect: false,
		        width: '100%',
		        height: 400,
		        showIndex: false,
		        dataSource: "${ctx}/creditactivity/listUserCredit.action",
		        autoFit: true,
		        colModel: [{
		        	header : '公众号',
		        	name : 'gzhName',
		        	align : 'center',
		        	width : 100
		        },
		        {
		            header: '用户名',
		            name: 'userName',
		            align: 'center',
		            width: 150
		        },
		        {
		            header: '用户昵称',
		            name: 'nickName',
		            align: 'center',
		            width: 150
		        },
		        {
		            header: '手机号',
		            name: 'userTel',
		            align: 'center',
		            width: 150
		        },
		        {
		            header: '当前积分',
		            name: 'currentCredit',
		            align: 'center',
		            width: 150
		        },
		        
		        {
		            header: '总积分',
		            name: 'countCredit',
		            align: 'center',
		            width: 150
		        },
		        {
		            header: '查看详细',
		            name : 'memberGuid',
		            align: 'center',
		            width: 'autoExpand',
		            renderer: function(colValue, rowData, rowIndex) {
//		            	return showDialog(rowData.memberGuid);
		              return "<a href='javascript:;' onclick=showDialog('"+rowData.userGuid+"')>详细信息</a>";
		                
		            }
		        }]
		    });
		    
		    
		    $('#grid2').omGrid({
		    	limit: 12,
		        singleSelect: false,
		        width: 730,
		        height: 400,
		        showIndex: false,
		        sortName: 'actCredit',
		        sortOrder: 'desc',
		        autoFit: true,
		        colModel: [{
		        	header : '活动名称',
		        	name : 'actName',
		        	align : 'center',
		        	width : 130,
		        	renderer: function(colValue,rowData,rowIndex)
		        	{
		        		return "<a href='javascript:;' onclick=showActiveInfo('"+rowData.memberCreditGuid+"')>"+rowData.actName+"</a>";
		        	}
		        },	
		        {
		            header: '开始日期',
		            name: 'startOn',
		            align: 'center',
		            width : 100
		        },
		        {
		            header: '结束日期',
		            name: 'endOn',
		            align: 'center',
		            width : 100
		        },
		        {
		            header: '参与日期',
		            name: 'createdOn',
		            align: 'center',
		            width : 100
		        },
		        {
		            header: '积分',
		            name : 'actCredit',
		            align: 'center',
		            width: 100
		        }]
		    });
		    
			$("#search-credit").omCombo({
				width : 100,
				value : '0',
				editable : false,
				dataSource : [ {
					text : '当前积分',
					value : '0'
				}, {
					text : '总积分',
					value : '1'
				} ]
			});
	
		    $("#search-panel").omPanel({
		        title: "高级搜索",
		        collapsed: false,
		        collapsible: false
		    });
	
		    $("#search-condition").omCombo({
		        width: 100,
		        value: '0',
		        editable: false,
		        dataSource: [{
		            text: '查询条件',
		            value: '0'
		        },
		        {
		            text: '公众号',
		            value: '1'
		        },
		        {
		            text: '用户名',
		            value: '2'
		        },
		        {
		        	text: '手机号',
		        	value: '3'
		        }]
		    });
		    
		    $('span#button-search').omButton({
		        icons: {
		            left: '${ctx}/scripts/image/search.png'
		        },
		        width: 70
		    });
		    
		    $("#dialog-show").omDialog({
		        autoOpen: false,
		        height: 510,
		        width: 830
		    });
		    
		    
		    $("#dialog-active").omDialog({
		        autoOpen: false,
		        height: 450,
		        width: 600
		    });
		});
		
	
		function showResponse(responseText, statusText, xhr, $form) {
			alert(responseText);
		    console.log(responseText);
		    if (responseText.success) {
		        $("#actReturnGuid").text(responseText.result);
		        clearForm();
		        $.omMessageBox.alert({
		            type: 'success',
		            title: '成功',
		            content: responseText.message,
		            onClose: function(v) {
		                $("#dialog-show").omDialog("close");
		                $('#grid').omGrid('reload');
		                clearForm();
		                $.omMessageBox.alert({
	                        title: '提示信息',
	                        content: '如果活动需要报名，请在列表中设置报名表单，若不设置则使用默认设置。'
	                    });
		            }
		        });
		    } else {
		        $.omMessageBox.alert({
		            type: 'error',
		            title: '失败',
		            content: responseText.message
		        });
		    }
		}
		
		function showDialog(val)
		{
			$('#dialog-show').omDialog('open');
			$('#grid2').omGrid('setData','${ctx}/creditactivity/listCreditActive.action?memberGuid='+val);
		}
		
		function showActiveInfo(val)
		{
			$("#dialog-active").omDialog('open');
			$("#dialog-active-frame").attr('src','${ctx}/admin/content/creditactive/qurey.jsp?memberCreditGuid='+val);
		}
		
		function search()
		{
			var condition = $('#search-condition').omCombo("value");
			var searchWord = $('#search-word').val();
			var minCredit = $('#search-min-credit').val();
			var maxCredit = $('#search-max-credit').val();
			var searchCredit = $('#search-credit').val();
			
			$('#grid').omGrid('setData','${ctx}/creditactivity/searchCreditActive.action?searchCredit='+searchCredit+'&condition='+condition+'&search='+encodeURI(searchWord)+'&minCredit='+minCredit+'&maxCredit='+maxCredit);
		}
	</script>
</body>

</html>
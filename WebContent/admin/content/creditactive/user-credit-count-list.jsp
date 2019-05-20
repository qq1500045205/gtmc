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
						<li>&nbsp;&nbsp;销售店：<input id="search-gongzhonghao" /></li>
						<li>&nbsp;&nbsp;参与时间：<input id="startOn" name="startOn" /></li>
						<li>至 <input id="endOn" name="endOn" /></li>
						<li>&nbsp;&nbsp;国家：<input id="search-country" name="oppQuery.status" class="input-text" /></li>
						<li>&nbsp;&nbsp;省：<input id="search-province" name="oppQuery.status" class="input-text" /></li>
						<li>&nbsp;&nbsp;市：<input id="search-city" name="oppQuery.status" class="input-text" /></li>
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
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#grid').omGrid({
		        limit: 12,
		        singleSelect: false,
		        width: '100%',
		        height: 400,
		        showIndex: false,
		        dataSource: "${ctx}/creditactivity/ListMemberCreditCount.action",
		        autoFit: true,
		        colModel: [{
		        	header : '公众号',
		        	name : 'gzhName'
		        	},
			        {
			            header: '活动名称',
			            name: 'actName',
			            align: 'center',
			            width: 150
			        },
			        {
			            header: '活动开始时间',
			            name: 'startOn',
			            align: 'center',
			            width: 150
			        },
			        {
			            header: '活动结束时间',
			            name: 'endOn',
			            align: 'center',
			            width: 150
			        },
			        {
			            header: '活动积分',
			            name: 'actCredit',
			            align: 'center',
			            width: 150
			        },
			        {
			            header: '用户名称',
			            name : 'userName',
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
			            header: '参与时间',
			            name: 'createdOn',
			            align: 'center',
			            width: 150
			        },
			        {
			            header: '手机号码',
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
			            header: '国家',
			            name: 'country',
			            align: 'center',
			            width: 150
			        },
			        {
			            header: '省',
			            name: 'province',
			            align: 'center',
			            width: 150
			        },
			        {
			            header: '市',
			            name: 'city',
			            align: 'center',
			            width: 150
		        }]
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
		            text: '用户名',
		            value: '1'
		        },
		        {
		        	text: '手机号',
		        	value: '2'
		        }]
		    });
		    
		    $('span#button-search').omButton({
		        icons: {
		            left: '${ctx}/scripts/image/search.png'
		        },
		        width: 70
		    });
		    
		    $("#search-gongzhonghao").omCombo({
		    	dataSource: '${ctx}/creditactivity/ListGongzhonghao.action',
		        width: 240,
		        valueField: 'value',
		        editable: false,
		        optionField: function(data, index) {
		            return data.text;
		        },
		        inputField: function(data, index) {
		            return data.text;
		        }
		    });
		    
		    $('#startOn').omCalendar({
	            editable: false
	        });
		    
		    $('#endOn').omCalendar({
	            editable: false
	        });
		});
		
		function search()
		{
			var condition = $('#search-condition').omCombo("value");
			var searchWord = $('#search-word').val();
			var gongzhonghao = $('#search-gongzhonghao').omCombo("value");
			var startOn = $('#startOn').val();
			var endOn = $('#endOn').val();
			var province = $("#search-province").val();
			var city = $("#search-city").val();
			var country = $("#search-country").val();
			/*
			alert(condition);
			alert(searchWord);
			alert(gongzhonghao);
			alert(startOn);
			alert(endOn);
			alert(province);
			alert(city);
			alert(country);
			*/
			$('#grid').omGrid('setData','${ctx}/creditactivity/SearchCreditCount.action?condition='+condition+'&search='+encodeURI(searchWord)+'&startDate='+startOn+'&endDate='+endOn+'&searchGzhGuid='+gongzhonghao+'&searchProvince='+encodeURI(province)+'&searchCity='+encodeURI(city)+'&searchCountry='+encodeURI(country));
		}
	</script>
</body>

</html>
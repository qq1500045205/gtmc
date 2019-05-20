<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>积分活动报名管理统计</title>
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
						<li><input id="search-condition" name="condition" /></li>
						<li><input id="search-word" name="search" class="input-text" /></li>
						<li>&nbsp;&nbsp;参加日期：<input name="startDate" id="startDate" class="input-combo"></li>
						<li>至<input name="endDate" id="endDate" class="input-combo"></li>
					</ul>
				</div>

				<div class="srch_line">
					<span id="button-search" onclick="search()">搜索</span>
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
	
	<div id="dialog-detail" title="查看活动">
		<iframe id="dialog-detail-frame" width="550" height="400" frameborder="0"></iframe>
	</div>
	
	<script type="text/javascript">
	var type=null;
	var memberCreditGuid = "${param.memberCreditGuid}";
	$(document).ready(function() {
		$.ajax({
			type : 'POST',
			url : "${ctx}/creditactivity/CreditEnrollControl",
			async : false,
			data : {
				type : "getEnrollView",
				memberCreditGuid : memberCreditGuid
			},
			dataType : "json",
			success : function(data) {
                if (data.success) {
                    //alert(data.result.length);
                    var t1="";
                    var t2="";
                    var t3="";
                    var t4="";
                    var t5="";
                    var t6="";
                    var t7="";
                    var t8="";
                    var t9="";
                    var t10="";
                    switch(data.result.length)
                    {
                    case 1:
                    	t1 = data.result[0].itemName;
                    	break;
                    case 2:
                    	t1 = data.result[0].itemName;
                    	t2 = data.result[1].itemName;
                    	break;
                    case 3:
                    	t1 = data.result[0].itemName;
                    	t2 = data.result[1].itemName;
                    	t3 = data.result[2].itemName;
                    	break;
                    case 4:
                    	t1 = data.result[0].itemName;
                    	t2 = data.result[1].itemName;
                    	t3 = data.result[2].itemName;
                    	t4 = data.result[3].itemName;
                    	break;
                    case 5:
                    	t1 = data.result[0].itemName;
                    	t2 = data.result[1].itemName;
                    	t3 = data.result[2].itemName;
                    	t4 = data.result[3].itemName;
                    	t5 = data.result[4].itemName;
                    	break;
                    case 6:
                    	t1 = data.result[0].itemName;
                    	t2 = data.result[1].itemName;
                    	t3 = data.result[2].itemName;
                    	t4 = data.result[3].itemName;
                    	t5 = data.result[4].itemName;
                    	t6 = data.result[5].itemName;
                    	break;
                    case 7:
                    	t1 = data.result[0].itemName;
                    	t2 = data.result[1].itemName;
                    	t3 = data.result[2].itemName;
                    	t4 = data.result[3].itemName;
                    	t5 = data.result[4].itemName;
                    	t6 = data.result[5].itemName;
                    	t7 = data.result[6].itemName;
                    	break;
                    case 8:
                    	t1 = data.result[0].itemName;
                    	t2 = data.result[1].itemName;
                    	t3 = data.result[2].itemName;
                    	t4 = data.result[3].itemName;
                    	t5 = data.result[4].itemName;
                    	t6 = data.result[5].itemName;
                    	t7 = data.result[6].itemName;
                    	t8 = data.result[7].itemName;
                    	break;
                    case 9:
                    	t1 = data.result[0].itemName;
                    	t2 = data.result[1].itemName;
                    	t3 = data.result[2].itemName;
                    	t4 = data.result[3].itemName;
                    	t5 = data.result[4].itemName;
                    	t6 = data.result[5].itemName;
                    	t7 = data.result[6].itemName;
                    	t8 = data.result[7].itemName;
                    	t9 = data.result[8].itemName;
                    	break;
                    case 10:
                    	t1 = data.result[0].itemName;
                    	t2 = data.result[1].itemName;
                    	t3 = data.result[2].itemName;
                    	t4 = data.result[3].itemName;
                    	t5 = data.result[4].itemName;
                    	t6 = data.result[5].itemName;
                    	t7 = data.result[6].itemName;
                    	t8 = data.result[7].itemName;
                    	t9 = data.result[8].itemName;
                    	t10 = data.result[9].itemName;
                    	break;
                    }
                    
                    
                    
            		$('#grid').omGrid({
            			limit : 12,
            			singleSelect : false,
            			width : '100%',
            			height:400,
            			showIndex : false,
            			dataSource : "${ctx}/creditactivity/CreditEnrollControl?type=getWxMemberCreditManageView&memberCreditGuid="+memberCreditGuid,
            			colModel : [  {
               				header : '活动名称',
               				name : 'actName',
               				align : 'left',
               				width : 60,
               				renderer: function(colValue, rowData, rowIndex) {
               					return '<a href="javascript:;" onclick="openActive('+rowIndex+')">' + colValue + '</a>';
        		            }
            			}, {
               				header : '用户名称',
               				name : 'userName',
               				align : 'left',
               				width : 60
            			}, {
               				header : '用户昵称',
               				name : 'nickName',
               				align : 'left',
               				width : 60
            			}, {
               				header : '手机号码',
               				name : 'userTel',
               				align : 'left',
               				width : 60
            			},{
               				header : '参加时间',
               				name : 'createdOn',
               				align : 'left',
               				width : 60
            			},{
               				header : t1,
               				name : 't1',
               				align : 'left',
               				width : 60,
               				renderer : function(v, rowData , rowIndex) {   
                                return '<span style="color:blue">'+v+'</span>';  
                            }
            			}, {
            				header : t2,
            				name : 't2',
            				align : 'left',
            				width : 60,
               				renderer : function(v, rowData , rowIndex) {   
                                return '<span style="color:blue">'+v+'</span>';  
               				}
            			}, {
            				header : t3,
            				name : 't3',
            				align : 'left',
            				width : 60,
               				renderer : function(v, rowData , rowIndex) {   
                                return '<span style="color:blue">'+v+'</span>';  
               				}
            			}, {
            				header : t4,
            				name : 't4',
            				align : 'left',
            				width : 60,
               				renderer : function(v, rowData , rowIndex) {   
                                return '<span style="color:blue">'+v+'</span>';  
               				}
            			}, {
            				header : t5,
            				name : 't5',
            				align : 'left',
            				width : 60,
               				renderer : function(v, rowData , rowIndex) {   
                                return '<span style="color:blue">'+v+'</span>';  
               				}
            			}, {
            				header : t6,
            				name : 't6',
            				align : 'left',
            				width : 60,
               				renderer : function(v, rowData , rowIndex) {   
                                return '<span style="color:blue">'+v+'</span>';  
               				}
            			}, {
            				header : t7,
            				name : 't7',
            				align : 'left',
            				width : 60,
               				renderer : function(v, rowData , rowIndex) {   
                                return '<span style="color:blue">'+v+'</span>';  
               				}
            			},   {
            				header : t8,
            				name : 't8',
            				align : 'left',
            				width : 60,
               				renderer : function(v, rowData , rowIndex) {   
                                return '<span style="color:blue">'+v+'</span>';  
               				}
            			},{
            				header : t9,
            				name : 't9',
            				align : 'left',
            				width : 60,
               				renderer : function(v, rowData , rowIndex) {   
                                return '<span style="color:blue">'+v+'</span>';  
               				}
            			},  {
            				header : t10,
            				name : 't10',
            				align : 'left',
            				width : 60,
               				renderer : function(v, rowData , rowIndex) {   
                                return '<span style="color:blue">'+v+'</span>';  
               				}
            			}],
            			autoFit : true
            		});

            		/*$('#buttonbar').omButtonbar({
            			btns : [ {
            				label : "到处",
            				id : "button-add",
            				icons : {
            					left : '${ctx}/scripts/image/add.png'
            				},
            				onClick : function() {
            					
            				}
            			}]
            		});*/
            		
            		$("#search-panel").omPanel({
            			title : "高级搜索",
            			collapsed : false,
            			collapsible : false
            		});

            		$("#search-condition").omCombo({
            			width : 100,
            			value : '0',
            			editable : false,
            			dataSource : [ {
            				text : '查询条件',
            				value : '0'
            			}, {
            				text : '用户名称',
            				value : '1'
            			}, {
            				text : '用户昵称',
            				value : '2'
            			}, {
            				text : '手机号码',
            				value : '3'
            			}]
            		});
            		
            		$('#startDate').omCalendar({
        	            editable: false
        	        });
            		
            		$('#endDate').omCalendar({
        	            editable: false
        	        });
            		
            		$("#dialog-detail").omDialog({
        		        autoOpen: false,
        		        width: 650,
        		        height: 500,
        		        modal: true
        		    });
            		
            		$('span#button-search').omButton({
            			icons : {
            				left : '${ctx}/scripts/image/search.png'
            			},
            			width : 70,
            			onClick : function(event) {

            			}
            		});
                } else {
                	alert(data);
                }
            }
		});
	});
	
	function openActive(rowIndex){
		var data = $('#grid').omGrid('getData');
		var obj = data.rows[rowIndex];
		//alert(obj.memberCreditGuid);
		//window.location.href = "${ctx}/admin/content/creditactive/qurey.jsp?memberCreditGuid=" + obj.memberCreditGuid;
		$("#dialog-detail").omDialog("open");
		$("#dialog-detail-frame").attr("src","${ctx}/admin/content/creditactive/qurey.jsp?memberCreditGuid=" + obj.memberCreditGuid);
	}
	
	function search()
	{
		var condition = $("#search-condition").omCombo("value");
		var search = $("#search-word").val();
		var startDate = $('input[name=startDate]').val();
		var endDate = $('input[name=endDate]').val();
		var link = "${ctx}/creditactivity/CreditEnrollSearchByCondition.action?condition="+condition+"&search="+search+"&startDate="+startDate+"&endDate="+endDate+"&memberCreditGuid="+"${param.memberCreditGuid}";
		$("#grid").omGrid('setData',link);
	}
</script>
</body>
</html>
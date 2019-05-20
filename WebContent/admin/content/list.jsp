<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模块设置</title>
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
<script type="text/javascript"
	src="${ctx }/scripts/ui/om-numberfield.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-calendar.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-sort.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-dialog.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/themes/default/om-all.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/themes/default/om-grid-rowexpander.css" />
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

.popwin {
	border: solid 1px #ccc;
	margin: 10px;
	background-color: #fff;
	padding: 5px;
	border-radius: 5px;
	float: left;
	width: 720px;
	color: #666;
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
#search-panel .input-text {
    border: 1px solid #6D869E;
    height: 18px;
    vertical-align: middle;
    width: 137px;
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

</style>
<script type="text/javascript">
	$(document).ready(function() {

		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height:400,
			showIndex : false,
			dataSource : "${ctx}/ajax/griddata.action?type=module",
			colModel : [ {
				header : '模块名称',
				name : 'moduleName',
				align : 'left',
				width : 130,
				renderer : function(colValue, rowData, rowIndex) {
                   if (rowData == '电信') {
                       return '<span style="color:red;"><b>' + colValue + '</b></span>';
                   } 
                   return colValue;
               }
			}, {
				header : '模块标识',
				name : 'moduleTitle',
				align : 'left',
				width : 100,
				sort : 'clientSide'
			}, {
				header : '模块描述',
				name : 'description',
				align : 'left',
				width : 200,
				sort : 'clientSide'
			}, {
				header : '功能介绍',
				name : 'ability',
				align : 'left',
				width : 200
			}, {
				header : '预览',
				name : 'moduleGuid',
				align : 'left',
				width : 'autoExpand', 
          	 	renderer:function(colValue, rowData, rowIndex){
               		return '<a href="${ctx}/template/generator.action">预览</a>';
         		}  
			} ],
			autoFit : true
		});

		$('#buttonbar').omButtonbar({
			btns : [ {
				label : "添加内容",
				id : "button-add",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					//$("#dialog-add").omDialog("open");
					var selectedRecords = $("#grid").omGrid('getSelections',true);
					
					if(selectedRecords.length != 1 ){
	            		$.omMessageBox.alert({
							type : 'alert',
							title : '提示',
							content : '请选择要添加内容的模块(只能选择一个)！'
						});
	            		return;
	            	}
					
					addModule(selectedRecords[0].moduleGuid, selectedRecords[0].pageCount, selectedRecords[0].modPage);
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
		
		$("#project").omCombo({
			width : 200,
			value : '1',
			editable : false,
			dataSource : [{
				text : '鹭岭',
				value : '1'
			}]
		});
		
		$(".modulePage").omCombo({
			width : 200,
			editable : false,
			dataSource : '${ctx}/ajax/combo?type=wxModulePage',
			valueField : 'pageGuid', 
            editable : false,
            optionField : function(data, index) {
                return data.pageName;
            },
            inputField : function(data, index) {
                return data.pageName; 
            }
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
	
	 function CheckTabsExist(currentTabId)
     {
    	 console.log(currentTabId)
         var total = parent.$('#center-tab').omTabs('getLength');   
         console.log(total);
         for (var i = 0; i < total; i++)
         {
             var tabId = parent.$('#center-tab').omTabs('getAlter', i);                
             if (tabId == currentTabId)
             {
                 return true;
             }                
         }
         return false;
     }
     
  	function addModule(moduleGuid, pageCount, modPage){
  		var tabId = "edit-module-tab-id";
  		if (CheckTabsExist(tabId))
        {
            var index = parent.$("#center-tab").omTabs('getAlter', tabId); 
            parent.$('#center-tab').omTabs('close', index);
        }
       
       	parent.$("#center-tab").omTabs('add',{
	           title : "新增模块内容", 
	           content : "<iframe id='inner-frame' border=0 frameBorder='no'  name='inner-frame' src='${ctx}/web/forwardEditModule?moduleGuid="+moduleGuid+"&pageCount="+pageCount+"&modPage="+modPage+"' width='100%' height='600'></iframe>",
	           closable : true,
	           tabId:tabId
	     	});
        
 	}
	
	function deletePage(th){
		$(th).parent().remove();
	}
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
	
</body>
</html>
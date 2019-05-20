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
<script type="text/javascript" src="${ctx }/scripts/common.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>

<script type="text/javascript" src="${ctx }/scripts/json.js"></script>

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
			limit : 14,
			singleSelect : false,
			width : '100%',
			height:440,
			showIndex : false,
			dataSource : "${ctx}/ajax/griddata.action?type=contentView",
			colModel : [  {
				header : '模块名称',
				name : 'moduleName',
				align : 'left',
				width : 150,
          	 	renderer:function(colValue, rowData, rowIndex){
          	 		if(rowData.contentGuid){
          	 			return '<a href="javascript:;" onclick=addModule("'+rowData.moduleGuid+'","'+rowData.pageCount+'","'+rowData.modPage+'","'+rowData.contentGuid+'","'+rowData.moduleName+'")>'+colValue+'</a>';
          	 		}
          	 		return '<a href="javascript:;" onclick=addModule("'+rowData.moduleGuid+'","'+rowData.pageCount+'","'+rowData.modPage+'")>'+colValue+'</a>';
         		}  
			}, {
				header : '内容标题',
				name : 'contentTitle',
				align : 'left',
				width : 150
			}, {
				header : '页面数',
				name : 'pageCount',
				align : 'left',
				width : 60,
				sort : 'clientSide'
			}, {
				header : '状态',
				name : 'status',
				align : 'left',
				width : 80,
				sort : 'clientSide' ,
	          	 	renderer:function(colValue, rowData, rowIndex){
	               		if(colValue == 0){
	               			return "未发布";
	               		}else if(colValue == 1){
	               			return "已发布";
	               		}else if(colValue == 2){
	               			return "已删除";
	               		}
	         		}  
			}, {
				header : '创建人',
				name : 'createdBy',
				align : 'left',
				width : 100
			}, {
				header : '创建时间',
				name : 'createdOn',
				align : 'left',
				width : 200
			}, {
				header : '预览',
				name : 'contentPage',
				align : 'left',
				width : 'autoExpand', 
          	 	renderer:function(colValue, rowData, rowIndex){
          	 		if(rowData.contentGuid){
               			return '<a href="javascript:;" onclick = preview("${ctx}/wx/cnthtml/'+colValue+'")>预览</a>';
          	 		}
          	 		return '请设置内容';
         		}  
			} ],
			autoFit : true
		});

		$('#buttonbar').omButtonbar({
			btns : [ {
				label : "删除",
				id : "button-delete",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					//$("#dialog-add").omDialog("open");
					var dels = $("#grid").omGrid('getSelections',true);
					if (dels.length <= 0) {
						$.omMessageBox.alert({
							type : 'alert',
							title : '提示',
							content : '请选择要删除的记录！'
						});
						return;
					}
					
					$.omMessageBox.confirm({
     	                title:'确认删除',
     	                content:'删除后相关的数据也将删除,您确认要删除吗？',
     	                onClose:function(v){
     	                   if(v){
     	                	   var items="";
     	                	   for(var i=0;i<dels.length;i++){
     	                		   items += "item="+dels[i].contentGuid;
     	                		   if(i< dels.length-1){
     	                			   items +="&";
     	                		   }
     	                	   }
     	                	  $.getJSON("${ctx}/ajax/deleteContent?"+items,function(data){
     	                		  if(data.success){
     	                			 $.omMessageBox.alert({
             	                         type:'success',
             	                         title:'成功',
             	                         content:data.message,
             	                         onClose:function(v){
             	                        	$('#grid').omGrid('reload');
             	                         }
             	                     });
     	                		  } else{
     	                			$.omMessageBox.alert({
               	                         type:'error',
               	                         title:'失败',
               	                         content:data.message
               	                     }); 
     	                		  }
     	                	  });
     	                   } 
     	                }
     	            });
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
	 
	 function addModule(moduleGuid, pageCount, modPage,contentGuid,moduleName){
	  		var tabId = "edit-module-tab-id";
	  		if (CheckTabsExist(tabId))
	        {
	            var index = parent.$("#center-tab").omTabs('getAlter', tabId); 
	            parent.$('#center-tab').omTabs('close', index);
	        }
	       if(contentGuid){
	    	 	parent.$("#center-tab").omTabs('add',{
			           title : "编辑模块内容", 
			           content : "<iframe id='inner-frame' border=0 frameBorder='no'  name='inner-frame' src='${ctx}/wx/choiceModules/edit-module-content.jsp?moduleGuid="+moduleGuid+"&pageCount="+pageCount+"&contentGuid="+contentGuid+"&moduleName="+encodeURI(encodeURI(moduleName))+"' width='100%' height='600'></iframe>",
			           closable : true,
			           tabId:tabId
			     	});
	       }else {
	    	 	parent.$("#center-tab").omTabs('add',{
			           title : "新增模块内容", 
			           content : "<iframe id='inner-frame' border=0 frameBorder='no'  name='inner-frame' src='${ctx}/admin1/forwardEditModule?moduleGuid="+moduleGuid+"&pageCount="+pageCount+"&modPage="+modPage+"' width='100%' height='600'></iframe>",
			           closable : true,
			           tabId:tabId
			     	});
	       }
	        
	 	}
     
  	function preview(url){
  		var tabId = "preview-tab-id";
  		if (CheckTabsExist(tabId))
        {
            var index = parent.$("#center-tab").omTabs('getAlter', tabId); 
            parent.$('#center-tab').omTabs('close', index);
        }
       
       	parent.$("#center-tab").omTabs('add',{
	           title : "预览", 
	           content : "<iframe id='inner-frame' border=0 frameBorder='no'  name='inner-frame' src='"+url+"' width='100%' height='600'></iframe>",
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
		<div id="buttonbar" class="om-buttonbar"></div>
		<div style="width: 100%;">
			<table id="grid"></table>
		</div>
	</div>
	
</body>
</html>
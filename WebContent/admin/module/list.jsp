<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head> 
<title>模块设置</title>
<%@ include  file="/common/admin_pre.jsp" %>  
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-sort.js"></script>
<script type="text/javascript" src="${ctx }/scripts/json.js"></script>
 
<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-grid-sort.css" /> 
<!-- view_source_begin --> 
<script type="text/javascript">
	$(document).ready(function() {

		$('#grid').omGrid({
			limit : 14,
			singleSelect : false,
			width : '100%',
			height: 440,
			showIndex : false,
			dataSource : "${ctx}/ajax/griddata.action?type=module",
			colModel : [ {
				header : '模块名称',
				name : 'moduleName',
				align : 'left',
				width : 150
			}, {
				header : '模块描述',
				name : 'description',
				align : 'left',
				width : 200,
				sort : 'clientSide'
			},{
				header : '创建人',
				name : 'createdBy',
				align : 'left',
				width : 100,
				sort : 'clientSide'
			},{
				header : '创建时间',
				name : 'createdOn',
				align : 'left',
				width : 200,
				sort : 'clientSide'
			},{
				header : '修改人',
				name : 'modifyBy',
				align : 'left',
				width : 100,
				sort : 'clientSide'
			},{
				header : '修改时间',
				name : 'modifyOn',
				align : 'left',
				width : 200,
				sort : 'clientSide'
			}],
			autoFit : true
		});

		$('#buttonbar').omButtonbar({
			btns : [ {
				label : "新增",
				id : "button-add",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					//$("#dialog-add").omDialog("open");
					addModule();
				}
			}, {
				label : "删除",
				id : "button-delete",
				icons : {
					left : '${ctx}/scripts/image/remove.png'
				},
				onClick : function() {
					var dels = $('#grid').omGrid('getSelections', true);
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
      	                		   items += "item="+dels[i].moduleGuid;
      	                		   if(i< dels.length-1){
      	                			   items +="&";
      	                		   }
      	                	   }
      	                	  $.getJSON("${ctx}/ajax/deleteModule?"+items,function(data){
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
			} ]
		});

		$("#search-panel").omPanel({
			title : "高级搜索",
			collapsed : false,
			collapsible : true
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
    	 console.log(currentTabId);
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
     
  	function addModule(){
  		var tabId = "add-module-tab-id";
  		if (CheckTabsExist(tabId))
        {
            var index = parent.$("#center-tab").omTabs('getAlter', tabId); 
            parent.$('#center-tab').omTabs('close', index);
        }
       
       	parent.$("#center-tab").omTabs('add',{
	           title : "新增模块", 
	           content : "<iframe id='inner-frame' border=0 frameBorder='no' name='inner-frame' src='${ctx}/admin1/forwardAddModule' width='100%' height='600'></iframe>",
	           closable : true,
	           tabId:tabId
	     	});
        
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
						<li><input class="input-text" /></li>
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
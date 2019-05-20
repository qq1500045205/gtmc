<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/admin_pre.jsp"%> 
<title>菜单设置</title>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-sort.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-drag.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-grid-sort.css" /> 
<!-- view_source_begin --> 
<script type="text/javascript">
	$(document).ready(function() {

		$('#grid').omGrid({
			limit : 18,
			singleSelect : false,
			width : '100%',
			height:540,
			showIndex : false,
			dataSource : "${ctx}/ajax/griddata.action?type=wxmenu",
			colModel : [ {
				header : '名称',
				name : 'menuName',
				align : 'left',
				width : 200,
				  renderer:function(colValue, rowData, rowIndex){
					 return (rowData.parentGuid?"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;":"")+'<a href="javascript:;" onclick=editMenuName("'+rowData.menuGuid+'")>'+colValue+'</a>';
                    }
			}, {
				header : '动作模块类型',
				name : 'menuPath',
				align : 'left',
				width : 200,
				sort : 'clientSide'
			}, {
				header : '模块名称',
				name : 'moduleName',
				align : 'left',
				width : 200,
				sort : 'clientSide'
			}, {
				header : '位置',
				name : 'menuOrder',
				align : 'left',
				width : 80
			}, {
				header : '操作',
				name : 'menuGuid',
				align : 'left',
				width : 'autoExpand', 
          	 	renderer:function(colValue, rowData, rowIndex){
               		return rowData.parentGuid?"":('<a href="javascript:;" onclick=addChildMenu("'+colValue+'")><span style="border:0;border-radius:2px;background:#ddd;padding:3px;color: #fff ">添加二级菜单</span></a>');
         		}  
			} ],
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
					addMenu();
				}
			}, {
				label : "排序调整",
				id : "button-sort",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					$("#sort-dialog").omDialog("open");
					$("#sort-iframe").attr("src","${ctx }/common/forwardSortPage");
				}
			}, {
				label : "删除",
				id : "button-delete",
				icons : {
					left : '${ctx}/scripts/image/remove.png'
				},
				onClick : function() {
					var data = $('#grid').omGrid('getSelections', true);
					if (data.length != 1 ) {
						$.omMessageBox.alert({
							type : 'alert',
							title : '提示',
							content : '请选择要删除的记录(只能选择一条)！'
						});
						return;
					}
					
					$.omMessageBox.waiting({
	 			           title:'请等待',
	 			           content:'服务器正在处理请求...'
	 			       });
					
					$.getJSON("${ctx}/ajax/deleteWxMenu",{menuGuid:data[0].menuGuid},function(data){
						$.omMessageBox.waiting('close');	
						$('#grid').omGrid('reload');
						if(data.success){
							$.omMessageBox.alert({
		                         type:'success',
		                         title:'成功',
		                         content:data.message
		                     });
						}else{
							$.omMessageBox.alert({
		                         type:'error',
		                         title:'失败',
		                         content:data.message
		                     });
						}
					});
					
				}
			}, {
				label : "发布",
				id : "button-publish",
				icons : {
					left : '${ctx}/scripts/image/open-close.png'
				},
				onClick : function() {
					$.omMessageBox.waiting({
	 			          title:'请等待',
	 			           content:'服务器正在处理请求...'
	 			    });
					$.getJSON("${ctx}/ajax/publishMenu",function(data){
						$.omMessageBox.waiting('close');	
						$('#grid').omGrid('reload');
						if(data.success){
							$.omMessageBox.alert({
		                         type:'success',
		                         title:'成功',
		                         content:data.message
		                     });
						}else{
							$.omMessageBox.alert({
		                         type:'error',
		                         title:'失败',
		                         content:data.message
		                     });
						}
					});
				}
			}, {
				label : "暂停发布",
				id : "button-no-publish",
				icons : {
					left : '${ctx}/scripts/image/remove.png'
				},
				onClick : function() {
					var data1 = $('#grid').omGrid('getSelections', true);

				}
			} ]
		});

		$("#sort-dialog").omDialog({
			autoOpen: false,
			minWidth: 730,
			minHeight: 400,
			modal: true
		});
		
		
	});
	
	//修改菜单名称
	function editMenuName(menuGuid){
		 $.omMessageBox.prompt({
             title:'菜单名称',
             content:'请输入新的菜单名称:<br/>',
             onClose:function(value){
            	 if(value===false){ //按了取消或ESC
                     return;
                 }
            	 
                 if(value==''){
                     alert('菜单名称不能为空');
                     return false; //不关闭弹出窗口
                 }
                 
                 $.getJSON("${ctx}/ajax/updateWxMenu",{menuGuid:menuGuid,menuName:value},function(data){
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
         });
		
	}
	
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
     
  	function addMenu(){
  		var tabId = "add-menu-tab-id";
  		if (CheckTabsExist(tabId))
        {
            var index = parent.$("#center-tab").omTabs('getAlter', tabId); 
            parent.$('#center-tab').omTabs('close', index);
        }
       
       	parent.$("#center-tab").omTabs('add',{
	           title : "新增一级菜单", 
	           content : "<iframe id='inner-frame' border=0 frameBorder='no' scrolling='no'  name='inner-frame' src='${ctx}/admin1/forwardAdd?limit=13&start=0' width='100%' height='600'></iframe>",
	           closable : true,
	           tabId:tabId
	     	});
        
 	}
  	function addChildMenu(guid){
  		var tabId = "add-child-menu-tab-id";
  		if (CheckTabsExist(tabId))
        {
            var index = parent.$("#center-tab").omTabs('getAlter', tabId); 
            parent.$('#center-tab').omTabs('close', index);
        }
       
       	parent.$("#center-tab").omTabs('add',{
	           title : "新增二级菜单", 
	           content : "<iframe id='inner-frame' border=0 frameBorder='no' scrolling='no'  name='inner-frame' src='${ctx}/admin1/forwardAddChild?parentGuid="+guid+"&limit=13&start=0' width='100%' height='600'></iframe>",
	           closable : true,
	           tabId:tabId
	     	});
        
 	}
</script>
<!-- view_source_end -->
</head>
<body>
	
	<div id="news">
		<div id="buttonbar" class="om-buttonbar"></div>
		<div style="width: 100%;">
			<table id="grid"></table>
		</div>
	</div>

	<div id="sort-dialog" title="调整菜单排序">
		<iframe src="" id="sort-iframe" style="width: 100%; height: 350px" border=0 frameBorder='no' scrolling='auto'></iframe>
	</div>

</body>
</html>
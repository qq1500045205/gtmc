<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车型管理</title>
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
<script type="text/javascript" src="${ctx }/scripts/ui/om-calendar.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-dialog.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-fileupload.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-grid-rowexpander.css" />	

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/themes/default/om-all.css" />
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
.input-text {
    border: 1px solid #6D869E;
    height: 18px;
    vertical-align: middle;
    width: 200px;
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
.button-css{
	width: 120px;
	height: 35px;
	margin-top: 10px;
	cursor: pointer;
	float: right;
	background-color: #00A1CB;
	border-color: #007998;
	color: white;
	text-shadow: 0 -1px 1px rgba(0, 40, 50, 0.35);
	border-radius: 3px;
}
。element.style {
}
Matched CSS Rules
.om-dialog .om-dialog-title {
float: left;
margin: 0.7em 16px 0.1em 0.5em;
font-size: 16px;
color: #999;
}
.om-dialog .om-dialog-title {
	margin:0 0 0 10px;
}
.color_red{
	color:red;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height:400,
			showIndex : true,
			dataSource : "${ctx}/car/getCarModelList",
			colModel : [ {
				header : '车型名称',
				name : 'carName',
				align : 'left',
				width : 100,
				renderer:function(colValue, rowData, rowIndex){//dataSource的action对应的结果
					return '<a href="#" onclick=addCarSfx("'+rowData.carTypeGuid+'")>'+rowData.carTypeTitle+'</a>';
         		} 
			}, {
				header : '最高价格(万)',
				name : 'carMinPrice',
				align : 'left',
				width : 100,
				renderer:function(colValue, rowData, rowIndex){//dataSource的action对应的结果
               		return rowData.carTypeMaxprice;
         		}
			}, {
				header : '最低价格(万)',
				name : 'carMaxPrice',
				align : 'left',
				width : 100,
				renderer:function(colValue, rowData, rowIndex){//dataSource的action对应的结果
               		return  rowData.carTypeMinprice;
         		}
			},{
				header : '创建日期',
				name : 'createTime',
				align : 'left',
				width : 200,
				sort : 'clientSide',
				renderer:function(colValue, rowData, rowIndex){
					return rowData.createdOn;
         		}  
			},{
				header : '查看详情',
				name : 'carTypeGuid',
				align : 'left',
				width : 200,
				sort : 'clientSide',
				renderer:function(colValue, rowData, rowIndex){
               		return '<a href="#" onclick=viewCarModel("'+rowData.carTypeGuid+'")>查看</a>';
         		}
			}],
			autoFit : true
		});

		$('#buttonbar').omButtonbar({
			btns : [ {
				label : "添加",
				id : "button-add",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					$("#dialog").omDialog('open');
				}
			},
 			{separtor:true},
 	        {label:"删除",
 			 id:"button-remove",
 		     disabled :  false,
 			 icons : {left : '${ctx}/scripts/image/remove.png'},
 	 		 onClick:function(){
 	 			var dels = $('#grid').omGrid('getSelections',true);
 	         	if(dels.length <= 0 ){
 	         		 $.omMessageBox.alert({
 	                    type:'alert',
 	                    title:'提示',
 	                    content:'请选择删除的记录！'
 	                });
 	         		return;
 	         	}
 	         	 $.omMessageBox.confirm({
 	                title:'确认删除',
 	                content:'您确定要删除？<p style="color:red">注意：删除车型信息后,对应的型号等信息也将被删除,不可恢复,请谨慎操作。<p>',
 	                onClose:function(v){
 	                   if(v){
 	                	   var items="";
 	                	   for(var i=0;i<dels.length;i++){
 	                		   items += "items="+dels[i].carTypeGuid;
 	                		   if(i< dels.length-1){
 	                			   items +="&";
 	                		   }
 	                	   }
 	                	  $.getJSON("${ctx}/car/deleteCarModel?"+items,function(data){
 	                		  if(data.success){
 	                			 $.omMessageBox.alert({
         	                         type:'success',
         	                         title:'成功',
         	                         content:data.message,
         	                         onClose:function(v){
         	                        	$('#grid').omGrid('deleteRow',dels);
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
 	        },{separtor:true},{
 	        	label : "导入",
				id : "button-import",
				icons : {
					left : '${ctx}/scripts/image/import.png'
				},
				onClick : function() {
					/* $.getJSON("${ctx}/car/getCarModelListCount",function(data){
						console.log(data);
						if (data.success) {
							if (data.result==0) {
								importCarTypes();
							} else {
								alert("导入前请先清空车型数据！");
							}
						} else {
							alert("只有DLR店才能导入车型数据！");
						}
					}); */
					if (confirm("不能导入重复的车型！继续导入？") ) {
						importCarTypes();	
					}
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
		
		$( "#dialog").omDialog({
   			autoOpen: false,
   			height:450,
   			width:550,
   			modal: true
   		});
		
		$( "#dialogview").omDialog({
   			autoOpen: false,
   			height:450,
   			width:550,
   			modal: true
   		});
		  
	  	$('#fileExtend').omFileUpload({
 		    action : '${ctx}/common/uploadImage',
 		    swf : '${ctx }/scripts/ui/om-fileupload.swf',
 		  	fileExt : '*.jpg;*.png;',
 		  	fileDesc : 'Image Files',
 		  	sizeLimit : 2000000, //限制大小为1m
 		  	autoUpload: true,
 		  	onComplete : function(ID,fileObj,response,data,event){
 		  		var image = eval('(' + response + ')');
 		  		$(this).parent().find("img").attr("src","${ctx}"+image.filepath);
 		  		$(this).parent().find("input[name=floorPic]").val(image.filepath);
 		  	}
 		  });
	  	
	    var options = {
                beforeSubmit : showRequest,
                success : showResponse
            };
        
        $('#addFloorForm').submit(function() {
            $(this).omAjaxSubmit(options);
            return false; //此处必须返回false，阻止常规的form提交。
        });
	});
	
	//提交前的处理
    function showRequest(formData, jqForm, options) {
        var queryString = $.param(formData);
		 console.log(queryString);
        return true;
    }
	 
    //提交返回的处理
    function showResponse(responseText, statusText, xhr, $form) {
   	  $("#dialog" ).omDialog("close");
        var json = eval(responseText);
        if(json.success){
	       	 $.omMessageBox.alert({
                type:'success',
                title:'成功',
                content:'添加成功'
             });
	       	 $('#grid').omGrid("reload");
	       	 $("#reset").click();
	       	 $("#image").attr("scr","");
        } else {
       	 	$.omMessageBox.alert({
                type:'error',
                title:'失败',
                content:'添加失败'
            });
        }
    }
	
	 function CheckTabsExist(currentTabId)
     {
    	 console.log(currentTabId);
         var total = parent.$('#make-tab').omTabs('getLength');   
         console.log(total);
         for (var i = 0; i < total; i++)
         {
             var tabId = parent.$('#make-tab').omTabs('getAlter', i);                
             if (tabId == currentTabId)
             {
                 return true;
             }                
         }
         return false;
     }
	 
	 function importCarTypes() {
		 var tabId = "mgr-car-import-tab-id";
	  		if (CheckTabsExist(tabId))
	        {
	            var index = parent.$("#make-tab").omTabs('getAlter', tabId); 
	            parent.$('#make-tab').omTabs('close', index);
	        }
	       
	       	parent.$("#make-tab").omTabs('add',{
		           title : "导入车型", 
		           content : "<iframe id='inner-frame' border=0 frameBorder='no'  name='inner-frame' src='import-car-data.jsp' width='100%' height='600'></iframe>",
		           closable : true,
		           tabId:tabId
		     	});
	 }
     
  	function addCarSfx(carTypeGuid){
  		var tabId = "mgr-car-sfx-tab-id";
  		if (CheckTabsExist(tabId))
        {
            var index = parent.$("#make-tab").omTabs('getAlter', tabId); 
            parent.$('#make-tab').omTabs('close', index);
        }
       
       	parent.$("#make-tab").omTabs('add',{
	           title : "型号管理", 
	           content : "<iframe id='inner-frame' border=0 frameBorder='no'  name='inner-frame' src='car-sfx-list.jsp?carTypeGuid="+carTypeGuid+"' width='100%' height='600'></iframe>",
	           closable : true,
	           tabId:tabId
	     	});
        
 	}
  	function viewCarModel(carTypeGuid){
  		$("#dialogview").omDialog('open');
  		$("#dialogIframe").attr("src", "${ctx}/admin/content/carModel/car-module-detail.jsp?carTypeGuid='"+carTypeGuid+"'");
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
	
	<div id="dialog" title="添加车型信息" style="overflow:hidden">
	<iframe src="${ctx}/admin/content/carModel/add-car-module.jsp" height="100%" width="100%">
	</iframe>
    </div>
    
    <div id="dialogview" title="查看车型信息" style="overflow:hidden">
   	 <iframe id="dialogIframe" src="" height="100%" width="100%"></iframe>
    </div>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息规则列表</title>
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
<script type="text/javascript" src="${ctx }/scripts/ui/om-numberfield.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-calendar.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-dialog.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-roweditor.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-rowexpander.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-fileupload.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-grid-rowexpander.css" />	
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

#search-panel li.label {
	padding-left: 5px;
	padding-right: 5px;
	line-height: 25px;
	width: 60px;
}

#search-panel .input-text {
	border: 1px solid #ccc;
	border-radius: 3px;
	background: #fcfcfc;
	padding: 2px;
	height: 21px;
	font-size: 14px;
	vertical-align: middle;
	width: 100px;
}

#search-panel .input-text:focus {
	outline: none;
	background: #ffffff;
}

#search-panel span.om-combo,#search-panel span.om-calendar {
	vertical-align: middle;
}

input#navSearch {
	width: 135px;;
	height: 16px;
	margin-left: 2px;
	background: url("${ctx}/scripts/image/nav-search.png") no-repeat scroll
		0 0 #FFFFFF;
	padding-left: 20px;
	border: 1px solid #99A8BB;
	line-height: 16px;
}
input.error {border: 1px solid red;}
  .om-button {font-size:12px;}
  #nav_cont{width:90%;margin-left:auto;margin-right:auto;}
  input {border: 1px solid #A1B9DF; vertical-align: middle; }
  input:focus{border: 1px solid #3A76C2;}
  .om-calendar input:focus, .om-combo input:focus{border: none;}
  .sex {width: auto;border:none;}
  .sex:focus{border:none;}
  .input_slelct {width: 81%;}
  .textarea_text {border: 1px solid #A1B9DF; height: 40px;width: 90%;}
  table.grid_layout tr td {font-weight: normal; height: 15px; padding: 5px 0; vertical-align: middle;}
  .color_red { color: red; }
  .toolbar { padding: 12px 0 5px;text-align: center; }
  .separator { border-top:1px solid #adadad; height: 2px; line-height: 2px; overflow: hidden; }
  .address {width:445px;}
  .om-span-field input:focus {border:none;}
  .errorImg{background: url("images/msg_bg.png") no-repeat scroll 0 0 transparent;height: 16px;width: 16px;cursor: pointer;}
  .errorMsg{border: 1px solid gray;background-color: #FCEFE3;display: none;position: absolute;margin-top: -18px;margin-left: 18px;}
  .display {color: gray;font-size: 13px;}
.querycontent {color: blue; font-size: 12px; width: 500px;}
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
.item-block{
	width:120px;
	height:100px;
	border: 1px solid #ddd;
	-webkit-border-radius: 4px;
	border-radius: 4px;
	text-align: center;
	line-height:100px; 
}
.item-block:hover{
	background: #ddd;
}

.om-dialog .om-dialog-title{
	margin: 0 0 0 1em;
}
</style>
 <script type="text/javascript">
 	 var rowDatas = [];
     $(document).ready(function() {
         $('#grid').omGrid({
             limit:14,
             singleSelect : false,
             width:'100%',
             height:440,
             autoFit : true,
             showIndex: false,
             dataSource : "${ctx}/ajax/griddata.action?type=wxrule",
             colModel : [ {header : '规则名称', name : 'ruleName', align : 'left',
			           	 	 renderer : function(colValue, rowData, rowIndex) {
			           	 		rowDatas[rowIndex] = rowData;
			                   return '<a href="javascript:;" onclick=editContent('+rowIndex+')>'+colValue+'</a>';
			                }
             			  }, 
                          {header : '关键字', name : 'keyword', align : 'left',
			            	 renderer : function(colValue, rowData, rowIndex) {
			                     var keywords = colValue.split(",");
			                     var result = "";
			                     for(var i=0;i<keywords.length;i++){
			                    	 var item = keywords[i];
			                    	 var type = item.substring(item.length-1); 
			                    	 var keyword  = item.substring(0,item.length-1); 
			                    	 result +="<span style='background:#ddd;margin-left:5px;padding:3px;-webkit-border-radius:2px;border-radius:2px;'>";
			                    	 result += type == "0"? keyword+"(包含)" : keyword+"(完全匹配)";
			                    	 result +="</span>";
			                     }
			                     return result;
			                 }
							},
                          {header : '消息组件类型', name : 'moduleName', align : 'left'}, 
                          {header : '是否默认回复', name : 'isDefault', align : 'left',
                        	  renderer : function(colValue, rowData, rowIndex) {
 			                    if(colValue == 1){
 			                    	return "是";
 			                    }
 			                    return "否";
 			                 }
                          }, 
                          {header : '状态', name : 'status', align : 'left'}]
    		
         });
         
         $('#buttonbar').omButtonbar({
         	btns : [{label:"新增",
         		     id:"button-new" ,
         		     icons : {left : '${ctx}/scripts/image/add.png'},
         	 		 onClick:function(){
         	 			addContent('消息模块','text','${ctx}/admin/rule/add-rule.jsp')
         	 		 }
         			},
         			{separtor:true},
         			{label:"设为默认",
            		     id:"button-default" ,
             		     icons : {left : '${ctx}/scripts/image/open-close.png'},
             	 		 onClick:function(){
             	 			var data = $('#grid').omGrid('getSelections',true);
             	         	if(data.length != 1 ){
             	         		 $.omMessageBox.alert({
             	                    type:'alert',
             	                    title:'提示',
             	                    content:'请选择要设为默认回复的模块(只能选择一个)！'
             	                });
             	         		return;
             	         	}
             	         	 $.omMessageBox.confirm({
             	                title:'确认',
             	                content:'您确定要将该条模块设为回复嘛？<p style="color:red">注意：一个公众号只能设一个默认回复，如果您已经设置了默认回复，则原来的默认回复将被取消。<p>',
             	                onClose:function(v){
             	                   if(v){
             	                	  $.getJSON("${ctx}/ajax/setDefault",
    	                			  	{ruleGuid:data[0].ruleGuid},
    	                			  	function(data){
    	                			  		if(data.success){
    	                			  			$('#grid').omGrid('reload');
    	                			  		}
    	                			  		$.omMessageBox.alert({
    	                 	                    type:'alert',
    	                 	                    title:'提示',
    	                 	                    content:data.message
    	                 	                });
      	                	  			});
             	                   } 
             	                }
             	            });            	 			 
             	 		 }
             		},
         			{separtor:true},
         			{label:"设为欢迎消息",
              		     id:"button-welcome" ,
               		     icons : {left : '${ctx}/scripts/image/open-close.png'},
               	 		 onClick:function(){
               	 			var data = $('#grid').omGrid('getSelections',true);
               	         	if(data.length != 1 ){
               	         		 $.omMessageBox.alert({
               	                    type:'alert',
               	                    title:'提示',
               	                    content:'请选择要设为欢迎的消息规则(只能选择一个)！'
               	                });
               	         		return;
               	         	}
               	         	 $.omMessageBox.confirm({
               	                title:'确认',
               	                content:'您确定要将该条消息设为欢迎消息嘛？<p style="color:red">注意：一个公众号只能设一个欢迎消息，如果您已经设置了欢迎消息，则原来的设置将被取消。<p>',
               	                onClose:function(v){
               	                   if(v){
               	                	  $.getJSON("${ctx}/ajax/welcome",
      	                			  	{ruleGuid:data[0].ruleGuid},
      	                			  	function(data){
      	                			  		if(data.success){
      	                			  			$('#grid').omGrid('reload');
      	                			  		}
      	                			  		$.omMessageBox.alert({
      	                 	                    type:'alert',
      	                 	                    title:'提示',
      	                 	                    content:data.message
      	                 	                });
        	                	  			});
               	                   } 
               	                }
               	            });            	 			 
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
         	                content:'删除后相关的数据也将删除,您确认要删除吗？',
         	                onClose:function(v){
         	                   if(v){
         	                	   var items="";
         	                	   for(var i=0;i<dels.length;i++){
         	                		   items += "item="+dels[i].ruleGuid;
         	                		   if(i< dels.length-1){
         	                			   items +="&";
         	                		   }
         	                	   }
         	                	  $.getJSON("${ctx}/ajax/deleteRule?"+items,function(data){
         	                		  if(data.success){
         	                			 $.omMessageBox.alert({
                 	                         type:'success',
                 	                         title:'成功',
                 	                         content:data.message,
                 	                         onClose:function(v){
                 	                        	//$('#grid').omGrid('deleteRow',dels);
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
         	        }
         	]
         });
         
         $( "#dialog").omDialog({
     			autoOpen: false,
     			height:200,
     			width:410,
     			modal: true
     		});
         
         $("#search-panel").omPanel({
         	title : "搜索",
         	collapsed: false,
         	collapsible:false
         });
       
         $("#startDate").omCalendar();
         $("#endDate").omCalendar();
         $('input[name=userBirth]').omCalendar(); //初始化Calendar
         $('span#button-search').omButton({
       	    icons : {left : '${ctx}/scripts/image/search.png'},width : 70
    	 });
         
         $('#fileExtend').omFileUpload({
 		    action : '${ctx}/web/uploadImage',
 		    swf : '${ctx }/scripts/ui/om-fileupload.swf',
 		  	fileExt : '*.jpg;*.png;',
 		  	fileDesc : 'Image Files',
 		  	sizeLimit : 2000000, //限制大小为1m
 		  	autoUpload: true,
 		  	onComplete : function(ID,fileObj,response,data,event){
 		  		$(this).parent().find("img").attr("src","${ctx}"+response.trim());
 		  		$(this).parent().find("input[name=userPic]").val(response.trim());
 		  	}
 		  });
         
         $("#submit").omButton();
         $("#reset").omButton();
         
         var options = {
                 beforeSubmit : showRequest,
                 success : showResponse
             };
         
         $('#addUserForm').submit(function() {
         	console.log(11);
             $(this).omAjaxSubmit(options);
             return false; //此处必须返回false，阻止常规的form提交。
         });
         
     });
     
     //提交前的处理
     function showRequest(formData, jqForm, options) {
         var queryString = $.param(formData);
         return true;
     }
 	 
     //提交返回的处理
     function showResponse(responseText, statusText, xhr, $form) {
    	 $.omMessageBox.waiting('close');
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
        	 $("#picImg").attr("scr","");
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
     
  	function addContent(title,name,url){
  		var tabId = "add-"+name+"-tab-id";
  		if (CheckTabsExist(tabId))
        {
            var index = parent.$("#center-tab").omTabs('getAlter', tabId); 
            parent.$('#center-tab').omTabs('close', index);
        }
       
       	parent.$("#center-tab").omTabs('add',{
	           title : "新增"+title, 
	           content : "<iframe id='inner-frame' border=0 frameBorder='no' name='inner-frame' src='"+url+"' width='100%' height='600'></iframe>",
	           closable : true,
	           tabId:tabId
	     	});
 	}
  	
	function editContent(index){
  		var tabId = "edit-Rule-tab-id";
  		if (CheckTabsExist(tabId))
        {
            var index = parent.$("#center-tab").omTabs('getAlter', tabId); 
            parent.$('#center-tab').omTabs('close', index);
        }
  		
  		var data = {};
  		data.ruleName = rowDatas[index].ruleName;
  		data.newsGuid = rowDatas[index].newsGuid;
  		data.ruleGuid = rowDatas[index].ruleGuid;
  		
  		saveCookie(data);
       
       	parent.$("#center-tab").omTabs('add',{
	           title : "编辑消息模块", 
	           content : "<iframe id='inner-frame' border=0 frameBorder='no' name='inner-frame' src='${ctx}/admin/rule/edit-rule.jsp' width='100%' height='600'></iframe>",
	           closable : true,
	           tabId:tabId
	     	});
       	
 	}
	
	function saveCookie(data) {
		if (window.localStorage) {
			window.localStorage.result_rule = JSON.stringify(data);
		}
	}

 </script>
<!-- view_source_end -->
</head>
<body>

	<!-- view_source_begin -->
	<div id="search-panel">
   			<div>
	   			<span class="label">关键词：</span>
	   			<input type="text" class="input-text" />
	   			<span class="label">添加时间(S)：</span>
	   			<input id="startDate" name="startDate"/>
	   			<span class="label">添加时间(E)：</span>
	   			<input id="endDate" name="endDate"/>
	   			<span id="button-search">搜索</span>
   			</div>
   		</div>
	
    <div id="news">
        <div id="buttonbar" class="om-buttonbar" style="margin-bottom: 5px;"></div>
    	<table id="grid"></table>
    </div>
   
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理列表</title>
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

.input-text {
	border: 1px solid #ccc;
	border-radius: 3px;
	background: #fcfcfc;
	padding: 2px;
	height: 21px;
	font-size: 14px;
	vertical-align: middle;
	width: 120px;
}

.input-text:focus {
	outline: none!important;
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
.label{
	margin-left:15px;	
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
  .textarea_text { height: 40px;width: 90%; border: 1px solid #ccc;border-radius: 3px;background: #fcfcfc;padding: 2px;
	height: 21px;font-size: 14px;vertical-align: middle;height:50px}
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
</style>
 <script type="text/javascript">
     $(document).ready(function() {
         $('#grid').omGrid({
             limit:14,
             singleSelect : false,
             width:'100%',
             height:440,
             autoFit : true,
             showIndex: false,
             dataSource : "${ctx}/ajax/griddata.action?type=zygw",
             colModel : [ {header : '姓名', name : 'zygwName', align : 'left',width:120,
            	 			renderer:function(colValue, rowData, rowIndex){//dataSource的action对应的结果
 							return '<a href="#" onclick=editZygw("'+rowData.zygwGuid+'")>'+rowData.zygwName+'</a>';
            	 			}
             			  },
                          {header : '编码', name : 'zygwCode', align : 'left',width:80}, 
                          {header : '性别', name : 'zygwSex', align : 'left',width:60}, 
                          {header : '手机号码', name : 'zygwTelephone', align : 'left',width:120}, 
                          {header : '头像', name : 'zygwImage', align : 'left',width:100},
                          {header : '添加人', name : 'createdBy', align : 'left',width:100},
                          {header : '添加时间', name : 'createdOn', align : 'left',width:200},
                          {header : '显示顺序', name : 'zygwSort', align : 'left',width:60}, 
                          {header : '介绍', name : 'zygwInfo', align : 'left',width:'autoExpand'}]
    		
         });
         
         $('#buttonbar').omButtonbar({
         	btns : [{label:"新增",
         		     id:"button-new" ,
         		     icons : {left : '${ctx}/scripts/image/add.png'},
         	 		 onClick:function(){
         	 			$("#dialog").omDialog('open');
         	 		 }
         			}, 
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
         	                content:'删除后不可恢复,您确认要删除吗？',
         	                onClose:function(v){
         	                   if(v){
         	                	   var items="";
         	                	   for(var i=0;i<dels.length;i++){
         	                		   items += "items="+dels[i].zygwGuid;
         	                		   if(i< dels.length-1){
         	                			   items +="&";
         	                		   }
         	                	   }
         	                	  $.getJSON("${ctx}/ajax/deleteGuwen?"+items,function(data){
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
         	        }
         	]
         });
         
         $( "#dialog").omDialog({
     			autoOpen: false,
     			height:420,
     			width:'45%',
     			minWidth: 400,
     			modal: true
     		});
         
         $( "#dialogview").omDialog({
  			autoOpen: false,
  			height:420,
  			width:'45%',
  			minWidth: 400,
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
 		    action : '${ctx}/common/uploadImage',
 		    swf : '${ctx }/scripts/ui/om-fileupload.swf',
 		  	fileExt : '*.jpg;*.png;',
 		  	fileDesc : 'Image Files',
 		  	sizeLimit : 2000000, //限制大小为1m
 		  	autoUpload: true,
 		  	onComplete : function(ID,fileObj,response,data,event){
 		  		var json = JSON.parse(response);
 		  		$(this).parent().find("img").attr("src","${ctx}"+json.filepath);
 		  		$(this).parent().find("input[name=zygwImage]").val(json.filepath);
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
		 console.log(queryString);
         return true;
     }
 	 
     //提交返回的处理
     function showResponse(responseText, statusText, xhr, $form) {
    	 console.log(responseText);
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
     function editZygw(zygwGuid){
   		$("#dialogview").omDialog('open');
   		$("#dialogIframe").attr("src", "${ctx}/admin/content/zygw/zygw-detail.jsp?zygwGuid='"+zygwGuid+"'");
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
	
    <div id="news" >
        <div id="buttonbar" class="om-buttonbar" style="margin-bottom: 5px;"></div>
    	<table id="grid"></table>
    </div>
    <div id="dialogview" title="查看详细信息" style="overflow:hidden">
   	 <iframe id="dialogIframe" src="" height="100%" width="100%"></iframe>
    </div>
    <div id="dialog" title="添加置业顾问" style="overflow:hidden">
       <form id="addUserForm" action="${ctx }/ajax/addGuwen" method="post">
		    <div id="nav_cont">
		        <table class="grid_layout">
		            <tr>
		                <td width="80px"><span class="color_red">*</span>姓名：</td>
		                <td ><input name="zygwName" class="input-text" style="width:150px;"/></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td><span class="color_red">*</span>编码：</td>
		                <td ><input name="zygwCode" class="input-text" style="width:150px;"/></td>
		                <td><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td>性别：</td>
		                <td >
		                    <span>男</span><input type="radio" name="zygwSex" class="sex" value="男"  checked="checked"/>
		                    <span>女</span><input type="radio" name="zygwSex" class="sex" value="女" />
		                </td>
		                 <td><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td>手机号码：</td>
		                <td ><input name="zygwTelephone"  class="input-text" style="width:150px;"/></td>
		                <td><span class="errorImg"></span><span class="errorMsg" class="input-text"></span></td>
		            </tr>
		            <tr>
		                <td>头像：</td>
		                <td >
		                	<img alt="" src="" width="70" height="70" id="picImg"/>
		                	<input id="fileExtend"/>
		                	<input name="zygwImage" type="hidden"/>
		                	
		                </td>
		                <td><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td>自我介绍：</td>
		                <td colspan="4"><label>
		                  <textarea name="zygwInfo" class="textarea_text"  class="input-text"  ></textarea>
		                </label></td>
		            </tr>
		        </table>
		        <div class="toolbar">
		            <button id="submit" type="submit" class="button-css">提交</button> 
		            <button id="reset" type="reset" class="button-css">重置</button>
		        </div>
		    </div>
	    </form>
     </div>
</body>
</html>
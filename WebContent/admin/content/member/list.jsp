<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员信息导入管理</title>
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
<script type="text/javascript" src="${ctx }/scripts/ui/om-dialog.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/ui/om-grid-roweditor.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/ui/om-grid-rowexpander.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-fileupload.js"></script>
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

input.error {
	border: 1px solid red;
}

.om-button {
	font-size: 12px;
}

#nav_cont {
	width: 90%;
	margin-left: auto;
	margin-right: auto;
}

input {
	border: 1px solid #A1B9DF;
	vertical-align: middle;
	width: 90%;
}

input:focus {
	border: 1px solid #3A76C2;
}

.om-calendar input:focus,.om-combo input:focus {
	border: none;
}

.sex {
	width: auto;
	border: none;
}

.sex:focus {
	border: none;
}

.input_slelct {
	width: 81%;
}

.textarea_text {
	border: 1px solid #A1B9DF;
	height: 40px;
	width: 90%;
}

table.grid_layout tr td {
	font-weight: normal;
	height: 15px;
	padding: 5px 0;
	vertical-align: middle;
}

.color_red {
	color: red;
}

.toolbar {
	padding: 12px 0 5px;
	text-align: center;
}

.separator {
	border-top: 1px solid #adadad;
	height: 2px;
	line-height: 2px;
	overflow: hidden;
}

.address {
	width: 445px;
}

.om-span-field input:focus {
	border: none;
}

.errorImg {
	background: url("images/msg_bg.png") no-repeat scroll 0 0 transparent;
	height: 16px;
	width: 16px;
	cursor: pointer;
}

.errorMsg {
	border: 1px solid gray;
	background-color: #FCEFE3;
	display: none;
	position: absolute;
	margin-top: -18px;
	margin-left: 18px;
}

.display {
	color: gray;
	font-size: 13px;
}

.querycontent {
	color: blue;
	font-size: 12px;
	width: 500px;
}

.button-css {
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
             dataSource : "${ctx}/ajax/griddata.action?type=importUser",
             colModel : [ {header : '会员卡号', name : 'huiyuankahao', align : 'left',width:100}, 
                          {header : '客户名称', name : 'kehumingcheng', align : 'left',width:100}, 
                          {header : '手机号码', name : 'createdOn', align : 'left',width:80},
                          {header : '车牌号', name : 'chepaihao', align : 'left',width:80}, 
                          {header : '卡类型', name : 'kaleixing', align : 'left',width:100},
                          {header : '积分', name : 'jifen', align : 'left',width:80},
                          {header : '证件号码', name : 'zhengjianhaoma', align : 'left',width:120},
                          {header : '会员卡销售员', name : 'huiyuankaxiaoshouyuan', align : 'left',width:80}]
    		
         });
         
         $('#buttonbar').omButtonbar({
         	btns : [
         	        {label:"导入",
         			 id:"button-import",
         		     disabled :  false,
         			 icons : {left : '${ctx}/scripts/image/add.png'},
         	 		 onClick:function(){
         	 			$("#import-user" ).omDialog("open");
         	 		 }
         	     }
         	]
          });
         
         $("#search-panel").omPanel({
         	title : "搜索",
         	collapsed: false,
         	collapsible:false
         });
       
         $("#startDate").omCalendar();
         $("#endDate").omCalendar();
         $('span#button-search').omButton({
       	    icons : {left : '${ctx}/scripts/image/search.png'},width : 70
    	 });
         
     	$('#importForm').submit(function() {
            $(this).omAjaxSubmit({
            	 beforeSubmit : showRequest,
                 success : showResponse
            });
            return false; 
            //此处必须返回false，阻止常规的form提交。
         });
     	
    	$("#import-user").omDialog({
			autoOpen: false,
			title:'导入会员信息',
			minWidth: 300,
			minHeight: 150,
			modal: true
		   
		});
		
         
     });
     
   //提交前的处理
     function showRequest(formData, jqForm, options) {
    	 $.omMessageBox.waiting({
             title:'请等待',
             content:'服务器正在处理请求...'
         });
         var queryString = $.param(formData);
         return true;
     }
   	//提交返回的处理
     function showResponse(responseText, statusText, xhr, $form) {
   	 	$.omMessageBox.waiting('close');
   	 	$("#import-user" ).omDialog("close");
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

 </script>
<!-- view_source_end -->
</head>
<body>

	<!-- view_source_begin -->
	<div id="search-panel">
		<div>
			<span class="label">关键词：</span> <input type="text" class="input-text" />
			<span class="label">添加时间(S)：</span> <input id="startDate"
				name="startDate" /> <span class="label">添加时间(E)：</span> <input
				id="endDate" name="endDate" /> <span id="button-search">搜索</span>
		</div>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin-bottom: 5px;"></div>
		<table id="grid"></table>
	</div>

	<div id="import-user">
		<form action="${ctx }/yj/importExcel" method="post" enctype="multipart/form-data" id="importForm">
			<input id="uploadFile"
				name="uploadFile" type="file" value="选择文件">
			<input name="tableName" type="hidden" value="import_user"><br /> 
			<input type="submit" value="开始导入"  class="button-css">
		</form>
	</div>

</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>

<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>新增消息规则</title>

<link rel="shortcut icon" href="${ctx}/template/assets/ico/favicon.png">
<!-- Bootstrap core CSS -->
<link href="${ctx}/template/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/template/dist/css/bootstrap-combined.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="${ctx}/template/dist/css/bootstrap-theme.min.css" rel="stylesheet">

<!-- Placed at the end of the document so the pages load faster -->
<script src="${ctx}/scripts/jquery-2.0.js"></script>
<script src="${ctx}/scripts/kindeditor/kindeditor-min.js"></script>
<script src="${ctx}/template/dist/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-tabs.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-mouse.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-resizable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-draggable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-position.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-dialog.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-messagebox.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-button.js"></script> 

<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />

<style>
body {
	padding:0;
	font-family: "微软雅黑", Arial;
} 

.div_header {
	border-bottom: 1px solid #ccc;
	font-weight: bold;
	font-size: 12px;
	display: block;
	padding: 8px;
	line-height: 35px; 
	background: #f8f8f8;
	box-shadow: 0 1px 2px #ddd;
}

.div_subheader {
	border-bottom: 1px solid #ddd;
	font-weight: bold;
	font-size: 12px;
	display: block;
	padding: 5px;
	line-height: 35px;
	background: #f8f8f8;
	box-shadow: 0 1px 1px #eee;
}

.add_key_btn {
	height: 35px;
}

.span4 {
	border-right: 1px solid #eee;
}
 
.div_body { 
	padding: 0;
	margin: 0;
}
  
img {
	max-width: 720px;
}

label {
	display: inline;
}

.preview {
	background: url(${ctx}/template/image/bg_mobile.png) no-repeat;
	padding-top: 168px;
	padding-left: 30px;
}

.key-item {
	height: 36px;
	padding: 5px;
	border-bottom: 1px solid #ddd;
}

.key-item-btn {
	height: 25px;
	padding: 2px;
	width: 30px;
}

.om-dialog .om-dialog-title {
	margin: 0 0 0 1em;
}

.om-dialog {
	padding: 10px;
	font-family: "微软雅黑", Arail;
}

.om-dialog input {
	margin-left: 10px;
}

.om-dialog button {
	border: 0;
	margin: 5px;
}

.om-messageBox .om-messageBox-content {
	position: relative;
	border: none;
	padding: .5em 1em;
	background: none;
	overflow: none;
	zoom: 1;
}
.div_title{
	border-bottom:1px solid #ccc;
	padding:5px 10px 5px 10px ;
}
</style>

</head>

<body>
<div class="row-fluid div_title">
	<div class="span10">
		<h4>新增消息规则</h4>
	</div>
	<div class="span2" style="padding-top:5px;text-align:right">
		<button id="save_key_content_btn" type="button" class="btn btn-success">保存</button> 
	</div> 
</div>
<div>
	<div class="row-fluid div_header"> 
			<label>消息规则名称：</label><input type="text" name="messageTitle" id="messageTitle" style="display: inline" /> 
	</div>
		<div class="row-fluid div_body">
			<div class="span4">
				<div class="accordion">
					<div style="height: 500px;">
						<div class="div_subheader"">
							<strong>关键字</strong>
							<a id="add_key_btn" onclick="addKey()" class="btn pull-right">+</a> 
						</div>
						<div id="key_container"></div>
					</div>
				</div>
			</div>
			<div class="span8 ">
				<div class="accordion">
					<div style="height: 500px;">
						<div class="" style="height: 36px; margin-bottom: 10px; margin-top: 20px;">
							<strong>选择消息组件</strong>
							<select id="content_name" style="margin-left: 15px;"></select>
						</div>
						<div class="preview">
							<iframe id="content_container" src="" width="264px" height="310px" style="border:0"></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>

	<!-- /container -->
	<div id="add_key_dialog" title="新增关键字">
		<div class="modal-body">
			<div>
				<label>关键字</label>
				<input type="text" name="key" id="key" style="display: inline">
			</div>
			<span style="display: none;">
			<input type="radio" name="key_type" value="0" id="key_type_1" />包含</span>
			<input type="radio" name="key_type" value="1" id="key_type_0" style="margin-left: 30px;" checked="checked" />完全匹配
		</div>

	</div>
	
	<%@ include file="/admin/user/relogin.jsp"%>
	<!-- Bootstrap core JavaScript
    ================================================== -->

	<script type="text/javascript">
	
		// allContent是所有消息模块，使用ajax请求得到
		var allContent;
		$.ajax({
			type : "POST",
			url : "${ctx}/ajax/combo?type=wxMessage",
			async : false,
			data : "code=${param.code}&hash=${param.state}",
			success : function(msg) {
				console.log(msg);
				//allContent = JSON.parse(msg);
				allContent = msg;
			}
		});
		
		console.log(allContent);
		
		// 使用 消息模块的下标
		var indexOfContent = -1;
		// 设置选择模块的选项
		var str = "<option value='-1'></option>";
		for ( var i = 0; i < allContent.length; i++) {
			str += "<option value='"+i+"'>" + allContent[i].newsName
					+ "</option>";
		}
		$("#content_name").html(str);
		$("#content_name").change(function() {
			indexOfContent = $(this).val();
			changeContent();
		});
		// 关键字
		var keys = [];

		// 初始化
		showKeys();
		changeContent();

		// 刷新关键字
		function showKeys() {
			var str = "";
			for ( var i = 0; i < keys.length; i++) {
				var eStr = '<div class="key-item"><span>'
						+ keys[i].name
						+ ' &nbsp;&nbsp;</span><span style="color: rgb(153, 153, 153);">('
						+ (keys[i].type == 0 ? "包含" : "完全匹配")
						+ ')</span><button id="cancel_key_btn_'+i+'" title="'+i+'" type="button" class="btn pull-right key-item-btn">×</button></div>';
				str += eStr;
			}
			$("#key_container").html(str);
			
			for ( var i = 0; i < keys.length; i++) {
				$('#cancel_key_btn_' + i).click(function() {
					var idx = parseFloat($(this).attr("title"));
					 $.omMessageBox.confirm({
			           title:'确认删除',
			           content:'你确定要删除关键字【'+keys[idx].name+'】吗？',
			           onClose:function(v){
			              if(v){
			            	  keys.splice(idx, 1);
								showKeys();
			              }
			           }
			        });
				});
			}
		}
		// 还原对话框中默认值，有bug，待调试
		function refreshDialog() {
			//$("#key_type_0").removeAttr("checked");
			//$("#key_type_1").removeAttr("checked");
			//$("#key_type_1").attr("checked",true);

			$("#key").val("");
		}
		
		// 添加 关键字 按钮
		function addKeyConfirm() {
			var key = {
				name : $("#key").val(),
				type : $('input:radio[name="key_type"]:checked').val(),
			}
			if (key.name.length == 0) {
				$.omMessageBox.alert({
	                    type:'alert',
	                    title:'提示',
	                    content:'关键字不能为空！'
	                });
			} else {
				//检查关键字是否重复
				for(var i=0;i<keys.length;i++){
					if(keys[i].name == key.name){
						$.omMessageBox.alert({
		                    type:'error',
		                    title:'失败',
		                    content:'不能重复添加关键字！'
		                });
						return;
					}
				}
				//检查关键字是否已存在数据库
				$.ajax({
					type : "POST",
					url : "${ctx}/ajax/checkKeyword",
					async : false,
					data : {
						keyword : key.name
					},
					success : function(msg) {
						if (msg.success) {
							keys.push(key);
							refreshDialog();
							showKeys();
							$("#add_key_dialog").omDialog("close");
						} else {
							$.omMessageBox.alert({
			                    type:'error',
			                    title:'失败',
			                    content:msg.message
			                });
						}
					}
				});
			}
		}
		// 切换内容模块，同时切换预览
		function changeContent() {
			console.log(indexOfContent);
			if (indexOfContent > -1) {
				$("#content_container").attr(
						"src",
						"${ctx}/admin1/forwardShowMessage?type="
								+ allContent[indexOfContent].type
								+ "&newsGuid="
								+ allContent[indexOfContent].newsGuid);
			} else {
				$("#content_container").attr("src", "");
			}
		}

		function addKey() {
			$("#add_key_dialog").omDialog("open");
		}

		$("#add_key_dialog").omDialog({
			autoOpen : false,
			height : 210,
			width : 410,
			modal : true,
			buttons : [ {
				text : "保存",
				click : function() {
					addKeyConfirm();
				}
			}, {
				text : "取消",
				click : function() {
					$("#add_key_dialog").omDialog("close");
				}
			} ]

		});

		// 保存按钮事件，具体操作待添加
		$("#save_key_content_btn").click(function() {
			var title = $("#messageTitle").val();
			var data = {};
			data.title = $("#messageTitle").val();
			data.keys = keys;
			
			if(title.trim().length == 0){
				$.omMessageBox.alert({
                    type:'alert',
                    title:'提示',
                    content:'消息规则名称不能为空！'
                });
				return;
			}
			
			if(data.keys.length == 0){
				$.omMessageBox.alert({
                    type:'alert',
                    title:'提示',
                    content:'请设置关键字！'
                });
				return;
			}
			
			if(indexOfContent < 0){
				$.omMessageBox.alert({
                    type:'alert',
                    title:'提示',
                    content:'请选择消息组件！'
                });
				return;
			}
			
			data.newsGuid = allContent[indexOfContent].newsGuid;
			data.type = allContent[indexOfContent].type;
			data.typeName = allContent[indexOfContent].typeName;

			$.ajax({
				type : "POST",
				url : "${ctx}/ajax/addRule",
				async : false,
				data : {
					data : JSON.stringify(data)
				},
				success : function(msg) {
					if(msg.success){
						$.omMessageBox.alert({
		                    type:'success',
		                    title:'成功',
		                    content:msg.message
		                });
						parent.$('#center-tab').omTabs("close");
					}else{
						$.omMessageBox.alert({
		                    type:'error',
		                    title:'失败',
		                    content:msg.message
		                });
					}
				}
			});
			
		});
		
		
	</script>

</body>
</html>

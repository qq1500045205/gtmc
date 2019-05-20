<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>新增微信菜单项</title>

<link rel="shortcut icon" href="${ctx}/template/assets/ico/favicon.png">
<!-- Bootstrap core CSS -->
<link href="${ctx}/template/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/template/dist/css/bootstrap-combined.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="${ctx}/template/dist/css/bootstrap-theme.min.css" rel="stylesheet">

<!-- Placed at the end of the document so the pages load faster -->
<script src="${ctx}/scripts/jquery-2.0.js"></script>
<script src="${ctx}/template/dist/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-fileupload.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-tabs.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-mouse.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-resizable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-draggable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-position.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-messagebox.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-sort.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />

<style>
body {
	padding: 0;
	font-family: "微软雅黑", Arial;
	font-size: 12px;
}
 
.div_title {
	border-bottom: 1px solid #ccc;
	padding:10px;
}

.div_header {
	border-bottom: 1px solid #ddd;
	font-weight: bold;
	font-size: 1em;
	display: block;
	padding: 8px;
	line-height: 30px; 
	background: #f8f8f8;
}

.div_header input {
	margin-left: 10px;
} 
.div_body {
	padding-top: 8px;
	padding-left: 8px;
	padding-right: 8px;
	min-height: 44px;
	height: auto;
	border-bottom: 1px solid #ddd;
}

.div_block {
	padding: 8px;
	height: auto;
	min-height: 44px;
	border: 1px solid #ccc;
	border-radius: 5px;
}  
.nav-tabs {
	margin-top: 10px;
}

img {
	max-width: 720px;
}  
label {
	display: inline;
}

.content_height {
	height: 600px !important;
}

.module {
	background: url(${ctx}/template/image/bg_mobile.png) no-repeat;
	padding-top: 168px;
	padding-left: 30px;
}

.module_span {
	background: #f0f0f0;
	border: 1px solid #ddd;
	padding: 5px 5px 5px 5px;
	border-radius: 4px;
	border: 0;
	font-size: 12px;
	margin: 0 5px 0 5px;
	width: 300px !important;
	float: left;
}

.red {
	color: red;
	padding-left: 10px;
	font-size: 12px;
}

.tab-content {
	height: 350px;
}
</style>

</head>

<body>
	<div>
		<div class="row-fluid">
			<div class="row-fluid div_title">
				<div class="span10">
					<h4>新增微信菜单项</h4>
				</div>
				<div class="span2" style="padding:5px;text-align:right">
					<button id="confirm_btn" type="button" class="btn btn-success">保存</button>
				</div> 
			</div>
			<div>
				<div class="span12">
					<div class="div_header">
						<label>菜单名称</label><input type="text" name="menu_title">
						<span id="msg_info" class="red"></span>
						<span id="parent_menu" class="pull-right"></span>
					</div>
					<div class="div_body row-fluid">
						<span class="span10">
							<span style="float: left; line-height: 30px;">选择对应模块</span>
							<span id="module_span" style="display: none;" class="module_span">
							<span id="module_name"></span>
							 <button id="delete_btn" type="button" class="close" aria-hidden="true">&times;</button>
						</span>
						</span> 
					</div>
				</div>
				<div class="row-fluid div_block">
					<div class="span5 ">
						<div class="tabbable" id="tabs-450915">
							<ul class="nav nav-tabs">
								<li class="active">
									<a id="messageModule" href="#panel-0" data-toggle="tab">消息模块</a></li>
								<li>
									<a id="contentModule" href="#panel-1" data-toggle="tab">内容模块</a>
								</li>
							</ul>
							<input type="hidden" id="menuType" />
							<input type="hidden" id="moduleGuid" /> <input type="hidden" id="namePath" />
							<input type="hidden" id="moduleName" />
							<div class="tab-content" id="menu-content">
								<table id="grid"></table>
							</div>
						</div>
					</div>
					<div class="span7  content_height module">
						<iframe id="iframe" src="" width="264px" height="310px"> </iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<script type="text/javascript">
		var data = ${jsonObject};
		if (data.isParent) {
			$("#parent_menu").text("一级菜单含有二级菜单时，选择模块无效");
			if (data.hasChildren) {
				$("#menu-content").hide();
			}
		} else {
			$("#parent_menu").text("父级菜单：" + data.parentName);
		}

		$("#messageModule").click(function() {
			setData("message");
		});

		$("#contentModule").click(function() {
			setData("content");
		});
		
		$('#grid').omGrid({
			limit : 10,
			singleSelect : true,
			width : '100%',
			height : 350,
			minWidth: 200,
			showIndex : false,
			dataSource : "${ctx}/ajax/griddata.action?type=message",
			colModel : [ {
				header : '名称',
				name : 'value',
				align : 'left',
				width : 'autoExpand'
			} ],
			autoFit : true,
			onRowClick:function(rowIndex,rowData,event){
				var e = rowData;
				console.log(e);
				$("#module_span").show();
				$("#module_name").text(e.type + " ： " + e.value);
				$("#namePath").val(e.type);
				$("#iframe").attr("src", e.src);
				$("#moduleName").val(e.value);
				if("内容模块" == e.type){
					$("#menuType").val('VIEW');
				}else{
					$("#menuType").val('CLICK');
				}
				$("#moduleGuid").val(e.id);
				$("#delete_btn").click(function() {
					$("#module_span").hide();
					$("#iframe").attr("src", "");
				});
		    }
		});
		
		function setData(type){
			$('#grid').omGrid('setData', '${ctx}/ajax/griddata.action?type='+type);
		}

		//str = '';
		// 消息模块
		//str += '<div class="tab-pane active" id="panel-0">';
		//if(data.message){
		//	for (i=0; i<data.message.length; i++) {
		//		str += '<label class="radio">'+
		//					'<input type="radio" alt="CLICK" name="optionsRadios"  lang="'+data.message[i].value+'" title="'+data.message[i].type+'" id="'+data.message[i].id+'" value="'+data.message[i].src+'" onclick="getRadio()">'+
		//					data.message[i].value +
		//				'</label>';
		//	}
		//}
		//str+='<c:if test="${empty(param.parentGuid) }"><a href="${ctx}/web/forwardAdd?limit=13&start=${start>0?start-1:0}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/web/forwardAdd?limit=13&start=${start+1}">下一页</a></c:if>';
		//str+='<c:if test="${!empty(param.parentGuid) }"><a href="${ctx}/web/forwardAddChild?parentGuid=${parentGuid }&limit=13&start=${start>0?start-1:0}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/web/forwardAddChild?parentGuid=${parentGuid }&limit=13&start=${start+1}">下一页</a></c:if>';
		//str += '</div>';
		// 内容模块
		//str += '<div class="tab-pane" id="panel-1">';
		//if(data.content){
		//	for (i=0; i<data.content.length; i++) {
		//		str += '<label class="radio">'+
		//					'<input type="radio" alt="VIEW" name="optionsRadios"  lang="'+data.content[i].value+'"  title="'+data.content[i].type+'" id="'+data.content[i].id+'" value="'+data.content[i].src+'" onclick="getRadio()">'+
		//					data.content[i].value +
		//				'</label>';
		//	}
		//}
		//str+='<c:if test="${empty(param.parentGuid) }"><a href="${ctx}/web/forwardAdd?limit=13&start=${start>0?start-1:0}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/web/forwardAdd?limit=13&start=${start+1}">下一页</a></c:if>';
		//str+='<c:if test="${!empty(param.parentGuid) }"><a href="${ctx}/web/forwardAddChild?parentGuid=${parentGuid }&limit=13&start=${start>0?start-1:0}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/web/forwardAddChild?parentGuid=${parentGuid }&limit=13&start=${start+1}">下一页</a></c:if>';
		//str += '</div>';
		//$("#menu-content").html(str);

		//function getRadio(evt) {
		//	var evt = evt || window.event;
		//	var e = evt.srcElement || evt.target;
		//	$("#module_span").show();
		//	$("#module_name").text(e.title + " ： " + e.lang);
		//	$("#namePath").val(e.title);
		//	$("#iframe").attr("src", e.value);
		//	$("#moduleName").val(e.lang);
		//	$("#menuType").val(e.alt);
		//	$("#moduleGuid").val(e.id);
		//	$("#delete_btn").click(function() {
		//		$("#module_span").hide();
		//		e.checked = false;
		//		$("#iframe").attr("src", "");
		//	});
		//}

		$("#confirm_btn").click(function() {
			var title = $("input[name=menu_title]").val();
			if (!title || title.length == 0) {
				$("#msg_info").text("菜单名称不能为空");
				return;
			}

			if (!data.isParent) {
				if ($("#moduleGuid").val()) {
					$("#msg_info").text("");
				} else {
					$("#msg_info").text("请选择对应模块");
					return;
				}

				//if (title.length > 7) {
				//	$("#msg_info").text("二级菜单名称最多7个字符");
				//	return;
				//} else {
				//	$("#msg_info").text("");
				//}

				$.omMessageBox.waiting({
					title : '请等待',
					content : '服务器正在处理请求...'
				});

				$.getJSON("${ctx}/ajax/addChildWxMenu", {
					menuName : title,
					menuType : $("#menuType").val(),
					parentGuid : "${parentGuid}",
					moduleContentGuid : $("#moduleGuid").val(),
					menuPath : $("#namePath").val(),
					moduleName : $("#moduleName").val()
				}, function(data) {
					$.omMessageBox.waiting('close');
					if (data.success) {
						$.omMessageBox.alert({
		                    type:'success',
		                    title:'成功',
		                    content:data.message
		                });
						parent.$('#center-tab').omTabs("close");
					} else {
						$.omMessageBox.alert({
		                    type:'error',
		                    title:'失败',
		                    content:data.message
		                });
					}
				});
			} else {
				//if (title.length > 4) {
				//	$("#msg_info").text("一级菜单名称最多4个字符");
				//	return;
				//} else {
				//	$("#msg_info").text("");
				//}

				$.omMessageBox.waiting({
					title : '请等待',
					content : '服务器正在处理请求...'
				});
				$.getJSON("${ctx}/ajax/addWxMenu", {
					menuName : title,
					menuType : $("#menuType").val(),
					moduleContentGuid : $("#moduleGuid").val(),
					menuPath : $("#namePath").val(),
					moduleName : $("#moduleName").val()
				}, function(data) {
					$.omMessageBox.waiting('close');
					if (data.success) {
						$.omMessageBox.alert({
		                    type:'success',
		                    title:'成功',
		                    content:data.message
		                });
						parent.$('#center-tab').omTabs("close");
					} else {
						$.omMessageBox.alert({
		                    type:'error',
		                    title:'失败',
		                    content:data.message
		                });
					}
				});
			}

		});
	</script>

</body>
</html>

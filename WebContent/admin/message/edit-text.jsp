<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>编辑文本消息</title>

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
<script src="${ctx}/scripts/kindeditor/lang/zh_CN.js"></script>

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
<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />

<style>
		body {
		  padding: 0;
		  font-family:"微软雅黑"，Arial
		}
		  
		.div_header {
			border-bottom: 1px solid #ddd;
			background:#f8f8f8;
			font-weight: bold;
			font-size: 12px;
			display: block;
			padding: 8px;
			line-height: 25px; 
			box-shadow:0px 1px 1px #eee;
		}
		.div_header input{
			margin-left:10px;
			margin-right:10px;
		} 
		.div_body {
			padding: 8px;
			border-left: 1px solid #ddd;
			border-right: 1px solid #ddd; 
		}
		 
		.padding_style {
			padding: 8px;
		}
		  
		  
		label {
			display: inline;
			line-height:30px;
		}
		 
		.div_title{
			border-bottom:1px solid #ccc;
			padding:5px 10px 5px 10px ;
		}
		.ke-icon-module {
                background-image: "url(${ctx}/scripts/kindeditor/themes/default/default.png)";
                background-position: 0px -1072px;
                width: 16px;
                height: 16px;
        }
	</style>

</head>

<body> 
		<div class="row-fluid div_title">
			<div class="span10">
				<h4>编辑文本消息</h4>
			</div>
			<div class="span2" style="padding-top:5px;text-align:right">
				<button id="save_page_content_btn" type="button" class="btn btn-success">保存</button>
				<button id="cancel_page_content_btn" type="button" class="btn btn-cancel">取消</button>
			</div> 
		</div>
		<div class="row-fluid div_header">
			<label>消息名称</label>
			<input type="text" name="name" style="display: inline" />
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="accordion padding_style">
					<div id="content_container_edit">
						<div>
							<label>文本内容</label>
							<input type="text" name="content" id="content"  style="width: 100%;">
							<!-- <textarea class="form-control" rows="4" name="content" id="content"></textarea> -->
						</div>
						<div style="padding-top:10px">
							<label>动态内容</label>
							<select name="dynamicContent" id="dynamicContent" ></select>
						</div>
					</div>
					
				</div>
			</div>
		</div> 
	<div id="define_url"></div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->


	<script type="text/javascript">
		var data = {};
		if (window.localStorage && window.localStorage.result) {
			data = JSON.parse(window.localStorage.result);
			console.log(data);
		}
		var editor = KindEditor.create("#content", {
			basePath : '${ctx}/scripts/kindeditor/',
			themeType : 'simple',
			langType : 'zh_CN',
			uploadJson : '${ctx}/ajax/kindUploadImage',
			resizeType : 1,
			filterMode:false,//不会过滤HTML代码
			allowImageUpload : true,
			items : [ 'undo', 'redo', '|',  'link', 'unlink','|','module'],
			afterChange : function() {
				this.sync();
			}
		});
		setData();
		function setData() {
			$("input[name=name]").val(data.name);
			editor.html(data.content);
			// TODO $("#dynamicContent").val(data.);
			//$("#content").val(data.content);
			//$("#content").val(data.content);
		}
		
		function getData() {
			data.name = $("input[name=name]").val();
			data.content = $("#content").val();
			data.content = data.content.replace(/<^a\/?[^a^>]*>/g,'')
				.replace(/<\/?p[^>]*>/g,'')
				.replace(/target=['"][^'"]*['"]/ig,"")
				.replace(/&nbsp;/ig,' '); 
			data.dynamicContent = $("#dynamicContent").val();
		}

		$("#save_page_content_btn").click(function() {
			getData();
			$.omMessageBox.waiting({
				title : '请等待',
				content : '服务器正在处理请求...'
			});
			$.getJSON("${ctx}/ajax/editTextNews", {
				data : JSON.stringify(data)
			}, function(data) {
				$.omMessageBox.waiting('close');
				alert(data.message);
				if (data.success) {
					parent.$('#center-tab').omTabs("close");
				}
			});
		});

		$("#cancel_page_content_btn").click(function() {
			parent.$('#center-tab').omTabs("close");
		});
		
		$.ajax({
			 type:"POST",
			 url:"${ctx}/ajax/combo?type=wxDynamicContent",
			 async: false,
			 success:function(msg){
				html ="";
				html +='<option value="">无</option>';
				for(var i=0;i<msg.length;i++) {
					if(msg[i].dynamicContentName != data.dynamicContent){
						html +='<option value="'+msg[i].dynamicContentName+'">'+msg[i].dynamicContentTitle+'</option>';
					} else {
						html +='<option value="'+msg[i].dynamicContentName+'" selected >'+msg[i].dynamicContentTitle+'</option>';
					}
				}
				$("#dynamicContent").html(html);
			 }
		  });
		
	</script>

</body>
</html>

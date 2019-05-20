<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>查看/编辑车型信息</title>

<link rel="shortcut icon" href="${ctx}/template/assets/ico/favicon.png">
<!-- Bootstrap core CSS -->
<link href="${ctx}/template/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/template/dist/css/bootstrap-combined.min.css"
	rel="stylesheet">
<!-- Bootstrap theme -->
<link href="${ctx}/template/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">

<!-- Placed at the end of the document so the pages load faster -->
<script src="${ctx}/scripts/jquery-2.0.js"></script>
<script src="${ctx}/scripts/kindeditor/kindeditor-min.js"></script>
<script src="${ctx}/scripts/kindeditor/lang/zh_CN.js"></script>
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
<script type="text/javascript" src="${ctx }/scripts/ui/om-button.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/themes/default/om-all.css" />

<style>
body {
	padding: 10px;
	font-family: "微软雅黑" ，Arial;
}

.align_horizontal_center {
	text-align: center !important;
}

.align_horizontal_left {
	text-align: left !important;
}

.display_block {
	display: block !important;
}

.item {
	min-height: 75px;
}

.div_header {
	border: 1px solid #ddd;
	font-weight: bold;
	font-size: 14px;
	display: block;
	padding: 8px;
	line-height: 30px;
	background: #f8f8f8;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	box-shadow: 0 1px 1px #eee;
}

.div_header1 {
	font-weight: bold;
	font-size: 1em;
	display: block;
	padding-left: 8px;
	padding-right: 8px;
	height: auto;
}

.div_header2 {
	min-height: 44px;
	font-weight: bold;
	font-size: 1em;
	display: block;
	padding-left: 8px;
	padding-right: 8px;
	height: auto;
}

.div_body {
	padding-top: 2px;
	padding-left: 8px;
	padding-right: 8px;
	border-bottom: 1px solid #ddd;
}

.div_body2 {
	padding: 2px;
	border-left: 1px solid #ddd;
	border-right: 1px solid #ddd;
}

.div_footer {
	padding: 8px;
	border: 1px solid #ddd;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
	background: #f8f8f8;
}

.div_box {
	display: block;
	border-radius: 5px;
	padding: 15px;
	border: 1px solid #ddd;
	box-shadow: 1px 1px 2px #eee, -1px -1px 2px #eee;
}

.div_box .title {
	font-size: 18px;
	line-height: 35px;
	font-weight: bold;
	color: #666;
}

.div_box .date {
	font-size: 12px;
	line-height: 25px;
	color: #999;
}

.box_up {
	height: 88px;
	overflow: hidden;
}

.header_pic {
	position: relative;
	background-color: rgba(0, 0, 0, 0.8);
	text-align: center;
	min-width: 320px;
	min-height: 160px;
	width: 100%;
	overflow: hidden;
	margin-bottom: 10px;
}

img {
	max-width: 720px;
}

.img_caption {
	position: absolute;
	color: #ffffff;
	text-align: left;
	left: 0;
	right: 0;
	bottom: 0;
	padding: 15px;
	background: rgba(0, 0, 0, 0.75);
}

.padding_style {
	padding: 8px;
}

.accordion-group {
	border: 0;
}

.accordion-group input,img {
	margin-left: 5px;
}

.accordion-group textarea,.ke-container {
	margin-top: 15px !important;
}

.accordion-group div {
	margin-bottom: 8px;
}

.align_center {
	text-align: center;
	line-height: 75px;
}

.no_float8 {
	width: 65.81196581196582% !important;
}

.float_right_img {
	float: right;
	background: #ddd;
	overflow: hidden;
}

label {
	display: inline;
}

.ke-icon-module {
	background-image:
		"url(${ctx}/scripts/kindeditor/themes/default/default.png)";
	background-position: 0px -672px;
	width: 16px;
	height: 16px;
}
</style>

</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid div_body2">
			<div class="span6">
				<div class="accordion">
					<div class="accordion-group padding_style"
						id="content_container_edit">
						<div>
							<label>标题</label><input type="text" name="edit_title"
								id="edit_title">
						</div>
						<div style="height: 110px; line-height: 110px;">
							<label>车型图片</label> <img id="edit_preview"
								src="${ctx}/scripts/image/pic.png"
								style="width: 100px; height: 100px; border: 1px solid #ddd;">
							<input id="edit_fileExtend" style="display: inline;"> <input
								type="hidden" name="edit_pic" id="edit_pic"
								style="display: inline">
						</div>
						<div style="height: 110px; line-height: 110px;">
							<label>车型配置图</label> <img id="edit_preview2"
								src="${ctx }/scripts/image/pic.png"
								style="width: 100px; height: 100px; border: 1px solid #ddd;">
							<input id="edit_fileExtend2" style="display: inline;"> <input
								type="hidden" name="edit_pic2" id="edit_pic2"
								style="display: inline">
						</div>
						<div>
							<label>最高价格(万)</label><input type="text"
								name="edit_inputMaxPrice" id="edit_inputMaxPrice"
								style="width: 100%;">
						</div>
						<div>
							<label>最低价格(万)</label><input type="text"
								name="edit_inputMinPrice" id="edit_inputMinPrice"
								style="width: 100%;">
						</div>
						<div>
							<label>显示排序(0表示最前面)</label><input type="text"
								name="edit_inputSortId" id="edit_inputSortId"
								style="width: 100%;" title="0表示最前面" value="0">
						</div>
						<div>
							<label>车型亮点</label><input type="text" name="edit_content"
								id="edit_content" style="width: 100%;">
						</div>
						<div style="color:red">以下数据从G-Cloud中获取，如果不填写，预约数据将无法写入G-Cloud系统。特别注意：凯美瑞 尊瑞，车型名称填写凯美瑞，备注中填写“凯美瑞 尊瑞 ”。</div>
						<div>
							<label>车型编码</label><input type="text" name="edit_carTypeCode"
								id="edit_carTypeCode" style="width: 100%;">
						</div>
						<div>
							<label>车型名称</label><input type="text" name="edit_carTypeName"
								id="edit_carTypeName" style="width: 100%;">
						</div>
						<div>
							<label>车型英文名称</label><input type="text" name="edit_carTypeEn"
								id="edit_carTypeEn" style="width: 100%;">
						</div>
						<div>
							<label>备注</label><input type="text" name="edit_remark"
								id="edit_remark" style="width: 100%;">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid div_footer" style="height: 44px;">
			<div class="span12">
				<span class="pull-right">
					<button id="save_page_content_btn" type="button"
						class="btn btn-success">保存</button>
					<button id="cancel_page_content_btn" type="button"
						class="btn btn-cancel">取消</button>
				</span>
			</div>
		</div>
	</div>
	<div id="define_url"></div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->


	<script type="text/javascript">
		$(function() {
			var data = {};
			if ("${param.carTypeGuid}") {
				data.carTypeGuid = "${param.carTypeGuid}";
			} else {
				$.omMessageBox.alert({
					type : 'alert',
					title : '提示',
					content : '请刷新界面!'
				});
			}
			//alert(data.carTypeGuid);
			$.ajax({
				type : "POST",
				url : "${ctx}/car/queryCarModel",
				data : {
					carModel : JSON.stringify(data)
				},
				success : function(data) {
					$.omMessageBox.waiting('close');
					console.log(data);
					setData(data.result);
				}
			});
			function setData(data) {
				$("#edit_title").val(data.carTypeTitle);
				$("#edit_pic").val(data.carTypeImageUrl);
				var temp = "${ctx}" + data.carTypeImageUrl;
				$("#edit_preview").attr("src", temp);
				$("#edit_pic2").val(data.carTypeParamUrl);
				temp = "${ctx}" + data.carTypeParamUrl;
				$("#edit_preview2").attr("src", temp);
				$("#edit_inputMaxPrice").val(data.carTypeMaxprice);
				$("#edit_inputMinPrice").val(data.carTypeMinprice);
				$("#edit_inputSortId").val(data.carTypeSortid);
				editor.html(data.carTypeMerit);
				$("#edit_content").val(data.carTypeMerit);
				
				//2014-3-17  magenming  add
				$("#edit_carTypeCode").val(data.carTypeCode);
				$("#edit_carTypeName").val(data.carTypeName);
				$("#edit_carTypeEn").val(data.carTypeEn);
				$("#edit_remark").val(data.remark);
				//2014-3-17  magenming  add
				
			}
		});
		var data = {};
		function changeItem() {
			data.title = $("#edit_title").val();
			data.picSrc = $("#edit_pic").val();
			data.paramPicSrc = $("#edit_pic2").val();
			data.inputMaxPrice = $("#edit_inputMaxPrice").val();
			data.inputMinPrice = $("#edit_inputMinPrice").val();
			data.inputSortId = $("#edit_inputSortId").val();
			data.content = $("#edit_content").val();
			
			//2014-3-17  magenming  add
			data.carTypeCode = $("#edit_carTypeCode").val();
			data.carTypeName = $("#edit_carTypeName").val();
			data.carTypeEn = $("#edit_carTypeEn").val();
			data.remark= $("#edit_remark").val();
			//2014-3-17  magenming  add
		}
		$("#save_page_content_btn").click(function() {
			data.carTypeGuid = "${param.carTypeGuid}";
			data.title = $("#edit_title").val();
			data.picSrc = $("#edit_pic").val();
			data.paramPicSrc = $("#edit_pic2").val();
			data.inputMaxPrice = $("#edit_inputMaxPrice").val();
			data.inputMinPrice = $("#edit_inputMinPrice").val();
			data.inputSortId = $("#edit_inputSortId").val();
			data.content = $("#edit_content").val();
			
			//2014-3-17  magenming  add
			data.carTypeCode = $("#edit_carTypeCode").val();
			data.carTypeName = $("#edit_carTypeName").val();
			data.carTypeEn = $("#edit_carTypeEn").val();
			data.remark= $("#edit_remark").val();
			//2014-3-17  magenming  add
			
			console.log(data);
			if (check(data)) {
				$.omMessageBox.waiting({
					title : '请等待',
					content : '服务器正在处理请求...'
				});

				$.ajax({
					type : "POST",
					url : "${ctx}/car/editCarModel",
					data : {
						carModel : JSON.stringify(data)
					},
					success : function(data) {
						$.omMessageBox.waiting('close');
						alert(data.message);
						if (data.success) {
							parent.$("#dialogview").omDialog("close");
							parent.$('#grid').omGrid("reload");
							parent.$("#reset").click();
							parent.$("#image").attr("scr", "");
						}
					}
				});
			}
		});
		function check(data) {
			if (data.title.length == 0) {
				$.omMessageBox.alert({
					type : 'alert',
					title : '提示',
					content : '标题不能为空！'
				});
				return false;
			}
			if (data.picSrc.length == 0) {
				$.omMessageBox.alert({
					type : 'alert',
					title : '提示',
					content : '车型图片未设置！'
				});
				return false;
			}
			if (data.content.length == 0) {
				$.omMessageBox.alert({
					type : 'alert',
					title : '提示',
					content : '车型亮点不能为空！'
				});
				return false;
			}
			return true;
		}
		$("#cancel_page_content_btn").click(function() {
			parent.$('#dialogview').omDialog("close");
		});

		$('#edit_fileExtend').omFileUpload({
			action : '${ctx}/common/uploadImage',
			swf : '${ctx }/scripts/ui/om-fileupload.swf',
			fileExt : '*.jpg;*.png;',
			fileDesc : 'Image Files',
			sizeLimit : 2000000, //限制大小为2m
			autoUpload : true,
			onComplete : function(ID, fileObj, response, data, event) {
				var res = JSON.parse(response);
				$("#edit_preview").attr("src", "${ctx}" + res.filepath);
				$("#edit_pic").val(res.filepath);
			}
		});

		$('#edit_fileExtend2').omFileUpload({
			action : '${ctx}/common/uploadImage',
			swf : '${ctx }/scripts/ui/om-fileupload.swf',
			fileExt : '*.jpg;*.png;',
			fileDesc : 'Image Files',
			sizeLimit : 2000000, //限制大小为2m
			autoUpload : true,
			onComplete : function(ID, fileObj, response, data, event) {
				var res = JSON.parse(response);
				$("#edit_preview2").attr("src", "${ctx}" + res.filepath);
				$("#edit_pic2").val(res.filepath);
			}
		});

		var editor = KindEditor.create("#edit_content", {
			basePath : '${ctx}/scripts/kindeditor/',
			themeType : 'simple',
			langType : 'zh_CN',
			uploadJson : '${ctx}/ajax/kindUploadImage',
			resizeType : 1,
			filterMode : false,//不会过滤HTML代码
			allowImageUpload : true,
			items : [ 'undo', 'redo', '|', 'formatblock', 'fontname',
					'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', '|', 'justifyleft',
					'justifycenter', 'justifyright', 'justifyfull',
					'insertorderedlist', 'insertunorderedlist', 'indent',
					'outdent', '|', 'image', 'h5player', 'table', 'hr',
					'emoticons', 'link', 'unlink', '|', 'preview',
					'plainpaste', '|', 'removeformat', 'module', 'baidumap',
					'|', 'fullscreen' ],

			afterBlur : function() {
				this.sync();
			}
		});
	</script>

</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>添加文章</title>
<link rel="shortcut icon" href="${ctx}/template/assets/ico/favicon.png">
    <!-- Bootstrap core CSS -->
	<link href="${ctx}/template/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/template/dist/css/bootstrap-combined.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="${ctx}/template/dist/css/bootstrap-theme.min.css" rel="stylesheet">
	
	<!-- Placed at the end of the document so the pages load faster -->
    <script src="${ctx}/scripts/jquery-2.0.js"></script>
    <script src="${ctx}/scripts/kindeditor/kindeditor-min.js"></script>
    <script src="${ctx}/scripts/kindeditor/lang/zh_CN.js"></script>
    <script src="${ctx}/template/dist/js/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
    <script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
    <script type="text/javascript" src="${ctx }/scripts/ui/om-combo.js"></script>
    <script type="text/javascript" src="${ctx }/scripts/ui/om-fileupload.js"></script>
    <script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
    <script type="text/javascript" src="${ctx }/scripts/ui/om-tabs.js"></script>
    <script type="text/javascript" src="${ctx }/scripts/ui/om-mouse.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-resizable.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-draggable.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-position.js"></script>
    <script type="text/javascript" src="${ctx }/scripts/ui/om-messagebox.js"></script>
    <script type="text/javascript" src="${ctx }/scripts/ui/om-button.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />
    
    	<style>
		body {
		  padding: 10px;
		  font-family:"微软雅黑"，Arial
		}
	 
		.div_header {
			border: 1px solid #ddd;
			background:#f8f8f8;
			font-weight: bold;
			font-size: 12px;
			display: block;
			padding: 8px;
			line-height: 25px;
			border-top-left-radius:5px;
			border-top-right-radius:5px;
			box-shadow:0px 1px 1px rgba(0,0,0,.05);
		}
		.div_header input{ 
			margin-bottom: 0;
			width:300px;
		} 
		.div_header select{ 
			margin-bottom: 0;
			width:120px;
		} 
		.div_body {
			padding: 8px;
			border-left: 1px solid #ddd;
			border-right: 1px solid #ddd;
			border-bottom:1px solid #eee;
		}

		.div_footer {
			padding: 8px; 
			border-bottom-left-radius:5px;
			border-bottom-right-radius:5px;
			border: 1px solid #ddd;
			border-top:0;
			background:#f8f8f8;
		}
		
		img {
			max-width: 720px;
		} 
		
		.padding_style {
			padding: 8px;
		}
		   
		.accordion{
			margin-bottom:0;
		}
		
		label{
			float:left;
			display:inline;
			font-size: 14px;
			font-weight: normal;
			line-height: 28px;
			margin:0 10px 0 20px;
		}
		.ke-container-simple{
			width:auto!important;
		}
		.ke-edit{
			height:220px!important;
		}
	</style>
</head>
<body>
<div class="container-fluid" style="min-width:600px">
		<div class="row-fluid div_header">
			<label>标题</label><input type="text" name="title" style="display:inline;float:left" />
			<label>所属类别</label>
			<input name="type" id="selectType" style="display:inline">
			
		</div> 
		<div class="row-fluid div_body">
			<div class="span12">
				<div class="accordion">
					<div class=" padding_style" id="content_container_edit">
						<div style="margin-left:10px;">
							<label style="margin-left:0;">正文</label>
							<textarea class="form-control" rows="8" name="content" id="content"></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid div_footer" style="height:44px;">
			<div class="span12">
				<span class="pull-right">
					<button id="save_page_content_btn" type="button" class="btn btn-success">保存</button>
					<button id="cancel_page_content_btn" type="button" class="btn btn-cancel">取消</button>
				</span>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var typeId = "";
		var type = "";
		$("#save_page_content_btn").click(function(){
			//检测标题
			var title = $("input[name=title]").val();
			if(title.length == 0){
				 $.omMessageBox.alert({
	                type:'alert',
	                title:'提示',
	                content:'请输入标题'
	            });
				return ;
			}
			//检测分类 
			if(typeId.length == 0){
				 $.omMessageBox.alert({
	                type:'alert',
	                title:'提示',
	                content:'请选择所属类别'
	            });
				return ;
			}
			
			//检测内容
			var cnt = $("#content").val();
			if(cnt.length == 0){
				 $.omMessageBox.alert({
	                type:'alert',
	                title:'提示',
	                content:'请填写内容'
	            });
				return ;
			}
			
			$.omMessageBox.waiting({
		           title:'请等待',
		           content:'服务器正在处理请求...'
		       });
			
			$.ajax({//ajax
				 type: 'POST',
				 url: "${ctx}/mannual/addMannualtext",
				 dataType:"json",
				 data:{typeGuid:typeId,
					 typeName:type,
					 title:title,
					 content:cnt},
				success:function(data){
					$.omMessageBox.waiting('close');	
					alert(data.message);
					if(data.success){
						parent.$('#center-tab').omTabs("close");
					}
				}
  			});
			
		});
		
		$("#cancel_page_content_btn").click(function(){
			 parent.$('#center-tab').omTabs("close");
		});
		
		//定义文本编辑器
		 var editor = KindEditor.create("#content", {
				basePath : '${ctx}/scripts/kindeditor/',
				themeType : 'simple',
				langType : 'zh_CN',
				uploadJson : '${ctx}/ajax/kindUploadImage',
				resizeType : 1, 
				filterMode:false,//不会过滤HTML代码
				allowImageUpload : true,
				items : [ 'undo', 'redo', '|', 'formatblock', 'fontname', 'fontsize',
						'|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'strikethrough', '|', 'justifyleft', 'justifycenter',
						'justifyright', 'justifyfull', 'insertorderedlist',
						'insertunorderedlist', 'indent', 'outdent', '|', 'image','h5player','table', 'hr', 'emoticons', 'link', 'unlink',
						'|', 'preview', 'plainpaste', '|', 'removeformat', 'module', 'baidumap','|','fullscreen'],
				
				afterBlur : function() {
					this.sync();
				}
			});
		
		 	$("#selectType").omCombo({
				width : 100,
				editable : false,
				valueField: 'typeguid',
				dataSource : '${ctx}/ajax/combo?type=mannualType',
				optionField : function(data, index) {
                   return data.typename;
               },
               inputField : function(data, index) {
                   return data.typename; 
               },
               onValueChange : function(target, newValue, oldValue, event) {
                   typeId = newValue;
                   type = target.val();
               },
			}); 
    </script>
</body>
</html>
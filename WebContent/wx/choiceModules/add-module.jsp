<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>${title}</title>

		<link rel="shortcut icon" href="${ctx}/template/assets/ico/favicon.png">
	    <!-- Bootstrap core CSS -->
		<link href="${ctx}/template/dist/css/bootstrap.min.css" rel="stylesheet">
	    <link href="${ctx}/template/dist/css/bootstrap-combined.min.css" rel="stylesheet">
	    <!-- Bootstrap theme -->
	    <link href="${ctx}/template/dist/css/bootstrap-theme.min.css" rel="stylesheet">
	    
	    <link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />
		
		<!-- Placed at the end of the document so the pages load faster -->
	    <script src="${ctx}/scripts/jquery-2.0.js"></script>
	    <script src="${ctx}/template/dist/js/bootstrap.min.js"></script>
	    
	    <script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
	    <script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
	    <script type="text/javascript" src="${ctx }/scripts/ui/om-mouse.js"></script>
		<script type="text/javascript" src="${ctx }/scripts/ui/om-resizable.js"></script>
		<script type="text/javascript" src="${ctx }/scripts/ui/om-draggable.js"></script>
		<script type="text/javascript" src="${ctx }/scripts/ui/om-position.js"></script>
	    <script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
	    <script type="text/javascript" src="${ctx }/scripts/ui/om-tabs.js"></script>
	    <script type="text/javascript" src="${ctx }/scripts/ui/om-messagebox.js"></script>
		<script type="text/javascript" src="${ctx }/scripts/ui/om-button.js"></script>
	    
		<style>
			body{
				font-family:"微软雅黑",Arial;
				 min-width:320px;
				overflow-x:hidden;
			}
			.padding_style {
				margin: 12px;
				border: 1px solid #ddd;
				-webkit-border-radius: 4px;
				border-radius: 4px;
				overflow: hidden;
				
			}
			.container-fluid{
				padding:0;
			}
			.div_header {
				border-bottom: 1px solid #ccc;
				font-weight: bold;
				font-size: 1em;
				display: block;
				padding: 5px 10px 5px 10px; 
				height: auto;
				background:#f0f0f0;
				box-shadow:1px 1px 2px #eee;
			}
			.div_title{
				border-bottom:1px solid #ccc;
				padding:5px 10px 5px 10px ;
			}
			.row-fluid input{
				margin:0;
				margin-left:5px;
			}
			.div_body {
				padding: 8px;
				min-height: 44px;
				border-bottom: 1px solid #ddd;
			}
			.div_footer {
				padding: 8px;
				min-width: 600px;
				background:#f0f0f0;
			}
			label {
				display:inline;
			}
			iframe{
				border: 1px #ddd solid;
			}
			.iframe{
				border-right:1px solid #ddd;
			}
			
		</style>
		
	</head>
	<body>
		<div class="container-fluid">
			<div class="row-fluid div_title">
				<div class="span11">
					<h4>新增模块</h4>
				</div>
				<div class="span1" style="padding-top:5px;">
					<button id="save_module_btn" type="button" class="btn btn-success">保存</button>
				</div>
				
			</div>
			<div class="row-fluid div_header">
					<div class="span12">
						<label>模块名称</label><input type="text" name="module_title">
					</div>
				</div>
			<div > 
				<div class="row-fluid div_body">
					<div class="span12">
						<label>模块描述</label><input type="text" name="module_description">
					</div>
				</div>
				<div class="row-fluid div_body">
					<div class="span12">
						<label>选择组件页首页</label>
						<select type="module_select" name="iframe_0" id="module_select_0">
						</select>
					</div>
				</div>
				<div class="row-fluid div_body" id="container_iframe_0">
					<div class="span5" style="width:380px">
						<div id="content_container_0"  class="iframe">
							<iframe id="iframe_0" src="" width="370px" height="480px" style="border:1px solid #ddd;"></iframe>
						</div>
					</div>
					<div class="span7">
						<div id="content_container_url_iframe_0"></div>
					</div>
				</div>
				<div id="select-page"> </div>
				<div class="div_footer">
					<button id="add_module_btn" type="button" class="btn btn-default">继续添加</button>
				</div>
			</div>
		</div>
	<script type="text/javascript">
		 
		var width = "720";
		var height = "175";
		var defaultPicSrc = "${ctx}/uploads/1.jpg";
		var defaultPic = "图片";
		var defaultTitle = "标题";
		var defaultContent = "内容";
		
		var module = {};
		var pages = [{
			name: "",
			moduleId: "",
			src: "",
			url: [],
		}];
		/*
		var modules = [{
			index: 0,
			name: "多图文(1大3小)",
			id: "0",
			src: "pageTexts-four-module.jsp",
		},{
			index: 1,
			name: "单图文",
			id: "1",
			src: "pageText-sinple-module.jsp",
		}];
		*/
		var modules = ${jsonObject};
		console.log("modules=", modules);
				
		// 选择模板
		function setModuleSelect(selector, pageIndex) {
			$(selector).change(function(){
				
				var index = $(this).val();
				var frameId = $(this)[0].name;
				
				console.log(index, (index=="-1"));
				if (index=="-1") {
					$("#"+frameId).attr("src", "");	
					
					pages[pageIndex].src = "";	
					pages[pageIndex].name = "";	
					pages[pageIndex].moduleId = "";
					
					$("#container_iframe_"+pageIndex).hide();					
				} else {
					var src = modules[index].src;
					var id = modules[index].id;					
					$("#"+frameId).attr("src", src);
					
					pages[pageIndex].src = modules[index].src;	
					pages[pageIndex].name = modules[index].name;	
					pages[pageIndex].moduleId = modules[index].id;
					
					$("#container_iframe_"+pageIndex).show();
				}
				console.log(pageIndex, index);
				
				$("#"+frameId).load(function(){
					hiddenInputs = $(this).contents().find("input[urlType=url]");
					var str2 = "";
					for (i=0; i<hiddenInputs.length; i++){
						var selector = $(hiddenInputs[i]);
						elementStr = "<div><label>"+selector.attr("title")+"</label> <select type='module_url' name='"+selector.attr("targetId")+"' ></select><span id='"+selector.attr("targetId")+"' style='display:none;'><label>地址：</label><input type='text' name='"+selector.attr("targetId")+"'></div>";
						str2 += elementStr;
					}
					$("#content_container_url_"+modules[index].id).html(str2);
					
					
				
					
				});
				
			});
		}
		// 获取pages，带url
		// 获取pages，带url
		function updatePageUrls() {
			module.title = $("input[name=module_title]").val();
			module.description = $("input[name=module_description]").val();
			
			for (var i=0; i<pages.length; i++) {
				var hiddenInputs = $("#iframe_"+i).contents().find("input[urlType=url]");
				pages[i].url = [];
				for (j=0; j<hiddenInputs.length; j++){
					var iframeId = $("#iframe_"+i).attr("id");
					var selector = $(hiddenInputs[j]);
					var targetId = selector.attr("targetId");
					var value = $('select[name='+iframeId+'-'+targetId+']').val();
					if (value=="link") {
						pages[i].url.push({targetId: targetId, src: $("input[name="+targetId+"]").val(), pageIndex: -1});
					} else if (value=="-1") {
						pages[i].url.push({targetId: targetId, src: "", pageIndex: -1});
					} else {
						pages[i].url.push({targetId: targetId, src: pages[value].src, pageIndex: value});
					}
				}
			}
			module.pages = pages;
		}
		$("#save_module_btn").click(function(){
			updatePageUrls();
			console.log("pages", pages);
			
			if(module.title.length == 0){
				$.omMessageBox.alert({
                    type:'alert',
                    title:'提示',
                    content:'模块名称不能为空！'
                });
				return;
			}
			
			if(module.pages[0].src.length == 0){
				$.omMessageBox.alert({
                    type:'alert',
                    title:'提示',
                    content:'请设置组件页！'
                });
				return;
			}
			
			$.ajax({
				  type: 'POST',
				  url: "${ctx}/ajax/saveModule",
				  data: {result:JSON.stringify(module)},
				  dataType: "json",
				  success:function(data){
					  	alert(data.message);
						if(data.success){
							parent.$('#center-tab').omTabs("close");
						}
				  }
				});
			
		});
		
		// 链接组件页
		function setPageSelectListener(selector) {
			$(selector).change(function(){
				var value = $(this).val();
				
				if (value=="link") {
					$("#"+$(this)[0].name).show();
				} else {
					$("#"+$(this)[0].name).hide();
				}
			});
		}
		function setPageSelect() {
			var urlSelects = $("select[type=module_url]");
			console.log(urlSelects);
			
			for (var i=0; i<urlSelects.length; i++){
				var str2 = "<option value='-1'></option>";
				var pageIndex = $(urlSelects[i]).attr("name").split("-")[0].split("_")[1];
				for (var j=0; j<pages.length; j++) {
					if (pageIndex!=j) {
						if (j>0) {
							str2 += "<option value='"+j+"'>组件页"+j+"："+pages[j].name+"</option>";
						} else {
							str2 += "<option value='"+j+"'>组件页首页："+pages[j].name+"</option>";
						}
					}
				}
				str2 += "<option value='link'>外部链接</option>";
				
				$(urlSelects[i]).html(str2);
				setPageSelectListener($(urlSelects[i]));
			}
		}
		// 设置选项内容
		function setOptions() {
			var selects  = $("select[type=module_select]");
			
			for (var i=0; i<selects.length; i++){
				console.log("selects:",pages[i]);
				var str = "<option value='-1'></option>";
				for (var j=0; j<modules.length; j++) {
					str += "<option value='"+modules[j].index+"' "+(pages[i].moduleId==modules[j].id? "selected" : "")+">"+modules[j].code+"-"+modules[j].name+"</option>"
				}
				$(selects[i]).html(str);
				setModuleSelect(selects[i], i);
			}
		}
		
		// 
		function setDeleteBtn() {
			console.log($("button[btnType=module_delete_btn]"));
			var delBtns  = $("button[btnType=module_delete_btn]");
			for (var i=0; i<delBtns.length; i++){
				if (i<delBtns.length-1) {
					$(delBtns[i]).attr("disabled","disabled"); 
				} else {
					$(delBtns[i]).click(function(){
						pages.pop();
						setPages();
					});
				}
			}
		}
		// 设置页面
		setPages();
		function setPages() {
			var iframeStr = "";
			for (var i=0; i<pages.length; i++) {
				if (i==0) {
					
				} else {
					iframeStr += '<div class="row-fluid div_body">'+
									'<div class="span11">'+
										'<label>选择组件页'+i+'</label>'+
										'<select type="module_select" name="iframe_'+i+'" id="module_select_'+i+'">'+
										'</select>'+
									'</div>'+
									'<div class="span1">'+
										'<button id="delete_module_btn_'+i+'" pageIndex="'+i+'" btnType="module_delete_btn" type="button" class="btn btn-info">删除</button>'+
									'</div>'+
								'</div>';
					iframeStr += '<div class="row-fluid div_body" id="container_iframe_'+i+'">'+
									'<div class="span5" style="width:380px">'+
										'<div id="content_container_'+i+'" class="iframe">'+
											'<iframe id="iframe_'+i+'" src=""  width="370px" height="480px" style="border:1px solid #ddd;"></iframe>'+
										'</div>'+
									'</div>'+
									'<div class="span7">'+
										'<div id="content_container_url_iframe_'+i+'">'+
										'</div>'+
									'</div>'+
								'</div>';
				}
			}
			$("#select-page").html(iframeStr);
			setOptions();
			setDeleteBtn();
			for (var i=0; i<pages.length; i++) {
				console.log($("#container_iframe_"+i));
				if (!pages[i].src || pages[i].src.length==0) {
					$("#container_iframe_"+i).hide();
				} else {
					$("#container_iframe_"+i).show();
				}
				$("#iframe_"+i).attr("src",pages[i].src);
				$("#module_select_"+i).val(pages[i].moduleId);
				if (i<pages.length-1) {
					$("#module_select_"+i).attr("disabled","disabled"); 
				} else {
					$("#module_select_"+i).removeAttr("disabled");
				}
				$("#iframe_"+i).load(function(){
					console.log($(this).attr("id"));
					hiddenInputs = $(this).contents().find("input[urlType=url]");
					var str2 = "";
					for (i=0; i<hiddenInputs.length; i++){
						var selector = $(hiddenInputs[i]);
						elementStr = "<div><label>"+selector.attr("title")+"</label> <select type='module_url' name='"+$(this).attr("id")+"-"+selector.attr("targetId")+"' ></select><span id='"+$(this).attr("id")+"-"+selector.attr("targetId")+"' style='display:none;'><label>地址：</label><input type='text' name='"+selector.attr("targetId")+"'></div>";
						str2 += elementStr;
					}
					$("#content_container_url_"+$(this).attr("id")).html(str2);
					setPageSelect();
				});
			}	
		}
		// 
		$("#add_module_btn").click(function(){
			var blankPage = {
				name: "",
				moduleId: "",
				src: "",
				url: [],
			}
			if (pages[pages.length-1].src && pages[pages.length-1].src.length>0) {
				pages.push(blankPage);
				setPages();
			}
		});
		
    </script>
	</body>
</html>
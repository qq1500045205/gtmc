<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>编辑单图文消息</title>

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
		  padding: 0;
		  font-family:"微软雅黑"，Arial;
		}
		.container-fluid{
			padding:0;
		} 
		.align_horizontal_left {
			text-align: left !important;
		}
		  
		.div_header {
			border-bottom: 1px solid #ccc;
			font-weight: bold;
			font-size: 14px;
			display: block;
			padding: 8px; 
			line-height: 35px;
			background:#f0f0f0; 
			box-shadow:0 1px 1px #eee;
		}   
		 
		
		.div_box {
			display: block;
			border-radius:5px;
			padding:15px;
			border:1px solid #ddd;
			background:#fff;
			box-shadow:1px 1px 2px #eee,-1px -1px 2px #eee; 
		}
		.div_box .title{
			font-size:18px;
			line-height:35px;
			font-weight:bold;
			color:#666;
		}
		.div_box .date{
			font-size:12px;
			line-height:25px;
			color:#999;
		} 
		.header_pic {
			position: relative;
			background-color: rgba(0,0,0,0.8);
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
		#content_container{
			padding:10px; 
		}
		#content_container_edit{
			padding:10px;
		}
		.span7{
			margin-left:0!important;
			border-left:1px solid #ddd;
		}   
		.accordion-group input,img{
			margin-left:15px;
		}
		.accordion-group textarea,.ke-container{
			margin-top:15px!important;
		} 
		.accordion-group div{
			margin-bottom:8px;
		} 
		  
		 label {
			display: inline;
			margin-right:10px;
		}
		.div_header input {
			display: inline;
			margin:0;
		}
		.div_title{
			border-bottom:1px solid #ccc;
			padding: 5px 10px 5px 10px;
		}
		
	</style>
	
</head>

<body>	
	<div class="container-fluid">
	<div class="row-fluid div_title">
			<div class="span10">
				<h4>编辑单图文消息</h4>
			</div>
			<div class="span2" style="padding-top:5px;text-align:right"> 
				<button id="save_page_content_btn" type="button" class="btn btn-success">保存</button>
				<button id="cancel_page_content_btn" type="button" class="btn btn-cancel">取消</button>
			</div> 
		</div>
		<div class="row-fluid div_header">
			<label>消息名称</label>
			<input type="text" name="messageTitle" style="display:inline" onchange="changeItem();"/>
		</div>
		<div class="row-fluid div_body2">
			<div class="span5">
				<div>
					<div class="padding_style page-left" id="content_container"></div> 
				</div>
			</div>
			<div class="span7">
				<div class="accordion">
					<div id="content_container_edit">
						<div>
							<label>标题</label>
							<input type="text" name="title" id="title" onchange="changeItem();">
						</div>
						<div>
							<label>作者</label>
							<input type="text" name="author" id="author" onchange="changeItem();">
						</div>
						<div style="height: 110px;line-height: 110px;">
							<label>封面</label>
							<img id="preview" src="${ctx }/scripts/image/pic.png" style="width:100px;height:100px;border: 1px solid #ddd;">
							<input id="fileExtend" style="display:inline;">
							<input type="hidden" name="pic" id="pic" style="display:inline" onchange="changeItem();">
						</div>
						<div>
							<label>摘要</label>
							<textarea class="form-control" rows="3" name="desc" id="desc" onchange="changeItem();"></textarea>
						</div>
						<div>
							<label>内容</label>
							<input type="text" name="content" id="content" onchange="changeItem();" style="width: 100%;">
						</div>
						<div>
							<label>链接地址</label>
							<select  name="url" id="url"  onchange="changeItem();"></select>
							<input name="outlink" id="outlink" value="" onblur="changeUrl()"/>
						</div>
						<div>
							<label>链接参数</label>
							<select name="urlParam" id="urlParam"></select>
							<select name="urlParamContent" id="urlParamContent"  onchange="changeParamContent();"></select>
							<input name="otherUrlParam" id="otherUrlParam" value="" onblur="changeOtherParam();" style="width:150px;display:none;"/>
						</div>
					</div>
				</div>
			</div>
		</div> 
	</div>
	<div id="define_url">
		
	</div>
	 <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    
    
    <script type="text/javascript">
		var defaultPicSrc = "/scripts/image/pic.png";
		var defaultItem = "图文";
		var defaultPic = "图片";
		var defaultTitle = "标题";
		var defaultDesc= "摘要";
		var defaultContent = "内容";
		var defaultAuthor = "作者";
		var CNTHTML = "/wx/cnthtml/";
		var index = 0;
		var data = {
			name:"",
			picSrc: "",
			title: "",
			desc:"",
			content: "",
			author: "",
			url: ""
		};
		var params = [];
		var keyName;
		
		if (window.localStorage && window.localStorage.result) {
			data = JSON.parse(window.localStorage.result);
			console.log(data);
		}
		
		setData();
		function setData(){
			$("input[name=messageTitle]").val(data.name);
			$("#title").val(data.title);
			$("#author").val(data.author);
			$("#pic").val(data.picSrc);
			$("#preview").attr("src", '${ctx}'+data.picSrc);
			$("#desc").val(data.desc);
			$("#content").val(data.content);
			$("#url").val(data.url);
			assignContent();
		}
		
		assignContent();
		function assignContent() {
			var myDate = new Date();
			var year = myDate.getFullYear(); 
			var month = ("0" + (myDate.getMonth() + 1)).slice(-2); 
			var day = ("0" + myDate.getDate()).slice(-2); 
			var h = ("0" + myDate.getHours()).slice(-2); 
			var m = ("0" + myDate.getMinutes()).slice(-2); 
			var s = ("0" + myDate.getSeconds()).slice(-2); 
			var dateString = year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s; 
			
			var str = '<div class="div_box align_horizontal_left">'+
							'<div id="title_0" class="title">' + (data.title ? data.title : defaultTitle) + '</div>'+
							'<div id="title_0" class="date">' + dateString + '</div>'+
							'<div class="header_pic">'+
								'<img alt="" id="pic_0" src="${ctx}' + (data.picSrc?data.picSrc:defaultPicSrc) + '" style="height:200px;width:320px">'+
							'</div>'+
							'<div id="title_0" style="font-weight: normal;">' + (data.desc ? data.desc : defaultDesc) + '</div>'+
						'</div>';
			$("#content_container").html(str);
		}
		
		$("#outlink").hide();
		function changeUrl(){
			data.url = $("#outlink").val();
		}

		function changeParamContent() {
			data.urlParamContent = $("#urlParamContent").val();
		}
		
		function changeOtherParam() {
			data.otherUrlParam = $("#otherUrlParam").val();
		}
		
		function changeItem(){
			data.name = $("input[name=messageTitle]").val();
			data.title = $("#title").val();
			data.author = $("#author").val();
			data.picSrc = $("#pic").val();
			data.desc = $("#desc").val();
			data.content = $("#content").val();
			data.url = $("#url").val();
			if(data.url == "outlink"){
				$("#outlink").show();
			} else {
				$("#outlink").hide();
			}
			data.urlParamName = keyName;
			data.urlParamContent = $("#urlParamContent").val();
			data.otherUrlParam = $("#otherUrlParam").val();
			assignContent();
		}
		function changeItem2(item){
			data.content = $("#content").val();
		}
		$("#save_page_content_btn").click(function(){
			console.log(data);
			$.omMessageBox.waiting({
		           title:'请等待',
		           content:'服务器正在处理请求...'
		       });
			
			 $.ajax({
				 type:"POST",
				 url:"${ctx}/ajax/editSingleNews",
				 data:{data:JSON.stringify(data)},
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
		
		 $('#fileExtend').omFileUpload({
		    action : '${ctx}/common/uploadImage',
		    swf : '${ctx }/scripts/ui/om-fileupload.swf',
		  	fileExt : '*.jpg;*.png;',
		  	fileDesc : 'Image Files',
		  	sizeLimit : 2000000, //限制大小为2m
		  	autoUpload: true,
		  	onComplete : function(ID,fileObj,response,data,event){
		  		var res = JSON.parse(response);
		  		$("#preview").attr("src","${ctx}"+res.filepath);
		  		$("#pic").val(res.filepath);
		  		changeItem();
		  	}
		  });
		 
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
				
				afterChange : function(){
					this.sync();
					changeItem2();
				}
			});
		 
		 $.ajax({
			 type:"POST",
			 url:"${ctx}/ajax/combo?type=wxModuleContent",
			 async: false,
			 success:function(msg){
				html ="";
				html +='<option value="">图文详细页</option>';
				html +='<option value="outlink">外部链接</option>';
				console.log(data.url);
				for(var i=0;i<msg.length;i++){
					if(msg[i].contentPage){
						var page = CNTHTML + msg[i].contentPage;
						if(page == data.url){
							html +='<option value="'+ CNTHTML +msg[i].contentPage+'" selected="selected">'+msg[i].moduleName+'</option>';
						} else {
							html +='<option value="'+ CNTHTML +msg[i].contentPage+'">'+msg[i].moduleName+'</option>';
						}
					}
				}
				//TODO html +='<option value="link">外部链接</option>';
				$("#url").html(html);
			 }
		  });
		 
		 $.ajax({
			 type:"POST",
			 url:"${ctx}/ajax/getNewsParam",
			 async: false,
			 success:function(res){
				var msg = res.result;
				params = msg;
				console.log("params=", params, data);
				html ="";
				html +='<option value=""></option>';
				for(var i=0;i<msg.length;i++){
					if(msg[i].id){
						if (msg[i].keyName==data.urlParamName) {
							html +='<option value="'+ msg[i].id +'" selected>'+msg[i].paramName+'</option>';
						} else {
							html +='<option value="'+ msg[i].id +'">'+msg[i].paramName+'</option>';
						}
					}
				}
				//TODO html +='<option value="link">外部链接</option>';
				$("#urlParam").html(html);
				changeParam($("#urlParam").val());
				$("#urlParam").change(function(){
					changeParam($("#urlParam").val());
				});
			 }
		  });
		 
		 function changeParam(id) {
			 console.log(id);
			 if (id) {
				 console.log(params,id,typeof(id), params[id-1].keyName);
				 keyName = params[id-1].keyName;
				 data.urlParamName = keyName;
				 $.ajax({
					 type:"POST",
					 url:"${ctx}/ajax/getNewsParamContent",
					 data: {
						key: params[id-1].key,
						value: params[id-1].value,
						tableName: params[id-1].tableName
					 },
					 async: false,
					 success:function(res){
						 console.log(res);
						var msg = res.result;
						html ="";
						html +='<option value=""></option>';
						for(var i=0;i<msg.length;i++){
							if(msg[i]){
								if (msg[i][0]==data.urlParamContent) {
									html +='<option value="'+ msg[i][0] +'" selected>'+msg[i][1]+'</option>';
								} else {
									html +='<option value="'+ msg[i][0] +'">'+msg[i][1]+'</option>';
								}
							}
						}
						//TODO html +='<option value="link">外部链接</option>';
						$("#urlParamContent").html(html);
					 }
				  });
			 } else {
				 html ="";
				 html +='<option value=""></option>';
				 $("#urlParamContent").html(html);
			 }
		 }
    </script>
  
  </body>
</html>

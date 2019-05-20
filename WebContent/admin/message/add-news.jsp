<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>新增多图文消息</title>

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

.item {
	min-height: 95px;
	border-bottom:1px solid #ddd;
	padding:10px;
	background:#fff;
}
.item img{
	margin:0;
}
.item:hover,.header_pic_outer:hover{
	background:#f0f0f0;
}
a:hover{
	text-decoration:none;
}
.div_header {
	border-bottom: 1px solid #ddd;
	font-weight: bold;
	font-size: 14px;
	display: block;
	padding-left: 8px; 
	line-height: 35px;
	background:#f0f0f0; 
	box-shadow:0 1px 1px #eee;
} 
.box {
	display: block;
	padding-top: 8px;
	line-height: 20px;
}
 
.div_box {
	display: block;
	border-radius:5px;
	border:1px solid #ddd;
	background:#fff;
	box-shadow:1px 1px 2px #eee,-1px -1px 2px #eee; 
	background:#f8f8f8;
}
#content_container_edit select,input,img{
	 margin:5px;
	 
}
.span5{
	padding:10px;
}
.span7{
	padding:10px;
	border-left:1px solid #ddd;
	margin:0!important;
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
.header_pic_outer{
	padding:10px;
	border-bottom:1px solid #ddd;
	background:#fff;
	border-top-left-radius:5px;
	border-top-right-radius:5px;
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
.float_right_img {
	float: right;
	background: #ddd;
	overflow: hidden;
}

label {
	display: inline;
	margin-right:10px;
	line-height:30px;
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
		<h4>新增多图文消息</h4>
	</div>
	<div class="span2" style="padding-top:5px;text-align:right">
		<button id="save_page_content_btn" type="button" class="btn btn-success">保存</button>
		<button id="cancel_page_content_btn" type="button" class="btn btn-cancel">取消</button>
	</div> 
</div>
	<div class="container-fluid">
		<div class="row-fluid div_header">
			<label>消息名称</label>
			<input type="text" name="messageTitle" style="display: inline" /> 
		</div>
		<div class="row-fluid">
			<div class="span5">
				<div class="div_box">
					<div id="content_container"></div> 
					<div style="padding:10px;">
						<button id="add_page_content_btn" type="button" class="btn btn-success">新增一项</button>
						<button id="remove_page_content_btn" type="button" class="btn btn-delete">删除一项</button>
					</div>
				</div>
			</div>
			<div class="span7">
				<div >
					<div id="content_container_edit">
						<div>
							<label>标题</label>
							<input type="text" name="title" id="title" onchange="changeItem(this);">
						</div>
						<div>
							<label>作者</label>
							<input type="text" name="author" id="author" onchange="changeItem();">
						</div>
						<div style="height: 110px; line-height: 110px;">
							<label>封面</label>
							<img id="preview" src="${ctx }/scripts/image/pic.png" style="width: 100px; height: 100px; border: 1px solid #ddd;">
							<input id="fileExtend" style="display: inline;">
							<input type="hidden" name="pic" id="pic" style="display: inline" onchange="changeItem();">
						</div>
						<div>
							<label>内容</label>
							<input type="text" name="content" id="content" onchange="changeItem();" style="width: 100%;">
						</div>
						<div>
							<label>链接地址</label>
							<select name="url" id="url" onchange="changeItem();"></select>
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
	<div id="define_url"></div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->


	<script type="text/javascript">
		var defaultPicSrc = "/scripts/image/pic.png";
		var defaultItem = "图文";
		var defaultPic = "图片";
		var defaultTitle = "标题";
		var defaultContent = "内容";
		var defaultAuthor = "作者";
		var CNTHTML = "/wx/cnthtml/";
		var index = 0;
		var data = [{
			picSrc: "",
			title: "",
			content: "",
			author: "",
			url: "",
			outlink: ""
		}];
		var params = [];
		var keyName;
		 
		assignContent();
		function assignContent() {
			var str = "";
			for (var i=0; i<data.length; i++){
				if (i==0) {
					elementStr = '<a href="#" id="item_'+i+'" onclick="itemClick('+i+')">'+
									'<div class="header_pic_outer ">'+
										'<div class="header_pic">'+
											'<img alt="" id="pic_'+i+'" src="${ctx}' + (data[i].picSrc?data[i].picSrc:defaultPicSrc) + '" style="height:200px;width:220px">'+
											'<div class="img_caption">'+
												'<span id="title_'+i+'">' + (data[i].title ? data[i].title : (defaultTitle+i)) + '</span>'+
											'</div>'+
										'</div>'+
									'</div>'+
								'</a>'+
								'<input type="hidden" targetId="item_' + i + '" urlType="url" title="'+ data[i].title + " 跳转至" +'"/>';
				} else {
					elementStr = '<a href="#" id="item_'+i+'" onclick="itemClick('+i+')">'+
									'<div class="item" >'+
										'<span>'+
											'<span id="title_'+i+'">' + (data[i].title ? data[i].title : (defaultTitle+i)) + '</span>'+
										'</span>'+
										'<div class="float_right_img" width="70xp" height="70xp">'+
											'<img alt="" id="pic_'+i+'" src="${ctx}' + (data[i].picSrc?data[i].picSrc:defaultPicSrc) + '" style="height:70px;width:70px">'+
										'</div>'+
									'</div>'+
								'</a>'+
								'<input type="hidden" targetId="item_'+i+'" urlType="url" title="'+ data[i].title + " 跳转至" +'"/>';
				}
				str += elementStr;
			}
			
			$("#content_container").html(str);
			
		}
		
		function showRightContent(){
			$("#title").val(data[index].title);
			$("#author").val(data[index].author);
			$("#pic").val(data[index].picSrc);
			$("#preview").attr("src",data[index].picSrc?("${ctx}"+data[index].picSrc):"${ctx}"+defaultPicSrc);
			//$("#content").val(data[index].content);
			editor.html(data[index].content);
			$("#url").val(data[index].url);
			if(data[index].url == "outlink"){
				$("#outlink").show();
			} else {
				$("#outlink").hide();
			}
			$("#outlink").val(data[index].outlink);
			var t = false;
			for (var i=0; i<params.length; i++) {
				if (params[i].keyName==data[index].urlParamName) {
					$("#urlParam").val(params[i].id);
					changeParam($("#urlParam").val());
					t = true;
				}
			}
			if (!t) {
				$("#urlParam").val("");
				changeParam($("#urlParam").val());
			}
			$("#otherUrlParam").val(data[index].otherUrlParam);
		}
		
		function itemClick(i) {
			index = i;
			showRightContent();
			console.log(data);
		}
		$("#outlink").hide();
		function changeUrl(){
			data[index].outlink = $("#outlink").val();
		}
		
		function changeParamContent() {
			data[index].urlParamContent = $("#urlParamContent").val();
		}
		
		function changeOtherParam() {
			data[index].otherUrlParam = $("#otherUrlParam").val();
		}
		
		function changeItem(item){
			data[index].title = $("#title").val();
			data[index].author = $("#author").val();
			data[index].picSrc = $("#pic").val();
			data[index].content = $("#content").val();
			data[index].url = $("#url").val();
			if(data[index].url == "outlink"){
				$("#outlink").show();
			} else {
				$("#outlink").hide();
			}
			data[index].urlParamName = keyName;
			data[index].urlParamContent = $("#urlParamContent").val();
			data[index].otherUrlParam = $("#otherUrlParam").val();
			assignContent();
		}

		function changeItem2(item){
			data[index].content = $("#content").val();
		}
		
		function  check(data1){
			
			if(data1.title.length == 0){
				 $.omMessageBox.alert({
	                type:'alert',
	                title:'提示',
	                content:'标题不能为空！'
	            });
				return false ;
			}
			if(data1.picSrc.length == 0){
				 $.omMessageBox.alert({
	                type:'alert',
	                title:'提示',
	                content:'封面图片未设置！'
	            });
				return false ;
			}
			if(data1.content.length == 0){
				 $.omMessageBox.alert({
	                type:'alert',
	                title:'提示',
	                content:'内容不能为空！'
	            });
				return false ;
			}
			return true;
		}
		
		$("#save_page_content_btn").click(function(){
			var title = $("input[name=messageTitle]").val();
			if(title.length == 0){
				 $.omMessageBox.alert({
	                type:'alert',
	                title:'提示',
	                content:'消息名称不能为空！'
	            });
				return ;
			}
			var flag = true;
			for(var i=0;i<data.length;i++){
				flag = check(data[i]);
				if(!flag){
					return;
				}
			}
			
			$.omMessageBox.waiting({
		           title:'请等待',
		           content:'服务器正在处理请求...'
		       });
			
			 $.ajax({
				 type:"POST",
				 url:"${ctx}/ajax/addNews",
				 data:{title:$("input[name=messageTitle]").val(),data:JSON.stringify(data)},
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
		
		$("#add_page_content_btn").click(function(){
			var item = {
				picSrc: "",
				title: "",
				content: "",
				author: "",
				url: "",
				outlink: ""
			};
			
			if(data.length <10){
				data.push(item);
				assignContent();
			} else {
				alert("图文消息条数限制在10条以内(如果图文数超过10，则将会无响应)。");
			}
		});
		
		$("#remove_page_content_btn").click(function(){
			if (data.length>1) {
				data.pop();
				assignContent();
			} else {
				alert("至少需要一个图文");
			}
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
						'|', 'preview', 'plainpaste', '|', 'removeformat', 'fullscreen','module' ],
				
				afterChange : function() {
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
				for(var i=0;i<msg.length;i++){
					if(msg[i].contentPage){
						html +='<option value="'+ CNTHTML +msg[i].contentPage+'">'+msg[i].moduleName+'</option>';
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
				html ="";
				html +='<option value=""></option>';
				for(var i=0;i<msg.length;i++){
					if(msg[i].id){
						html +='<option value="'+ msg[i].id +'">'+msg[i].paramName+'</option>';
					}
				}
				//TODO html +='<option value="link">外部链接</option>';
				$("#urlParam").html(html);
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
				 data[index].urlParamName = keyName;
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
								if (msg[i][0]==data[index].urlParamContent) {
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
				 keyName = "";
				 data[index].urlParamName = keyName;
				 html ="";
				 html +='<option value=""></option>';
				 $("#urlParamContent").html(html);
			 }
			 
		 }
    </script>

</body>
</html>

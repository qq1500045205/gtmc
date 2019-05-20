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
	<link href="${ctx}/template/dist/css/bootstrap-combined.min.css"
		rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="${ctx}/template/dist/css/bootstrap-theme.min.css"
		rel="stylesheet">
	
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${ctx}/scripts/jquery-2.0.js"></script>
 	<script src="${ctx}/scripts/kindeditor/kindeditor-min.js"></script>
    <script src="${ctx}/template/dist/js/bootstrap.min.js"></script>
    <script src="${ctx}/scripts/kindeditor/lang/zh_CN.js"></script>
	
	<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-tabs.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-fileupload.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-calendar.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />

<style>
body{
	font-family:"微软雅黑",Arial;
	 min-width:320px;
	overflow-x:hidden;
	padding:0;
}
.padding_style { 
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
	padding-top: 8px;
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
	 border: 1px solid #ccc;
}
#content_container_0{
	border-right:1px solid #ccc;
} 
</style>

</head>
<body> 
	<div class="container-fluid">
		<div class="row-fluid div_title">
				<div class="span11">
					<h4>编辑内容模块</h4>
				</div>
				<div class="span1" style="padding-top:5px;">
					<button id="edit_module_content_btn" type="button" class="btn btn-success pull-right">保存</button>
				</div>
				
		 </div>
		<div class="row-fluid div_header"> 
					 	 
			<div class="span12"  >
				<label>内容模块名称</label>
				<input type="text" name="module_content_title" id="module_content_title" value="${myfn:decode(myfn:decode(param.moduleName))}">
				
			</div>
		</div>
		<div id="add_content_container"></div>
	</div>
	<script type="text/javascript">
	
		var path = "${ctx}/wx/cnthtml/";
		var contentGuid = "${param.contentGuid}";
		var moduleGuid = "${param.moduleGuid}";
		var pageCount = ${param.pageCount};
		
		//$("#save_module_content_btn").attr("disabled","disabled"); 
		
		$(function(){
			$.ajax({
			  type: 'POST',
			  url: "${ctx}/ajax/getModuleContentPages",
			  dataType: "json",
			  success:function(res){
				  
				  if (res.success) {
						contentPages = res.result;
						console.log("contentPages:", contentPages);
						renderPages();
					} else {
						renderPages();
					}
			    }
			});
		});

		function showoptions(i){
			var obj = $('#optionlist_'+i);
			if(obj.css('display') == 'none'){
				obj.css('display', 'block');
			}else{
				obj.css('display', 'none');
			}
		}
		
		function renderPages() {
			// 引入模板页
			var str = "";
			for ( var i = 0; i < pageCount; i++) {
				str += '<div class="row-fluid div_body">'
						+ '<div class="span4 style="width:360px">'
						+ '<div class="padding_style">'
						+ '<iframe id="iframe_'+i+'" src="'+ path + contentGuid + '-' + i +'.jsp" width="330px" height="480px"></iframe>'
						+ '</div>'
						+ '</div>'
						+ '<div class="span8" style="width:auto;min-width:500px">'
						+ '<div class="padding_style" id="content_container_content_iframe_'+i+'">'
						+ '</div>' + '</div>' 
						+ '<div class="span12" id="contentMgr_'+i+'"></div>'
						+ '</div>';
			}
			$("#add_content_container").html(str);
			// 获取模板页的填充内容
			var pageIdx = 0;
			for ( var i = 0; i < pageCount; i++) {
				
				$("#iframe_" + i)
						.load(
								function() {
									pageIdx++;
									var me = this;
									var index = $(me).attr("id").split("_")[1];
									var dataStr = "";
									console.log("pageUrls", $(me)[0].contentWindow.PAGE_URLS);
									var originalUrls = JSON.parse($(me)[0].contentWindow.PAGE_URLS);
									var originalModuleUrls = JSON.parse($(me)[0].contentWindow.MODULE_URLS);
									
									/* ---- 设置下拉连接 ---- */
									dataStr += "<div style='border:1px dashed #cccccc;padding: 6px;'>";
									dataStr += "<div style='padding:3px;cursor:pointer' onclick='showoptions("+pageIdx+")'>设置跳转</div>";
									dataStr += "<div style='padding:3px;display:none' id='optionlist_"+pageIdx+"'>";
									hiddenInputs = $(this).contents().find("input[urlType=url]");
									console.log("iframe_"+i+" url length:", hiddenInputs.length);
									for (var j=0; j<hiddenInputs.length; j++){
										console.log("originalUrls[j].src :", originalUrls[j].src)
										if (!originalUrls[j].src) {
											var selector = $(hiddenInputs[j]);
											elementStr = "<div><label>"+selector.attr("title")+"</label> <select type='module_url' name='"+$(me).attr("id") +"-"+selector.attr("targetId")+"' >";
											if (contentPages.length>0) {
												elementStr += "<option value='-1'></option>";
												for (var k=0; k<contentPages.length; k++){
													if (originalModuleUrls[j].src==contentPages[k].pageUrl) {
														elementStr += "<option value='" + contentPages[k].pageUrl + "' selected>" + contentPages[k].pageName + "</option>";
													} else {
														elementStr += "<option value='" + contentPages[k].pageUrl + "'>" + contentPages[k].pageName + "</option>";
													}
												}
											}
											elementStr += "</select><span id='"+selector.attr("targetId")+"' style='display:none;'><label>地址：</label><input type='text' name='"+selector.attr("targetId")+"'></div>";
											dataStr += elementStr;
										}										
									}
									dataStr += '</div>';
									dataStr += '</div>';
									/* ---- ---------- ---- */
									
									var dataInputs = $(me).contents().find(
											"input[dataType=value]");
									for ( var j = 0; j < dataInputs.length; j++) {
										var selector = $(dataInputs[j]);
										targetId = selector.attr("targetId").replace(new RegExp("#", "gi"),"");
										console.log(1,selector);
										switch (selector.attr("inputType")) {
											case "title":
												dataStr += '<h6 class="border_top">'+selector.attr("title")+':</h6>';
												break;
											case "text":
												console.log(selector.val(),typeof(selector.val()));
												dataStr += '<div><label>'+selector.attr("title")+' </label><input type="text" belong="'+$(me).attr("id")+'" name="'+targetId+'" value="'+selector.val()+'"></div>';
												break;
											case "textarea":
												dataStr += '<div><label>'+selector.attr("title")+' </label><textarea cols="30" rows="3" belong="'+$(me).attr("id")+'"  id="content-'+i+'-'+j+'" name="'+targetId+'">'+selector.val()+'</textarea></div>';
												break;
											case "pic":
												dataStr += '<div><label>'+selector.attr("title")+' </label><img alt="" src="'+selector.val()+'" width="100" height="100"/><input id="fileExtend-'+i+'-'+j+'"/><input class="uploadImg" type="hidden" belong="'+$(me).attr("id")+'" name="'+targetId+'" value="'+selector.val()+'"></div>';
												break;
											case "date":
												dataStr += '<div><label>'+selector.attr("title")+' </label><input id="dateTime-'+i+'-'+j+'" type="text" belong="'+$(me).attr("id")+'" name="'+targetId+'" value="'+selector.val()+'"></div>';
												break;
											case "flash":
												dataStr += '<div><label>'+selector.attr("title")+' </label><input type="text" belong="'+$(me).attr("id")+'" name="'+targetId+'"></div>';
												break;	
										}
									}
									$("#content_container_content_iframe_" + index)
											.html(dataStr);
									console.log($(this).contents().find("#contentManager"));
									if($(this).contents().find("#contentManager")){
										$("#contentMgr_" + index).html($(this).contents().find("#contentManager").html());
										$("#contentMgr_" + index+" a").show();
										$("#contentMgr_" + index+" a").click(function(){
											openURL($(this).attr("title"), $(this).attr("id"), $(this).text());
										});
									}
									
									for (var j=0; j<dataInputs.length; j++){
										 $('#fileExtend-'+i+'-'+j).omFileUpload({
								 		    action : '${ctx}/common/uploadImage',
								 		    swf : '${ctx }/scripts/ui/om-fileupload.swf',
								 		  	fileExt : '*.jpg;*.png;',
								 		  	fileDesc : 'Image Files',
								 		  	sizeLimit : 2000000, //限制大小为1m
								 		  	autoUpload: true,
								 		  	onComplete : function(ID,fileObj,response,data,event){
								 		  		var json = JSON.parse(response);
								 		  		//$(this).parent().find("img").attr("src","${ctx}"+json.filepath);
								 		  		$(this).parent().find("input[class=uploadImg]").val("${ctx}"+json.filepath);
								 		  		$(this).parent().find("img").attr("src","${ctx}"+json.filepath);
								 		  	}
								 		  });
										 
										 $('#dateTime-'+i+'-'+j).omCalendar({});
										 KindEditor.create('#content-'+i+'-'+j, {
												basePath : '${ctx}/scripts/kindeditor/',
												themeType : 'simple',
												langType : 'zh_CN',
												uploadJson : '${ctx}/ajax/kindUploadImage',
												resizeType : 1,
												filterMode:false,//不会过滤HTML代码
												allowImageUpload : true,
												items : [  'undo', 'redo', '|', 'formatblock', 'fontname', 'fontsize',
															'|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
															'strikethrough', '|', 'justifyleft', 'justifycenter',
															'justifyright', 'justifyfull', 'insertorderedlist',
															'insertunorderedlist', 'indent', 'outdent', '|', 'image','h5player','table', 'hr', 'emoticons', 'link', 'unlink',
															'|', 'preview', 'plainpaste', '|', 'removeformat', 'fullscreen','module'],
												afterChange : function() {
													this.sync();
												}
											});
									}
								});
			}
		}
		
		
		
		function CheckTabsExist(currentTabId)
	     {
	    	 console.log(currentTabId)
	         var total = parent.$('#center-tab').omTabs('getLength');   
	         console.log(total);
	         for (var i = 0; i < total; i++)
	         {
	             var tabId = parent.$('#center-tab').omTabs('getAlter', i);                
	             if (tabId == currentTabId)
	             {
	                 return true;
	             }
	         }
	         return false;
	     }
		//
		$("#edit_module_content_btn").click(
				function() {
					var datas = [];
					var urls = [];
					console.log(pageCount);
					for ( var i = 0; i < pageCount; i++) {
						var data = $("#iframe_" + i).contents().find("#data")
								.text();
						console.log("before",data);
						var dataInputs = $("#iframe_" + i).contents().find(
								"input[dataType=value]");
						for ( var j = 0; j < dataInputs.length; j++) {
							var selector = $(dataInputs[j]);
							if(selector.attr("inputType") == "textarea"){
								data = data.replace(selector.attr("targetId"), $("textarea[name="+selector.attr("targetId").replace(new RegExp("#", "gi"),"")+"]").val().replace(/\"/g,"\\\"").replace(/(\n)+|(\r\n)+/g,""));
							}else {
								data = data.replace(selector.attr("targetId"), $("input[name="+selector.attr("targetId").replace(new RegExp("#", "gi"),"")+"]").val());
							}						
						}
						console.log("after",data);
						datas.push({
							index : i,
							value : data
						});
						//获取urls
						var hiddenInputs = $("#iframe_"+i).contents().find("input[urlType=url]");
						var url = {};
						url.index = i;
						console.log("indexs:", i);
						url.url = [];
						for (j=0; j<hiddenInputs.length; j++){
							var iframeId = $("#iframe_"+i).attr("id");
							var selector = $(hiddenInputs[j]);
							var targetId = selector.attr("targetId");
							var value = $('select[name='+iframeId+'-'+targetId+']').val();
							console.log("url:", value);
							if (value=="-1") {
								url.url.push({targetId: targetId, src: ""});
							} else {
								url.url.push({targetId: targetId, src: value});
							}
						}
						urls.push(url);
					}
					console.log("datas=",datas[0]);
					var title = $("#module_content_title").val();
					$.ajax({
						type : 'POST',
						url : "${ctx}/ajax/editModuleContent",
						data : {
							result : JSON.stringify(datas),
							urlResult: JSON.stringify(urls),
							contentGuid : contentGuid,
							moduleGuid : moduleGuid,
							contentTitle : title,
							pageCount : pageCount
						},
						dataType : "json",
					  success:function(data){
						  	alert(data.message);
							if(data.success){
								parent.$('#center-tab').omTabs("close");
							}
					  }
					});
				});
		
		function openURL(url, id, title){
	  		var tabId = "openurl-tab-id-"+id;
	  		if (CheckTabsExist(tabId))
	        {
	            var index = parent.$("#center-tab").omTabs('getAlter', tabId); 
	            parent.$('#center-tab').omTabs('close', index);
	        }
	  		
    	 	parent.$("#center-tab").omTabs('add',{
		           title : title,
		           content : "<iframe id='inner-frame' border=0 frameBorder='no'  name='inner-frame' src='"+url+"' width='100%' height='600'></iframe>",
		           closable : true,
		           tabId:tabId
		     	});
	        
	 	}
	</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
<title>爱车养护内容管理</title>

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
<script type="text/javascript" src="${ctx }/scripts/ui/om-numberfield.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-calendar.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-dialog.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-roweditor.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-rowexpander.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-fileupload.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>

<link rel="stylesheet" type="text/css"
	href="${ctx}/scripts/themes/default/om-all.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/scripts/themes/default/om-grid-rowexpander.css" />
<style>
body{
	font-family:"微软雅黑"!important;
	
}
.input-text {
	border: 1px solid #ccc;
	border-radius: 3px;
	background: #fcfcfc;
	padding: 2px;
	height: 21px;
	font-size: 14px;
	vertical-align: middle;
	width: 220px;
	margin-bottom:5px;
}

.input-text:focus {
	outline: none;
	background: #ffffff;
}
.input-text2 {
	border: 1px solid #ccc;
	border-radius: 3px;
	background: #fcfcfc;
	padding: 2px;
	height: 60px;
	font-size: 14px; 
	width: 360px;
}
#dialog{
	float:left;
	display:inline;
	margin:15px; 
	width: 100%!important;
}
.addtypefrm{
	float:left;
	display:inline;
	width:100%;
}
.btn {
	display: inline-block;
	margin-bottom: 0;
	font-size: 14px;
	padding:0 15px 0 15px;
	text-align: center;
	vertical-align: middle;
	cursor: pointer;
	border-radius:4px;
}
.btn-success{
	color:#ffffff;
	text-shadow:0 -1px 0 rgba(0, 0, 0, 0.25);
	background-color:#5bb75b;
	background-image:-moz-linear-gradient(top, #62c462, #51a351);
	background-image:-webkit-gradient(linear, 0 0, 0 100%, from(#62c462), to(#51a351));
	background-image:-webkit-linear-gradient(top, #62c462, #51a351);
	background-image:-o-linear-gradient(top, #62c462, #51a351);
	background-image:linear-gradient(to bottom, #62c462, #51a351);
	background-repeat:repeat-x;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff62c462', endColorstr='#ff51a351', GradientType=0);
	border-color:#51a351 #51a351 #387038;
	border-color:rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
	*background-color:#51a351;filter:progid:DXImageTransform.Microsoft.gradient(enabled = false);
	text-shadow: 0 -1px 0 rgba(0,0,0,0.2);
	-webkit-box-shadow: inset 0 1px 0 rgba(255,255,255,0.15),0 1px 1px rgba(0,0,0,0.075);
	box-shadow: inset 0 1px 0 rgba(255,255,255,0.15),0 1px 1px rgba(0,0,0,0.075);
}

.btn-success:hover,.btn-success:focus,.btn-success:active,.btn-success.active,
.btn-success.disabled,.btn-success[disabled]{
	color:#ffffff;
	background-color:#51a351;
	*background-color:#499249;
	}
.toolbar{
	width:100%;
	text-align:center;
	padding-top:15px;
}

</style>
<script type="text/javascript">
	//渲染文章列表
	$(function(){
		$('#grid').omGrid({
				limit:10,
				
				width:"100%",
				singleSelect : false,
				height:400,
				autoFit:true,
				showIndex:true,
				dataSource:"${ctx}/ajax/griddata.action?type=manul",
				colModel:[
					{
						header:"类别",
						name:'typeName',
						align:'left',
						width:60,
					},
					{
						header:"标题",
						name:'title',
						align:'left', 
						width:200,
						renderer : function(colValue, rowData, rowIndex) {
							console.log(colValue, rowData, rowIndex);
							return '<a href="javascrpit:void;" onclick="editContent(\''+rowData.textGuid+'\');">' + colValue + '</a>';
						}
					},
				]
			});
		
		//渲染列别列表
		
		$('#button-bar').omButtonbar({
			btns:[
			      {
						label:"类别管理",
						id:"button-newtype",
						icons:{
							left:'${ctx}/scripts/image/add.png'
						},
						onClick:function(){
							addText('类别管理','type','${ctx}/admin/content/mannual/mannual-type-list.jsp');
						}
					},
			      {
						label:"新增",
						id:"button-add",
						icons:{
							left:'${ctx}/scripts/image/add.png'
						},
						onClick:function(){
							addText('新增文章','text','${ctx}/admin/content/mannual/add-mannual.jsp');
						}
				  },
					{
						label:"删除",
						id:"button-remove",
						icons:{
							left:'${ctx}/scripts/image/remove.png'
						},
						onClick:function(){
							var dels = $('#grid').omGrid('getSelections',true);
							if(dels.length<=0){
								$.omMessageBox.alert({
									type:'alert',
									title:'提示',
									content:'请选择要删除的项',
								});
								return;
							}
							
							$.omMessageBox.confirm({
								title:'确认删除',
								content:'删除后无法恢复，是否确认？',
								onClose:function(v){
									if(v){
										var items = "";
										for(var i = 0;i < dels.length; i++){
											items += "item="
												+ dels[i].textGuid;
											
											if(i < dels.length-1){
												items += "&";
											}
										}
										console.log(items);
										$.getJSON("${ctx}/mannual/delMannualtext?"+items,
											function(data){
												if(data.success){
													$.omMessageBox.alert({
														type:'success',
														title:'成功',
														content:data.message,
														onClose:function(v){
															$('#grid').omGrid('deleteRow'.dels);
															$('#grid').omGrid('reload');
														}
													});
												}
												else{
													$.omMessageBox.alert({
														type:'error',
														title:'失败',
														content:data.message
													});
												}
										}
										);
									}
								}
							});

						}
					}]
		}); 
		
		$('#dialog').omDialog({
			autoOpen:false,
			width:'500',
			height:'300',
			modal:true,
		});
		
		function addText(title, name, url) {
			var tabId = "add-" + name + "-tab-id";
			if (CheckTabsExist(tabId)) {
				var index = parent.$("#center-tab").omTabs('getAlter', tabId);
				parent.$('#center-tab').omTabs('close', index);
			}

			parent.$("#center-tab")
					.omTabs(
							'add',
							{
								title : title,
								content : "<iframe id='inner-frame' border=0 frameBorder='no' name='inner-frame' src='"
										+ url
										+ "' width='100%' height='600'></iframe>",
								closable : true,
								tabId : tabId
							});
 
		}
		
        
        $('#addMannualtype').submit(function() {
         	console.log('adding new mannual type');
            $(this).omAjaxSubmit({
            	 beforeSubmit : showRequest,
                 success : showResponse
            });
            return false; 
            //此处必须返回false，阻止常规的form提交。
         });
		
		$("#submit2").omButton();
        $("#reset2").omButton();
		
	});
	//定义图片上传
	$('#fileExtend2').omFileUpload({
	    action : '${ctx}/common/uploadImage',
	    swf : '${ctx}/scripts/ui/om-fileupload.swf',
	  	fileExt : '*.jpg;*.png;',
	  	fileDesc : 'Image Files',
	  	sizeLimit : 200000, //限制大小为200k
	  	autoUpload: true,
	  	onComplete : function(ID,fileObj,response,data,event){
	  		var res = JSON.parse(response);
	  		$("#preview").attr("src", "${ctx}"+res.filepath);
	  		$("#pic").val(res.filepath);
	  	}
	  });
	
	//提交前的处理
	function showRequest(formData, jqForm, options) {
		var queryString = $.param(formData);
		console.log(queryString);
		return true;
	}

	//提交返回的处理
	function showResponse(responseText, statusText, xhr, $form) {
		console.log(responseText);
		$.omMessageBox.waiting('close');
		$("#dialog").omDialog("close");
		var json = eval(responseText);
		if (json.success) {
			$.omMessageBox.alert({
				type : 'success',
				title : '成功',
				content : '添加成功'
			});
			$('#grid').omGrid("reload");
			$("#reset").click();
			$("#picImg").attr("scr", "");
		} else {
			$.omMessageBox.alert({
				type : 'error',
				title : '失败',
				content : '添加失败'
			});
		}
	}
	
	function changeItem(){
		data.name = $("input[name=messageTitle]").val();
		data.title = $("#title").val();
		data.author = $("#author").val();
		data.picSrc = $("#pic").val();
		data.desc = $("#desc").val();
		data.content = $("#content").val();
		data.url = $("#url").val();
		console.log(data);
		assignContent();
	}
	
	function editContent(textGuid){
  		var tabId = "edit-Rule-tab-id";
  		if (CheckTabsExist(tabId))
        {
            var index = parent.$("#center-tab").omTabs('getAlter', tabId); 
            parent.$('#center-tab').omTabs('close', index);
        }
  		
       	parent.$("#center-tab").omTabs('add',{
           title : "编辑文章", 
           content : "<iframe id='inner-frame' border=0 frameBorder='no' name='inner-frame' src='${ctx}/admin/content/mannual/edit-mannual.jsp?textGuid="+textGuid+"' width='100%' height='600'></iframe>",
           closable : true,
           tabId:tabId
     	});
       	
 	}
	
	function CheckTabsExist(currentTabId) {
		console.log(currentTabId)
		var total = parent.$('#center-tab').omTabs('getLength');
		console.log(total);
		for ( var i = 0; i < total; i++) {
			var tabId = parent.$('#center-tab').omTabs('getAlter', i);
			if (tabId == currentTabId) {
				return true;
			}
		}
		return false;
	}
</script>
</head>
<body>
<div id="news">
	<div id="button-bar"></div>
	<table id="grid"></table>
</div>
 

<div id="dialog" title="新增类别" style="overflow:hidden">
	 
	<div class="addtypefrm">
		<form id="addMannualtype" action="${ctx}/mannual/addMannualtype" method="POST">
			<div>
				<table class="dialog-tbl">
					<tr>
						<td>类别名称</td>
						<td><input type="text" class="input-text"/></td>
					</tr>
					<tr>
						<td>图片</td>
						<td><img alt="" src="" width="100" height="100" id="preview" />
							<input id="fileExtend2" /> <input name="pic" type="hidden" />
						</td>
						<td><span class="errorImg"></span><span class="errorMsg"></span></td>
					</tr> 
				</table>
			
				<div class="toolbar">
					<button id="submit2" type="submit"  class="btn btn-success">提交</button>
					<button id="reset2" type="reset"  class="btn btn-success">重置</button>
				</div>
			</div> 
		</form>
	</div>
</div>

</body>
</html>
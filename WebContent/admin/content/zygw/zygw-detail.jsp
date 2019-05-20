<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理列表</title>
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

<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-grid-rowexpander.css" />	
<!-- view_source_begin -->
<style>
html,body {
	font-family: "Arial", "Helvetica", "Verdana", "sans-serif";
	font-size: 12px;
	width: 100%;
	height: 96%;
	margin: 0 auto;
	padding: 0
}

.popwin {
	border: solid 1px #ccc;
	margin: 10px;
	background-color: #fff;
	padding: 5px;
	border-radius: 5px;
	float: left;
	width: 720px;
	color: #666;
}

.item1_label {
	width: auto;
	display: inline;
	float: left;
	background-color: #eee;
	border-radius: 3px; 
}

.td7 {
	line-height: 30px;
	float: left;
	padding-left: 10px;
	border-bottom: 1px solid #ccc;
}

li {
	list-style: none;
	margin: 0;
	padding: 0;
}

ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

.om-panel {
	margin-left: 0px;
	margin-top: 5px;
}

#search-panel li.label {
	padding-left: 5px;
	padding-right: 5px;
	line-height: 25px;
	width: 60px;
}

.input-text {
	border: 1px solid #ccc;
	border-radius: 3px;
	background: #fcfcfc;
	padding: 2px;
	height: 21px;
	font-size: 14px;
	vertical-align: middle;
	width: 120px;
}

.input-text:focus {
	outline: none!important;
	background: #ffffff;
}

#search-panel span.om-combo,#search-panel span.om-calendar {
	vertical-align: middle;
}

input#navSearch {
	width: 135px;;
	height: 16px;
	margin-left: 2px;
	background: url("${ctx}/scripts/image/nav-search.png") no-repeat scroll
		0 0 #FFFFFF;
	padding-left: 20px;
	border: 1px solid #99A8BB;
	line-height: 16px;
}
.label{
	margin-left:15px;	
}
input.error {border: 1px solid red;}
  .om-button {font-size:12px;}
  #nav_cont{width:90%;margin-left:auto;margin-right:auto;}
  input {border: 1px solid #A1B9DF; vertical-align: middle; }
  input:focus{border: 1px solid #3A76C2;}
  .om-calendar input:focus, .om-combo input:focus{border: none;}
  .sex {width: auto;border:none;}
  .sex:focus{border:none;}
  .input_slelct {width: 81%;}
  .textarea_text { height: 40px;width: 90%; border: 1px solid #ccc;border-radius: 3px;background: #fcfcfc;padding: 2px;
	height: 21px;font-size: 14px;vertical-align: middle;height:50px}
  table.grid_layout tr td {font-weight: normal; height: 15px; padding: 5px 0; vertical-align: middle;}
  .color_red { color: red; }
  .toolbar { padding: 12px 0 5px;text-align: center; }
  .separator { border-top:1px solid #adadad; height: 2px; line-height: 2px; overflow: hidden; }
  .address {width:445px;}
  .om-span-field input:focus {border:none;}
  .errorImg{background: url("images/msg_bg.png") no-repeat scroll 0 0 transparent;height: 16px;width: 16px;cursor: pointer;}
  .errorMsg{border: 1px solid gray;background-color: #FCEFE3;display: none;position: absolute;margin-top: -18px;margin-left: 18px;}
  .display {color: gray;font-size: 13px;}
.querycontent {color: blue; font-size: 12px; width: 500px;}
.button-css{
	width: 120px;
	height: 35px;
	margin-top: 10px;
	cursor: pointer;
	float: right;
	background-color: #00A1CB;
	border-color: #007998;
	color: white;
	text-shadow: 0 -1px 1px rgba(0, 40, 50, 0.35);
	border-radius: 3px;
}
</style>
<!-- view_source_end -->
</head>
<body>  
    <div id="dialog" title="修改置业顾问" style="overflow:hidden">
		    <div id="nav_cont">
		        <table class="grid_layout">
		            <tr>
		                <td width="80px"><span class="color_red">*</span>姓名：</td>
		                <td ><input name="zygwName" id="zygwName" class="input-text" style="width:150px;"/></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td><span class="color_red">*</span>编码：</td>
		                <td ><input name="zygwCode" id="zygwCode" class="input-text" style="width:150px;"/></td>
		                <td><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td>性别：</td>
		                <td >
		                    <span>男</span><input type="radio" name="zygwSexMan" id="zygwSexMan" class="sex" value="男" />
		                    <span>女</span><input type="radio" name="zygwSexWomen" id="zygwSexWomen" class="sex" value="女" />
		                </td>
		                 <td><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td>手机号码：</td>
		                <td ><input name="zygwTelephone" id="zygwTelephone" class="input-text" style="width:150px;"/></td>
		                <td><span class="errorImg"></span><span class="errorMsg" class="input-text"></span></td>
		            </tr>
		            <tr>
		                <td>头像：</td>
		                <td >
		                	<img id="edit_preview" src="${ctx}/scripts/image/pic.png" style="width:100px;height:100px;border: 1px solid #ddd;">
							<input id="edit_fileExtend" style="display:inline;">
							<input type="hidden" name="edit_pic" id="edit_pic" style="display:inline" >
		                	
		                </td>
		                <td><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td>自我介绍：</td>
		                <td colspan="4"><label>
		                  <textarea name="zygwInfo" id="zygwInfo" class="textarea_text"  class="input-text"  ></textarea>
		                </label></td>
		            </tr>
		        </table>
		        <div class="row-fluid div_footer" style="height:44px;">
					<div class="span12">
					<span class="pull-right">
					<button id="save_page_content_btn" type="button" class="btn btn-success">保存</button>
					<button id="cancel_page_content_btn" type="button" class="btn btn-cancel">取消</button>
					</span>
					</div>
				</div>
		    </div>
     </div>
     
 <script type="text/javascript">
    $(function() {
		var data = {};
		if ("${param.zygwGuid}") {
    		data.zygwGuid = "${param.zygwGuid}";
		}else{
			$.omMessageBox.alert({
                type:'alert',
                title:'提示',
                content:'请刷新界面!'
            });
		}
		//alert(data.zygwGuid);
		$.ajax({
			 type:"POST",
			 url:"${ctx}/yjajax/queryZygwByGuid",
			 data:{zygwData:JSON.stringify(data)},
			 success:function(data){
				 $.omMessageBox.waiting('close');
				 console.log(data);
				 setData(data.result);
			 }
		});
		function setData(data){
			$("#zygwName").val(data.zygwName);
			$("#zygwCode").val(data.zygwCode);
			if(data.zygwSex=="男"){
				$("#zygwSexMan").attr("checked","checked");
				$("#zygwSexWomen").attr("checked","checked");
			}else{
				$("#zygwSexWomen").attr("checked","checked");
			}
			$("#zygwTelephone").val(data.zygwTelephone);
			var temp="${ctx}"+data.zygwImage;
			$("#edit_preview").attr("src",temp);
			$("#zygwInfo").val(data.zygwInfo);
		}
	});
    $("#save_page_content_btn").click(function(){
    	var data={};
    	if ("${param.zygwGuid}") {
    		data.zygwGuid = "${param.zygwGuid}";
		}else{
			$.omMessageBox.alert({
                type:'alert',
                title:'提示',
                content:'请刷新界面!'
            });
			parent.$("#dialog" ).omDialog("close");
		}
    	//alert("${param.carParaGuid}");
    	data.zygwImage = $("#edit_pic").val();
    	data.zygwName=$("#zygwName").val();
    	data.zygwCode=$("#zygwCode").val();
    	var val=$('input:radio[name="zygwSexMan"]:checked').val();
		if(val==null){
			data.zygwSex="女";
		}else{
			data.zygwSex="男";
		}
		data.zygwTelephone=$("#zygwTelephone").val();
		data.zygwInfo=$("#zygwInfo").val();
		if(check(data)){
			$.omMessageBox.waiting({
		           title:'请等待',
		           content:'服务器正在处理请求...'
		       });
			 $.ajax({
				 type:"POST",
				 url:"${ctx}/yjajax/updateZygw",
				 data:{zygwDataUpdate:JSON.stringify(data)},
				 success:function(data){
					 $.omMessageBox.waiting('close');	
						alert(data.message);
						if(data.success){
							parent.$("#dialogview" ).omDialog("close");
							parent.$('#grid').omGrid("reload");
							parent.$("#reset").click();
							parent.$("#image").attr("scr","");
						}
				 }
			  });
		}
	});
		function check(data){
			if(data.zygwName.length == 0){
				 $.omMessageBox.alert({
	                type:'alert',
	                title:'提示',
	                content:'姓名不能为空！'
	            });
				return false ;
			}
			if(data.zygwCode.length == 0){
				 $.omMessageBox.alert({
	                type:'alert',
	                title:'提示',
	                content:'编码不能为空！'
	            });
				return false ;
			}
			return true;
		}
		
		
		
		$("#cancel_page_content_btn").click(function(){
			 parent.$('#dialogview').omDialog("close");
		});
		
		
		 $('#edit_fileExtend').omFileUpload({
			    action : '${ctx}/common/uploadImage',
			    swf : '${ctx }/scripts/ui/om-fileupload.swf',
			  	fileExt : '*.jpg;*.png;',
			  	fileDesc : 'Image Files',
			  	sizeLimit : 2000000, //限制大小为2m
			  	autoUpload: true,
			  	onComplete : function(ID,fileObj,response,data,event){
			  		var res = JSON.parse(response);
			  		$("#edit_preview").attr("src","${ctx}"+res.filepath);
			  		$("#edit_pic").val(res.filepath);
			  	}
	    });
    </script>
</body>
</html>
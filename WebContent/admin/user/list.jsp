<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head> 

<%@ include file="/common/admin_pre.jsp"%>
<script type="text/javascript" src="${ctx }/scripts/common.js"></script>
<script type="text/javascript" src="${ctx }/scripts/wx.js"></script>

<title>用户管理列表</title>
<!-- view_source_begin -->
<style> 
 
.label {
	margin-left: 15px;
}

input.error {
	border: 1px solid red;
}

 
.om-calendar input:focus,.om-combo input:focus {
	border: none;
}

.input_slelct {
	width: 81%;
}


table.grid_layout tr td {
	font-weight: normal;
	height: 15px;
	padding: 5px 0;
	vertical-align: middle;
}
 
.toolbar {
	padding: 12px 0 5px;
	text-align: center;
}

.separator {
	border-top: 1px solid #adadad;
	height: 2px;
	line-height: 2px;
	overflow: hidden;
}

.address {
	width: 445px;
}

.om-span-field input:focus {
	border: none;
}

.errorImg {
	background: url("images/msg_bg.png") no-repeat scroll 0 0 transparent;
	height: 16px;
	width: 16px;
	cursor: pointer;
}

.errorMsg {
	border: 1px solid gray;
	background-color: #FCEFE3;
	display: none;
	position: absolute;
	margin-top: -18px;
	margin-left: 18px;
}

.display {
	color: gray;
	font-size: 13px;
}

.querycontent {
	color: blue;
	font-size: 12px;
	width: 500px;
} 
</style>
<script type="text/javascript">
     $(document).ready(function() {
    	 var selectIds = [];
    	 
    		 $('#grid').omGrid({
                 limit:14,
                 singleSelect : false,
                 width:'100%',
                 height:440,
                 autoFit : true,
                 showIndex: false,
                 dataSource : "${ctx}/webuser/getsubwebuserlist",
               	 //dataSource: dt.result,
               	 //dataSource: '${ctx}/webuser/getsubwebuserlist',
                 colModel : [ {header : '用户姓名', name : 'userName', align : 'left'}, 
                              {header : '登录名', name : 'loginName', align : 'left'}, 
                             {header : '用户角色', name : 'roleType', align : 'left',
                            	  renderer:function(value,rowData,rowIndex){
                            		  if(value == 'DISTADMIN'){
                            			  return "DIST管理员";
                            		  } else if(value == 'DLRADMIN'){
                            			  return "DLR管理员";
                            		  }
                  					return value;
                  				}
                              }, 
                              {header : '添加人', name : 'createdBy', align : 'left'},
                              {header : '添加时间', name : 'createdOn', align : 'left'}],
        		
             }); 
         
         
         $('#buttonbar').omButtonbar({
         	btns : [{label:"新增",
         		     id:"button-new" ,
         		     icons : {left : '${ctx}/scripts/image/add.png'},
         	 		 onClick:function(){
         	 			$("#dialog").omDialog('open');
         	 		 }
         			}, 
         	        {label:"删除",
         			 id:"button-remove",
         		     disabled :  false,
         			 icons : {left : '${ctx}/scripts/image/remove.png'},
         	 		 onClick:function(){
         	 			var dels = $('#grid').omGrid('getSelections',true);
         	         	if(dels.length <= 0 ){
         	         		 $.omMessageBox.alert({
         	                    type:'alert',
         	                    title:'提示',
         	                    content:'请选择删除的记录！'
         	                });
         	         		return;
         	         	}
         	         	 $.omMessageBox.confirm({
         	                title:'确认删除',
         	                content:'删除后不可恢复,您确认要删除吗？',
         	                onClose:function(v){
         	                   if(v){
         	                	   var items="";
         	                	   for(var i=0;i<dels.length;i++){
         	                		   items += "items="+dels[i].userGuid;
         	                		   if(i< dels.length-1){
         	                			   items +="&";
         	                		   }
         	                	   }
         	                	  $.getJSON("${ctx}/ajax/deleteWebuser?"+items,function(data){
         	                		  if(data.success){
         	                			 $.omMessageBox.alert({
                 	                         type:'success',
                 	                         title:'成功',
                 	                         content:data.message,
                 	                         onClose:function(v){
                 	                        	$('#grid').omGrid('deleteRow',dels);
                 	                        	$('#grid').omGrid('reload');
                 	                         }
                 	                     });
         	                		  } else{
         	                			$.omMessageBox.alert({
                   	                         type:'error',
                   	                         title:'失败',
                   	                         content:data.message
                   	                     }); 
         	                		  }
         	                	  });
         	                   } 
         	                }
         	            });
         	 		 }
         	        }
         	]
         });
         
         $( "#dialog").omDialog({
     			autoOpen: false,
     			height:310,
     			width:400,
     			modal: true
     		});
         
         $("#search-panel").omPanel({
         	title : "搜索",
         	collapsed: false,
         	collapsible:false
         });
       
         $("#startDate").omCalendar();
         $("#endDate").omCalendar();
         $('input[name=userBirth]').omCalendar(); //初始化Calendar
         $('span#button-search').omButton({
       	    icons : {left : '${ctx}/scripts/image/search.png'},width : 70
    	 });
         
         $('#fileExtend').omFileUpload({
 		    action : '${ctx}/web/uploadImage',
 		    swf : '${ctx }/scripts/ui/om-fileupload.swf',
 		  	fileExt : '*.jpg;*.png;',
 		  	fileDesc : 'Image Files',
 		  	sizeLimit : 2000000, //限制大小为1m
 		  	autoUpload: true,
 		  	onComplete : function(ID,fileObj,response,data,event){
 		  		var json = JSON.parse(response);
 		  		$(this).parent().find("img").attr("src","${ctx}"+json.filepath);
 		  		$(this).parent().find("input[name=userPic]").val(json.filepath);
 		  	}
 		  });
         
         $("#submit").omButton();
         $("#reset").omButton();
         
         var options = {
                 beforeSubmit : showRequest,
                 success : showResponse
             };
         
         $('#addUserForm').submit(function() {
         	console.log(11);
             $(this).omAjaxSubmit(options);
             return false; //此处必须返回false，阻止常规的form提交。
         });
         $.getJSON("${ctx}/ajax/combo?type=assignGzh", function(ret){
        	 $('#gzhGuid').omCombo({
	        	width : 154,
	 			value : '微信号',
	 			editable : false,
	 			dataSource :ret
        	 });
         });
         var types = [{text:'DIST管理员', value:'DISTADMIN'},{text:'DLR管理员', value:'DLRADMIN'}];
         $('#roleType').omCombo({
	        	width : 154,
	 			value : '权限',
	 			editable : false,
	 			dataSource :types
     	 });
         /*
         $.getJSON("${ctx}/ajax/griddata.action?type=subprivilegelevel", function(ret){
        	var lst = new Array();
       		for(var i=0;i<ret.rows.length;i++){
	       		var ar = ret.rows[i];
	       		lst.push({
	       			text : ar.privilegeName,
	   				value : ar.privilegeLevel
	       		});
	       	 }
       		
	         $('#userPrivilegeLevel').omCombo({
	        	 width : 100,
	 			value : '用户类型',
	 			editable : false,
	 			dataSource :lst, 
	 			onValueChange : function(target, newValue, oldValue, event) {
	 				 $.getJSON("${ctx}/ajax/griddata.action?type=subprivilegeitem&level="+newValue, function(ret){
                            var lst = new Array();
                            for(var i=0;i<ret.rows.length;i++){
                                var ar = ret.rows[i];
                                lst.push({
                                    text : ar.itemName,
                                    value : ar.itemGuid
                                });
                            }
                            $('#userPrivileges').omCombo({
                                 width : 100,
                                value : '权限',
                                editable : false,
                                dataSource :lst,
                                multi : true
                            }); 

                     });
	 			}
	         });
       		
         });
         */
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
    	 $("#dialog" ).omDialog("close");
         var json = eval(responseText);
         if(json.success){
        	 $.omMessageBox.alert({
                 type:'success',
                 title:'成功',
                 content:'添加成功'
             });
        	 $('#grid').omGrid("reload");
        	 $("#reset").click();
        	 $("#picImg").attr("scr","");
         } else {
        	 $.omMessageBox.alert({
                 type:'error',
                 title:'失败',
                 content:'添加失败'
             });
         }
     }
	$(function(){
		if('${loginObject.roleType}'!='ADMIN'){
			$('#roleType_box').css('display','none');
			$('#gzhGuid_box').css('display','none');
		}
	})
 </script>
<!-- view_source_end -->
</head>
<body>

	<!-- view_source_begin -->
	<div id="search-panel">
		<div>
			<span class="label">关键词：</span> <input type="text" class="input-text" />
			<span class="label">添加时间(S)：</span> <input id="startDate" name="startDate" />
			<span class="label">添加时间(E)：</span>
			<input id="endDate" name="endDate" /> <span id="button-search">搜索</span>
		</div>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin-bottom: 5px;"></div>
		<table id="grid"></table>
	</div>

	<div id="dialog" title="添加用户" style="overflow: hidden">
		<form id="addUserForm" action="${ctx }/ajax/addWebuser" method="post">
			<div id="nav_cont">
				<table class="grid_layout">
					<tr>
						<td width="100px"><span class="color_red">*</span>用户姓名：</td>
						<td><input name="userName" class="input-text" style="width: 150px;" /></td>
						<td width="30px"><span class="errorImg"></span><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td><span class="color_red">*</span>登录名：</td>
						<td><input name="loginName" class="input-text" style="width: 150px;" /></td>
						<td><span class="errorImg"></span><span class="errorMsg"></span></td>
					</tr>
					<tr style="display:none">
						<td><span class="color_red">*</span>登录密码：</td>
						<td><input name="userPwd" type="password" class="input-text" value="123456" style="width: 150px;" /></td>
						<td><span class="errorImg"></span><span class="errorMsg"></span></td>
					</tr>
					<tr id='roleType_box'>
						<td><span class="color_red">*</span>用户角色：</td>
						<td>
							<input id='roleType' name='roleType' />
						</td>
						<td><span class="errorImg"></span><span class="errorMsg"></span></td>
					</tr>
					<tr id='gzhGuid_box'>
						<td><span class="color_red">*</span>微信号：</td>
						<td>
							<input id='gzhGuid' name='gzhGuid' />
						</td>
						<td><span class="errorImg"></span><span class="errorMsg"></span></td>
					</tr>
					<!--tr>
						<td><span class="color_red">*</span>权限：</td>
						<td><input name="userPrivileges" id='userPrivileges' /></td>
						<td><span class="errorImg"></span><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>性别：</td>
						<td><span>男</span><input type="radio" name="userSex"
							class="sex" value="男" /> <span>女</span><input type="radio"
							name="userSex" class="sex" value="女" /></td>
						<td><span class="errorImg"></span><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>出生日期：</td>
						<td><input name="userBirth" style="width: 150px;" /></td>
						<td><span class="errorImg"></span><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>移动电话：</td>
						<td><input name="userTel" class="input-text"
							style="width: 150px;" /></td>
						<td><span class="errorImg"></span><span class="errorMsg"
							class="input-text"></span></td>
					</tr>
					<tr>
						<td>头像：</td>
						<td><img alt="" src="" width="70" height="70" id="picImg" />
							<input id="fileExtend" /> <input name="userPic" type="hidden" />

						</td>
						<td><span class="errorImg"></span><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>自我介绍：</td>
						<td colspan="4"><label> <textarea name="description" class="input-text"></textarea>
						</label></td>
					</tr-->
				</table>
				<div class="toolbar" style='padding-top:0px'>
					<button id="submit" type="submit" class="btn-def" style='width:50px;height:28px'>提交</button>
					<button id="reset" type="reset" class="btn-def" style='width:50px;height:28px'>重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
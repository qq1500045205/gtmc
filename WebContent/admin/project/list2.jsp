<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>销售店管理_广州店列表</title>
<%@ include file="/common/admin_pre.jsp"%> 
<!-- view_source_begin -->
<style>  
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

#search-panel span.om-combo,#search-panel span.om-calendar {
	vertical-align: middle;
}
 
input.error {border: 1px solid red;}
  .om-button {font-size:12px;}
  #nav_cont{width:90%;margin-left:auto;margin-right:auto;}
  input {border: 1px solid #A1B9DF; vertical-align: middle;  }
  input:focus{border: 1px solid #3A76C2;}
  .om-calendar input:focus, .om-combo input:focus{border: none;}
  .sex {width: auto;border:none;}
  .sex:focus{border:none;}
  .input_slelct {width: 81%;}
  .textarea_text {border: 1px solid #A1B9DF; height: 40px;width: 90%;}
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
             dataSource : "${ctx}/ajax/griddata.action?type=userprojects_gz&projectGuid=44A",
             colModel : [ {header : '销售店编号', name : 'dealerCode', align : 'left',width:100}, 
                          {header : '状态', name : 'status', align : 'left',width:70,
				            renderer: function(colValue, rowData, rowIndex) {
				            	if(colValue == 0)
				            		return "有效";
				                return  "无效";
				            }
             			  }, 
                          {header : '销售店名称', name : 'projectName', align : 'left',width:160}, 
                          {header : '地址', name : 'address', align : 'left',width:200},
                          {header : '咨询电话', name : 'mobile', align : 'left',width:100},
                          {header : '售后电话', name : 'asTel', align : 'left',width:100},
                          {header : '救援电话', name : 'helpTel', align : 'left',width:100},
                          {header : '传真', name : 'fax', align : 'left',width:100},
                          {header : '添加时间', name : 'createdOn', align : 'left',width:180}]
         });
         
         $('#button-search').click( function () { 
        	 var beginTime=$('#startDate').val();
        	 var endTime=$('#endDate').val();
        	 if(beginTime !='' && beginTime.lenght != 0){
        		 beginTime+= " 00:00:00";
        	 }else{
        		 beginTime = '1900-01-01 00:00:00';
        	 }
        	 if(endTime !=''&& endTime.lenght != 0){
        		 endTime += " 23:59:59";
        	 }else{
        		 endTime = "2110-01-01 23:59:59";
        	 }
      
        	 $('#grid').omGrid({
                 limit:14,
                 singleSelect : false,
                 width:'100%',
                 height:440,
                 autoFit : true,
                 showIndex: false,
                 dataSource : "${ctx}/ajax/griddata.action?type=userprojects_gz&projectGuid=44A&beginTime="+beginTime+"&endTime="+endTime,
                 colModel : [ {header : '销售店编号', name : 'dealerCode', align : 'left',width:100}, 
                              {header : '状态', name : 'status', align : 'left',width:70,
    				            renderer: function(colValue, rowData, rowIndex) {
    				            	if(colValue == 0)
    				            		return "有效";
    				                return  "无效";
    				            }
                 			  }, 
                              {header : '销售店名称', name : 'projectName', align : 'left',width:160}, 
                              {header : '地址', name : 'address', align : 'left',width:200},
                              {header : '咨询电话', name : 'mobile', align : 'left',width:100},
                              {header : '售后电话', name : 'asTel', align : 'left',width:100},
                              {header : '救援电话', name : 'helpTel', align : 'left',width:100},
                              {header : '传真', name : 'fax', align : 'left',width:100},
                              {header : '添加时间', name : 'createdOn', align : 'left',width:180}]
             });
         });
         
         $('#buttonbar').omButtonbar({
         	btns : [{label:"新增",
         		     id:"button-new" ,
         		     icons : {left : '${ctx}/scripts/image/add.png'},
         	 		 onClick:function(){
         	 			$("#dialog").omDialog('open');
         	 		 }
         			},
         			{separtor:true},
         	        {label:"删除",
         			 id:"button-remove",
         		     disabled :  false,
         			 icons : {left : '${ctx}/scripts/image/remove.png'},
         	 		 onClick:function(){
         	 			var dels = $('#grid').omGrid('getSelections',true);
         	 			console.log(dels);
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
         	                		   items += dels[i].projectGuid;
         	                		   if(i< dels.length-1){
         	                			   items +="&";
         	                		   }
         	                	   }
         	                	  $.getJSON("${ctx}/admin/delProject?items="+items,function(data){
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
         	        },
         	       {separtor:true},
         	       {label:"导入救援电话",
           		     id:"button-new" ,
           		     icons : {left : '${ctx}/scripts/image/add.png'},
           	 		 onClick:function(){
           	 			$("#dialog-input").omDialog('open');
           	 		 }
           		}
         	]
         });
         
         $( "#dialog").omDialog({
     			autoOpen: false,
     			height:500,
     			width:500,
     			modal: true
     		});
         
         $( "#dialog-input").omDialog({
  			autoOpen: false,
  			height:300,
  			width:500,
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
 		    action : '${ctx}/common/uploadImage',
 		    swf : '${ctx }/scripts/ui/om-fileupload.swf',
 		  	fileExt : '*.jpg;*.png;',
 		  	fileDesc : 'Image Files',
 		  	sizeLimit : 2000000, //限制大小为1m
 		  	autoUpload: true,
 		  	onComplete : function(ID,fileObj,response,data,event){
 		  		var image = eval('(' + response + ')');
 		  		$(this).parent().find("img").attr("src","${ctx}"+image.filepath);
 		  		$(this).parent().find("input[name=image]").val(image.filepath);
 		  	}
 		  });
         
         $("#submit").omButton();
         $("#reset").omButton();
         
         var options = {
                 beforeSubmit : showRequest,
                 success : showResponse
             };
         
         $('#addProjectForm').submit(function() {
        	 $.omMessageBox.waiting({
                 title:'请稍候',
                 content:'服务器正在处理您的请求，请稍候...',
             });
             $(this).omAjaxSubmit(options);
             return false; //此处必须返回false，阻止常规的form提交。
         });
         
         $('#importForm').submit(function() {
        	 $.omMessageBox.waiting({
                 title:'请稍候',
                 content:'服务器正在处理您的请求，请稍候...',
             });
             $(this).omAjaxSubmit(options);
             return false; //此处必须返回false，阻止常规的form提交。
         });
         
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
    	 $("#dialog-input" ).omDialog("close");
         var json = eval(responseText);
         if(json.success){
        	 $.omMessageBox.alert({
                 type:'success',
                 title:'成功',
                 content:'操作成功'
             });
        	 $('#grid').omGrid("reload");
        	 $("#reset").click();
        	 $("#image").attr("scr","");
         } else {
        	 $.omMessageBox.alert({
                 type:'error',
                 title:'失败',
                 content:'操作失败'
             });
         }
     }

 </script>
<!-- view_source_end -->
</head>
<body>

	<!-- view_source_begin -->
	<div id="search-panel">
   			<div>
	   			<span class="label">关键词：</span>
	   			<input type="text" class="input-text" />
	   			<span class="label">添加时间(S)：</span>
	   			<input id="startDate" name="startDate"/>
	   			<span class="label">添加时间(E)：</span>
	   			<input id="endDate" name="endDate"/>
	   			<span id="button-search">搜索</span>
   			</div>
   		</div>
	
    <div id="news">
        <div id="buttonbar" class="om-buttonbar"></div>
    	<table id="grid"></table>
    </div>
    
    <div id="dialog" title="添加销售店" style="overflow:hidden">
       <form id="addProjectForm" action="${ctx}/admin/addProject" method="post">
		    <div id="nav_cont">
		        <table class="grid_layout">
		            <tr>
		                <td width="80px"><span class="color_red">*</span>销售店名称：</td>
		                <td ><input name="projectName" class="input-text" style="width:150px;" /></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		             <tr>
		                <td width="80px"><span class="color_red">*</span>销售店编号：</td>
		                <td ><input name="projectNumber" class="input-text" style="width:150px;"/></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td width="80px"><span class="color_red">*</span>描述：</td>
		                <td ><textarea name="description" cols="30" rows="3" class="input-text" style="width:200px;height:80px"></textarea></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td width="80px"> 咨询电话：</td>
		                <td ><input name="mobile" class="input-text" style="width:150px;"></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		             <tr>
		                <td width="80px"> 救援电话：</td>
		                <td ><input name="helpTel" class="input-text" style="width:150px;"></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td> 销售店图片：</td>
		                <td >
		                	<img alt="" src="" width="70" height="70" id="picImg"/>
		                	<input id="fileExtend"/>
		                	<input name="image" type="hidden" id='image'/>
		                	
		                </td>
		                <td><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            
		        </table>
		        <div class="toolbar">
		            <button id="submit" type="submit" class="btn-def">提交</button> 
		            <button id="reset" type="reset" class="btn-def">重置</button>
		        </div>
		    </div>
	    </form>
     </div>
     
     <div id="dialog-input" title="导入救援电话" style="overflow:hidden">
       <form id="importForm" action="${ctx}/admin1/importData" method="post" enctype="multipart/form-data">
		    <div id="nav_cont">
		        <table class="grid_layout">
		            <tr>
		                <td  width="80px"> 文件：</td>
		                <td >
		                	<input type="file" name="xlsFile" style="width:150px;">
		                </td>
		                <td  width="30px">&nbsp;</td>
		            </tr>
		            <tr>
		                <td  width="80px"> &nbsp;</td>
		                <td >
		                	<a href="${ctx }/uploads/help-tel.xls">查看/下载模版文件</a>
		                </td>
		                <td  width="30px">&nbsp;</td>
		            </tr>
		        </table>
		        <div class="toolbar">
		            <button type="submit" class="btn-def">提交</button> 
		        </div>
		    </div>
	    </form>
     </div>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商铺类型管理</title>
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
<script type="text/javascript" src="${ctx }/scripts/ui/om-calendar.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-dialog.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-fileupload.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-all.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/scripts/themes/default/om-grid-rowexpander.css" />	

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/themes/default/om-all.css" />
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

 #search-panel span.label{
      	margin-left: 10px;
}
.input-text {
    border: 1px solid #6D869E;
    height: 18px;
    vertical-align: middle;
    width: 200px;
}
#search-panel span.om-combo,#search-panel span.om-calendar{
	vertical-align: middle;
}
input#navSearch{
	width: 135px;;
	height: 16px;
	margin-left: 2px;
	background: url("${ctx}/scripts/image/nav-search.png") no-repeat scroll 0 0 #FFFFFF;
	padding-left: 20px;
	border: 1px solid #99A8BB;
	line-height: 16px;
}
 .srch_line {
	display: block;
	margin: 5px;
	width: auto;
}

 .srch_line ul {
	display: block;
}

 .srch_line ul li {
	list-style-type: none;
	float: left;
	display: inline;
	margin: 3px;
}
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
。element.style {
}
Matched CSS Rules
.om-dialog .om-dialog-title {
float: left;
margin: 0.7em 16px 0.1em 0.5em;
font-size: 16px;
color: #999;
}
.om-dialog .om-dialog-title {
	margin:0 0 0 10px;
}

.color_red{
	color:red;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		
		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height:400,
			showIndex : false,
			dataSource : "${ctx}/ajax/griddata.action?type=contentShopType",
			colModel : [ {
				header : '类型名称',
				name : 'typeName',
				align : 'left',
				width : 200
			}, {
				header : '商标图片',
				name : 'shopsPic',
				align : 'left',
				width : 100,
				sort : 'clientSide',
				renderer:function(colValue, rowData, rowIndex){
               		return '<a href="${ctx}'+rowData.typePic+'" target="_blank">查看</a>';
         		}  
			}, {
				header : '创建人',
				name : 'createdBy',
				align : 'left',
				width : 100
			}, {
				header : '创建时间',
				name : 'createdOn',
				align : 'left',
				width : '200'
			}],
			autoFit : true
		});

		$('#buttonbar').omButtonbar({
			btns : [{
				label : "添加商铺类型",
				id : "button-add",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					$("#dialog-type").omDialog('open');
				}
			},
 			{separtor:true},
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
 	                content:'您确定要删除？<p style="color:red">注意：删除楼层信息后,不可恢复,请谨慎操作。<p>',
 	                onClose:function(v){
 	                   if(v){
 	                	   var items="";
 	                	   for(var i=0;i<dels.length;i++){
 	                		   items += "items="+dels[i].typeGuid;
 	                		   if(i< dels.length-1){
 	                			   items +="&";
 	                		   }
 	                	   }
 	                	  $.getJSON("${ctx}/ajax/deleteTypeShop?"+items,function(data){
 	                		  if(data.success){
 	                			 $.omMessageBox.alert({
         	                         type:'success',
         	                         title:'成功',
         	                         content:data.message,
         	                         onClose:function(v){
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
 	        }]
		});

		$("#search-panel").omPanel({
			title : "高级搜索",
			collapsed : false,
			collapsible : false
		});

		$("#search-status").omCombo({
			width : 100,
			value : '-',
			editable : false,
			dataSource : [ {
				text : '查询条件',
				value : '-'
			}, {
				text : '条件一',
				value : '1'
			}, {
				text : '条件二',
				value : '2'
			} ]
		});
		
		$('span#button-search').omButton({
			icons : {
				left : '${ctx}/scripts/image/search.png'
			},
			width : 70,
			onClick : function(event) {

			}

		});
		
		  $( "#dialog").omDialog({
   			autoOpen: false,
   			height:350,
   			width:450,
   			modal: true
   		});
		  
		  $( "#dialog-type").omDialog({
	   			autoOpen: false,
	   			height:350,
	   			width:450,
	   			modal: true
	   		});
		  
		$('#fileExtend2').omFileUpload({
 		    action : '${ctx}/common/uploadImage',
 		    swf : '${ctx }/scripts/ui/om-fileupload.swf',
 		  	fileExt : '*.jpg;*.png;',
 		  	fileDesc : 'Image Files',
 		  	sizeLimit : 2000000, //限制大小为1m
 		  	autoUpload: true,
 		  	onComplete : function(ID,fileObj,response,data,event){
 		  		var image = eval('(' + response + ')');
 		  		$(this).parent().find("img").attr("src","${ctx}"+image.filepath);
 		  		$(this).parent().find("input[name=typePic]").val(image.filepath);
 		  	}
 		  });
	  	
	    var options = {
                beforeSubmit : showRequest,
                success : showResponse
            };
        
        $('#addTypeForm').submit(function() {
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
   	  $("#dialog-type" ).omDialog("close");
        var json = eval(responseText);
        if(json.success){
	       	 $.omMessageBox.alert({
                type:'success',
                title:'成功',
                content:'添加成功'
             });
	       	 $('#grid').omGrid("reload");
	       	 $("#reset").click();
	       	 $("#typePic").attr("scr","");
        } else {
       	 	$.omMessageBox.alert({
                type:'error',
                title:'失败',
                content:'添加失败'
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
			<form action="#" id="search-form" method="post">
				<div class="srch_line">
					<ul>
						<li><input id="search-status" name="oppQuery.status" /></li>
						<li><input style="width: 100px;" /></li>
					</ul>
				</div>

				<div class="srch_line">
					<span id="button-search">搜索</span>
				</div>

			</form>
		</div>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar"></div>
		<div style="width: 100%;">
			<table id="grid"></table>
		</div>
	</div>
	
	 <div id="dialog-type" title="添加商铺类型">
	 	 <form id="addTypeForm" action="${ctx}/ajax/addTypeShop" method="post">
		    <div id="nav_cont">
		        <table class="grid_layout">
		            <tr>
		                <td width="80px"><span class="color_red">*</span>类型名称：</td>
		                <td ><input name="typeName" class="input-text"/></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		               <tr>
		                <td width="80px"><span class="color_red">*</span>类型图标：</td>
		                <td >
							<img alt="" src="" width="100" height="100" id="typePic"/>
		                	<input id="fileExtend2"/>
		                	<input name="typePic" type="hidden"/>
						</td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		        </table>
		        <div class="toolbar">
		            <button type="submit" class="button-css">提交</button> 
		            <button type="reset" class="button-css">重置</button>
		        </div>
		    </div>
	    </form>
	 </div>
</body>
</html>
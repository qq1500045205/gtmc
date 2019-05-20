<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head> 

<%@ include file="/common/admin_pre.jsp"%> 
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-sort.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/editor/omeditor.js"></script>
<script type="text/javascript" src="${ctx}/scripts/json.js"></script>  
<link rel="stylesheet" type="text/css" href="${ctx}/scripts/themes/default/om-grid-sort.css" /> 

<title>公众号管理</title> 
<!-- view_source_begin -->
<style>  
#button-search{
	height:30px!important;
	line-height:26px!important;
}
.grid_layout{
	line-height:35px;
}
.grid_layout input {
	width:200px;
} 
.toolbar{
	text-align: center;
	padding:10px;
} 
</style>
<script type="text/javascript">
	$(document).ready(function() {

		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height: 400,
			showIndex : false,
			dataSource : "${ctx}/ajax/griddata.action?type=gzh",
			colModel : [ {
				header : '销售店名',
				name : 'projectName',
				align : 'left',
				width : 180,
				sort : 'clientSide'
			}, {
				header : '销售店编码',
				name : 'projectNo',
				align : 'left',
				width : 80,
				sort : 'clientSide'
			}, {
				header : '公众号帐号',
				name : 'gzhAccount',
				align : 'left',
				width : 180
			},{
				header : 'TOKEN',
				name : 'token',
				align : 'left',
				width : 150
			}, 
			 {
				header : 'API地址',
				name : 'api',
				align : 'left',
				width : 300,
				renderer:function(value,rowData,rowIndex){
					return "${myfn:getBaseUrl()}"+value;
				}
			} ,{
				header : '状态',
				name : 'status',
				align : 'left',
				width : 40
			} ],
			autoFit : true 
		});

		$('#buttonbar').omButtonbar({
			btns : [ {
				label : "新增",
				id : "button-add",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					$("#add_gbh_dialog").omDialog('open');
					
					$("#project_comb").omCombo({
						width : 180,
						value : '销售店名',
						editable : false,
						dataSource : '${ctx}/ajax/combo?type=wxProject'
					});
				}
			}, {
				label : "删除",
				id : "button-delete",
				icons : {
					left : '${ctx}/scripts/image/remove.png'
				},
				onClick : function() {
					var data = $('#grid').omGrid('getSelections', true);
					if (data.length <= 0) {
						$.omMessageBox.alert({
							type : 'alert',
							title : '提示',
							content : '请选择要删除的记录！'
						});
						return;
					}
					for(var i=0;i<data.length;i++){
						var dt = data[i];
						$.post("${ctx}/ajax/delGzh", {gzhGuid: dt.gzhGuid},function(rt){
							$('#grid').omGrid("reload");
						});
					}
				}
			}]
		});

		
		$("#search-panel").omPanel({
			title : "高级搜索",
			collapsed : false,
			collapsible : true
		});

		$("#search-area").omCombo({
			width : 100,
			value : '-',
			editable : false,
			dataSource : [ {
				text : '所在区域',
				value : '-'
			}, {
				text : '北京',
				value : '1'
			}, {
				text : '上海',
				value : '2'
			} ]
		});
		
		
		$("#search-name").omCombo({
			width : 100,
			value : '-',
			editable : false,
			dataSource : [ {
				text : '销售店名',
				value : '-'
			}, {
				text : '北京',
				value : '1'
			}, {
				text : '上海',
				value : '2'
			} ]
		});
		
		$("#search-status").omCombo({
			width : 100,
			value : '-',
			editable : false,
			dataSource : [ {
				text : '状态',
				value : '-'
			}, {
				text : '北京',
				value : '1'
			}, {
				text : '上海',
				value : '2'
			} ]
		});

		$('.editor').omEditor({
			toolbar : 'Basic',
			height : 200,
			width : 420
		});

		$('span#button-search').omButton({
			icons : {
				left : '${ctx}/scripts/image/search.png'
			},
			width : 70,
			onClick : function(event) {
			}
		});
		
		$("#add_gbh_dialog").omDialog({
			autoOpen: false,
			title:'绑定公共账号',
			minWidth: 400,
			minHeight: 350,
			modal: true
		   
		});
		
		
		$('#addGzhForm').submit(function() {
         	console.log('adding new gzh');
            $(this).omAjaxSubmit({
            	 beforeSubmit : showRequest,
                 success : showResponse
            });
            return false; 
            //此处必须返回false，阻止常规的form提交。
         });
		
		$("#submit").omButton();
        $("#reset").omButton();
		
        //project 
        

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
   	 	$("#add_gbh_dialog" ).omDialog("close");
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
  	
</script>
<!-- view_source_end -->
</head>
<body>

	<!-- view_source_begin -->
	<div id="search-panel">
		<div>

			<form action="#" id="search-form" method="post">
				<div class="srch_line new_line">
					<ul>
						<li><input id="search-area" name="oppQuery.status" /></li>
						<li><input id="search-name" name="oppQuery.status" /></li>
						<li><input id="search-status" name="oppQuery.status" /></li>
						<li>公众号帐号: <input id="search-wxh" name="oppQuery.status" class="input-text" /></li>
						<li>销售店编码: <input id="search-bh" name="oppQuery.status" class="input-text" /></li>
					</ul>
				</div>
				<div class="srch_line inline">
					<span id="button-search">搜索</span>
				</div>

			</form>
		</div>
	</div>
	<div id="add_gbh_dialog" style='display:none;width: 340px;clear:both;padding: 0 10px;'>
		<form id='addGzhForm' action="${ctx }/ajax/addGzh" method="post">
			<div style='width:380px;margin:10px;'>
				<table class="grid_layout">
					<tr>
		                <td width="100px"><span class="color_red">*</span>公众号名称：</td>
		                <td ><input name="gzhName" id='gzhName' class="input-text"/></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td width="100px"><span class="color_red">*</span>销售店名称：</td>
		                <td ><input name="projectGuid" id="project_comb"  class="input-text" style="width:180px"/></td>
		                <td width="30px"><span class="errorImg"></span>
		                <span class="errorMsg"></span></td>
		            </tr>
		            <tr>
		                <td width="100px"><span class="color_red">*</span>Appid：</td>
		                <td ><input name="appid"  class="input-text"/></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td width="80px"><span class="color_red">*</span>appsecret：</td>
		                <td ><input name="appsecret"  class="input-text"/></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		             <tr>
		                <td width="100px"><span class="color_red">*</span>公众号帐号：</td>
		                <td ><input name="gzhAccount"  class="input-text"/></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            <tr>
		                <td width="100px"><span class="color_red">*</span>公众号密码：</td>
		                <td ><input name="gzhPwd"  class="input-text"/></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		             <tr>
		                <td width="100px"><span class="color_red">*</span>原始帐号：</td>
		                <td ><input name="original" class="input-text"/></td>
		                <td width="30px"><span class="errorImg"></span><span class="errorMsg" ></span></td>
		            </tr>
		            
				</table>
				<div class="toolbar">
		            <button id="submit" type="submit" class="btn-def">提交</button> 
		            <button id="reset" type="reset" class="btn-def">重置</button>
		        </div>
			</div>
		</form>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar"></div>
		<div style="width: 100%;">
			<table id="grid"></table>
		</div>
	</div>

</body>
</html>
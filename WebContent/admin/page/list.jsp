<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>组件页设置</title>
<%@ include  file="/common/admin_pre.jsp" %> 

<script type="text/javascript">
	$(document).ready(function() {

		$('#grid').omGrid({
			limit : 14,
			singleSelect : false,
			width : '100%',
			height: 540,
			showIndex : false,
			dataSource : "${ctx}/ajax/griddata.action?type=modulePage",
			colModel : [ {
				header : '组件页名称',
				name : 'pageName',
				align : 'left',
				width : 130,
				sort:'serverSide'
			}, {
				header : '组件页编码',
				name : 'pageCode',
				align : 'left',
				width : 100,
				sort : 'clientSide'
			}, {
				header : '模版地址',
				name : 'pageFtl',
				align : 'left',
				width : 200,
				sort:'serverSide'
			}, {
				header : '组件页描述',
				name : 'pageDiscription',
				align : 'left',
				width : 200,
				sort : 'clientSide'
			}],
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
					$("#dialog-add").omDialog("open");
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
					var items = "item=";
					for ( var i = 0; i < data.length; i++) {
						items += data[i].pageGuid;
						if(i<data.length -1){
							items +=",item=";
						}
					}
					 $.omMessageBox.confirm({
		                    title: '确认删除',
		                    content: '删除后不可恢复,您确认要删除吗？',
		                    onClose: function(v) {
		                        if (v) {
									$.getJSON("${ctx}/page/delPage?"+items,
											function(responseText){
										if (responseText.success) {
									          $.omMessageBox.alert({
									              type: 'success',
									              title: '成功',
									              content: responseText.message,
									          });
									          $('#grid').omGrid('reload');
									       } else {
									          $.omMessageBox.alert({
									              type: 'error',
									              title: '失败',
									              content: responseText.message
									          });
									          consoe.log(responseText.result);
									      }
									});
		            			}
		                    }
		                });
				}
			} ]
		});

	  	$("#dialog-add").omDialog({
	        autoOpen: false,
	        height: 300,
	        width: 400,
	        modal: true,
	        onClose : function(event) {
	        	
	        }
	    });

		 $("#addForm").validate({
	            rules : {
	            	pageName : {
	                	required : true
	                },
	                pageCode : {
	                    required : true
	                },
	                pageFtl : {
	                    required : true
	                }
	            },
	            messages : {
	            	pageName : {
	                	required : "请输入组件页名称"
	                },
	                pageCode : {
	                    required : "请输入组件页编码"
	                },
	                pageFtl : {
	                    required : "请输入模版地址"
	                }
	            },
	            submitHandler : function(){
	            	$('#addForm').omAjaxSubmit(options);
	                return false;
	            }
	        });
		 
		 var options = {
                beforeSubmit : showRequest, 
                success : showResponse
			};
			
			function showRequest(formData, jqForm, options) {
			    return true;
			}
			function showResponse(responseText, statusText, xhr, $form) {
			   	if (responseText.success) {
			          $.omMessageBox.alert({
			              type: 'success',
			              title: '成功',
			              content: responseText.message,
			          });
			          $("#dialog-add").omDialog("close");
			          $('#grid').omGrid('reload');
			       } else {
			          $.omMessageBox.alert({
			              type: 'error',
			              title: '失败',
			              content: responseText.message
			          });
			          console.log(responseText.result);
			      }
			}

	});
</script>
<!-- view_source_end -->
</head>
<body>

	<div>&nbsp;</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar"></div>
		<div style="width: 100%;">
			<table id="grid"></table>
		</div>
	</div>
	
	<div id="dialog-add"  title="添加组件页">
		<form action="${ctx }/page/addPage" id="addForm" method="post">
			<table>
				<tr>
					<td width="20%">组件页名称<span class="color_red">*</span></td>
					<td width="80%"><input type="text" name="pageName" class="input-text" ><span class="errorMsg"></span></td>
				</tr>
				<tr>
					<td>组件页编码<span class="color_red">*</span></td>
					<td><input type="text" name="pageCode" class="input-text" ><span class="errorMsg"></span></td>
				</tr>
				<tr>
					<td>模版地址<span class="color_red">*</span></td>
					<td><input type="text" name="pageFtl" class="input-text" ><span class="errorMsg"></span></td>
				</tr>
				<tr>
					<td>组件页描述</td>
					<td><textarea name="pageDiscription" class="area-text" ></textarea><span class="errorMsg"></span></td>
				</tr>
			</table>
			<div style="text-align:center">
				<input type="submit" id="sub" name="提交" class="btn-def">						
			</div>
		</form>
	</div>

</body>
</html>
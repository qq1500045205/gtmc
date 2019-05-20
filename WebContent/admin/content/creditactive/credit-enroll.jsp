<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<title>报名项管理</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/css/common.css" /> 
	<style>
	body {
    	width: 100%;
	}
</style>
</head>

<body>
	<div id="search-panel">
	<div id="enrollAdd" title="活动报名表单设置">
	<div class="om-widget om-panel" style="margin-top:0px;margin-left:0px;">
		<div class="om-panel-header">
				<span style="color:red;display: block;margin-bottom:10px;">活动名称、微信名、用户姓名、手机号，为必须字段，此处无须设置</span>
				注：项目名不超过50个字,提示不超过50个汉字，用户填写内容不超过100汉字,最多增加10个项目
		</div>
	</div>
		
		<form id="enrollAddForm" action="${ctx}/creditactivity/CreditEnrollControl?type=activeSave" method="post">
			<input type="hidden" name="memberCreditGuid" id ="memberCreditGuid" class="input-text">
			<input type="hidden" name="wxMemberCreditEnrollGuid" id ="wxMemberCreditEnrollGuid" class="input-text">
			<table>
				<tr>
					<td>项目 <span class="color_red"> * </span>
					</td>
					<td><input type="text" name="itemName" maxlength="50" id ="itemName" 
						class="input-text"> <span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>项目提示
					</td>
					<td><input type="text" name="itemNotice" maxlength="50" id="itemNotice"
						class="input-text"> <span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>排序<span class="color_red"> * </span>
					</td>
					<td><input type="text" name="itemSort" maxlength="2"  id="itemSort"
						class="input-text"> <span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>是否必填 <span class="color_red"> * </span>
					</td>
					<td><input type="radio" name="requiredItem" id="requiredItem"
						class="input-radio" value="0" checked="checked">
						否&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="requiredItem"
						id="requiredItem" class="input-radio" value="1">是 <span
						class="errorMsg"> </span></td>
				</tr>
			</table>
		<div class="om-widget om-panel" style="margin-top:0px;margin-left:0px;">
			<div class="om-panel-header">
					<span style="color:red;display: block;margin-bottom:10px;">注：项目名不超过50个字,提示不超过50个汉字，用户填写内容不超过100汉字,最多增加10个项目</span>
			</div>
		</div>
			<div>
				<input type="submit" id="enrollSubmit" style="width:100px" value="新增确认" >
				<input type="submit" id="enrollModifySubmit" style="width:100px" value="修改确认" >
				<input type="reset" id="reset" style="display:none">
			</div>
		</form>
	</div>
	</div>
	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin: 5px 0 5px 0;">
		</div>
		<div style="width: 100%;">
			<table id="grid">
			</table>
		</div>
	</div>
	<span style="display:none" id="actReturnGuid" ></span>
	<script type="text/javascript">
	var type=null;
	var memberCreditGuid = "${param.memberCreditGuid}";
	$("#memberCreditGuid").val(memberCreditGuid);
	
	$("#enrollModifySubmit").hide();
	    
		//$(document).ready(function() {
		$(function(){
			$.getJSON("${ctx}/creditactivity/CreditEnrollCheckStatus?memberCreditGuid=" + $("#memberCreditGuid").val(),
               function(data) {
                   if (!data.success) {
                   	$("input[name=itemName]").attr("disabled",true);
               		$("input[name=itemNotice]").attr("disabled",true);
               		$("input[name=itemSort]").attr("disabled",true);
               		$("#enrollModifySubmit").attr("disabled",true);
               		$("#enrollSubmit").attr("disabled",true);
                  		$.omMessageBox.alert({
                  			type: 'error',
       		            title: '失败',
       		            content: data.message,
       		        });
                   }
               });
			
		    $('#grid').omGrid({
		        limit: 9,
		        singleSelect: false,
		        width: '100%',
		        height: 300,
		        showIndex: false,
		        dataSource: "${ctx}/creditactivity/CreditEnrollControl?type=getData&memberCreditGuid="+memberCreditGuid,
		        colModel: [{
		            header: '项目名',
		            name: 'itemName',
		            align: 'left',
		            width: 200,
		            renderer: function(colValue, rowData, rowIndex) {
		                return '<a href="javascript:;" onclick="editActive('+rowIndex+')">' + colValue + '</a>';
		            }
		        },
		        {
		            header: '项目提示',
		            name: 'itemNotice',
		            align: 'left',
		            width: 200
		        },
		        {
		            header: '排序',
		            name: 'itemSort',
		            align: 'center',
		            width: 80
		        },
		        {
		            header: '是否必填',
		            name: 'requiredItem',
		            align: 'center',
		            width: 50,
		            renderer: function(colValue, rowData, rowIndex) {
		                if (colValue == 1) {
		                    return '是';
		                }
		                return '否';
		            }
		        },
		        {
		            header: '添加人',
		            name: 'createdBy',
		            align: 'center',
		            width: 80
		        },
		        {
		            header: '添加时间',
		            name: 'createdOn',
		            align: 'center',
		            width: 120
		        }],
		        autoFit: true
		    });
	
		    $('#buttonbar').omButtonbar({
		        btns: [
		        {
		            label: "添加",
		            id: "enroll_Add",
		            icons: {
		                left: '${ctx}/scripts/image/add.png'
		            },
		            onClick: function() {
		                clearForm();
		                $("#enrollSubmit").show();
						$("#enrollModifySubmit").hide();
				        $("input[name=itemName]").val("");
				        $("input[name=itemNotice]").val("");
				        $("input[name=itemSort]").val("");
				        $("input[name=requiredItem][value=0]").attr("checked", "checked");
		                //$("#enrollAdd").omDialog("open");
		                
				        $.getJSON("${ctx}/creditactivity/CreditEnrollCheckStatus?memberCreditGuid=" + $("#memberCreditGuid").val(),
		                function(data) {
		                    if (!data.success) {
		                    	$("input[name=itemName]").attr("disabled",true);
		                		$("input[name=itemNotice]").attr("disabled",true);
		                		$("input[name=itemSort]").attr("disabled",true);
		                		$("#enrollModifySubmit").attr("disabled",true);
		                		$("#enrollSubmit").attr("disabled",true);
		                   		$.omMessageBox.alert({
		                   			type: 'error',
		        		            title: '失败',
		        		            content: data.message,
		        		        });
		                    }
		                });
		            }
		        },
		        {
		            label: "删除",
		            id: "button-remove",
		            icons: {
		                left: '${ctx}/scripts/image/remove.png'
		            },
		            onClick: function() {
		            	var val = true;
		            	
		            	//将ajax设置为同步
		            	$.ajaxSetup({ async: false});
            			$.getJSON("${ctx}/creditactivity/CreditEnrollCheckStatus?memberCreditGuid=" + $("#memberCreditGuid").val(),
                        	function(data) {
                            if (!data.success) {
                            	$("input[name=itemName]").attr("disabled",true);
                        		$("input[name=itemNotice]").attr("disabled",true);
                        		$("input[name=itemSort]").attr("disabled",true);
                        		$("#enrollModifySubmit").attr("disabled",true);
                           		$.omMessageBox.alert({
                           			type: 'error',
                		            title: '失败',
                		            content: data.message,
                		        });
                           		val = false;
                            }
                        });
            			if(!val)
            				return;
            			
		                var dels = $('#grid').omGrid('getSelections', true);
		                if (dels.length <= 0) {
		                    $.omMessageBox.alert({
		                        type: 'alert',
		                        title: '提示',
		                        content: '请选择要删除的项目！'
		                    });
		                    return;
		                }
		                $.omMessageBox.confirm({
		                    title: '确认删除',
		                    content: '您确认要删除吗？',
		                    onClose: function(v) {
		                        if (v) {
		                            var items = "";
		                            for (var i = 0; i < dels.length; i++) {
		                                items += "item=" + dels[i].wxMemberCreditEnrollGuid;
		                                if (i < dels.length - 1) {
		                                    items += "&";
		                                }
		                            }
		                            $.getJSON("${ctx}/creditactivity/CreditEnrollControl?type=delete&"+ items,
		                            function(data) {
		                                if (data.success) {
		                                    $.omMessageBox.alert({
		                                        type: 'success',
		                                        title: '成功',
		                                        content: data.message,
		                                        onClose: function(v) {
	
		                                            $('#grid').omGrid('reload');
		                                        }
		                                    });
		                                } else {
		                                    $.omMessageBox.alert({
		                                        type: 'error',
		                                        title: '失败',
		                                        content: data.message
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
		    
		    $("#enrollAddForm").validate({
		        rules: {
		        	itemName: {
		                required: true,
		                maxlength: 50
		            },
		            itemNotice: {
		                required: false,
		                maxlength: 50
		            },
		            itemSort: {
		                required: true,
		                digits:true,
		                maxlength: 2
		           	}
		        },
		        messages: {
		        	itemName: {
		                required: "请输入项目名称",
		                maxlength: "最多50个汉字"
		            },
		            itemNotice: {
		                //required: "请输入活动摘要",
		                maxlength: "最多50个汉字"
		            },
		            itemSort: {
		                required: "请输入项目排序",
		                digits: "只能输入整数",
		                maxlength: "最大支持输入2位整数"
		                
		            }
		        },
		        submitHandler: function() {
		            $('#enrollAddForm').omAjaxSubmit({
		                beforeSubmit: showRequest,
		                success: showResponse
		            });
		            return false;
		        }
		    });
		    $("#search-panel").omPanel({
		        title: "活动报名表单设置",
		        collapsed: false//,
		        //collapsible: false
		    });
		});
	
		function editActive(rowIndex) {
			var data = $('#grid').omGrid('getData');
			var obj = data.rows[rowIndex];
			
			$("#wxMemberCreditEnrollGuid").val(obj.wxMemberCreditEnrollGuid);
			
	        clearForm("edit");
	        
	        $.getJSON("${ctx}/creditactivity/CreditEnrollCheckStatus?memberCreditGuid=" + $("#memberCreditGuid").val(),
            function(data) {
                if (!data.success) {
                	$("input[name=itemName]").attr("disabled",true);
            		$("input[name=itemNotice]").attr("disabled",true);
            		$("input[name=itemSort]").attr("disabled",true);
            		$("#enrollModifySubmit").attr("disabled",true);
               		$.omMessageBox.alert({
               			type: 'error',
    		            title: '失败',
    		            content: data.message,
    		        });
                }
            });

	        $("input[name=itemName]").val(obj.itemName);
	        $("input[name=itemNotice]").val(obj.itemNotice);
	        $("input[name=itemSort]").val(obj.itemSort);
	        if (obj.requiredItem == 1) {
	            $("input[name=requiredItem][value=1]").attr("checked", "checked");
	        } else {
	            $("input[name=requiredItem][value=0]").attr("checked", "checked");
	        }

	        //setContent(obj.actContent);
		}
	
		function clearForm(type) {
				$("#enrollSubmit").hide();
				$("#enrollModifySubmit").show();
				
				$("input[name=itemName]").val("");
				$("input[name=itemNotice]").val("");
				$("input[name=itemSort]").val("");
				$("input[name=requiredItem][value=0]").attr("checked",true);
				
				$("input[name=itemName]").attr("disabled",false);
        		$("input[name=itemNotice]").attr("disabled",false);
        		$("input[name=itemSort]").attr("disabled",false);
        		$("#enrollModifySubmit").attr("disabled",false);
		}
		function showRequest(formData, jqForm, options) {
		    return true;
		}
		function showResponse(responseText, statusText, xhr, $form) {
		    console.log(responseText);
		    if (responseText.success) {
		        $("#actReturnGuid").text(responseText.result);
		        clearForm();
		        $.omMessageBox.alert({
		            type: 'success',
		            title: '成功',
		            content: responseText.message,
		            onClose: function(v) {
		                $("#enrollAdd").omDialog("close");
		                $('#grid').omGrid('reload');
		                clearForm();
		               // $.omMessageBox.alert({
	                    //    title: '提示信息',
	                     //   content: responseText.message
	                    //});
		            }
		        });
		    } else {
		        $.omMessageBox.alert({
		            type: 'error',
		            title: '失败',
		            content: responseText.message
		        });
		    }
		}
/*
		$('#enrollSubmit').bind('click', function(){
			
			//var actType=$('input:radio[name="actType"]:checked').val();
			//var actType2=$('input:radio[name="actType2"]:checked').val();
			var itemName=$("#itemName").val();
			var itemNotice=$("#itemNotice").val();
			var itemSort=$("#itemSort").val();
			var requiredItem = $("input[name='requiredItem']:checked").val();
			var memberCreditGuid=$("#memberCreditGuid").val();
			
			if (itemName==''){
				return;
			}
			if (itemNotice==''){
				return;				
			}
			if (itemSort==''){
				return;				
			}

			$.ajax({
				type : 'POST',
				url : "${ctx}/creditactivity/CreditEnrollControl",
				async : false,
				data : {
					type : "activeSave",
					itemName : itemName,
					itemNotice : itemNotice,
					itemSort : itemSort,
					requiredItem : requiredItem,
					memberCreditGuid : memberCreditGuid
				},
				dataType : "json",
				success : function(data) {
                    if (data.success) {
                        $.omMessageBox.alert({
                            type: 'success',
                            title: '成功',
                            content: data.message,
                            onClose: function(v) {

                                $('#grid').omGrid('reload');
                            }
                        });
                    } else {
                        $.omMessageBox.alert({
                            type: 'error',
                            title: '失败',
                            content: data.message
                        });
                    }
                }
			});
		});
		
		$('#enrollModifySubmit').bind('click', function(){
			
			//var actType=$('input:radio[name="actType"]:checked').val();
			//var actType2=$('input:radio[name="actType2"]:checked').val();
			var itemName=$("#itemName").val();
			var itemNotice=$("#itemNotice").val();
			var itemSort=$("#itemSort").val();
			var requiredItem = $("input[name='requiredItem']:checked").val();
			var wxMemberCreditEnrollGuid=$("#wxMemberCreditEnrollGuid").val();
			var memberCreditGuid=$("#memberCreditGuid").val();
        		
			if (itemName==''){
				return;
			}
			if (itemNotice==''){
				return;				
			}
			if (itemSort==''){
				return;				
			}

			$.ajax({
				type : 'POST',
				url : "${ctx}/creditactivity/CreditEnrollControl",
				async : false,
				data : {
					type : "activeUpdata",
					itemName : itemName,
					itemNotice : itemNotice,
					itemSort : itemSort,
					requiredItem : requiredItem,
					wxMemberCreditEnrollGuid : wxMemberCreditEnrollGuid,
					memberCreditGuid : memberCreditGuid
				},
				dataType : "json",
				success : function(data) {

					
                    if (data.success) {
 					   
                        $.omMessageBox.alert({
                            type: 'alert',
                            title: '成功',
                            content: data.message,
                            onClose: function(v) {
								
                                $('#grid').omGrid('reload');
                            }
                        });
                    } else {
                        $.omMessageBox.alert({
                            type: 'error',
                            title: '失败',
                            content: data.message
                        });
                    }
                }		
			});
		});
*/
	</script>
</body>

</html>
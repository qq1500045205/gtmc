<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>调查问卷</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>

<link rel="stylesheet" type="text/css"  href="${ctx }/scripts/css/common.css" />
</head>
<body>
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
		<div id="buttonbar" class="om-buttonbar" style="margin: 5px 0 5px 0;"></div>
		<div style="width: 100%;">
			<table id="grid"></table>
		</div>
	</div>
	
	<div id="dialog-add"  title="添加题目">
		<form action="${ctx }/question/addQuestion" id="addForm" method="post">
			<table>
				<tr>
					<td width="20%">题目<span class="color_red">*</span></td>
					<td width="80%"><textarea name="questionValue" id="questionValue" class="area-text" cols="3" ></textarea><span class="errorMsg"></span></td>
				</tr>
				<tr>
					<td>题目类型<span class="color_red">*</span></td>
					<td>
						<input name="type" id="type" class="input-combo" ><span class="errorMsg"></span>
					</td>
				</tr>
			</table>
			<div>
				<input type="submit" id="sub" name="提交" class="button-css">						
			</div>
		</form>
	</div>
	
	<div id="dialog-edit"  title="修改题目" style="width:100%;height:100%;">
		<iframe id="edit-question-frame" style="width:400px;height:98%;"></iframe>
	</div>
	
	<script type="text/javascript">

	var editQuestion = function(questionGuid, questionValue, type) {
		//console.log(questionGuid, questionValue, type);
		$("#dialog-edit").omDialog("open");
		$("#edit-question-frame").attr("src", "${ctx}/admin/content/questionnaire/edit-question.jsp?questionGuid="+questionGuid+"&questionValue="+questionValue+"&type="+type);
		
	};
	
	$(document).ready(function() {
		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height:400,
			showIndex : true,
			dataSource : "${ctx}/ajax/griddata.action?type=question",
			colModel : [ {
				header : '题目编号',
				name : 'questionGuid',
				align : 'left',
				width : 60,
				
			}, {
				header : '题目',
				name : 'questionValue',
				align : 'left',
				width : 400,
				renderer : function(colValue, rowData, rowIndex) {
					console.log(rowData);
					return '<a href="javascrpit:void;" onclick="editQuestion(\''+rowData.questionGuid+'\',\''+rowData.questionValue+'\',\''+rowData.type+'\');">' + colValue + '</a>';
				}
			}, {
				header : '题目类型',
				name : 'type',
				align : 'left',
				width : 100,
				renderer : function(colValue, rowData, rowIndex) {
	                   switch (colValue) {
	                   		case 1: return '是否题';
	                   		case 2: return '10分制题';
	                   		case 3: return '简述题';
	                   		default: return '';
	                   } 
	               }
			}, {
				header : '添加人',
				name : 'createdBy',
				align : 'left',
				width : 80
			}, {
				header : '添加时间',
				name : 'createdOn',
				align : 'left',
				width : 150
			},  {
				header : '统计',
				name : 'questionGuid',
				align : 'left',
				width : 'autoExpand', 
          	 	renderer:function(colValue, rowData, rowIndex){
               		return '<a href="${ctx}/admin/content/questionnaire/answer-list.jsp?questionGuid=' + colValue + '">答题情况</a>';
         		}
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
					$("#dialog-add").omDialog("open");
				 	$('#type').omCombo({
				 		editable : false,
		            	dataSource : [ {text : '是否题', value : '1'}, 
		                               {text : '10分制题', value : '2'}, 
		                               {text : '简述题', value : '3'} ],
		            });
				 	//addEditor("questionValue");
				}
			},{
				label : "删除",
				id : "button-delete",
				icons : {
					left : '${ctx}/scripts/image/remove.png'
				},
				onClick : function() {
					var dels = $('#grid').omGrid('getSelections', true);
	                if (dels.length <= 0) {
	                    $.omMessageBox.alert({
	                        type: 'alert',
	                        title: '提示',
	                        content: '请选择要删除的题目！'
	                    });
	                    return;
	                }
	                $.omMessageBox.confirm({
	                    title: '确认删除',
	                    content: '删除后相关的答题信息也将被删除,您确认要删除吗？',
	                    onClose: function(v) {
	                        if (v) {
	                            var items = "";
	                            for (var i = 0; i < dels.length; i++) {
	                                items += "items=" + dels[i].questionGuid;
	                                if (i < dels.length - 1) {
	                                    items += "&";
	                                }
	                            }
	                            $.getJSON("${ctx}/question/deleteQuestion?" + items,
	                            function(data) {
	                                console.log(data);
	                                $.omMessageBox.alert({
	        	                        type: 'alert',
	        	                        title: '提示',
	        	                        content: data.message
	        	                    });
	                                $('#grid').omGrid("reload");
	                            });
	                        }
	                    }
	                });
				}
			}]
		});
		
	    $("#dialog-add").omDialog({
	        autoOpen: false,
	        height: 500,
	        width: 600,
	        modal: true,
	        onClose : function(event) {
	        	
	        }
	    });
	    
	    $("#dialog-edit").omDialog({
	        autoOpen: false,
	        height: 500,
	        width: 600,
	        modal: true,
	        onClose : function(event) {
	        	
	        }
	    });
	    
	    $("#addForm").validate({
            rules : {
            	questionName : {
                	required : true,
                },
                type : {
                    required : true
                }
            },
            messages : {
            	questionName : {
                	required : "请输入题目",
                },
                type : {
                    required : "请选择题目类型"
                }
            },
            showErrors: function(errorMap, errorList) {
                if(errorList && errorList.length > 0){  //如果存在错误信息
                    $.each(errorList,function(index,obj){
                    	if($(obj.element).attr("class") == "input-combo"){
                    		$(obj.element).parent().parent().find("span.errorMsg").show();
                    		$(obj.element).parent().parent().find("span.errorMsg").html(obj.message);
                    	}else {
                    		$(obj.element).parent().find("span.errorMsg").show();
                    		$(obj.element).parent().find("span.errorMsg").html(obj.message);
                    	}
	                   });
                }else{
                    var obj = this.currentElements;
                    if($(obj[0]).attr("class") == "input-combo"){
                    	$(obj[0]).parent().parent().find("span.errorMsg").hide();
                		$(obj[0]).parent().parent().find("span.errorMsg").html("");
                	}else {
                		$(obj[0]).parent().find("span.errorMsg").hide();
                		$(obj[0]).parent().find("span.errorMsg").html("");
                	}
                }
			},
            submitHandler : function(){
            	$('#addForm').omAjaxSubmit(options);
            	$(this)[0].currentForm.reset();
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
            console.log(responseText);
            $("#dialog-add" ).omDialog("close");
            $('#grid').omGrid("reload");
            var json = eval(responseText);
            alert(json.message);
        }
	    
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
	});

</script>
</body>
</html>
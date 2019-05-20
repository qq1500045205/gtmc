<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>修改题目</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>

<link rel="stylesheet" type="text/css"  href="${ctx }/scripts/css/common.css" />
</head>
<body>
	<form action="${ctx }/question/editQuestion" id="editForm" method="post">
		<input type="hidden" name="questionGuid" id="questionGuid" />
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
	<script type="text/javascript">
		
		$("#editForm").validate({
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
            	$('#editForm').omAjaxSubmit(options);
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
            var json = eval(responseText);
            alert(json.message);
            parent.$("#dialog-edit" ).omDialog("close");
            parent.$("#grid" ).omGrid("reload");
        }
        
        var questionGuid = "${param.questionGuid}";
		var questionValue = "${param.questionValue}";
		var type = ${param.type};
		$("#dialog-edit" ).omDialog("open");
		$("#questionGuid").val(questionGuid);
		$("#questionValue").val(questionValue);
		
		$('#type').omCombo({
	 		editable : false,
        	dataSource : [ {text : '是否题', value : '1'}, 
                           {text : '10分制题', value : '2'}, 
                           {text : '简述题', value : '3'} ],
            value: type
        });
		
		// addEditor("questionValue");
	
	</script>
</body>
</html>
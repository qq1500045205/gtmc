<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>积分活动</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/css/common.css" /> 
</head>

<body>
	<div id="dialog-add" title="添加积分活动">
		<form id="addForm" >
			<table>
				<tr>
					<td width="20%">活动名称 <span class="color_red"> * </span>
					</td>
					<td width="80%"><label  id ="actName" > </label></td>
				</tr>
				<tr>
					<td width="20%">积分设置 <span class="color_red"> * </span>
					</td>
					<td width="80%"><label id="actCredit"></label></td>
				</tr>
				<tr>
					<td>活动摘要 <span class="color_red"> * </span>
					</td>
					<td><label id="actDesc" ></label></td>
				</tr>
				<tr>
					<td>是否支持报名 <span class="color_red"> * </span>
					</td>
					<td><input type="radio" name="actType" id="actType"
						class="input-radio" value="0" checked="checked"  disabled="disabled">
						不支持报名&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="actType"
						id="actType" class="input-radio" value="1" disabled="disabled"> 支持报名 <span
						class="errorMsg"> </span> <a href="javascript:;" id="setForm"
						style="display: none"> 设置报名表单 </a> <br /></td>
				</tr>
				<tr>
					<td>车主相关 <span class="color_red"> * </span>
					</td>
					<td><input type="radio" name="actType2" id="actType2"
						class="input-radio" value="0" checked="checked" disabled="disabled">
						针对非车主&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="actType2"
						id="actType2" class="input-radio" value="1" disabled="disabled"> 针对车主 <span
						class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>开始时间 <span class="color_red"> * </span>
					</td>
					<td><label  id="startOn" ></label></td>
				</tr>
				<tr>
					<td>结束时间 <span class="color_red"> * </span>
					</td>
					<td><label id="endOn" ></label></td>
				</tr>
				<tr>
					<td>活动封面</td>
					<td><img id="preview" src="${ctx }/scripts/image/pic.png"
						style="width: 100px; height: 100px; border: 1px solid #ddd;"><input
						type="hidden" name="actImage" id="pic" style="display: inline">
					</td>
				</tr>
				<tr>
					<td>活动详情 <span class="color_red"> * </span>
					</td>
<!-- 					<td><div id="actContent"  style="width: 100%; height: 200px;"></div></td> -->
					<td><div id="actContent"  style="width: 100%;"></div></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
	var obj=null;
	var type=null;
	var memberCreditGuid ="${param.memberCreditGuid}";
	if (memberCreditGuid !=''){
		editActive(memberCreditGuid);
	}
	
		$(document).ready(function() {
		    clearForm();

		});
	
		
		
		function editActive(memberCreditGuid) {
			var CreditGuid = memberCreditGuid;
	        clearForm();
			$.ajax({
				type : 'POST',
				url : "${ctx }/creditactivity/creditActive.action",
				async : false,
				data : {
					type : "getData",
					memberCreditGuid : CreditGuid	
				},
				dataType : "json",
				success : function(json) {
					obj =json.result;
			        $("#actName").text(obj.actName);
			        $("#actCredit").text(obj.actCredit);
			        $("#actDesc").text(obj.actDesc);
			        if (obj.actType == 1) {
			            $("input[name=actType][value=1]").attr("checked", "checked");
			        } else {
			            $("input[name=actType][value=0]").attr("checked", "checked");
			        }
			        if (obj.actType2 == 1) {
			            $("input[name=actType2][value=1]").attr("checked", "checked");
			        } else {
			            $("input[name=actType2][value=0]").attr("checked", "checked");
			        }
			        $("#startOn").text(obj.startOn);
			        $("#endOn").text(obj.endOn);
			        if (obj.actImage) {
						var temp = "${ctx}" + obj.actImage;
			           $("#preview").attr('src', temp);
			        }
			        //$("#actContent").text(obj.actContent);
			        $("#actContent").html(obj.actContent);
				}
			});		
	        
		}
		
		function clearForm() {
				$("#dialog-add").attr("title","查询品牌活动");
		}
		
        function showRequest(formData, jqForm, options) {
            return true;
        }
        function showResponse(responseText, statusText, xhr, $form) {
		    console.log(responseText);
		    if (responseText.success) {
                $.omMessageBox.alert({
                    title: '成功',
                    content: responseText.message
                });
		    } else {
		        $.omMessageBox.alert({
		            type: 'error',
		            title: '失败',
		            content: responseText.message
		        });
		    }
        }
	
	</script>
</body>

</html>
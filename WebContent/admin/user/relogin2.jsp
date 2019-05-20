<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>

<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>重新登录</title>

<link rel="shortcut icon" href="${ctx}/template/assets/ico/favicon.png">
<!-- Bootstrap core CSS -->
<link href="${ctx}/template/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/template/dist/css/bootstrap-combined.min.css"
	rel="stylesheet">
<!-- Bootstrap theme -->
<link href="${ctx}/template/dist/css/bootstrap-theme.min.css"
	rel="stylesheet">

<!-- Placed at the end of the document so the pages load faster -->
<script src="${ctx}/scripts/jquery-2.0.js"></script>
<script src="${ctx}/scripts/kindeditor/kindeditor-min.js"></script>
<script src="${ctx}/template/dist/js/bootstrap.min.js"></script>
<style>
.form-horizontal .control-label {
	float: left;
	width: 100px;
	padding-top: 5px;
	text-align: right;
}

.form-horizontal .controls {
	margin-left: 80px;
}

.input-xlarge {
	width: 200px;
	margin-left: 20px;
}

.input-code {
	width: 100px;
	margin-left: 20px;
}

.img-code {
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	border: 1px solid #ddd;
	height: 30px;
}

.modal {
	height: 300px;
	overflow: hidden;
}

.modal-body {
	height: 80%;
}
</style>
</head>

<body>
	<a id="modal-598025" href="#modal-container-535983" role="button"
		class="btn hide" data-toggle="modal">ddd</a>

	<div id="modal-container-535983" class="modal fade" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="overflow: none;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">X</button>
			<h3 id="myModalLabel">回话已过期，请重新登录</h3>
		</div>
		<div class="modal-body">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<form class="form-horizontal" action="" id="reloginForm">
							<fieldset>
								<div class="control-group">
									<label class="control-label" for="input01">用户名</label>
									<div class="controls">
										<input type="text" name="loginName" class="input-xlarge"
											disabled="disabled" value="magenm" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="input01">密码</label>
									<div class="controls">
										<input type="text" name="userPwd" class="input-xlarge" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="input01">验证码</label>
									<div class="controls">
										<input type="text" name="validator" class="input-code" /> <img
											id='validator' alt="" src="${ctx}/web/securityCode"
											class="img-code" /> <a href='#this'
											onclick='$("#validator").attr("src","${ctx}/web/securityCode?="+Math.random());'>å·æ°</a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"></label>
									<div class="controls">
										<button class="btn btn-success" style="margin-left: 20px;">重新登录</button>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$("#modal-598025").click();
		$("#reloginForm").submit(function() {
			var url = "${ctx }/admin/relogin";
			var name = $("input[name=loginName]").val();
			var pwd = $("input[name=userPwd]").val();
			var code = $("input[name=validator]").val();

			if (name.trim().length == 0) {
				alert("非法登录");
				return false;
			}

			if (pwd.trim().length == 0) {
				alert("密码不能为空");
				return false;
			}

			if (code.trim().length == 0) {
				alert("验证码不能为空");
				return false;
			}

			$.getJSON(url, {
				loginName : name,
				userPwd : pwd,
				validator : code
			}, function(data) {
				if (data.success) {

				} else {

				}
			});

			return false;
		});
	</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登陆</title>
<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-mouse.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-draggable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-position.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-messagebox.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-button.js"></script>
<link rel="stylesheet" href="${ctx }/scripts/themes/default/om-all.css"> 

<style type="text/css">
body {
	font-family: "微软雅黑",Arial!important;
	font-size: 14px;
	background: #eee;
}

#out-box {
	width: 300px;
	height: 300px;
	margin: 0 auto;
}

.button-css {
	width: 100%;
	height: 30px;
	margin-top: 10px;
	cursor: pointer;
	float: right;
	background-color: #00A1CB;
	border: 1px solid #007998;
	color: white;
	text-shadow: 0 -1px 1px rgba(0, 40, 50, 0.35);
	border-radius: 3px;
	box-shadow: 0 1px 0 rgba(255,255,255, 0.5) inset;
}
.button-css:hover{
	background-color: #0094bb;
}
#loginForm{
	padding:10px;
}
.box {
	border-radius: 5px;
	border: 1px solid #ccc;
	background: #fff;
	padding: 20px;
	box-shadow: 0 1px 0 rgba(255,255,255,.5);
}

.box .title {
	border-bottom: 1px solid #eee;
	margin: 20px;
	font-size: 20px;
	padding: 10px;
	font-weight: bold;
}

.inpt {
	border: 1px solid #ddd;
	background: #f8f8f8;
	border-radius: 3px;
	font-size:14px;
	width: 300px;
	color: #999; 
	padding:5px;
	color:#666;
}

.inpt:focus {
	background: #fff; 
	outline:#0094bb solid 2px;
	
}
</style>

<script type="text/javascript">
	if (window.location.href != window.top.location.href) {
		window.top.location.href = window.location.href;
	}
</script>

</head>
<body>
	<div id='out-box'>
		<form action='${ctx}/admin/login' method='post' id="loginForm">
			<table class="box">
				<tr>
					<td class="title">登陆</td>
				</tr>
				<tr>
					<td>用户名</td>
				</tr>
				<tr>
					<td><input type='text' name='loginName' class="inpt" /></td>
				</tr>
				<tr>
					<td>密码</td>
				</tr>
				<tr>
					<td><input type='password' name='userPwd' class="inpt" /></td>
				</tr>
				<tr>
					<td>验证码</td>
				</tr>
				<tr>
					<td><input type='text' name='validator' maxlength='4'
						class="inpt" style="width: 100px" /> <img id='validator' alt=""
						src="${ctx}/common/securityCode" /> <a href='#this'
						onc^lick='$("#validator").attr("src","${ctx}/common/securityCode?="+Math.random());'>刷新</a></td>
				</tr>
				<tr>
					<td><input type='submit' value='登陆' id='submit' class='button-css' /></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
		if ('${message}' != '' && "success" != "${message}") {
			$.omMessageBox.alert({
				content : '${message}'
			});
		}

		$("#loginForm").submit(function() {
			var name = $("input[name=loginName]").val();
			var pwd = $("input[name=userPwd]").val();
			var code = $("input[name=validator]").val();

			if (name.trim().length == 0) {
				$.omMessageBox.alert({
					content : '用户名不能为空！'
				});
				return false;
			}

			if (pwd.trim().length == 0) {
				$.omMessageBox.alert({
					content : '密码不能为空！'
				});
				return false;
			}

			if (code.trim().length == 0) {
				$.omMessageBox.alert({
					content : '验证码不能为空！'
				});
				return false;
			}
			
			$.getJSON("${ctx }/admin/ajxlogin", {
				loginName : name,
				userPwd : pwd,
				validator : code
			}, function(data) {
				if (data.success) {
					location.href = '${ctx}'+data.result;
				} else {
					$.omMessageBox.alert({
						content : data.message
					});
				}
			});

			return false;
		});
	</script>
</body>
</html>
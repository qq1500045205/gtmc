<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1,minimum-scale=1.0,user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">

<link rel="shortcut icon" href="${ctx}/template/assets/ico/favicon.png">
<script src="${ctx}/scripts/jquery-2.0.js"></script>
<link href="${ctx}/template/dist/css/form_gtmc.css" rel="stylesheet"> 
<!-- Bootstrap core CSS -->
<link href="${ctx}/template/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/template/dist/css/bootstrap-combined.min.css" rel="stylesheet">


<!-- Bootstrap theme -->
	<link href="${ctx}/template/dist/css/bootstrap-theme.min.css" rel="stylesheet"> 
	<script src="${ctx}/template/dist/js/bootstrap.min.js"></script>

<script>
	window.openid = "${empty(wxopenid)?param.wxopenid:wxopenid}";
	window.ctx="${ctx}";
</script>
<script src="${ctx}/scripts/common.js"></script>
<script src="${ctx}/scripts/wx.js"></script>
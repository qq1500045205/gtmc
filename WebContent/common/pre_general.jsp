<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1,minimum-scale=1.0,user-scalable=no">

<link rel="shortcut icon" href="${ctx}/template/assets/ico/favicon.png">
<script src="${ctx}/scripts/jquery-2.0.js"></script>
<script src="${ctx}/scripts/jquery.scrollto.js"></script>
<link href="${ctx}/template/dist/css/style.css" rel="stylesheet"> 
<link href="${ctx}/scripts/css/m/form.css?Asdf" rel="stylesheet"> 
<script>
	window.openid = "${empty(wxopenid)?param.wxopenid:wxopenid}";
	window.ctx="${ctx}";
	window.GZHHASH = '##GZHHASH##';
	window.DATA = '##DATA##';
	window.URL = '##URL##';
	window.GZHTYPE = '##GZHTYPE##';
</script>
<script src="${ctx}/scripts/common.js"></script>
<script src="${ctx}/scripts/wx.js"></script>
<script src="${ctx}/scripts/view.js"></script>
<script src="${ctx}/template/dist/js/js_general.js"></script>
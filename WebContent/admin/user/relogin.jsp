<%@ include file="/common/geturl.jsp"%>

<script type="text/javascript">
	$.ajaxSetup({
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		cache : false,
		complete : function(XHR, TS) {
			console.log(XHR, TS);
			var resText = XHR.responseText;
			if (resText != null && resText.indexOf("timeout") > 0) {
				top.window.open("${ctx}/admin/user/relogin2.jsp", "",
						"width=500,height=400")
				return;
			}
		}
	});
</script>



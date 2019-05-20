<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>系统参数设置</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx }/scripts/css/common.css" /> 
<style type="text/css">
	body{
		padding:20px;
		font-family:"微软雅黑",Arial;
		box-sizing:border-box;
	}
	div{
		box-sizing:border-box;
	}
	button{ 
		width:200px;
		height:150px;
		margin:5px;
		padding: 8px;
		box-sizing:border-box;
		background:#eee;
		border:1px solid #ddd;
		box-shadow:0 1px 1px rgba(255,255,255,.9);
		border-radius:5px;
		color: #666; 
		font-size: 14px;
		font-family:"微软雅黑",Arial;  
	}
	button:hover{
		background:#5EB0D5;
		cursor:pointer;
		color:#fff;
	}
	.cell{
		padding:5px 10px;
		margin:10px 5px; 
		width:100%;
		background:#f8f8f8;
		border:1px solid #ddd;
		border-radius:5px;
	}
</style>
</head>
<body>

	<c:if test="${loginObject.roleType == 'ADMIN' }">
		<div class="cell">
			<h3>G-CLOUD数据</h3>
			<button class="btn" onclick="initQueryData('${ctx}/ajax/syncData?method=GetPROVINCEBYMEDIA')">同步省份数据</button> 
			<button class="btn" onclick="initQueryData('${ctx}/ajax/syncData?method=GetCITYBYMEDIA')">同步城市数据</button> 
			<button class="btn" onclick="initQueryData('${ctx}/ajax/syncData?method=GetDEALERSBYMEDIA')">同步销售店数据</button>
			<button class="btn" onclick="initQueryData('${ctx}/ajax/syncData?method=GetINFOBYMEDIA')">同步车型编码</button>
		</div>
	</c:if>
	<div class="cell">
		<h3>第三方数据</h3>
		<button class="btn" onclick="initQueryData('${ctx}/lllegal/createData')">违章查询数据初始化</button>
	</div>
<script type="text/javascript">
		//初始化违章查询数据
		function initQueryData(url) {
			$.omMessageBox.waiting({
                title:'请稍候',
                content:'服务器正在处理您的请求，请稍候...',
            });
			$.ajax({
				type : 'POST',
				url : url,
				success : function(data) {
					$.omMessageBox.waiting('close');
					if (data.success) {
						$.omMessageBox.alert({
							type:'success',
							title:'成功',
							content : data.message
						});
					} else {
						$.omMessageBox.alert({
							type:'error',
							title:'失败',
							content : data.message+"("+data.result+")"
						});
					}
				}
			});
		}
	</script>
</body>
</html>
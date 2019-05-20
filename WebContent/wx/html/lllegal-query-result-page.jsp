<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<%@ include file="/common/pre_general.jsp"%>
	<title>违章查询</title>
	 <script src="${ctx}/scripts/wx.js"></script>
	<style>
	.list{
		padding:10px;
		margin-top:10px;
	}  
	.date{
		color:#106dbb;
		line-height:30px; 
	}
	.title{
		margin:0;
		line-height:40px;
		text-align:left;
		border-bottom:1px dashed #83b9e6 ;
		font-size:16px;
		background: url("/gtmc_wx/scripts/image/search.png") no-repeat 5px 12px;
		background-size: 25px 25px;
		padding-left:30px;
	}
	</style>
</head>
<body>
	<form>
		<div id="testpage" class="content" style=''> 
				<h3 id="headerTitle" class="title">您的违章记录</h3> 
				<!-- /header --> 
				<div id="resultList" class="list">
					<div style="text-align:center">请稍候...</div>
				</div>
			</div> 
	</form>

<script>

	$(function() {
		var data = [];
		var title = "";
		$("#headerTitle").text("正在查询中..."); 
		
		$.ajax({
			type : 'POST',
			url : "${ctx}/lllegal/getResult",
			data : {
				id:"${param.id}",
				carString : "${param.carString}"
			},
			success : function(result) {
				var html = "";
				if (result.success) {
					data = 	result.result;			
					title = result.message;
					$("#headerTitle").text(title+"的违章信息");
					if(data.length == 0){
						html += '<h4>恭喜,暂未查询到您的违章信息</h4>';
					} else {
						for ( var i = 0; i < data.length; i++) { 
							html += '<div class="date">' + data[i].time + '</div>'+
									'<div>' + data[i].address + '</div>'+
									'<div><span class="label">类型：</span>' + data[i].violation_type + '<div>'+
									'<div><span class="label">扣分 </span>'+
										'<span style="color: red">'+ (data[i].point == -1 ? "未知" : data[i].point)+
									'</span></div>'+
									'<div><span class="label">罚款 </span>'+
										'<span style="color: red">' + data[i].fine + '元</span>'+
									'</div>'+
									'<div><span class="label">状态 </span>'+ 
											(data[i].handled ? "已处理" : "未处理") +
									'</div>'; 
						}
					}
				} else {
					html += '<h4">'+result.message+'</h4>';
				}
				$("#resultList").html(html);
			}
		});
	});
	
	var util = new Wx.Param();
	var gzhHash = util.getGZHHASH("##GZHHASH##");
	var openid = util.getOPENID();
	var stat = new Wx.Stat();
	stat.report('15-2', "违章查询结果", gzhHash, openid, {});
</script>
</body>
</html>

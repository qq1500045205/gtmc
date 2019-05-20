<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<title>品牌活动</title>
	<%@ include file="/common/pre_general.jsp"%> 
</head>
<body> 
	<div id="content_container"></div> 
	<div id="contentManager">
		<a style="display: none;" href="javascript:;" title="${ctx}/admin/content/active/list.jsp">品牌活动管理</a>
	</div>
	<input type="hidden" targetId="item_0" urlType="url" title="跳转至:" />

	<script type="text/javascript">
		var util = new Wx.Param();
		var gzhHash = util.getGZHHASH("##GZHHASH##");
		var openid = util.getOPENID();
		var url = util.getURL('##URL##', [ {
			targetId : "item_0",
			src : "brand-active-detail-page.jsp"
		}]);
		var data = [];
		$.ajax({
			type : 'POST',
			url : "${ctx}/activity/getActive",
			async : false,
			data : {
				gzhHash : gzhHash
			},
			dataType : "json",
			success : function(res) {
				console.log(res);
				if (res.success) {
					data = res.result;
				}
			}
		});

		var str = "";
		for ( var i = 0; i < data.length; i++) { 
				elementStr = '<a id="item_' + i + '" onclick="goPage(this,'+i+');">'+
									'<div class="pic-item" id="item_' + i + '" onclick="goPage(this,'+i+');">'+
										'<div class="pic">'+
											'<img alt="" src="${ctx}' + data[i].picSrc + '" style="height:150px;width:100%;">'+ 
										'</div>'+
										'<h3 class="title">'+ data[i].title + '</h3>'+
									'</div>'+
							'</a>'; 
						
			str += elementStr;
			console.log(data[i].title);
		}

		$("#content_container").html(str);
 
		function goPage(th, i) {
			$(th).attr("href", url[0].src + "?actGuid=" + data[i].actGuid);
			var storage = new Util.Storage();
			storage.set("activeData",data[i]);
		}
		
		var stat = new Wx.Stat();
    	stat.report('3-1', "品牌活动", gzhHash, openid, {});
	</script>
	<script src="${ctx}/template/dist/js/js_general.js"></script>
</body>
</html>
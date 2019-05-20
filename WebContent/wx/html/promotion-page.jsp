<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>促销活动</title>
<%@ include file="/common/pre_general.jsp"%> 
</head>

<body> 
	<div id="content_container"></div>

	<input type="hidden" targetId="item_0" urlType="url" title="跳转至" />
	<div id="contentManager">
		<a style="display: none;" href="javascript:;" id="promotion" title="${ctx}/admin/content/promotion/list.jsp">促销管理</a>
	</div>
	<div id="define_url"></div>
	<!-- /container -->

	<!-- ================================================== -->

	<script type="text/javascript">
		var util = new Wx.Param();
		var gzhHash = util.getGZHHASH("##GZHHASH##");
		var openid = util.getOPENID();
		var url = util.getURL('##URL##', [ {
			targetId : "item_0",
			src : "promotion-detail-page.jsp"
		} ]);
		var src = "${ctx }/scripts/image/pic.png";
		var data = [];	
		$(function() { 
			loadData();
			function loadData() {
				console.log("load data");
				$.ajax({
					type : 'POST',
					url : "${ctx}/promo/list",
					data : {gzhHash: gzhHash},
					dataType : "json",
					success : function(result) {
						if( result.result.length == 0 ){
		    				//$('#content_container').html('<div style="padding:10px;">没有记录!</div>');
		    				return ;
		    			}
						if (result.success) {
							data = result.result;
							renderHtml();
						} else {
							alert("连接服务器失败，请重试！");
						}
					}
				});
			}

			function renderHtml() {
				var str = '' ;
				// TODO add li_click
				for ( var i = 0; i < data.length; i++) {
						elementStr = '<div class="pic-item" id="item_'+i+'" onclick="li_click('+i+')">'+
											'<div class="pic">'+
												'<img alt="" src="${ctx}' + data[i].promotionPicture + '">'+
											'</div>'+
												'<h4 class="title">活动主题：'+ data[i].promotionTitle + '</h4>'+
												'<span>截至日期：'+ data[i].promotionDeadline + '</span>'+
												'<div>活动摘要：'+(data[i].promotionSummary==null ? "" : data[i].promotionSummary)+'</span>'+
										'</div>';
					str += elementStr;
				}
				$("#content_container").html(str); 
			}
		});

		function li_click(index) {
			window.localStorage['promotionData'] = JSON.stringify(data[index]);
			//var obj = JSON.parse(window.localStorage['promotionData']);
			var param = "promotionGuid="+data[index].promotionGuid;
			//window.location.href = url[0].src + "?" + param;
			Util.Browser.jump(url[0].src + "?" + param,"4-1");
		}	 
		var stat = new Wx.Stat();
    	stat.report('4-1', "促销信息", gzhHash, openid, {});
	</script>
	<script src="${ctx}/template/dist/js/js_general.js"></script>
</body>
</html>

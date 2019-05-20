<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title></title>
<%@ include file="/common/pre_general.jsp"%>

<style>
body {
	overflow-x: hidden;
	min-width: 300px;
	font-family: "微软雅黑", Arial;
	font-size: 13px;
	padding: 0;
}

#content_container {
	padding: 18px;
	margin-bottom: 60px;
	padding-top:5px;
}

img {
	max-width: 100%;
}

.titlename {
	font-size: 16px;
	color: #106dbb;
	font-weight: bold;
	font-size: 18px;
	display: block;
	height: auto;
}

#footer {
	position: fixed;
	bottom: 0px;
	width: 100%;
	min-width: 300px;
	text-align: center;
	background:rgb(233,243,250);
	padding-bottom:10px;
}

.btn-primary {
	min-width: 200px;
	line-height:25px;
}

.item_pic {
	background: #ccc;
	width: 100%;
	max-height: 180px;
	overflow: hidden;
	text-align: center;
	min-height:150px;
}
</style>
</head>

<body>
	<div id="content_container"></div>
	<div class="footer" id='footer' style='	'>
		<button id="sign_up_btn" type="button" class="btn_main" style="">预约试驾</button>
	</div>
	<input type="hidden" targetId="item_0" urlType="url" title="预约试驾 跳转至" />
	
	<div style="display:none;" id="data">{"promotionTitle":"##promotionTitle#", "promotionPicture": "##promotionPicture#", "promotionContent": "##promotionContent#"}</div>
    
    <input id='header_text_hidden' type="hidden" targetId="##promotionTitle#" dataType="value" inputType="text" title="促销标题" value="" >
    <input id='header_pic_hidden' type="hidden" targetId="##promotionPicture#" dataType="value" inputType="pic" title="促销图片图片" value="">
    <input id='body_text_hidden' type="hidden" targetId="##promotionContent#" dataType="value" inputType="textarea" title="促销内容" value="" >
	<!-- 
    ================================================== -->

	<script type="text/javascript">
		var util = new Wx.Param();
		var param = new Util.Param();
		var gzhHash = util.getGZHHASH("##GZHHASH##");
		var openid = util.getOPENID();
		var url = util.getURL('##URL##', [ {
			targetId : "item_0",
			src : "promotion-detail-page.jsp"
		} ]);

		var data = util.getDATA('##DATA##',{
			promotionTitle: "促销标题",
			promotionPicture: "${ctx}/static-data/pic/EZ.jpg",
			promotionContent: "促销内容"
		});
		renderHtml(data);
		window.document.title = data.promotionTitle;
		function renderHtml(data) {

			var elementStr = '<h4 class="titlename">' + data.promotionTitle
					+ '</h4>' + '<div class="item_pic">'
					+ '<img src="'+data.promotionPicture+'"/>' + '</div>'
					+ '<div style="height:auto;margin-top:10px">' + '<span>'
					+ data.promotionContent + '</span>' + '</div>';

			$("#content_container").html(elementStr);

			$("#sign_up_btn").click(function() {
				Util.Browser.jump(url[0].src, "8-2-a");
			});
		}
		
		var stat = new Wx.Stat();
		stat.report('8-2-a', "金融购车详情", gzhHash, openid, {});
	</script>
</body>
</html>

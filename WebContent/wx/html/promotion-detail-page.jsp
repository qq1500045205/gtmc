<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>促销活动</title>
<%@ include file="/common/pre.jsp"%>

<style>
body {
	overflow-x: hidden;
	min-width: 300px;
	font-family: "微软雅黑", Arial;
	font-size: 13px;
	padding: 0;
}

#content_container {
	padding: 20px;
	margin-bottom: 60px;
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
	padding: 12px;
	bottom: 0px;
	width: 100%;
	min-width: 300px;
	text-align: center;
	height: 60px;
	background: rgba(0, 0, 0, .3)
}

.btn-primary {
	min-width: 130px;
	line-height:25px; 
}
#mobile_btn{
	position:relative;
	padding-left:20px;
}
#mobile_btn:before{
	content:"";
	background:url("${ctx}/scripts/image/ico-dial-w.png") no-repeat;
	background-size:24px 24px;
	width:24px;
	height:24px;
	position:absolute;
	left:10px;
	top:2px；
}
.item_pic {
	background: #ccc;
	width: 100%;
	max-height: 180px;
	min-height: 150px;
	overflow: hidden;
	text-align: center; 
}
</style>
</head>

<body>
	<div id="content_container"></div>
	<div id="footer">
		<button id="sign_up_btn" type="button" class="btn btn-primary" style="margin-left: 8px;">预约试驾</button>
		<button id="mobile_btn" type="button" class="btn btn-primary" style="margin-left: 8px;">电话咨询</button>
	</div>
	
	<div class="modal" id="myModal">
	  <div class="modal-body">
	    <p>销售热线：<span id="call">110</span></p>
	  </div>
	  <div class="modal-footer">
	    <a href="#" class="btn" id="cancelBtn">取消</a>
	    <a href="#" class="btn btn-primary" id="sureBtn">呼出</a>
	  </div>
	</div>
	<a class="btn hide" data-toggle="modal" href="#myModal" id="showModal">提示框</a>
	
	<input type="hidden" targetId="item_0" urlType="url" title="预约试驾 跳转至" />
	
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

		var data = JSON.parse(window.localStorage['promotionData']);
		if (data) {
			renderHtml(data);
		} else {
			$.ajax({
				type : 'POST',
				url : "${ctx}/promo/getOne",
				data : {
					promotionGuid : param.get("promotionGuid"),
				},
				dataType : "json",
				success : function(result) {
					console.log(result);
					if (result.success) {
						renderHtml(result.result);
					} else {
						alert("系统繁忙，稍后重试...");
					}
				}
			});
		}
		
		$('#cancelBtn').click(function(){
			$('#myModal').modal('toggle');
		});
		$('#sureBtn').click(function(){
			window.location.href = "tel:"+$("#call").text();
		});

		function renderHtml(data) {

			if (data.carType == 1) { //车型相关
				$("#sign_up_btn").show();
			} else {
				$("#sign_up_btn").hide();
			}

			var elementStr = '<h4 class="titlename">' + data.promotionTitle
					+ '</h4>' + '<div class="item_pic">'
					+ '<img src="${ctx}'+data.promotionPicture+'"/>' + '</div>'
					+ '<div style="height:auto;margin-top:10px">' + '<span>'
					+ data.promotionContent + '</span>' + '</div>';

			$("#content_container").html(elementStr);
			$("#sign_up_btn").click(function() {
				var param = "carTypeGuid=" + data.carTypeGuid;
				//window.location.href = url[0].src + "?" + param;
				Util.Browser.jump(url[0].src + "?" + param, "4-1");
			});
			$.ajax({
		        type: 'POST',
		        url: "${ctx}/dealerquery/queryDealerInfo?gzhHash="+gzhHash,
		        dataType: "json",
		        success: function(res) {
		            var result = eval(res);
		            if (result.message == "success") {
		                var result = eval(res); 
		                console.log(result);
		                data = result.result;	                
		                mapdata = data[0];
		                projectGuid = mapdata.projectGuid;
		                //dataRead(data[0]);
		                $("#call").text(data[0].mobile);

		            } else {
		                alert("连接服务器失败，请重试！");
		            }
		        }

		    });
			
			$("#mobile_btn").click(function() {
				$('#myModal').modal('toggle');
			});
		}
		var stat = new Wx.Stat();
		stat.report('4-2', "促销详情", gzhHash, openid, {});
	</script>
</body>
</html>

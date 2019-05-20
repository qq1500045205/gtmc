<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>${title}</title>
<%@ include file="/common/pre_general.jsp"%>

<style>
body {
	padding: 20px;
	overflow-x: hidden;
	font-family: "微软雅黑", Arial;
} 
img {
	max-width: 100%!important ; 
}

.title {
	color: black;
	font-size: 20px;
	font-weight: bold;
	word-break: normal;
	word-wrap: break-word;
	margin:0;
	line-height:40px;
} 
 
.date {
	display: inline-block;
	line-height:30px;
	color: #8C8C8C;
	font-size: 13px;
}
 
.theme-showcase>p>.btn {
	margin: 5px 0;
}

.btn-all-width{
	width:100%;	
}
.hide{
	display: none;
}
.modal{
	width:270px;
	height:200px;
	overflow: visible;
	overflow-y: none;
}
.modal-body {
 	max-height: 100px;
 	overflow: visible;
	overflow-y: none;
}
</style>

</head>

<body>
	<div id="content_container"></div>
	
	<div class="modal" id="myModal" style='display:none;'>
	 <div class="modal-header">
	    <a class="close" data-dismiss="modal">×</a>
	    <h3>提示</h3>
	  </div>
	  <div class="modal-body">
	    <p>参与车主活动，点击"确定"，完善车主信息</p>
	  </div>
	  <div class="modal-footer">
	    <a href="#" class="btn" id="cancelBtn">取消</a>
	    <a href="#" class="btn btn-primary" id="sureBtn">确定</a>
	  </div>
	</div>
	<a class="btn hide" data-toggle="modal" href="#myModal" id="showModal">提示框</a>
	
	<button class="btn_main hide" id="activeBtn">报名参加</button>
	<input type="hidden"  targetId="item_0" urlType="url" title="报名跳转至:" >
	
	<div id="openid"></div>
	<script type="text/javascript">
		var util = new Wx.Param();
		var param = new Util.Param();
		var gzhHash = util.getGZHHASH("##GZHHASH##");
		var openid = util.getOPENID();
		var url = util.getURL('##URL##', [ {
			targetId : "item_0",
			src : "brand-active-sign-page.jsp"
		} ]);
		
		var storage = new Util.Storage();
		//var activeData = storage.get('activeData');
		//if(null == activeData){
			if(param.get("actGuid")){
				$.ajax({
					type : 'POST',
					url : "${ctx}/activity/getOneActive",
					async : false,
					data : {
						actGuid : param.get("actGuid")
					},
					dataType : "json",
					success : function(data) {
						if (data.success) {
							activeData = data.result;
						}
					}
				});
			}else{
				activeData={
				        "picSrc": "\/uploads\/e6c3ee84-21f6-4785-aaca-cc27fed8c4c8.jpg",
				        "title": "品牌促销活动",
				        "date": "2014-02-28 15:52:13",
				        "actGuid": "8a10949b4477539d0144777ab23b0000",
				        "type": 0,
				        "type2": 0,
				        "content": "活动DEMO",
				        "desc": "不支持报名的活动",
				        "start": "2014-02-28",
				        "end": "2014-02-27"
				    };
			}
			
		//}
		var data = activeData;
		window.document.title = data.title;
		//支持报名，则移除hide
		if(data.type == 1){
			$("#activeBtn").removeClass("hide");
		}
		
		$('#cancelBtn').click(function(){
			$('#myModal').modal('toggle');
		});
		$('#sureBtn').click(function(){
			$('#myModal').modal('toggle');
			Util.Browser.jump(url[0].src+"?type2="+data.type2+"&actGuid="+data.actGuid,"3-3");
		});
		
		
		
		$("#activeBtn").click(function(){
			if(data.type2 == 1){
				var user  = new Wx.User();
				user.getUserInfo(function(info){
					//如果是车主
					if(info !=null &&  info.memberLevel == 2){
						Util.Browser.jump(url[0].src+"?type2="+data.type2+"&actGuid="+data.actGuid,"3-3");
					}else {
						//如果该用户还没注册或还没车信息
						//$('#myModal').modal('toggle');
						var y = confirm('参与车主活动，点击"确定"，完善车主信息');
						if(y){
							Util.Browser.jump(url[0].src+"?type2="+data.type2+"&actGuid="+data.actGuid,"3-3");
						}else{
							
						}
					} 
				});
			}else {
				//针对非车主
				Util.Browser.jump(url[0].src+"?type2="+data.type2+"&actGuid="+data.actGuid,"3-3");
			} 		
		});
     	
		var elementStr =  
			'<div>'+
	         	 '<h3 class="title">'+ data.title +'</h3>'+
				 '<div class="date">'+ data.date +'</div>'+
			  	 '<div>'+ 
		         	 data.content + 
		      	 '</div>'+
		     '</div>';
		$("#content_container").html(elementStr);
		
		var stat = new Wx.Stat();
    	stat.report('3-2', "品牌活动详情", gzhHash, openid, {});
    </script>

</body>
</html>

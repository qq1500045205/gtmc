<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">  
    <title>多图文消息预览</title> 
	
	<style>
		body {
		  padding: 10px;
		  font-size:14px;
		  background:#eee;
		  font-family:"微软雅黑",Arial;
		  min-width:300px;
		} 
		a,a:visited{
			color:inherit;
			text-decoration:none;
		}
		.item {
			min-height: 70px;
			background:#fff;
			padding: 10px; 
		}
		
		.div_header {
			border-bottom: 1px solid #ddd;
			font-weight: bold;
			font-size: 1em;
			display: block;
			padding: 10px; 
			height: auto;
			background:#fff;
			border-top-left-radius:5px;
			border-top-right-radius:5px;
		} 
		.div_body { 
			border-bottom: 1px solid #ddd;
		} 
		.div_footer {
			border-bottom-left-radius:5px;
			border-bottom-right-radius:5px;
		}
		
		.div_header:hover, .div_body:hover, .div_footer:hover {
			background-color: #f5f5f5;
		} 
		
		.header_pic {
			position: relative;
			background-color: rgba(0,0,0,0.8);
			text-align: center;
			min-height: 160px;
			width: 100%;
			overflow: hidden; 
		}
		img {
			max-width: 100%;
		}
		
		.img_caption {
			position: absolute;
			color: #ffffff;
			text-align: left;
			left: 0;
			right: 0;
			bottom: 0;
			padding: 10px 5px;
			font-size:16px;
			background: rgba(0, 0, 0, 0.75);
		}  
		.float_right_img {
			float: right;
			background: #ddd;
			overflow: hidden;
			height:70px;
			width:70px;
		} 
	</style>
</head>

<body>	 
	<div id="content_container">
	<!--  
	<a href="#" id="item_'+i+'">
		<div class="div_header">
		<div class="header_pic">
			<img alt="" id="pic_1" src="${ctx}/uploads/car1.jpg" style="height:200px;width:320px">
			<div class="img_caption">
				<span id="title_'+i+'">点击立即进入我的个人主页</span>
			</div>
		</div>
		</div>
	</a>
	<input type="hidden" targetId="item_' + i + '" urlType="url" title="'+ data[i].title + " 跳转至" +'"/>
	
 	<a href="#" id="item_'+i+'" >
	 	<div class=" div_body item" >
	 		<span>
	 			<span id="title_2">优惠促销 奥迪A4全系最高价5.24万</span>
	 		</span>
	 	<div class="float_right_img" >
	 		<img alt="" id="pic_2" src="${ctx} " style="height:70px;width:70px">
	 	</div>
	 	</div>
 	</a>
 	
 	<input type="hidden" targetId="item_'+i+'" urlType="url" title="'+ data[i].title + " 跳转至" +'"/>
 	 <a href="#" id="item_'+i+'" >
	 	<div class="div_footer item" >
	 		<span>
	 			<span id="title_3">奥马5系列进口</span>
	 		</span>
	 	<div class="float_right_img" >
	 		<img alt="" id="pic_3" src="${ctx} " style="height:70px;width:70px">
	 	</div>
	 	</div>
 	</a>
 	<input type="hidden" targetId="item_'+i+'" urlType="url" title="'+ data[i].title + " 跳转至" +'"/>
	-->
	</div> 
	 <!-- /container -->
   
    
    <script type="text/javascript">
		var defaultPicSrc = "/scripts/image/pic.png";
		var defaultItem = "图文";
		var defaultPic = "图片";
		var defaultTitle = "标题";
		var defaultContent = "内容";
		var defaultAuthor = "作者";
		var index = 0;
		var data = ${jsonObject};
		assignContent();
		function assignContent() {
			var str = "";
			for (i=0; i<data.length; i++){
				if (i==0) {
					elementStr = '<a href="#" id="item_'+i+'">'+
									'<div class="div_header">'+
										'<div class="header_pic">'+
											'<img alt="" id="pic_'+i+'" src="${ctx}' + (data[i].picSrc?data[i].picSrc:defaultPicSrc) + '" style="height:200px;width:320px">'+
											'<div class="img_caption">'+
												'<span id="title_'+i+'">' + (data[i].title ? data[i].title : (defaultTitle+i)) + '</span>'+
											'</div>'+
										'</div>'+
									'</div>'+
								'</a>'+
								'<input type="hidden" targetId="item_' + i + '" urlType="url" title="'+ data[i].title + " 跳转至" +'"/>';
				} else {
					elementStr = '<a href="#" id="item_'+i+'" >'+
									'<div class="'+(i==length-1? "div_footer": "div_body")+' item" >'+
										'<span>'+
											'<span id="title_'+i+'">' + (data[i].title ? data[i].title : (defaultTitle+i)) + '</span>'+
										'</span>'+
										'<div class="float_right_img" >'+
											'<img alt="" id="pic_'+i+'" src="${ctx}' + (data[i].picSrc?data[i].picSrc:defaultPicSrc) + '" style="height:70px;width:70px">'+
										'</div>'+
									'</div>'+
								'</a>'+
								'<input type="hidden" targetId="item_'+i+'" urlType="url" title="'+ data[i].title + " 跳转至" +'"/>';
				}
				str += elementStr;
			} 
			$("#content_container").html(str); 
		} 
    </script> 
  </body>
</html>

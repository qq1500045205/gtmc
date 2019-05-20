<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 

    <title>单图文消息预览</title> 
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
			border-radius:5px;
		} 
		
		.item:hover{
			background-color: #f5f5f5;
		} 
		.item h3{
			margin:0;
			line-height:35px;
		}
		.item .date{
			color:#ccc;
			line-height:30px;
		}
		.item .more{
			line-height:35px;
			border-top:1px solid #ddd;
		}
		.item .desc{
			margin:10px auto;
		}
		.header_pic {
			position: relative;
			background-color: #ddd;
			text-align: center;
			min-height: 160px;
			width: 100%;
			overflow: hidden; 
		}
		img {
			max-width: 100%;
			border:0;
		}
		 
	</style>
	
  </head>

   <body> 
	 <div id="content_container"> </div> 
	 
	 <div class="item">
			<h3 id="title_0" class="title"> title</h3>
			<div id="title_0" class="date">3月2日</div>
			<div class="header_pic"> 
				<img alt="" id="pic_0" src="${ctx}/uploads/car1.jpg" > 
			</div>
			<div id="title_0" class="desc">desc</div>
			<div class="more">查看全文</div> 
		</div>


<!-- /container --> 
    
    <script type="text/javascript">
		var defaultPicSrc = "/scripts/image/pic.png";
		var defaultName = "名称";
		var defaultItem = "图文";
		var defaultPic = "图片";
		var defaultTitle = "标题";
		var defaultDesc= "摘要";
		var defaultContent = "内容";
		var defaultAuthor = "作者";
		var index = 0;
		var data = ${jsonObject};
		
		assignContent();
		function assignContent() {
			var myDate = new Date();
			var year = myDate.getFullYear(); 
			var month = ("0" + (myDate.getMonth() + 1)).slice(-2); 
			var day = ("0" + myDate.getDate()).slice(-2); 
			var h = ("0" + myDate.getHours()).slice(-2); 
			var m = ("0" + myDate.getMinutes()).slice(-2); 
			var s = ("0" + myDate.getSeconds()).slice(-2); 
			var dateString = year + "-" + month + "-" + day + " " + h + ":" + m + ":" + s; 
			
			var str = '<div class="item">'+
							'<div id="title_0" class="title>' + (data.title ? data.title : defaultTitle) + '</div>'+
							'<div id="title_0" class="date">' + dateString + '</div>'+
							'<div class="header_pic">'+
								'<img alt="" id="pic_0" src="${ctx}' + (data.picSrc?data.picSrc:defaultPicSrc) + '">'+
							'</div>'+
							'<div id="title_0" class="desc">' + (data.desc ? data.desc : defaultDesc) + '</div>'+
							'<div class="more">查看全文</div>'+
						'</div>';
			$("#content_container").html(str);
			$("#messageTitle").text(data.name?data.name:defaultName);
		}
	
    </script>
  
  </body>
</html>

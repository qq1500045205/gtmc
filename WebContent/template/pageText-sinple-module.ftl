<#assign ctx=request.contextPath>  
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${title}</title>

	<link rel="shortcut icon" href="${ctx}/template/assets/ico/favicon.png">
    <!-- Bootstrap core CSS -->
	<link href="${ctx}/template/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/template/dist/css/bootstrap-combined.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="${ctx}/template/dist/css/bootstrap-theme.min.css" rel="stylesheet">
	
	<!-- Placed at the end of the document so the pages load faster -->
    <script src="${ctx}/scripts/jquery-2.0.js"></script>
    <script src="${ctx}/template/dist/js/bootstrap.min.js"></script>
	
	<style>
		body {
		  padding: 10px;
		}
		
		.align_horizontal_center {
			text-align: center !important;
		}
		
		.display_block {
			display: block !important;
		}
		
		.div_header {
			border-bottom: 2px solid #ddd;
			font-weight: bold;
			font-size: 1em;
			display: block;
			padding-left: 8px;
			padding-right: 8px;
			height: auto;
		}
		
		.div_header1 {
			font-weight: bold;
			font-size: 1em;
			display: block;
			padding-left: 8px;
			padding-right: 8px;
			height: auto;
		}
		
		.div_header2 {
			min-height: 44px;
			font-weight: bold;
			font-size: 1em;
			display: block;
			padding-left: 8px;
			padding-right: 8px;
			height: auto;
		}
		
		.div_body {
			padding-top: 8px;
			padding-left: 8px;
			padding-right: 8px;
			height: 75px;
			border-bottom: 1px solid #ddd;
		}
		
		.div_footer {
			padding-top: 8px;
			padding-left: 8px;
			padding-right: 8px;
			height: 75px;
		}
		
		.div_header:hover, .div_body:hover, .div_footer:hover {
			background-color: #eeeeee;
		}
		
		.box {
			display: block;
			padding-top: 8px;
			line-height: 20px;
		}
		.box_up {
			height: 88px;
			overflow: hidden;
		}
		
		.header_pic {
			position: relative;
			background-color: rgba(0,0,0,0.8);
			text-align: center;
			width: 100%;
			overflow: hidden;
			margin-bottom: 10px;
		}
		img {
			max-width: 720px;
		}
		
		.img_caption {
			position: absolute;
			color: #ffffff;
			text-align: left;
			left: 0;
			right: 0;
			bottom: 0;
			padding: 15px;
			background: rgba(0, 0, 0, 0.75);
		}
		
		.padding_style {
			padding: 8px;
		}
		
		.align_center {
			text-align: center;
			line-height: 75px;
		}
		
		.no_float8 {
			width: 65.81196581196582% !important;
		}
		
		.float_right_img {
			float: right;
			background: #ddd;
			overflow: hidden;
		}
		
		label {
			display: inline;
		}
	</style>
	
  </head>

  <body>	
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="accordion">
					<div class="accordion-group padding_style" id="content_container">
					
					</div>
				</div>
			</div>
		</div>
	</div>
	 <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    
    
    <script type="text/javascript">
		var width = "720";
		var height = "175";
		var defaultPicSrc = "http://42.96.145.106/wechat/images/1.jpg";
		var defaultPic = "图片";
		var defaultTitle = "标题";
		var defaultContent = "内容";
		
		var str = "";
	
		elementStr = '<a href="#" id="item_0">'+
						'<div class="div_header1">'+
						'<div class="div_header2">'+
							'<span>'+ defaultTitle +'</span>'+
						'</div>'+
						'<div class="header_pic">'+
							'<img alt="" src="'+defaultPicSrc+'"><span>'+defaultPic+'</span>'+
						'</div>'+
						'<div style="height:44px;">'+
							'<span>'+defaultContent+'</span>'+
						'</div>'+
						'<div class="div_footer" style="height:44px;border-top:1px solid #ddd;">'+
							'<span>查看全文</span>'+'<span style="float:right;padding-right:10px;">></span>'+
						'</div>'+
					'</div></a>'+
					'<input type="hidden" targetId="item_0" urlType="url" title="'+ defaultTitle + ",跳转至" +'"/>';
		str += elementStr;
		$("#content_container").html(str);
		
    </script>
  
  </body>
</html>

<#assign ctx=request.contextPath>  
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>${title}</title>

	<link rel="shortcut icon" href="assets/ico/favicon.png">
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
			height: 2.8em;
			line-height: 2.8em;
		}
		
		.div_body {
			padding-top: 8px;
			padding-left: 8px;
			padding-right: 8px;
		}
		
		.div_footer {
			border-top: 1px solid #ddd;
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
			background-color: rgba(0,0,0,0.8);
			text-align: center;
			min-width: 320px;
			width: 100%;
			overflow: hidden;
			margin-bottom: 20px;
		}
		.header_pic img {
			max-width: 720px;
		}
	</style>
	
  </head>

  <body>
	<div class="header_pic" id="header_pic">		
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12" id="content_container">
				
			</div>
		</div>
	</div>
	 <!-- /container -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    
    
    <script type="text/javascript">
		var width = "720";
		var height = "175";
		var data = ${jsonObject};
		if (data.title) {
			var titleStr = '<img src="'+data.title.src+'" width="'+width+'" height="'+height+'">';
			$("#header_pic").html(titleStr);
		}
		var str = "";
		for (i=0; i<data.content.length; i++){
			if (data.content[i].type=="pic-text"){
				elementStr = '<div class="accordion">'+
					'<div class="accordion-group">'+
						'<div class="div_header">'+
							'<span>'+
								'<span>'+data.content[i].mainTitle+'</span><span>'+data.content[i].secondTitle+'</span>'+
							'</span>'+
						'</div>'+
						'<div class="div_body">'+
							'<span class="align_horizontal_center display_block"><img alt="" src="'+data.content[i].picSrc+'" ></span>'+
							'<span class="box box_up" id="accordion_span_'+i+'">'+data.content[i].content+'</span>'+
						'</div>'+
						'<div class="accordion-heading div_footer">'+
							 '<a class="accordion-toggle align_horizontal_center" id="accordion_button_'+i+'" spanid="accordion_span_'+i+'">展开</a>'+
						'</div>'+					
					'</div>'+
				'</div>';
			} else if (data.content[i].type=="text") {
				elementStr = '<div class="accordion">'+
					'<div class="accordion-group">'+
						'<div class="div_header">'+
							'<span>'+
								'<span>'+data.content[i].mainTitle+'</span><span>'+data.content[i].secondTitle+'</span>'+
							'</span>'+
						'</div>'+
						'<div class="div_body">'+
							'<span class="box box_up" id="accordion_span_'+i+'">'+data.content[i].content+'</span>'+
						'</div>'+
						'<div class="accordion-heading div_footer">'+
							 '<a class="accordion-toggle align_horizontal_center" id="accordion_button_'+i+'" spanid="accordion_span_'+i+'">展开</a>'+
						'</div>'+					
					'</div>'+
				'</div>';
			}
			str += elementStr;
		}
		$("#content_container").html(str);
		for (i=0; i<data.content.length; i++){
			btnId = "#accordion_button_" + i;
			$(btnId).click(function(){
				var spanId = "#"+$(this).attr("spanid").toString();
					console.log(spanId);
				if ($(this).text()=="展开") {
					$(this).text("收起");
					$(spanId).height("auto");
				} else {
					$(this).text("展开");
					$(spanId).height("80px");
				}
			});
		}
    </script>
  
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
  
	<title>反馈</title>
   <%@ include file="/common/pre_general.jsp"%>
   <script src="${ctx}/scripts/wx.js"></script> 
	<style>
		body {
			padding: 0;
			background:#eee;
			font-family:"微软雅黑",Arial;
			min-width:300px;
		    overflow-x:hidden;
		    font-size:13px;
		}
		.head-pic{
			height:120px;
			width:100%;
			overflow:hidden;
			background:#ddd;
			text-align:center;
		}
		.content{
			padding:20px; 
			text-align:center;
			color:#999;
		}
		.content h4{
			color:#666;
		}
		.btn-primary a{
			color:#fff;
			text-decoration:none; 
		}
		.btn-primary { 
			min-width:260px!important;
			margin:0 auto;
			text-align:center;
		}
		
	</style>
	
  </head>

  <body>
    
    
    
	<div id="header_pic" class="head-pic">
		<img id='header_pic' src="${ctx}/uploads/car1.jpg"/>	
		<input type='hidden' dataType='pic' targetId='header_pic' />
	</div>
	<div class="content" id='desc'> 
		<h4 id='header_text'></h4>
		<br><span id='body_text'></span> 
	</div>
	<div style="text-align:center;">
		<Button type='button' class='btn_main' id='href' style='width:70%' >个人中心</Button>
	</div> 
	
	<!-- /container -->
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <script>
	    var param = new Wx.Param();
	    var urls = param.getURL('##URL##', [{
	    	"src": 'person-center.jsp'
	    }]);
	    var data = param.getDATA('##DATA##', [{
	    	"header_text": '预约成功!',
	    	"body_text": '我们将在24小时与您联系,请耐心等候。'
	    	//"header_pic": '${ctx}/uploads/car1.jpg'
	    }]);
	    
	    //----------------------------------------------------
	    $(function(){
	    	$("#href").bind('click', function(){
	    		Util.Browser.jump(urls[0].src, '0-1');
	    	});

	    	
	    	//$('#header_pic').attr('src', data[0].header_pic);
	    	$('#header_text').html(data[0].header_text);
	    	$('#body_text').html(data[0].body_text);
	    	
	    	$('#header_text_hidden').val(data[0].header_text);
	    	$('#body_text_hidden').val(data[0].body_text);
	    	//$('#body_pic_hidden').val(data[0].header_pic);
	    });
  	//----------------------------------------------------
    </script>
    
    <script type="text/javascript">

    </script>
    
    
    <div style="display:none;" id="data">[{"header_text":"##header_text_0#", "body_text": "##body_text_0#"}]</div>
    <!--  input id='header_pic_hidden' type="hidden" targetId="##header_pic_0#" dataType="value" inputType="pic" title="图片" value="" -->
    <input id='header_text_hidden' type="hidden" targetId="##header_text_0#" dataType="value" inputType="text" title="标题" value="" >
    <input id='body_text_hidden' type="hidden" targetId="##body_text_0#" dataType="value" inputType="textarea" title="内容" value="" >
  	<input type="hidden" targetId="baoyang_url" urlType="url" title="个人中心跳转至" />
  </body>
</html>

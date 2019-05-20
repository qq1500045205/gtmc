<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include  file="/common/admin_pre.jsp" %>  
<title>模块分配</title> 
<script type="text/javascript" src="${ctx }/scripts/ui/om-sortable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-itemselector.js"></script> 

<!-- view_source_begin -->
<style> 
div{
	box-sizing:border-box;
}
.top{
	position:relative;
	margin-bottom: 5px;
	border-bottom:1px solid #ddd;
	width:100%;
	padding:10px;
}
.top h3{
	font-size:20px; 
	line-height:35px;
	margin:0;
}
#saveAssign{
	position:absolute;
	right:5px;
	top:10px; 
} 
.body{
	width:100%;
	min-width:900px;
} 
.width_50{
	width:50%;
	padding:5px;
	float:left;
	display:inline;
	border-right:1px solid #ddd;
} 
.p_title{
	background:#fff;
	color:#666; 
	line-height: 25px;
	padding:5px;
	font-weight:bold;
	border-radius:5px;
	text-align:center;
}

#selectModule,#selectGzh{
	width:100%!important;
	padding:10px;
}  
</style>
<script type="text/javascript">
		 $(document).ready(function() {
			 var modules = [];
			 var gzhs =[];
			 $.ajax({
				 type:"POST",
				 url:"${ctx}/ajax/combo",
				 async: false,
				 data:"type=assignModule",
				 success:function(msg){
				 //alert(msg);
					 modules= msg;
				 }
			  });
			 $.ajax({
				 type:"POST",
				 url:"${ctx}/ajax/combo",
				 async: false,
				 data:"type=assignGzh",
				 success:function(msg){
				 //alert(msg);
					 gzhs= msg;
				 }
			  });
			 
	            $('#selectModule').omItemSelector({
	                availableTitle : '可分配模块',
	                selectedTitle : '已选择模块',
	                dataSource : modules,
	                //dataSource : '${ctx}/ajax/combo?type=assignModule',
	                width:'90%',
	                height:450
	            });
	            $('#selectGzh').omItemSelector({
	                availableTitle : '所有公众号',
	                selectedTitle : '已选择公众号',
	                dataSource : gzhs,
	                //dataSource : '${ctx}/ajax/combo?type=assignGzh',
	                width:'90%',
	                height:450
	            });
	            
	            $("#saveAssign").click(function(){
	            	var modules =  $('#selectModule').omItemSelector("value");
	            	var gzhs = $('#selectGzh').omItemSelector("value");
	            	
	            	if(modules.length == 0){
	            		alert("请选择需要分配的模块！");
	            		return ;
	            	}
	            	
	            	if(gzhs.length == 0){
	            		alert("选择分配的公众号！");
	            		return ;
	            	}
	            	var module =[];
	            	for(var i=0;i<modules.length;i++){
	            		module.push({"moduleGuid":modules[i]});
	            	}
	            	var gzh =[];
					for(var i=0;i<gzhs.length;i++){
						gzh.push({"gzhGuid":gzhs[i]});
	            	}
	            	
	            	$.ajax({
	  				  type: 'POST',
	  				  url: "${ctx}/admin/saveAssign",
	  				  data: {modules:JSON.stringify(module),gzhs:JSON.stringify(gzh)},
	  				  dataType: "json",
	  				  success:function(data){
	  				  	alert(data.message);
	  				 	if(data.success){
							parent.$('#center-tab').omTabs("close");
						}
	  				  }
	  				});
	            	
	            	
	            });
	        });
		
	
</script>
<!-- view_source_end -->
</head>
<body>
	<div class="top">
		<h3>分配模块</h3>
		<button id="saveAssign" class="btn-def">保存</button>
	</div>
	<div class="body">
		<div class="width_50">
			<div class="p_title">选择要分配的模块</div>
			<div id="selectModule"></div>
		</div>
		<div class="width_50">
			<div class="p_title">选择分配的公众号</div> 
			<div id="selectGzh"></div>  
		</div>
	</div> 
	
</body>
</html>
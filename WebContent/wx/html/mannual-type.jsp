<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<%@ include file="/common/pre_general.jsp"%>
	<title>爱车生活</title>
	<style>
		body {
		  padding: 0;
		  background:#333;
		  font-family:"微软雅黑",Arial;
		  overflow-x:hidden;
		}
		.row1{ 
			margin: 0 auto;
			text-align:center;
			width:100%;
			height: auto;
		}
		.item{
			background:rgba(74,181,236,.8);
			margin: 5px;
			width:45%;
			height:8em;
			padding-top:1.5em;
			font-size:16px;
			vertical-align:middle;
			text-align:center;
			display: inline-block;
		}
		.item:hover{
			background:rgba(121,209,254,.8);
		}
		.title{
			font-weight:normal;
			color:#fff;
		}
	</style>
	
  </head>

  <body>
  
  <div id="main" style='padding-top:8px;'></div>
	 <!-- /container -->
 	<input type="hidden" targetId="item" urlType="url" title="选项 跳转至" />
	<div id="contentManager"><a style="display:none;" href="javascript:;" id="mannual" title="${ctx}/admin/content/mannual/mannual-list.jsp">爱车生活 内容管理</a></div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <script type="text/javascript">
    
    	var wx = new Wx.Param();
    	var gzhHash = wx.getGZHHASH('##GZHHASH##');
    	var url = wx.getURL('##URL##', [{src: "${ctx}/wx/html/mannual-list.jsp"}]);
	    $(function(){
	    	load();
	    });
	    
	    function load(){
	    	console.log(gzhHash);
	    		$.ajax({
					type:'POST',
					url:"${ctx}/mannual/getMannualType",
					dataType:"json",
					data:{
						gzhHash: gzhHash
					},
					success:function(res){
						var result = eval(res);
						console.log(result);
						if(result.message=="success"){
							data = result.result;
							if (!data || data.length==0) {
								data = [{
					    			gzhHash: "-2042484612",
				    				ord: "1",
				    				pic: "/gtmc_wx/uploads/icon-car.png",
				    				typeGuid: "0",
				    				typeName: "爱车保养",
					    		},{
					    			gzhHash: "-2042484612",
				    				ord: "2",
				    				pic: "/gtmc_wx/uploads/icon-wheel.png",
				    				typeGuid: "0",
				    				typeName: "驾驶技巧",
					    		},{
					    			gzhHash: "-2042484612",
				    				ord: "3",
				    				pic: "/gtmc_wx/uploads/icon-insurance.png",
				    				typeGuid: "0",
				    				typeName: "保险理赔",
					    		}];
							}
							loadhtml();
						}
						else{
							alert("服务器连接失败");
						}
					}
				});
		}
	    
	  /*  var data = [{
	   		pic: "${ctx}/uploads/icon-car.png",
	   		name: "爱车保养" 
	   	},{
	   		pic: "${ctx}/uploads/icon-insurance.png",
	   		name: "保险理赔" 
	   	},{
	   		pic: "${ctx}/uploads/icon-wheel.png",
	   		name: "驾驶技巧" 
	   	}];
	*/
		var html = "";
	
		function pic_click(index) {
			
			var param = "?typeGuid="+ data[index].typeGuid;
			Util.Browser.jump(url[0].src + param, "14-1", data[index]);
		}
	
	    function loadhtml(){   
	    	console.log(data.length/2);
	    	for(var i=0;i<data.length/2;i++){
	    		x=2*i;
	    		if(data.length){
	    			html += 
	    	    		'<div class="row1">'+
	       					'<div class="item" onclick="pic_click('+x+');">'+
	       						'<img src="'+data[x].pic+'" style="width:3em;height:3em;">'+
	       						'<div class="title">'+data[x].typeName+'</div>'+
	       					'</div>'+ 
	       					'<div class="item"' + (data[x+1] ? 'onclick="pic_click('+(x+1)+');"':'') +'>'+
	       						(data[x+1] ? '<img src="'+ data[x+1].pic +'" style="width:3em;height:3em;" >':'')+
	      						'<div class="title">'+(data[x+1] ? data[x+1].typeName :'') +'</div>'+
	      					'</div>'+
	      				'</div>';
	    		}
   			 }
   			$('#main').html(html);
   		}
	 
    </script>
  
  </body>
</html>

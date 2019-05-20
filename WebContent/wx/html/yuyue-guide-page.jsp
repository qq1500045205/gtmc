<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
   <%@ include file="/common/pre_general.jsp"%>
   <title>预约服务</title>
   <script src="${ctx}/scripts/wx.js"></script>
	<style> 
		.list{
			padding:10px; 
			margin-bottom:10px;
			height:70px;
			background:url('${ctx}/scripts/images/arrow-right.png') no-repeat 94% 50%;
			border:none;
		}
		.itemimg{
			width:70px;
			height:70px;
		}
		.itemtxt{
			height:50px;
			line-height:50px;
		}
	</style>
	
  </head>

  <body> 
	<div class="content">
		<div class='list' onclick="div_click(0)">
			<div class="left itemimg">
				<img src="${ctx}/uploads/icon2-repair.png" />
			</div>
			<div class="left itemtxt">
				<span>预约维修保养</span>
			</div>
		</div>
		<div class='list'  onclick="div_click(1)">
			<div class="left itemimg">
				<img src="${ctx}/uploads/icon2-car.png" />
			</div>
			<div class="left itemtxt">
				<span>预约年审</span>
			</div>
		</div>
		<div class='list'  onclick="div_click(2)">
			<div class="left itemimg">
				<img src="${ctx}/uploads/icon2-drive.png" />
			</div>
			<div class="left itemtxt">
				<span>预约试驾</span>
			</div>
		</div>
	</div> 
  
    <script>
    	var param = new Wx.Param();
	    var openid = param.getOPENID();
		var gzhHash = param.getGZHHASH('##GZHHASH##');
		
		var urls = param.getURL('##URL##', [
   			{"src": 'yuyue-baoyang-page.jsp'},
   			{"src": 'yuyue-nianshen-page.jsp'},
   			{"src": 'yuyue-shijia-page.jsp'}
   		]);
		                       
    	
    	function div_click(i){
    		 Util.Browser.jump(urls[i].src, '0-2', {});
    	}
    	var stat = new Wx.Stat();
    	stat.report('0-2', "预约导航", gzhHash, openid, {});
        
    </script>
    
    <div id='data'>
    	
    
    </div>
  	<input type="hidden" targetId="baoyang_url" urlType="url" title="预约维修保养跳转至" />
  	<input type="hidden" targetId="nianshen_url" urlType="url" title="预约年审跳转至" />
  	<input type="hidden" targetId="shijia_url" urlType="url" title="预约试驾跳转至" />
  </body>
</html>

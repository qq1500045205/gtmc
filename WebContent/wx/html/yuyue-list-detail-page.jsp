<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
   <%@ include file="/common/pre_general.jsp"%>
   <script src="${ctx}/scripts/wx.js"></script>
   <title>预约记录</title>
   <style>
		.list{
			padding:10px;   
		} 
		.list .time{
			font-size:12px;
		}
		.labelx{
			display: inline-block;
			width:120px;
			color: #666;
			font-weight:bold;
		}
	</style> 
</head>
  <body>
  <div class="content"> 
  	<form name='yuyue'>
		<div id='info-box' class="list"> 
			<div id='first-line'>
				<span class='labelx' id=''>销售店</span>
				<span id='yuyue_dealer'></span>
			</div>
			<div id='second-line'>
				<span class='labelx' id='yuyue_type_label'>预约类型</span>
				<span id='yuyue_type'></span>
			</div>
			<div>
				<span class='labelx' id='yuyue_time_label'></span>
				<span id='yuyue_time'></span>
			</div>
			<div id='extra'>
				<span class='labelx' id='yuyue_item_label'></span>
				<span id='yuyue_item'></span>
			</div>
			<div style='display:none'>
				<span class='labelx' id='expire_time_label'></span>
				<span id='expire_time'></span>
			</div>
			<div>
				<span class='labelx'>备注</span>
				<span id='memo'></span>
			</div>
		</div>
	</form>
</div>  
    <script>
   		
    	$(function(){
    		/*-----------------------------------*/
    		var req = new Wx.Request();
    		var param = new Wx.Param();
	    	var openid = param.getOPENID();
	    	var gzhHash = param.getGZHHASH('##GZHHASH##');
    		var form = new Util.Form('yuyue');
    		var item = Util.Browser.getIntent();
    		/*-----------------------------------*/
    		var yuyue_type_label = '预约类型';
    		var expire_time_label = '期望时间';
    		var yuyue_time_label = '预约时间';
    		var yuyue_item_label = '';
    		var yuyue_item = '';
    		/*-----------------------------------*/
			if( item.yuyueType == '1' ){
				yuyue_type = '预约试驾';
				yuyue_item_label = '车型名称';
				yuyue_item = item.yuyueField1;
				req.postData('/car/getCarType',{carTypeGuid:yuyue_item}, function(ret){
					form.setValues({"yuyue_item": ret.result.carTypeTitle});
				});
				
			}
			if( item.yuyueType == '2' ){
				yuyue_type = '预约维修保养';
				yuyue_item_label = '车牌号码';
				yuyue_item = item.yuyueField1 + item.yuyueField2;
			}
			if( item.yuyueType == '3' ){
				yuyue_type = '预约年审';
				yuyue_item_label = '车牌号码';
				yuyue_item = item.yuyueField1 + item.yuyueField2;
			}
			form.setValues({
				"yuyue_time_label": "预约时间", 
				"yuyue_type_label": yuyue_type_label, 
				"expire_time_label": expire_time_label,
				"yuyue_item_label": yuyue_item_label
			});
			form.setValues({
				"yuyue_time": item.createdOnTime,
				"yuyue_type": yuyue_type,
				"yuyue_item": yuyue_item,
				"yuyue_dealer": item.projectName,
				"expire_time": item.yuyueTime,
				"memo": item.yuyueMemo
			});
    	});
    	
    	var param = new Wx.Param();
    	var stat = new Wx.Stat();
    	var openid = param.getOPENID();
    	var gzhHash = param.getGZHHASH('##GZHHASH##');
    	stat.report('17-5-2', "预约列表详细", gzhHash, openid, {});
    </script>
    <script src="${ctx}/template/dist/js/js_general.js"></script> 
  </body>
</html>

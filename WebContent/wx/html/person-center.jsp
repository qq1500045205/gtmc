<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <%@ include file="/common/pre_general.jsp"%>
	<title>个人中心</title>
	
	<style> 
		.head-pic{ 
			width:100%;
			height:120px;
			overflow:hidden;
			background:#ddd;
			text-align:center; 
			position:relative;
		}
		.head-pic img{
			position:absolute;
			top:0;
			left:0;
			width: 100%;
			height：auto; 
			min-height:100px;
		}
		.content{
			z-index:999;
			position:relative;
		}
		.round {
			margin-bottom: 15px;
			background-color: #fff; 
			margin-left: 10px;
			margin-right: 10px; 
			text-align: left;
			font-size: 14px;
			line-height: 24px;
			overflow: hidden;
		} 
		
		.round>li {
			font-size: 16px; 
			line-height: 23px;
			background:#fff;
			background-position:100% 50%; 
			padding: 15px;  
			
			background:url('${ctx}/scripts/images/arrow-right.png') no-repeat 98% 50%;
		} 
		.round>li:active{
			background-color:#106dbb;
			color:#fff;	
		} 
	 
	</style>
	
  </head>

  <body> 
    <div class="head-pic">
		<img id='header_pic' src="${ctx}/static-data/pic/toyota-bg2.jpg" />
	</div>
	<div class="content">
		<ul id="member-action" class="round">
			<li onclick="li_click(0)" class='personal-info'>注册信息 </li>
			<li onclick="li_click(1)"> 维修保养记录 </li>
			<li onclick="li_click(2)">活动报名记录 </li>
			<li onclick="li_click(3)"> 服务预约记录 </li>
			<li onclick="li_click(4)"> 支付记录 </li>
			<!-- //add by luowenwu 2014/06/13 Start -->
			<li onclick="li_click(5)"> 积分管理记录 </li>
			<!-- //add by yuanwenjie 2014/07/03 start -->
			<li onclick="li_click(7)">  积分商城</li>
			<!-- //add by yuanwenjie 2014/07/03 start -->
			<li onclick="li_click(6)"> 商品订单管理</li>
			<!-- //add by luowenwu 2014/06/13 end -->

		</ul>
	</div>
  	<input type="hidden" targetId="register" urlType="url" title="注册信息 跳转至" />
  	<input type="hidden" targetId="srvorderhistory" urlType="url" title="维修保养记录 跳转至" />
  	<input type="hidden" targetId="activity" urlType="url" title="活动报名记录 跳转至" />
  	<input type="hidden" targetId="yuyue" urlType="url" title="服务预约记录 跳转至" />
  	<input type="hidden" targetId="pay" urlType="url" title="支付记录 跳转至" />
	<!-- //add by luowenwu 2014/06/13 Start -->
  	<input type="hidden" targetId="creditactivity" urlType="url" title="积分管理记录 跳转至" />
  	<input type="hidden" targetId="orderManage" urlType="url" title="商品订单管理 跳转至" />
	<!-- //add by luowenwu 2014/06/13 end -->
		<!-- //add by yuanwenjie 2014/07/03 Start -->
	<input type="hidden" targetId="creditShop" urlType="url" title="积分商城" />
	<!-- //add by yuanwenjie 2014/07/03 End -->
  	<input type="hidden" targetId="unRegister" urlType="url" title="未注册 跳转至" />


  	
  	<script	type="text/javascript">
  	
  		var testdata = 'a';
  		var wx = new Wx.Param();
  		var url = wx.getURL('##URL##',[{src: "person-info.jsp"},{src: "active-list-page.jsp"},{src: "yuyue-list-page.jsp"},{src: "#this"},{src: "register.jsp"},{src: "srv-order-history.jsp"}]);
  		
  		function li_click(index) {
  			console.log(url[index]);
  			var wxUser = new Wx.User();
  	  		wxUser.getUserInfo(function(result){
  	  			if (!result || result.memberLevel==0) {
  	  				//if (wx.isWeiXin()) {
  	  					if (confirm("您还不是广汽TOYOTA会员，立即注册享受更多服务！")) {
  	  						//update by luowenwu 2014/07/02  Start
  	  						//backup code
  	  						//Util.Browser.jump(url[5].src, "");	
  	  						Util.Browser.jump(url[8].src, "");	
  	  						//update by luowenwu 2014/07/02 end
  	  					}
  	  				//}
  	  			} else {
  	  				Util.Browser.jump(url[index].src, "17-1");
  	  			}
  	  		});
  			
  		}
  	</script>
  	<script src="${ctx}/template/dist/js/js_general.js"></script>
  	<script type="text/javascript">
		var wx = new Wx.Param();
		var gzhHash = wx.getGZHHASH("##GZHHASH##");
		var openid = wx.getOPENID();
		var stat = new Wx.Stat();
		stat.report('17-1', "会员中心首页", gzhHash, openid, {});
	</script>
  </body>
  
</html>

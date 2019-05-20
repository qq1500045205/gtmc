<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<%@ include file="/common/pre.jsp"%>
<title>爱车生活</title>
<style>
body {
	  font-family:"微软雅黑",Arial;
	  padding: 20px; 
	  overflow-x:hidden;
	  min-width:300px; 
} 
.title {
	color: black;
	font-size: 20px;
	font-weight: bold;
	word-break: normal;
	word-wrap: break-word;
	margin:0;
	line-height:40px;
} 
 
.date {
	display: inline-block;
	line-height:30px;
	color: #8C8C8C;
	font-size: 13px;
}
img {
	max-width:100%!important;
	overflow:hidden;
}  
</style>
	
  </head>

  <body>	 
	<div id="content_container"></div>
    
     <script type="text/javascript">
     	$(function(){
     		load();
     	});
     	var textGuid="${param.textGuid}";
     	function load(){
     		if (textGuid) {
     			$.ajax({
         			type:'POST',
         			url:"${ctx}/mannual/getMannualtext",
         			data:{
         				textGuid:textGuid
         			},
         			success:function(res){
         				var result = eval(res);
         				console.log(result);
         				if(result.message=="success"){
         					data = result.result;
         					loadhtml(data);
         				}else{
         					alert("连接服务器失败");
         				}
         			}
         		});
     		} else {
     			data = [{
     				content: "夏季超高温天气往往伴随强对流天气的时时出现，暴雨、台风、高温面前，你的车险还给力吗?<br>“全险”不含“涉水险”<br>车主们一般所说的车险全险并不一定包括涉水险。所谓的全险是指所有的主险以及一些主流的附加险，包括三者险、车损险、盗抢险、车上人员险、划痕险、交强险，但未必包含涉水险责任。涉水险也被称为发动机特别险，相关资料显示，目前国内车险市场中的A、B、C三款行业产品，涉水险都需要作为附加险另行购买。由于风险高，很多保险公司并不推荐该附加险种。<br>据保险理赔部工作人员介绍，如果购买了涉水险，那么保险公司对受保车辆因路面积水导致发动机熄火以及车辆被水淹造成的损失都可以进行赔付。但如果车主在水中熄火，强行二次启动所造成的损失保险公司是不赔的。涉水险的价格并不贵，它不以车型、车价确定保费价格，在可享受商业保险最低折扣的情况下，通常保费不足百元。<br>夏季超高温天气往往伴随强对流天气的时时出现，暴雨、台风、高温面前，你的车险还给力吗?<br>“全险”不含“涉水险”<br>车主们一般所说的车险全险并不一定包括涉水险。所谓的全险是指所有的主险以及一些主流的附加险，包括三者险、车损险、盗抢险、车上人员险、划痕险、交强险，但未必包含涉水险责任。涉水险也被称为发动机特别险，相关资料显示，目前国内车险市场中的A、B、C三款行业产品，涉水险都需要作为附加险另行购买。由于风险高，很多保险公司并不推荐该附加险种。<br>据保险理赔部工作人员介绍，如果购买了涉水险，那么保险公司对受保车辆因路面积水导致发动机熄火以及车辆被水淹造成的损失都可以进行赔付。但如果车主在水中熄火，强行二次启动所造成的损失保险公司是不赔的。涉水险的价格并不贵，它不以车型、车价确定保费价格，在可享受商业保险最低折扣的情况下，通常保费不足百元。<br>夏季超高温天气往往伴随强对流天气的时时出现，暴雨、台风、高温面前，你的车险还给力吗?<br>“全险”不含“涉水险”<br>车主们一般所说的车险全险并不一定包括涉水险。所谓的全险是指所有的主险以及一些主流的附加险，包括三者险、车损险、盗抢险、车上人员险、划痕险、交强险，但未必包含涉水险责任。涉水险也被称为发动机特别险，相关资料显示，目前国内车险市场中的A、B、C三款行业产品，涉水险都需要作为附加险另行购买。由于风险高，很多保险公司并不推荐该附加险种。<br>据保险理赔部工作人员介绍，如果购买了涉水险，那么保险公司对受保车辆因路面积水导致发动机熄火以及车辆被水淹造成的损失都可以进行赔付。但如果车主在水中熄火，强行二次启动所造成的损失保险公司是不赔的。涉水险的价格并不贵，它不以车型、车价确定保费价格，在可享受商业保险最低折扣的情况下，通常保费不足百元。<br>夏季超高温天气往往伴随强对流天气的时时出现，暴雨、台风、高温面前，你的车险还给力吗?<br>“全险”不含“涉水险”<br>车主们一般所说的车险全险并不一定包括涉水险。所谓的全险是指所有的主险以及一些主流的附加险，包括三者险、车损险、盗抢险、车上人员险、划痕险、交强险，但未必包含涉水险责任。涉水险也被称为发动机特别险，相关资料显示，目前国内车险市场中的A、B、C三款行业产品，涉水险都需要作为附加险另行购买。由于风险高，很多保险公司并不推荐该附加险种。<br>据保险理赔部工作人员介绍，如果购买了涉水险，那么保险公司对受保车辆因路面积水导致发动机熄火以及车辆被水淹造成的损失都可以进行赔付。但如果车主在水中熄火，强行二次启动所造成的损失保险公司是不赔的。涉水险的价格并不贵，它不以车型、车价确定保费价格，在可享受商业保险最低折扣的情况下，通常保费不足百元。",
   					createdTime: "2014-03-01 14:22:45",
   					gzhHash: null,
   					textGuid: "adf1ddf0-9a36-4b42-8807-73673d80cf39",
   					title: "车辆遇高温和暴雨 哪些保险能买单",
   					typeGuid: "1",
   					typeName: "驾驶技巧",
     			}];
				loadhtml(data);
     		}
     		
     	
     	loadhtml();
     	}
	/*	var data = {
				title: "车辆遇高温和暴雨 哪些保险能买单",
				date: "2014/01/03", 
				content: "夏季超高温天气往往伴随强对流天气的时时出现，暴雨、台风、高温面前，你的车险还给力吗?<br><b>“全险”不含“涉水险”</b><br>车主们一般所说的车险全险并不一定包括涉水险。所谓的全险是指所有的主险以及一些主流的附加险，包括三者险、车损险、盗抢险、车上人员险、划痕险、交强险，但未必包含涉水险责任。涉水险也被称为发动机特别险，相关资料显示，目前国内车险市场中的A、B、C三款行业产品，涉水险都需要作为附加险另行购买。由于风险高，很多保险公司并不推荐该附加险种。据保险理赔部工作人员介绍，如果购买了涉水险，那么保险公司对受保车辆因路面积水导致发动机熄火以及车辆被水淹造成的损失都可以进行赔付。但如果车主在水中熄火，强行二次启动所造成的损失保险公司是不赔的。涉水险的价格并不贵，它不以车型、车价确定保费价格，在可享受商业保险最低折扣的情况下，通常保费不足百元。"
		};
	*/	
		function loadhtml(data){
			var elementStr =  
				'<h3 class="title">'+data[0].title+'</h3>'+
				'<div>'+
					 data[0].content+'</span>'+
				'</div>';
		$("#content_container").html(elementStr);
		}
		
    </script>
  </body>
</html>

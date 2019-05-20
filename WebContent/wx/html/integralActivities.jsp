<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<%@ include file="/common/pre_general.jsp"%>
<%@ include file="/common/admin_pre.jsp"%> 
<!DOCTYPE html>
<html lang="zh">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${ctx}/template/dist/css/bootstrap-combined.min.css" rel="stylesheet">
<link href="${ctx }/scripts/mobile/jquery.mobile-1.3.2.min.css" rel="stylesheet" >
<script src="${ctx }/scripts/jquery-2.0.js"></script>
<script src="${ctx }/scripts/mobile/jquery.mobile-1.3.2.min.js"></script>
<head>
<style>
body {
	padding: 0;
	font-family: "微软雅黑", Arial;
	overflow-x: hidden;
}
.red{
	color:red;
}
</style>
</head>
<body>
	<div data-role="page" id="page" data-theme="d">
	  <div data-role="content"  id="wrapper" >
		<div id="content">
	     </div>
	        	<div data-role="popup" id="popdiv" data-overlay-theme="a"  data-dismissible="false"  style="max-width:400px;" class="ui-corner-all">
		        	<div data-role="header" data-theme="b"  class="ui-corner-top">
		        	<h2>提示</h2>
		        	</div>
		        	<div data-role="content" data-theme="d" id = "tips" style="text-align: center">
		        	</div>
				</div>
	     
	     </div>
	       <div data-role="footer" data-position="fixed" data-tap-toggle="false"  id="footer"  style="text-align: center" data-theme="d">
	       	<a  id='getIntegralNow' data-ajax="false"  href="#"  onclick="getIntegralNow()"  data-icon='check'  data-role="button" style="width: 80%" data-corners="false" data-theme="b" >立 即 获 得 <font id="jifen" color="red" size="3"></font> 积 分</a>
	 	 </div>
	</div>
	
	<div data-role="page" id="pageReg"  data-theme="d">
	  
	
	  <div data-role="content"  id="wrapper">
		<div id="contentReg">
	     </div>
	     
	     <div data-role="popup" id="popdivReg" data-overlay-theme="a" data-theme="c" data-dismissible="false" >
		     	<div data-role="header" data-theme="b"  class="ui-corner-top">
		     	<h2>提示</h2></div>
		     	<div data-role="content" data-theme="d" id = "tipsReg" style="text-align: center">
		        </div>
		     </div>
	     </div>
	       <div data-role="footer" data-position="fixed"  data-tap-toggle="false"  id="footer"  data-theme="d"  style="text-align: center">
	       	<a data-ajax="false"  href="#"  onclick="getIntegralActivitesByLogin()"  data-icon='check'  style="width: 80%" data-corners="false"  data-theme="b">立即报名</a>
	 	 </div>
	</div>
	
	<script type="text/javascript">
    var util = new Util.Param();
	var param = new Wx.Param();
	var openid=util.get('openid');		             
	var guid=util.get('guid');
	var hash=util.get('hash');   
	if(guid==""||guid==null){
		 guid=util.get('actGuid');	
	}
	if(openid==""||openid==null){
		 openid=util.get('openid');	
	}
	if(hash==""||hash==null){
		 hash=util.get('gzhHash_1');	
	}
	
// 	 openid="orTv6t3SZeTdZPwa1WFGb0482olo";
// 	 guid="841760b6-f188-44d2-a6cd-95e423abbefb";
// 	 hash="451512561";
	
	
	//跳转页面
	var url = param.getURL('##URL##', [{src: "${ctx}/wx/html/register_intergral.jsp"}]) ;
    var obj;
    var obj1;
    var ary;
    $(document).on("pageinit","#page",function(){
    	loadStart("...加载中",'d',false);
    	$.ajax({
			type : 'POST',
			url : "${ctx}/creditactivity/getIntegralActivites",
			async : false,
			data : {
				guid : guid,
				openid:openid,
			},
			dataType : "json",
			success : function(res) {
				loadStop();
				if (res.success) {
					obj = res.result;
					$("#jifen").html(obj["actCredit"]);
					var str = "";
					  elementStr = '<h4>'+obj["title"]+' </h4><small>活动时间：'+obj["startTime"]+" 至 "+obj["endTime"]+'</small>'+
					    '<div class="des" style="text-align:left"><p>&nbsp; &nbsp; &nbsp; &nbsp;  '+obj["desc"]+'</p></div>'+
						'<div class="pic" style="text-align:center">'+'<img alt="" src="${ctx}' + obj["picSrc"] + '"  style="height:150px;">'+ 
					    '</div>'+ obj["content"];
				       str += elementStr;
				       $("#content").html(str);
				}
			}
		}); 
    });
	
    function getIntegralNow(){
    	loadStart("...加载中",'d',false);
		 $.ajax({
				type : 'POST',
				url : "${ctx}/creditactivity/getIntegralActivitesCheck",
				async : false,
				data : {
					guid:guid,
					openid : openid,
					hash:hash,
					endTime:obj["endTime"],
					actType:obj["actType"],
				    actGuid:obj["actGuid"]
				},
				dataType : "json",
				success : function(res1) {
					loadStop();
					if (res1.success) {
						 //0：您还不是会员 ； 1：活动已过期 	；2：您已参加过活动	  3：跳转到报名页面 ； 4：直接获得积分		 ; 5: 活动已过期		;6:您还未关注												
						obj1 = res1.result;
						  reslut1 =obj1["actStatue"]  ;    
			    		 if(reslut1 ==0){
			    			 var tips = "<h3 class='ui-title'>尊敬的用户，请先注册获取更多优惠！</h3>"+
			 						"<a id='sure' data-ajax='false' href='#' data-role='button'  onclick='register_intergral();'  data-inline='true' data-transition='flow' data-theme='b'>注册</a>"+
			 						"<a id='cancel' href=''#'  data-role='button' data-inline='true' data-rel='back' data-theme='c'>取消</a>";
			    			 $("#tips").html(tips);
			    			 $('#sure').button();
			    			 $('#cancel').button();
			    			 $("#popdiv").popup("open");
			    		}else if(reslut1==1){
			    			 var tips = "<h3 class='ui-title'>尊敬的"+obj1["userName"]+"用户，该活动已过期，下次记得早点参加！</h3>"+
		 						"<a id='cancelByA0' href=''#'  data-role='button' data-inline='true' data-rel='back' data-theme='c'>确定</a>";
		 						 $("#tips").html(tips);
		 						 $('#cancelByA0').button();
		 						 $("#popdiv").popup("open");
			    		}else if(reslut1 ==2){
			    			 var tips = "<h3 class='ui-title'>尊敬的"+obj1["userName"]+"用户，您已参加过该活动！详细记录请前往个人中心查询。</h3>"+
		 						"<a id='cancelByA0' href=''#'  data-role='button' data-inline='true' data-rel='back' data-theme='c'>确定</a>";
		 						 $("#tips").html(tips);
		 						 $('#cancelByA0').button();
		 						 $("#popdiv").popup("open");
			    		}else if(reslut1 ==3){   
			    			loadStart("...加载中",'d',false);
			  		       $.ajax({
			  					type : 'POST',
			  					url : "${ctx}/creditactivity/getIntegralActivitesByLogin",
			  					async : false,
			  					data : {
			  						guid : guid
			  					},
			  					dataType : "json",
			  					success : function(res) {
			  						loadStop();
			  						if (res.success) {
			  							ary = res.result;
			  							var jsElem="";
			  							for(var i=0;i<ary.length;i++){
			  								jsElem+=' <div class="m-form-item"><div class="m-label" style="margin-top:3px;">';
			  							if(ary[i].requiredItem=='1'){
			  								jsElem+='<span class="red">*</span>';
			  							}
			  								jsElem+='<font size=4>'+ary[i].itemName+':</font></div>'+
			  											'<div class="m-input-box" ><input type="text"  style="height:35px;"  id=act'+ary[i].requiredItem+'_'+i+'  name="intealActName_'+i+'"  placeholder=提示：'+ary[i].itemNotice+'>'+
			  								'<div id="Msg">&nbsp;</div></div></div>';          
			  							}   
			  							var jsHtml='<form  id="signForm" name="signForm" class="m-form"><div class="m-label" style="margin-top: 2px;margin-bottom:8px;width:100%"><font size="3" color="red">*  该活动必须填写以下选项才可以获得积分</font></div>'+jsElem+'</form>';
		    			  		         $("#contentReg").html(jsHtml);
		    			  		       $.mobile.changePage("#pageReg", "slideup");
			  						}
			  					}
			  				});                   
			    		}else if(reslut1 ==4){  
			    			loadStart("...加载中",'d',false);
			    		     $.ajax({
			    					type : 'POST',
			    					url : "${ctx}/creditactivity/getIntegral",
			    					async : false,
			    					data : {
			    						guid : guid,
			    						userGuid :obj1["userGuid"],
			    						hash:hash,
			    						actCredit:obj["actCredit"]
			    					},
			    					dataType : "json",
			    					success : function(res) {
			    						loadStop();
			    						if (res.success) {
			    							var tips = "<h3 class='ui-title'>尊敬的"+obj1["userName"]+"用户，恭喜您获得了"+obj["actCredit"]+"积分！详细记录请前往个人中心查询。</h3>"+
					 						"<a id='cancelByA0' href=''#'  data-role='button' data-inline='true' data-rel='back' data-theme='c'>确定</a>";
					 						 $("#tips").html(tips);
					 						 $('#cancelByA0').button();
					 						 $("#popdiv").popup("open");
			    						}else{
			    							alert(res.message);
			    						}
			    					}
			    				});   
			    		} else if(reslut1 ==5){
			    			var tips = "<h3 class='ui-title'>尊敬的"+obj1["userName"]+"用户，该活动还未发布！</h3>"+
	 						"<a id='cancelByA0' href=''#'  data-role='button' data-inline='true' data-rel='back' data-theme='c'>确定</a>";
	 						 $("#tips").html(tips);
	 						 $('#cancelByA0').button();
	 						 $("#popdiv").popup("open");
		    			} 
					}
					else{
						alert(res1.message);
					}
				}
			});  
    }
    
    function register_intergral(){
    	Util.Browser.jump("${ctx}/wx/html/register_intergral.jsp?actGuid="+guid+"&gzhHash="+hash+"&openid="+openid, "");
    }
    
    function getIntegralActivitesByLogin(){
   	 var paramName = $("form").serialize();
   	paramName= decodeURIComponent(paramName,true);
   	 var flag=true;
   	$("#Msg").html("");
   	 $("input[id^='act1_']").each(function(){
   			if($(this).val().length==0){
   			  flag=false;
			 $(this).parent().parent().parent().find("#Msg").html("<font color='red'>必须输入必输选项！</font>"); 
			 $(this).parent().parent().find("input").focus();
			 return false;
   			}
   			if($(this).val().length>10){
   				flag=false;
				 $(this).parent().parent().parent().find("#Msg").html("<font color='red'>您输入字数过长，请勿超过50个字！</font>"); 
				 $(this).parent().parent().parent().find("input").focus();
				 return false;
	  		   	}
   	 });
  if(flag){
	  loadStart("...加载中",'d',false);
      $.ajax({
			type : 'POST',
			url : "${ctx}/creditactivity/saveScoreByLogin",
			async : false,
		data : {
			guid : guid,
			hash:hash,
			userGuid:obj1['userGuid'],
			length:ary.length,
			actCredit:obj["actCredit"],
			paramName : paramName
		},
			dataType : "json",
			success : function(res) {
				loadStop();
				if (res.success) {
					 var tips = "<h3 class='ui-title'>尊敬的"+obj1["userName"]+"用户，恭喜您获得了"+obj["actCredit"]+"积分！详细记录请前往个人中心查询。</h3>"+
						"<a data-ajax='false' id='cancelByA0' href=''#' onclick='cancelByA0()'  data-role='button' data-inline='true'  data-theme='c'>确定</a>";
						 $("#tipsReg").html(tips);
						 $('#cancelByA0').button();
						 $("#popdivReg").popup("open");
				}
			}
		});  
      	 }
    }
    
    function cancelByA0(){
    	WeixinJSBridge.call('closeWindow');
    }
    
  //打开loading组件
  //text(string): 加载提示文字
  //str(string): load的背景颜色样式(取值:a,b,c,d)
  //flag(boolean): 背景是否透明(取值:true透明,false不透明)
  function loadStart(text,str,flag){
      if(!text){
          text = "加载中...";
      }
      $(".ui-loader h1").html(text);
      var _width = window.innerWidth;
      var _height = window.innerHeight;
      var htmlstr = '<div style="width:'+_width+'px;height:'+_height+'px;position:fixed;top:0px;left:0px;opacity:0.1;z-index:99999" class="loader-bg"></div>';
      $("body").append(htmlstr);
      if(flag){
          $(".ui-loader").removeClass("ui-loader-verbose").addClass("ui-loader-default");
      }
      else{
          $(".ui-loader").removeClass("ui-loader-default").addClass("ui-loader-verbose");
      }
      var cla = "ui-body-"+str;
      $("html").addClass("ui-loading");
      var arr = $(".ui-loader").attr("class").split(" ");
      var reg = /ui-body-[a-f]/;
      for(var i in arr){
          if(reg.test(arr[i])){
              $(".ui-loader").removeClass(arr[i]);
          }
      }
      $(".ui-loader").addClass(cla);
  }
  //结束loading组件
  function loadStop(){
      $("html").removeClass("ui-loading");
      $(".loader-bg").remove();
  }
	</script>
</body>
</html>

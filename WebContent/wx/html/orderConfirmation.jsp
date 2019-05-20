<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<%@ include file="/common/pre_general.jsp"%>
<!DOCTYPE html>
<html lang="zh">
 <head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"  href="${ctx }/scripts/mobile/jquery.mobile-1.3.2.min.css">
<script src="${ctx }/scripts/jquery-2.0.js"></script>
<script src="${ctx }/scripts/mobile/jquery.mobile-1.3.2.min.js"></script>
<style type="text/css">
body {
	padding: 0;
	font-family: "微软雅黑", Arial;
	overflow-x: hidden;
}
</style>

</head>
<body >
	<div data-role="page" id="page" data-theme="d">
	  
<!-- 	  <div data-role="header"  data-position="fixed"  data-theme="d"> -->
<!-- 	  	<a data-rel="back">返回</a>   -->
<!-- 	    <h1 style="right: 10%">收货地址</h1> -->
<!-- 	  </div> -->
	
	  <div data-role="content"  id="wrapper"  >
		<div data-role="collapsible-set" id="listViewFullArea">
	     </div>
	     	
	     	<div data-role="popup" id="popdiv" data-overlay-theme="a"  data-dismissible="false"  style="max-width:400px;" class="ui-corner-all">
		        	<div data-role="header" data-theme="b"  class="ui-corner-top">
		        	<h2>提示</h2>
		        	</div>
		        	<div data-role="content" data-theme="d" id = "tips" style="text-align: center">
		        	</div>
				</div>
	     
	     </div>
	     
	       <div data-role="footer" data-position="fixed"  id="footer"  data-tap-toggle="false"  data-theme="b" >
	       	<a data-ajax="false'" id ="saveOrder"  href="#" onclick="saveOrder()" style="float:right" data-icon='check' >提交订单</a>
	 	 </div>
	     
	</div>



	 <div data-role="page" id="pageone" data-theme="d">
	 
<!-- 	  <div data-role="header" data-theme="d"> -->
<!-- 	    <h1>确认订单</h1> -->
<!--          <a href="http://www.baidu.com" data-rel="back"> -->
<!--                 返回 -->
<!--          </a> -->
<!-- 	  </div> -->
	  
	 <div data-role="content"  >
		<form >
		<input type="hidden"  name="hp" id="hp"   >
        <input type="hidden"  name="hc"  id="hc"  >
        <input type="hidden"  name="ha"  id="ha"  >
        <input type="hidden"  name="user_guid"  id="user_guid"  >
	    <input type="hidden"  name="userLocationGuid"  id="userLocationGuid" >
	    <input type="hidden"  name="product_guid"  id="product_guid" >
	     <input type="hidden"  name="product_count"  id="product_count" >
	    <input type="hidden"  name="gzh_hash"  id="gzh_hash" >
	     <input type="hidden"  name="open_id"  id="open_id" >
	     <input type="hidden"  name="credit"  id="credit" >
	  
	    <div data-role="fieldcontain">
	      <table style="width: 100%">
	        <tr style="height: 60px; ">
	          <td style="width: 25%" >
	            <label for="userName" style="font-size:120%">收货人</label>
	          </td>
	          <td style="width: 75%">
	            <input type="text" name="userName" id="userName" placeholder="姓名">
	            <div id="nameMsg"></div>
	          </td>
	        </tr>
	          <tr style="height: 60px;">
	          <td style="width: 35%">
	            <label for="prCiAr" style="font-size:120%">地区信息</label>
	          </td>
	          <td style="width: 65%">
	            <input type="text" name="prCiAr" id="prCiAr" placeholder="地区信息" readonly="readonly">
	          </td>
	        </tr>
	        <tr style="height: 60px;">
	          <td style="width: 35%">
	            <label for="fullArea" style="font-size:120%">详细地址</label>
	          </td>
	          <td style="width: 65%">
	            <input type="text" name="fullArea"  id="fullArea"  placeholder="详细地址"  >
	            <div id="fullAreaMsg"></div>
	          </td>
	        </tr>
	        <tr style="height: 60px;">
	          <td style="width: 35%">
	            <label for="telePhone" style="font-size:120%">联系电话</label>
	          </td>
	          <td style="width: 65%">
	            <input type="text" name="mobile"  id="mobile"  placeholder="手机或固话"  >
	            <div id="moileMsg"></div>
	          </td>
	        </tr>
	      </table>
	    </div>
		</form>
	</div>
	
	  <div data-role="footer" data-position="fixed"  data-tap-toggle="false" data-theme="b" >
	  		  <a data-rel="back" style="float:left" data-icon="arrow-l">返回</a>
	  		<a data-ajax='false'  href="#"  onclick="sumbitOrder()"  style="float:right" data-icon="check" >提交订单</a>  
	  </div>
  
  </div>
  
	  <div data-role="page" id="pagetwo" data-theme="d">
	  
<!-- 	  <div data-role="header" data-theme="d"> -->
<!-- 	  	<a data-rel="back">返回</a>   -->
<!-- 	    <h1 style="right: 10%">收货地址</h1> -->
<!-- 	  </div> -->
	
	  <div data-role="content"  id="wrapper" >
		<div id="scroller">
	     <ul id="listViewP" data-role="listview" data-dividertheme="d" style="margin-top: 0;">
	     </ul>
	     </div>
	     </div>
	</div> 
	
	<div data-role="page" id="pagethree" data-theme="d">
	  
<!-- 	  <div data-role="header" data-theme="d"> -->
<!-- 	  <a data-rel="back">返回</a>   -->
<!-- 	    <h1 style="right: 10%">收货地址</h1> -->
<!-- 	  </div> -->
	
	  <div data-role="content"  id="wrapper"  >
		<div id="scroller">
	     <ul id="listViewC" data-role="listview" data-dividertheme="d" style="margin-top: 0;">
	     </ul>
	     </div>
	     </div>
	</div>
	
		<div data-role="page" id="pagefour"  data-theme="d">
	  
<!-- 	  <div data-role="header" data-theme="d"> -->
<!-- 	    <h1 style="right: 10%">收货地址</h1> -->
<!-- 	  </div> -->
	
	  <div data-role="content"  id="wrapper"  >
		<div id="scroller">
	     <ul id="listViewA" data-role="listview" data-dividertheme="d" style="margin-top: 0;">
	     </ul>
	     </div>
	     </div>
	</div>
	
		<div data-role="page" id="pageOder"  data-theme="d">
	  
<!-- 	  <div data-role="header" data-theme="d"> -->
<!-- 	    <h1 style="right: 10%">订单生成</h1> -->
<!-- 	  </div> -->
	
	  <div data-role="content"  id="wrapper" >
		<div id="orderContent">
		
	     </div>
	     </div>
	     
	     <div data-role="footer" data-position="fixed" data-tap-toggle="false"  data-theme="b" >
	  		<a data-ajax='false'  href="#"  onclick="closeWx()"  style="float:right" >个人中心</a>  
	  </div>
	</div> 
	
	<script type="text/javascript">
	//0002a0a6-a55b-47f4-b418-7e021f0ce6c0

	var util = new Util.Param();
	var open_id=util.get('open_id');		             
	var gzh_hash=util.get('gzh_hash');
	var product_count=util.get('product_count');   
	var product_guid = util.get('product_guid'); 
	var credit = util.get('credit');

// 	var open_id = "oLuGzjl7FYFZCve80spkz_3uWcJ8";
// 	var gzh_hash = "-1624756160";	 
// 	var product_guid = "379941dc-bd0b-4a69-b6ca-0fa28d34fbbf";
// 	var product_count = "2";
// 	var credit= 200;

	$("#gzh_hash").val(gzh_hash);                 //保存open_id 与gzh_hash
	$("#open_id").val(open_id);
	$("#product_guid").val(product_guid);                 //保存商品ID与商品数量	$("#product_count").val(product_count);
	$("#credit").val(credit);   
	
	$(document).on("pageinit","#page",function(){
		var aryList;
		loadStart("...加载中",'d',false);
		$.ajax({
			cache:false,
			async:true,
			data:{
				open_id:open_id,
				gzh_hash:gzh_hash,
			},
			type:"POST",
			url:"${ctx}/integralmall/getUserLocation",
			success:function(res){
				loadStop();
				aryList = res.result;
				if(res.success){
					var li="";
					if(aryList.length<1){
						$("#listViewFullArea").html("<a data-ajax='false' id='addNewArea'  href='#'  onclick='addNewArea()'  >新增收货地址</a>");
					}else{
						for(var i=0;i<aryList.length;i++){
						li+="<div data-role='collapsible' data-collapsed='false' id='deleteArea_"+aryList[i]["userLocationGuid"]+"' ><h3>收货人："+aryList[i]["userLocationName"]+"</h3><div style='font-weight: bold; margin-top:10px'>收货地址：</div><div style='font-weight: bold; margin-top:10px'>"+aryList[i]["userLocationProvice"]+"  "+aryList[i]["userLocationCity"]
						+"  "+aryList[i]["userLocationArea"]+"  "+aryList[i]["userLocationStreet"]+"</div><div style='font-weight: bold; margin-top:10px'>联系电话：</div><div style='font-weight: bold; margin-top:10px'>"+aryList[i]["userLocationPhone"]+"</div>"+
						"<div class='ui-grid-a'><div class='ui-block-a'><a id='changeAreaInfo' data-ajax='false'  href='#'   onclick=changeAreaInfo('"+aryList[i]["userLocationGuid"]+"','"+aryList[i]["userLocationName"]+"','"+aryList[i]["userLocationProvice"]+"','"+aryList[i]["userLocationCity"]
						+"','"+aryList[i]["userLocationArea"]+"','"+aryList[i]["userLocationStreet"]+"','"+aryList[i]["userLocationPhone"]+"','"+aryList[i]["userGuid"]+"');    data-icon='info'>修改</a></div>"+
						"<div class='ui-block-b'><a data-ajax='false'  href='#'  onclick=deleteArea('deleteArea_"+aryList[i]["userLocationGuid"]+"') data-icon='delete'>删除</a></div>"+
						"<div class='ui-block-a' style='width:100%'><fieldset data-role='controlgroup' data-iconpos='right'><label for='chooseArea' style='text-align:center'>使用该收货地址</label><input type='radio'  name='favcolor' id='chooseArea' value='选择该地址' status="+aryList[i]["userLocationGuid"]+" lang="+aryList[i]["userGuid"]+"></fieldset ></div></div></div>";
						}	
						$("#footer").append("<a data-ajax='false' id='addNewArea'  href='#'  onclick='addNewArea()' style='folat:left' data-icon='plus'>新增收货地址</a>");
						$("#listViewFullArea").html(li);
					}
					
					
					
				$("#listViewFullArea").collapsibleset("refresh"); 
				$("#listViewFullArea").find("a").each(function(){
					$(this).button();
				});
				$("#listViewFullArea").find("input[type=radio]").each(function(){
					$(this).checkboxradio();  
				});
				$('#addNewArea').button();
				}
			}
		});
		
		
		  $("#prCiAr").on("click",function(){
			 	var ary;
			  	var $listView=$("#listViewP");
				$.ajax({
					cache:false,
					async:true,
					type:"POST",  
					url:" ${ctx}/integralmall/getOderProvince",
					success:function(res){
						ary = res.result;
						if(res.success){
							var li="";
							for(var i=0;i<ary.length;i++){
								li+="<li><a data-ajax='false' href='#' onclick=getOderCityByprovinceID("+ary[i].provinceID+",'"+ary[i].province+"') >"+ary[i].province+"</a></li>";
							}
							$listView.html(li);
						}
						$listView.listview("refresh");
		 			}
		 		});
			  $.mobile.changePage("#pagetwo", "slideup");
		  });         
		});
	
		function deleteArea(obj1){
			var userLocationGuid = obj1.split("deleteArea_")[1];
			$.ajax({
				cache:false,
				async:true,
				data:{
					userLocationGuid:userLocationGuid
				},
				type:"POST",  
				url:" ${ctx}/integralmall/delLocation",
				success:function(res){
					if(res.success){
						$("#"+obj1).hide(1000);
					}else{
						alert(res.message);
						}
					}
				});
		}
	
	
		function getOderCityByprovinceID(obj,obj1){
			$("#hp").val(obj1);
			var $listView=$("#listViewC");
			$.ajax({
				cache:false,
				async:true,
				data:{
					provinceID:obj
				},
				type:"POST",  
				url:" ${ctx}/integralmall/getOderCityByprovinceID",
				success:function(res){
					ary = res.result;
					if(res.success){
						var li="";
						for(var i=0;i<ary.length;i++){
							li+="<li><a data-ajax='false' href='#' onclick=getOderAreaBycityID("+ary[i].cityID+",'"+ary[i].city+"') >"+ary[i].city+"</a></li>";
						}
						$listView.html(li);
					}
					$listView.listview("refresh");
				}
			});
			  $.mobile.changePage("#pagethree", "slideup");
		}
		
		function getOderAreaBycityID(obj,obj1){
			$("#hc").val(obj1);
			var $listView=$("#listViewA");
			$.ajax({
				cache:false,
				async:true,
				data:{
					cityID:obj
				},
				type:"POST",  
				url:" ${ctx}/integralmall/getOderAreaBycityID",
				success:function(res){
					ary = res.result;
					if(res.success){
						var li="";
						for(var i=0;i<ary.length;i++){
							li+="<li><a data-ajax='false' href='#' onclick=getDetialAddress('"+ary[i].area+"') >"+ary[i].area+"</a></li>";
						}
						$listView.html(li);
					}
					$listView.listview("refresh");
				}
			});
			  $.mobile.changePage("#pagefour", "slideup");
		}
		
		function getDetialAddress(obj){
			$("#ha").val(obj);
			var place = $("#hp").val()+" "+$("#hc").val()+" "+$("#ha").val();
			if(place.length<=13){
			$("#prCiAr").val(place);
			}else{
				place = place.substring(place.length-13, place.length);
				$("#prCiAr").val(place);
			}
			 $.mobile.changePage("#pageone", "slideup");
		}
		
		var flag =false;
		function sumbitOrder(){
			checkSubmit();
			if(flag){
				var param = $("form").serialize();
				param= decodeURIComponent(param,true);
				loadStart("...订单生成中",'d',false);
				$.ajax({
					cache:false,
					async:true,
					data:param,
					type:"POST",  
					url:" ${ctx}/integralmall/setOrderInformation",
					success : function(res) {
						loadStop();
						if(res.success){
							var div = "";
							div += "<div style='text-align:center'><div style='margin-top:15px'><img src='${ctx}/admin/content/creditactive/images/done.png'>订单号：<font color='red'>"+res.result["oderNum"]+"</font></div>"+
							"<span style='display:block;margin-top: 10px;margin-left: 8px;'>您的订单已提交，请等待管理员审核！</span><span style='display:block;margin-top: 10px;margin-left: 34px;'>您可以返回个人中心查看最新的订单状态。</span></div>";
							$("#orderContent").html(div);
							 $.mobile.changePage("#pageOder", "slideup");
						}else{
							alert(res.message);
						}
					}
				});
			};
		}
		
		function checkSubmit() { 
			$("#moileMsg").html("");
			$("#nameMsg").html("");
			$("#fullAreaMsg").html("");
			if ($("#userName").val() == "") { 
				$("#nameMsg").html("<font color='red'>收货人不能为空！</font>"); 
				$("#nameMsg").focus(); 
					return false; 
				}
			if ($("#userName").val().length>10) { 
				$("#nameMsg").html("<font color='red'>输入字符不能超过10个！</font>"); 
				$("#nameMsg").focus(); 
					return false; 
				}
			if ($("#fullArea").val() == "") { 
				$("#fullAreaMsg").html("<font color='red'>详细地址不能为空！</font>"); 
				$("#fullAreaMsg").focus(); 
					return false; 
				}
			if ($("#fullArea").val().length>50) { 
				$("#fullAreaMsg").html("<font color='red'>详细地址不能超过50个汉字！</font>"); 
				$("#fullAreaMsg").focus(); 
					return false; 
				}
			
			if ($("#mobile").val() == "") { 
			$("#moileMsg").html("<font color='red'>手机号码不能为空！</font>"); 
			$("#mobile").focus(); 
				return false; 
			}
			var mobileTelephone=/^(((13[0-9]{1})|15[0-9]|18[0-9])+\d{8})$/;  //电话匹配
			var telePhone=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;                 //座机固话匹配
			if (mobileTelephone.test($("#mobile").val())||telePhone.test($("#mobile").val())) { 
				
			}else{
				$("#moileMsg").html("<font color='red'>电话格式不正确！请重新输入！</font>"); 
				$("#mobile").focus(); 
					return false;
			}
				return flag=true; 
			};
		
			function changeAreaInfo(obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8){
				$("#userLocationGuid").val(obj1);
				$("#userName").val(obj2);
				$("#prCiAr").val(obj3+" "+obj4+" "+obj5);
				$("#fullArea").val(obj6);
				$("#mobile").val(obj7);
				$("#user_guid").val(obj8);
				$.mobile.changePage("#pageone", "slideup");
			}
			
			function saveOrder(){
				var userGuid="";
				var userLocationGuid="";
				$("#listViewFullArea").find("h3").each(function(){
						 userGuid =$(this).parent().parent().find("input:checked[type=radio]").attr("lang");
						 userLocationGuid= $(this).parent().parent().find("input:checked[type=radio]").attr("status");
				});
						if(typeof(userGuid) == "undefined"){
							 var tips = "<h3 class='ui-title'>尊敬的用户，请先选择收货地址！</h3>"+
		 						"<a id='cancel' href=''#'  data-role='button' data-inline='true' data-rel='back' data-theme='c'>确定</a>";
				    			 $("#tips").html(tips);
				    			 $('#cancel').button();
				    			 $("#popdiv").popup("open");
							
						}else{
							loadStart("...订单生成中",'d',false);
							 $.ajax({
									cache:false,
									async:true,
									data:{
										userGuid:userGuid,
										userLocationGuid:userLocationGuid,
										product_guid :product_guid,
									    product_count :product_count,
									    credit:credit,
									},
									type:"POST",
									url:"${ctx}/integralmall/setOrderInformationBy",
									success : function(res) {
										loadStop();
										if(res.success){
											var div = "";
											div += "<div style='text-align:center'><div style='margin-top:15px'><img src='${ctx}/admin/content/creditactive/images/done.png'>订单号：<font color='red'>"+res.result["oderNum"]+"</font></div>"+
											"<span style='display:block;margin-top: 10px;margin-left: 8px;'>您的订单已提交，请等待管理员审核！</span><span style='display:block;margin-top: 10px;margin-left: 34px;'>您可以返回个人中心查看最新的订单状态。</span></div>";
											$("#orderContent").html(div);
											 $.mobile.changePage("#pageOder", "slideup");
										}else{
											alert(res.message);
										}
										
									}
							 });
						}
			}
			function addNewArea(){
				$("#user_guid").val("");
				$("#userLocationGuid").val("");
				$("#pageone").find("input[type=text]").each(function(){
					$(this).val("");
				});
				$.mobile.changePage("#pageone", "slideup");
			}			
			
			function closeWx(){
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

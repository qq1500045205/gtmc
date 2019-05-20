<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta content="telephone=no" name="format-detection" />
<title>问卷调查</title>
<%@ include file="/common/pre_general.jsp"%> 
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>
<style type="text/css">
html,body{ margin: 0; line-height: 22px; height: 100%; font-size:17px;}
input,label { vertical-align:middle; line-height: 6px;} 
a{ color:black; }
#content_container{ height:88%;width: 100%;overflow-y:auto;overflow-x:hidden;padding: 12px 12px 0 12px;  }
#prevButton{ width: 100px; position:fixed !important; position:absolute; z-index:100; left: 3px;bottom: 2px;  }
#nextButton{ width: 100px;position:fixed !important;  position:absolute; z-index:100; right:3px;bottom: 2px;}
#submitButton{width:100px; position:fixed !important;position:absolute; z-index:100; right:3px;bottom: 2px;}
#pageindex{width:100px;height:30px;  position:fixed !important;position:absolute; z-index:80; left:35%;bottom: 2px;line-height: 30px;}
#d1 div{ width: 40px; height:40px;top:15px; position: relative; margin-left:16px; float: left;background-color: #F2F2F2; text-align: center; line-height: 40px;cursor: pointer;z-index:90;}
#d2 div{ width: 40px; height:40px;top:30px; position: relative; margin-left:16px; float: left;background-color: #F2F2F2; text-align: center; line-height: 40px;cursor: pointer;z-index:90;}
</style>
</head>
<body> 
	<div id="content_container">
		<div id="tab1">
			<span id="q1text"></span>
			<font style="color:red; ">1分表示无法接受，5分表示一般，10分表示非常好</font><br/>
			<div id="d1" style="position: relative; float: left; width: 100%; left:1px;">
				<div id="q11">1</div>
				<div id="q12">2</div>
				<div id="q13">3</div>
				<div id="q14">4</div>
				<div id="q15">5</div>
			</div>
			<div id="d2" style="position: relative;float: left; width: 100%;left:1px;">
				<div id="q16">6</div>
				<div id="q17">7</div>
				<div id="q18">8</div>
				<div id="q19">9</div>
				<div id="q110">10</div>
			</div>
			<input type="hidden" id="q1" value="7"/>
		</div>
		<div id="tab2" style="display:none;">
			<span id="q2text"></span><br/>
			<table style="border-collapse: collapse;">
				<tr>
					<td valign="middle" style="width: 20px;"><input type="radio" name="q2" id="q2a" seq="a"/></td>
					<td valign="middle"><label id="l2a" for="q2a" ></label></td>
				</tr>
				<tr>
					<td valign="middle"><input type="radio" name="q2" id="q2b" seq="b"/></td>
					<td valign="middle"><label id="l2b" for="q2b"></label></td>
				</tr>
				<tr>
					<td valign="middle"><input type="radio" name="q2" id="q2c" seq="c"/></td>
					<td valign="middle"><label id="l2c" for="q2c"></label></td>
				</tr>
				<tr>
					<td valign="middle"><input type="radio" name="q2" id="q2d" seq="d"/></td>
					<td valign="middle"><label id="l2d" for="q2d"></label></td>
				</tr>
				<tr>
					<td valign="middle"><input type="radio" name="q2" id="q2e" seq="e"/></td>
					<td valign="middle"><label id="l2e" for="q2e"></label></td>
				</tr>
			</table>
		</div>
		<div id="tab3-a" style="display:none;">
			<span id="q3a1text"></span><font style="color: red;">(可多选)</font><br/>
			<table>
				<tr>
					<td valign="middle" style="width: 20px;"><input type="checkbox" name="q3a1" id="q3a11"/>  </td>
					<td valign="middle"><label id="l3a11" for="q3a11" ></label>  </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3a1" id="q3a12"/>  </td>
					<td valign="middle"><label id="l3a12" for="q3a12"></label>  </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3a1" id="q3a13"/>  </td>
					<td valign="middle"><label id="l3a13" for="q3a13"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3a1" id="q3a14"/>  </td>
					<td valign="middle"><label id="l3a14" for="q3a14"></label>  </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3a1" id="q3a15"/>  </td>
					<td valign="middle"><label id="l3a15" for="q3a15"></label>  </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3a1" id="q3a16"/>  </td>
					<td valign="middle"><label id="l3a16" for="q3a16"></label>  </td>
				</tr>
				<tr>
					<td width="20px"  colspan="2">其他:</td>
				</tr>
				<tr>
					<td valign="middle"  colspan="2"> <input type="text" name="q3a17" id="q3a17" style="height:30px;"/></td>
				</tr>
				<tr>
					<td valign="middle" colspan="2"> <span id="q3atext"></span><font style="color: red;">*</font> </td>
				</tr>
				<tr>
					<td valign="middle" colspan="2"> <textarea name="q3areason" id="q3areason"style=" height:80px;"></textarea> </td>
				</tr>
			</table>
			
		</div>
		<div id="tab3-b" style="display:none;">
			<span id="q3b1text"></span><font style="color: red;">(可多选)</font><br/>
			<table>
				<tr>
					<td valign="middle" style="width: 20px;"><input type="checkbox" name="q3b1" id="q3b11"/> </td>
					<td valign="middle"> <label id="l3b11" for="q3b11" ></label></td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3b1" id="q3b12"/> </td>
					<td valign="middle"><label id="l3b12" for="q3b12"></label>  </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3b1" id="q3b13"/> </td>
					<td valign="middle"><label id="l3b13" for="q3b13"></label>  </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3b1" id="q3b14"/></td>
					<td valign="middle"><label id="l3b14" for="q3b14"></label>   </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3b1" id="q3b15"/> </td>
					<td valign="middle"> <label id="l3b15" for="q3b15"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3b1" id="q3b16"/> </td>
					<td valign="middle"><label id="l3b16" for="q3b16"></label>  </td>
				</tr>
				<tr>
					<td  colspan="2">其他:</td>
				</tr>
				<tr>
					<td colspan="2"><input type="text" name="q3b17" id="q3b17" style="height:30px;"/></td>
				</tr>
				<tr>
					<td  colspan="2"><span id="q3btext"></span><font style="color: red;">*</font><br/></td>
				</tr>
				<tr>
					<td colspan="2"><textarea name="q3breason" id="q3breason" style=" height:80px;"></textarea></td>
				</tr>
			</table>
		</div>
		<div id="tab3-c" style="display:none;">
			<span id="q3c1text"></span><font style="color: red;">(可多选)</font><br/>
			<table>
				<tr>
					<td valign="middle" style="width: 20px;"><input type="checkbox" name="q3c1" id="q3c11"/> </td>
					<td valign="middle"><label id="l3c11" for="q3c11" ></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3c1" id="q3c12"/> </td>
					<td valign="middle"><label id="l3c12" for="q3c12"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3c1" id="q3c13"/> </td>
					<td valign="middle"><label id="l3c13" for="q3c13"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3c1" id="q3c14"/> </td>
					<td valign="middle"><label id="l3c14" for="q3c14"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3c1" id="q3c15"/> </td>
					<td valign="middle"><label id="l3c15" for="q3c15"></label> </td>
				</tr>
				<tr>
					<td colspan="2">其他: </td>
				</tr>
				<tr>
					<td colspan="2"><input type="text" name="q3c16" id="q3c16" style="height:30px;"/> </td>
				</tr>
				<tr>
					<td colspan="2"><span id="q3ctext"></span><font style="color: red;">*</font><br/> </td>
				</tr>
				<tr>
					<td colspan="2"><textarea name="q3creason" id="q3creason"style="height:80px;"></textarea> </td>
				</tr>
			</table>
		</div>
		<div id="tab3-d" style="display:none;">
			<span id="q3d1text"></span><font style="color: red;">(可多选)</font><br/>
			<table>
				<tr>
					<td valign="middle" style="width: 20px;"><input type="checkbox" name="q3d1" id="q3d11"/> </td>
					<td valign="middle"><label id="l3d11" for="q3d11" ></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3d1" id="q3d12"/> </td>
					<td valign="middle"><label id="l3d12" for="q3d12"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3d1" id="q3d13"/> </td>
					<td valign="middle"><label id="l3d13" for="q3d13"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3d1" id="q3d14"/> </td>
					<td valign="middle"><label id="l3d14" for="q3d14"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3d1" id="q3d15"/> </td>
					<td valign="middle"><label id="l3d15" for="q3d15"></label> </td>
				</tr>
				<tr>
					<td colspan="2">其他: </td>
				</tr>
				<tr>
					<td colspan="2"><input type="text" name="q3d16" id="q3d16" style="height:30px;"/> </td>
				</tr>
				<tr>
					<td colspan="2"><span id="q3dtext"></span><font style="color: red;">*</font><br/> </td>
				</tr>
				<tr>
					<td colspan="2"><textarea name="q3dreason" id="q3dreason"style="height:80px;"></textarea> </td>
				</tr>
			</table>	
		</div>
		<div id="tab3-e" style="display:none;">
			<span id="q3e1text"></span><font style="color: red;">(可多选)</font><br/>
			<table>
				<tr>
					<td valign="middle" style="width: 20px;"><input type="checkbox" name="q3e1" id="q3e11"/> </td>
					<td valign="middle"><label id="l3e11" for="q3e11" ></label></td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3e1" id="q3e12"/> </td>
					<td valign="middle"><label id="l3e12" for="q3e12"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3e1" id="q3e13"/> </td>
					<td valign="middle"><label id="l3e13" for="q3e13"></label></td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3e1" id="q3e14"/> </td>
					<td valign="middle"><label id="l3e14" for="q3e14"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q3e1" id="q3e15"/> </td>
					<td valign="middle"><label id="l3e15" for="q3e15"></label> </td>
				</tr>
				<tr>
					<td colspan="2"> 其他:</td>
				</tr>
				<tr>
					<td colspan="2"> <input type="text" name="q3e16" id="q3e16" style="height:28px;"/></td>
				</tr>
				<tr>
					<td colspan="2"> <span id="q3etext"></span><font style="color: red;">*</font><br/></td>
				</tr>
				<tr>
					<td colspan="2"> <textarea name="q3ereason" id="q3ereason"style=" height:80px;"></textarea></td>
				</tr>
			</table>
		</div>
		<div id="tab4" style="display:none;">
			<span id="q4text"></span><font style="color: red;">(可多选)</font><br/>
			<table style="width: 100%;">
				<tr>
					<td valign="middle" style="width: 20px;"><input type="checkbox" name="q4" id="q41"/> </td>
					<td valign="middle"><label id="l41" for="q41" ></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q4" id="q42"/> </td>
					<td valign="middle"><label id="l42" for="q42"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q4" id="q43"/> </td>
					<td valign="middle"><label id="l43" for="q43"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q4" id="q44"/> </td>
					<td valign="middle"><label id="l44" for="q44"></label> </td>
				</tr>
				<tr>
					<td valign="middle"><input type="checkbox" name="q4" id="q45"/> </td>
					<td valign="middle"><label id="l45" for="q45"></label> </td>
				</tr>
				<tr>
					<td colspan="2">其他:</td>
				</tr>
				<tr>
					<td colspan="2"><input type="text" name="q46" id="q46" style="height:30px;"/> </td>
				</tr>
			</table>
		</div>
		<div id="tab5" style="display:none;">
			<span id="q5text"></span><br/>
			<input type="radio" name="q5" id="q51" value="1" checked="checked"/><label id="l51" for="q51" >是</label><br/>
			<input type="radio" name="q5" id="q52" value="0"/><label id="l52" for="q52">否</label><br/>
		</div>
	</div>
	<button type="button" id="prevButton" class="btn_main">上一步</button>
	<button type="button" id="nextButton" class="btn_main">下一步</button>
	<button type="button" id="submitButton" class="btn_main" style="display:none;">提交</button>
	<div id="pageindex">第1页/共5页</div>

	<script type="text/javascript">
	var util = new Wx.Param();
	var gzhHash = util.getGZHHASH("##GZHHASH##");
	var openid = util.getOPENID();
	var data = [];
	
	var activeTabIdx = 1;//当前激活的问题序号
	var secondSelect ="";
	
	
	
	$(document).ready(function() {
		var d = [];
		var guid = "${param.guid}";
		var datestr = "${param.senddate}";
		var senddate = new Date(datestr);
		var nowday = new Date();
		var datediff=nowday.getTime()-senddate.getTime();
		var days=Math.floor(datediff/(24*3600*1000));
		if ((datestr ==null || ""==datestr || days>7) && (guid==null || ""==guid)){
			$("#pageindex").hide();
			$("#prevButton").hide();
			$("#nextButton").hide();
			$("#submitButton").hide();
			$("#content_container").html("非常抱歉，您的售后满意度调查项已经过期！谢谢您的关注。");
			return;
		}
		var order = "${param.orderno}";
		$.ajax({
			type : 'POST',
			url : "${ctx}/survey/check",
			async : false,
			data : {orderNo:order},
			dataType : "json",
			success : function(res) {
				if (res.success) {
					if (res.result[0].count>0){
						$("#pageindex").hide();
						$("#prevButton").hide();
						$("#nextButton").hide();
						$("#submitButton").hide();
						$("#content_container").html("您已经接受过本次调查！谢谢您的关注。");
						return;
					}
				}
			}
		});
		
		$.ajax({
			type : 'POST',
			url : "${ctx}/survey/init",
			async : false,
			data : {
				gzhHash : gzhHash
			},
			dataType : "json",
			success : function(res) {
				if (res.success) {
					data = res.result;
					$("#q1text").text("1."+data[0].q1);
					$("#q2text").text("2."+data[0].q2);
					$("#l2a").text(""+data[0].q2a);
					$("#l2b").text(""+data[0].q2b);
					$("#l2c").text(""+data[0].q2c);
					$("#l2d").text(""+data[0].q2d);
					$("#l2e").text(""+data[0].q2e);
					$("#q3a1text").text("3."+data[0].q3a1);
					$("#l3a11").text(""+data[0].q3a11);
					$("#l3a12").text(""+data[0].q3a12);
					$("#l3a13").text(""+data[0].q3a13);
					$("#l3a14").text(""+data[0].q3a14);
					$("#l3a15").text(""+data[0].q3a15);
					$("#l3a16").text(""+data[0].q3a16);
					$("#q3atext").text(""+data[0].q3a);
					$("#q3b1text").text("3."+data[0].q3b1);
					$("#l3b11").text(""+data[0].q3b11);
					$("#l3b12").text(""+data[0].q3b12);
					$("#l3b13").text(""+data[0].q3b13);
					$("#l3b14").text(""+data[0].q3b14);
					$("#l3b15").text(""+data[0].q3b15);
					$("#l3b16").text(""+data[0].q3b16);
					$("#q3btext").text(""+data[0].q3b);
					$("#q3c1text").text("3."+data[0].q3c1);
					$("#l3c11").text(""+data[0].q3c11);
					$("#l3c12").text(""+data[0].q3c12);
					$("#l3c13").text(""+data[0].q3c13);
					$("#l3c14").text(""+data[0].q3c14);
					$("#l3c15").text(""+data[0].q3c15);
					$("#q3ctext").text(""+data[0].q3c);
					$("#q3d1text").text("3."+data[0].q3d1);
					$("#l3d11").text(""+data[0].q3d11);
					$("#l3d12").text(""+data[0].q3d12);
					$("#l3d13").text(""+data[0].q3d13);
					$("#l3d14").text(""+data[0].q3d14);
					$("#l3d15").text(""+data[0].q3d15);
					$("#q3dtext").text(""+data[0].q3d);
					$("#q3e1text").text("3."+data[0].q3e1);
					$("#l3e11").text(""+data[0].q3e11);
					$("#l3e12").text(""+data[0].q3e12);
					$("#l3e13").text(""+data[0].q3e13);
					$("#l3e14").text(""+data[0].q3e14);
					$("#l3e15").text(""+data[0].q3e15);
					$("#q3etext").text(""+data[0].q3e);
					$("#q4text").text("4."+data[0].q4);
					$("#l41").text(""+data[0].q41);
					$("#l42").text(""+data[0].q42);
					$("#l43").text(""+data[0].q43);
					$("#l44").text(""+data[0].q44);
					$("#l45").text(""+data[0].q45);
					$("#q5text").text("5."+data[0].q5);
				}
			}
		});
		
		$("#q16").css("background-color","blue");
		$("#q16").css("color","white");
		$("div[id^='q1']").each(function(){
			$(this).bind("click",function(){
				if (guid!=null && ""!=guid){
					return;
				}
				$("div[id^='q1']").css("background-color","#F2F2F2");
				$("div[id^='q1']").css("color","#666");
				$(this).css("background-color","blue");
				$(this).css("color","white");
				$("#q1").val($(this).text());
			});
		});
		
		/*用户查看历史记录*/
		
		if (guid!=null && ""!=guid){
			$("input").attr("disabled","disabled");
			$("textarea").attr("disabled","disabled");
			$("#prevButton").removeAttr("disabled");
			$("#nextButton").removeAttr("disabled");
			
			$.ajax({
				type : 'POST',
				url : "${ctx}/survey/go",
				async : false,
				data :  {clickId:guid } ,
				dataType : "json",
				success : function(res) {
					d= res.result;
					$("div[id^='q1']").css("background-color","#F2F2F2");
					$("div[id^='q1']").css("color","#666");
					$("div[id='q1"+d[0].q1+"']").css("background-color","blue");
					$("div[id='q1"+d[0].q1+"']").css("color","white");
					$("#q1").val($("div[id='q1"+d[0].q1+"']").text());
					
					if (d[0].q2a=="1"){
						secondSelect="a";
						$("#q2a").attr("checked","checked");
						if (d[0].q3a11=="1"){
							$("#q3a11").attr("checked","checked");
						}
						if (d[0].q3a12=="1"){
							$("#q3a12").attr("checked","checked");
						}
						if (d[0].q3a13=="1"){
							$("#q3a13").attr("checked","checked");
						}
						if (d[0].q3a14=="1"){
							$("#q3a14").attr("checked","checked");
						}
						if (d[0].q3a15=="1"){
							$("#q3a15").attr("checked","checked");
						}
						if (d[0].q3a16=="1"){
							$("#q3a16").attr("checked","checked");
						}
						if (d[0].q3a17!=null && d[0].q3a17!="" && d[0].q3a17!="0" && d[0].q3a17!="99"){
							$("#q3a17").val(d[0].q3a17);
						}
						if (d[0].q3areason!=null && d[0].q3areason!="" && d[0].q3areason!="0" && d[0].q3areason!="99"){
							$("#q3areason").val(d[0].q3areason);
						}
					}
					
					if (d[0].q2b=="1"){
						secondSelect="b";
						$("#q2b").attr("checked","checked");
						if (d[0].q3b11=="1"){
							$("#q3b11").attr("checked","checked");
						}
						if (d[0].q3b12=="1"){
							$("#q3b12").attr("checked","checked");
						}
						if (d[0].q3b13=="1"){
							$("#q3b13").attr("checked","checked");
						}
						if (d[0].q3b14=="1"){
							$("#q3b14").attr("checked","checked");
						}
						if (d[0].q3b15=="1"){
							$("#q3b15").attr("checked","checked");
						}
						if (d[0].q3b16=="1"){
							$("#q3b16").attr("checked","checked");
						}
						if (d[0].q3b17!=null && d[0].q3b17!="" && d[0].q3b17!="0" && d[0].q3b17!="99"){
							$("#q3b17").val(d[0].q3b17);
						}
						if (d[0].q3breason!=null && d[0].q3breason!="" && d[0].q3breason!="0" && d[0].q3breason!="99"){
							$("#q3breason").val(d[0].q3breason);
						}
					}
					
					if (d[0].q2c=="1"){
						secondSelect="c";
						$("#q2c").attr("checked","checked");
						if (d[0].q3c11=="1"){
							$("#q3c11").attr("checked","checked");
						}
						if (d[0].q3c12=="1"){
							$("#q3c12").attr("checked","checked");
						}
						if (d[0].q3c13=="1"){
							$("#q3c13").attr("checked","checked");
						}
						if (d[0].q3b14=="1"){
							$("#q3c14").attr("checked","checked");
						}
						if (d[0].q3c15=="1"){
							$("#q3c15").attr("checked","checked");
						}
						if (d[0].q3c16!=null && d[0].q3c16!="" && d[0].q3c16!="0" && d[0].q3c16!="99"){
							$("#q3c16").val(d[0].q3c16);
						}
						if (d[0].q3creason!=null && d[0].q3creason!="" && d[0].q3creason!="0" && d[0].q3creason!="99"){
							$("#q3creason").val(d[0].q3creason);
						}
					}
					
					if (d[0].q2d=="1"){
						secondSelect="d";
						$("#q2d").attr("checked","checked");
						if (d[0].q3d11=="1"){
							$("#q3d11").attr("checked","checked");
						}
						if (d[0].q3d12=="1"){
							$("#q3d12").attr("checked","checked");
						}
						if (d[0].q3d13=="1"){
							$("#q3d13").attr("checked","checked");
						}
						if (d[0].q3b14=="1"){
							$("#q3d14").attr("checked","checked");
						}
						if (d[0].q3d15=="1"){
							$("#q3d15").attr("checked","checked");
						}
						if (d[0].q3d16!=null && d[0].q3d16!="" && d[0].q3d16!="0" && d[0].q3d16!="99"){
							$("#q3d16").val(d[0].q3d16);
						}
						if (d[0].q3dreason!=null && d[0].q3dreason!="" && d[0].q3dreason!="0" && d[0].q3dreason!="99"){
							$("#q3dreason").val(d[0].q3dreason);
						}
					}
					
					if (d[0].q2e=="1"){
						secondSelect="e";
						$("#q2e").attr("checked","checked");
						if (d[0].q3e11=="1"){
							$("#q3e11").attr("checked","checked");
						}
						if (d[0].q3e12=="1"){
							$("#q3e12").attr("checked","checked");
						}
						if (d[0].q3e13=="1"){
							$("#q3e13").attr("checked","checked");
						}
						if (d[0].q3b14=="1"){
							$("#q3e14").attr("checked","checked");
						}
						if (d[0].q3e15=="1"){
							$("#q3e15").attr("checked","checked");
						}
						if (d[0].q3e16!=null && d[0].q3e16!="" && d[0].q3e16!="0" && d[0].q3e16!="99"){
							$("#q3e16").val(d[0].q3e16);
						}
						if (d[0].q3ereason!=null && d[0].q3ereason!="" && d[0].q3ereason!="0" && d[0].q3ereason!="99"){
							$("#q3ereason").val(d[0].q3ereason);
						}
					}
					
					if (d[0].q41=="1"){
						$("#q41").attr("checked","checked");
					}
					if (d[0].q42=="1"){
						$("#q42").attr("checked","checked");
					}
					if (d[0].q43=="1"){
						$("#q43").attr("checked","checked");
					}
					if (d[0].q44=="1"){
						$("#q44").attr("checked","checked");
					}
					if (d[0].q45=="1"){
						$("#q45").attr("checked","checked");
					}
					if (d[0].q46!=null && d[0].q46!="" && d[0].q46!="0" && d[0].q46!="99"){
						$("#q46").val(d[0].q46);
					}
					
					if (d[0].dealerenabled=="1"){
						$("#q51").attr("checked","checked");
					}else{
						$("#q52").attr("checked","checked");
					}
				}
			});
		}
		/*用户查看历史记录*/
		
		$("input[type='radio'][id^='q2']").attr("value","0");
		$("input[type='checkbox']").attr("value","99");
		
		$("[id^='q2']").each(function(){
			$(this).bind("click",function(){
				secondSelect=$('input:radio[name="q2"]:checked').attr("seq");
			});
		});
		
		$("#prevButton").bind('click', function(){
			if (activeTabIdx==1){
				return;
			}
			activeTabIdx--;
			if (activeTabIdx<5){
				$("#nextButton").show();
				$("#submitButton").hide();
			}
			$("[id^='tab']").each(function(){
				$(this).hide();
			});
			if (activeTabIdx==3 ){
				$("#tab3-"+secondSelect).show();
			}else{
				$("#tab"+activeTabIdx).show();
			}
			$("#pageindex").text("第"+activeTabIdx+"页/共5页");
		});
		
		$("#nextButton").bind('click', function(){
			if (activeTabIdx==2 ){
				if (secondSelect=="" || secondSelect==null || secondSelect==undefined ){
					alert("请至少选择一个项目继续");
					return;
				}
			}
			if (activeTabIdx==3){
				var len = $("input[type='checkbox'][name='q3"+secondSelect+"1']:checked").length;
				var t = $("input[type='text'][name^='q3"+secondSelect+"']").val();
				var rason = $("#q3"+secondSelect+"reason").val();
				if (len==0 && t==""){
					alert("请至少选择一项或者输入其他原因");
					return;
				}
				
				if (rason==null || rason==""){
					alert("为了更好的改善我们的服务，请您具体描述原因或者经历");
					return;
				}
			}
			if (activeTabIdx==4){
				var len = $("input[type='checkbox'][name='q4']:checked").length;
				var t = $("#q46").val();
				if (len==0 && t==""){
					alert("请至少选择一项或者输入其他原因");
					return;
				}
				
				$("#nextButton").hide();
				if (guid!=null && ""!=guid){
					
				}else{
					$("#submitButton").show();
				}
			}
			activeTabIdx++;
			$("[id^='tab']").each(function(){
				$(this).hide();
			});
			if (activeTabIdx==3 ){
				$("#tab3-"+secondSelect).show();
			}else{
				$("#tab"+activeTabIdx).show();
			}
			$("#pageindex").text("第"+activeTabIdx+"页/共5页");
		});
		
		$("#submitButton").bind('click', function(){
			if (guid!=null && ""!=guid){
				alert("当前为查看历史状态不能提交！");
				return;
			}
			var datastr = '[{"q1":"'+$("#q1").val()+'",';
			datastr += '"q2'+secondSelect+'":"1",';
			datastr+='"q3'+secondSelect+'reason":"'+($("#q3"+secondSelect+"reason").val()==""?"0":$("#q3"+secondSelect+"reason").val())+'",';
			if (secondSelect=="a" || secondSelect=="b"){
				datastr+='"q3'+secondSelect+'17":"'+($("#q3"+secondSelect+"17").val()==""?"0":$("#q3"+secondSelect+"17").val())+'",';
			}else{
				datastr+='"q3'+secondSelect+'16":"'+($("#q3"+secondSelect+"16").val()==""?"0":$("#q3"+secondSelect+"16").val())+'",';
			}
			$("input:checkbox[id^='q3"+secondSelect+"']:checked").each(function(){
				datastr+='"'+$(this).attr("id")+'":"1",';
			});
			$("input:checkbox[id^='q4']:checked").each(function(){
				datastr+='"'+$(this).attr("id")+'":"1",';
			});
			datastr+='"q46":"'+($("#q46").val()==""?"0":$("#q46").val())+'",';
			
			datastr+='"openid":"${param.openid}",';
			datastr+='"orderno":"${param.orderno}",';
			
			datastr+='"dealerenabled":"'+$("input:radio[id^='q5']:checked").val()+'"}]';
			
			
			$.ajax({
				type : 'POST',
				url : "${ctx}/survey/add",
				async : false,
				data :  {formdata:datastr } ,
				dataType : "json",
				success : function(res) {
					if (res.success) {
						//alert("您的调查已提交。感谢您配合接受售后满意度调查！");
						$("#pageindex").hide();
						$("#prevButton").hide();
						$("#nextButton").hide();
						$("#submitButton").hide();
						$("#content_container").html("您的调查已提交。感谢您配合接受售后满意度调查！");
					}
				}
			});
		});
	});
	
	 var stat = new Wx.Stat();
 	 stat.report('20-1', "李俊测试", gzhHash, openid, {});	
 	
	</script>
</body>
</html>
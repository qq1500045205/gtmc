<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head> 
<title>满意度调查</title>
<%@ include file="/common/pre_general.jsp"%>
 
</head>
<body> 
<div id="question_container" class="content"></div>

	<input type="hidden" targetId="submit" urlType="url" title="提交 跳转至" />
	<script	type="text/javascript">
		var wx = new Wx.Param();
		var openid = wx.getOPENID();
		var gzhHash = wx.getGZHHASH("##GZHHASH##");
		var url = wx.getURL('##URL##', [{src: "${ctx}/wx/html/question-naire-feedback.jsp"}]) 
		$.ajax({
			  type: 'POST',
			  url: "${ctx}/question/getRandomQA",
			  data: {
				  gzhHash: gzhHash
			  },
			  success: function(data){
				  console.log(data);
				  renderHtml(data.result);
			  }
		});
		
		function renderHtml(data) {
			var htmlStr = "";
			for (var i=0; i<data.length; i++) {
				htmlStr += '<div class="form_item">';
				// htmlStr += '<div class="title">问题' + (i+1) + '</div>';
				htmlStr += '<div class="title">' + data[i].questionValue + '</div>';
				switch (data[i].type) {
				case 1: htmlStr += '<div>'
							+ '<input name="answer_'+i+'" type="radio" value="是" />是 '
							+ '<input name="answer_'+i+'" type="radio" value="否" />否 ';
						break; 
				case 2: htmlStr += "<div>" 
							+ '<input name="answer_'+i+'" type="radio" value="好评">好评 '
							+ '<input name="answer_'+i+'" type="radio" value="中评">中评 '
							+ '<input name="answer_'+i+'" type="radio" value="差评">差评 ' ; 
						break;
				case 3: htmlStr += '<textarea name="answer_'+i+'" id="answer_'+i+'" ></textarea>';
						break;
							
				}
				htmlStr += "</div>";
			}
			htmlStr += '<button type="button" id="commit" class="btn_main">提  交</button>'; 
			$("#question_container").html(htmlStr); 

				$(".radio").click(function(){
					var field = $(this).attr("for"); 
					var val = $(this).attr("value"); 
					$("#" + field).val(val);
					
					$(".radio").each(function(){
						if($(this).attr("for")==field){
							if ($(this).attr("value")== val){
								$(this).removeClass("radio_off");
								$(this).addClass("radio_on");
							} 
							else{
								$(this).removeClass("radio_on");
								$(this).addClass("radio_off");
							} 
						}
					}); 
	
				}); 

			
			
			
			$("#commit").click(function(){
				var submit = true;
				for (var i=0; i<data.length; i++) {
					switch (data[i].type) {
					case 1: data[i].answer = $("input[name=answer_"+i+"]").val();
							if (!data[i].answer && submit) {
								submit = false;
								alert("问题"+(i+1)+"未选择");
							}
							break;
					case 2: data[i].answer = $("input[name=answer_"+i+"]").val();
							if (!data[i].answer && submit) {
								submit = false;
								alert("请为本次服务评分,谢谢");
							}
							break; 
					
					case 3: data[i].answer = $("textarea[name=answer_"+i+"]").val();
							break; 
				}
		} 
				if (submit) {
					$.ajax({
						  type: 'POST',
						  url: "${ctx}/question/answerQA",
						  data: {
							  openId: openid,
							  answer: JSON.stringify(data)
						  },
						  success: function(data){
							  // console.log(data);
							  // alert(data.message);
							  Util.Browser.jump(url[0].src, "9-1");
							  // 跳转
						  }
					});
				} 
			
			});
		}
	</script> 
	<script src="${ctx}/template/dist/js/js_general.js"></script>
	<script>history.forward();</script>
	<script type="text/javascript">
		var wx = new Wx.Param();
		var gzhHash = wx.getGZHHASH("##GZHHASH##");
		var openid = wx.getOPENID();
		var stat = new Wx.Stat();
		stat.report('9-1', "满意度调查", gzhHash, openid, {});
	</script>
</body>
	
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title></title>

<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/pop.js"></script>
<link href="${ctx}/scripts/css/pop.css?Asdf" rel="stylesheet"> 
</head>
<body>
<div id="id_container"><a href="weixin://addfriend/lyn00750">一键关注梧州吃喝玩乐</a>
<div>

as
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
aaa
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>
bb
</body>
<script type="text/javascript">

function viewProfile() 
{
	typeof WeixinJSBridge != "undefined" && WeixinJSBridge.invoke && WeixinJSBridge.invoke("profile", {username: user_name,scene: "57"});
}

var confirmPop = null;  
if(confirmPop==null){  
 confirmPop = new ConfirmPop({  
 title : "友情提示",  
 msg : "确认执行此操作吗？",  
 ok : "确定",  
 cancel : "取消"},  
 {ok:function(){  
  alert("ok");  
 }});  
 confirmPop.show();  
}  
</script>
<script> 
  var x = 1, y = z = 0; 
  function add(n) { 
  return n = n+1; 
　 } 
  y = add(x); 
    
  function add(n) { 
  return n = n + 3; 
  } 
  z = add(x); 
  console.log(x, y , z);
</script>
</html>   
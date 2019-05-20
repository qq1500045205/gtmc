<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <%@ include file="/common/pre_general.jsp"%>
	<title>问卷调查</title> 
	<style>
		.list{
			padding:10px;
			text-align:center;
		}
	</style>
  </head>

  <body> 
	<div class="content" id='desc'> 
		<div class="list">
			<h3>问卷已提交</h3>
			非常感谢您配合接受购车满意度调查^_^
		</div>
	</div>
	  <script type="text/javascript">
			var wx = new Wx.Param();
			var gzhHash = wx.getGZHHASH("##GZHHASH##");
			var openid = wx.getOPENID();
			var stat = new Wx.Stat();
			stat.report('9-2', "问卷调查结果反馈", gzhHash, openid, {});
			history.forward();
		</script>
  </body>
</html>

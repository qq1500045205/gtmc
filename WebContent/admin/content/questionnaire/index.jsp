<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>满意度调查</title>
	<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-tabs.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>
	
	<script type="text/javascript" src="${ctx }/scripts/json.js"></script>
	
	<link rel="stylesheet" type="text/css"
		href="${ctx }/scripts/themes/default/om-all.css" />
	<script>
       $(function() {
           var tab = $('#make-tab').omTabs({});
           
           $("#tab1 > iframe").attr("src", "list.jsp");
           $("#tab2 > iframe").attr("src", "list2.jsp");
       });
   </script>
	
</head>
<body>
	<div id="make-tab" >
        <ul>
            <li><a href="#tab1">题目管理</a></li>
            <li><a href="#tab2">用户答题报表</a></li>
        </ul>
        <div id="tab1">
        	<iframe src="" width="100%" height="500px" border=0 frameBorder='no'></iframe>
        </div>
        <div id="tab2">
        	<iframe src="" width="100%" height="500px" border=0 frameBorder='no'></iframe>
        </div>
    </div>
    
</body>
</html>
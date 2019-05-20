<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>预约管理</title>
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
           $("#add").click(function(){
               tab.omTabs("add" , {
                   title:"",
                   content:"<iframe id='inner-frame' border=0 frameBorder='no' name='inner-frame' src='' width='100%'></iframe>",
               });
               $(this).attr("disabled" , true);
           });
           $("#tab1 > iframe").attr("src", "shijia.jsp");
           $("#tab2 > iframe").attr("src", "fuwu.jsp?yuyue_type=1");
           $("#tab3 > iframe").attr("src", "fuwu.jsp?yuyue_type=3");
           $("#tab4 > iframe").attr("src", "fuwu.jsp?yuyue_type=2");
       });
   </script>
	
</head>
<body>
	<div id="make-tab" >
        <ul>
            <li><a href="#tab1">预约试驾</a></li>
            <li><a href="#tab2">预约保养</a></li>
            <li><a href="#tab3">预约二手车</a></li>
            <li><a href="#tab4">预约年审</a></li>
        </ul>
        <div id="tab1">
        	<iframe src="" width="100%" height="500px" border=0 frameBorder='no'></iframe>
        </div>
        <div id="tab2">
        	<iframe src="" width="100%" height="500px" border=0 frameBorder='no'></iframe>
        </div>
        <div id="tab3">
        	<iframe src="" width="100%" height="500px" border=0 frameBorder='no'></iframe>
        </div>
        <div id="tab4">
        	<iframe src="" width="100%" height="500px" border=0 frameBorder='no'></iframe>
        </div>
    </div>
    
    <input name="floorGuid" id="floorGuid" type="hidden">

</body>
</html>
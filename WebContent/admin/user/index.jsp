<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>预约维护</title>
	<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-tabs.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>
	
	<script type="text/javascript" src="${ctx }/scripts/json.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${ctx }/scripts/themes/default/om-all.css" />
	<script>
		var QueryString = function () {
			  var query_string = {};
			  var query = window.location.search.substring(1);
			  var vars = query.split("&");
			  for (var i=0;i<vars.length;i++) {
			    var pair = vars[i].split("=");
			    if (typeof query_string[pair[0]] === "undefined") {
			      query_string[pair[0]] = pair[1];
			    } else if (typeof query_string[pair[0]] === "string") {
			      var arr = [ query_string[pair[0]], pair[1] ];
			      query_string[pair[0]] = arr;
			    } else {
			      query_string[pair[0]].push(pair[1]);
			    }
			  } 
			  return query_string;
		} ();
       $(function() {
    	   //alert(QueryString.type);
           var tab = $('#make-tab').omTabs({});
           $("#add").click(function(){
               tab.omTabs("add" , {
                   title:"",
                   content:"<iframe id='inner-frame' border=0 frameBorder='no' name='inner-frame' src='' width='100%'></iframe>",
                   active: true
               });
               
               $(this).attr("disabled" , true);
           });
           var list = {'shijia':0, 'baoyang':1, 'nianshen':2, 'ershouche':3};
           
           $('#make-tab').omTabs('activate', list[QueryString.type]);

           
           $("#users > iframe").attr("src", "list.jsp");
           $("#rights > iframe").attr("src", "rights.jsp");
           
            
       });
   </script>
	
</head>
<body>
	<div id="make-tab" >
        <ul>
            <li><a href="#users">用户管理</a></li>
            <li><a href="#rights">权限管理</a></li>
           
        </ul>
        <div id="users">
        	<iframe src="" width="100%" height="500px" border=0 frameBorder='no'></iframe>
        </div>
        <div id="rights">
        	<iframe src="" width="100%" height="500px" border=0 frameBorder='no'></iframe>
        </div>
        
    </div>
    
    

</body>
</html>
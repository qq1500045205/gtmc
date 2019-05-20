<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信公众平台</title>
<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-menu.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-mouse.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-resizable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-borderlayout.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-tabs.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-tree.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-combo.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid.js"></script>
<script type="text/javascript" src="${ctx }/scripts/themewriter.js"></script>

<!-- view_source_begin -->
<style>
html,body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
}

#center-tab .om-panel-body {
	padding: 0;
}

.om-panel-center {
	height: 100%;
}

#north-panel {
	line-height: 35px;
}

#north-panel a {
	text-decoration: none;
	color: #999;
}

#north-panel a:hover {
	text-decoration: none;
	color: #fff;
}

#center-panel {
	min-width: 900px; 
}
.om-widget-content{
	overflow-y:hidden;
}
</style>
<SCRIPT type="text/javascript">
//<!--
	$(document).ready(function() {
		$('body').omBorderLayout({
			panels : [ {
				id : "north-panel",
				header : false,
				region : "north",
				height : 35
			},{
				id : "center-panel",
				header : false,
				region : "center"
			}, {
				id : "west-panel",
				resizable : true,
				collapsible : true,
				title : "导航",
				region : "west",
				width : 160
			} ],
			spacing:5
		});
	  	var tabElement = $('#center-tab').omTabs({
             height : "fit",
             tabMenu : true,
             border : false,
             onActivate : function(n,event) {
               	$(window.frames["inner-frame"].document).contents().find("#grid").omGrid('reload');
             }
         });
		$("#navTree").omTree({
			dataSource : '${ctx}/admin/getusermenu',
			simpleDataModel: true,
			onClick : function(nodeData, event) {
				if(nodeData.url){
            		var tabId = tabElement.omTabs('getAlter', 'tab_'+nodeData.id);
            		if(tabId){
            			tabElement.omTabs('activate', tabId);
            		}else{
	                	tabElement.omTabs('add',{
	                        title : nodeData.text, 
	                        tabId : 'tab_'+nodeData.id,
	                        content : "<iframe id='"+nodeData.id+"' border=0 frameBorder='no' scrolling='auto' name='inner-frame' src='"+nodeData.url+"' height='"+ifh+"' width='100%'></iframe>",
	                        closable : true,
	                        contextMenu : true
	                    });
            		}
            	}
			}
		});
		 var ifh = tabElement.height() - tabElement.find(".om-tabs-headers").outerHeight() - 4; //为了照顾apusic皮肤，apusic没有2px的padding，只有边框，所以多减去2px
         $('#3Dbox').height(ifh);
		 
         
         $.ajaxSetup({   
             contentType:"application/x-www-form-urlencoded;charset=utf-8",   
             cache:false ,   
             complete:function(XHR,TS){   
            	 console.log(XHR,TS);
                 var resText=XHR.responseText;   
                 if(resText!=null && resText.indexOf("sessionState:0")>0){   
                      document.location.href='whatever_justTotheLoginPage.action';   
                      alert("您的登录已超时, 请点确定后重新登录！");    
                      //showMsg("您的登录已超时, 请重新登录!",'error');  
                 }   
             }   
         });
	});
//-->
</SCRIPT>
<!-- view_source_end -->
</head>
<body>

	<!-- view_source_begin -->
	<div id="north-panel" style="background-color: #666">
		<span style="padding-left: 10px; font-size: 14px; font-weight: bold; color: #fff; line-height: 35px; float: left;">微信公众平台管理系统</span>
		<span style="float: right; color: white;">
			<span style="margin-right: 10px;">当前登录用户：</span>
			<span style="color: #ddd; margin-right: 20px;">${loginObject.userName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span id="gzhName">${loginObject.gzhName }</span>
			</span> 
			<a href="${ctx }/admin/logout" style="margin-right: 10px; margin-left: 20px;">退出</a>
		</span>
	</div>
	<div id="center-panel"> 
		<div id="center-tab">
			<ul>
				<li><a href="#tab1">首页</a></li>
			</ul>
			<div id="tab1">
				<iframe id='3Dbox' border=0 frameBorder='no' scrolling="auto" src='' width='100%'></iframe>
			</div>
		</div>
	</div>
	<div id="west-panel">
		<ul id="navTree"></ul>
	</div>
	<!-- view_source_end -->
	<div id="view-desc"></div>
</body>
</html>
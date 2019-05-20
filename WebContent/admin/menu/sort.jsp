<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/admin_pre.jsp"%>  
<title>调整排序</title>
<style type="text/css">
/* CSS Document */
html,body,div,span,h1,h2,h3,h4,h5,h6 {
	margin: 0;
	padding: 0;
	border: 0;
	outline: 0;
	font-size: 100%;
	vertical-align: baseline;
	background: transparent
}

#sortable1,#sortable2,#sortable3 {
	list-style-type: none;
	margin: 0;
	padding: 0;
	float: left;
	margin-right: 10px;
	background: #eee;
	padding: 5px;
	width: 200px;
}

#sortable1 li,#sortable2 li,#sortable3 li {
	margin: 5px;
	padding: 5px;
	font-size: 1.2em;
	width: 170px;
}

table {
	border-collapse: collapse;
	border-spacing: 0
}

body {
	height: 100%;
	font: 12px/18px "Microsoft Yahei", Tahoma, Helvetica, Arial, Verdana,
		"\5b8b\4f53", sans-serif;
	color: #51555C;
}

#main {
	width: 680px;
	min-height: 290px;
	margin: 30px auto 0 auto;
	background: #fff;
	-moz-border-radius: 5px;
	-khtml-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}

#module_list {
	margin-left: 4px
}

.modules {
	float: left;
	width: 200px;
	height: 240px;
	margin: 10px;
	overflow: hidden;
	border: 1px solid #ddd;
	background: #f8f8f8;
}

.m_title {
	height: 30px;
	line-height: 24px;
	background: #eee;
	bottom: 0px;
	text-align: center;
}
 
</style>
<link rel="stylesheet" href="${ctx }/scripts/css/jquery-ui.css"> 
<script type="text/javascript" src="${ctx }/scripts/jquery-ui.js"></script> 
<script type="text/javascript">
	$(function() {
		var $list = $("#module_list");
		$(".m_title").bind('mouseover', function() {
			$(this).css("cursor", "move")
		});
		var data = ${jsonObject};

		for ( var i = 0; i < data.length; i++) {
			var $div = $("#module_list div:eq(" + i + ")");
			$div.children(".m_title").html(data[i].name);
			$div.attr("title", data[i].sort);
			$div.attr("id", data[i].guid);
			var html = "";
			for ( var j = 0; j < data[i].child.length; j++) {
				html += '<li class="ui-state-default" title="'+data[i].child[j].sort+'" id="'+data[i].child[j].guid+'">'
						+ data[i].child[j].name + '</li>';
			}
			$("#sortable" + (i + 1)).html(html);
		}
		$list.sortable({
			opacity : 0.6,
			//revert: true,
			cursor : 'move',
			handle : '.m_title'
		});
		var $sortable1 = $("#sortable1");
		var $sortable2 = $("#sortable2");
		var $sortable3 = $("#sortable3");
		$sortable1.sortable({
			connectWith : "#sortable1"
		});
		$sortable2.sortable({
			connectWith : "#sortable2"
		});
		$sortable3.sortable({
			connectWith : "#sortable3"
		});
		$("#saveSort").click(
				function() {
					var resData = [];
					var index = 10;
					for ( var i = 0; i < $("#module_list div").length; i++) {
						var $div = $("#module_list div:eq(" + i + ")");
						var idx = index * (i + 1);
						resData.push({
							"sort" : idx,
							"guid" : $div.attr("id")
						});
						for ( var j = 0; j < $("#module_list div:eq(" + i
								+ ") li").length; j++) {
							resData.push({
								"sort" : idx + (j + 1),
								"guid" : $(
										"#module_list div:eq(" + i + ") li:eq("
												+ j + ")").attr("id")
							});
						}
					}
					
					 $.getJSON("${ctx}/ajax/saveSortWxMenu",{data:JSON.stringify(resData)},function(data){
	                	 if(data.success){
	             			 parent.$.omMessageBox.alert({
	 	                         type:'success',
	 	                         title:'成功',
	 	                         content:data.message,
	 	                         onClose:function(v){
	 	                        	parent.$('#grid').omGrid('reload');
	 	                        	parent.$("#sort-dialog").omDialog("close");
	 	                         }
	 	                     });
	             		  } else{
	             			 parent.$.omMessageBox.alert({
	   	                         type:'error',
	   	                         title:'失败',
	   	                         content:data.message
	   	                     }); 
	             		  }
	                 });
				});
	});
</script>
</head>

<body>
	<div id="main">
		<div id="module_list">
			<div class="modules" title="10">
				<h3 class="m_title"></h3>
				<ul id="sortable1">

				</ul>
			</div>
			<div class="modules" title="20">
				<h3 class="m_title"></h3>
				<ul id="sortable2">

				</ul>
			</div>
			<div class="modules" title="30">
				<h3 class="m_title"></h3>
				<ul id="sortable3">

				</ul>
			</div>
			<button id="saveSort" class="btn-def">保存排序</button>
		</div>
	</div>
</body>

</html>
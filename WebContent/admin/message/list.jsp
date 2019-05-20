<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息列表</title>
<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-mouse.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-resizable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-draggable.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-position.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-combo.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-messagebox.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-button.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-buttonbar.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/ui/om-numberfield.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-calendar.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-dialog.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/ui/om-grid-roweditor.js"></script>
<script type="text/javascript"
	src="${ctx }/scripts/ui/om-grid-rowexpander.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-fileupload.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/themes/default/om-all.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/themes/default/om-grid-rowexpander.css" />
<!-- view_source_begin -->
<style>
html,body {
	font-family: "Arial", "Helvetica", "Verdana", "sans-serif";
	font-size: 12px;
	width: 100%;
	height: 96%;
	margin: 0 auto;
	padding: 0;
	font-family: "微软雅黑", Arial;
}

.popwin {
	border: solid 1px #ccc;
	margin: 10px;
	background-color: #fff;
	padding: 5px;
	border-radius: 5px;
	float: left;
	width: 720px;
	color: #666;
}

.item1_label {
	width: auto;
	display: inline;
	float: left;
	background-color: #eee;
	border-radius: 3px;
}

.td7 {
	line-height: 30px;
	float: left;
	padding-left: 10px;
	border-bottom: 1px solid #ccc;
}

li {
	list-style: none;
	margin: 0;
	padding: 0;
}

ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

.om-panel {
	margin-left: 0px;
	margin-top: 5px;
}

#search-panel li.label {
	padding-left: 5px;
	padding-right: 5px;
	line-height: 25px;
	width: 60px;
}

#search-panel .input-text {
	border: 1px solid #ccc;
	border-radius: 3px;
	background: #fcfcfc;
	padding: 2px;
	height: 21px;
	font-size: 14px;
	vertical-align: middle;
	width: 100px;
}

#search-panel .input-text:focus {
	outline: none;
	background: #ffffff;
}

#search-panel span.om-combo,#search-panel span.om-calendar {
	vertical-align: middle;
}

input#navSearch {
	width: 135px;;
	height: 16px;
	margin-left: 2px;
	background: url("${ctx}/scripts/image/nav-search.png") no-repeat scroll
		0 0 #FFFFFF;
	padding-left: 20px;
	border: 1px solid #99A8BB;
	line-height: 16px;
}

input.error {
	border: 1px solid red;
}

.om-button {
	font-size: 12px;
}

#nav_cont {
	width: 90%;
	margin-left: auto;
	margin-right: auto;
}

input {
	border: 1px solid #A1B9DF;
	vertical-align: middle; 
}

input:focus {
	border: 1px solid #3A76C2;
}

.om-calendar input:focus,.om-combo input:focus {
	border: none;
}

.sex {
	width: auto;
	border: none;
}

.sex:focus {
	border: none;
}

.input_slelct {
	width: 81%;
}

.textarea_text {
	border: 1px solid #A1B9DF;
	height: 40px;
	width: 90%;
}

table.grid_layout tr td {
	font-weight: normal;
	height: 15px;
	padding: 5px 0;
	vertical-align: middle;
}

.color_red {
	color: red;
}

.toolbar {
	padding: 12px 0 5px;
	text-align: center;
}

.separator {
	border-top: 1px solid #adadad;
	height: 2px;
	line-height: 2px;
	overflow: hidden;
}

.address {
	width: 445px;
}

.om-span-field input:focus {
	border: none;
}

.errorImg {
	background: url("images/msg_bg.png") no-repeat scroll 0 0 transparent;
	height: 16px;
	width: 16px;
	cursor: pointer;
}

.errorMsg {
	border: 1px solid gray;
	background-color: #FCEFE3;
	display: none;
	position: absolute;
	margin-top: -18px;
	margin-left: 18px;
}

.display {
	color: gray;
	font-size: 13px;
}

.querycontent {
	color: blue;
	font-size: 12px;
	width: 500px;
}

.button-css {
	width: 120px;
	height: 35px;
	margin-top: 10px;
	cursor: pointer;
	float: right;
	background-color: #00A1CB;
	border-color: #007998;
	color: white;
	text-shadow: 0 -1px 1px rgba(0, 40, 50, 0.35);
	border-radius: 3px;
}

.item-block {
	width: 120px;
	height: 100px;
	border: 1px solid #eee;
	-webkit-border-radius: 4px;
	border-radius: 4px;
	text-align: center;
	line-height: 100px;
}

.item-block:hover {
	background: #f8f8f8;
	cursor: pointer;
}
 

.om-dialog { 
	font-family: "微软雅黑", Arial;
 
}
.om-dialog .om-widget-content{
	margin:0;
	float:left;
	padding:20px;
}
</style>
<script type="text/javascript">
	var rowDatas = [];
	$(document).ready(function() {
	    $('#grid').omGrid({
	        limit: 14,
	        singleSelect: false,
	        width: '100%',
	        height: 440,
	        autoFit: true,
	        showIndex: false,
	        dataSource: "${ctx}/ajax/griddata.action?type=wxnewscontent",
	        colModel: [{
	            header: '消息名称',
	            name: 'newsName',
	            align: 'left',
	            width: 150,
	            renderer: function(colValue, rowData, rowIndex) {
	                rowDatas[rowIndex] = rowData;
	                console.log(rowData);
	                return "<a href='javascript:;' onclick=editMessage(" + rowIndex + ")>" + colValue + "</a>";
	            }
	        },
	        {
	            header: '标题',
	            name: 'newsTitle',
	            align: 'left',
	            width: 150
	        },
	        {
	            header: '消息组件类型',
	            name: 'typeName',
	            align: 'left'
	        },
	        {
	            header: '创建人',
	            name: 'createdBy',
	            align: 'left',
	            width: 100
	        },
	        {
	            header: '创建时间',
	            name: 'createdOn',
	            align: 'left',
	            width: 200
	        }],

	    });

	    $('#buttonbar').omButtonbar({
	        btns: [{
	            label: "新增",
	            id: "button-new",
	            icons: {
	                left: '${ctx}/scripts/image/add.png'
	            },
	            onClick: function() {
	                $("#dialog").omDialog('open');
	            }
	        },
	        {
	            separtor: true
	        },
	        {
	            label: "删除",
	            id: "button-remove",
	            disabled: false,
	            icons: {
	                left: '${ctx}/scripts/image/remove.png'
	            },
	            onClick: function() {
	                var dels = $('#grid').omGrid('getSelections', true);
	                if (dels.length <= 0) {
	                    $.omMessageBox.alert({
	                        type: 'alert',
	                        title: '提示',
	                        content: '请选择删除的记录！'
	                    });
	                    return;
	                }
	                $.omMessageBox.confirm({
	                    title: '确认删除',
	                    content: '删除后相关的数据也将删除,您确认要删除吗？',
	                    onClose: function(v) {
	                        if (v) {
	                            var items = "";
	                            for (var i = 0; i < dels.length; i++) {
	                                items += "item=" + dels[i].newsGuid;
	                                if (i < dels.length - 1) {
	                                    items += "&";
	                                }
	                            }
	                            $.getJSON("${ctx}/ajax/deleteMessage?" + items,
	                            function(data) {
	                                if (data.success) {
	                                    $.omMessageBox.alert({
	                                        type: 'success',
	                                        title: '成功',
	                                        content: data.message,
	                                        onClose: function(v) {
	                                            $('#grid').omGrid('reload');
	                                        }
	                                    });
	                                } else {
	                                    $.omMessageBox.alert({
	                                        type: 'error',
	                                        title: '失败',
	                                        content: data.message
	                                    });
	                                }
	                            });
	                        }
	                    }
	                });
	            }
	        }]
	    });

	    $("#dialog").omDialog({
	        autoOpen: false,
	        height: 180,
	        width: 430,
	        modal: true
	    });

	    $("#search-panel").omPanel({
	        title: "搜索",
	        collapsed: false,
	        collapsible: false
	    });

	    $("#startDate").omCalendar();
	    $("#endDate").omCalendar();
	    $('input[name=userBirth]').omCalendar(); //初始化Calendar
	    $('span#button-search').omButton({
	        icons: {
	            left: '${ctx}/scripts/image/search.png'
	        },
	        width: 70
	    });

	    $('#fileExtend').omFileUpload({
	        action: '${ctx}/common/uploadImage',
	        swf: '${ctx }/scripts/ui/om-fileupload.swf',
	        fileExt: '*.jpg;*.png;',
	        fileDesc: 'Image Files',
	        sizeLimit: 2000000,
	        //限制大小为1m
	        autoUpload: true,
	        onComplete: function(ID, fileObj, response, data, event) {
	            var res = JSON.parse(response);
	            $(this).parent().find("img").attr("src", "${ctx}" + res.filepath);
	            $(this).parent().find("input[name=userPic]").val(res.filepath);
	        }
	    });

	    $("#submit").omButton();
	    $("#reset").omButton();

	    var options = {
	        beforeSubmit: showRequest,
	        success: showResponse
	    };

	    $('#addUserForm').submit(function() {
	        $(this).omAjaxSubmit(options);
	        return false; //此处必须返回false，阻止常规的form提交。
	    });

	});

	//编辑消息
	function editMessage(index) {
		var saveRowData = rowDatas[index];
		console.log("data clicked:", saveRowData);
		switch (saveRowData.type) {
		case "text": {
			data = {};
			data.name = saveRowData.newsName;
			data.content = saveRowData.newsContent;
			data.dynamicContent = saveRowData.dynamicContent;
			data.newsGuid = saveRowData.newsGuid;
			saveCookie(data);
			addContent("编辑文本消息", 'editNews',
					'${ctx}/admin/message/edit-text.jsp');
			break;
		}
		case "single": {
			data = {};
			data.name = saveRowData.newsName;
			data.title = saveRowData.newsTitle;
			data.author = saveRowData.newsAuthor;
			data.picSrc = saveRowData.newsPic;
			data.desc = saveRowData.newsDescription;
			data.content = saveRowData.newsContent;
			data.url = saveRowData.newsUrl;
			data.urlParamName = saveRowData.urlParamName;
			data.urlParamContent = saveRowData.urlParamContent;
			data.otherUrlParam = saveRowData.otherUrlParam;
			data.newsGuid = saveRowData.newsGuid;
			console.log("before",data);
			saveCookie(data);
			addContent("编辑单图文消息", 'editNews',
					'${ctx}/admin/message/edit-single-news.jsp');
			break;
		}
		case "news": {
			data = {};
			data.name = saveRowData.newsName;
			data.title = saveRowData.newsTitle;
			data.author = saveRowData.newsAuthor;
			data.picSrc = saveRowData.newsPic;
			data.content = saveRowData.newsContent;
			data.url = saveRowData.newsUrl;
			data.urlParamName = saveRowData.urlParamName;
			data.urlParamContent = saveRowData.urlParamContent;
			data.otherUrlParam = saveRowData.otherUrlParam;
			data.newsGuid = saveRowData.newsGuid;
			console.log("post data", data, saveRowData);
			saveCookie(data);
			addContent("编辑多图文消息", 'editNews',
					'${ctx}/admin/message/edit-news.jsp');
			break;
		}
		}

		console.log(index);
	}

	function saveCookie(data) {
		if (window.localStorage) {
			window.localStorage.result = JSON.stringify(data);
		}
	}

	//提交前的处理
	function showRequest(formData, jqForm, options) {
		var queryString = $.param(formData);
		console.log(queryString);
		return true;
	}

	//提交返回的处理
	function showResponse(responseText, statusText, xhr, $form) {
		console.log(responseText);
		$.omMessageBox.waiting('close');
		$("#dialog").omDialog("close");
		var json = eval(responseText);
		if (json.success) {
			$.omMessageBox.alert({
				type : 'success',
				title : '成功',
				content : '添加成功'
			});
			$('#grid').omGrid("reload");
			$("#reset").click();
			$("#picImg").attr("scr", "");
		} else {
			$.omMessageBox.alert({
				type : 'error',
				title : '失败',
				content : '添加失败'
			});
		}
	}

	function CheckTabsExist(currentTabId) {
		console.log(currentTabId)
		var total = parent.$('#center-tab').omTabs('getLength');
		console.log(total);
		for ( var i = 0; i < total; i++) {
			var tabId = parent.$('#center-tab').omTabs('getAlter', i);
			if (tabId == currentTabId) {
				return true;
			}
		}
		return false;
	}

	function addContent(title, name, url) {
		var tabId = "add-" + name + "-tab-id";
		if (CheckTabsExist(tabId)) {
			var index = parent.$("#center-tab").omTabs('getAlter', tabId);
			parent.$('#center-tab').omTabs('close', index);
		}

		parent
				.$("#center-tab")
				.omTabs(
						'add',
						{
							title : "新增" + title,
							content : "<iframe id='inner-frame' border=0 frameBorder='no' name='inner-frame' src='"
									+ url
									+ "' width='100%' height='600'></iframe>",
							closable : true,
							tabId : tabId
						});

		$("#dialog").omDialog("close");
	}
</script>
<!-- view_source_end -->
</head>
<body>

	<!-- view_source_begin -->
	<div id="search-panel">
		<div>
			<span class="label">关键词：</span> <input type="text" class="input-text" />
			<span class="label">添加时间(S)：</span> <input id="startDate"
				name="startDate" /> <span class="label">添加时间(E)：</span> <input
				id="endDate" name="endDate" /> <span id="button-search">搜索</span>
		</div>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin-bottom: 5px;"></div>
		<table id="grid"></table>
	</div>

	<div id="dialog" title="新增消息" style="overflow: hidden" class="dialog">
		<div class="item-block" style="float: left; "
			onclick="addContent('文本消息','text','${ctx}/admin/message/add-text.jsp')">
			文本消息</div>
		<div class="item-block"
			style="float: left; margin-left: 10px;"
			onclick="addContent('单图文消息','news','${ctx}/admin/message/add-single-news.jsp')">
			单图文消息</div>
		<div class="item-block"
			style="float: left; margin-left: 10px; "
			onclick="addContent('多图文消息','news','${ctx}/admin/message/add-news.jsp')">
			多图文消息</div>
	</div>
</body>
</html>
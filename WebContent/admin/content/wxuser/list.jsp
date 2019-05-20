<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>关注用户管理</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/css/common.css" />

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
	<script type="text/javascript">
	$(document).ready(function() {
		$('#grid').omGrid({
	        limit: 14,
	        singleSelect: false,
	        width: '100%',
	        height: 440,
	        autoFit: true,
	        showIndex: false,
	        dataSource: "${ctx}/ajax/griddata.action?type=wxuser&param=",
	        colModel: [{
	            header: '用户名',
	            name: 'userName',
	            align: 'left',
	            width: 100
	        },
	        {
	            header: '微信名',
	            name: 'nickName',
	            align: 'left',
	            width: 100
	        },
	        {
	            header: 'OpenID',
	            name: 'openId',
	            align: 'left',
	            width: 120
	        },
	        {
	            header: '手机号码',
	            name: 'userTel',
	            align: 'left',
	            width: 100
	        },
	        {
	            header: '状态',
	            name: 'status',
	            align: 'left',
	            width: 80,
	            renderer: function(value, rowData, rowIndex) {
	                if (value == 1) {
	                    return "已关注";
	                } else if (value == 2) {
	                    return "已取消关注";
	                }
	                return value;
	            }
	        },
	        {
	            header: '关注来源',
	            name: 'sourceFrom',
	            align: 'left',
	            width: 150
	        },
	        {
	            header: 'GZHHASH',
	            name: 'gzhHash',
	            align: 'left',
	            width: 100
	        },
	        {
	            header: '关注时间',
	            name: 'createdOn',
	            align: 'left',
	            width: 150
	        },
	        {
	            header: '修改时间',
	            name: 'modifyOn',
	            align: 'left',
	            width: 150
	        },
	        {
	            header: '取消关注时间',
	            name: 'cancelOn',
	            align: 'left',
	            width: 150
	        }],

	    });

	    $('#buttonbar').omButtonbar({
	        btns: [{
	            label: "导出",
	            id: "button-remove",
	            disabled: false,
	            icons: {
	                left: '${ctx}/scripts/image/open-close.png'
	            },
	            onClick: function() {
	                
	            }
	        },{
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
	                    content: '删除后不可恢复,您确认要删除吗？',
	                    onClose: function(v) {
	                        if (v) {
	                            var items = "";
	                            for (var i = 0; i < dels.length; i++) {
	                                items += "items=" + dels[i].userGuid;
	                                if (i < dels.length - 1) {
	                                    items += "&";
	                                }
	                            }
	                            //$.getJSON("${ctx}/ajax/deleteWebuser?"+items,function(data){
	                            //  if(data.success){
	                            $.omMessageBox.alert({
	                                type: 'success',
	                                title: '成功',
	                                content: data.message,
	                                onClose: function(v) {
	                                    $('#grid').omGrid('deleteRow', dels);
	                                    $('#grid').omGrid('reload');
	                                }
	                            });
	                            //  } else{
	                            //	$.omMessageBox.alert({
	                            ///         type:'error',
	                            //         title:'失败',
	                            //         content:data.message
	                            //    }); 
	                            // }
	                            // });
	                        }
	                    }
	                });
	            }
	        }]
	    });

	    $("#search-panel").omPanel({
	        title: "搜索",
	        collapsed: false,
	        collapsible: false
	    });

	    $("#startDate").omCalendar();
	    $("#endDate").omCalendar();
	    $('span#button-search').omButton({
	        icons: {
	            left: '${ctx}/scripts/image/search.png'
	        },
	        width: 70
	    });

	});
	</script>
</body>
</html>
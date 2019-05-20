<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关注用户管理</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>
<script type="text/javascript" src="${ctx }/scripts/common.js"></script>

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/css/common.css" />
<style>
.input-text{
	width:60px;
}
.input-combo{
	width:100px;
}
.input-label{
	display:inline-block;
	padding:2px 4px;
}
.input-label .label{
	display:inline-block;
	width: 70px;
}
#button-search{
	height:25px!important;
	line-height: 22px!important;
	float:left;
}
#button-reset{
	border: 1px solid #007998;
	border-radius: 3px;
	line-height:22px;
	height: 23px;
	padding: 2px 20px 0px 20px;
	background: linear-gradient(top, #00b5e5, #008db2);
	background-color: #00a1cb;
	box-shadow: inset 0px 1px 0px rgba(255, 255, 255, 0.5), 0px 1px 2px
	 rgba(0, 0, 0, 0.15);
	color: #fff;
	background-position: 10% 50% !important;
	text-shadow: 0 -1px 1px rgba(0, 40, 50, 0.35);
	display:inline-block;
	float:right;
	margin-top:2px;
	cursor:pointer;
}
.test .om-state-default{
	float:left;
}
</style>
</head>
<body>
	<!-- view_source_begin -->
	<div id="search-panel">
		<form id="searchForm" name="searchForm">
			<div>
				<div class='input-label'><span class='label'>姓名</span><span><input type="text" name="userName" id="userName" class="input-text" /></span></div>
			</div>
			<div>
				<div class='input-label'><span class='label'>手机号码</span><span><input type="text" name="userTel" id="userTel" class="input-text" /></span></div>
				<div class='input-label'><span class='label'>性别</span><span><input type="text" name="userSex" id="userSex" class="input-text" /></span></div>
			</div>
			<div>
				<div class='input-label'><span class='label'>关注时间从</span><span><input type="text" name="gzStartDate" id="gzStartDate" class="input-text" /> 至 <input type="text" name="gzEndDate" id="gzEndDate" class="input-text" /></span></div>
			</div>
			<div>
				<div class='input-label test'>
					 	<span id="button-search">搜索</span>
						<span type="reset" id='button-reset' style="margin-left:10px;" name="reset" id='reset' onclick='document.searchForm.reset();'>重置</span>
				</div>
			</div>
		</form>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin-bottom: 5px;"></div>
		<table id="grid"></table>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
	 	$("#search-panel").omPanel({
	        title: "搜索",
	        collapsed: false,
	        collapsible: false
	    });
	    
	    $("#userSex").omCombo({
			width : 80,
			editable : false,
			dataSource : [ {
				text : '男',
				value : '男'
			}, {
				text : '女',
				value : '女'
			} ]
		});

	    $("#gzStartDate").omCalendar({
               dateFormat : "yy-mm-dd H:i:s",
               showTime : true
           });
	    $("#gzEndDate").omCalendar({
               dateFormat : "yy-mm-dd H:i:s",
               showTime : true
           });
	    
	    $('span#button-search').omButton({
	        icons: {
	            left: '${ctx}/scripts/image/search.png'
	        },
	        width: 70,
	        onClick: function(event){
	        	//定义一个form对象
	        	var form = new Util.Form("searchForm");
	        	var data = form.getValues();
				$('#grid').omGrid('setData', '${ctx}/ajax/searchdata?param=&data='+encodeURIComponent(JSON.stringify(data)));
	        }
	    });
	    
	    $('#grid').omGrid({
	        limit: 14,
	        singleSelect: false,
	        width: '100%',
	        height: 440,
	        autoFit: true,
	        showIndex: false,
	        dataSource: "${ctx}/ajax/griddata.action?type=wxuser&param=",
	        colModel: [
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
            	 	//定义一个form对象
    	        	var form = new Util.Form("searchForm");
    	        	var data = form.getValues();
    	        	location.href = '${ctx}/export/export?param=&data='+encodeURIComponent(JSON.stringify(data));
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

	});
	</script>
</body>
</html>
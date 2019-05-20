<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>模版消息列表</title>
<%@ include file="/common/admin_pre.jsp"%>
<!-- view_source_begin -->
<style> 

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

.inpt2 {
	vertical-align: middle;
	width: 150px;
}
 

#search-panel span.om-combo,#search-panel span.om-calendar {
	vertical-align: middle;
}
 
input.error {
	border: 1px solid red;
}
 
#nav_cont {
	width: 90%; 
	margin-left: auto;
	margin-right: auto;
}

.om-calendar input:focus,.om-combo input:focus {
	border: none;
}

.input_slelct {
	width: 81%;
}

table.grid_layout tr td {
	font-weight: normal;
	height: 15px;
	padding: 5px 0;
	vertical-align: middle;
}
 d
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
.om-dialog .om-widget-content{
	margin:0;
	float:left;
	padding:20px;
} 
 label.error{
	background: #fff6bf url(images/alert.png) center no-repeat;
	background-position: 5px 50%;
	text-align: left;
	padding: 2px 20px 2px 25px;
	border: 1px solid #ffd324;
	display: none;
	width: 200px;
	margin-left: 10px;
}

</style>

<!-- view_source_end -->
</head>
<body>

	<!-- view_source_begin -->
	<div id="search-panel">
		<div>
			<span class="label">关键词：</span>
				<input type="text" class="input-text" />
			<span class="label">添加时间(S)：</span>
				<input id="startDate" name="startDate" />
			<span class="label">添加时间(E)：</span>
				<input id="endDate" name="endDate" /> <span id="button-search">搜索</span>
		</div>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin-bottom: 5px;"></div>
		<table id="grid"></table>
	</div>

	<div id="dialog" title="新增模版消息" style="height: 350px;">
		<div class="color_red" >
			1、编码为自行定义，尽量使用标题对应首字母缩写，最多5位。<br/>
			2、模版ID、标题从微信工作平台模版消息中获取。<br/>
			3、名称和说明从从微信工作平台模版消息详细内容获取。<br/>
			&nbsp;&nbsp;&nbsp;如：业主名称:{{userName.DATA}},则名称为“userName”,说明为“业主名称”。
		</div>
		<div>
			<form action="${ctx }/ajax/saveMsgTmp" id="msgForm" method="post">
				<table>
					<tr>
						<td>标题<span class="color_red">*</span></td>
						<td><input type="text" name="tmpName" class="input-text" ></td>
					</tr>
					<tr>
						<td>颜色</td>
						<td><input type="text" name="topColor" class="input-text" value="#FF0000"></td>
					</tr>
					<tr>
						<td>模版ID<span class="color_red">*</span></td>
						<td><input type="text" name="tmpId" class="input-text" ></td>
					</tr>
					<tr>
						<td>编码<span class="color_red">*</span></td>
						<td><input type="text" name="tmpCode" class="input-text" ></td>
					</tr>
				</table>
				<table>
					<tr>
						<td>名称<span class="color_red">*</span></td>
						<td>说明</td>
						<td>颜色</td>
					</tr>
					<tr>
						<td><input type="text" name="tmpField" class="input-text inpt2" ></td>
						<td><input type="text" name="tmpDesc"  class="input-text inpt2" ></td>
						<td><input type="text" name="tmpColor" class="input-text inpt2" value="#173177"></td>
					</tr>
					
					<tr>
						<td rowspan="3"> &nbsp;<br/>
						<a href="javascript:;" onclick="$(this).parent().parent().before('<tr><td><input type=text name=tmpField class=input-text inpt2></td><td><input type=text name=tmpDesc class=input-text></td><td><input type=text name=tmpColor class=input-text value=#173177></td></tr>')">
							 添加 
						</a>
						</td>
					</tr>
				</table>
				<div style="text-align:center;padding-top:10px;"> 
					<input type="submit" id="sub" name="提交" class="btn-def">						
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	var html = $("#msgForm").html();
	$(document).ready(function() {
	    $('#grid').omGrid({
	        limit: 14,
	        singleSelect: false,
	        width: '100%',
	        height: 440,
	        autoFit: true,
	        showIndex: false,
	        dataSource: "${ctx}/ajax/griddata.action?type=msgTemplate",
	        colModel: [{
	            header: '标题',
	            name: 'tmpName',
	            align: 'left',
	            width: 150,
	            renderer: function(colValue, rowData, rowIndex) {
	                return "<a href='javascript:;' onclick=editMessage(" + rowIndex + ")>" + colValue + "</a>";
	            }
	        },
	        {
	            header: '名称',
	            name: 'tmpField',
	            align: 'left',
	            width: 150
	        },
	        {
	            header: '说明',
	            name: 'tmpDesc',
	            align: 'left',
	            width: 150
	        },
	        {
	            header: '颜色',
	            name: 'tmpColor',
	            align: 'left'
	        },
	        {
	            header: '模版ID',
	            name: 'tmpId',
	            align: 'left',
	            width: 200
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
	        },
	        {
	            header: '修改人',
	            name: 'modifyBy',
	            align: 'left',
	            width: 100
	        },
	        {
	            header: '修改时间',
	            name: 'modifyOn',
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
	        height: 400,
	        width: 530,
	        modal: true,
	        onClose : function(event) {
	        	$("#msgForm").html(html);
	        }
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
	    
	    $.validator.addMethod("tmpCodeCheck", function(value) {
            return value != "yyfu1";
        }, '此编码已被使用，请换一个再试！');
	    //$.validator.addMethod("cRequired", $.validator.methods.required,"请输入名称");
	    //$.validator.addClassRules("input-text2", { cRequired: true});
	    $("#msgForm").validate({
            rules : {
            	tmpName : {
                	required : true
                },
                tmpId : {
                    required : true
                },
                tmpCode : {
                    required : true,
                    tmpCodeCheck : true
                }
            },
            messages : {
            	tmpName : {
                    required : "请输入标题"
                },
                tmpId : {
                    required : "请输入模版Id"
                },
                tmpCode : {
                    required : "请输入模版编码"
                }
            },
            submitHandler : function(){
            	//$("#msgForm").html(html);
            	$('#msgForm').omAjaxSubmit(options);
                return false;
            }
        });
	    
	    var options = {
                beforeSubmit : showRequest, 
                success : showResponse
        };
		
        function showRequest(formData, jqForm, options) {
            return true;
        }
        function showResponse(responseText, statusText, xhr, $form) {
            console.log(responseText);
        }
	    
	});

</script>
</body>
</html>
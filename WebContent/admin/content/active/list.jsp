<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<title>品牌活动</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/css/common.css" /> 
</head>

<body>
	<div id="search-panel">
		<div>
			<form action="#" id="search-form" method="post">
				<div class="srch_line">
					<ul>
						<li><input id="search-status" name="oppQuery.status" /></li>
						<li><input style="width: 100px;" /></li>
					</ul>
				</div>
				<div class="srch_line">
					<span id="button-search"> 搜索 </span>
				</div>
			</form>
		</div>
	</div>
	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin: 5px 0 5px 0;">
		</div>
		<div style="width: 100%;">
			<table id="grid">
			</table>
		</div>
	</div>
	<div id="dialog-add" title="添加品牌活动">
		<form action="${ctx }/ajax/addActive" id="addForm" method="post">
			<input type="hidden" name="actGuid" class="input-text">
			<table>
				<tr>
					<td width="20%">活动名称 <span class="color_red"> * </span>
					</td>
					<td width="80%"><input type="text" name="actName"
						class="input-text"> <span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>活动摘要 <span class="color_red"> * </span>
					</td>
					<td><textarea name="actDesc" id="actDesc" class="area-text">
                                    </textarea> <span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>是否支持报名 <span class="color_red"> * </span>
					</td>
					<td><input type="radio" name="actType" id="actType"
						class="input-radio" value="0" checked="checked">
						不支持报名&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="actType"
						id="actType" class="input-radio" value="1"> 支持报名 <span
						class="errorMsg"> </span> <a href="javascript:;" id="setForm"
						style="display: none"> 设置报名表单 </a> <br /></td>
				</tr>
				<tr>
					<td>车主相关 <span class="color_red"> * </span>
					</td>
					<td><input type="radio" name="actType2" id="actType2"
						class="input-radio" value="0" checked="checked">
						针对非车主&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="actType2"
						id="actType2" class="input-radio" value="1"> 针对车主 <span
						class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>开始时间 <span class="color_red"> * </span>
					</td>
					<td><input name="startOn" id="startOn" class="input-combo">
						<span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>结束时间 <span class="color_red"> * </span>
					</td>
					<td><input name="endOn" id="endOn" class="input-combo">
						<span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>活动封面</td>
					<td><img id="preview" src="${ctx }/scripts/image/pic.png"
						style="width: 100px; height: 100px; border: 1px solid #ddd;">
						<input id="fileExtend" style="display: inline;"> <input
						type="hidden" name="actImage" id="pic" style="display: inline">
					</td>
				</tr>
				<tr>
					<td>活动详情 <span class="color_red"> * </span>
					</td>
					<td><textarea name="actContent" id="actContent"
							class="area-text" style="width: 100%; height: 200px;">
                                    </textarea> <span class="errorMsg"> </span></td>
				</tr>
			</table>
			<div>
				<input type="submit" id="sub" name="提交" class="button-css">
				<input type="reset" id="reset" style="display:none">
			</div>
		</form>
	</div>
	<div id="dialog-assign" title="活动下发">
		<div id="selector"></div>
		<input type="hidden" id="assingActGuid">
	</div>
	<span style="display:none" id="actReturnGuid" ></span>
	<script type="text/javascript">
	    
		$(document).ready(function() {
		    $('#grid').omGrid({
		        limit: 12,
		        singleSelect: false,
		        width: '100%',
		        height: 400,
		        showIndex: false,
		        dataSource: "${ctx}/ajax/griddata.action?type=active",
		        colModel: [{
		            header: '活动名称',
		            name: 'actName',
		            align: 'left',
		            width: 100,
		            renderer: function(colValue, rowData, rowIndex) {
		                return '<a href="javascript:;" onclick="editActive('+rowIndex+')">' + colValue + '</a>';
		            }
		        },
		        {
		            header: '活动摘要',
		            name: 'actDesc',
		            align: 'left',
		            width: 200,
		            sort: 'clientSide'
		        },
		        {
		            header: '是否支持报名',
		            name: 'actType',
		            align: 'left',
		            width: 100,
		            renderer: function(colValue, rowData, rowIndex) {
		                if (colValue == 1) {
		                    return '是&nbsp;&nbsp;<a href="javascript:;" onclick=goPage("'+rowData.actGuid+'")>设置表单</a>';
		                }
		                return '否';
		            }
		        },
		        {
		            header: '是否针对车主',
		            name: 'actType2',
		            align: 'left',
		            width: 80,
		            renderer: function(colValue, rowData, rowIndex) {
		                if (colValue == 1) {
		                    return '针对车主';
		                }
		                return '不针对车主';
		            }
		        },
		        {
		            header: '状态',
		            name: 'status',
		            align: 'left',
		            width: 80,
		            renderer: function(colValue, rowData, rowIndex) {
		                if (colValue == 1) {
		                    return '已发布';
		                }
		                return '未发布';
		            }
		        },
		        {
		            header: '开始时间',
		            name: 'startOn',
		            align: 'left',
		            width: 120
		        },
		        {
		            header: '结束时间',
		            name: 'endOn',
		            align: 'left',
		            width: 120
		        },
		        {
		            header: '来源',
		            name: 'source',
		            align: 'left',
		            width: 80,
		            renderer: function(colValue, rowData, rowIndex) {
		                if (colValue == 1) {
		                    return '集团下发';
		                }
		                return '自行添加';
		            }
		        },
		        {
		            header: '添加人',
		            name: 'createdBy',
		            align: 'left',
		            width: 80
		        },
		        {
		            header: '添加时间',
		            name: 'createdOn',
		            align: 'left',
		            width: 120
		        },
		        {
		            header: '统计',
		            name: 'actGuid',
		            align: 'left',
		            width: 'autoExpand',
		            renderer: function(colValue, rowData, rowIndex) {
		                return '<a href="${ctx}/admin/content/active/count-list.jsp?actGuid=' + rowData.actGuid + '">查看报名</a>';
		            }
		        }],
		        autoFit: true
		    });
	
		    $('#buttonbar').omButtonbar({
		        btns: [{
		            label: "添加",
		            id: "button-add",
		            icons: {
		                left: '${ctx}/scripts/image/add.png'
		            },
		            onClick: function() {
		                clearForm();
		                $("#dialog-add").omDialog("open");
		            }
		        },
		        {
		            label: "删除",
		            id: "button-remove",
		            icons: {
		                left: '${ctx}/scripts/image/remove.png'
		            },
		            onClick: function() {
		                var dels = $('#grid').omGrid('getSelections', true);
		                if (dels.length <= 0) {
		                    $.omMessageBox.alert({
		                        type: 'alert',
		                        title: '提示',
		                        content: '请选择要删除的活动！'
		                    });
		                    return;
		                }
		                $.omMessageBox.confirm({
		                    title: '确认删除',
		                    content: '删除后相关的报名信息也将被删除,您确认要删除吗？',
		                    onClose: function(v) {
		                        if (v) {
		                            var items = "";
		                            for (var i = 0; i < dels.length; i++) {
		                                items += "item=" + dels[i].actGuid;
		                                if (i < dels.length - 1) {
		                                    items += "&";
		                                }
		                            }
		                            $.getJSON("${ctx}/ajax/deleteActive?" + items,
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
		        },
		        {
		            label: "发布",
		            id: "button-publish",
		            icons: {
		                left: '${ctx}/scripts/image/open-close.png'
		            },
		            onClick: function() {
		                var dels = $('#grid').omGrid('getSelections', true);
		                if (dels.length <= 0) {
		                    $.omMessageBox.alert({
		                        type: 'alert',
		                        title: '提示',
		                        content: '请选择要发布的活动！'
		                    });
		                    return;
		                }
		                var items = "";
		                for (var i = 0; i < dels.length; i++) {
		                    items += "item=" + dels[i].actGuid;
		                    if (i < dels.length - 1) {
		                        items += "&";
		                    }
		                }
		                $.getJSON("${ctx}/ajax/publishActive?" + items,
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
		        },
		        {
		            label: "下发",
		            id: "button-publish",
		            icons: {
		                left: '${ctx}/scripts/image/open-close.png'
		            },
		            onClick: function() {
		                var datas = $('#grid').omGrid('getSelections', true);
		                if (datas.length != 1) {
		                    $.omMessageBox.alert({
		                        type: 'alert',
		                        title: '提示',
		                        content: '请选择要下布的活动（只能选择一个）！'
		                    });
		                    return;
		                }
		                $("#assingActGuid").val(datas[0].actGuid);
	
		                $("#dialog-assign").omDialog("open");
		                $.ajax({
		                    type: "POST",
		                    url: "${ctx}/ajax/combo?type=assignGzhHash",
		                    success: function(data) {
		                        console.log(data);
		                        $('#selector').omItemSelector({
		                            availableTitle: '请选择分配的目标公众号',
		                            selectedTitle: '已选择的公众号',
		                            dataSource: data,
		                            width: 550
		                        });
		                    }
		                });
		            }
		        }]
		    });
		    
		    $('#startOn').omCalendar({
	            editable: false
	        });
	        $('#endOn').omCalendar({
	            editable: false
	        });
	        
		    $("#dialog-add").omDialog({
		        autoOpen: false,
		        height: 500,
		        width: 600,
		        modal: true
		    });
	
		    $("#dialog-edit").omDialog({
		        autoOpen: false,
		        height: 500,
		        width: 600,
		        modal: true
		    });
	
		    $("#dialog-assign").omDialog({
		        autoOpen: false,
		        height: 450,
		        width: 600,
		        modal: true,
		        buttons: [{
		            text: "确定",
		            click: function() {
		                var actGuid = $("#assingActGuid").val();
		                var gzhs = $('#selector').omItemSelector("value");
		                var gzh = [];
		                for (var i = 0; i < gzhs.length; i++) {
		                    gzh.push({
		                        "gzhHash": gzhs[i]
		                    });
		                }
		                $.ajax({
		                    type: "POST",
		                    url: "${ctx}/ajax/assignActive",
		                    data: {
		                        gzhs: JSON.stringify(gzh),
		                        actGuid: actGuid
		                    },
		                    success: function(data) {
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
		                    }
		                });
		            }
		        },
		        {
		            text: "取消",
		            click: function() {
		                $("#dialog-assign").omDialog("close");
		            }
		        }]
		    });
	
		    $('#fileExtend').omFileUpload({
		        action: '${ctx}/common/uploadImage',
		        swf: '${ctx }/scripts/ui/om-fileupload.swf',
		        fileExt: '*.jpg;*.png;',
		        fileDesc: 'Image Files',
		        sizeLimit: 2000000,
		        //限制大小为2m
		        autoUpload: true,
		        onComplete: function(ID, fileObj, response, data, event) {
		            var res = JSON.parse(response);
		            $("#preview").attr("src", "${ctx}" + res.filepath);
		            $("#pic").val(res.filepath);
		        }
		    });
	
		    $("#addForm").validate({
		        rules: {
		            actName: {
		                required: true,
		                maxlength: 50
		            },
		            actDesc: {
		                required: true,
		                maxlength: 200
		            },
		            actType: {
		                required: true
		            },
		            startOn: {
		                required: true
		            },
		            endOn: {
		                required: true
		            },
		            actContent: {
		                required: true
		            }
		        },
		        messages: {
		            actName: {
		                required: "请输入活动名称",
		                maxlength: "最多50个字"
		            },
		            actDesc: {
		                required: "请输入活动摘要",
		                maxlength: "最多200个字"
		            },
		            actType: {
		                required: "请选择活动类型"
		            },
		            startOn: {
		                required: "请设置开始日期"
		            },
		            endOn: {
		                required: "请设置结束日期"
		            },
		            actContent: {
		                required: "请输入活动详情"
		            }
		        },
		        showErrors: function(errorMap, errorList) {
		            if (errorList && errorList.length > 0) { //如果存在错误信息
		                $.each(errorList,
		                function(index, obj) {
		                    if ($(obj.element).attr("class") == "input-combo") {
		                        $(obj.element).parent().parent().find("span.errorMsg").show();
		                        $(obj.element).parent().parent().find("span.errorMsg").html(obj.message);
		                    } else {
		                        $(obj.element).parent().find("span.errorMsg").show();
		                        $(obj.element).parent().find("span.errorMsg").html(obj.message);
		                    }
		                });
		            } else {
		                var obj = this.currentElements;
		                if ($(obj[0]).attr("class") == "input-combo") {
		                    $(obj[0]).parent().parent().find("span.errorMsg").hide();
		                    $(obj[0]).parent().parent().find("span.errorMsg").html("");
		                } else {
		                    $(obj[0]).parent().find("span.errorMsg").hide();
		                    $(obj[0]).parent().find("span.errorMsg").html("");
		                }
		            }
		        },
		        submitHandler: function() {
		            $('#addForm').omAjaxSubmit({
		                beforeSubmit: showRequest,
		                success: showResponse
		            });
		            return false;
		        }
		    });
	
		    $("#search-panel").omPanel({
		        title: "高级搜索",
		        collapsed: false,
		        collapsible: false
		    });
	
		    $("#search-status").omCombo({
		        width: 100,
		        value: '-',
		        editable: false,
		        dataSource: [{
		            text: '查询条件',
		            value: '-'
		        },
		        {
		            text: '条件一',
		            value: '1'
		        },
		        {
		            text: '条件二',
		            value: '2'
		        }]
		    });
	
		    $('span#button-search').omButton({
		        icons: {
		            left: '${ctx}/scripts/image/search.png'
		        },
		        width: 70
		    });
		});
	
		function editActive(rowIndex) {
			var data = $('#grid').omGrid('getData');
			var obj = data.rows[rowIndex];
			
	        clearForm("edit");
	        $("#dialog-add").omDialog("open");

	        $("input[name=actGuid]").val(obj.actGuid);
	        $("input[name=actName]").val(obj.actName);
	        $("textarea[name=actDesc]").text(obj.actDesc);
	        if (obj.actType == 1) {
	            $("input[name=actType][value=1]").attr("checked", "checked");
	        } else {
	            $("input[name=actType][value=0]").attr("checked", "checked");
	        }
	        if (obj.actType2 == 1) {
	            $("input[name=actType2][value=1]").attr("checked", "checked");
	        } else {
	            $("input[name=actType2][value=0]").attr("checked", "checked");
	        }
	        $("input[name=startOn]").val(obj.startOn);
	        $("input[name=endOn]").val(obj.endOn);
	        if (obj.actImage) {
	            $("#preview").attr('src','${ctx}' + obj.actImage);
	            $("input[name=actImage]").val(obj.actImage);
	        }
	        setContent(obj.actContent);
		}
	
		function CheckTabsExist(currentTabId) {
		    var total = parent.$('#center-tab').omTabs('getLength');
		    console.log(total);
		    for (var i = 0; i < total; i++) {
		        var tabId = parent.$('#center-tab').omTabs('getAlter', i);
		        if (tabId == currentTabId) {
		            return true;
		        }
		    }
		    return false;
		}
		
		function goPage(actGuid) {
	        var tabId = "add-setform-tab-id";
	        console.log(CheckTabsExist(tabId));
	        if (CheckTabsExist(tabId)) {
	            var index = parent.$("#center-tab").omTabs('getAlter', tabId);
	            parent.$('#center-tab').omTabs('close', index);
	        }
	        parent.$("#center-tab").omTabs('add', {
	            title: "报名表单设置",
	            content: "<iframe id='inner-frame' border=0 frameBorder='no' name='inner-frame' src='${ctx}/admin/content/active/form.jsp?actGuid=" + actGuid + "' width='100%' height='600'></iframe>",
	            closable: true,
	            tabId: tabId
	        });
	    }
	
		function clearForm(type) {
			//添加富文本编辑器
			if(window.editor){
				setContent("");
			}else {
				addEditor("actContent");
			}
	   		
			if("edit" == type){
				$("#dialog-add").attr("title","编辑品牌活动");
			}else {
				$("#dialog-add").attr("title","添加品牌活动")
			}
			$("#actDesc").html("");
			
		    $("#reset").click();
		    $("#preview").attr("src", "${ctx }/scripts/image/pic.png");
		}
		function showRequest(formData, jqForm, options) {
		    return true;
		}
		function showResponse(responseText, statusText, xhr, $form) {
		    console.log(responseText);
		    if (responseText.success) {
		        $("#actReturnGuid").text(responseText.result);
		        clearForm();
		        $.omMessageBox.alert({
		            type: 'success',
		            title: '成功',
		            content: responseText.message,
		            onClose: function(v) {
		                $("#dialog-add").omDialog("close");
		                $('#grid').omGrid('reload');
		                clearForm();
		                $.omMessageBox.alert({
	                        title: '提示信息',
	                        content: '如果活动需要报名，请在列表中设置报名表单，若不设置则使用默认设置。'
	                    });
		            }
		        });
		    } else {
		        $.omMessageBox.alert({
		            type: 'error',
		            title: '失败',
		            content: responseText.message
		        });
		    }
		}
	
	</script>
</body>

</html>
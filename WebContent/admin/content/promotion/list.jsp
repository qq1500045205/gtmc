<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>促销活动列表</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>

<link rel="stylesheet" type="text/css"  href="${ctx }/scripts/css/common.css" />
<!-- view_source_end -->
</head>
<body>

	<!-- view_source_begin -->
	<div id="search-panel">
		<div>
			<span class="label">关键词：</span> <input type="text" class="input-text" />
			<span id="button-search">搜索</span>
		</div>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin-bottom: 5px;"></div>
		<table id="grid"></table>
	</div>

	<div id="dialog" title="促销信息" >
		<form id="addPromotionForm" action="${ctx }/prom/add" method="post">
			<input type="hidden" name="promotionGuid"/>
			<div id="nav_cont">
				<table class="grid_layout" id="activity-table">
					<tr>
						<td width="80px">促销名称<span class="color_red">*</span>：</td>
						<td><input name="promotionTitle" class="input-text"/></td>
						<td width="30px"><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>促销摘要：</td>
						<td><textarea name="promotionSummary" class="area-text"></textarea></td>
						<td width="30px"><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>是否车型相关：</td>
						<td>
							<input type="radio" name="carType"  class="input-radio" value="0" checked="checked">车型无关&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="carType" class="input-radio" value="1" >车型相关<span class="errorMsg"></span>
						</td>
						<td width="30px"><span class="errorMsg"></span></td>
					</tr>
					<tr id="carTypeTr" style="display: none">
						<td>促销车型：</td>
						<td>
							<input name="carTypeGuid" id="promotionCarTypeGuid"  class="input-combo"/>
							<input type="hidden" name="carTypeName" id="promotionCarTypeName"/>
						</td>
						<td width="30px"><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>促销排序：</td>
						<td><input name="sort" id="order"  class="input-text" value="10"/></td>
						<td width="30px"><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>咨询电话：</td>
						<td><input name=mobile class="input-text"/></td>
						<td width="30px"><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>截至时间：</td>
						<td><input name="promotionDeadline" id="promotionDate"  class="input-combo"/></td>
						<td><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>促销图片：</td>
						<td>
							<img alt="" src="${ctx }/scripts/image/pic.png" width="100" height="100" id="picImg" />
							<input id="fileExtend" /> 
							<input name="promotionPicture" type="hidden" />
						</td>
						<td><span class="errorMsg"></span></td>
					</tr>
					<tr>
						<td>促销内容：</td>
						<td><textarea name="promotionContent" id="promotionContent"  class="area-text" style="width:100%;height:200px;"></textarea></td>
					</tr>
				</table>
				<div class="toolbar">
					<button id="submit" type="submit" class="button-css">提交</button>
					<button id="reset" type="reset" style="display:none;">重置</button>
				</div>
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
	var data = [];
	$(document).ready(function() {
	    $('#grid').omGrid({
	        limit: 10,
	        singleSelect: false,
	        width: '100%',
	        height: 400,
	        autoFit: true,
	        showIndex: false,
	        dataSource: "${ctx}/ajax/griddata.action?type=promotion",
	        colModel: [{
	            header: '促销名称',
	            name: 'promotionTitle',
	            width : 100,
	            align: 'left',
	            renderer: function(colValue, rowData, rowIndex) {
	            	data.push(rowData);
	                return '<a href="javascript:;" onclick=edit('+rowIndex+')>'+colValue+'</a>';
	            }
	        },
	        {
	            header: '促销状态',
	            name: 'status',
	            width : 80,
	            align: 'left',
	            renderer: function(colValue, rowData, rowIndex) {
	                if (colValue == 1) {
	                    return "已发布";
	                } else  {
	                    return "未发布";
	                }
	            }
	        },
	        {
	            header: '是否车型相关',
	            name: 'carType',
	            width : 80,
	            align: 'left',
				renderer : function(colValue, rowData, rowIndex) {
	                   if (colValue ==1) {
	                       return '车型相关';
	                   } 
	                   return '车型无关';
	               }
	        },
	        {
	            header: '排序',
	            name: 'sort', 
	            width : 50,
	            align: 'left'
	        },
	        {
	            header: '促销摘要',
	            name: 'promotionSummary',
	            width : 200,
	            align: 'left'
	        },
	        {
	            header: '截至时间',
	            name: 'promotionDeadline',
	            width : 150,
	            align: 'left'
	        },
	        {
	            header: '添加人',
	            name: 'createdBy',
	            width : 80,
	            align: 'left'
	        },
	        {
	            header: '添加时间',
	            name: 'createdOn',
	            width : 150,
	            align: 'left'
	        }]
	    });
	
	    $('#buttonbar').omButtonbar({
	        btns: [{
	            label: "新增",
	            id: "button-new",
	            icons: {
	                left: '${ctx}/scripts/image/add.png'
	            },
	            onClick: function() {
	            	$("#promotionGuid").val("");
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
	                            console.log(dels);
	                            for (var i = 0; i < dels.length; i++) {
	
	                                items += "items=" + dels[i].promotionGuid;
	                                if (i < dels.length - 1) {
	                                    items += "&";
	                                }
	                            }
	                            $.getJSON("${ctx}/yjajax/deleteCarPromotion?" + items,
	                            function(data) {
	                                if (data.success) {
	                                    $.omMessageBox.alert({
	                                        type: 'success',
	                                        title: '成功',
	                                        content: data.message,
	                                        onClose: function(v) {
	                                            $('#grid').omGrid('deleteRow', dels);
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
	        height: 500,
	        width: '50%',
	        modal: true
	    });
	
	    $("#search-panel").omPanel({
	        title: "搜索",
	        collapsed: false,
	        collapsible: false
	    });
	
	    $("input[name=carType]").click(function(){
	    	if($(this).val() == 1){
	    		$("#carTypeTr").show();
	    	}else{
	    		$("#carTypeTr").hide();
	    	}
	    });
	    
	    $("#promotionDate").omCalendar();
	
	    //添加富文本编辑器
		addEditor("promotionContent");
	  
	    $("#promotionCarTypeGuid").omCombo({
	        dataSource: '${ctx}/ajax/combo.action?type=carType',
	        valueField: 'value',
	        editable: false,
	        optionField: function(data, index) {
	            return data.text;
	        },
	        inputField: function(data, index) {
	            return data.text;
	        },
	        onValueChange: function(target, newValue, oldValue, event) {
	            $('#promotionCarTypeName').val(target.val());
	            console.log(target.val());
	        }
	    });
	    
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
	            var file = JSON.parse(response.trim());
	            $(this).parent().find("img").attr("src", "${ctx}" + file.filepath);
	            $(this).parent().find("input[name=promotionPicture").val(file.filepath);
	        }
	    });
	
	    $("#submit").omButton();
	    $("#reset").omButton();
	
	    var options = {
	        beforeSubmit: showRequest,
	        success: showResponse
	    };
	
	    $('#addPromotionForm').submit(function() {
	        $(this).omAjaxSubmit(options);
	        return false; //此处必须返回false，阻止常规的form提交。
	    });
	});
	
	function edit(rowIndex){
		$("#dialog").omDialog('open');
		
		var rowData = data[rowIndex];
		$("input[name=promotionGuid]").val(rowData.promotionGuid);
		$("input[name=promotionTitle]").val(rowData.promotionTitle);
		$("textarea[name=promotionSummary]").val(rowData.promotionSummary);
		if(rowData.carType == 0){
			$("input[name=carType][value=0]").attr("checked","checked");
		}else{
			$("input[name=carType][value=1]").attr("checked","checked");
		}
		$("input[name=carTypeGuid]").val(rowData.carTypeGuid);
		$("input[name=carTypeName]").val(rowData.carTypeName);
		$("input[name=sort]").val(rowData.sort);
		$("input[name=mobile]").val(rowData.mobile);
		$("input[name=promotionDeadline]").val(rowData.promotionDeadline);
		$("#picImg").val("${ctx}"+rowData.promotionPicture);
		$("input[name=promotionPicture]").val(rowData.promotionPicture);
		setContent(rowData.promotionContent); 
	}
	
	//提交前的处理
	function showRequest(formData, jqForm, options) {
		console.log(formData);
	    //var queryString = $.param(formData);
	    //console.log(queryString);
	    return true;
	}
	
	//提交返回的处理
	function showResponse(responseText, statusText, xhr, $form) {
	    console.log(responseText);
	    ///$.omMessageBox.waiting('close');
	    $("#dialog").omDialog("close");
	    var json = eval(responseText);
	    if (json.success) {
	        $.omMessageBox.alert({
	            type: 'success',
	            title: '成功',
	            content: '添加成功'
	        });
	        $('#grid').omGrid("reload");
	        $('#reset').click();
	        $("#picImg").attr("src", "${ctx }/scripts/image/pic.png");
	    } else {
	        $.omMessageBox.alert({
	            type: 'error',
	            title: '失败',
	            content: '添加失败'
	        });
	    }
	}
	
	function CheckTabsExist(currentTabId) {
	    console.log(currentTabId);
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
	
	function openURL(url) {
	    console.log(url);
	    var id = "car-promotion";
	    var title = "促销信息";
	    var tabId = "openurl-tab-id-" + id;
	    if (CheckTabsExist(tabId)) {
	        var index = parent.$("#center-tab").omTabs('getAlter', tabId);
	        parent.$('#center-tab').omTabs('close', index);
	    }
	
	    parent.$("#center-tab").omTabs('add', {
	        title: title,
	        content: "<iframe id='inner-frame' border=0 frameBorder='no'  name='inner-frame' src='" + url + "' width='100%' height='600'></iframe>",
	        closable: true,
	        tabId: tabId
	    });
	
	}
</script>
</body>
</html>
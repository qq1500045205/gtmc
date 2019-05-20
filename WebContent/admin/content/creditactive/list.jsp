<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<title>积分活动</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%> 
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/css/common.css" /> 
	<style type="text/css">
	.srch_line_new{

	display: inline;
	margin-left: 3px;
}</style>
</head>

<body>
	<div id="search-panel">
		<div>
			<form action="#" id="search-form" method="post">
				<div class="srch_line_new">
			活动名称 <input type="text" id="actName"  maxlength="50"></input>
				</div>
					<div class="srch_line_new">
				<span class="label">活动有效日</span> <input id="startOn"
				name="startOn" /> <span class="label">至</span> <input
				id="endOn" name="endOn" />
				</div>
					<div class="srch_line_new">		
					发布状态<input id="status" name="status" />		
				</div>
					<div class="srch_line_new">		
					来源<input id="source" name="source" />		
				</div>
					<div class="srch_line_new">
				<span class="label">积分</span> <input id="actCreditS"
				name="actCreditS" style="width: 50px"/> <span class="label">至</span> <input
				id="actCreditE" name="actCreditE" style="width: 50px"/>
				</div>
				
				<div class="srch_line_new">
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
	<div id="dialog-add"  title="新增积分活动" >
<!-- 	<iframe id="add-credit-frame" style="width:600px;height:98%;" scrolling="yes"  frameborder=”no” border=”0″  marginwidth=”0″  marginheight=”0″  allowtransparency=”yes”></iframe> -->
		
		<form id="addForm1" >
			<input type="hidden" name="memberCreditGuid" id ="memberCreditGuid" class="input-text">
			<input type="hidden" name="gzhHash" id ="gzhHash" class="input-text">
			<table>
				<tr>
					<td width="20%">活动名称 <span class="color_red"> * </span>
					</td>
					<td width="80%"><input type="text" name="actName1" id ="actName1" 
						class="input-text"> <span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td width="20%">积分设置 <span class="color_red"> * </span>
					</td>
					<td width="80%"><input type="text" name="actCredit"  id="actCredit"
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
					<td><input name="startOn1" id="startOn1" class="input-combo">
						<span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>结束时间 <span class="color_red"> * </span>
					</td>
					<td><input name="endOn1" id="endOn1" class="input-combo">
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
			</div>
		</form>
	</div>
<!-- 	<div id="dialog-edit"  title="编辑积分活动" > -->
<!-- 	<iframe id="edit-credit-frame" style="width:600px;height:98%;" scrolling="yes"  frameborder=”no” border=”0″  marginwidth=”0″  marginheight=”0″  allowtransparency=”yes”></iframe> -->
		
<!-- 	</div> -->
	<div id="dialog-assign" title="活动下发">
		<div id="selector"></div>
		<input type="hidden" id="assingActGuid">
	</div>
	<div id="dialog-showQRCode" title="二维码：右键另存为可下载原图">
	<img id="qrImg"  style="width: 300px;height: 300px">
	</div>
	<span style="display:none" id="actReturnGuid" ></span>
	<script type="text/javascript">
	var gzhHash='${loginObject.gzhHash}';

    var gzhData=null;

	//add by luowenwu 2014/06/20 start 
	if(gzhHash==''){
		gzhHash='-2042484612';
	}
	$("input[name=gzhHash]").val(gzhHash);
	var type=null;
	var createdBy=null;
	var createdOn=null;
	$.validator.addMethod("isInteger", function(value) {
		var regu = /^[-]{0,1}[0-9]{1,}$/;
        return regu.test(value) ;

    }, '必须输入整数!');

	//add by luowenwu 2014/06/20 end 

	var showQRCode = function(ticket) {
		
//         $.ajax({
//             type: "POST",
//             url: "${ctx}/creditactivity/createQRCodeCreditActiveInfo?memberCreditGuid="+memberCreditGuid+"&gzhHash="+gzhHash,
//             success: function(data) {
//             	$("#dialog-showQRCode").omDialog("open");
//            	$("#qrImg").attr("src", "${ctx }/uploads/Michael_QRCode.png");


//             }
//         });	
             	$("#dialog-showQRCode").omDialog("open");
        		var picSrc = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" +ticket;

               	$("#qrImg").attr("src", picSrc);
	};	    
		$(document).ready(function() {
			var param = '?';
			param += 'actCreditS='+$("#actCreditS").val();
			param += '&actCreditE='+$("#actCreditE").val();
			param += '&startOn='+$("#startOn").val();
			param += '&endOn='+$("#endOn").val();
			param += '&status='+$("#status").val();
			param+='&gzhHash='+gzhHash+'';
			param+='&source='+$("#source").val();
			param += '&actName='+encodeURI(encodeURI($("#actName").val()));
		    $('#grid').omGrid({
		        limit: 12,
		        singleSelect: false,
		        width: '100%',
		        height: 400,
		        showIndex: false,
		      dataSource: "${ctx}/creditactivity/getAllListCreditActiveInfo.action"+param,
		        colModel: [{
		            header: '活动名称',
		            name: 'actName',
		            align: 'center',
		            width: 100,
		            renderer: function(colValue, rowData, rowIndex) {
	                    return '<a href="javascript:;" onclick=editActive("'+rowData.memberCreditGuid+'")>' + colValue + '</a>';

		            }
		        },
		        {
		            header: '活动摘要',
		            name: 'actDesc',
		            align: 'center',
		            width: 150,
		            sort: 'clientSide'
		        },
		        {
		            header: '积分',
		            name: 'actCredit',
		            align: 'center',
		            width: 70
		        },
		        {
		            header: '查看二维码',
		            name: 'memberCreditGuid',
		            align: 'center',
		            width: 100,
		            renderer: function(colValue, rowData, rowIndex) {       
		                    return '<a href="javascript:;" onclick=showQRCode("'+rowData.ticket+'")>查看</a>';
		            }
		        },
		        {
		            header: '是否支持报名',
		            name: 'actType',
		            align: 'center',
		            width: 100,
		            renderer: function(colValue, rowData, rowIndex) {
		                if (colValue == 1) {
		                    return '是&nbsp;&nbsp;<a href="javascript:;" onclick=goPage("'+rowData.memberCreditGuid+'")>设置表单</a>';
		                }
		                return '否';
		            }
		        },
		        {
		            header: '是否针对车主',
		            name: 'actType2',
		            align: 'center',
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
		            align: 'center',
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
		            align: 'center',
		            width: 120
		        },
		        {
		            header: '结束时间',
		            name: 'endOn',
		            align: 'center',
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
		            align: 'center',
		            width: 80
		        },
		        {
		            header: '添加时间',
		            name: 'createdOn',
		            align: 'center',
		            width: 120
		        },
		        {
		            header: '经销店代码',
		            name: 'dealerCode',
		            align: 'center',
		            width: 120
		        },
		        {
		            header: '统计',
		            name: 'memberCreditGuid',
		            align: 'center',
		            width: 'autoExpand',
		            renderer: function(colValue, rowData, rowIndex) {
		            	if(rowData.actType=='1'){
		                return '<a href="${ctx}/admin/content/creditactive/credit-enroll-count-list.jsp?memberCreditGuid=' + rowData.memberCreditGuid + '">查看报名</a>';
		            	}else{
		            		return ' ';
		            	}
		            }
		        }],
		        autoFit: true
		    });
			if(gzhHash=='-2042484612' || gzhHash==''){
		    $('#buttonbar').omButtonbar({
		        btns: [{
		            label: "添加",
		            id: "button-add",
		            icons: {
		                left: '${ctx}/scripts/image/add.png'
		            },
		            onClick: function() {
		            	//modify by luowenwu 2014/06/20 start
		                clearForm();
		                $("#dialog-add").omDialog("open");
		      //          $("#dialog-add").omDialog("open");
// 		            	$("#add-credit-frame").attr("src", "${ctx}/admin/content/creditactive/add.jsp");
		            	//modify by luowenwu 2014/06/20 end


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
		                        content: '请选择要删除的积分活动！'
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
		                            	if(dels[i].status==1){
		                            		alert('删除的积分活动中存在已经发布的积分活动，删除失败');
		                            		return;
		                            	}
		                                items += "item=" + dels[i].memberCreditGuid;
		                                if (i < dels.length - 1) {
		                                    items += "&";
		                                }
		                            }
		                            $.getJSON("${ctx}/creditactivity/deleteCreditActiveInfo.action?" + items,
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
		                    items += "item=" + dels[i].memberCreditGuid;
		                    if (i < dels.length - 1) {
		                        items += "&";
		                    }
		                }
		                $.getJSON("${ctx}/creditactivity/publishCreditActiveInfo.action?" + items,
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
		                        content: '请选择要下发的积分活动（只能选择一个）！'
		                    });
		                    return;
		                }
		                $("#assingActGuid").val(datas[0].memberCreditGuid);
		                $("#dialog-assign").omDialog("open");      
		                if(gzhData==null){
		                $.ajax({
		                    type: "POST",
		                    url: "${ctx}/ajax/combo?type=assignGzhHash",
		        			async : false,
		                    success: function(data) {
		                        console.log(data);
		                    	gzhData=data;
		                        $('#selector').omItemSelector({
		                            availableTitle: '请选择分配的目标公众号',
		                            selectedTitle: '已选择的公众号',
		                            dataSource: data,
		                            width: 550
		                        });
		                    }
		                });
		            }
		        
		            }
		        }]
		    });
			}else{
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
			                        content: '请选择要删除的积分活动！'
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
			                            	if(dels[i].status==1){
			                            		alert('删除的积分活动中存在已经发布的积分活动，删除失败');
			                            		return;
			                            	}
			                                items += "item=" + dels[i].memberCreditGuid;
			                                if (i < dels.length - 1) {
			                                    items += "&";
			                                }
			                            }
			                            $.getJSON("${ctx}/creditactivity/deleteCreditActiveInfo.action?" + items,
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
			                    items += "item=" + dels[i].memberCreditGuid;
			                    if (i < dels.length - 1) {
			                        items += "&";
			                    }
			                }
			                $.getJSON("${ctx}/creditactivity/publishCreditActiveInfo.action?" + items,
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
			        }]
			    });
		
			}
		    
		    $('#startOn').omCalendar({
	            editable: false
	        });
	        $('#endOn').omCalendar({
	            editable: false
	        });
		    $('#startOn1').omCalendar({
	            editable: false
	        });
	        $('#endOn1').omCalendar({
	            editable: false
	        });
	        
		    $("#dialog-add").omDialog({
		        autoOpen: false,
		        height: 700,
		        width: 700,
		        modal: true
		    });
	//add by lowenwu 2014/06/20 start
	
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

		    $("#addForm1").validate({
		        rules: {
		            actName1: {
		                required: true,
		                maxlength: 50
		            },
		            actCredit: {
		                required: true,
		                isInteger :true,
		                maxlength: 6
		            },
		            actDesc: {
		                required: true,
		                maxlength: 100
		            },
		            actType: {
		                required: true
		            },
		            startOn1: {
		                required: true
		            },
		            endOn1: {
		                required: true
		            },
		            actContent: {
		                required: true
		            }
		        },
		        messages: {
		            actName1: {
		                required: "请输入活动名称",
		                maxlength: "最多50个字"
		            },
		            actCredit: {
		                required: "请输入积分设置",
		                maxlength: "最多为六位"
		            },
		            actDesc: {
		                required: "请输入活动摘要",
		                maxlength: "最多100个字"
		            },
		            actType: {
		                required: "请选择活动类型"
		            },
		            startOn1: {
		                required: "请设置开始日期"
		            },
		            endOn1: {
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
		            $('#addForm1').omAjaxSubmit();
		            return false;
		        }
		    });		
		    //add by luowenwu 2014/0620 end
// 		    $("#dialog-edit").omDialog({
// 		        autoOpen: false,
// 		        height: 700,
// 		        width: 700,
// 		        modal: true
// 		    });
		    $("#dialog-showQRCode").omDialog({
		        autoOpen: false,
		        height: 400,
		        width: 400,
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
		            	
		                var memberCreditGuid = $("#assingActGuid").val();
		                var gzhs = $('#selector').omItemSelector("value");
		                var gzh = [];
		                for (var i = 0; i < gzhs.length; i++) {
		                    gzh.push({
		                        "gzhHash": gzhs[i]
		                    });
		                }
		                $.ajax({
		                    type: "POST",
		                    url: "${ctx}/creditactivity/assignCreditActiveInfo.action",
		                    data: {
		                        gzhs: JSON.stringify(gzh),
		                        memberCreditGuid: memberCreditGuid
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
// 		                $('#selector').omItemSelector({        
//                             dataSource: null                  
//                         });
		            }
		        }]
		    });
		    $("#search-panel").omPanel({
		        title: "高级搜索",
		        collapsed: false,
		        collapsible: false
		    });
	
			$("#status").omCombo({
				width : 70,
				value : '',
				editable : false,
				dataSource : [{
					text : '',
					value : ''
				},  {
					text : '已发布',
					value : 1
				}, {
					text : '未发布',
					value : 0
				} ]
			});
			$("#source").omCombo({
				width : 90,
				value : '',
				editable : false,
				dataSource : [{
					text : '',
					value : ''
				},  {
					text : '集团下发',
					value : 1
				}, {
					text : '自行添加',
					value : 0
				} ]
			});
		
		});
		$('span#button-search').omButton({
			icons : {
				left : '${ctx}/scripts/image/search.png'
			},
			width : 70,
			onClick : function() {	
						if (( $.trim($("#startOn").val())=='')!=( $.trim($("#endOn").val())=='')) {					
							alert("活动有效日必须同时输入.");
						}
						else	if (( $.trim($("#actCreditS").val())=='')!=( $.trim($("#actCreditE").val())=='')) {					
							alert("积分必须同时输入.");
						}
				else{					
					var param = '?';
					param += 'actCreditS='+$("#actCreditS").val();
					param += '&actCreditE='+$("#actCreditE").val();
					param += '&startOn='+$("#startOn").val();
					param += '&endOn='+$("#endOn").val();
					param += '&status='+$("#status").val();
					param+='&gzhHash='+gzhHash+'';
					param+='&source='+$("#source").val();
					param += '&actName='+encodeURI(encodeURI($("#actName").val()));

					$('#grid').omGrid({
						dataSource : "${ctx}/creditactivity/getAllListCreditActiveInfo.action"+param,				
					});
				}
			}
		});     
	
//modify by luowenwu 2014/06/20 start
		function editActive(memberCreditGuid) {
			 type="edit";
	        clearForm("edit");
			var CreditGuid = memberCreditGuid;
			$.ajax({
				type : 'POST',
				url : "${ctx }/creditactivity/creditActive.action",
				async : false,
				data : {
					type : "getData",
					memberCreditGuid : CreditGuid	
				},
				dataType : "json",
				success : function(json) {
					var obj =json.result;
			 createdBy=obj.createdBy;
			 createdOn=obj.createdOn;
	        $("#dialog-add").omDialog("open");
	        $("input[name=memberCreditGuid]").val(obj.memberCreditGuid);
	        $("input[name=actName1]").val(obj.actName);
	        $("input[name=actCredit]").val(obj.actCredit);
	        if (obj.status==1){
	        $("input[name=actCredit]").attr("disabled","disabled");
			}
	        $("textarea[name=actDesc]").val(obj.actDesc);
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
	        $("input[name=startOn1]").val(obj.startOn);
	        $("input[name=endOn1]").val(obj.endOn);
	        if (obj.actImage) {
				var temp = "${ctx}" + obj.actImage;
	           $("#preview").attr('src', temp);
	           $("#pic").val(obj.actImage);
	        }
	        setContent(obj.actContent);
		}
			});		
	        
		}
		//modify by luowenwu 2014/06/20 end
		
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
		
		function goPage(memberCreditGuid) {
	        var tabId = "add-setform-tab-id";
	        console.log(CheckTabsExist(tabId));
	        if (CheckTabsExist(tabId)) {
	            var index = parent.$("#center-tab").omTabs('getAlter', tabId);
	            parent.$('#center-tab').omTabs('close', index);
	        }
	        parent.$("#center-tab").omTabs('add', {
	            title: "报名表单设置",
	            content: "<iframe id='inner-frame' border=0 frameBorder='no' name='inner-frame' src='${ctx}/admin/content/creditactive/credit-enroll.jsp?memberCreditGuid=" + memberCreditGuid + "' width='100%' height='600'></iframe>",
	            closable: true,
	            tabId: tabId
	        });
	    }
		
		//add by luowenwu 2014/06/20 start
		function clearForm(type) {
			//添加富文本编辑器
			if(window.editor){
				setContent("");
			}else {
				addEditor("actContent");
			}
	   		
            $("#dialog-add").parent().find("span.errorMsg").hide();
            $("#dialog-add").parent().find("span.errorMsg").html("");
			
			if("edit" == type){
				$("#dialog-add").omDialog({ title:'编辑积分活动'}); //attr("title","编辑品牌活动");
			}else {
				$("#dialog-add").omDialog({ title:'添加积分活动'});//$("#dialog-add").attr("title","添加品牌活动")
			}
			//$("#actDesc").html("");
			$("textarea[name=actDesc]").val("");
	        $("input[name=actName1]").val("");
	        $("input[name=actCredit]").val("");
            $("input[name=actType][value=0]").attr("checked", "checked");
            $("input[name=actType2][value=0]").attr("checked", "checked");
	        $("input[name=startOn1]").val("");
	        $("input[name=endOn1]").val("");
	        $("input[name=actCredit]").attr("disabled",false);
			
		    $("#preview").attr("src", "${ctx }/scripts/image/pic.png");
		}
		
		
		$('#sub').bind('click', function(){
			var memberCreditGuid= $("input[name=memberCreditGuid]").val();
			var actType=$('input:radio[name="actType"]:checked').val();
			var actType2=$('input:radio[name="actType2"]:checked').val();
			var actName=$("#actName1").val();
			var actCredit=$("#actCredit").val();
			var actDesc=$("#actDesc").val();
			var startOn=$("#startOn1").val();
			var endOn=$("#endOn1").val();
			var actImage=$("#pic").val();
			var actContent=$("#actContent").val();
        		
			if (actName==''){
				return;
			}
			if (actCredit==''){
				return;				
			}
			if (actDesc==''){
				return;				
			}
			if (startOn==''){
				return;				
			}
			if (endOn==''){
				return;				
			}
			if (actContent==''){
				return;				
			}
			clearForm();
			if(type!='edit'){
			$.ajax({
				type : 'POST',
				url : "${ctx }/creditactivity/creditActive.action",
				async : false,
				data : {
					type : "activeSave",
					actName : actName,
					actCredit : actCredit,
					actDesc : actDesc,
					actType : actType,
					actType2 : actType2,
					startOn : startOn,
					endOn : endOn,
					actImage : actImage,
					gzhHash : gzhHash,
					actContent : actContent					
				},
				dataType : "json",
				success : function(json) {
			        $.omMessageBox.alert({
			            type: 'success',
			            title: '成功',
			            content: '数据添加成功',
				           onClose: function(v) {
				               $("#dialog-add").omDialog("close");
				               $('#grid').omGrid('reload');
				           }
			        });
		            //alert(json.message);
				}
			});
	}else{
		$.ajax({
			type : 'POST',
			url : "${ctx }/creditactivity/creditActive.action",
			async : false,
			data : {
				type : "activeUpdata",
				memberCreditGuid : memberCreditGuid,
				actName : actName,
				actCredit : actCredit,
				actDesc : actDesc,
				actType : actType,
				actType2 : actType2,
				startOn : startOn,
				endOn : endOn,
				actImage : actImage,
				gzhHash : gzhHash,
				createdBy:createdBy,
				createdOn : createdOn,
				actContent : actContent					
			},
			dataType : "json",
			success : function(json) {
		        $.omMessageBox.alert({
		            type: 'success',
		            title: '成功',
		            content: '数据更新成功',
		           onClose: function(v) {
		               $("#dialog-add").omDialog("close");
		               $('#grid').omGrid('reload');
		           }
		        });
			}
		});
	}
		});
		
		//add by luowenwu 2014/06/20 end

	</script>
</body>

</html>
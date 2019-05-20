<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>二维码管理</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>

<link rel="stylesheet" type="text/css"  href="${ctx }/scripts/css/common.css" />
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
					<span id="button-search">搜索</span>
				</div>

			</form>
		</div>
	</div>

	<div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin: 5px 0 5px 0;"></div>
		<div style="width: 100%;">
			<table id="grid"></table>
		</div>
	</div>
	<div id="dialog-add" title="新增二维码" >
		<form id="addQrCodeForm" action="${ctx }/qrcode/addQrCode" method="post">
			<div id="nav_cont">
				<table class="grid_layout" id="activity-table">
					<tr>
						<td width="120px">二维码应用场景<span class="color_red">*</span>：</td>
						<td><input name="sourceFrom" class="input-text"/></td>
						<td width="30px"><span class="errorMsg"></span></td>
					</tr>
				</table>
				<div class="toolbar">
					<button id="submit" type="submit" class="btn-def">确定</button>
				</div>
			</div>
		</form>
	</div>
	<div id="qrCode-view" title="查看二维码" >
		<img alt="" src="" id="qrcode" style="width: 371px;height: 371px;">
	</div>
	<script type="text/javascript">
	
	var datas = [];
	$(document).ready(function() {
		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height: 400,
			showIndex : true,
			dataSource : "${ctx}/qrcode/list",
			colModel : [ {
				header : '二维码编号',
				name : 'id',
				align : 'left',
				width : 100
			}, {
				header : '二维码使用场景',
				name : 'sourceFrom',
				align : 'left',
				width : 300,          	 	
			}, {
				header : '二维码图片',
				name : 'ticket',
				align : 'left',
				width : 'autoExpand',
				renderer:function(colValue, rowData, rowIndex){
               		return '<a href="javascript:void" onclick="javascript:showQrCode('+rowIndex+');return false;">查看二维码</a>';
         		}
			}],
			autoFit : true,
			onSuccess : function(data){
				datas = data.rows;
			}
		});

		$('#buttonbar').omButtonbar({
			btns : [ 
			 {
				label : "新增",
				id : "button-add",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					$("#dialog-add").omDialog("open");
				}
			},
			{
				label : "删除",
				id : "button-delete",
				icons : {
					left : '${ctx}/scripts/image/remove.png'
				},
				onClick : function() {
					var dels = $('#grid').omGrid('getSelections', true);
	                if (dels.length <= 0) {
	                    $.omMessageBox.alert({
	                        type: 'alert',
	                        title: '提示',
	                        content: '请选择要删除的题目！'
	                    });
	                    return;
	                }
	                $.omMessageBox.confirm({
	                    title: '确认删除',
	                    content: '删除后相关的答题信息也将被删除,您确认要删除吗？',
	                    onClose: function(v) {
	                        if (v) {
	                            var items = "";
	                            for (var i = 0; i < dels.length; i++) {
	                                items += "items=" + dels[i].id;
	                                if (i < dels.length - 1) {
	                                    items += "&";
	                                }
	                            }
	                            $.getJSON("${ctx}/qrcode/deleteQrCode?" + items,
	                            function(data) {
	                                console.log(data);
	                                $.omMessageBox.alert({
	        	                        type: 'alert',
	        	                        title: '提示',
	        	                        content: data.message
	        	                    });
	                                $('#grid').omGrid("reload");
	                            });
	                        }
	                    }
	                });
				}
			}]
		});
		
		$("#dialog-add").omDialog({
	        autoOpen: false,
	        height: 500,
	        width: 600,
	        modal: true,
	        onClose : function(event) {
	        	
	        }
	    });
		
		$("#qrCode-view").omDialog({
	        autoOpen: false,
	        height: 450,
	        width: 400,
	        modal: true,
	        onClose : function(event) {
	        	
	        }
	    });
	    
	    var options = {
                beforeSubmit : showRequest, 
                success : showResponse
        };
	    
	    $('#addQrCodeForm').submit(function() {
            $(this).omAjaxSubmit(options);
            return false; //此处必须返回false，阻止常规的form提交。
        });
	    
        function showRequest(formData, jqForm, options) {
            return true;
        }
        
        function showResponse(responseText, statusText, xhr, $form) {
            $("#dialog-add").omDialog("close");
            console.log(responseText);
            var json = responseText;
            if(json.success){
    	       	 $.omMessageBox.alert({
                    type:'success',
                    title:'成功',
                    content:'添加成功'
                 });
    	       	 $('#grid').omGrid("reload");
            } else {
           	 	$.omMessageBox.alert({
                    type:'error',
                    title:'失败',
                    content: json.message
                });
            }
        }
	    
		$("#search-panel").omPanel({
			title : "高级搜索",
			collapsed : false,
			collapsible : false
		});

		$("#search-status").omCombo({
			width : 100,
			value : '-',
			editable : false,
			dataSource : [ {
				text : '查询条件',
				value : '-'
			}, {
				text : '条件一',
				value : '1'
			}, {
				text : '条件二',
				value : '2'
			} ]
		});
		
		$('span#button-search').omButton({
			icons : {
				left : '${ctx}/scripts/image/search.png'
			},
			width : 70,
			onClick : function(event) {

			}
		});
	});

	// 显示大二维码
	function showQrCode(index) {
		var picSrc = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + datas[index].ticket;
		$("#qrcode").attr("src", picSrc);
		$("#qrCode-view").omDialog("open");
	};
</script>
</body>
</html>
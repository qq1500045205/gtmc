<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<title>商品管理</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/css/common.css" /> 
</head>

<body>
	<div id="search-panel">
		<div>
			<form action="${ctx}/creditactivity/search.action" id="search-form" method="post">
				<div class="srch_line">
					<ul>
						<li><input id="search-condition" name="condition" /></li>
						<li><input id="search-word" name="search" class="input-text" /></li>
						<li>&nbsp;&nbsp;商品类型：<input id="search-type" name="typeid" /></li>
						<li>&nbsp;&nbsp;发布状态：<input id="search-status" name="status" /></li>
						<li>&nbsp;&nbsp;积分：<input id="search-min-credit" name="minCredit" class="input-text" /></li>
						<li>至 <input id="search-max-credit" name="maxCredit" class="input-text" /></li>
					</ul>
				</div>
				<div class="srch_line" onclick="">
					<span id="button-search" onclick="search()"> 搜索 </span>
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
	<div id="dialog-add" title="添加商品">
		<form action="${ctx }/creditactivity/addProduct" id="addForm" method="post" onsubmit="AddFlag()">
			<input type="hidden" name="productGuid" class="input-text" />
			<table>
				<tr>
					<td width="20%">商品名称 <span class="color_red"> * </span>
					</td>
					<td width="80%"><input type="text" name="productName"
						class="input-text"> <span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>兑换编码 <span class="color_red"> * </span>
					</td>
					<td><input type="text" name="productKey" class="input-text" onblur="CheckKey()" />
					<span id="repeatKey" class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>市场价<span class="color_red"> * </span>
					</td>
					<td><input type="text" name="productPrice" class="input-text" /><span
						class="errorMsg"> </span><br /></td>
				</tr>
				<tr>
					<td>添加日期 <span class="color_red"> * </span>
					</td>
					<td><input name="productDate" id="productDate" class="input-combo">
						<span class="errorMsg"> </span></td>
				</tr>
				<tr>
					<td>商品logo</td>
					<td><img id="preview" src="${ctx }/scripts/image/pic.png"
						style="width: 100px; height: 100px; border: 1px solid #ddd;">
						<input id="fileExtend" style="display: inline;"> <input
						type="hidden" name="actImage" id="pic" style="display: inline">
					</td>
				</tr>
				<tr>
					<td>商品说明 </td>
					<td><textarea name="productDoc" id="productDoc"
							class="area-text" style="width: 100%; height: 200px;">
                                    </textarea> <span class="errorMsg"> </span></td>
				</tr>
				
				<tr>
					<td>商品规格 </td>
					<td><textarea name="productSpec" id="productSpec"
							class="area-text" style="width: 100%; height: 200px;">
                                    </textarea> <span class="errorMsg"> </span></td>
				</tr>
				
				<tr>
					<td>兑换积分 <span class="color_red"> * </span>
					</td>
					<td><input type="text" name="productCredit" class="input-text" /><span class="errorMsg"> </span></td>
				</tr>
				
				<tr>
					<td>商品分类 <span class="color_red"> * </span>
					</td>
					<td><input name="productTypeId" id="productTypeId" class="input-combo" /><span class="errorMsg"> </span></td>
				</tr>
				
				<tr>
					<td>库存数 <span class="color_red"> * </span>
					</td>
					<td><input type="text" name="productStock" class="input-text" /><span class="errorMsg"> </span></td>
				</tr>
			</table>
			<div>
				<input value="保存" id="sub" class="btn-def" type="submit">
				<input type="reset" id="reset" style="display:none">
			</div>
		</form>
	</div>
	<script type="text/javascript">
		var isRepeat = false;
		var editorDoc;
		var editorSpec;
		KindEditor.ready(function(K){
			editorDoc = K.create('textarea[name="productDoc"]',{
				allowFileManager: true
			});
			editorSpec = K.create('textarea[name="productSpec"]',{
				allowFileManager: true
			});
		});
		
		$(document).ready(function() {
		    $('#grid').omGrid({
		        limit: 12,
		        singleSelect: false,
		        width: '100%',
		        dataSource: '${ctx}/creditactivity/getAllProduct.action',
		        height: 400,
		        showIndex: false,
		        colModel: [{
		            header: '商品名称',
		            name: 'productName',
		            align: 'left',
		            width: 100,
		            renderer: function(colValue, rowData, rowIndex){
		            	return '<a href="javascript:;" onclick="editProduct('+rowIndex+')">' + colValue + '</a>';
		            }
		        },
		        {
		        	header: '商品分类',
		        	name: 'productTypeName',
		        	align: 'left',
		        	width: 100
		        },
		        {
		            header: '兑换编号',
		            name: 'productKey',
		            align: 'left',
		            width: 200
		        },
		        {
		            header: '市场参考价',
		            name: 'productPrice',
		            align: 'left',
		            width: 100
		        },
		        {
		            header: '创建日期',
		            name: 'productDate',
		            align: 'left',
		            width: 80
		        },
		    	{
		        	header: '状态',
		            name: 'status',
		            align: 'left',
		            width: 80,
		            renderer: function(colValue, rowData, rowIndex) {
		            	if(rowData.status==0)
	            		{
	            			return "未发布";
	            		}
		            	else
	            		{
	            			return "已发布";
	            		}
		            }
		    	},
		        {
		        	header: '发布日期',
		        	name: 'publishOn',
		        	align: 'left',
		        	width: 80
		        },
		        {
		        	header: '兑换积分',
		        	name: 'productCredit',
		        	align: 'left',
		        	width: 80
		        },
		        {
		        	header: '库存数',
		        	name: 'productStock',
		        	align: 'left',
		        	width: 80
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
		                    content: '您确认要删除该商品吗？',
		                    onClose: function(v) {
		                        if (v) {
		                            var items = "";
		                            for (var i = 0; i < dels.length; i++) {
		                                items += "item=" + dels[i].productGuid;
		                                if (i < dels.length - 1) {
		                                    items += "&";
		                                }
		                            }
		                            $.getJSON("${ctx}/creditactivity/deleteProduct?" + items,
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
		    
		    $('#productDate').omCalendar({
	            editable: false
	        });
		    
		    $("#dialog-add").omDialog({
		        autoOpen: false,
		        width: 650,
		        height: 500,
		        modal: true
		    });
		    $("#productTypeId").omCombo({
		        dataSource: '${ctx}/creditactivity/listProductType',
		        width: 140,
		        valueField: 'value',
		        editable: false,
		        optionField: function(data, index) {
		            return data.text;
		        },
		        inputField: function(data, index) {
		            return data.text;
		        }
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
		            productName: {
		                required: true
		            },
		            productKey: {
		                required: true,
		            },
		            productPrice: {
		                required: true
		            },
		            productDate: {
		                required: true
		            },
		            productCredit: {
		                required: true
		            },
		            productTypeId: {
		            	required: true
		            },
		            productStock: {
		                required: true
		            }
		        },
		        messages: {
		        	productName: {
		                required: "请输入商品名称"
		            },
		            productKey: {
		                required: "请输入商品编码",
		            },
		            productPrice: {
		                required: "请输入商品价格"
		            },
		            productDate: {
		                required: "请输入商品日期"
		            },
		            productCredit: {
		                required: "请输入兑换积分"
		            },
		            productTypeId: {
		            	required: "请选择类型"
		            },
		            productStock: {
		                required: "请输入库存数"
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
		            	
		            	/*
            			$.each(errorList,
		                function(index, obj) {
		            		//$("#error").html(obj.message);
		            		$(obj.element).parent().find("span.errorMsg").show();
		            		$(obj.element).parent().find("span.errorMsg").html(obj.message);
		                });
		            	*/
		            	
		            } else {
		                var obj = this.currentElements;
		                if ($(obj[0]).attr("class") == "input-combo") {
		                    $(obj[0]).parent().parent().find("span.errorMsg").hide();
		                    $(obj[0]).parent().parent().find("span.errorMsg").html("");
		                } else {
		                    $(obj[0]).parent().find("span.errorMsg").hide();
		                    $(obj[0]).parent().find("span.errorMsg").html("");
		                }
		            	/*
		                $(obj.element).parent().find("span.errorMsg").hide();
		            	$(obj.element).parent().find("span.errorMsg").html("");
		                */
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
	
		    $("#search-condition").omCombo({
		        width: 100,
		        value: '0',
		        editable: false,
		        dataSource: [{
		            text: '查询条件',
		            value: '0'
		        },
		        {
		        	text: '商品名称',
		        	value: '1'
		        },
		        {
		            text: '兑换编号',
		            value: '2'
		        }]
		    });
		    
		    $("#search-type").omCombo({
		    	dataSource: '${ctx}/creditactivity/listProductType',
		        width: 140,
		        valueField: 'value',
		        editable: false,
		        optionField: function(data, index) {
		            return data.text;
		        },
		        inputField: function(data, index) {
		            return data.text;
		        }
		    });
		    
		    $("#search-status").omCombo({
		        width: 100,
		        editable: false,
		        value: '2',
		        dataSource: [{
		        	text: '所有',
		        	value: '2'
		            
		        },
		        {
		        	text: '已发布',
		        	value: '1'
		        },
		        {
		        	text: '未发布',
		            value: '0'
		        }]
		    });
	
		    $('span#button-search').omButton({
		        icons: {
		            left: '${ctx}/scripts/image/search.png'
		        },
		        width: 70
		    });
		});
	
		function editProduct(rowIndex) {
			var data = $('#grid').omGrid('getData');
			var obj = data.rows[rowIndex];
			
	        clearForm("edit");
			isRepeat = true;
	        $("#dialog-add").omDialog("open");

	        $("input[name=productGuid]").val(obj.productGuid);
	        $("input[name=productName]").val(obj.productName);
	        $("input[name=productPrice]").val(obj.productPrice);
	        $("input[name=productKey]").val(obj.productKey);
	        $("input[name=productDate]").val(obj.productDate);
	        $("input[name=productCredit]").val(obj.productCredit);
	        $("input[name=productStock]").val(obj.productStock);
	        $("#productTypeId").omCombo('value',obj.productTypeId);
	        editorDoc.html(obj.productDoc);
	        editorSpec.html(obj.productSpec);
	        
	        if(obj.productLogo)
	        {
	        	$("#preview").attr('src','$(ctx)'+obj.productLogo);
	        	$("#pic").val(obj.productLogo);
	        }
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
		
		function AddFlag()
		{
			$("#productDoc").attr("value",editorDoc.html());
			$("#productSpec").attr("value",editorSpec.html());
		}
	
		function clearForm(type) {
			isRepeat = false;
			$("#dialog-add").attr("title","编辑商品");
			//if("edit" == type){
			//	$("#dialog-add").attr("title","编辑商品");
			//}else {
			//	$("#dialog-add").attr("title","添加商品");
			//}
			
			$("#productDoc").html("");
			$("#productSpec").html("");
			
			
			
		    $("#reset").click();
		    $("#preview").attr("src", "${ctx }/scripts/image/pic.png");
		    
		    $(".errorMsg").hide();
		}
		function showRequest(formData, jqForm, options) {
			return isRepeat;
		}
		
		function CheckKey()
		{
				$.getJSON("${ctx}/creditactivity/checkProductKey?checkKey=" + $("input[name=productKey]").val(),
	            function(data) {
	                if (data.success) {
	               		isRepeat = true;
	                }
	                else
	                {
	                	$("#repeatKey").show();
	                	$("#repeatKey").html("重复的商品编码");
	                	isRepeat = false;
	                }
	            });
			
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
		
		function search()
		{
			var condition = $("#search-condition").omCombo("value");
			var search = $("#search-word").val();
			var type = $("#search-type").omCombo("value");
			var status = $("#search-status").omCombo("value");
			var minCredit = $('#search-min-credit').val();
			var maxCredit = $('#search-max-credit').val();
			
			var link = "${ctx}/creditactivity/search.action?condition="+condition+"&search="+search+"&typeid="+type+"&productStatus="+status+"&minCredit="+minCredit+"&maxCredit="+maxCredit;
			$("#grid").omGrid('setData',link);
		}
	</script>
</body>

</html>
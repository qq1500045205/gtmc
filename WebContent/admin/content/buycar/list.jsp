<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>贷款方案管理</title>
<%@ include file="/common/admin_pre.jsp"%>
<%@ include file="/common/kindeditor.jsp"%>

<link rel="stylesheet" type="text/css"  href="${ctx }/scripts/css/common.css" />

<script type='text/javascript'>
    loadXML = function(xmlString){
        var xmlDoc=null;
        //判断浏览器的类型
        //支持IE浏览器 
        if(!window.DOMParser && window.ActiveXObject){   //window.DOMParser 判断是否是非ie浏览器
            var xmlDomVersions = ['MSXML.2.DOMDocument.6.0','MSXML.2.DOMDocument.3.0','Microsoft.XMLDOM'];
            for(var i=0;i<xmlDomVersions.length;i++){
                try{
                    xmlDoc = new ActiveXObject(xmlDomVersions[i]);
                    xmlDoc.async = false;
                    xmlDoc.loadXML(xmlString); //loadXML方法载入xml字符串
                    break;
                }catch(e){
                }
            }
        }
        //支持Mozilla浏览器
        else if(window.DOMParser && document.implementation && document.implementation.createDocument){
            try{
                /* DOMParser 对象解析 XML 文本并返回一个 XML Document 对象。
                 * 要使用 DOMParser，使用不带参数的构造函数来实例化它，然后调用其 parseFromString() 方法
                 * parseFromString(text, contentType) 参数text:要解析的 XML 标记 参数contentType文本的内容类型
                 * 可能是 "text/xml" 、"application/xml" 或 "application/xhtml+xml" 中的一个。注意，不支持 "text/html"。
                 */
                domParser = new  DOMParser();
                xmlDoc = domParser.parseFromString(xmlString, 'text/xml');
            }catch(e){
            }
        }
        else{
            return null;
        }

        return xmlDoc;
    }
</script>

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
	
	<div id="dialog-import"  title="导入金融方案">
		<form action="${ctx}/car/importLoan" method="post" enctype="multipart/form-data" id="importLoan">
			<input id="inputfile" name="inputfile" type="file" />
			<input type="button" id="sub" value="开始导入"  class="button-css">
		</form>
	</div>
	
	<script type="text/javascript">
	
	$(document).ready(function() {
		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height:400,
			showIndex : true,
			dataSource : "${ctx}/ajax/griddata.action?type=loan",
			colModel : [ {
				header : '方案名',
				name : 'loanSchemeName',
				align : 'left',
				width : 200
			}, {
				header : '万元月供系数',
				name : 'payPerMonthPercent',
				align : 'left',
				width : '200',          	 	
			}, {
				header : '首付比例',
				name : 'firstPayPercent',
				align : 'left',
				width : 100
			}, {
				header : '尾款比例',
				name : 'lastPayPercent',
				align : 'left',
				width : 100
			}, {
				header : '贷款期数',
				name : 'monthNum',
				align : 'left',
				width : 'autoExpand'
			}],
			autoFit : true
		});

		$('#buttonbar').omButtonbar({
			btns : [ 
			         /* {
				label : "新增",
				id : "button-add",
				icons : {
					left : '${ctx}/scripts/image/add.png'
				},
				onClick : function() {
					$("#dialog-add").omDialog("open");
					$("#sub").click(function(){
						$("#dialog-add").omDialog("close");
					});
				}
			}, */
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
	                                items += "items=" + dels[i].loanSchemeGuid;
	                                if (i < dels.length - 1) {
	                                    items += "&";
	                                }
	                            }
	                            $.getJSON("${ctx}/car/deleteLoanScheme?" + items,
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
			},{
				label : "导出",
				id : "button-export",
				icons : {
					left : '${ctx}/scripts/image/output.png'
				},
				onClick : function() {
					window.location = "${ctx}/car/exportLoan";
				}
			},{
				label : "导入",
				id : "button-import",
				icons : {
					left : '${ctx}/scripts/image/import.png'
				},
				onClick : function() {
					$("#dialog-import").omDialog("open");
					$("#sub").click(function(){
						$("#importLoan").omAjaxSubmit(options);
					});
				}
			}]
		});
		
	    $("#dialog-import").omDialog({
	        autoOpen: false,
	        height: 300,
	        width: 600,
	        modal: true,
	        onClose : function(event) {
	        	
	        }
	    });
	    
	    /* 
	    $("#importLoan").validate({
            rules : {
            	inputfile : {
                	required : true,
                }
            },
            messages : {
            	inputfile : {
                	required : "请选择excel文件",
                }
            },
            showErrors: function(errorMap, errorList) {
            	console.log("errorList", errorList);
                if(errorList && errorList.length > 0){  //如果存在错误信息
                    $.each(errorList,function(index,obj){
                    	if($(obj.element).attr("class") == "input-combo"){
                    		$(obj.element).parent().parent().find("span.errorMsg").show();
                    		$(obj.element).parent().parent().find("span.errorMsg").html(obj.message);
                    	}else {
                    		$(obj.element).parent().find("span.errorMsg").show();
                    		$(obj.element).parent().find("span.errorMsg").html(obj.message);
                    	}
	                   });
                }else{
                    var obj = this.currentElements;
                    if($(obj[0]).attr("class") == "input-combo"){
                    	$(obj[0]).parent().parent().find("span.errorMsg").hide();
                		$(obj[0]).parent().parent().find("span.errorMsg").html("");
                	}else {
                		$(obj[0]).parent().find("span.errorMsg").hide();
                		$(obj[0]).parent().find("span.errorMsg").html("");
                	}
                }
			},
            submitHandler : function(){
            	$('#importLoan').omAjaxSubmit(options);
            	$(this)[0].currentForm.reset();
                return false;
            }
        }); */
	    
	    var options = {
                beforeSubmit : showRequest, 
                success : showResponse
        };
		
        function showRequest(formData, jqForm, options) {
            return true;
        }
        
        function showResponse(responseText, statusText, xhr, $form) {
            $("#dialog-import").omDialog("close");
            console.log(loadXML(responseText).getElementsByTagName("pre")[0].textContent);
            var json = JSON.parse(loadXML(responseText).getElementsByTagName("pre")[0].textContent);
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
                    content:'添加失败'
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

</script>
</body>
</html>
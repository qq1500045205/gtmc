<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>订单列表</title>
<%@ include file="/common/admin_pre.jsp"%>
<link rel="stylesheet" type="text/css"  href="${ctx }/scripts/css/common.css" />
	<style type="text/css">
	.srch_line_new{

	display: inline;
	margin-left: 5px;
}</style>

</head>
<body>
		<div id="search-panel">
			<div>
			<form action="#" id="search-form" method="post">
				<div class="srch_line_new" >
					<input id="queryConditions" />
				</div>
				<div class="srch_line_new" id="keyWordDiv" >
				&nbsp;<input type="text"  style="width: 120px"  id="keyWord" name="keyWord" class="input-text"  placeholder="关键字"/>
				</div>
				<div class="srch_line_new">		
					商品分类&nbsp;&nbsp;<input id="saleInformation"  />		
				</div>
				<div class="srch_line_new">		
					订单状态&nbsp;&nbsp;<input id="queryOrderStatus"   class="input-combo"/>		
				</div>
				<div class="srch_line_new">
				<span class="label">订单日期&nbsp;&nbsp;</span> <input id="startOn"
				name="startOn"  style="width:110px;"/> <span class="label">至&nbsp;&nbsp;</span> <input
				id="endOn" name="endOn" style="width:110px;" />
				</div>
					<div class="srch_line_new"  >
				&nbsp;<input type="text"  id="exchangeIntegral" name="exchange integral" style="width: 120px;" class="input-text"  placeholder="兑换积分"/>
				</div>
				<div class="srch_line_new">
					<span id="button-search"> 搜索 </span>
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
	
	<div id="orderDetail" title="订单详细编辑">
	</div>
	
	
	<script type="text/javascript">
	$(document).ready(function() {
		var cols=[{
			header : '用户名',
			name : 'userName',
			align : 'center',
			width : 75
		}, {
			header : '用户昵称',
			name : 'nickName',
			align : 'center',
			width : 75
		},{
			header : '订单日期',
			name : 'product_order_date',
			align : 'center',
			width : 100
		}, {
			header : '订单状态',
			name : 'order_status_name',
			align : 'center',
			width : 75,
			sort : 'clientSide'
		}, {
			header : '订单号',
			name : 'product_order_num',
			align : 'center',
			width : 130,
		}, {
			header : '收货人',
			name : 'user_location_name',
			align : 'center',
			width : 60
		},{
			header : '商品名称',
			name : 'product_namedoc',
			align : 'center',
			width : 80
		},{
			header : '商品分类',
			name : 'product_name',
			align : 'center',
			width : 80
		},  {
			header : '兑换积分',
			name : 'product_credit',
			align : 'center',
			width : 50
		},{
			header : '数量',
			name : 'product_count',
			align : 'center',
			width : 50
		},{
			header : '查看详细',
			name : 'detail',
			align : 'center',
			width : 80
		}];
		$('#grid').omGrid({
			limit : 12,
			singleSelect : false,
			width : '100%',
			height:400,
			showIndex : false,
			dataSource : "${ctx}/integralmall/getIntegralorders",
			colModel : cols,
			autoFit : true
		});

		
		$('#buttonbar').omButtonbar({
			btns : [ {
				label : "删除",
				id : "removeItems",
				icons : {
					left : '${ctx}/scripts/image/remove.png'
				},
				onClick : function() {
					  var dels = $('#grid').omGrid('getSelections', true);
		                if (dels.length <= 0) {
		                    $.omMessageBox.alert({
		                        type: 'alert',
		                        title: '提示',
		                        content: '请选择要删除的订单！'
		                    });
		                    return;
		                }
		                $.omMessageBox.confirm({
		                    title: '确认删除',
		                    content: '删除后相关的订单信息也将被删除,您确认要删除吗？',
		                    onClose: function(v) {
		                        if (v) {
		                            var items = "";
		                            for (var i = 0; i < dels.length; i++) {
		                                items += "item=" + dels[i].product_order_num;
		                                if (i < dels.length - 1) {
		                                    items += "&";
		                                }
		                            }
		                            $.getJSON("${ctx}/integralmall/deleteOrder?" + items,
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
		
		$("#search-panel").omPanel({
			title : "高级搜索",
			collapsed : false,
			collapsible : false
		});

		$("#queryConditions").omCombo({
			width : 95,
			value : '0',
			editable : false,
			dataSource : [ {
				text : '查询条件',
				value : '0'
			}, {
				text : '用户名',
				value : 'user.userName'
			},{
				text : '订单号',
				value : 'product_order_num'
			}, {
				text : '收货人',
				value : 'userLocationFTemp.user_location_name'
			},{
				text : '商品名称',
				value : 'productListF.productName'
			} ],
			onValueChange:function(target,newValue,oldValue,event){ 
				 if(newValue=="0"){
			        	$("#keyWord").attr("disabled","disabled");
			        	$("#keyWord").val("");
			        }else{
			        	$("#keyWord").removeAttr("disabled");
			        }
			 }
		});
		
		$('span#button-search').omButton({
			icons : {
				left : '${ctx}/scripts/image/search.png'
			},
			width : 60,
			onClick : function(event) {
					var queryConditions= $('#queryConditions').val();
					var keyWord =$("#keyWord").val();
					keyWord=encodeURI(encodeURI(keyWord));
					var saleInformation=$("#saleInformation").val();
					var queryOrderStatus=$("#queryOrderStatus").val();
					var startOn=$("#startOn").val();
					var endOn=$("#endOn").val();
					var exchangeIntegral=$("#exchangeIntegral").val();
					if(compareDate(startOn,endOn)>0){
						alert("订单开始日期不能大于结束日期！");
						return false;
					}
					var  param="?";
					param += 'queryConditions='+queryConditions;
					param += '&keyWord='+keyWord;
					param += '&saleInformation='+saleInformation;
					param += '&queryOrderStatus='+queryOrderStatus;
					param += '&exchangeIntegral='+exchangeIntegral;
					param+='&startOn='+startOn;
					param+='&endOn='+endOn;

					$('#grid').omGrid({
						dataSource : "${ctx}/integralmall/getOrderByQuery"+param,				
					});
			}
		});

	$("#queryOrderStatus").omCombo({              //订单状态
		width : 95,
		value : '',
		editable : false,
		dataSource :"${ctx}/integralmall/getALLOrderStatus",
	});
	
	$("#saleInformation").omCombo({					//商品分类
		width : 95,
		value : '',
		editable : false,
		dataSource :"${ctx}/integralmall/getALLProductType"
	});
	$("#orderDetail").omDialog({
        autoOpen: false,
        height: 550,
        width: 350,
        modal: true,
        buttons: [{
            text : "确定", 
            click : function () {
            	var oderguid=$("#product_order_num").val();                   //订单号            	var order_status_name=$("#order_status_name").val(); 			// 修改前订单状态id
            	if(typeof(userGuid) == "undefined"){
            		$("#orderDetail").omDialog("close");
            	}else{
	    			var product_order_num =$("#orderStatus").val();				// 修改后订单状态id
	    			if(order_status_name==product_order_num){
	    				 $("#orderDetail").omDialog("close");
	    			}else{
	    				var order_status=	$("#order_status_name").find("option:selected").text();   //修改后订单状态    				  $.ajax({
    						type : 'POST',
    						url : "${ctx}/integralmall/changeDetailOrderByOrderNum",
    						async : false,
    						data : {
    							oderguid : oderguid,
    							order_status_name : order_status_name,
    							order_status : order_status
    						},
    						dataType : "json",
    						success : function(res) {
    							if (res.success) {
    								 $.omMessageBox.alert({
      									type : 'alert',
      									title : '提示',
      									content : res.message,
      									onClose:function(value){
 	     								 	if(value){
 	     								 		$("#orderDetail").omDialog("close");
 	     								 		var $ht= $("table").find('td:contains('+oderguid+')').prev().find("div");  //根据订单号，定位到该订单的订单状态
 	     								 		$ht.text(order_status).css("color","red"); 
 	     								 		}
 	     				             		   }
     			  						});
    								 
    							}
    						}
    					});
    			}
              }
            }
        }]
    });

     	 $('#startOn').omCalendar({
	            editable: false,
	        });
	        $('#endOn').omCalendar({
	            editable: false
	        });
	        
	        $('#keyWordDiv').click(function(){
	        	if($('#queryConditions').val()==0){
	        		alert("请选择查询条件");
	        	}
	        });
	        
	});
	    	function openOrderDetail(orderGuid){
	     		var ordernum = orderGuid;
	     		$.ajax({
					cache:false,
					async:true,
					type:"POST",  
					url:" ${ctx}/integralmall/getDetailOrderByGuid?ordernum="+ordernum,
					success:function(html){
						$("#orderDetail").html(html);
						  $("#orderDetail").omDialog({autoOpen : true});
	     			}
	     		});
	     		}
	    	
	    	 	 /** 日期比较 **/
  	            function compareDate(strDate1,strDate2)
  	            {
  	                var date1 = new Date(strDate1.replace(/\-/g, "\/"));
  	                var date2 = new Date(strDate2.replace(/\-/g, "\/"));
  	                return date1-date2;
  	            }

</script>
</body>
</html>
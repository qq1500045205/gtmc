<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head> 
<%@ include file="/common/admin_pre.jsp"%> 
<script type="text/javascript" src="${ctx }/scripts/ui/om-grid-sort.js"></script>
<script type="text/javascript" src="${ctx }/scripts/ui/editor/omeditor.js"></script>
<script type="text/javascript" src="${ctx}/scripts/json.js"></script>  
<link rel="stylesheet" type="text/css" href="${ctx}/scripts/themes/default/om-grid-sort.css" /> 
<link rel="stylesheet" type="text/css" href="${ctx}/scripts/themes/default/om-dialog.css" />
<title>问卷调查管理</title>
<style type="text/css">
.errstyle{background-color: red;}
#grid a {color:blue; text-decoration:none; cursor:pointer;}
#grid a:link { color:blue;  text-decoration:underline; cursor:pointer;}
#grid a:visited { color:blue;  text-decoration:underline; cursor:pointer;}
#grid a:hover { color:blue;  text-decoration:underline; cursor:pointer;}
#grid a:active { color:blue;  text-decoration:underline; cursor:pointer;}
</style>
</head>
<body>
 <div id="search-panel">
	<div>
		<span class="label">销售店代号：</span> <input type="text" width="80px" id="dlrcodeSText" class="input-text" />
		至 <input type="text" width="80px" id="dlrcodeEText" class="input-text" />
		<span class="label">接收调查时间：</span> <input type="text" id="replySDate" name="replySDate" maxlength="10" />
		至 <input type="text" id="replyEDate" name="replyEDate"  maxlength="10"/>
		<span class="label">总体评价：</span><input type="text" id="sumText"/>
		<span id="button-search">搜索</span>
	</div>
 </div>
 <div id="news">
		<div id="buttonbar" class="om-buttonbar" style="margin-bottom: 5px;"></div>
		<table id="grid"></table>
 </div>
 <div id="dialog-view"  title="查看问卷记录" >
 	<iframe id="view-question-frame" style="width:360px;height:440px;"></iframe>
 </div>
<script type="text/javascript">
	var auth = "";
	
	function checkFormdata(){
		var sdlr = $("#dlrcodeSText").val();
		var edlr =  $("#dlrcodeEText").val();
		if (sdlr=="" && edlr==""){
			$.omMessageBox.alert({
			       type:'alert',
			       title:'提示',
			       content:'请至少输入一个销售店代号'
			});
			return false;
		}
		
		if (!isEmpty(sdlr) && !isEmpty(edlr) && (sdlr>edlr)){
			$.omMessageBox.alert({
			       type:'alert',
			       title:'提示',
			       content:'起始销售店代号不能大于结束销售店代号。'
			});
			return false;
		}
		
		
		var sdate= $("#replySDate").val();
		var edate = $("#replyEDate").val();
		if (!isEmpty(sdate) && !IsValidDate(sdate)){
			$.omMessageBox.alert({
			       type:'alert',
			       title:'提示',
			       content:'请输入正确的时间格式【YYYY-MM-DD】'
			});
			return false;
		}
		
		if (!isEmpty(edate) && !IsValidDate(edate)){
			$.omMessageBox.alert({
			       type:'alert',
			       title:'提示',
			       content:'请输入正确的时间格式【YYYY-MM-DD】'
			});
			return false;
		}
		
		if (!isEmpty(sdate) && !isEmpty(edate) && (sdate>edate)){
			$.omMessageBox.alert({
			       type:'alert',
			       title:'提示',
			       content:'接收调查开始时间不能大于结束时间。'
			});
			return false;
		}
		
		return true;
		
	}
	
	function isEmpty(str){
		if (str==null){
			return true;
		}
		
		if (str==""){
			return true;
		}
		
		return false;
	}
	
	/*验证日期格式YYYY-MM-DD
	**个位数月份前面必须加0前缀
	**合法：2014-01-01
	**不合法：2014-1-01 2014-01-1*/
	function IsValidDate(str){
		var date = str;
		if(date.length!=10){
			return false;
		}
        var result = date.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);

        if (result == null)
            return false;
        var d = new Date(result[1], result[3] - 1, result[4]);
        return (d.getFullYear() == result[1] && (d.getMonth() + 1) == result[3] && d.getDate() == result[4]);

	}
	
     $(document).ready(function() {
    	 //页面初始化样式
    	 $("#search-panel").omPanel({
          	title : "高级搜索",
          	collapsed: false,
          	collapsible:false
         });
    	 
    	 $("#dialog-view").omDialog({
 	        autoOpen: false,
 	        height: 550,
 	        width: 400,
 	        modal: true,
 	        resizable:false
 	    });
    	 
    	 $('span#button-search').omButton({
        	    icons : {left : '${ctx}/scripts/image/search.png'},
        	    width : 70, 
        	    height:25,
        	    onClick : function(event) {
        	    	if (checkFormdata()){
        	    		getAnswerList();
        	    	}
    			}
     	 });
    	 $("#replySDate").omCalendar();
    	 $("#replyEDate").omCalendar();
         
         $('#buttonbar').omButtonbar({
 			btns : [ {
 				label : "导出",
 				id : "button-export",
 				icons : {
 					left : '${ctx}/scripts/image/output.png'
 				},
 				onClick : function() {
 					var store  = $('#grid').omGrid('getData');
 					if (store != null && store.total>0){
 						location.href = "${ctx}/survey/export";
 					}else{
 						$.omMessageBox.alert({
 					       type:'alert',
 					       title:'提示',
 					       content:'请先检索出数据，再导出'
 					});
 					}
 					
 				}
 			}]
 		 });
         
         $("#sumText").omCombo({
 			width : 100,
 			value : '-',
 			editable : false,
 			dataSource : [ {
 				text : '选择评分',
 				value : '-'
 			}, {
 				text : '1',
 				value : '1'
 			}, {
 				text : '2',
 				value : '2'
 			} , {
 				text : '3',
 				value : '3'
 			} , {
 				text : '4',
 				value : '4'
 			} , {
 				text : '5',
 				value : '5'
 			} , {
 				text : '6',
 				value : '6'
 			} , {
 				text : '7',
 				value : '7'
 			} , {
 				text : '8',
 				value : '8'
 			} , {
 				text : '9',
 				value : '9'
 			} , {
 				text : '10',
 				value : '10'
 			} ]
 		 });
         getAuth();
     });
     
     getAuth= function(){
    	 $.ajax({
			  type: 'POST',
			  url: "${ctx}/survey/getAuth",
			  data: {},
			  success: function(res){
				  data = res.result;
				  auth=data[0].auth;
				  if (auth=="ADMIN" || auth=="DISTADMIN"){
					  
				  }else{
					  $("#dlrcodeSText").val(data[0].dlrcode);
					  $("#dlrcodeEText").val(data[0].dlrcode);
					  $("#dlrcodeSText").attr("readonly","readonly");
					  $("#dlrcodeSText").css("background-color","#eeeeee");
					  $("#dlrcodeEText").attr("readonly","readonly");
					  $("#dlrcodeEText").css("background-color","#eeeeee");
				  }
				  //getAnswerList();
			  }
		});
     }
     
     showQuestion = function(guid){
    	 $("#dialog-view").omDialog("open");
    	 $("#view-question-frame").attr("src","${ctx}/wx/html/service-question.jsp?guid="+guid);
	 }
     
     getAnswerList = function(){
    	 $('#grid').omGrid({
    		 limit:12,
             singleSelect : false,
             width:'100%',
             height:440,
             autoFit : true,
             showIndex: false,
             dataSource : "${ctx}/survey/g?dlrcodeS="+$("#dlrcodeSText").val()+
            			"&dlrcodeE="+$("#dlrcodeEText").val()+
            		 	"&replySDate="+$("#replySDate").val()+
            		 	"&replyEDate="+$("#replyEDate").val()+
            		 	"&number="+$("#sumText").val(),
             colModel : 
             [
				 {header : '接收调查时间',name : 'replydate',align : 'center',width : 100,
					renderer : function(colValue, rowData, rowIndex) {
						return '<a href="javascrpit:void;" onclick="showQuestion(\''+rowData.id+'\')">' + colValue + '</a>';
					}
				 },
				 {header : '销售店代号',name : 'companycode',align : 'center',width : 80},
				 {header : '销售店名',name : 'companyname',align : 'left',width : 160},
				 {header : '保养人姓名',name : 'customername',align : 'center',width : 80},
				 {header : 'VIN码',name : 'vinno',align : 'center',width : 140},
				 {header : '保养电话',name : 'mobileno',align : 'center',width : 120}, 
				 {header : '维修日期',name : 'lastmaintenancedate',align : 'center',width : 100},  
                 {header : '总体评价',name : 'q1',align : 'center',width:90}, 
                 {header : '备注',name:'remark',align:'center',width:'autoExpand'},
			 ]
    	 });
     }
</script>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<%@ include file="/common/pre_general.jsp"%>
<script src="${ctx}/scripts/wx.js"></script>
<title>积分活动记录</title>
<style>
table {
    *border-collapse: collapse; /* IE7 and lower */
    border-spacing: 0;
    width: 100%;    
}

.bordered {
    border: solid #ccc 1px;
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    -webkit-box-shadow: 0 1px 1px #ccc; 
    -moz-box-shadow: 0 1px 1px #ccc; 
    box-shadow: 0 1px 1px #ccc;         
}

.bordered tr:hover {
    background: #fbf8e9;
    -o-transition: all 0.1s ease-in-out;
    -webkit-transition: all 0.1s ease-in-out;
    -moz-transition: all 0.1s ease-in-out;
    -ms-transition: all 0.1s ease-in-out;
    transition: all 0.1s ease-in-out;     
}    
    
.bordered td, .bordered th {
    border-left: 1px solid #ccc;
    border-top: 1px solid #ccc;
    padding: 10px;
    text-align: left;    
}

.bordered th {
    background-color: #dce9f9;
    background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc), to(#dce9f9));
    background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:    -moz-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:     -ms-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:      -o-linear-gradient(top, #ebf3fc, #dce9f9);
    background-image:         linear-gradient(top, #ebf3fc, #dce9f9);
    -webkit-box-shadow: 0 1px 0 rgba(255,255,255,.8) inset; 
    -moz-box-shadow:0 1px 0 rgba(255,255,255,.8) inset;  
    box-shadow: 0 1px 0 rgba(255,255,255,.8) inset;        
    border-top: none;
    text-shadow: 0 1px 0 rgba(255,255,255,.5); 
}

.bordered td:first-child, .bordered th:first-child {
    border-left: none;
}

.bordered th:first-child {
    -moz-border-radius: 6px 0 0 0;
    -webkit-border-radius: 6px 0 0 0;
    border-radius: 6px 0 0 0;
}

.bordered th:last-child {
    -moz-border-radius: 0 6px 0 0;
    -webkit-border-radius: 0 6px 0 0;
    border-radius: 0 6px 0 0;
}

.bordered th:only-child{
    -moz-border-radius: 6px 6px 0 0;
    -webkit-border-radius: 6px 6px 0 0;
    border-radius: 6px 6px 0 0;
}

.bordered tr:last-child td:first-child {
    -moz-border-radius: 0 0 0 6px;
    -webkit-border-radius: 0 0 0 6px;
    border-radius: 0 0 0 6px;
}

.bordered tr:last-child td:last-child {
    -moz-border-radius: 0 0 6px 0;
    -webkit-border-radius: 0 0 6px 0;
    border-radius: 0 0 6px 0;
}
</style>
</head>
<body>
	<form id="form" name="form">
		<!-- <h3 class='title'>积分活动记录</h3> -->
		<div>
			<input type="hidden" name="start" id="start" class="input-text">
		</div>
		<div id='info-box-body'></div>

		<div>
			<a href="javascript:;" onclick="li_click(0)"><input type="submit" id="sub" value="上一页" class="button-css"></a>
			<a href="javascript:;" onclick="li_click(1)"><input type="submit" id="sub1" value="下一页" class="button-css"></a>
			总记录数为<label id="datacount"></label>
		</div>
	</form>
	<script type="text/javascript">
	var param = new Wx.Param();
	var openId =param.getOPENID();
	var gzhHash =param.getGZHHASH('##GZHHASH##');
	var entrance = Util.Browser.getEntrance();
	var req = new Wx.Request();
	var countCredit=0;
	var currentCredit=0;
	var username=null;
	var datacount=0;
	var data = [];
	var start;
	var limit=10;
	var value = "${param.start}";
	$(document).ready(function() {
		var start=$("input[name=start]").val();
        if(value==""){        
        	start=0;
        	getData(start);
        }else{
        	var startflag =parseInt(value)
        	getData(startflag);
        }
	});
	

	function getData(start) {
		$.ajax({
			type : 'POST',
			url : "${ctx }/creditactivity/creditDetail.action",
			async : false,
			data : {
				type : "getData",
				start:start,
				openId: openId,
				gzhHash:gzhHash
			},
			dataType : "json",
			success : function(json) {
			var html = "";
			if( json.rows.length == 0 ){
				return ;
			}
			data = json.rows;
			datacount=json.total;
	        $("#datacount").text(datacount);
			for(var i=0;i<data.length;i++){
				countCredit = data[i].countCredit;
				currentCredit = data[i].currentCredit;
				username=data[i].userName;
			    $("input[name=start]").val(data[i].start);
			}
			if(username !=undefined){
			html += "<B>"+ username+" 您好 欢迎登陆到积分管理后台系统</B></br>";	
			}else{
				html += "<B>&nbsp;&nbsp;&nbsp; 您好 欢迎登陆到积分管理后台系统</B></br>";	
			}
			html += "<B> 您当前的积分为"+currentCredit+"</B>";	
			html += "<B> 你的总积分为" +countCredit+"</B></br>";
			html += "<B> 积分获取/兑换记录</B>";	
			html+="<div align=center><table class=\"bordered\" align='center' border='1' cellpadding='0' cellspacing='0' width='80%'>";
			html+="<thead><tr><th>积分活动标题</th><th>参加日期</th><th>活动积分</th></tr></thead>";
			for(var i=0;i<data.length;i++){
				item = data[i];
				html += "<tr>";
				html += "<td><a href=\"javascript:;\" onclick=\"lia_click("+i+")\">"+item.actName +"</td>";	
				if(item.creDate!=undefined){  
				html += "<td>"+item.creDate +"</td>";	
			      }else{
	    				html += "<td> </td>";	
			      }
				html += "<td>"+item.actCredit +"</td>";	
				html += "</tr>";
			}
			html+="</table></div>";
			$('#info-box-body').html(html);
			}
		});		
	}
	
	function li_click(index) {	
		var url1,url2;
		if (index==0){
			start=parseInt($("#start").val());
			if(start==0){
			     alert("已经是最前一页");
				//return;				
			}else{
				start=start-limit;
			    $("input[name=start]").val(start);
			     url1="creditactive-detail-page.jsp?start:"+start+",openId:"+ openId+",gzhHash:"+ gzhHash;
			}			
		}
		if(index==1){
			var page=datacount/limit;
			var Records=page*limit;
			start=parseInt($("#start").val());
			var startflag=start+limit;
			if(startflag>=Records){
			     alert("已经是最后一页");
				//return;				
			}else{
			    $("input[name=start]").val(startflag);
			     url2="creditactive-detail-page.jsp?start:"+start+",openId:"+ openId+",hash:"+ gzhHash;
			}
		}
		var url = param.getURL('##URL##',[{src: url1},{src:url2}]);
		console.log(url[index]);
  		Util.Browser.jump(url[index].src, "23-1");  	
	}
	function lia_click(i) {	
		var url1;
			var memberCreditGuid= data[i].memberCreditGuid;
		     url1="${ctx}/wx/html/integralActivities.jsp?guid="+ memberCreditGuid+"&openId="+ openId+"&hash="+ gzhHash;	
		     //url1="${ctx}/admin/content/creditactive/qurey.jsp?memberCreditGuid="+ memberCreditGuid;	
		console.log(url1);
  		Util.Browser.jump(url1, "23-1");  	
		
	}
	</script>
</body>
</html>

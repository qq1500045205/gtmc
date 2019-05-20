<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户权限</title>
	<script type="text/javascript" src="${ctx }/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-core.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-panel.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-tabs.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/ui/om-ajaxsubmit.js"></script>
	
	<!-- script type="text/javascript" src="${ctx }/scripts/json.js"></script-->
	<script type="text/javascript" src="${ctx }/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/wx.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${ctx }/scripts/themes/default/om-all.css" />
	<style type='text/css'>
		#content{
			padding-top: 20px;
		}
		.menu_box{
			float:left;
			padding: 5px 0px;
			display: inline-block;
			margin-right: 7px;
			font-size: 14px;
		}
		.top_title{
			background: #91bdd8;
			cursor: pointer;
			padding:5px 7px;
		}
		.sub_title{
			padding: 3px 5px 3px 20px;
			border-left:  1px dotted #bdb6b7;
			border-right:  1px dotted #bdb6b7;
			border-bottom:  1px dotted #bdb6b7;
		}
		.btn{
			border:1px solid #ddd;
			border-radius:5px;
			background:#eee;
			padding:5px; 
		}
	</style>
	<body>
	<div style="padding:20px;">
		
			<div>
				管理员
				<select id="userlist" >
				</select>
				<input type='button' value='保存' id='save' class="btn"/>
			</div>
			<form action='' name='rights'>
			<div id='content'>
				 
			
			</div>
			</form>
	</div>
	<script>
		var html = '';
		var req = new Wx.Request();
		req.postData('admin/getusermenu', {}, function(ret){
			for(var i=0;i<ret.length;i++){
				var title = ret[i].text;
				var id = ret[i].id;
				html += '<div class="menu_box">';
				html += '	<div class="top_title"><label>'+ret[i].text+'</label></div>';
				if(ret[i].children && typeof ret[i].children != 'undefined' ){
					var items = ret[i].children;
					for(var j=0;j<items.length;j++){
						html += '<div class="sub_title"><label><input class="checkbtn" type="checkbox" id="right_' + items[j].rights + '" name="right_' + items[j].rights + '" />'+items[j].text+'</label></div>';
					}
					
				}
				html += '</div>';
			}
			$('#content').html(html);
			
			
			$(".checkbtn").each(function(){
				console.log('each');
				$(this).bind('click', function(){
					var me = this;
					var name = $(this).attr('name');
					var value = $(this).attr("checked");
					//console.log(value);
					
					if(value=='checked'){
						$("input[name='"+name+"']").each(function(){
							if(this!=me)
								$(this).attr("checked", 'checked');
						});
					}else{
						$("input[name='"+name+"']").each(function(){
							$(this).attr("checked", null);
						});
					}
					
				});
			});
		});

		var getdata;
		$('#save').bind(
				'click',
				function() {
					var form = new Util.Form('rights');
					var sum = 0;
					getdata = form.getValues();
					console.log(getdata);
					for ( var item in getdata) {
						if (getdata[item] == true) {
							sum += parseInt(item.replace("right_", ""), 2);
							console.log(parseInt(item.replace("right_", ""), 2)
									.toString(2));
						}
					}
					;
					console.log(sum.toString(2).toString());
					var userGuid=$("#userlist").val();
					var rights=sum.toString(2).toString();
					console.log($("#userlist").val());
					req.postData('admin/saveusermemu?userGuid='+userGuid+'&rights='+rights, {}, function(ret){});
					
					
				});
		
		$(function(){
	    $.get('${ctx}/webuser/getsubwebuserlist',
	    	    function(res) {
	    	        var data = res.result;
	    	       // $("#userlist").append("<option value='all'></option>");
	    	        for (var i = 0; i < data.length; i++) {
	    	            if (data[i].userGuid == "${param.userGuid}") {
	    	                $("#userlist").append("<option value='" + data[i].userGuid + "' selected='selected'>" + data[i].userName + "</option>");
	    	                $("#userlist").change();
	    	            } else {
	    	                $("#userlist").append("<option value='" + data[i].userGuid + "'>" + data[i].userName + "</option>");
	    	            }
	    	        }
	    	    });
		});
	</script>
</body>





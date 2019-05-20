<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/common/admin_pre.jsp"%> 

	<script type="text/javascript" src="${ctx}/scripts/common.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/wx.js"></script> 
	
	<title>用户权限</title>
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
			background: #eee;
			cursor: pointer;
			padding:5px;
			line-height:25px;
			color:#666;
			border-top-left-radius:5px;
			border-top-right-radius:5px;
		}
		.sub_title{
			padding: 5px 20px 5px 10px;
			border:1px solid #eee;
			border-top:0; 
		} 
	</style>
	<body>
	<div style="padding:20px;">
		<div>管理员姓名
			<input id="userlist" />
			<input type='button' value='保存' id='save' class="btn-def"/>
		</div>
		<form action='' name='rights'>
			<div id='content'></div>
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
				
				if(ret[i].children && typeof ret[i].children != 'undefined' ){
					var items = ret[i].children;
					if(items.length>0){
						html += '<div class="top_title"><label><input class="checkbtn" style="display:none" type="checkbox" id="right_' + ret[i].rights + '" name="right_' + ret[i].rights + '" />'+ret[i].text+'</label></div>';
						for(var j=0;j<items.length;j++){
							html += '<div class="sub_title"><label><input class="checkbtn" type="checkbox" id="right_' + items[j].rights + '" name="right_' + items[j].rights + '" />'+items[j].text+'</label></div>';
						}
					}
				}
				html += '</div>';
			}
			$('#content').html(html);
			renerUserList();
			
			$(".sub_title .checkbtn").each(function(){
				console.log('each');
				$(this).change(function(){
					var me = this;
					var name = $(this).attr('name');
					var value = this.checked;
					//console.log(value);
					
					if(value==true){
						$("input[name='"+name+"']").each(function(){
							if(this!=me)
								$(this).attr("checked", 'checked');
						});
					}else{
						$("input[name='"+name+"']").each(function(){
							if(this!=me)
								$(this).attr("checked", null);
						});
					}
					var topright = false;
					$(this).parent().parent().parent().find('.sub_title input[type="checkbox"]').each(function(){
						topright = topright || this.checked;
					});
					$(this).parent().parent().parent().find('.sub_title input[type="checkbox"]').each(function(){
						//console.log('checked = ' + this.checked);
					});
					//console.log('topright . = ' + topright);
					if(topright){
						$(this).parent().parent().parent().find('.top_title  input').each(function(){
							//console.log('value : ' + this.checked);
							$(this).attr('checked', 'checked');
						});
					}else{
						$(this).parent().parent().parent().find('.top_title  input').each(function(){
							//console.log('value : ' + this.checked);
							$(this).attr('checked', null);
						});
					}
					$(this).parent().parent().parent().find('.top_title  input').each(function(){
						//console.log('value : ' + this.checked);
					});
					//console.log('-------------');
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
					req.postData('admin/saveusermemu?userGuid='+userGuid+'&rights='+rights, {}, function(ret){
						if(ret.length==0){
							alert('保存成功');
						}else{
							alert('保存失败');
						}
					});
					
					
				});
		
		function renerUserList(){
		    $.get('${ctx}/webuser/getsubwebuserlist',
		    	    function(res) {
		    			var list = [];
		    	        var data = res.rows;
		    	        if(data.length==0){
		    	        	alert('没有可管理的用户');
		    	        	return;
		    	        }
		    	        for (var i = 0; i < data.length; i++) {
		    	        	list.push({
		    	        		text: data[i].userName,
		    	        		value: data[i].userGuid,
		    	        	});
		    	        }
		    	        
		    	        $('#userlist').omCombo({
		    	        	width : 154,
		    	 			value : '管理员',
		    	 			editable : false,
		    	 			dataSource :list,
		    	 			onValueChange : function(target, newValue, oldValue, event) {
		    	 				req.postData('../../webuser/getUserRight', {user_guid:newValue}, function(ret){
		    	 					if(ret.total>0){
			    	 					var item = ret.rows[0];
			    	 					setRights(item.rights);
		    	 					}
		    	 				});
		    	 			}
		            	 });
		    
		   		});
		}
		function setRights(rights){
			var name = '';
			var right = '';
			var naught = '';
			$("input[type='checkbox']").each(function(){
				$(this).attr('checked', null);
			})
			for(var i=rights.length-1;i>=0;i--){
				var ch = rights.charAt(i);
				right = ch + naught; 
				$("input[name='right_"+right+"']").attr('checked', 'checked');
				naught = naught + '0';
			}
		}
	</script>
</body>





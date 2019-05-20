<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/geturl.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<title>品牌活动</title>
<%@ include file="/common/pre.jsp"%>
<%@ include file="/common/admin_pre.jsp"%>
<style>
body {
	font-family: "微软雅黑", Arial;
	background: #ddd;
	min-width: 300px;
}

.box {
	border-radius: 5px;
	background: #fff;
	width: 100%;
	box-shadow: 0 1px 0 #fff;
}

.info_title {
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	font-weight: bold;
	line-height: 35px;
	background-color: #eee;
	padding-left: 8px;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, .1);
}

input {
	width: auto !important;
	height: 25px !important;
	line-height: 25px !important;
	margin-bottom: 0 !important;
}

textarea {
	width: auto !important;
	margin-bottom: 0 !important;
}

.select-small {
	min-width: 50px;
	width: 50px;
	height: 25px;
	line-height: 25px;
	margin-top: 5px;
	margin-bottom: 0 !important;
}

.select-big {
	min-width: 165px;
	width: 165px;
	height: 25px;
	line-height: 25px;
	margin-bottom: 0 !important;
}

.table th,.table td {
	padding: 2px;
}
</style>

</head>
<body>
	<div class="container-fluid" style="padding: 8px;">
		<div class="row-fluid box">
			<div class="info_title">活动报名表单设置</div>
			<table class="table">
				<tr>
					<td colspan="4" style="color: red">
						活动名称、微信名、用户姓名、手机号，为必须字段，此处无须设置</td>
				</tr>
				<tr>
					<td style="padding-left: 12px;">车牌号</td>
					<td><input type="text" class="input" name="userCarNo"
						disabled="disabled"></td>
					<td>&nbsp;</td>
					<td><a href="javascript:;" onclick="setNoShow('userCarNo')"
						id="userCarNo-A">设为不显示</a></td>
				</tr>
				<tr>
					<td style="padding-left: 12px;">参加时间</td>
					<td><input type="text" class="input" name="attendOn"
						disabled="disabled"></td>
					<td>&nbsp;</td>
					<td><a href="javascript:;" onclick="setNoShow('attendOn')"
						id="attendOn-A">设为不显示</a></td>
				</tr>
				<tr>
					<td style="padding-left: 12px;">成人数</td>
					<td>
						<select class="select-big" name="attendNum">
						</select>
					</td>
					<td><a href="javascript:;"
						onclick="setSelectMax('attendNum')">设置最大值</a></td>
					<td><a href="javascript:;" onclick="setNoShow('attendNum')"
						id="attendNum-A">设为不显示</a></td>
				</tr>
				<tr>
					<td style="padding-left: 12px;">儿童数</td>
					<td><select class="select-big" name="attendChild">
					</select></td>
					<td><a href="javascript:;"
						onclick="setSelectMax('attendChild')">设置最大值</a></td>
					<td><a href="javascript:;" onclick="setNoShow('attendChild')"
						id="attendChild-A">设为不显示</a></td>
				</tr>
				<tr>
					<td style="padding-left: 12px;">到场方式</td>
					<td>
						<select class="select-big" name="arriveBy">
						</select>
					</td>
					<td><a href="javascript:;"
						onclick="setSelect('arriveBy')">设置选项</a></td>
					<td><a href="javascript:;" onclick="setNoShow('arriveBy')"
						id="arriveBy-A">设为不显示</a></td>
				</tr>
				<tr>
					<td style="padding-left: 12px;">班车路线</td>
					<td>
						<select class="select-big" name="arriveLine">
						</select>
					</td>
					<td><a href="javascript:;"
						onclick="setSelect('arriveLine')">设置选项</a></td>
					<td><a href="javascript:;" onclick="setNoShow('arriveLine')"
						id="arriveLine-A">设为不显示</a></td>
				</tr>
				<tr>
					<td style="padding-left: 12px;">备注</td>
					<td><textarea name="remark" disabled="disabled"> </textarea></td>
					<td><a href="javascript:;"
						onclick="setRemarkMax('remark')">设置字数上线</a></td>
					<td><a href="javascript:;" onclick="setNoShow('remark')"
						id="remark-A">设为不显示</a></td>
				</tr>
				<tr>
					<td colspan="3">
						<button style="background-color: red; width: 60%">向手机发送短信验证码&nbsp;&nbsp;&nbsp;</button>
						<a href="#" style="margin-left: 10px">重发</a>
					</td>
					<td><a href="javascript:;" onclick="setNoShow('sendVal')"
						id="sendVal-A">设为不显示</a></td>
				</tr>
				<tr>
					<td colspan="4">
						<button class="btn btn-primary btn-block" type="button"
							onclick="saveSetting()">保存设置</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<div id="dialog" title="设置选项">
	</div>

	<script type="text/javascript">
		var data = {
			userCarNo : {
				form_field : "userCarNo",
				form_field_show : 1
			},
			attendOn : {
				form_field : "attendOn",
				form_field_show : 1
			},
			attendNum : {
				form_field : "attendNum",
				form_select_max : 5,
				form_field_show : 1
			},
			attendChild : {
				form_field : "attendChild",
				form_select_max : 2,
				form_field_show : 1,
			},
			arriveBy : {
				form_field : "arriveBy",
				form_select : [{text:"自驾",value:"自驾"},{text:"公交",value:"公交"},{text:"出租",value:"出租"}],
				form_field_show : 1
			},
			arriveLine : {
				form_field : "arriveLine",
				form_select : [{text:"西直门--草房",value:"西直门--草房"},{text:"望京--草房",value:"望京--草房"}],
				form_field_show : 1
			},
			remark : {
				form_field : "remark",
				form_remark_num : 200,
				form_field_show : 1
			},
			sendVal : {
				form_field : "sendVal",
				form_field_show : 1
			}
		};
		
		$.ajax({
			type : 'POST',
			url : "${ctx}/activity/getFormActive",
			async : false,
			data : {
				actGuid : "$param.actGuid}"
			},
			dataType : "json",
			success : function(json) {
				if (json.success) {
					data = json.result;
				}
			}
		});

		init();
		function init() {
			//设置是否显示
			check("userCarNo");
			check("attendOn");
			check("attendNum");
			check("attendChild");
			check("arriveBy");
			check("arriveLine");
			check("remark");
			check("sendVal");
			//设置下拉框选项
			setSelectValue("attendNum");
			setSelectValue("attendChild");
			setSelectValue("arriveBy");
			setSelectValue("arriveLine");
			//设置下拉框选项管理
			//setSelectItem("arriveBy");
			//setSelectItem("arriveLine");
		}

		function check(name) {
			if (data[name]["form_field_show"] == 1) {
				$("#" + name + "-A").html("设为不显示（当前显示）");
			} else {
				$("#" + name + "-A").html("设为显示（当前不显示）");
			}
		}
		function setSelectValue(name) {
			if( data[name]){
				var num = data[name]["form_select_max"];
				var sd = data[name]["form_select"];
				$("select[name="+name+"]").html("");
				if(num){
					var html = "";
					for(var i=1;i<=num;i++){
						html += "<option value="+i+">"+i+"</option>";
					}
					$("select[name="+name+"]").html(html);
				}
				
				if(sd && sd.length > 0 ){
					var html = "";
					for(var i=0;i<sd.length;i++){
						html += "<option value="+sd[i].value+">"+sd[i].text+"</option>";
					}
					$("select[name="+name+"]").html(html);
				}
			}
		}
		
		//设置select选项
		function setSelect(name) {
			$( "#dialog").omDialog('open');
			setSelectItem(name);
			/* var str = prompt("设置选项，多个选项使用|分割。");
			if (str) {
				var ary = str.split("|");
				for ( var i = 0; i < ary.length; i++) {
					data[name]["form_select"].push({
						text : i,
						value : ary[i]
					});
				}
			} */
		}
		
		function setSelectItem(name){
			if( data[name]){
				var sd = data[name]["form_select"];
				if(sd && sd.length > 0 ){
					var html = "<table  class='table'>";
					for(var i=0;i<sd.length;i++){
						html += "<tr><td style='padding-left: 12px;width:60%'>"+sd[i].text
						+"</td><td width='20%'><a href='javascript:;' onclick=delItem(this,'"+name+"','"+sd[i].value+"')>删除</a></td></tr>";
					}
					 html += "</table><a href='javascript:;' style='float:right;margin-right:30px;' onclick=addItem('"+name+"')>添加</a>";
					$("#dialog").html(html);
				}
				$("#dialog").html(html);
			}
		}
		
		function delItem(th,name,value){
			console.log(name,value);
			var sd = data[name]['form_select'];
			if(sd && sd.length > 0 ){
				for(var i=0;i<sd.length;i++){
					if(sd[i].value = value){
						data[name]['form_select'].splice(i,1);
						$(th).parent().parent().remove();
						setSelectValue(name);
						break;
					}
				}
			}
		}
		
		function addItem(name){
			$.omMessageBox.prompt({
		           title:'设置选项',
		           content:'请输入新的选项：',
		           onClose:function(v){
		               if(v===false){
		                   return;
		               }
		               if(v==''){
		                   alert('输入不能为空');
		                   return false;
		               }
		               data[name]["form_select"].push({
							text : v,
							value : v
						});
		               setSelectItem(name);
		               setSelectValue(name);
		           }
		       });
		}

		//设置为不显示0  显示1
		function setNoShow(name) {
			if (data[name]["form_field_show"] == 0) {
				data[name]["form_field_show"] = 1;
				$("#" + name + "-A").html("设为不显示（当前显示）");
			} else {
				data[name]["form_field_show"] = 0;
				$("#" + name + "-A").html("设为显示（当前不显示）");
			}
		}
		//设置select最大值
		function setSelectMax( name) {
			 $.omMessageBox.prompt({
		           title:'设置最大值',
		           content:'请输入人数：',
		           onClose:function(v){
		               if(v===false){
		                   return;
		               }
		               if(v==''){
		                   alert('数量不能为空');
		                   return false;
		               }
		               if(v-0+'' === v){
		            	   data[name]["form_select_max"] = v;
		            	   setSelectValue(name);
		               }else{
		                   alert('只能输入数字');
		                   return false;
		               }
		           }
		       });
		}
		
		$( "#dialog").omDialog({
			autoOpen: false,
			width: 400,
			height: 300,
			modal: true
		});

		//设置remark最大值
		function setRemarkMax(name) {
			$.omMessageBox.prompt({
		           title:'设置最大值',
		           content:'请输入备注最大字数：',
		           onClose:function(v){
		               if(v===false){
		                   return;
		               }
		               if(v==''){
		                   alert('数量不能为空');
		                   return false;
		               }
		               if(v-0+'' === v){
		            	   data[name]["form_remark_num"] =v;
		               }else{
		                   alert('只能输入数字');
		                   return false;
		               }
		           }
		       });
		}

		function saveSetting() {
			$.ajax({
				type : "POST",
				url : "${ctx}/ajax/saveSignForm",
				data : {
					data : JSON.stringify(data),
					actGuid : "${param.actGuid}"
				},
				success : function(data) {
					if (data.success) {
						alert(data.message);
					} else {
						alert(data.message);
					}
				}
			});
		}
	</script>
</body>
</html>
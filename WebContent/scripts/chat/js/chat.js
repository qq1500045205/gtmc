// JavaScript Document
//@Author xuwei   Zcool ID:xuwei0930
//email:xuwei0930@gmail.com
//chat0.1 Demo
$(function(){
	var chat='';
	var location = (window.location+'').split('/'); 
	var basepath =location[0]+'//'+location[2]+'/'+location[3]; 

	var path = basepath+"/scripts/chat/";
	chat+='<div class="chat-mini">';
	chat+='<div class="chat-status">聊天室</div>';
	chat+='</div>';
	chat+='<div class="chat-side">';
	chat+='<div class="chat-title"><span class="close">&times;</span>聊天室</div>';
	chat+='<div class="chat-body">'
	chat+='<ul class="tab cf"><li class="now" tid="1">房间</li><li tid="2">好友</li><li tid="3">在线</li></ul>'
	chat+='<div class="tab-body">'
	chat+='<div tid="1" class="tab-list" style="display:block;"><center><br/>加载中</center></div>'//第一tab
	chat+='<div tid="2" class="tab-list"><center><br/>加载中</center></div>'//第二tab
	chat+='<div tid="3" class="tab-list"><center><br/>加载中</center></div></div>'//第三tab
	chat+='</div>';
	chat+='</div>';
	//$('body').append(chat);//加载聊天插件
	
	
	$('.chat-status').live("click",function(){//展开
		$('.chat-mini').hide();
		$('.chat-side').slideDown(200);
		tid=$('.tab-body>.tab-list:visible').attr("tid");
		loadUser(tid);
	})
	$('.chat-title>.close').live("click",function(){
		$('.chat-side').slideUp(300,function(){
			$('.chat-mini').slideDown(200);
		});
		$('.msg-window').slideUp(300);
	})
	$('.tab li').live({
		"mouseover":function(){$(this).css("color","#666")},
		"mouseout":function(){$(this).removeAttr("style")},
		"click":function(){
			tid=$(this).attr("tid")
			$('.tab>li').removeClass("now");
			$(this).addClass("now");
			$('.tab-body>.tab-list').removeAttr("style");
			$('.tab-body>.tab-list[tid='+tid+']').css("display","block");
			loadUser(tid);
		}
	})
	$('.tab-list:visible>ul>li>a').live({//点击头像
		"click":function(){
			id=$(this).attr("uid")//获取属性
			kind=$(this).attr("kind")//获取属性
			$('.tab-body>.tab-list:visible>ul>li>a').removeClass("on");
			$(this).addClass("on");
			name=$(this).children("span.name").text();
			newWin(id,kind,name);//用户标识，分类，名称
		}
	})
	$('.msg-window>.msg-title>.close').live({//点击关闭
		"click":function(){
			$(this).parents(".msg-window").hide(); //此处使用隐藏
		}
	})
	$('.face').live("click",function(){//点击表情
		var face='';
		currt=this
		$.ajax({//ajax加载表情
             url: path+"inc/face.xml",
             dataType:"xml",
			 error: function(){
				alert("error");
			},
            success:function(xml){
				if($(currt).next('.faceDiv').length>0){
					$(currt).next('.faceDiv').toggle();
				}else{
					face+='<div class="faceDiv"><i></i><ul>';
					$(xml).find("msglist > msg").each(function(){
						face+='<li><img src="'+path+$(this).find("img").text()+'" title="'+$(this).find("name").text()+'"></li>';
					});
					face+='</ul></div>';
					$(currt).after(face);
				}
		  	}
	   	})
		$('body').click(function(e){//隐藏表情
			var clickDOM = $(e.target);
			var a = clickDOM.parents('.faceDiv');
			if(a.hasClass("faceDiv")){//点击表情
				if(clickDOM.is("img")){//如果点中表情
					faceText=clickDOM.attr("title");
					txtAreaObj=clickDOM.parents(".msg-act").next(".msg-text").find("textarea.msg-text-textarea");
					txtAreaObj.setCaret();
					txtAreaObj.insertAtCaret(faceText);
					txtAreaObj.focus();
				}
				a.hide();
				$('body').unbind('click');
				$('.faceDiv').unbind('mouseout');
			}else if(clickDOM.hasClass("faceDiv")){}else{//点击外部
				a.hide();
				$('body').unbind('click');
				$('.faceDiv').unbind('mouseout');
			}
		})
	})
	
	$('.msg-text-textarea').live({
		"focus":function(){
			currt=this;
			$('body').keydown(function(e){
				var key = e.which;
				if (key == 13){
					$(".send").click(); ////////////////////////////////////////按键过快会导致浏览器崩溃不知道怎么解决，望高人指点。
					e.preventDefault();
				}
			})
		},
		"blur":function(){$('body').unbind('keydown');}		
	})
	
	$('.send').live("click",function(){
		currt=this
		txtAreaObj=$(this).parent("div").prev("textarea.msg-text-textarea").val();
		if(txtAreaObj!=""){
			var regx=/(\[[\u4e00-\u9fa5]*\w*\])/g;//正则查找"[]"格式
			var rs=txtAreaObj.match(regx)
			if(rs!=null){
				$.ajax({//ajax加载表情
					 url: path+"inc/face.xml",
					 dataType:"xml",
					 error: function(){
						alert("error");
					},
					success:function(xml){
						for( i = 0; i < rs.length; i++) {
							$(xml).find("msglist > msg").each(function(){
								if($(this).find("name").text()== rs[i]){
									var t = "<img src='"+path+$(this).find("img").text()+"' />";
									txtAreaObj = txtAreaObj.replace(rs[i],t);
								}
							});
						}
					$(currt).parents(".msg-window").find(".msg-body>.scrollDiv").append('<div class="cle cf"><div class="own"><i></i>'+txtAreaObj+'</div></div>');
		  			}
	   			})
			}else{
				$(currt).parents(".msg-window").find(".msg-body>.scrollDiv").append('<div class="cle cf"><div class="own"><i></i>'+txtAreaObj+'</div></div>');
				$(currt).parents(".msg-window").find(".msg-body").scrollTop($(currt).parents(".msg-window").find(".msg-body>.scrollDiv").height()); 
			}
		}else{
			$(currt).next(".error-msg").fadeIn(500).html("<i></i>发送消息为空");
			 setTimeout(function(){$('.error-msg').fadeOut(500)},2000);
		}
		$(this).parent("div").prev("textarea.msg-text-textarea").val("").focus();//获取焦点
	})
	

	
	
	
	
	
	
	
})//$end

function loadUser(tid){//加载用户
	
	//ajax读取
	var list='';
	list+='<ul>';
	if(tid==1){//房间（群）room
		list+='<li><a href="javascript:;" class="ch-m" uid="1" kind="room"><img src="img/200.jpg" class="head"><span class="name">GUI交流房</span></a></li>'//uid为每个单独id  kind为类型（房间，好友，在线）
		list+='<li><a href="javascript:;" class="ch-m" uid="2" kind="room"><img src="img/200.jpg" class="head"><span class="name">ICO交流房</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="3" kind="room"><img src="img/200.jpg" class="head"><span class="name">WEB交流房</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="4" kind="room"><img src="img/200.jpg" class="head"><span class="name">HTML交流房</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="4" kind="room"><img src="img/200.jpg" class="head"><span class="name">JQUERY交流房</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="5" kind="room"><img src="img/200.jpg" class="head"><span class="name">java交流房</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="6" kind="room"><img src="img/200.jpg" class="head"><span class="name">C#交流房</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="7" kind="room"><img src="img/200.jpg" class="head"><span class="name">IOS交流房</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="8" kind="room"><img src="img/200.jpg" class="head"><span class="name">android交流房</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="5" kind="room"><img src="img/200.jpg" class="head"><span class="name">WP8交流房</span></a></li>'
	}else if(tid==2){//好友 friend
		list+='<li><a href="javascript:;" class="ch-m" uid="1" kind="friend"><img src="img/200.jpg" class="head"><span class="name">阿桑奇</span></a></li>'//uid为每个单独id  kind为类型（房间，好友，在线）
		list+='<li><a href="javascript:;" class="ch-m" uid="2" kind="friend"><img src="img/200.jpg" class="head"><span class="name">昵称已到期</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="3" kind="friend"><img src="img/200.jpg" class="head"><span class="name">谢谢谢小果</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="4" kind="friend"><img src="img/200.jpg" class="head"><span class="name">求肉肉</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="5" kind="friend"><img src="img/200.jpg" class="head"><span class="name">秘密</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="6" kind="friend"><img src="img/200.jpg" class="head"><span class="name">随风</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="7" kind="friend"><img src="img/200.jpg" class="head"><span class="name">菠菜</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="8" kind="friend"><img src="img/200.jpg" class="head"><span class="name">世良插画</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="5" kind="friend"><img src="img/200.jpg" class="head"><span class="name">秘密</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="6" kind="friend"><img src="img/200.jpg" class="head"><span class="name">随风</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="7" kind="friend"><img src="img/200.jpg" class="head"><span class="name">菠菜</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="8" kind="friend"><img src="img/200.jpg" class="head"><span class="name">世良插画</span></a></li>'
	}else if(tid==3){//在线 online
		list+='<li><a href="javascript:;" class="ch-m" uid="2" kind="online"><img src="img/200.jpg" class="head"><span class="name">昵称已到期</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="3" kind="online"><img src="img/200.jpg" class="head"><span class="name">谢谢谢小果</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="4" kind="online"><img src="img/200.jpg" class="head"><span class="name">求肉肉</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="5" kind="online"><img src="img/200.jpg" class="head"><span class="name">秘密</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="6" kind="online"><img src="img/200.jpg" class="head"><span class="name">随风</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="7" kind="online"><img src="img/200.jpg" class="head"><span class="name">菠菜</span></a></li>'
		list+='<li><a href="javascript:;" class="ch-m" uid="8" kind="online"><img src="img/200.jpg" class="head"><span class="name">世良插画</span></a></li>'
	}else{
		list+='<li><center>参数错误，加载失败</center></li>'//错误传参
	}
	
	list+='</ul>';
	
	
	$('.tab-body>.tab-list[tid='+tid+']').html(list);
}
function newWin(id,kind,name){//聊天窗口
	if($('.msg-window[uid='+id+'][kind='+kind+']').length>0){//判断窗口是否已经存在
		$('.msg-window').hide();
		$('.msg-window[uid='+id+'][kind='+kind+']').show();
	}else{
		$('.msg-window').hide();
		var win='';
		win+='<div class="msg-window" uid="'+id+'" kind="'+kind+'">';
		win+='<div class="msg-title" id="msg-title cf"><span class="close">&times</span><img src="img/200.jpg" width="50" height="50" class="msg-head">'+name+'</div>';
		win+='<div class="msg-body"><div class="scrollDiv">';
		win+='<div class="cle cf"><div class="oth"><i></i>你好，我叫獠麝鸡。<img src="img/face/2.gif"></div></div>';
		win+='</div></div>';
		win+='<div class="msg-act"><ul class="msg-act-ul"><li class="msg-act-ul-li"><a href="javascript:;" class="face"><span class="face-span"></span>表情</a></li></ul></div>';
		win+='<div class="msg-text"><textarea class="msg-text-textarea"></textarea><div><a href="javascript:;" class="send">发送</a><div class="error-msg"></div></div></div>';
		win+='</div>';
		$('.chat-side').after(win);
		$('.msg-window[uid='+id+'][kind='+kind+']').find(".msg-text>textarea.msg-text-textarea").focus();//获取焦点
	}
}
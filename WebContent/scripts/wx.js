/*
 * 使用例子
 			//微信相关
			var wx = new Wx.Param();
			var data = wx.getDATA('##DATA##', {a:11231231});
			alert(data.a);

			wx.getURL("##URL##", [{src:'personal-center.jsp'}]);
			
			alert(wx.getGZHHASH('#GZHHASH##'));
			alert(wx.getOPENID());
			
			
			//统计相关
			var stat = new Wx.Stat();
			stat.report('7-1', '预约试驾', gzhhash, openid, data);
			
			
			
			
 */


var TESTING_GZHHASH = '-2042484612';
var TESTING_GZHTYPE = 'DIST';
var TESTING_OPENID = '999999999';
var Wx = Wx || {};
Wx.Param = function(){};
Wx.Param.prototype = {
		init:function(){},
		isWeiXin:function(){
			var ua = navigator.userAgent.toLowerCase();
		    if(ua.match(/MicroMessenger/i)=="micromessenger") {
		        return true;
		    } else {
		        return false;
		    }
		},
		getGZHHASH:function(hashTk){
			var self = this;
			if(self.isWeiXin()){
				if(hashTk=='##GZHHASG##'){
					return TESTING_GZHHASH;
				}
				return hashTk;
			}else{
				return TESTING_GZHHASH;
			}
		},
		getURL:function(urls, def){
			if(urls == '##URL##'){
					return def;
			}else{
				var retUrls = [];
				var obj = JSON.parse(urls);
				if(typeof MODULE_URLS != 'undefined'){
					var us = JSON.parse(MODULE_URLS);
					for(var i=0;i<us.length;i++){
						if(typeof us[i].src != 'undefined' && us[i].src!=''){
							retUrls.push(us[i]);
						}else{
							retUrls.push(obj[i]);
						}
					}
				}else{
					retUrls = obj;
				}
				return retUrls;
			}
		},
		getDATA: function(data, def){
			var self = this;
			//if(self.isWeiXin()){
				if(data == '##DATA##'){
					//alert('DATA has not been replaced!');
					return def;
				}else{
					//var obj = JSON.parse(data);
					var tempstr = data.replace(/style="/g, "style='");
                    tempstr = tempstr.replace(/">/g, "'>");

                    data = tempstr;
                    var obj = eval("("+data+")"); //JSON.parse(data);
					
					
					return obj;
				}
			//}else{
			//	return def;
			//}
		},
		getGZHTYPE: function(){
			var self = this;
			if(self.isWeiXin()){
				if(window.GZHTYPE!='DLR'){
					return 'DIST';
				}else{
					return 'DLR';
				}
			}else{
				return TESTING_GZHTYPE;
			}
		},
		getOPENID: function(){
			var self = this;
			if(self.isWeiXin()){
				return window.openid;
			}else{
				var opid = window.openid;
				if(opid!=null&&opid!=''){
					Util.Cookie.setCookie('tmpopenid', opid);
				}else{
					opid = Util.Cookie.getCookie('tmpopenid');
					if(opid == ''){
						if(typeof TESTING_OPENID == 'undefined'){
							opid = Util.Random.getRndInt(100000000000, 9999999999999);
						}else{
							opid = TESTING_OPENID;
						}
						Util.Cookie.setCookie('tmpopenid', opid);
					}
				}
				return opid;
			}
		}
};
Wx.User = function(){};
Wx.User.prototype = {
	getUserLocation: function(callback) {
		var wx = new Wx.Param();
		var openid = wx.getOPENID();
		$.ajax({
			type : 'POST',
			url : ctx + "/user/getUserLocation",
			data : {
				openid:openid
			},
			dataType : "json",
			success : function(result) {
				console.log(result);
				if (result.success==true) {
					callback(result.result);
				}else{
					callback(null);
				}
			}
		});
	},
	getUserInfo: function(callback){
		var wx = new Wx.Param();
		var openid = wx.getOPENID();
		$.ajax({
			type : 'POST',
			url : ctx + "/user/getUserInfo",
			data : {
				openid:openid
			},
			dataType : "json",
			success : function(result) {
				console.log(result);
				if (result.success==true) {
					callback(result.result);
					/*
					if(result.result.user.memberLevel != '1'){
						renderHtml(result.result);	
						registered = true;
					}else{
						registered = false;
					}
					*/
				}else{
					callback(null);
					
					//registered = false;
				}
			}
		});
	},
	getUserCarInfo: function(callback) {
		var wx = new Wx.Param();
		var openid = wx.getOPENID();
		$.ajax({
			type : 'POST',
			url : ctx + "/user/getUCPInfo",
			data : {
				openId: openid
			},
			dataType : "json",
			success : function(result) {
				console.log(result);
				if (result.success==true) {
					callback(result.result[0]);
					/*
					if(result.result.user.memberLevel != '1'){
						renderHtml(result.result);	
						registered = true;
					}else{
						registered = false;
					}
					*/
				}else{
					callback(null);
					
					//registered = false;
				}
			}
		});
	},
	regUser: function(data, callback){
		
	},
};

Wx.Request = function(){};
Wx.Request.prototype = {
	postData:function(url, data, callback){
		var purl = '';
		if( typeof ctx != 'undefined' ){
			purl = ctx+'/'+url;
		}else{
			purl = url;
		}
		if(url.indexOf('http') == 0){
			purl = url;
		}
		$.post(purl, data, function(obj){
			callback(obj);
		});
	}
};

Wx.Stat = function(){};
Wx.Stat.prototype = {
	init:function(){
	},
	report: function(module, alias, gzhhash, openid, data){
		console.log('reporting sent.');
		var rq = new Wx.Request();
		rq.postData('http://115.28.20.72/gtmc_wx/stat/' + module + '/' + alias + '/' + gzhHash + '/' + openid + '/?stat=true', data, function(){});
	},
};
Util.DatePicker = function(){};




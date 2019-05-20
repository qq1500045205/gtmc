//------------------------------------
/*
 * 	使用例子
 * 
 			function pointback(obj){
				obj.lantitude;
				obj.longtitude;
			}
			function cityback(location){
				location.city;
				location.province;
				location.address;
				location.district;
			}
			
			//获取地理位置 
			var geo = new Util.Geo();
			geo.getLocalPoint(pointback);
			geo.getLocalCity(cityback);
			geo.getCityByPoint({"longitude":116.6131332,"latitude":39.9251824}, function(obj){
				obj.province;
			});
			
			//本地存取
			var storage = new Util.Storage();
			storage.set('key1', '{"val":"1"}');
			var obj = storage.get('key1');
			
			
			storage.set('key1', {"val":"1"});
			var obj = storage.get('key1');
			obj.val;
			
			

			//页面跳转
			Util.Browser.jump('action.html', '7-01');
			Util.Browser.jump('action.html', ''7-01', {"v":"1"});
			//--
			in action.html get data
			var obj = Util.Browser.getIntent();
			obj.v;
			
			//统计接口
			var stat = new Util.Stat();
			stat.report('cp-7-1', '预约试驾', {key: val});
			
			//表单
			var form = new Util.Form(formname);
			form.enableInput(['ida','idb']); //使IDS对应的控件可用
			form.disableInput(['ida','idb']); //使用ids对应的控件不可用
			form.setValues({
				"a","b" //设置id=a的控件对应的值为b
			});
			form.getValues() //返回表单中所有id对应的值, 以object方式返回
			
 */
/*
JS Util
author WangShaobo@tlan.com.cn
1.1.0
*/
/*-----------------------------------------------------------*/

var BAIDU_MAP_URL = 'http://api.map.baidu.com/api?ak=goT4KEnxiuN4FEQ4mF3XUdcv&v=2.0';
/*-----------------------------------------------------------*/
var Util = Util || {};
Util.Browser = function() {};
Util.Browser.jump = function(tourl, from, data) {
 
    var storage = new Util.Storage();
    if (data != 'undefined') {
        storage.set('intent', data);
    } else {
        storage.set('intent', null);
    }
 
    //storage.set('entrance', from);
    var param = '';
    if( from == null || from == '' ){
    	
    }else{
	    if(tourl.indexOf('&')>0 || tourl.indexOf('=') > 0){
	    	param = '&entrance='+from;
	    }else{
	    	param = '?entrance='+from;
	    }	
    }
    window.location = tourl + param;
};
Util.Browser.getEntrance = function() {
	/*
	 * 改为URL形式
    var storage = new Util.Storage();
    return storage.get('entrance');
    */
	var param = new Util.Param();
	var entrance = param.get('entrance');
	return entrance;
};
Util.Browser.getIntent = function() {
    var storage = new Util.Storage();
    return storage.get('intent');
};
Util.Window = function() {};
Util.Window.getHeight = function() {
    return window.innerHeight;
};
Util.Window.getWidth = function() {
    return window.innerWidth;
};
Util.Sys = function() {
    this.alert = function(msg) {
        alert(msg);
    };
};
Util.Sys.IncludeJS = function(file, callback) {
    var script = document.createElement('script');
    script.defer = 'defer';
    script.async = false;
    script.onload = function() {
        if (callback) {
            callback();
        }
    };
    script.src = file;
    document.head.appendChild(script);
};
Util.Sys.IncludeCSS = function(file, callback) {
    var link = document.createElement('link');
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.href = file;
    link.media = 'all';
    link.onload = function() {
        if (callback) {
            callback();
        }
    };
    document.head.appendChild(link);
};

Util.Sys.prototype = {};
Util.Random = {};
Util.Random.getRndInt = function(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
};
Util.Geo = function() {
    var self = this;
    self.init();
};
Util.Geo.prototype = {
    init: function() {
    },
    getLocalPoint: function(callback, errorCallback) {
    	/*
        function onGeoSuccess(location) {
        	var point = {
                    "longitude": location.coords.longitude,
                    "latitude": location.coords.latitude
            };
            alert("longitude: " + location.coords.longitude + " latitude: " + location.coords.latitude);
            if (typeof callback !== "undefined") {
                callback(point);
            } else {
                alert("callback function is not valid");
            }
    	}
    	// Error Callback
    	function onGeoError(message) {
    	    console.log("error",message);
    	    errorCallback(message);
    	}
    	//Locate by IP on load
    	// geolocator.locateByIP(onGeoSuccess, onGeoError, 0);
    	var html5Options = { enableHighAccuracy: true, timeout: 10000, maximumAge: 0 };
    	geolocator.locate(onGeoSuccess, onGeoError, 1, html5Options);
    	*/
        console.log('requiring local point!');
        
        navigator.geolocation.getCurrentPosition(function(location) {
            var point = {
                "longitude": location.coords.longitude,
                "latitude": location.coords.latitude
            };
            console.log("longitude: " + location.coords.longitude + " latitude: " + location.coords.latitude);
            if (typeof callback !== "undefined") {
                callback(point);
            } else {
                alert("callback function is not valid");
            }
            console.log('got location point');
        }, function(e){
        	console.log(e);
        	alert("无法获取具体位置，请确认开启gps，再重试！");
        }, {
        	enableHighAccuracy: true,
        	timeout: 60000,
        	maximumAge: 60000,
        });
    },
    getCityByPoint: function(point, callback) {
        var self = this;
        self.initBMap(function() {
            //var map = new BMap.Map("l-map");
            var geotemp = new BMap.Geocoder();
            geotemp.getLocation(new BMap.Point(point.longitude, point.latitude),
            function(result) {
                callback({
                    "province": result.addressComponents.province,
                    "city": result.addressComponents.city,
                    "district": result.addressComponents.district,
                    "address": result.address

                });
                console.log('province: ' + result.addressComponents.province);
                console.log('city: ' + result.addressComponents.city);
                console.log('district: ' + result.addressComponents.district);
                console.log('address: ' + result.address);
            });
        });
    },
    getLocalCity: function(callback) {
        var self = this;

        self.initBMap(function() {
            self.getLocalPoint(function(point) {
                self.getCityByPoint(point, callback);
            });
        });
    },
    initBMap: function(callback) {
        window.baidu_callback = function() {
            console.log('baidu map is loaded!');
            window.bdmaploaded = true;
            callback();
        };
        //baidu_callback();
        if (window.bdmaploaded == null || window.bdmaploaded == 'undefined') {
            Util.Sys.IncludeJS(BAIDU_MAP_URL + '&' + 'callback=baidu_callback',
            function() {
                console.log('js file loaded!');
            });
        } else {
            callback();
        }
    },
    createMap: function(id, center) {
        var map = new Util.BaiduMap(id, center);
        return map;
    }
};
Util.BaiduMap = function(id, center) {
    this.bdmap = new BMap.Map(id);
    if (center == undefined) {
        alert('center should be given');
    } else {
        var point = new BMap.Point(center[0], center[1]);
        this.bdmap.centerAndZoom(point, 15);
    }
    this.bdmap.addControl(new BMap.NavigationControl());
};
Util.BaiduMap.prototype = {
    addMark: function(list, latk, lonk) {
        /**
		  * 使用方法:
		  *  addMark([ [lat, lng], [lat, lng], [lat, lng] ]
		  *  addMark([{x:1, y:2},{x:2,y:3}, {x:3, y:3}], 'x', 'y'} 
		  */
        for (var i = 0; i < list.length; i++) {
            var item = list[0];
            var lat = 0,
            lng = 0;
            if (latk == undefined) {
                lat = item[0];
                lng = item[1];
            } else {
                lat = item[latk];
                lng = item[lonk];
            }
            var point = new BMap.Point(lat, lng);
            var marker = new BMap.Marker(point);
            this.bdmap.addOverlay(marker);
            
    	    //百度地图API追加信息展示功能  Start addby limengjie
            var showinfo="";
            showinfo=item[2];
            //alert(showinfo);
    	    var infoWindow = new BMap.InfoWindow(showinfo); // 创建信息窗口对象
    	    marker.addEventListener("click",
    	    function() {
    	        this.openInfoWindow(infoWindow);
    	        //图片加载完毕重绘infowindow
    	        /*document.getElementById('imgDemo').onload = function() {
    	            infoWindow.redraw(); //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
    	        };*/
    	    });
    	    //百度地图API追加信息展示功能  End
            
            
            
            
            
            
            
            
        }
    },
    drivingSearch: function(address_from, address_to) {
    	address_from = new BMap.Point(address_from[0], address_from[1]);
    	address_to = new BMap.Point(address_to[0], address_to[1]);
        var self = this;
        var driving = new BMap.DrivingRoute(self.bdmap, {
            renderOptions: {
                map: self.bdmap,
                autoViewport: true
            }
        });
        driving.search(address_from, address_to);
    },
    walkingSearch: function(address_from, address_to){
    	address_from = new BMap.Point(address_from[0], address_from[1]);
    	address_to = new BMap.Point(address_to[0], address_to[1]);
    	var self = this;
    	var driving = new BMap.DrivingRoute(self.bdmap, {
            renderOptions: {
                map: self.bdmap,
                autoViewport: true
            }
        });
        driving.search(address_from, address_to);
    }
};
Util.Param = function() {};
Util.Param.prototype = {
    get: function(sname) {
    	/*
    	 * 如果找到则返回
    	 * 没有找到则返回null
    	 * 
    	 */
        var sval = "";
        var retl = {};
        var params = window.location.search.substring(1);
        params = params.split("&");
        if( params.length > 0 ){
	        for (var i = 0; i < params.length; i++) {
	            temp = params[i].split("=");
	            if ([temp[0]] == sname) {
	                sval = temp[1];
	            }
	            retl[temp[0]] = temp[1];
	        }
	        if (sname == undefined) {
	            return retl;
	        } else {
	            return sval;
	        }
        }else{
        	return null;
        }
    }
};
Util.Storage = function() {};
Util.Storage.prototype = {
    init: function() {
        if (window.localStorage == 'undefined') {
            alert('browser doesnot support local storage');
        }
    },
    set: function(key, val) {
        if (key == 'key') {
            alert('key is keyword.');
        }
        window.localStorage['' + key + ''] = JSON.stringify(val);
    },
    get: function(key) {
        if (window.localStorage[key] == undefined) {
            return null;
        }
        var obj = JSON.parse(window.localStorage[key]);
        return obj;
    }
};
Util.Cookie = function() {};
Util.Cookie.setCookie = function(cname, cvalue) {
    var d = new Date();
    var exdays = 5;
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toGMTString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
};
Util.Cookie.getCookie = function(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
};

/*--------------------------------------------------------------------*/

Util.Form = function(formname) {
    var setting = {
        "formname": formname
    };
    this.form = {};
    this.form = $("form[name='" + formname + "']");
};
Util.Form.init = function() {

};
Util.Form.prototype = {
    disableInputs: function(ids) {
        if (ids == null || ids == undefined) {
            $(this.form).find('input, submit, textarea, select').each(function() {
                $(this).attr('disabled', true);
            });
        } else {
            for (var i = 0; i < ids.length; i++) {
                $('#' + ids[i]).attr('disabled', true);
            }
        }
    },
    enableInputs: function(ids) {
        if (ids == 'undefined') {
            $(this.form).find('input, submit, textarea, select, checkbox').each(function(obj) {
                $(obj).attr('disabled', false);
            });
        } else {
            for (var i = 0; i < ids.length; i++) {
                $('#' + ids[i]).attr('disabled', false);
            }
        }
    },
    hideControls: function(ids) {
        for (var i = 0; i < ids.length; i++) {
            $('#' + ids[i]).css('display', 'none');
        }
    },
    setValues: function(opts) {
        for (var key in opts) {
            if ($('#' + key).length && $('#' + key).get(0).nodeName == 'INPUT') {
                $('#' + key).val(opts[key]);
            } else if ($('#' + key).length && $('#' + key).get(0).nodeName == 'SELECT') {
                var found = false;
                $('#' + key + ' > option').each(function() {
                    if (this.value == opts[key]) {
                        $(this).attr("selected", "selected");
                        found = true;
                    }
                });
                if (found == false) {
                    $('#' + key).append($("<option></option>").attr("value", opts[key]).text(''));
                }
            } else {
                $('#' + key).html(opts[key]);
            }

        }
    },
    getValues: function() {
        var values = {};
        //console.log(this.form.find('input'));
        $(this.form).find('input[type="text"], input[type="hidden"],  input[type="date"], textarea, select').each(function() {
            //console.log(this);
            var id = $(this).attr('id');
            var val = $(this).val();
            values[id] = val;
        });
        $(this.form).find('input[type="checkbox"]').each(function() {
            var id = $(this).attr('id');
            var val = $(this).is(":checked");
            values[id] = val;
        });
        console.log(values);
        return values;
    },
    setOptions: function(id, opts, kykey, vlkey) {
        $('#' + id).html('');
        for (var key in opts) {
            if (kykey != undefined && vlkey != undefined) {
                $('#' + id).append($("<option></option>").attr("value", opts[key][kykey]).text(opts[key][vlkey]));
            } else {
                $('#' + id).append($("<option></option>").attr("value", key).text(opts[key]));
            }
        }
    }
};
/*--------------------------------------------------------------------*/
Util.Document = function() {};
Util.Document.prototype = {
    hide: function(ids) {
        for (var i = 0; i < ids.length; i++) {
            $('#' + ids[i]).css('display', 'none');
        }
    }
};
Util.Timer = function() {};
Util.Timer.play = function(interval, times, callback) {

    window._interval = interval;
    window._times = times;
    window._counter = 0;
    window._callback = callback;

    window._timeProc = function() {
        window._counter++;
        if (window._times-->0) {
            window._callback(window._counter, window._times);
        } else {
            clearInterval(window._timer);
        }
    };
    window._timer = setInterval(function() {
        window._timeProc();
    },
    window._interval);
};
/*--------------------------------------------------------------------*/

function array_merge() {
    var args = Array.prototype.slice.call(arguments),
    argl = args.length,
    arg,
    retObj = {},
    k = '',
    argil = 0,
    j = 0,
    i = 0,
    ct = 0,
    toStr = Object.prototype.toString,
    retArr = true;

    for (i = 0; i < argl; i++) {
        if (toStr.call(args[i]) !== '[object Array]') {
            retArr = false;
            break;
        }
    }

    if (retArr) {
        retArr = [];
        for (i = 0; i < argl; i++) {
            retArr = retArr.concat(args[i]);
        }
        return retArr;
    }

    for (i = 0, ct = 0; i < argl; i++) {
        arg = args[i];
        if (toStr.call(arg) === '[object Array]') {
            for (j = 0, argil = arg.length; j < argil; j++) {
                retObj[ct++] = arg[j];
            }
        } else {
            for (k in arg) {
                if (arg.hasOwnProperty(k)) {
                    if (parseInt(k, 10) + '' === k) {
                        retObj[ct++] = arg[k];
                    } else {
                        retObj[k] = arg[k];
                    }
                }
            }
        }
    }
    return retObj;
}
/*-------------------------------scroll------------------------------------------*/
Util.Scroller = function() {};
Util.Scroller.prototype = {
	scrollTo : function(id) {
		$("#" + id).ScrollTo(500);
	}
}
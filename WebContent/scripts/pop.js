/** 
 * 确认提示框 
 */  
var ConfirmPop = function(config, callback) {  
    this.config = {  
        title : "提示",  
        msg : "确认此操作吗？",  
        ok : "确定",  
        cancel : "取消"  
    };  
    this.setConfig(config);  
    this.id = new Date().getTime() + "-" + Math.ceil(Math.random() * 1000);  
    this.callback = {  
        ok : callback.ok,  
        cancel : callback.cancel  
    };  
    this.windowObj =getWindowObj();  
    this.init();  
}  
function getWindowObj(){  
    var _windowObj = null;  
    if (!get("id_confirmPop")) {  
        _windowObj = new WindowObj();  
    }  
    return _windowObj;  
}  
// 扩展ConfirmPop对象  
ConfirmPop.prototype = {  
    init:function() {  
    },  
    setConfig:function(config) {  
        if (config) {  
            if (config.title) {  
                this.config.title = config.title;  
            };  
            if (config.msg) {  
                this.config.msg = config.msg;  
            }  
        }  
    },  
    hide:function() {  
        this.windowObj.hide();  
    },  
    show:function() {  
        this.windowObj.show(this.config, this.callback);  
    }  
};  
var WindowObj = function() {  
    this.callback = {  
        ok : null,  
        cancel : null  
    };  
    this.el = '<div class="tips_top">'  
            + '<h2 class="fz_18" id="id_confirm_title">'  
            + '</h2>'  
            + '<p class="pt10 " id="id_confirm_msg">'  
            + '</p>'  
            + '</div>'  
            + '<div class="tips_btn clear">'  
            + '<a class="action_btn btn_long  fl" href="javascript:void(0);" id="id_confirm_ok">'  
            + '</a>'  
            + '<a class="action_btn btn_long  fr" href="javascript:void(0);" id="id_confirm_cancel">'  
            + '</a>' + '</div>';  
    this.init();  
};  
  
WindowObj.prototype = {  
    init : function() {  
        var el = document.createElement("div");  
        el.className = "tips_div confirm_tips_layer";  
        el.id = "id_confirmPop";  
        el.innerHTML = this.el;  
        get("id_container").appendChild(el);  
        var self = this;  
        setclick("id_confirm_ok", function() {  
                    if (typeof(self.callback.ok) == "function") {  
                        self.callback.ok();  
                    }  
                });  
        setclick("id_confirm_cancel", function() {  
                    self.hide();  
                    if (typeof(self.callback.cancel) == "function") {  
                        self.callback.cancel();  
                    }  
                });  
    },  
    _show : function() {  
        fullmask(true);  
        if (getScrollTop() > 0) {  
            get("id_confirmPop").style.top = getClientHeight() / 2  
                    + getScrollTop() + "px";  
        } else {  
            get("id_confirmPop").style.top = "50%";  
        }  
  
        setdisplay("id_confirmPop", true);  
    },  
    hide : function() {  
        fullmask(false);  
        setdisplay("id_confirmPop", false);  
    },  
    show : function(config, callback) {  
        get("id_confirm_title").innerHTML = config.title;  
        get("id_confirm_msg").innerHTML = config.msg;  
        get("id_confirm_ok").innerHTML = config.ok;  
        get("id_confirm_cancel").innerHTML = config.cancel;  
        this.callback.ok = callback.ok;  
        this.callback.cancel = callback.cancel;  
        this._show();  
    }  
};  
// 创建遮罩  
function fullmask(isShow) {  
    if (!get("id_full_marsk_layer")) {  
        var el = document.createElement("div");  
        el.className = "folder_layer full_mask_layer";  
        el.id = "id_full_marsk_layer";  
        get("id_container").appendChild(el);  
    }  
    if (isShow) {  
        setdisplay("id_full_marsk_layer", true);  
    } else {  
        setdisplay("id_full_marsk_layer", false);  
    }  
}  
// 设置显示  
function setdisplay(id, t, isInline) {  
    var tempElById = get(id);  
    if (tempElById) {  
        tempElById.style.display = t ? "block" : "none";  
        if (t && typeof(isInline) != "undefined") {  
            if (isInline === true) {  
                tempElById.style.display = "inline-block";  
            } else {  
                tempElById.style.display = "";  
            }  
  
        }  
    }  
}  
  
// 获取元素  
function get(id) {  
    return document.getElementById(id);  
}  
  
/** 
 * 取窗口滚动条高度 
 */  
function getScrollTop() {  
    var scrollTop = 0;  
    if (document.documentElement && document.documentElement.scrollTop) {  
        scrollTop = document.documentElement.scrollTop;  
    } else if (document.body) {  
        scrollTop = document.body.scrollTop;  
    }  
    return scrollTop;  
}  
  
/** 
 * 取窗口可视范围的高度 
 */  
function getClientHeight() {  
    var clientHeight = 0;  
    if (document.body.clientHeight && document.documentElement.clientHeight) {  
        var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight)  
                ? document.body.clientHeight  
                : document.documentElement.clientHeight;  
    } else {  
        var clientHeight = (document.body.clientHeight > document.documentElement.clientHeight)  
                ? document.body.clientHeight  
                : document.documentElement.clientHeight;  
    }  
    return clientHeight;  
}  
  
// 设置按钮的click事件  
function setclick(id, fn) {  
    if (get(id)) {  
        get(id).onclick = fn;  
    }  
}  
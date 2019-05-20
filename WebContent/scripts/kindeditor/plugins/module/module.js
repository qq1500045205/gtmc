/*******************************************************************************
* KindEditor - WYSIWYG HTML Editor for Internet
* Copyright (C) 2006-2011 kindsoft.net
*
* @author Roddy <luolonghao@gmail.com>
* @site http://www.kindsoft.net/
* @licence http://www.kindsoft.net/license.php
*******************************************************************************/

KindEditor.plugin('module', function(K) {
	var self = this, name = 'module';
	var location = (window.location+'').split('/'); 
	var basepath =location[0]+'//'+location[2]+'/'+location[3]; 
	self.plugin.module = {
		edit : function(id) {
			var lang = self.lang(name + '.'),
				html = '<div style="padding:20px;">' +
					//url
					'<div class="ke-dialog-row">' +
					'<label for="keUrl" style="width:60px;">' + lang.moduleName + '</label>' +
					'<input class="ke-input-text" type="text" id="moduleName" name="moduleName" value="" style="width:260px;" /></div>' +
					//type
					'<div class="ke-dialog-row"">' +
					'<label for="keType" style="width:60px;">' + lang.moduleType + '</label>' +
					'<select id="moduleType" name="moduleType"></select>' +
					'</div>' +
					'</div>',
				dialog = self.createDialog({
					name : name,
					width : 450,
					title : self.lang(name),
					body : html,
					yesBtn : {
						name : self.lang('yes'),
						click : function(e) {
							var moduleName = K.trim(urlBox.val());
							var moduleType = K.trim(typeBox.val());
							if(moduleName){			
								 path = basepath +"/wx/cnthtml/"+moduleType;
								 html ='<a href="'+path+'">'+moduleName+"</a>";
								 self.insertHtml(html).hideDialog().focus();
							}else {
								alert(self.lang("invalidModuleName"));
							}
						}
					}
				}),
				div = dialog.div,
				urlBox = K('input[name="moduleName"]', div),
				typeBox = K('select[name="moduleType"]', div);
				
				var cmd = self.cmd.selection();
				urlBox.val(cmd.range.html());
			

			 $.ajax({
				 type:"POST",
				 url:basepath+"/ajax/combo?type=wxModuleContent",
				 async: false,
				 success:function(msg){
					for(var i=0;i<msg.length;i++){
						if(msg[i].contentPage){
							typeBox[0].options[i] = new Option(msg[i].moduleName, msg[i].contentPage);
						}
					}
				 }
			  });
			
			urlBox[0].focus();
			urlBox[0].select();
		}
	};
	self.clickToolbar(name, self.plugin.module.edit);
});

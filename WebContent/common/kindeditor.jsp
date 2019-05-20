<script src="${ctx}/scripts/kindeditor/kindeditor-min.js"></script>
<script src="${ctx}/scripts/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
	//添加富文本编辑器
	function addEditor(textareaId){
		window.editor = KindEditor.create("#"+textareaId, {
			basePath : '${ctx}/scripts/kindeditor/',
			themeType : 'simple',
			langType : 'zh_CN',
			uploadJson : '${ctx}/ajax/kindUploadImage',
			resizeType : 1,
			filterMode:false,//不会过滤HTML代码
			allowImageUpload : true,
			items : [ 'undo', 'redo', '|', 'formatblock', 'fontname', 'fontsize',
					'|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
					'strikethrough', '|', 'justifyleft', 'justifycenter',
					'justifyright', 'justifyfull', 'insertorderedlist',
					'insertunorderedlist', 'indent', 'outdent', '|', 'image','h5player','table', 'hr', 'emoticons', 'link', 'unlink',
					'|', 'preview', 'plainpaste', '|', 'removeformat', 'fullscreen','module' ],
			afterChange : function() {
				this.sync();
			}
		});
	}
	
	//设置内容
	function setContent(content){
		window.editor.html(content);
	}
	
</script>
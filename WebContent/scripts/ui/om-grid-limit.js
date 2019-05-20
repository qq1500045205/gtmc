/*
 * Depends:
 *  om-grid.js
 */
(function($) {
	/**
	 * 为表格增加分页下拉
	 */
    $.omWidget.addInitListener('om.omGrid',function(){
        var grid=this,op = this.options;
		var limit = op.limit;
		var limits=op.limits || [10,20,50,100];
		if($.inArray(limit,limits)==-1){
		   limits.push(op.limit);
		}
		//.pLimit
		var limitDiv = $("<div class='pGroup'><div class='pLimit'><select></select><div></div>");
		var select = $("select",limitDiv);
		$.each(limits,function(i,v){
		    select.append($("<option></option>").attr("value",v).text(v));
		});
		select.val(limit);
		select.change(function(){
		   grid.option("limit",parseInt(select.val()));
           //需要跳转到第1页
			grid.reload(1);		
        });
		$(".pDiv2",this.pDiv).prepend(limitDiv);
    });
})(jQuery);
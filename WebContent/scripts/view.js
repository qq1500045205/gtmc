$(function(){
	if($('.m-form').length){
		//alert($('.m-form  .m-checkbox').length);
		//$('.m-form  .m-checkbox').after('<label class="m-checkbox-label"></label>');
		$('.m-form  .m-checkbox-label').bind('click', function(){
			if($(this).parent().find('.m-checkbox').attr('checked')==null){
				//$(this).parent().find('.m-checkbox').attr('checked', 'checked');
				//$(this).css('backgroundPosition', '2px -17px');
			}else{
				//$(this).parent().find('.m-checkbox').attr('checked', null);
				//$(this).css('backgroundPosition', '2px 5px');
			}
		});
	}
});
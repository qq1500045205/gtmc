<script src="${ctx}/scripts/mobiscroll/dev/js/mobiscroll.core-2.5.2.js" type="text/javascript"></script>
<script src="${ctx}/scripts/mobiscroll/dev/js/mobiscroll.core-2.5.2-zh.js" type="text/javascript"></script>

<link href="${ctx}/scripts/mobiscroll/dev/css/mobiscroll.core-2.5.2.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/mobiscroll/dev/css/mobiscroll.animation-2.5.2.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/scripts/mobiscroll/dev/js/mobiscroll.datetime-2.5.1.js" type="text/javascript"></script>
<script src="${ctx}/scripts/mobiscroll/dev/js/mobiscroll.datetime-2.5.1-zh.js" type="text/javascript"></script>

<!-- S 可根据自己喜好引入样式风格文件 -->
<script src="${ctx}/scripts/mobiscroll/dev/js/mobiscroll.android-ics-2.5.2.js" type="text/javascript"></script>
<link href="${ctx}/scripts/mobiscroll/dev/css/mobiscroll.android-ics-2.5.2.css" rel="stylesheet" type="text/css" />
<!-- E 可根据自己喜好引入样式风格文件 -->

<script>
	var currYear = (new Date()).getFullYear();	
	window.opt={};
	opt.date = {preset : 'date'};
	//opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };
	opt.datetime = {preset : 'datetime'};
	opt.time = {preset : 'time'};
	opt.default = {
		theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式 
        mode: 'scroller', //日期选择模式
		lang:'zh',
        startYear:currYear - 10, //开始年份
        endYear:currYear + 10 //结束年份
	};
	
	window.datepick = function(dateId){
		$("#"+dateId).val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));
	}
	
</script>
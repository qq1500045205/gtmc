package com.nec.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.nec.model.survey.WxSurverSend;
import com.tlan.admin.module.ModuleAction;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;

public class TaskListener extends HttpServlet implements ServletContextListener  {

	private static final long serialVersionUID = -5982724628559911600L;
	ScheduledExecutorService service;

	@Override
	/*
	 * 服务停止
	 * */
	public void contextDestroyed(ServletContextEvent arg0) {
		service.shutdown();
	}

	@SuppressWarnings("unchecked")
	@Override
	/*
	 * 服务启动
	 * */
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("服务启动！！");
		WebApplicationContext  context= WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());   
		IBaseService surverSendService = (IBaseService)context.getBean(IBaseService.class);
		service = Executors.newScheduledThreadPool(20);
		
        String url = "http://" + PropertiesUtil.getProperty(ParamString.BASE_IP)+ context.getServletContext().getContextPath() + "/"
				+ModuleAction.ADMIN_HTML+"service-question.jsp";
		
		//生成需要发送问卷调查的数据，每天5.30开始
		Calendar c = Calendar.getInstance();  
		Date now = new Date();
		Date begindate=null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		try {
			begindate = sdf.parse(sdf2.format(now)+" "+PropertiesUtil.getProperty("send_survery_time"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (now.after(begindate)){
			c.setTime(begindate);
			c.add(Calendar.DAY_OF_MONTH, 1);
		}else{
			c.setTime(begindate);
		}
		
		long sec = (c.getTime().getTime()-now.getTime())/1000;
		System.out.println("sec="+sec);
		//今天10:00或者明天10:00开始计划任务，每隔24小时执行一次
		service.scheduleAtFixedRate(new SendSurverMessage(surverSendService,url), sec, 3600*24, TimeUnit.SECONDS);
		//service.scheduleAtFixedRate(new SendSurverMessage(surverSendService,url), 0, 1000*3600*24, TimeUnit.SECONDS);
	}

}

package com.tlan.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.tlan.contrants.ParamString;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class CreateHtml{

	/**
	 * 
	 * @param ftl
	 *            模板文件
	 * @param htmlName
	 *            html文件名称
	 * @param map
	 *            map保存数据
	 * @param relaPath
	 *            //在这里没有用到
	 * @throws IOException
	 * @throws TemplateException
	 */
	private void init(String ftl, String htmlName, Map map, String relaPath)
			throws IOException, TemplateException {

		String sepChart = File.separator;
		String encode = PropertiesUtil.getProperty(ParamString.ENCODING);
		
		// 创建Configuration对象
		Configuration cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(
				ServletActionContext.getServletContext(), sepChart);
		cfg.setEncoding(Locale.getDefault(), encode);

		// 创建Template对象
		Template template = cfg.getTemplate(ftl);
		template.setEncoding(encode);
		
		// 生成静态页面
		String path = ServletActionContext.getServletContext().getRealPath(sepChart);
		File fileName = new File(path + htmlName);
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(fileName),encode));
		template.process(map, out);
		out.flush();
		out.close();

	}
	
	/**
	 * 
	 * @param listName  集合名称
	 * @param lists  数据集合
	 * @param ftl   模版全路径
	 * @param htmlName  html存放路径+名称
	 * @param relaPath  
	 * @return
	 */
	public String createHtml(Map<String, Object> map, String ftl, String htmlName,
			String relaPath) {
		try {
			init(ftl, htmlName, map, relaPath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return htmlName;
	}
}
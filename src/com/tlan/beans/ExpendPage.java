package com.tlan.beans;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 伸缩段落内容封装
 * 
 * @author magenm
 
 	//伸缩段落      json格式
 	{
		title: {
			src: "http://42.96.145.106/wechat/images/1.jpg"				
		},
		content: [{
			type: "pic-text",
			mainTitle: "鹭岭",
			secondTitle: "项目介绍",
			picSrc: "http://42.96.145.106/wechat/images/1.jpg",
			content: "鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是项目是由阳光新业地产股份有限公司开发建设的作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的"
		},{
			type: "text",
			mainTitle: "鹭岭",
			secondTitle: "项目介绍",
			content: "鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的鹭岭项目是由阳光新业地产股份有限公司开发建设的，作为国内第一家引进国际战略投资的"
		}]
	}
 
 */
public class ExpendPage {
	private String titleSrc;
	private List<ExpendPageElement> elements;
	
	public ExpendPage(String titleSrc,
			List<ExpendPageElement> elements) {
		super();
		this.titleSrc = titleSrc;
		this.elements = elements;
	}
	
	public String toJSON(){
		JSONObject title = new JSONObject();
		title.put("src", titleSrc);
		
		JSONObject obj = new JSONObject();
		obj.put("title", title);
		obj.put("content", JSONArray.fromObject(elements));
		return obj.toString();
	}
	
}

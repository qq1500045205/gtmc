package com.tlan.wxkit.vo.send;

import org.jdom.Document;

public class WxSendTextMsg extends WxSendMsg {
	private String content;
	
	public WxSendTextMsg(WxSendMsg msg, String content) {
		super(msg);
		setMsgType("text");
		this.content = content;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public Document toDocument() {
		Document doc = super.toDocument();
		String textContent = getContent();
		
		createElement(doc.getRootElement(), "Content", textContent);
		return doc;
	}
}

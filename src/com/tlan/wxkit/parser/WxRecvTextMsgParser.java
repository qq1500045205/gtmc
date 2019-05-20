package com.tlan.wxkit.parser;

import org.jdom.Element;
import org.jdom.JDOMException;

import com.tlan.wxkit.vo.recv.WxRecvMsg;
import com.tlan.wxkit.vo.recv.WxRecvTextMsg;


public class WxRecvTextMsgParser extends WxRecvMsgBaseParser{

	@Override
	protected WxRecvTextMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
		return new WxRecvTextMsg(msg, getElementText(root, "Content"));
	}

	
}

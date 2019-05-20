package com.tlan.wxkit.parser;

import org.jdom.Element;
import org.jdom.JDOMException;

import com.tlan.wxkit.vo.recv.WxRecvMsg;
import com.tlan.wxkit.vo.recv.WxRecvPicMsg;


public class WxRecvPicMsgParser extends WxRecvMsgBaseParser {

	@Override
	protected WxRecvPicMsg parser(Element root, WxRecvMsg msg) throws JDOMException {
		return new WxRecvPicMsg(msg, getElementText(root, "PicUrl"));
	}

}

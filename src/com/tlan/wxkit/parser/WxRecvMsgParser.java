package com.tlan.wxkit.parser;

import org.jdom.Document;
import org.jdom.JDOMException;

import com.tlan.wxkit.vo.recv.WxRecvMsg;


public interface WxRecvMsgParser {
	WxRecvMsg parser(Document doc) throws JDOMException;
}

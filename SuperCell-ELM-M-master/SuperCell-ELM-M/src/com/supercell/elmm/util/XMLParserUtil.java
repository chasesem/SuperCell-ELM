package com.supercell.elmm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.supercell.elmm.vo.StateDescription;


public class XMLParserUtil {
	public static Map<Integer, StateDescription> parser(String fileName){
		Map<Integer, StateDescription> map = new HashMap<Integer, StateDescription>();
		InputStream is = XMLParserUtil.class.getClassLoader().getResourceAsStream(fileName);
		SAXBuilder builder = new SAXBuilder();
		Document document;
		try {
			document = builder.build(is);
			Element element = document.getRootElement();
			List<Element> list = element.getChildren("state");
			for(Element e:list){
				String action = e.getChildText("action");
				String description = e.getChildText("description");
				String url = e.getChildText("url");
				StateDescription stateDes = new StateDescription(action, description, url);
				Integer state = Integer.parseInt(action);
				map.put(state, stateDes);
			}
			
		} catch (JDOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return map;
	}
}

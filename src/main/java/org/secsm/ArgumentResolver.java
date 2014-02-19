/*
 * @(#)UserArgumentResolver.java $version 2013. 7. 22.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.secsm;

import java.util.Map;

import org.secsm.model.FilePush;
import org.secsm.model.Guide;
import org.secsm.model.Request;
import org.secsm.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;


public class ArgumentResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter methodParam, NativeWebRequest request) throws Exception {
		
		if (methodParam.getParameterName().equals("user")) {

			User user = new User();
			
			if(request.getParameter("user_id") != null){
				user.setUser_id(Integer.parseInt(request.getParameter("user_id")));
			}
			
			user.setName(request.getParameter("name"));
			user.setPhone(request.getParameter("phone"));
			user.setRegitid(request.getParameter("regitid"));
			
			Map<String,String[]> map =  request.getParameterMap();
			System.out.println(map.containsKey("name")+"");
			
			System.out.println(">>" + user.toString()+request.getParameter("name"));
			
			return user;
		} else if (methodParam.getParameterName().equals("guide")) {

			Guide guide = new Guide();

			if(request.getParameter("idx") != null)
				guide.setIdx(Integer.parseInt(request.getParameter("idx")));
			
			if(request.getParameter("creator") != null)
				guide.setCreator(Integer.parseInt(request.getParameter("creator")));
			
			guide.setDate(request.getParameter("date"));
			guide.setGidx(request.getParameter("gidx"));
			guide.setName(request.getParameter("name"));
			guide.setImage(request.getParameter("image"));
			guide.setOs(request.getParameter("os"));
			
			if(request.getParameter("res") != null)
				guide.setRes(Integer.parseInt(request.getParameter("res")));
			
			guide.setDescription(request.getParameter("description"));
			
			if(request.getParameter("download") != null)
				guide.setCreator(Integer.parseInt(request.getParameter("download")));

			System.out.println(request.toString());
			System.out.println(">>" + guide.toString());
			return guide;

		} else if (methodParam.getParameterName().equals("req")) {
			Request req = new Request();
			
			if(request.getParameter("rid") != null)
				req.setRid(Integer.parseInt(request.getParameter("rid")));
			
			if(request.getParameter("user_id") != null)
				req.setUser_id(Integer.parseInt(request.getParameter("user_id")));
			
			req.setTitle(request.getParameter("title"));
			req.setBody(request.getParameter("body"));

			if(request.getParameter("accept") != null)
				req.setAccept(Integer.parseInt(request.getParameter("accept")));

			
			if(request.getParameter("gidx") != null)
				req.setGidx(Integer.parseInt(request.getParameter("gidx")));
			
			System.out.println(req.toString());
			System.out.println(">>" + req.toString());
			
			return req;

		} else if (methodParam.getParameterName().equals("filePush")) {
			FilePush filePush = new FilePush();
			
			if(request.getParameter("fidx") != null)
				filePush.setFidx(Integer.parseInt(request.getParameter("fidx")));

			if(request.getParameter("sender") != null)
				filePush.setSender(Integer.parseInt(request.getParameter("sender")));
			
			if(request.getParameter("receiver") != null)
				filePush.setReceiver(Integer.parseInt(request.getParameter("receiver")));
			
			filePush.setGidx(request.getParameter("gidx"));
			filePush.setTime(request.getParameter("time"));

			System.out.println(filePush.toString());
			System.out.println(">>" + filePush.toString());

		}
		return UNRESOLVED;
	}

}

package org.secsm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;

import org.secsm.dao.FilePushDao;
import org.secsm.dao.GuideDao;
import org.secsm.dao.RequestDao;
import org.secsm.dao.UserDao;
import org.secsm.model.FilePush;
import org.secsm.model.Guide;
import org.secsm.model.Request;
import org.secsm.model.UploadItem;
import org.secsm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

@Controller
public class MainController {
	@Inject
	UserDao userDao;

	@Inject
	RequestDao requestDao;

	@Inject
	GuideDao guideDao;
	
	@Inject
	FilePushDao filePushDao;

	@Autowired
	private FileSystemResource fsResource;

	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public void addUser(User user) {
		userDao.insertUser(user);
	}

	@ResponseBody
	@RequestMapping(value="/user/{user_id}", method=RequestMethod.GET)
	public Object getUser(@PathVariable String user_id) {
		return userDao.selectUser(user_id);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.PUT)
	public void editUser(User user) {
		System.out.println("test");
		userDao.updateUser(user);
	}

	@RequestMapping(value="/user/{user_id}", method=RequestMethod.DELETE)
	public void delUser(@PathVariable String user_id) {
		userDao.deleteUser(user_id);
	}

	@RequestMapping(value="/guide", method=RequestMethod.POST)
	public void addGuide(Guide guide) {
		guideDao.insertGuide(guide);
	}

	@ResponseBody
	@RequestMapping(value="/guides")
	public Object getGuide(Guide guide) {
		System.out.println("test");
		return guideDao.selectGuide(guide);
	}
	
	@ResponseBody
	@RequestMapping(value="/guide/{idx}", method=RequestMethod.GET)
	public Object getGuide(@PathVariable String idx) {
		return guideDao.selectGuideByIdx(idx);
	}
	
	@RequestMapping(value="/guide", method=RequestMethod.PUT)
	public void editGuide(Guide guide) {
		guideDao.updateGuide(guide);
	}

	@RequestMapping(value="/guide/{idx}", method=RequestMethod.DELETE)
	public void delGuide(@PathVariable String idx) {
		guideDao.deleteGuide(idx);
	}

	@RequestMapping(value="/request", method=RequestMethod.POST)
	public void addRequest(Request request) {
		requestDao.insertRequest(request);
	}

	@ResponseBody
	@RequestMapping("requests")
	public Object getRequests() {
		return requestDao.selectRequests();
	}

	@ResponseBody
	@RequestMapping(value="/request/{user_id}", method=RequestMethod.GET)
	public Object getRequest(@PathVariable String user_id) {
		return requestDao.selectRequestById(user_id);
	}

	@RequestMapping("editRequest")
	public void editGuide(Request request) {
		requestDao.updateGuide(request);
	}

	@RequestMapping(value="/request/{rid}", method=RequestMethod.DELETE)
	public void delRequest(@PathVariable String rid) {
		requestDao.deleteGuide(rid);
	}
	
	@RequestMapping(value="/push", method=RequestMethod.POST)
	public void filePush(FilePush filePush) {
		filePushDao.insertPush(filePush);
	}
	
	@ResponseBody
	@RequestMapping(value="/check/{user_id}", method=RequestMethod.GET)
	public Object checkPush(@PathVariable String user_id) {
		return filePushDao.checkPushFileById(user_id);
	}

	@ResponseBody
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void upload(UploadItem uploadItem, BindingResult result) {
		System.out.println("test");
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
		}

		if (!uploadItem.getFileData().isEmpty()) {
			String filename = uploadItem.getFileData().getOriginalFilename();
			String fileExt = filename.substring(filename.lastIndexOf(".") + 1,
					filename.length());

			if (fileExt.equalsIgnoreCase("json")
					|| fileExt.equalsIgnoreCase("Zip")) {
				byte[] bytes = uploadItem.getFileData().getBytes();

				try {
					File outFileName = new File(fsResource.getPath() + filename);
					FileOutputStream fileoutputStream = new FileOutputStream(
							outFileName);
					fileoutputStream.write(bytes);
					fileoutputStream.close();
				} catch (IOException ie) {
					System.err.println("File writing error! " + ie);
				}
				System.err.println("File upload success! ");
			} else {
				System.err.println("File type error! ");
			}
		}
	}

	
	@RequestMapping("sendGCM")
	public boolean sendGCM(User user) {
		
		String regitId = userDao.getRegitidByPhone(user).get("regitid").toString();
		
		String API = "AIzaSyBabui03rvBsTdArIpv-kJwZ4i_PgMEUPo";
		Sender sender = new Sender(API);
		String ENC = "UTF-8";


		try {
			Message.Builder messageBuilder = new Message.Builder();
			messageBuilder.addData("title", "재환이 바보");
			String message = "test";
			messageBuilder.addData("msg", message);
			message = URLEncoder.encode(message, ENC);
			
			com.google.android.gcm.server.Result result = sender.send(messageBuilder.build(), regitId, 5);
			String messageId = result.getMessageId();

			return (messageId != null);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
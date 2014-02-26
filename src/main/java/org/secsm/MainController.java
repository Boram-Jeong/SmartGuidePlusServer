package org.secsm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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

	@ResponseBody
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public Object addUser(User user) {
		long id = System.currentTimeMillis();
		user.setUser_id("user"+id);

		userDao.insertUser(user);
		
		Map<String, Object> _user = new HashMap<String, Object>();
		_user.put("user_id", user.getUser_id());
		
		return _user;
		
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
		return guideDao.selectGuides(guide);
	}
	
	@ResponseBody
	@RequestMapping(value="/search_res")
	public Object getGuideByRes(Guide guide) {
		return guideDao.selectGuideOfRes(guide);
	}
	
	@ResponseBody
	@RequestMapping(value="/search")
	public Object getSearchList(Guide guide) {
		return guideDao.selectGuideByName(guide);
	}
	
	@ResponseBody
	@RequestMapping(value="/guide_by_user/{user_id}", method=RequestMethod.GET)
	public Object getGuideByID(@PathVariable String user_id) {
		return guideDao.selectGuideByID(user_id);
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
	
	@RequestMapping(value="/download")
	public void updateDownload(Guide guide) {
		guideDao.updateDownload(guide);
	}

	@RequestMapping(value="/request", method=RequestMethod.POST)
	public void addRequest(Request req) {
		requestDao.insertRequest(req);
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
	public void editGuide(Request req) {
		requestDao.updateGuide(req);
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
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
		}

		if (!uploadItem.getFileData().isEmpty()) {
			String filename = uploadItem.getFileData().getOriginalFilename();
			String dirname = filename.substring(0, filename.lastIndexOf(".")) + "\\";
			String fileExt = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

			if (fileExt.equalsIgnoreCase("json")
					|| fileExt.equalsIgnoreCase("Zip")) {
				byte[] bytes = uploadItem.getFileData().getBytes();

				try {
					File dir = new File(fsResource.getPath() + dirname);
					if(!dir.isDirectory()){
						dir.mkdirs();
					}
					
					File outFileName = new File(fsResource.getPath()+ dirname + filename);
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
		System.out.println("[phone] " + user.getPhone() + " [regitid] " + regitId);
		
		String API = "AIzaSyBabui03rvBsTdArIpv-kJwZ4i_PgMEUPo";
		Sender sender = new Sender(API);
		String ENC = "UTF-8";


		try {
			Message.Builder messageBuilder = new Message.Builder();
			String sender_name = user.getName();
			messageBuilder.addData("sender", sender_name);
			sender_name = URLEncoder.encode(sender_name, ENC);
			
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
package org.secsm.model;

public class Request {
	int rid;
	int user_id;
	int gidx;
	String title;
	String body;
	int accept;
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getGidx() {
		return gidx;
	}
	public void setGidx(int gidx) {
		this.gidx = gidx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getAccept() {
		return accept;
	}
	public void setAccept(int accept) {
		this.accept = accept;
	}
	@Override
	public String toString() {
		return "Request [rid=" + rid + ", user_id=" + user_id + ", gidx="
				+ gidx + ", title=" + title + ", body=" + body + ", accept="
				+ accept + "]";
	}

	
}

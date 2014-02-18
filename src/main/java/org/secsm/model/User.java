package org.secsm.model;

public class User {
	int user_id;
	String name;
	String phone;
	String regitid;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegitid() {
		return regitid;
	}
	public void setRegitid(String regitid) {
		this.regitid = regitid;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", phone="
				+ phone + ", regitid=" + regitid + "]";
	}
}

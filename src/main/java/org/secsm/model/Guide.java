package org.secsm.model;


public class Guide {
	int idx;
	int creator;
	String date;
	String gidx;
	String name;
	String image;
	String model;
	String os;
	int res;
	String description;
	int download;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGidx() {
		return gidx;
	}

	public void setGidx(String gidx) {
		this.gidx = gidx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	@Override
	public String toString() {
		return "Guide [idx=" + idx + ", creator=" + creator + ", date=" + date
				+ ", gidx=" + gidx + ", name=" + name + ", image=" + image
				+ ", model=" + model + ", os=" + os + ", res=" + res
				+ ", description=" + description + ", download=" + download
				+ "]";
	}

	

}

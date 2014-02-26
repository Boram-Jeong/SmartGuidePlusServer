package org.secsm.model;

public class Guide {
	int idx;
	String creator;
	String date;
	String gidx;
	String name;
	String image;
	String os;
	String device;
	int width;
	int height;
	String description;
	int download;
	int limit;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
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

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "Guide [idx=" + idx + ", creator=" + creator + ", date=" + date
				+ ", gidx=" + gidx + ", name=" + name + ", image=" + image
				+ ", os=" + os + ", device=" + device + ", width=" + width
				+ ", height=" + height + ", description=" + description
				+ ", download=" + download + ", limit=" + limit + "]";
	}
}

package com.smart.helper;

public class Message {
	private String content;
	private String type;
	
	public Message(String Content, String type) {
		super();
		this.content = Content;
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
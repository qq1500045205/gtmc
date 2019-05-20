package com.tlan.plugins.lllagel.weiche;

public class Struct {
	private String name;
	private String value;
	private String pinyin;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Struct(String name, String pinyin, String value) {
		super();
		this.name = name;
		this.value = value;
		this.pinyin = pinyin;
	}
}

package com.ufo.appi.dto;

import java.util.ArrayList;

public class Cjxx extends Cjxxlist {
	//采集信息上报的图片列表
	private ArrayList<Cjxxpic> cjxxpic;

	public Cjxx() {

	}

	public ArrayList<Cjxxpic> getCjxxpic() {
		return cjxxpic;
	}

	public void setCjxxpic(ArrayList<Cjxxpic> cjxxpic) {
		this.cjxxpic = cjxxpic;
	}



}

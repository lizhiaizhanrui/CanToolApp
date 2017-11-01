package com.example.cantoolapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class App extends Application{
	private List<String> fileList  = new ArrayList<String>();
	
	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
	}
}

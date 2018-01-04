package com.s_ap.www.iot.datatranster.ui;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import com.s_ap.www.iot.datatranster.entity.FilePathBean;

public class LoadConfig {
	private Ini ini = null;
	private String path;

	public LoadConfig(String path) throws IOException {
		this.path = path;
		ini = new Ini(new File(path));
	}

	public String getTopic() {
		String result = null;
		Section section = ini.get("TOPIC");
		result = section.get("APPNAME") + "/" + section.get("SOURCE") + "/" + section.get("TARGET") + "/";
		return result;
	}

	public FilePathBean getFilePath() {
		String dataFileFullPath = ini.get("DATA_PATH").get("VALUE");

		int lastIndexOf = dataFileFullPath.lastIndexOf("/");
		String dataFilePath = dataFileFullPath.substring(0, lastIndexOf + 1);
		String dataFileName = dataFileFullPath.substring(lastIndexOf + 1, dataFileFullPath.length());

		FilePathBean filePathBean = new FilePathBean(dataFilePath, dataFileName, path);
		System.out.println(filePathBean);
		return filePathBean;
	}
}

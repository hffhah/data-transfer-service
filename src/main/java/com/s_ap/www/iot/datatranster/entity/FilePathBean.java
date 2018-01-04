package com.s_ap.www.iot.datatranster.entity;

public class FilePathBean {
	private String dataFilePath;
	private String dataFileName;
	private String configPath;

	
	public FilePathBean(String dataFilePath, String dataFileName, String configPath) {
		super();
		this.dataFilePath = dataFilePath;
		this.dataFileName = dataFileName;
		this.configPath = configPath;
	}

	public String getDataFilePath() {
		return dataFilePath;
	}

	public void setDataFilePath(String dataFilePath) {
		this.dataFilePath = dataFilePath;
	}

	public String getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

	@Override
	public String toString() {
		return "FilePathBean [dataFilePath=" + dataFilePath + ", dataFileName=" + dataFileName + ", configPath="
				+ configPath + "]";
	}
	
	

}

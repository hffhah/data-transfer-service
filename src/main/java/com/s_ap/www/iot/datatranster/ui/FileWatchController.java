package com.s_ap.www.iot.datatranster.ui;

import com.s_ap.www.iot.datatranster.entity.FilePathBean;
import com.s_ap.www.iot.datatranster.service.FileWatchService;

/**
 * 界面控制类
 * 
 * @author zihaozhu
 * @date 2017-12-06 5:39:09 PM
 */
public class FileWatchController {
	private FileWatchService service;
	private String path;

	public FileWatchController(FilePathBean filePathBean) {
		this.path = filePathBean.getDataFilePath();
		this.service = new FileWatchService(filePathBean);
	}

	public void execute() {
		this.service.run(path);
	}
}

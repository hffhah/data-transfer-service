package com.s_ap.www.iot.datatranster.test;

import java.io.IOException;

import com.s_ap.www.iot.datatranster.entity.FilePathBean;
import com.s_ap.www.iot.datatranster.ui.FileWatchController;
import com.s_ap.www.iot.datatranster.ui.LoadConfig;

/**
 * 定义测试类
 * 
 * @date 2017-12-06 5:39:00 PM
 */
public class SimpleTest {
	private static final String CONFIG_NAME = "E:\\WorkBench\\data-transfer-service\\src\\main\\resources\\topicConfig.ini";
	private static LoadConfig loadConifg;
	private static FileWatchController fileWatchController;

	public static void main(String[] args) {
		try {
			loadConifg = new LoadConfig(CONFIG_NAME);
			FilePathBean filePath = loadConifg.getFilePath();
			fileWatchController = new FileWatchController(filePath);
			fileWatchController.execute();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

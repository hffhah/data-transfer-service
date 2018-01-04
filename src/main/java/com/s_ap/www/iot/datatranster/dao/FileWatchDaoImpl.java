package com.s_ap.www.iot.datatranster.dao;

import java.io.IOException;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s_ap.www.iot.datatranster.entity.FilePathBean;
import com.s_ap.www.iot.datatranster.utils.CommUtils;

public class FileWatchDaoImpl implements FileWatchDao {
	private WatchService service;
	private String dataFileName;
	private String dataFileFullName;
	private static Logger logger = LoggerFactory.getLogger(FileWatchDaoImpl.class);

	public FileWatchDaoImpl(WatchService service, FilePathBean filePathBean) {
		this.service = service;
		this.dataFileName = filePathBean.getDataFileName();
		this.dataFileFullName = filePathBean.getDataFilePath() + filePathBean.getDataFileName();
	}

	private void onDataChange() {
		String context = CommUtils.readFileByLines(dataFileFullName);

		if (CommUtils.isGoodJson(context)) {
			// todo
			
		} else {
			logger.error("invalid json format!\r\n" + context);
		}
	}

	@Override
	public void monitor() {
		try {
			while (true) {
				WatchKey watchKey = service.take(); // waiting...
				List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
				for (WatchEvent<?> event : watchEvents) {
					if (event.context().toString().equals(this.dataFileName)
							&& event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
						onDataChange();
					}
				}
				boolean reset = watchKey.reset();
				if (!reset) {
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			logger.error("service.close");
			try {
				service.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

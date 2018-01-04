package com.s_ap.www.iot.datatranster.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.s_ap.www.iot.datatranster.dao.FileWatchDao;
import com.s_ap.www.iot.datatranster.dao.FileWatchDaoImpl;
import com.s_ap.www.iot.datatranster.entity.FilePathBean;

/**
 * @author zihaozhu
 * @date 2018-01-04 2:48:00 PM
 */
public class FileWatchService {
	private FileWatchDao fileWatchDao;
	private WatchService ws;
	private String path;
	private Executor executor = Executors.newSingleThreadExecutor();

	public FileWatchService(FilePathBean filePathBean) {
		this.path = filePathBean.getDataFilePath();

		try {
			if (!checkPath(path)) {
				throw new ServiceException(path + " don't exists!");
			}

			this.ws = FileSystems.getDefault().newWatchService();
			this.fileWatchDao = new FileWatchDaoImpl(this.ws, filePathBean);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run(String path) {
		try {
			executor.execute(() -> {
				this.fileWatchDao.monitor();
			});

			// register service
			Path p = Paths.get(path);
			p.register(this.ws, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_CREATE);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkPath(String path) {
		boolean flag = false;

		File file = new File(path);
		if (file.exists()) {
			flag = true;
		}
		return flag;
	}
}

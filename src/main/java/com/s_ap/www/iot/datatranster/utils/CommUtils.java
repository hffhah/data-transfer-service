package com.s_ap.www.iot.datatranster.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * 工具类
 * 
 * @author zihaozhu
 * @date 2017-12-06 5:39:16 PM
 */
public class CommUtils {
	public static boolean isGoodJson(String context) {
		boolean flag;
		try {
			new JsonParser().parse(context);
			flag = true;
		} catch (JsonParseException e) {
			flag = false;
		}

		return flag;
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuilder result = new StringBuilder();

		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// int line = 1;
			while ((tempString = reader.readLine()) != null) {
				// line++;
				result.append(System.lineSeparator() + tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		return result.toString();
	}

	public static String getFileName(String path) {

		return null;
	}

}

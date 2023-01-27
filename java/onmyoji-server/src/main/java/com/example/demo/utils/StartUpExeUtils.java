package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * java启动本地exe程序工具类
 *
 * @author myt
 * @since 2020年11月1日
 */
public class StartUpExeUtils {
	
	private static final boolean startExeStatus = true;//exe启动状态
	
	//仅启动exe客户端，不检查进程
	public static void startUpExeOnly (String exePath) throws IOException {
		if (!exePath.equals ("")) {
			Runtime.getRuntime ()
			       .exec (exePath);
		}
	}
	/**
	 * 仅检查进程是否存在
	 *
	 */
	public static boolean checkProcessOnly (String procName) {
		//判断是否存在进程
		boolean existProc = false;
		BufferedReader bufferedReader = null;
		try {
			Process proc = Runtime.getRuntime ()
			                      .exec ("tasklist -fi " + '"' + "imagename eq " + procName + '"');
			bufferedReader = new BufferedReader (new InputStreamReader (proc.getInputStream ()));
			String line;
			while ((line = bufferedReader.readLine ()) != null) {
				if (line.contains (procName)) {
					existProc = true;//存在
				}
			}
		} catch (Exception ex) {
			System.out.println ("查询程序进程异常：" + ex.getMessage ());
			return existProc;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close ();
				} catch (Exception ignored) {
				}
			}
		}
		return existProc;
	}
	

}
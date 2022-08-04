package com.example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
	
	/***
	 * @description: 仅kill指定进程
	 * @param procName
	 * @return: void
	 * @author: orcakill
	 * @date: 2022/1/10 8:40
	 */
	public static void killExeOnly (String procName) throws IOException {
		if (!procName.equals ("")) {
			String command = "taskkill /F /IM " + procName;
			Runtime.getRuntime ()
			       .exec ("cmd /c " + command);
		}
	}
	
	//启动exe客户端
	public static boolean startUpExe (String exePath, String procName) {
		if (!exePath.equals ("") && !procName.equals ("")) {
			String result = checkProcess (procName);//检查exe进程
			if (result.isEmpty ()) {
				try {
					Runtime.getRuntime ()
					       .exec (exePath);
				} catch (Exception e) {
					e.printStackTrace ();
					System.out.println ("程序：" + exePath + "不存在！");
				}
			}
		}
		return startExeStatus;
	}
	
	//启动exe客户端,并传参
	public static boolean startUpExe (String exePath, String procName, int subId, String curModeId, String riskSet1,
	                                  String riskSet2, String riskSet3) {
		if (!exePath.equals ("") && !procName.equals ("")) {
			String result = checkProcess (procName);//检查exe进程
			if (result.isEmpty ()) {
				try {
					//启动exe执行程序
					String[] cmd = {exePath, subId + "", curModeId, riskSet1, riskSet2, riskSet3};
					Runtime.getRuntime ()
					       .exec (cmd);
				} catch (Exception e) {
					e.printStackTrace ();
					System.out.println ("程序：" + exePath + "不存在！");
				}
			}
		}
		return startExeStatus;
	}
	
	/**
	 * 检查进程是否存在，存在则杀死进程
	 *
	 * @param procName
	 */
	public static String checkProcess (String procName) {
		String result = "";
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
			result = "查询程序进程异常：" + ex.getMessage ();
			System.out.println ("查询程序进程异常：" + ex.getMessage ());
			return result;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close ();
				} catch (Exception ignored) {
				}
			}
		}
		
		// 存在，则先杀死该进程
		if (existProc) {
			BufferedReader br = null;
			try {
				if (!procName.equals ("")) {
					//执行cmd命令
					String command = "taskkill /F /IM " + procName;
					Runtime runtime = Runtime.getRuntime ();
					Process process = runtime.exec ("cmd /c " + command);
					br = new BufferedReader (new InputStreamReader (process.getInputStream (), StandardCharsets.UTF_8));
				}
			} catch (Exception e) {
				result = "关闭程序进程异常：" + e.getMessage ();
				System.out.println ("关闭程序进程异常：" + e.getMessage ());
				return result;
			} finally {
				if (br != null) {
					try {
						br.close ();
					} catch (Exception ignored) {
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 仅检查进程是否存在
	 *
	 * @param procName
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
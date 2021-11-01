package other.document.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName ConvertVideo.java
 * @Description TODO
 * @createTime 2021年11月01日 09:43:00
 */
public class ConvertVideo {

		
		private static String ffmpegPath  = "D:/study/utils/ffmpeg/bin/ffmpeg.exe";
		
		private static String mplayerPath = "D:/study/utils/mencoder/mencoder.exe";
		
		
		/**
		 * 视频格式转换入口
		 *
		 * @param inputPath  源文件 路径
		 * @param outputPath 目标文件 路径
		 * @param outputPath1 中间文件地址 路径
		 * @return
		 */
		public synchronized static boolean process(String inputPath, String outputPath ,String outputPath1) {
			int type = checkContentType(inputPath);
			boolean status = false;
			Long old = System.currentTimeMillis();
			System.out.println("old--------" + old);
			if (type == 0) {
				System.out.println("直接转成MP4格式");
				status = processMP4(inputPath, outputPath);// 直接转成flv格式
			} else if (type == 1) {
				String avifilepath = processAVI(inputPath, outputPath1);
				System.out.println(avifilepath);
				if (avifilepath == null)
					return false;// 没有得到avi格式
				status = processMP4(avifilepath, outputPath);// 将avi转成flv格式
			}
			Long yong = System.currentTimeMillis();
			System.out.println("消耗时间----------" + (yong-old));
			return status;
		}
		
		/**
		 * 判断文件类型.执行不同的转换流程
		 *
		 * @param inputPath 源文件路径
		 * @return
		 */
		private static int checkContentType(String inputPath) {
			String type = inputPath.substring(inputPath.lastIndexOf(".") + 1, inputPath.length())
			                       .toLowerCase();
			// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
			if (type.equals("avi")) {
				return 0;
			} else if (type.equals("mpg")) {
				return 0;
			} else if (type.equals("wmv")) {
				return 0;
			} else if (type.equals("3gp")) {
				return 0;
			} else if (type.equals("mov")) {
				return 0;
			} else if (type.equals("mp4")) {
				return 0;
			} else if (type.equals("asf")) {
				return 0;
			} else if (type.equals("asx")) {
				return 0;
			} else if (type.equals("flv")) {
				return 0;
			}
			// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
			// 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
			else if (type.equals("wmv9")) {
				return 1;
			} else if (type.equals("rm")) {
				return 1;
			} else if (type.equals("rmvb")) {
				return 1;
			}
			return 9;
		}
		
		/**
		 * 判断源文件是否存在
		 *
		 * @param path 文件路径
		 * @return
		 */
		private static boolean checkfile(String path) {
			File file = new File(path);
			if (!file.isFile()) {
				return false;
			}
			return true;
		}
		
		// 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
		private static String processAVI(String inputPath, String outputPath1) {
			List<String> commend = new ArrayList<> ();
			commend.add(mplayerPath);
			commend.add(inputPath);
			commend.add("-oac");
			commend.add("mp3lame");
			commend.add("-lameopts");
			commend.add("preset=64");
			commend.add("-ovc");
			commend.add("xvid");
			commend.add("-xvidencopts");
			commend.add("bitrate=600");
			commend.add("-of");
			commend.add("avi");
			commend.add("-o");
			commend.add(outputPath1);
			try {
				ProcessBuilder builder = new ProcessBuilder();
				Process process = builder.command(commend).redirectErrorStream(true).start();
				new PrintStream(process.getInputStream()).start();
				new PrintStream(process.getErrorStream()).start();
				process.waitFor();
				process.destroy();
				return outputPath1;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等） 转换为MP4
		private static boolean processMP4(String inputPath, String outputPath) {
			
			if (!checkfile(inputPath)) {
				System.out.println(inputPath + " is not file");
				return false;
			}
			
			List<String> command = new ArrayList<>();
			command.add(ffmpegPath);
			command.add("-i");
			command.add(inputPath);
			command.add("-c:v");
			command.add("libx264");
			command.add("-mbd");
			command.add("0");
			command.add("-c:a");
			command.add("aac");
			command.add("-strict");
			command.add("-2");
			command.add("-pix_fmt");
			command.add("yuv420p");
			command.add("-movflags");
			command.add("faststart");
			command.add(outputPath);
			try {
				Process videoProcess = new ProcessBuilder(command).redirectErrorStream(true).start();
				
				new PrintStream(videoProcess.getErrorStream()).start();
				
				new PrintStream(videoProcess.getInputStream()).start();
				
				videoProcess.waitFor();
				videoProcess.destroy();
				System.out.println(outputPath);
				return true;
			} catch (Exception e) {
				
				return false;
			}
		}
		public static void main(String[] args) {
			//本地文件地址
			String inputPath = "E:/test/11.flv";
			//目标文件地址
			String outputPath ="E:/video/11.mp4";
			//中间文件随机数
			String uuidname = UUID.randomUUID().toString();
			//中间文件地址,需要两次转换的情况下会用到该中间文件.直接转换为MP4的文件不会生成中间文件.
			String path = "E:/video/";
			String outputPath1 = path  + uuidname + ".avi";
			//执行转换
			ConvertVideo.process(inputPath,outputPath,outputPath1);//执行完毕后可以删除没用的文件
			
		}
	}
	
	/**
	 * 资源释放流.避免内存溢出导致进程阻塞
	 */
	class PrintStream extends Thread {
		
		java.io.InputStream __is = null;
		
		public PrintStream(java.io.InputStream is) {
			__is = is;
		}
		
		public void run() {
			try {
				while (this != null) {
					int _ch = __is.read();
					if (_ch != -1)
						System.out.print((char) _ch);
					else break;
				}
				__is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
	}


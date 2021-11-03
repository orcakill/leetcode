package other.document.utils;

/**
 * @Classname FFmpegException
 * @Description TODO
 * @Date 2021/11/2 23:18
 * @Created by orcakill
 */
class FFmpegException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public FFmpegException () {
		super ();
	}
	
	public FFmpegException (String message) {
		super (message);
	}
	
	public FFmpegException (Throwable cause) {
		super (cause);
	}
	
	public FFmpegException (String message, Throwable cause) {
		super (message, cause);
	}
}

package com.example.demo.utils;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinGDI;
import com.sun.jna.platform.win32.WinGDI.BITMAPINFO;
import com.sun.jna.platform.win32.WinGDI.BITMAPINFOHEADER;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.win32.W32APIOptions;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferUShort;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 * @Classname BackgroundScript
 * @Description 后台截图
 * @Date 2021/10/24 20:38
 * @Created by orcakill
 */
public class ScanningProcessUtils {
	
	/**
	 * 本方法会根据游戏界面句柄扫描进程窗口,窗口尺寸并且返回窗口截图(进程不可以最小化)
	 * @param hwnd        - 游戏界面句柄
	 * @param game_width  - 窗口宽度
	 * @param game_height - 窗口高度
	 * @return - 返回窗口截图
	 */
	public static BufferedImage scanningProcess (HWND hwnd, int game_width, int game_height) {
		
		// 检索游戏窗口区域的显示设备上下文环境的句柄,以后在GDI函数中使用该句柄来在设备上下文环境中绘图
		HDC gameDC = GDI32.INSTANCE.GetDC (hwnd);
		// 创建与指定的设备环境相关的设备兼容的位图
		HBITMAP outputBitmap = GDI32.INSTANCE.CreateCompatibleBitmap (gameDC, game_width, game_height);
		try {
			// 创建一个与指定设备兼容的内存设备上下文环境
			HDC blitDC = GDI32.INSTANCE.CreateCompatibleDC (gameDC);
			try {
				// 选择一对象到指定的设备上下文环境中
				HANDLE oldBitmap = GDI32.INSTANCE.SelectObject (blitDC, outputBitmap);
				try {
					// 对指定的源设备环境区域中的像素进行位块(bit_block)转换
					GDI32.INSTANCE.BitBlt (blitDC, 0, 0, game_width, game_height, gameDC, 0, 0, GDI32.SRCCOPY);
				} finally {
					GDI32.INSTANCE.SelectObject (blitDC, oldBitmap);
				}
				// 位图信息头,大小固定40个字节
				BITMAPINFO bi = new BITMAPINFO (40);
				bi.bmiHeader.biSize = 40;
				// 函数获取指定兼容位图的位,然后将其作一个DIB—设备无关位图使用的指定格式复制到一个缓冲区中
				boolean ok = GDI32.INSTANCE.GetDIBits (blitDC, outputBitmap, 0, game_height, (byte[]) null, bi,
				                                       WinGDI.DIB_RGB_COLORS);
				if (ok) {
					BITMAPINFOHEADER bih = bi.bmiHeader;
					bih.biHeight = -Math.abs (bih.biHeight);
					bi.bmiHeader.biCompression = 0;
					return bufferedImageFromBitmap (blitDC, outputBitmap, bi);
				}
			} finally {
				GDI32.INSTANCE.DeleteObject (blitDC);
			}
		} finally {
			GDI32.INSTANCE.DeleteObject (outputBitmap);
		}
		
		return null;
		
	}
	
	// 依赖方法scanningProcess
	private static BufferedImage bufferedImageFromBitmap (HDC blitDC, HBITMAP outputBitmap, BITMAPINFO bi) {
		BITMAPINFOHEADER bih = bi.bmiHeader;
		int height = Math.abs (bih.biHeight);
		final DirectColorModel cm;
		final DataBuffer buffer;
		final WritableRaster raster;
		int strideBits = (bih.biWidth * bih.biBitCount);
		int strideBytesAligned = (((strideBits - 1) | 0x1F) + 1) >> 3;
		final int strideElementsAligned;
		switch (bih.biBitCount) {
			case 16:
				strideElementsAligned = strideBytesAligned / 2;
				cm = new DirectColorModel (16, 0x7C00, 0x3E0, 0x1F);
				buffer = new DataBufferUShort (strideElementsAligned * height);
				raster = Raster.createPackedRaster (buffer, bih.biWidth, height, strideElementsAligned,
				                                    cm.getMasks (), null);
				break;
			case 32:
				strideElementsAligned = strideBytesAligned / 4;
				cm = new DirectColorModel (32, 0xFF0000, 0xFF00, 0xFF);
				buffer = new DataBufferInt (strideElementsAligned * height);
				raster = Raster.createPackedRaster (buffer, bih.biWidth, height, strideElementsAligned,
				                                    cm.getMasks (), null);
				break;
			default:
				throw new IllegalArgumentException ("检测到不支持的图片位数: " + bih.biBitCount);
		}
		final boolean ok;
		switch (buffer.getDataType ()) {
			case DataBuffer.TYPE_INT: {
				assert buffer instanceof DataBufferInt;
				int[] pixels = ((DataBufferInt) buffer).getData ();
				ok = GDI32.INSTANCE.GetDIBits (blitDC, outputBitmap, 0, raster.getHeight (), pixels, bi, 0);
			}
			break;
			case DataBuffer.TYPE_USHORT: {
				assert buffer instanceof DataBufferUShort;
				short[] pixels = ((DataBufferUShort) buffer).getData ();
				ok = GDI32.INSTANCE.GetDIBits (blitDC, outputBitmap, 0, raster.getHeight (), pixels, bi, 0);
			}
			break;
			default:
				throw new AssertionError ("检测到不支持的缓冲元素类型: " + buffer.getDataType ());
		}
		if (ok) {
			return new BufferedImage (cm, raster, false, null);
		}
		else {
			return null;
		}
	}
	
	interface GDI32 extends com.sun.jna.platform.win32.GDI32 {
		GDI32 INSTANCE = Native.loadLibrary (GDI32.class);
		int SRCCOPY = 0xCC0020;
		
		boolean BitBlt (HDC hdcDest, int nXDest, int nYDest, int nWidth,
		                int nHeight, HDC hdcSrc, int nXSrc, int nYSrc, int dwRop);
		
		HDC GetDC (HWND hWnd);
		
		boolean GetDIBits (HDC dc, HBITMAP bmp, int startScan, int scanLines,
		                   byte[] pixels, BITMAPINFO bi, int usage);
		
		boolean GetDIBits (HDC dc, HBITMAP bmp, int startScan, int scanLines,
		                   short[] pixels, BITMAPINFO bi, int usage);
		
		boolean GetDIBits (HDC dc, HBITMAP bmp, int startScan, int scanLines,
		                   int[] pixels, BITMAPINFO bi, int usage);
	}
	
	interface User32 extends com.sun.jna.platform.win32.User32 {
		User32 INSTANCE = Native.loadLibrary (User32.class,
		                                      W32APIOptions.UNICODE_OPTIONS);
		
		HWND GetDesktopWindow ();
		
	}
	
}

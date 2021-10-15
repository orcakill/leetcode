package other.scenario.service;

import other.scenario.service.impl.IndexServiceImpl;

import java.awt.*;

public interface IndexService {
	//判断首页是否存在
	static Boolean indexEmpty() throws InterruptedException, AWTException{
		return IndexServiceImpl.indexEmpty ();
	}
	//根据返回按钮返回
	static void indexBack() throws InterruptedException, AWTException{
		IndexServiceImpl.indexBack ();
	}
	//点击头像返回
	static void indexHeadBack() throws InterruptedException, AWTException{
		IndexServiceImpl.indexHeadBack ();
	}
}

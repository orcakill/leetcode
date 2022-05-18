package other.document.service;

import other.document.service.impl.FindServiceImpl;

/**
 * @author orcakill
 * @version 1.0.0
 * @ClassName FindService.java
 * @Description TODO
 * @createTime 2021年11月02日 08:23:00
 */
public interface FindService {
	
	static  void findVideo(){
		FindServiceImpl.findVideo();
	}
}

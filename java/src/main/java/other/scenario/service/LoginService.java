package other.scenario.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import other.scenario.dao.OnmyojiInfoMapper;
import other.scenario.entity.PictureIdentifyWorkPO;
import other.scenario.map.ExeAddress;
import other.scenario.service.impl.LoginServiceImpl;
import other.scenario.util.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface LoginService {
	
	
	static void loginService () throws Exception{
		LoginServiceImpl.loginService();
	}
	
	static void loginAreaService (String userName) throws Exception{
		LoginServiceImpl.loginAreaService (userName);
	}
	
	static void loginBackService () throws InterruptedException, AWTException{
		LoginServiceImpl.loginBackService ();
	}
	
	
}

package other.scenario.map;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExeAddress {
	public  static String exeAddress(){
		String address="";
		List<String> list=new ArrayList<> ();
		boolean add = list.add ("D:\\software\\Onmyoji");
		
		for (String s : list) {
			File file = new File (s);
			if (file.exists ()) {
				address = s + "\\Launch.exe";
				break;
			}
		}
		return address;
	}
}

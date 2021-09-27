package other.auto;

import other.auto.entity.ClassPO;
import other.auto.method.*;
import other.auto.util.CommonUtils;
import java.util.List;



public class TestApp {
	public static void main (String[] args) throws ClassNotFoundException {
		Class<?> c1 = Class.forName ("other.scenario.entity.TaskListPO");
		final String name = "TaskListPO";
		List<ClassPO> classPOS = CommonUtils.getClassPO (c1);
		List<ClassPO> classPOS1 = CommonUtils.getPk (classPOS);
		List<ClassPO> classPOS2 = CommonUtils.getNoPk (classPOS);
		String name1 = CommonUtils.toLower (name);
		/*测试findById*/
		StringBuilder str1= FindByIdTest.findByIdTest (name,name1,classPOS,classPOS1);
		/*测试findAll*/
		StringBuilder str2= FindAllTest.findAllTest (name,name1,classPOS,classPOS1);
		/*测试check*/
		StringBuilder str3= CheckTest.checkTest (name,name1,classPOS,classPOS1);
		/*测试save*/
		StringBuilder str4= SaveTest.saveTest (name,name1,classPOS,classPOS1);
		/*测试deleteAll*/
		StringBuilder str5= DeleteAllTest.deleteAllTest (name,name1,classPOS,classPOS1);
		/*测试deleteById*/
		StringBuilder str6=DeleteByIdTest.deleteByIdTest (name,name1,classPOS,classPOS1);
		StringBuilder str=new StringBuilder ();
		str.append (str1).append (str2).append (str3).append (str4).append (str5).append (str6);
		System.out.println (str);
	}
	
}

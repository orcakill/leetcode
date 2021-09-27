package other.auto;

import other.auto.entity.ClassPO;
import other.auto.method.CheckTest;
import other.auto.method.FindAllTest;
import other.auto.method.FindByIdTest;
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
		StringBuilder str4=new StringBuilder ();
		/*测试deleteAll*/
		StringBuilder str5=new StringBuilder ();
		/*测试deleteById*/
		StringBuilder str6=new StringBuilder ();
		System.out.println (str3);
	}
	
}

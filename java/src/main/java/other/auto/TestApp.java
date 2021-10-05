package other.auto;

import other.auto.entity.ClassPO;
import other.auto.method.*;
import other.auto.util.CommonUtils;
import java.util.List;



public class TestApp {
	public static void main (String[] args) throws ClassNotFoundException {
		Class<?> c1 = Class.forName ("other.scenario.entity.TaskInfoPO");
		final String name = "TaskInfoPO";
		List<ClassPO> classPOS = CommonUtils.getClassPO (c1);
		List<ClassPO> classPOS1 = CommonUtils.getPk (classPOS);
		String name1 = CommonUtils.toLower (name);
		/*测试findById*/
		StringBuilder str1= FindByIdTest.findByIdTest (name,name1,classPOS,classPOS1);
		/*测试findAll*/
		StringBuilder str2= FindAllTest.findAllTest (name,name1,classPOS);
		/*测试check*/
		StringBuilder str3= CheckTest.checkTest (name, classPOS1);
		/*测试save*/
		StringBuilder str4= SaveTest.saveTest (name,name1,classPOS);
		/*测试deleteAll*/
		StringBuilder str5= DeleteAllTest.deleteAllTest (name,name1,classPOS,classPOS1);
		/*测试deleteById*/
		StringBuilder str6=DeleteByIdTest.deleteByIdTest (name, classPOS1);
		System.out.println (String.valueOf (str1) + str2 + str3 + str4 + str5 + str6);
	}
	
}

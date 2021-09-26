package other.auto;

import other.auto.entity.ClassPO;
import other.auto.method.*;
import other.auto.util.CommonUtils;

import java.util.List;

public class MapperApp {
	public static void main (String[] args) throws ClassNotFoundException {        /* 赋值类名*/
		Class<?> c = Class.forName ("other.scenario.entity.TaskListPO");
		final String name = "TaskListPO";
		String table = "task_list";
		List<ClassPO> classPOS = CommonUtils.getClassPO (c);
		List<ClassPO> classPOS1 = CommonUtils.getPk (classPOS);
		List<ClassPO> classPOS2 = CommonUtils.getNoPk (classPOS);
		String name1 = CommonUtils.toLower (name);
		/*根据主键查询一条数据*/
		StringBuilder str1 =FindByID.findById (name, name1, table, classPOS, classPOS1);
		/*查询所有数据的mapper方法*/
		StringBuilder str2 =FindAll.findAll (name, name1, table, classPOS);
		/*根据主键判断数据已存在的mapper方法*/
		StringBuilder str3 =Check.check (table,classPOS, classPOS1);
		/*保存方法*/
		StringBuilder str4=Save.save (name, name1, table, classPOS, classPOS1, classPOS2);
		/*删除全部的方法*/
		StringBuilder str5=DeleteAll.deleteAll (table);
		/*根据主键删除的方法*/
		StringBuilder str6= DeleteOne.deleteById (table, classPOS, classPOS1);
		StringBuilder str=str1.append (str2).append (str3).append (str4).append (str5).append (str6);
		System.out.println (str);
	}
}

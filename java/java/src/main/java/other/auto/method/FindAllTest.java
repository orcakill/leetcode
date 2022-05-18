package other.auto.method;

import other.auto.entity.ClassPO;
import other.dao.CommonUtils;

import java.util.List;

public class FindAllTest {
	public static  StringBuilder findAllTest (String name, String name1, List<ClassPO> classPOS){
		StringBuilder stringBuilder=new StringBuilder ();
		stringBuilder.append ("@Test\r\n")
				.append ("public void findAll() throws SQLException {\n");
		stringBuilder.append ("\tList<")
		             .append (name)
		             .append (">")
		             .append (" ")
		             .append (name1)
		             .append ("List="+name.replaceAll ("PO","")+"Mapper.findAll();\r\n")
		             .append ("\tfor(int i=0;i<")
		             .append (name1)
		             .append ("List.size();i++){\n");
		for (ClassPO classPO : classPOS) {
			stringBuilder.append ("\t\tSystem.out.println (")
			             .append (name1)
			             .append ("List.get(i).get")
			             .append (CommonUtils.toUpper (classPO.getName ()))
			             .append ("());\n");
		}
		stringBuilder.append ("\t}\n")
				.append ("}\n\n");
		
		return  stringBuilder;
	}
}

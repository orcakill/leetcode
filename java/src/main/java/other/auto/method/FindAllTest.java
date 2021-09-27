package other.auto.method;

import other.auto.entity.ClassPO;

import java.util.List;

public class FindAllTest {
	public static  StringBuilder findAllTest (String name, String name1, List<ClassPO> classPOS,
	                                         List<ClassPO> classPOS1){
		StringBuilder stringBuilder=new StringBuilder ();
		stringBuilder.append ("@Test\r\n")
				.append ("public void findAll() throws SQLException {\n");
		stringBuilder.append ("\tList<"+name+">"+" "+name1+"List=new ArrayList();\r\n")
				.append ("\tfor(int i=0;i<"+name1+"List.size();i++){\n");
		for(int i=0;i<classPOS.size ();i++){
			stringBuilder.append ("\t\tSystem.out.println ("+name1+"List.get(i)."+classPOS.get (i).getName ()+");\n");
		}
		stringBuilder.append ("\t}\n")
				.append ("}\n");
		
		return  stringBuilder;
	}
}

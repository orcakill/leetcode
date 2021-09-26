package other.auto.method;

import other.auto.entity.ClassPO;
import util.Case;

import java.util.List;

public class FindByIdTest {
	public  static  StringBuilder findById (String name, String name1,List<ClassPO> classPOS,List<ClassPO> classPOS1){
		StringBuilder str=new StringBuilder ();
		str.append ("@Test\r\n");
		str.append ("public void findById() throws SQLException {\r\n");
		String substring = name.substring (0, name.length () - 2);
		str.append ("  ")
		   .append (name)
		   .append (" ")
		   .append (name1)
		   .append ("=")
		   .append (substring);
		str.append ("Mapper");
		str.append (".");
		str.append ("findByID(");
		str.append ("BigDecimal.valueOf(1));\r\n");
		for (ClassPO classPO : classPOS) {
			str.append ("        System.out.println(")
			   .append (name1)
			   .append (".get")
			   .append (Case.toUpper (classPO.getName ()))
			   .append ("());\r\n");
		}
		str.append ("}")
		   .append ("\r\n")
		   .append ("    @Test\r\n");
		return  str;
	}
}

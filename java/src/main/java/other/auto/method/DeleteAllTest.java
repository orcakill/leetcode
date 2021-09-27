package other.auto.method;

import other.auto.entity.ClassPO;

import java.util.List;

public class DeleteAllTest {
	public static StringBuilder deleteAllTest (String name, String name1, List<ClassPO> classPOS,
	                                      List<ClassPO> classPOS1) {
		StringBuilder str = new StringBuilder ();
		str.append ("@Test\r\n");
		str.append ("public void deleteAll() throws SQLException {\n")
		   .append ("\t"+name.replaceAll ("PO", ""))
		   .append ("Mapper.deleteAll(")
		   .append (");\n")
		   .append ("}\n\n");
		return  str;
	}
}

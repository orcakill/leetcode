package other.auto.method;

import other.auto.entity.ClassPO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static other.auto.util.CommonUtils.toType;

public class DeleteByIdTest {
	public static StringBuilder deleteByIdTest (String name,
	                                            List<ClassPO> classPOS1) {
		StringBuilder str = new StringBuilder ();
		Date date = new Date ();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd");
		String strDate = simpleDateFormat.format (date);
		str.append ("@Test\r\n");
		str.append ("public void deleteById() throws SQLException {\n")
		   .append ("\t")
		   .append (name.replaceAll ("PO", ""))
		   .append ("Mapper.deleteById(");
		setParam (classPOS1, str, strDate);
		str.append (");\n")
		   .append ("}\n\n");
		return  str;
	}
	
	public static void setParam (List<ClassPO> classPOS1, StringBuilder str, String strDate) {
		for (int i = 0; i < classPOS1.size (); i++) {
			if(toType(classPOS1.get (i).getType ()).equals ("Date")){
				str.append ("\"")
				             .append (strDate)
				             .append ("\"");
			}
			if(toType(classPOS1.get (i).getType ()).equals ("Integer")){
				str.append (1);
			}
			if(toType(classPOS1.get (i).getType ()).equals ("String")){
				str.append ("\"测试\"");
			}
			if (i < classPOS1.size () - 1) {
				str.append (",");
			}
		}
	}
}

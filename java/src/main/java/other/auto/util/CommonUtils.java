package other.auto.util;

import other.auto.entity.ClassPO;
import util.Case;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommonUtils {
	public  static List<ClassPO> getClassPO(Class<?> c){
		List<ClassPO> classPOS=new ArrayList<> ();
		Field[] field = c.getDeclaredFields ();
		for (Field f : field) {
			f.setAccessible (true); /* 设置些属性是可以访问的*/
			String type = f.getType ()
			               .toString ();/* 得到此属性的类型*/
			String key = f.getName ();/* key:得到属性名*/
			int text = 0;
			Id id = f.getAnnotation (Id.class);
			if (id != null) {
				text = 1;
			}
			ClassPO classPO = new ClassPO ();
			classPO.setName (key);
			classPO.setType (type);
			classPO.setPk (text);
			classPOS.add (classPO);
		}
		return  classPOS;
	}
	
	public static List<ClassPO> getPk(List<ClassPO> classPOS){
		List<ClassPO> classPOS1=new ArrayList<> ();
		for (ClassPO classPO : classPOS) {
			if (classPO.getPk () == 1) {
				classPOS1.add (classPO);
			}
			
		}
		return  classPOS1;
	}
	
	public static List<ClassPO> getNoPk(List<ClassPO> classPOS){
		List<ClassPO> classPOS1=new ArrayList<> ();
		for (ClassPO classPO : classPOS) {
			if (classPO.getPk () != 1) {
				classPOS1.add (classPO);
			}
			
		}
		return  classPOS1;
	}
	
	/*将java.lang.Integer转换为Integer*/
	public static String toType (String str) {
		return str.substring (str.lastIndexOf (".") + 1);
	}
	
	/*将emailId转换为email_id*/
	public static String toLowerLine (String str) {
		if (str.length () == 0) {
			return str;
		}
		StringBuilder stringBuilder = new StringBuilder ();
		for (int i = -0; i < str.length (); i++) {
			char c = str.charAt (i);
			if (Character.isLowerCase (c)) {
				stringBuilder.append (c);
			}
			else {
				stringBuilder.append ("_")
				             .append (str.substring (i, i + 1)
				                         .toLowerCase ());
			}
		}
		return String.valueOf (stringBuilder);
	}
	
	/*将EmailId转换为emailId*/
	public static String toLower (String name) {
		return name.substring (0, 1)
		           .toLowerCase (Locale.ROOT) + name.substring (1);
	}
	
	/*将emailId转换为EmailId*/
	public static String toUpper(String name) {
		return name.substring (0, 1)
		           .toUpperCase (Locale.ROOT) + name.substring (1);
	}
	
	public static void getResultSet (List<ClassPO> classPOS, String name1, StringBuilder str) {
		for (int i = 0; i < classPOS.size (); i++) {
			str.append ("    ")
			   .append (name1)
			   .append (".set")
			   .append (Case.toUpper (classPOS.get (i)
			                                  .getName ()))
			   .append ("(resultSet.get");
			if (toType (classPOS.get (i)
			                    .getType ()).equals ("BigInteger")) {
				str.append ("BigDecimal");
			}
			else if (toType (classPOS.get (i)
			                         .getType ()).equals ("Integer")) {
				str.append ("Int");
			}
			else {
				str.append (toType (classPOS.get (i)
				                            .getType ()));
			}
			str.append ("(")
			   .append (i + 1)
			   .append ("))")
			   .append ("; \r\n");
		}
	}
	
	public static void getId (List<ClassPO> classPOS, StringBuilder str) {
		for (int i = 0; i < classPOS.size (); i++) {
			if (classPOS.get (i)
			            .getPk () == 1) {
				if(toType (classPOS.get (i).getType ()).equals ("Date")){
					str.append ("String");
				}
				else{
					str.append (toType (classPOS.get (i).getType ()));
				}
				str.append (" ")
				   .append (classPOS.get (i)
				                    .getName ());
				if (i != classPOS.size () - 1) {
					str.append (",");
				}
			}
		}
		str.delete (str.length () - 1, str.length ());
	}
}

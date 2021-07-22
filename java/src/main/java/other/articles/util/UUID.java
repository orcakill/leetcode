package other.articles.util;

public class UUID {
    public static String getUUID(){
        String uuid=UUID.getUUID();
        String uuidStr=uuid.replace("-", "");
        return uuidStr;
    }
}

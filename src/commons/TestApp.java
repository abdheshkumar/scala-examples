package commons;

/**
 * Created by abdhesh on 6/17/15.
 */
public class TestApp {
    public String testDataType(Object in) {
        if (in == null) {
            return "null";
        }
        if (in instanceof String) {
            String s = (String) in;
            return "String, length " + s.length();
        }
        if (in instanceof Integer) {
            int i = ((Integer) in).intValue();
            if (i > 0) {
                return "Natural Int";
            }
            return "Another Int";
        }
        return in.getClass().getName();
    }
}

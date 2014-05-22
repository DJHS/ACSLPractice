package derek_booleanalgebra;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        map = new HashMap<>();
        parseParans("A*(B+C)");
    }
    public static Map<String, String> map;
    static final String VI = "[01AB]";
    static final String OP = "[~\\*\\+]";
    public static void parseParans(String s){
        if(s.matches(".*("+ VI + OP + VI + ").*")){
            System.out.println("CANES!");
        }
    }
    public static void parseNot(String s){
        
    }
    public static void parseAnd(String s){
        
    }
    public static void parseOr(String s){
        
    }
}

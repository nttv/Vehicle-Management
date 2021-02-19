import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Object o1 = new Object();
        System.out.println(o1);
        change(o1);
    }

    public static void change(Object o1) {
        Object o2 = new Object();
        System.out.println(o2);
        o1 = o2;
    }
}

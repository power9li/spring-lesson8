import java.io.*;

/**
 * Created by shenli on 2017/2/11.
 */
public class TestOut {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
//        System.out.println(System.out);
//        PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream("/tmp/out.txt")){
//
//        }, true,"UTF8");
//        System.setOut(ps);
//        System.out.println("Hello");
//        System.out.println("TestOut.main");

        PrintStream pp = new PrintStream(System.out){
            @Override
            public void println(String s) {
                String a = "[HELLO] "+s;
                super.println(a);
            }
        };
        System.setOut(pp);
        System.out.println("TestOut.main = 123");
    }
}

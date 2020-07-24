package day13;

import java.math.BigInteger;
import java.util.Base64;

import static java.util.Base64.Encoder;

/**
 * @Data 2020/7/22 0022
 */
public class Test {
    public static void main(String[] args) {
        BigInteger i = new BigInteger("33333333333333333333333");
        byte[] bytes = i.toByteArray();
        System.out.println(bytes.length);
        Encoder enc = Base64.getEncoder();
        String str = enc.encodeToString(bytes);
        System.out.println(str);
    }
}

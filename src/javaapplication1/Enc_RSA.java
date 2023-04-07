/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;

/**
 *
 * @author Administrator
 */
public class Enc_RSA {
    public static void main(String[] args)throws Exception {
        String s="Hello word";
        FileInputStream f = new FileInputStream("C:\\Users\\Administrator\\Desktop\\Skey_RSA_pub.dat");
        ObjectInputStream b =new ObjectInputStream(f);
        RSAPublicKey pbk = (RSAPublicKey)b.readObject();
        BigInteger e = pbk.getPublicExponent();
        BigInteger n =pbk.getModulus();
        System.out.println("e="+e);
        System.out.println("n="+n);
        byte ptext[] = s.getBytes("UTF8");
        BigInteger m =new BigInteger(ptext);
        BigInteger c =m.modPow(e, n);
        System.out.println("c="+c);
        String cs =c.toString();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\Enc_RSA.dat")));
        out.write(cs,0,cs.length());
        out.close();
        
    }
}

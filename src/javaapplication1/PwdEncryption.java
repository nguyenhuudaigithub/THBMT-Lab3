/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.math.BigInteger;
import java.util.Scanner;
import sun.security.util.Password;

/**
 *
 * @author Administrator
 */
public class PwdEncryption {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String nhash;
        BigInteger[] ciphertext = null;
        BigInteger n = null;
        BigInteger d = null;
        System.out.println("Enter text to be encrypted");
        String password = in.nextLine();
        System.out.println("Password(input):"+password);
        RSA rsa = new RSA(8);
        n= rsa.getN();
        d=rsa.getD();
        ciphertext = rsa.encrypt(password);
        StringBuffer bf =new StringBuffer();
        for(int i=0;i<ciphertext.length;i++){
            bf.append(ciphertext[i].toString(16).toUpperCase());
            if(i!= ciphertext.length-1)
                System.out.println(" ");
        }
        String message = bf.toString();
        System.out.println();
        System.out.println("Encrypted message:"+message);
        String dhash = rsa.decrypt(ciphertext, d, n);
        System.out.println();
        System.out.println("Decrypted message:"+dhash);
    }
}

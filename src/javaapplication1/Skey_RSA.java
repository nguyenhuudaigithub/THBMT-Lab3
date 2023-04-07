/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author Administrator
 */
public class Skey_RSA {

    /**
     *
     * @param args
     */
    public static void main(String args[]) throws Exception{
    KeyPairGenerator kpg=KeyPairGenerator.getInstance("RSA");
    kpg.initialize(1024);
    KeyPair kp=kpg.genKeyPair();
    PublicKey pbkey =kp.getPublic();
    PrivateKey prkey=kp.getPrivate();
    FileOutputStream f1 =new FileOutputStream("C:\\Users\\Administrator\\Desktop\\Skey_RSA_pub.dat");
    ObjectOutputStream b1 = new ObjectOutputStream(f1);
    b1.writeObject(pbkey);
    FileOutputStream f2 = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\Skey_RSA_priv.dat");
    ObjectOutputStream b2 =new ObjectOutputStream(f2);
    b2.writeObject(prkey);
    
    
}
}

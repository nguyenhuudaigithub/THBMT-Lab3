/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Administrator
 */
public class RSA {
    int primeSize;
    BigInteger p,q;
    BigInteger N;
    BigInteger r;
    BigInteger E,D;
    public RSA(){
}
    public RSA(int primeSize){
        this.primeSize=primeSize;
        generatePrimeNumbers();
        generatePublicPrivateKeys();
    }

    private void generatePrimeNumbers() {
        Random random =new Random();
       p = BigInteger.probablePrime(primeSize/2, random);
       do{
           q=BigInteger.probablePrime(primeSize/2, random);
       }
       while(q.compareTo(p)==0);
    }

    private void generatePublicPrivateKeys() {
        N=p.multiply(q);
        r=p.subtract(BigInteger.valueOf(1));
        r=r.multiply(q.subtract(BigInteger.valueOf(1)));
        do{
            E=new BigInteger(2* primeSize,new Random());
        }while((E.compareTo(r)!=1)||(E.gcd(r).compareTo(BigInteger.valueOf(1))!=0));
        D=E.modInverse(r);
    }
    public BigInteger[]encrypt(String message){
        int i;
        byte[] temp = new byte[1];
        byte[] digits =message.getBytes();
        BigInteger[] bigdigits = new BigInteger[digits.length];
        for(i=0;i<bigdigits.length;i++){
            temp[0]=digits[i];
            bigdigits[i]=new BigInteger(temp);
            
        }
        BigInteger[] encrypted =new BigInteger[bigdigits.length];
        for(i=0;i<bigdigits.length;i++){
            encrypted[i]=bigdigits[i].modPow(E, N);
        }
        return (encrypted);
    }
    public BigInteger[]encrypt(String message,BigInteger userD,BigInteger userN){
        int i;
        byte[] temp = new byte[1];
        byte[] digits =message.getBytes();
        BigInteger[] bigdigits = new BigInteger[digits.length];
        for(i=0;i<bigdigits.length;i++){
            temp[0]=digits[i];
            bigdigits[i]=new BigInteger(temp);
            
        }
        BigInteger[] encrypted =new BigInteger[bigdigits.length];
        for(i=0;i<bigdigits.length;i++){
            encrypted[i]=bigdigits[i].modPow(userD, userN);
        }
        return (encrypted);
    }
    public String decrypt(BigInteger[]encrypted,BigInteger D,BigInteger N){
        int i;
        BigInteger[] decrypted = new BigInteger[encrypted.length];
        for(i=0;i<decrypted.length;i++)
            decrypted[i] = encrypted[i].modPow(D, N);
            char[] charArray =new char[decrypted.length];
            
            for(i=0;i<charArray.length;i++)
                charArray[i]=(char)(decrypted[i].intValue());
            return (new String(charArray));
        
    }
    public BigInteger getp(){
        return (p);
    }
     public BigInteger getq(){
        return (q);
    }
      public BigInteger getr(){
        return (r);
    }
       public BigInteger getN(){
        return (N);
    }
        public BigInteger getE(){
        return (E);
    }
         public BigInteger getD(){
        return (D);
    }
         public static void main(String[] args) throws IOException {
             {
                 System.out.println("Syntax:java RSA SIZE");
                 System.out.println("e.g.java RSA 8");
                 System.out.println("e.g.java RSA 12");
//                 System.exit(-1);
             }
             int primeSize=8;
             RSA rsa = new RSA(primeSize);
             System.out.println("Key Size:["+primeSize+"]");
             System.out.println("");
             
             System.out.println("Generated prime numbers p and q");
             System.out.println("p:["+rsa.getp().toString(16).toUpperCase()+"]");
             System.out.println("q:["+rsa.getq().toString(16).toUpperCase()+"]");
             System.out.println("");
             
             System.out.println("The public key is the pair(N,E)which will be published.");
             System.out.println("N:["+rsa.getN().toString(16).toUpperCase()+"]");
             System.out.println("E:["+rsa.getE().toString(16).toUpperCase()+"]");
             System.out.println("");
             
             
             System.out.println("The private key is the pair(N,D) which will be keep private");
             System.out.println("N:["+rsa.getN().toString(16).toUpperCase()+"]");
             System.out.println("D:["+rsa.getD().toString(16).toUpperCase()+"]");
             System.out.println("");
             
             System.out.println("plese enter message(plaintext):");
             String plaintex =(new BufferedReader(new InputStreamReader(System.in))).readLine();
             System.out.println("");
             
             
             BigInteger[]ciphertext = rsa.encrypt(plaintex);
             System.out.println("Ciphertext:[");
             
                 for(int i = 0;i<ciphertext.length;i++){
                     System.out.println(ciphertext[i].toString(16).toUpperCase()); 
                     if(i!=ciphertext.length-1)
                         System.out.println("");
             }
                 System.out.println("]");
                 System.out.println("");
                 RSA rsa1 = new RSA(8);
                 String recoveredPlaintext = rsa1.decrypt(ciphertext,rsa.getD(),rsa.getN());
                 System.out.println("RECOVER plaintex:["+recoveredPlaintext+"]");
    }
         
}  

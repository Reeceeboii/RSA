package RSARunner;

import Decrypter.Decrypt;
import Encrypter.Encrypt;
import EuclideanGCD.GCD;
import PrimeGenerator.PrimeGen;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class RSARunner {
    public static void main(String[] args){
        // primeGen class generates two arbitrarily large prime numbers, p and q
        // pass true into primeGen to use the BigInteger's probablePrime method.
        PrimeGen primeGen = new PrimeGen(true);
        GCD euclidean = new GCD();
        Encrypt enc = new Encrypt();
        Decrypt dec = new Decrypt();

        BigInteger[] publicKey = new BigInteger[2];
        BigInteger[] privateKey = new BigInteger[2];

        System.out.println("P is: " + primeGen.getP());
        System.out.println("q is: " + primeGen.getQ());

        // n = pq, it becomes the modulus component of both the private and public key
        BigInteger n = primeGen.getP().multiply(primeGen.getQ());
        publicKey[1] = n;
        privateKey[1] = n;

        System.out.println("n is: " + n.bitCount() + " bits");

        // phi function is used to calculate the number of co-primes
        BigInteger one = BigInteger.ONE;
        BigInteger phi = (primeGen.getP().subtract(one)).multiply(primeGen.getQ().subtract(one));

        BigInteger e = new BigInteger("2");
        while(e.compareTo(phi) == -1){
            BigInteger gcd = euclidean.gcd(e, phi);
            if(gcd.equals(BigInteger.ONE)){
                break;
            }else{
                e = e.add(BigInteger.ONE);
            }
        }

        publicKey[0] = e;

        BigInteger k = BigInteger.TWO;
        BigInteger d = (BigInteger.ONE.add(k.multiply(phi))).divide(publicKey[0]);

        privateKey[0] = d;

        System.out.println("Public key: (" + publicKey[0] + "," + publicKey[1] + ")");
        System.out.println("Private key: (" + privateKey[0] + "," + privateKey[1] + ")");

        String plaintextstr = "My name is Reece";
        System.out.println("\nPlain text: " + plaintextstr);

        char[] plaintext = plaintextstr.toCharArray();
        ArrayList<BigInteger> message = new ArrayList<>();
        for(int i = 0; i < plaintext.length; i++){
            message.add(BigInteger.valueOf((int)plaintext[i]));
        }

        System.out.print("Plain text as values: ");
        for(BigInteger value: message){
            System.out.print(value + " ");
        }

        System.out.println();
        message = enc.encrypt(message,publicKey);

        System.out.print("Encrypted message: ");
        for(BigInteger value: message){
            System.out.print(value);
        }

        ArrayList<BigInteger> decryptedMessage = dec.decrypt(message, privateKey);

        System.out.println();
        System.out.print("Decrypted values: ");
        for(BigInteger value: decryptedMessage){
            System.out.print(value + " ");
        }

        System.out.println();
        System.out.print("Decrypted values as text: ");
        for(BigInteger value: decryptedMessage){
            int intValue = value.intValue();
            System.out.print((char)intValue);
        }

        
    }
}

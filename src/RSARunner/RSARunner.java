package RSARunner;

import Decrypter.Decrypt;
import Encrypter.Encrypt;
import EuclideanGCD.GCD;
import PrimeGenerator.PrimeGen;

import java.math.BigInteger;
import java.util.ArrayList;


/*
 * Main class for the RSA algorithm. This generates objects for prime number generation, euclidean gcd,
 * encryption and decryption, and brings them all together in to one functional unit.
 */


public class RSARunner {
    public static void main(String[] args){
        /* primeGen class generates two arbitrarily large prime numbers, p and q.
         * pass true as a parameter into primeGen to use the BigInteger's 'probablePrime' method.
         * This is preferable over my own custom written code, as it can generate much larger primes
         * at much more efficient speeds. However, I added my own way in too just for the heck of it.
         * Change the first parameter to false to use my approach, the second parameter states the size
         * of the generated prime numbers if my method is used.
        */
        PrimeGen primeGen = new PrimeGen(false, 1000000);

        // euclidean greatest common divisor algorithm object
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


        /*
         * While e is less than the value of phi, calculate the gcd of e and phi. If this value is equal to one,
         * break out of the loop and the current e becomes our public exponent, else, increment.
         */
        BigInteger e = new BigInteger("2");
        while(e.compareTo(phi) == -1){
            BigInteger gcd = euclidean.gcd(e, phi);
            if(gcd.equals(BigInteger.ONE)){
                break;
            }else{
                e = e.add(BigInteger.ONE);
            }
        }

        // at this stage, the public key is complete
        publicKey[0] = e;


        //Solving the equation for d, which becomes the private exponent
        BigInteger k = BigInteger.TWO;
        BigInteger d = (BigInteger.ONE.add(k.multiply(phi))).divide(publicKey[0]);

        // private key is now complete
        privateKey[0] = d;

        System.out.println("Public key: (" + publicKey[0] + "," + publicKey[1] + ")");
        System.out.println("Private key: (" + privateKey[0] + "," + privateKey[1] + ")");



        //the set up is now fully complete, encryption can now begin.

        String plaintextstr = "My name is Reece";
        System.out.println("\nPlain text: " + plaintextstr);

        char[] plaintext = plaintextstr.toCharArray();
        ArrayList<BigInteger> message = new ArrayList<>();

        // the plaintext message is converted into its numeric form. ASCII values appended to a BigInteger
        // arraylist
        for(int i = 0; i < plaintext.length; i++){
            message.add(BigInteger.valueOf((int)plaintext[i]));
        }

        System.out.print("Plain text as values: ");
        for(BigInteger value: message){
            System.out.print(value + " ");
        }

        System.out.println();
        // carry out the encryption
        message = enc.encrypt(message,publicKey);

        System.out.print("Encrypted message: ");
        for(BigInteger value: message){
            System.out.print(value);
        }

        // decrypt the message back to its original ascii form
        ArrayList<BigInteger> decryptedMessage = dec.decrypt(message, privateKey);

        System.out.println();
        System.out.print("Decrypted values: ");
        for(BigInteger value: decryptedMessage){
            System.out.print(value + " ");
        }

        // convert the decrypted back to their original character equivalents, and display output
        System.out.println();
        System.out.print("Decrypted values as text: ");
        for(BigInteger value: decryptedMessage){
            int intValue = value.intValue();
            System.out.print((char)intValue);
        }

        
    }
}

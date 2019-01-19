package RSARunner;

import PrimeGenerator.PrimeGen;

import java.math.BigInteger;

public class RSARunner {
    public static void main(String[] args){
        // rsaRunner generates two large primes, p and q
        // pass true into primeGen for test values of 2 and 7 so the maths is easier to follow
        PrimeGen primeGen = new PrimeGen(true);

        System.out.println("P is: " + primeGen.getP());
        System.out.println("q is: " + primeGen.getQ());

        // n = pq, it becomes the modulus component of both the private and public key
        BigInteger n = primeGen.getP().multiply(primeGen.getQ());

        System.out.println("n is: " + n + "(" + n.bitCount() + " bits)");

        // phi function is used to calculate the number of co-primes
        BigInteger one = BigInteger.ONE;
        BigInteger phi = (primeGen.getP().subtract(one)).multiply(primeGen.getQ().subtract(one));

        System.out.println("n has " + phi + " co-primes");

        BigInteger e = new BigInteger("1");
        

    }

}

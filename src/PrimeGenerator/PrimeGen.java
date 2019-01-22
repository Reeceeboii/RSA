package PrimeGenerator;

import java.math.BigInteger;
import java.util.Random;


public class PrimeGen {

    private BigInteger p;
    private BigInteger q;
    private int primeSize;

    // constructor takes args - see RSARunner.java
    public PrimeGen(boolean useBigInt, int primeSize){

        // if my own methods are used
        if(!useBigInt) {
            boolean prime1Chosen = false;
            boolean prime2Chosen = false;

            this.primeSize = primeSize;

            // until both primes are chosen, generate a random value within the specified primeSize
            // parameter, and only continue when it's a confirmed prime
            while (!prime1Chosen) {
                p = generateNewValue();
                prime1Chosen = checkPrimality(p);
            }

            while (!prime2Chosen) {
                q = generateNewValue();
                prime2Chosen = checkPrimality(q);
            }
        // if the BigInteger methods are chosen (preferable)
        }else{
            Random rand = new Random();
            // generate two 512 bit probable prime values (chance of not prime is < 0.002%)
            p = BigInteger.probablePrime(512, rand);
            q = BigInteger.probablePrime(512, rand);
        }
    }

    // generating new possible prime values
    private BigInteger generateNewValue(){
        Random rand = new Random();
        BigInteger upperLimit = BigInteger.valueOf(primeSize);
        BigInteger returnVal;
        do{
            returnVal = new BigInteger(upperLimit.bitLength(), rand);
        }while(returnVal.compareTo(upperLimit) >= 0);

        return returnVal;
    }

    /* Checking if the randomly generated number is prime.
     * Uses the Miller-Rabin test */
    private boolean checkPrimality(BigInteger n){
        // even check
        BigInteger even = new BigInteger("2");
        if(!even.equals(n) && BigInteger.ZERO.equals(n.mod(even))){
            return false;
        }
        // check for factors aside from 1 and itself
        for(BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(n) < 1; i = i.add(even)){
            if(BigInteger.ZERO.equals(n.mod(i))){
                return false;
            }
        }
        return true;
    }

    public BigInteger getP(){
        return p;
    }

    public BigInteger getQ(){
        return q;
    }

}

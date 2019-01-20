package PrimeGenerator;

import java.math.BigInteger;
import java.util.Random;


public class PrimeGen {

    private BigInteger p;
    private BigInteger q;

    public PrimeGen(boolean useBigInt){
        if(!useBigInt) {
            boolean prime1Chosen = false;
            boolean prime2Chosen = false;

            while (!prime1Chosen) {
                p = generateNewValue();
                prime1Chosen = checkPrimality(p);
            }

            while (!prime2Chosen) {
                q = generateNewValue();
                prime2Chosen = checkPrimality(q);
            }
        }else{
            Random rand = new Random();
            p = BigInteger.probablePrime(512, rand);
            q = BigInteger.probablePrime(512, rand);
        }
    }

    private BigInteger generateNewValue(){
        Random rand = new Random();
        // TODO make this value a lot bigger when stuff works
        BigInteger upperLimit = new BigInteger("1000000");
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

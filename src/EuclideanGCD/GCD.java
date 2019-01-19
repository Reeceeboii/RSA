package EuclideanGCD;

import java.math.BigInteger;

/* Euclidean greatest common divisor algorithm. Used to choose the public key's
 * exponent. Two values x and y are co-prime if gcd(x,y) == 1 */

public class GCD {
    public BigInteger gcd(BigInteger value1, BigInteger value2){
        if(value2.equals(BigInteger.ZERO)){
            return value1;
        }
        return gcd(value2, value1.mod(value2));
    }
}

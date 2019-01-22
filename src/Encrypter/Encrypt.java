package Encrypter;

import java.math.BigInteger;
import java.util.ArrayList;

// encryption class, returns an arraylist of BigInteger encrypted ASCII values


public class Encrypt {
    public ArrayList<BigInteger> encrypt(ArrayList<BigInteger> message, BigInteger[] publicKey){
        ArrayList<BigInteger> encryptedMessage = new ArrayList<>();

        // for every value in the message, raise it to the exponent, modulo the modulus.
        // append to encrypted arraylist. Return list when done.

        for(BigInteger value: message){
            value = value.modPow(publicKey[0], publicKey[1]);
            encryptedMessage.add(value);
        }
        return encryptedMessage;
    }
}

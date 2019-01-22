package Decrypter;

import java.math.BigInteger;
import java.util.ArrayList;

// decryption class, returns an arraylist of BigInteger decrypted ASCII values

public class Decrypt {
    public ArrayList<BigInteger> decrypt(ArrayList<BigInteger> message, BigInteger[] privateKey){
        ArrayList<BigInteger> decryptedMessage = new ArrayList<>();

        // for every value in the message, raise it to the exponent, modulo the modulus.
        // append to decrypted arraylist. Return list when done.

        for(BigInteger value: message){
            value = value.modPow(privateKey[0], privateKey[1]);
            decryptedMessage.add(value);
        }
        return decryptedMessage;
    }
}

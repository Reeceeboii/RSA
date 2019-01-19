package Encrypter;

import java.math.BigInteger;
import java.util.ArrayList;

public class Encrypt {
    public ArrayList<BigInteger> encrypt(ArrayList<BigInteger> message, BigInteger[] publicKey){
        ArrayList<BigInteger> encryptedMessage = new ArrayList<>();
        for(BigInteger value: message){
            value = value.modPow(publicKey[0], publicKey[1]);
            encryptedMessage.add(value);
        }
        return encryptedMessage;
    }
}

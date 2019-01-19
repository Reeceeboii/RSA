package Decrypter;

import java.math.BigInteger;
import java.util.ArrayList;

public class Decrypt {
    public ArrayList<BigInteger> decrypt(ArrayList<BigInteger> message, BigInteger[] privateKey){
        ArrayList<BigInteger> decryptedMessage = new ArrayList<>();
        for(BigInteger value: message){
            value = value.modPow(privateKey[0], privateKey[1]);
            decryptedMessage.add(value);
        }
        return decryptedMessage;
    }
}

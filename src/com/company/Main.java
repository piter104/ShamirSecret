package com.company;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

public class Main {
    static final Integer secret = 456;
    static final Integer k = 1000;
    static final Integer n = 10;
    static final Integer t = 5;

    public static void main(String[] args) {
        System.out.println("Metoda Trywialna");

        Map<Integer, Integer> shares;
        Integer result;

        SimpleMethod simpleMethod = new SimpleMethod(new SecureRandom());
        shares = simpleMethod.generateShares(secret, k, n);
        System.out.println("Podzieliłem sekret na klucze: " + shares);

        result = simpleMethod.decipher(shares, k);
        System.out.println("Odtworzyłem sekret: " + result);

        System.out.println("--------------------------------------");
        System.out.println("Shamir Schema");

        ShamirSchema shamirSchema = new ShamirSchema(new SecureRandom());

        Integer prime = shamirSchema.generatePrime(secret, n);

        System.out.println(prime);

        List<SecretShare> secretShares = shamirSchema.split(secret, n, t, prime);
        System.out.println(secretShares);

        GFG.interpolate(secretShares, t, new BigInteger(String.valueOf(prime)));
    }
}

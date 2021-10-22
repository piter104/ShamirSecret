package com.company;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShamirSchema {
    private final SecureRandom random;

    public ShamirSchema(SecureRandom random) {
        this.random = random;
    }

    private static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))
                .allMatch(n -> number % n != 0);
    }

    private static void checker(boolean isCorrect, String message) {
        if (!isCorrect) {
            throw new IllegalArgumentException(message);
        }
    }

    public Integer generatePrime(int secret, int n) {
        return random.ints().filter(e -> e > secret && e > n && isPrime(e)).findAny().getAsInt();
    }

    public List<SecretShare> split(int secret, int n, int t, int p) {
        checker(secret >= 0, "N nie jest >=0");
        checker(t > 1, "T nie jest >1");
        checker(n >= t, "N nie jest >=T");

        List<SecretShare> shares = new ArrayList<>(n);

        // randomizing coefficients
        List<Integer> coefficients = random
                .ints(t - 1, 0, p)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());

        // adding secret as a0
        coefficients.add(0, secret);
        System.out.println(coefficients);
        String prime = String.valueOf(p);

        for (int x = 0; x < n; x++) {
            BigInteger accumulation = new BigInteger(String.valueOf(secret));

            for (int j = 1; j < t; j++) {
                accumulation = accumulation
                        .add(new BigInteger(String.valueOf(coefficients.get(j)))
                                .multiply(BigInteger.valueOf(x + 1).pow(j)));
            }

            shares.add(x, new SecretShare(new BigInteger(String.valueOf(x + 1)),
                    accumulation.mod(new BigInteger(prime))));
            System.out.println("Share " + shares.get(x));
        }
        return shares;
    }

}


package com.company;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SimpleMethod {

    private final SecureRandom random;

    public SimpleMethod(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    private static void checker(boolean isCorrect, String message) {
        if (!isCorrect) {
            throw new IllegalArgumentException(message);
        }
    }

    public Map<Integer, Integer> generateShares(int secret, int k, int n) {
        checker(secret >= 0, "N nie jest >=0");
        checker(secret <= k - 1, "N nie jest <=K-1");
        checker(n >= 2, "N nie jest >=2");

        Map<Integer, Integer> shares;
        AtomicInteger counter = new AtomicInteger(0);

        // populating with shares
        shares = random.ints(n - 1, 0, k)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toMap(e -> counter.incrementAndGet(), e -> e));

        // adding last share
        shares.put(
                counter.incrementAndGet(),
                k + (shares.values().stream().reduce(secret, (a, b) -> a - b) % k));
        return shares;
    }

    public Integer decipher(Map<Integer, Integer> shares, int k) {
        return shares
                .values()
                .stream()
                .reduce(0, Integer::sum) % k;
    }
}

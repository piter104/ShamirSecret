package com.company;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

class GFG {

    static BigInteger interpolate(List<SecretShare> f, int t, BigInteger p) {
        BigInteger result = BigInteger.ZERO;

        for (int i = 1; i < t + 1; i++) {
            BigDecimal up;
            BigDecimal down;
            BigDecimal laGrange;
            BigDecimal term = new BigDecimal(1);

            for (int j = 1; j < t + 1; j++) {
                if (j != i) {
                    up = new BigDecimal(f.get(j).getNumber().negate());

                    down = new BigDecimal(f.get(i).getNumber())
                            .subtract(new BigDecimal(f.get(j).getNumber()));

                    term = term.multiply(up.divide(down,
                            15, RoundingMode.HALF_UP));
                }
            }

            laGrange = new BigDecimal(f.get(i).getShare()).multiply(term);

            System.out.println("i: " + i + " laGrange = " + laGrange);

            result = result.add(laGrange.toBigInteger().mod(p));
            System.out.println("i: " + i + " result = " + result);
        }

        System.out.println(result.abs().mod(p));

        return result;
    }
}
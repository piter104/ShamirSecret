package com.company;

// Java program for implementation
// of Lagrange's Interpolation

import java.math.BigInteger;
import java.util.List;

class GFG {
    // function to interpolate the given
    // data points using Lagrange's formula
    static BigInteger interpolate(List<SecretShare> f, int n, BigInteger p) {
        BigInteger result = new BigInteger(String.valueOf(0)); // Initialize result
        f.remove(0);
        for (int i = 0; i < n; i++) {
            // Compute individual terms of above formula
            BigInteger term = f.get(i).getShare();
            for (int j = 0; j < n; j++) {
                if (j != i)
                    term = term.multiply(f.get(j).getNumber()
                            .divide(f.get(i).getNumber()
                                    .subtract(f.get(j).getNumber())));
            }

            // Add current term to result
            result = result.add(term);
            System.out.println("i: " + i + " = " + result);
        }
        System.out.println(result.abs().mod(p));
        return result;
    }
}

// This code is contributed by 29AjayKumar
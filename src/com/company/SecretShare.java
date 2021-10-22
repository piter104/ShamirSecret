package com.company;

import java.math.BigInteger;

public class SecretShare {
    private final BigInteger number;
    private final BigInteger share;

    public SecretShare(final BigInteger number, final BigInteger share) {
        this.number = number;
        this.share = share;
    }

    public BigInteger getNumber() {
        return number;
    }

    public BigInteger getShare() {
        return share;
    }

    @Override
    public String toString() {
        return "[number=" + number + ", share=" + share + "]";
    }
}

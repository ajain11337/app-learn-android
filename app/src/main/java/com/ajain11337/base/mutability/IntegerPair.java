package com.ajain11337.base.mutability;

/**
 * Link : https://www.interviewcake.com/concept/java/mutable
 */
class MutableIntegerPair {
    int x;
    int y;

    MutableIntegerPair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class ImmutableIntegerPair {
    private final int x;
    private final int y;

    ImmutableIntegerPair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

/**
 * Can't get fields for type private & final.
 */
class TestingPairs {
    void testing() {
        MutableIntegerPair p = new MutableIntegerPair(1, 2);
        p.x = 10;
        p.y = 20;

        ImmutableIntegerPair q = new ImmutableIntegerPair(1, 2);
    }
}
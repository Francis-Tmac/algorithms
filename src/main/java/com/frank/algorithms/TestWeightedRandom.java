package com.frank.algorithms;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author fukangyang
 * @date 2022/6/15
 * @ desc
 */

public class TestWeightedRandom {

    public static void testWeightedRandom(Function<int[], WeightedRandom> factory) {
        int[] input = { 4, 2, 1, 3, 3 };
        WeightedRandom random = factory.apply(input);
        int[] count = new int[input.length];
        for (int i = 0; i < 10000; i++) {
            int v = random.next();
            if (v < 0 || v >= input.length) {
                throw new RuntimeException("invalid random value: " + v);
            }
            count[v]++;
        }
        int sum = Arrays.stream(input).sum();
        for (int i = 0; i < input.length; i++) {
            double expectedWeight = (double) input[i] / (double) sum;
            double realWeight = (double) count[i] / 10000D;
            if (Math.abs(expectedWeight - realWeight) > 0.01) {
                throw new RuntimeException(
                        "invalid weight " + realWeight + " for index " + i + ", expected is " + expectedWeight
                );
            }
        }
    }
}

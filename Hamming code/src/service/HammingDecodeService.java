package service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HammingDecodeService extends CreationControlSums {

    private boolean isTwoPower(int value) {
        return (value & -value) == value;
    }

    private List<Integer> createControlDigits(int len) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < len; ++i) {
            if (isTwoPower(i)) {
                list.add(i);
            }
        }

        return list;
    }

    private char invertBit(char bit) {
        return (char) (bit ^ 1 + '0');
    }

    public String decodeString(String data) {
        List<Integer> controlDigits = createControlDigits(data.length());
        StringBuilder builder = new StringBuilder(data);

        int[][] controlSums = super.createControlSums(data.length() - controlDigits.size());

        Set<Integer> errorBits = IntStream.range(1, data.length() + 1)
                .boxed()
                .collect(Collectors.toSet());

        for (int[] controlSum : controlSums) {
            int sum = Arrays.stream(controlSum)
                    .map(index -> data.charAt(index - 1))
                    .sum();
            if (sum % 2 != 1) {
                errorBits.retainAll(Collections.singleton(controlSum));
            }
        }

        if (errorBits.size() != data.length()) {
            int errorIndex = errorBits.stream().findFirst().get();
            char errorBit = data.charAt(errorIndex);
            builder.setCharAt(errorIndex, invertBit(errorBit));
        }

        controlDigits.forEach(index -> builder.setCharAt(index-1, '_'));
        return builder.toString().replaceAll("_", "");

    }
}

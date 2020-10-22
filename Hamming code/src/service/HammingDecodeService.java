package service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HammingDecodeService extends HammingService {

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

        int[][] controlSums = super.createControlSums(binaryCodeDigitAmount);

        Set<Integer> errorBits = IntStream.range(1, data.length() + 1)
                .boxed()
                .collect(Collectors.toSet());

        StringBuilder error=new StringBuilder();
        for (int[] controlSum : controlSums) {
            int sum = Arrays.stream(controlSum)
                    .filter(el->el!=0)
                    .map(index -> data.charAt(index - 1))
                    .sum();
            error.append(sum%2);
//            int j=sum%2;
//            if (sum % 2 == 1) {
//                errorBits.retainAll(Collections.singleton(controlSum));
//            }
        }

        int errorIndex=Integer.parseInt(error.reverse().toString(), 2);
        if (errorIndex!=0) {
            char errorBit = data.charAt(errorIndex);
            builder.setCharAt(errorIndex, invertBit(errorBit));
        }

        controlDigits.forEach(index -> builder.setCharAt(index-1, '_'));
        return builder.toString().replaceAll("_", "");

//        if (errorBits.size() != 0) {
//            int errorIndex = errorBits.stream().findFirst().get();
//            char errorBit = data.charAt(errorIndex);
//            builder.setCharAt(errorIndex, invertBit(errorBit));
//        }
//
//        controlDigits.forEach(index -> builder.setCharAt(index-1, '_'));
//        return builder.toString().replaceAll("_", "");

    }
}

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
        return (char) ((bit-'0') ^ 1 + '0');
    }

    public String decodeString(String data) {
        List<Integer> controlDigits = createControlDigits(data.length());
        StringBuilder builder = new StringBuilder(data);

        int[][] controlSums = super.createControlSums(data.length());

//        Set<Integer> errorBits = IntStream.range(1, data.length() + 1)
//                .boxed()
//                .collect(Collectors.toSet());

        StringBuilder error=new StringBuilder();
        for (int[] controlSum : controlSums) {
            int sum = Arrays.stream(controlSum)
                    .filter(el->el!=0)
                    .map(index -> data.charAt(index - 1))
                    .sum();
            error.append(sum%2);
        }

        int errorIndex=Integer.parseInt(error.reverse().toString(), 2);
        if (errorIndex!=0) {
            char errorBit = data.charAt(errorIndex-1);
            builder.setCharAt(errorIndex-1, invertBit(errorBit));
        }

        controlDigits.forEach(index -> builder.setCharAt(index-1, '_'));
        return builder.toString().replaceAll("_", "");


    }
}

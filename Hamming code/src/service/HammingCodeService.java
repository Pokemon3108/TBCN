package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HammingCodeService extends CreationControlSums {

    private final int maxLen = 18;

    private List<Integer> insertedBits = new ArrayList<>();

    public List<Integer> getInsertedBits() {
        return insertedBits;
    }

    public int getMaxLen() {
        return maxLen;
    }

    private StringBuilder markInsertedPositions(String data) {
        StringBuilder builder = new StringBuilder(data);
        for (int i = 0, j = 1; i < binaryCodeDigitAmount; ++i) {
            builder.insert(j - 1, "_");
            j *= 2;
        }


        return builder;
    }

    private int getBinaryCodeAmount(String data) {
        int i = 1, amount=0;
        while (i<=data.length()+amount) {
            ++amount;
            i*=2;
        }

        return amount;
    }

    public String codeString(String data) {
        super.binaryCodeDigitAmount = getBinaryCodeAmount(data);
        StringBuilder builder = new StringBuilder(markInsertedPositions(data));

        int[][] controlSums = createControlSums(builder.length());
        for (int i = 0; i < binaryCodeDigitAmount; ++i) {
            int sum = Arrays.stream(controlSums[i])
                    .filter(index->index!=0)
                    .map(index -> builder.charAt(index - 1))
                    .sum() - '_';
            int position = (int) (Math.pow(2, i) - 1);
            insertedBits.add(position);
            builder.setCharAt(position, (char) (sum % 2 + '0'));
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return "Maximum data length: " + maxLen + "\n";
    }
}

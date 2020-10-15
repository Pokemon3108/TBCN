package service;

public class CreationControlSums {

    int binaryCodeDigitAmount;

    int[][] createControlSums(int len) {
        int[][] controlSum = new int[binaryCodeDigitAmount][len / 2+1];
        for (int i = 0; i < binaryCodeDigitAmount; ++i) {
            int step = (int) Math.pow(2, i);
            for (int j = (int) Math.pow(2, i), col = 0, f = 0; j <= len; ++col) {
                controlSum[i][col] = j;
                ++f;

                if (f >= step) {
                    f = 0;
                    j += Math.pow(2, i) + 1;
                } else {
                    ++j;
                }
            }
        }

        return controlSum;
    }

}

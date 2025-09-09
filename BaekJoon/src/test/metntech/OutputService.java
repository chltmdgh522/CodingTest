package test.metntech;

import test.metntech.service.Problem;

public class OutputService {

    public void printResults(int[] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.println((i + 1) + "번 문제 답: " + result[i]);
        }
    }

}

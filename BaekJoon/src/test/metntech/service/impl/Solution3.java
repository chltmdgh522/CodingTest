package test.metntech.service.impl;

import test.metntech.service.Problem;

public class Solution3 implements Problem {

    private final int[] point;

    public Solution3(int[] point) {
        this.point = point;
    }

    @Override
    public int publish() {
        int result = 0;

        for (int i : point) {
            result = Math.max(result, i);
        }
        return result;
    }
}

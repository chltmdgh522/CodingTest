package test.metntech.service.impl;

import test.metntech.service.Problem;

public class Solution2 implements Problem {

    private final int [] point;

    public Solution2(int [] point){
        this.point = point;
    }

    @Override
    public int publish() {
        int result =0;
        for (int i = point[0]; i <= point[1]; i++) {
            if (i % 2 == 1) {
                result += 1;
            }
        }

        return result;
    }
}

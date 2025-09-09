package test.metntech;

import test.metntech.service.Problem;

public class ProblemRunner {
    public int[] runAll(Problem[] problems) {
        int[] results = new int[problems.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = problems[i].publish();
        }

        return results;
    }
}

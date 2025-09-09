package test.metntech;

import test.metntech.service.Problem;
import test.metntech.service.impl.Solution1;
import test.metntech.service.impl.Solution2;
import test.metntech.service.impl.Solution3;

public class InputService {
    public Problem[] loadProblems() {

        int[][] inputs = {
                {1, 10},
                {10, 20},
                {999, 3, 44, 32}
        };

        return new Problem[]{
                ProblemFactory.create(1, inputs[0]),
                ProblemFactory.create(2, inputs[1]),
                ProblemFactory.create(3, inputs[2]),
        };
    }

}

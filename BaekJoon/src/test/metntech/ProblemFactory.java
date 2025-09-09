package test.metntech;

import test.metntech.service.Problem;
import test.metntech.service.impl.Solution1;
import test.metntech.service.impl.Solution2;
import test.metntech.service.impl.Solution3;

public class ProblemFactory {
    public static Problem create(int type, int[] input) {
        return switch (type) {
            case 1 -> new Solution1(input);
            case 2 -> new Solution2(input);
            case 3 -> new Solution3(input);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}

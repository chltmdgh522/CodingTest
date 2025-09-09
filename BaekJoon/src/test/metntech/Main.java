package test.metntech;

import test.metntech.service.Problem;
import test.metntech.service.impl.Solution1;
import test.metntech.service.impl.Solution2;

public class Main {


    /**
     *
     * A초등학교는 1학년 대상으로 수학 경진 대회를 준비하고 있습니다.1학년은 총 5반으로 선생님이 반별로 1문제씩 출제하기로 하였습니다.
     *
     * - 1반) 1 ~ 10 숫자 중에 짝수만 모두 더한 결과를 반환
     * - 2반) 10 ~ 20 숫자 중에 홀수의 개수를 반환
     * - 3반) 임의의 숫자 4개 중에 가장 큰 숫자를 반환
     *
     * 반별로 출제한 문제의 실행 결과를 출력하는 코드를 작성하십시오.
     *
     */


    public static void main(String[] args) {

        InputService inputService = new InputService();
        ProblemRunner problemRunner = new ProblemRunner();
        OutputService outputService = new OutputService();

        Problem[] problems = inputService.loadProblems();
        int[] result = problemRunner.runAll(problems);
        outputService.printResults(result);
    }


}

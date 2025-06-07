
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int []num = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);
        int result =0;
        for(int i=0; i<N; i++){
            int left =0;
            int right =N-1;
            int sum = num[i];
            while(left < right){
                int target = num[left] + num[right];
                if(sum == target){
                    if(left !=i && right !=i){ // 문제 조고너이 어떤 수가 다른 수 두개의 합임 즉 내 자신이 나를 선택할 수 없음!
                        result++;
                      break;
                    }else if(left == i){
                        left ++; // 0 0 0 0 일때 i가 1번째 이고 left랑 right 가 1번쨰 3번째일떄 left는 자신의 것을 쓸 수 없어 업!
                    }else{
                        right--; // 0 0 4 4 일때 i가 3번째 이고 left랑 right가 각각 0번쨰 3번째일떄 right는 자신의 것을 쓸 수 없으므로 다운
                    }
                }else if(sum > target){
                    left++;
                }else{
                    right--;
                }
            }
        }

        System.out.println(result);

    }
}

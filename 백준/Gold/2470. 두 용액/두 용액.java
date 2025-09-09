import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> liquid = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            liquid.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(liquid);

        int left =0;
        int right = N-1;
        int []result = new int[2];
        int min = Integer.MAX_VALUE;
        while(left < right){
            int sum = Math.abs(liquid.get(left) + liquid.get(right));
            if(min > sum){
                min = sum;
                result[0] = liquid.get(left);
                result[1] = liquid.get(right);
            }


  if(liquid.get(left) + liquid.get(right)>=0){
                right --;
            }else{
                left ++;
            }
        }

        System.out.println(result[0]+" "+ result[1]);
    }
}

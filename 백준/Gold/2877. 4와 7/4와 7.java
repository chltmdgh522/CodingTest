
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K= Integer.parseInt(br.readLine());
        
        K= K+1; 
        StringBuilder sb =new StringBuilder();
        while(K !=0){
            int number = K % 2;
            sb.append(number);
            K/=2;
        }
        
        StringBuilder result = new StringBuilder();
        for(int i= sb.toString().length()-2; i>=0; i--){
            if(sb.charAt(i) == '0'){
                result.append("4");
            }else{
                result.append("7");
            }
        }
        System.out.println(result.toString());
    }
}

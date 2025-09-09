package implement;

public class Impl {
    public static void main(String[] args) {
        char[] hiddenMessage = {
                '흐', '빅', '퍼', '송', '너', '처', '므', '텍', '키', '독',  // 3번째 송, 9번째 독
                '프', '벡', '민', '딕', '루', '미', '크', '엑', '그', '펙',  // 12번째 민
                '칙', '일', '섹', '헥', '투', '멕', '서', '덕', '니', '쿠',  // 21번째 일
                '터', '못', '생', '긴', '송', '민', '아', '킥', '안', '버',  // 36번째 아
                '저', '택', '바', '보', '잘', '부', '직', '릭', '케', '휴',  // 44번째 잘
                '머', '산', '오', '박', '갔', '코', '젝', '노', '사', '힉',  // 54번째 갔
                '더', '러', '워', '넉', '트', '다', '후', '가', '로', '츠',  // 65번째 다
                '픽', '모', '소', '무', '공', '식', '레', '택', '케', '메',
                '와', '스', '이', '구', '호', '테', '델', '팩', '믹', '겉',  // 80번째 와
                '시', '으', '즈', '이', '송', '민', '멍', '청', '이', '크',
                '저', '번', '부', '못', '해', '메', '롱', '뻐', '큐', '전'
        };

        int[] hiddenIndex1 = {3, 12, 36, 9, 21, 44, 54, 65, 80};
        int[] hiddenIndex2 = {40, 101, 74, 71, 109, 31, 104, 26, 15, 38};
        StringBuffer sb = new StringBuffer();
        
        for (int idx = 0; idx < hiddenIndex1.length; idx++) {
            sb.append(hiddenMessage[hiddenIndex1[idx]]);
        }
        System.out.println(sb.toString());

        sb = new StringBuffer();
        for (int idx = 0; idx < hiddenIndex2.length; idx++) {
            sb.append(hiddenMessage[hiddenIndex2[idx]]);
        }
        System.out.println(sb.toString());
    }


    // 3 12 36 9 21 44 54 65 80


}

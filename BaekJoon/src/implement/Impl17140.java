package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Impl17140 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (time <= 100) {
            if (r - 1 < arr.length && c - 1 < arr[0].length && arr[r - 1][c - 1] == k) {
                System.out.println(time);
                return;
            }

            if (arr.length >= arr[0].length) {
                arr = R(arr);
            } else {
                arr = C(arr);
            }

            time++;
        }

        System.out.println(-1);
    }

    private static int[][] R(int[][] arr) {
        int maxCol = 0;
        List<List<Integer>> newRows = new ArrayList<>();

        for (int[] row : arr) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int num : row) {
                if (num == 0) continue;
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }

            List<int[]> numCountList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                numCountList.add(new int[]{entry.getKey(), entry.getValue()});
            }

            numCountList.sort((a, b) -> {
                if (a[1] != b[1]) return a[1] - b[1]; // count 오름차순
                return a[0] - b[0];                   // 숫자 오름차순
            });

            List<Integer> newRow = new ArrayList<>();
            for (int[] pair : numCountList) {
                newRow.add(pair[0]);
                newRow.add(pair[1]);
            }

            maxCol = Math.max(maxCol, newRow.size());
            newRows.add(newRow);
        }

        maxCol = Math.min(maxCol, 100); // 열 최대 100
        int[][] newArr = new int[arr.length][maxCol];
        for (int i = 0; i < arr.length; i++) {
            List<Integer> row = newRows.get(i);
            for (int j = 0; j < row.size() && j < 100; j++) {
                newArr[i][j] = row.get(j);
            }
        }

        return newArr;
    }

    private static int[][] C(int[][] arr) {
        int maxRow = 0;
        List<List<Integer>> newCols = new ArrayList<>();

        int colLen = arr[0].length;
        for (int j = 0; j < colLen; j++) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i][j] == 0) continue;
                countMap.put(arr[i][j], countMap.getOrDefault(arr[i][j], 0) + 1);
            }

            List<int[]> numCountList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                numCountList.add(new int[]{entry.getKey(), entry.getValue()});
            }

            numCountList.sort((a, b) -> {
                if (a[1] != b[1]) return a[1] - b[1]; // count 오름차순
                return a[0] - b[0];                   // 숫자 오름차순
            });

            List<Integer> newCol = new ArrayList<>();
            for (int[] pair : numCountList) {
                newCol.add(pair[0]);
                newCol.add(pair[1]);
            }

            maxRow = Math.max(maxRow, newCol.size());
            newCols.add(newCol);
        }

        maxRow = Math.min(maxRow, 100); // 행 최대 100
        int[][] newArr = new int[maxRow][colLen];
        for (int j = 0; j < colLen; j++) {
            List<Integer> col = newCols.get(j);
            for (int i = 0; i < col.size() && i < 100; i++) {
                newArr[i][j] = col.get(i);
            }
        }

        return newArr;
    }
}

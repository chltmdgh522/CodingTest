package test;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    /*
     * Complete the 'getSubTeams' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. 2D_STRING_ARRAY existingTeamEdges
     *  2. STRING_ARRAY queries
     */

    public static List<Integer> getSubTeams(List<List<String>> existingTeamEdges, List<String> queries) {
        Map<String, List<String>> tree = new HashMap<>();

        for (int i = 0; i < existingTeamEdges.size(); i++) {
            String parent = existingTeamEdges.get(i).get(0);
            String child = existingTeamEdges.get(i).get(1);



            if (!tree.containsKey(parent)) {
                tree.put(parent, new ArrayList<>());
            }
            tree.get(parent).add(child);

            if (!tree.containsKey(child)) {
                tree.put(child, new ArrayList<>());
            }
        }

        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < queries.size(); i++) {
            String[] arr = queries.get(i).split(" ");
            String operation = arr[0];

            if (operation.equals("create_team")) {
                String parent = arr[1];
                String child = arr[2];

                if (!tree.containsKey(parent)) {
                    tree.put(parent, new ArrayList<>());
                }
                tree.get(parent).add(child);

                if (!tree.containsKey(child)) {
                    tree.put(child, new ArrayList<>());
                }

            } else if (operation.equals("delete_team")) {
                String team = arr[1];
                deleteAll(tree, team);

            } else if (operation.equals("count_teams")) {
                String team = arr[1];
                int cnt = countAll(tree, team);
                answer.add(cnt);
            }
        }

        return answer;
    }

    private static void deleteAll(Map<String, List<String>> tree, String team) {
        if (!tree.containsKey(team)) {
            return;
        }

        List<String> children = new ArrayList<>(tree.get(team));
        for (String child : children) {
            deleteAll(tree, child);
        }

        for (String key : tree.keySet()) {
            tree.get(key).remove(team);
        }

        tree.remove(team);
    }

    private static int countAll(Map<String, List<String>> tree, String team) {
        if (!tree.containsKey(team)) {
            return 0;
        }

        int cnt = 0;
        List<String> children = tree.get(team);

        for (String child : children) {
            cnt = cnt + 1;
            cnt = cnt + countAll(tree, child);
        }

        return cnt;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int existingTeamEdgesRows = Integer.parseInt(bufferedReader.readLine().trim());
        int existingTeamEdgesColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> existingTeamEdges = new ArrayList<>();

        IntStream.range(0, existingTeamEdgesRows).forEach(i -> {
            try {
                existingTeamEdges.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<Integer> result = Result.getSubTeams(existingTeamEdges, queries);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
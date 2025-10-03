import java.util.*;
import java.io.*;

public class Subway {
    static List<Integer>[] adj; // กราฟสาย ↔ สาย
    static Map<Integer, List<Integer>> stationToLines = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int N = Integer.parseInt(nm[0]); // จำนวนสถานี
        int M = Integer.parseInt(nm[1]); // จำนวนสาย

        // เก็บสถานีในแต่ละสาย
        List<int[]> lines = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String[] parts = br.readLine().split(" ");
            int Si = Integer.parseInt(parts[0]);
            int[] stations = new int[Si];
            for (int j = 0; j < Si; j++) {
                stations[j] = Integer.parseInt(parts[j+1]);
                stationToLines.computeIfAbsent(stations[j], k -> new ArrayList<>()).add(i);
            }
            lines.add(stations);
        }

        // สร้างกราฟสาย ↔ สาย
        adj = new ArrayList[M];
        for (int i = 0; i < M; i++) adj[i] = new ArrayList<>();
        for (List<Integer> lineList : stationToLines.values()) {
            int size = lineList.size();
            for (int i = 0; i < size; i++) {
                for (int j = i+1; j < size; j++) {
                    int a = lineList.get(i), b = lineList.get(j);
                    adj[a].add(b);
                    adj[b].add(a);
                }
            }
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            String[] ab = br.readLine().split(" ");
            int A = Integer.parseInt(ab[0]);
            int B = Integer.parseInt(ab[1]);
            sb.append(processQuery(A, B)).append("\n");
        }

        System.out.print(sb.toString());
    }

    static String processQuery(int A, int B) {
        if (!stationToLines.containsKey(A) || !stationToLines.containsKey(B)) {
            return "impossible";
        }

        List<Integer> startLines = stationToLines.get(A);
        List<Integer> endLines = stationToLines.get(B);
        Set<Integer> endSet = new HashSet<>(endLines);

        // ถ้าอยู่ในสายเดียวกัน
        for (int l : startLines) {
            if (endSet.contains(l)) return "0";
        }

        // BFS บนกราฟสาย
        int[] dist = new int[adj.length];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();

        for (int l : startLines) {
            dist[l] = 0;
            queue.add(l);
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    if (endSet.contains(v)) {
                        return String.valueOf(dist[v]);
                    }
                    queue.add(v);
                }
            }
        }

        return "impossible";
    }
}

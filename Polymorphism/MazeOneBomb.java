import java.io.*;
import java.util.*;


public class MazeOneBomb {
    static class Point { int r, c; Point(int r, int c){this.r=r;this.c=c;} }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        int rs = Integer.parseInt(st.nextToken()) - 1;
        int cs = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine().trim());
        int re = Integer.parseInt(st.nextToken()) - 1;
        int ce = Integer.parseInt(st.nextToken()) - 1;

        int[][] a = new int[M][N];
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; ++j) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] distS = bfsFrom(rs, cs, a, M, N);
        int[][] distE = bfsFrom(re, ce, a, M, N);

        int best = Integer.MAX_VALUE;
        if (distS[re][ce] > 0) best = Math.min(best, distS[re][ce]);

        int countWalls = 0;
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        for (int i=0;i<M;i++){
            for (int j=0;j<N;j++){
                if (a[i][j] != 0) continue;
                ArrayList<Integer> left = new ArrayList<>();
                ArrayList<Integer> right = new ArrayList<>();
                for (int k=0;k<4;k++){
                    int ni = i + dr[k], nj = j + dc[k];
                    if (ni<0||ni>=M||nj<0||nj>=N) continue;
                    if (distS[ni][nj] > 0) left.add(distS[ni][nj]);
                    if (distE[ni][nj] > 0) right.add(distE[ni][nj]);
                }
                if (!left.isEmpty() && !right.isEmpty()){
                    countWalls++;
                    int localBest = Integer.MAX_VALUE;
                    for (int ls : left){
                        for (int reDist : right){
                            localBest = Math.min(localBest, ls + 1 + reDist);
                        }
                    }
                    best = Math.min(best, localBest);
                }
            }
        }

        System.out.println(countWalls);
        if (best==Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(best);
    }

    static int[][] bfsFrom(int sr, int sc, int[][] a, int M, int N){
        int[][] dist = new int[M][N];
        for (int[] row : dist) Arrays.fill(row, 0);
        if (sr<0 || sr>=M || sc<0 || sc>=N) return dist;
        if (a[sr][sc] != 1) return dist;
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(sr, sc));
        dist[sr][sc] = 1;
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};
        while(!q.isEmpty()){
            Point p = q.poll();
            int d = dist[p.r][p.c];
            for (int k=0;k<4;k++){
                int ni = p.r + dr[k], nj = p.c + dc[k];
                if (ni<0||ni>=M||nj<0||nj>=N) continue;
                if (a[ni][nj] != 1) continue;
                if (dist[ni][nj] == 0){
                    dist[ni][nj] = d + 1;
                    q.add(new Point(ni, nj));
                }
            }
        }
        return dist;
    }
}

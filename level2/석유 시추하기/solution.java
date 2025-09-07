import java.util.*;

class Solution {

    static class T {
        int x, y;
        T(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;

        int[][] group = new int[n][m]; // 각 위치의 그룹 번호 저장
        boolean[][] visited = new boolean[n][m];

        // 효율성 테스트를 위한 코드! 이미 계산해본 석유들은 미리 맵으로 만들어 전체 양을 기록해두고 불러오자!
        Map<Integer, Integer> oilAmount = new HashMap<>(); // groupID -> 석유양
        Map<Integer, Set<Integer>> columnGroupMap = new HashMap<>(); // 열 번호 -> 해당 열에 걸친 groupID

        int groupId = 1;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    Queue<T> q = new LinkedList<>();
                    q.add(new T(i, j));
                    visited[i][j] = true;
                    group[i][j] = groupId;

                    int count = 1;
                    Set<Integer> columns = new HashSet<>();
                    columns.add(j);

                    while (!q.isEmpty()) {
                        T t = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = t.x + dx[k];
                            int ny = t.y + dy[k];
                            if (check(nx, ny, n, m) && land[nx][ny] == 1 && !visited[nx][ny]) {
                                visited[nx][ny] = true;
                                group[nx][ny] = groupId;
                                q.add(new T(nx, ny));
                                count++;
                                columns.add(ny);
                            }
                        }
                    }

                    oilAmount.put(groupId, count);
                    for (int col : columns) {
                        columnGroupMap.computeIfAbsent(col, k -> new HashSet<>()).add(groupId);
                    }

                    groupId++;
                }
            }
        }

        int maxOil = 0;
        for (int col = 0; col < m; col++) {
            Set<Integer> groups = columnGroupMap.getOrDefault(col, new HashSet<>());
            int sum = 0;
            for (int gid : groups) {
                sum += oilAmount.get(gid);
            }
            maxOil = Math.max(maxOil, sum);
        }

        return maxOil;
    }

    boolean check(int x, int y, int n, int m) {
        return (0 <= x && x < n) && (0 <= y && y < m);
    }
}

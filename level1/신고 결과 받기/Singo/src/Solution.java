import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, Set<String> > hm = new HashMap<>();
        HashMap<String, Integer> singo = new HashMap<>();
        int[] answer = new int[id_list.length];

        for(String id : id_list){
            singo.put(id,0);
            hm.put(id, new HashSet<String>());
        }

        for(String cur : report) {
            StringTokenizer st = new StringTokenizer(cur, " ");
            String reporter = st.nextToken();
            String reported = st.nextToken();
            if(hm.get(reporter).add(reported)) {
                singo.put(reported, singo.get(reported) + 1);          
            }
        }
        for(String cur : report) {
            StringTokenizer st = new StringTokenizer(cur, " ");
            String reporter = st.nextToken();
            String reported = st.nextToken();
            if(singo.get(reported) < k) {
                hm.get(reporter).remove(reported);          
            }
        }

        for(int i = 0 ; i<id_list.length; i++)
            answer[i] = hm.get(id_list[i]).size();


        return answer;
    }
}
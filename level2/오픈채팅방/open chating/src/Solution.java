import java.util.*;
import java.io.*;

class Solution {
	public static String[] solution(String[] record) {
        int ans = 0;
        StringTokenizer st;
        HashMap<String, String> id = new HashMap<>();
        String[] command = new String[record.length];
        String[] key = new String[record.length];

        for(int i = 0; i<record.length; i++)
        {
            st = new StringTokenizer(record[i], " ");
            String com = st.nextToken();
            if(com.equals("Enter"))
            {
                ans++;
                command[ans-1] = com;
                key[ans-1] = st.nextToken();
                id.put(key[ans-1], st.nextToken());
            }
            else if(com.equals("Change"))
            {
                id.replace(st.nextToken(), st.nextToken());
            }
            else{
                ans++;
                command[ans-1] = com;
                key[ans-1] = st.nextToken();
            }
        }
        String[] answer = new String[ans];
        for(int i = 0; i<ans; i++)
        {
            if(command[i].equals("Enter")){
                answer[i] = ( id.get(key[i]) + "´ÔÀÌ µé¾î¿Ô½À´Ï´Ù.");
            }
            else if(command[i].equals("Leave")){
                answer[i] = (id.get(key[i]) + "´ÔÀÌ ³ª°¬½À´Ï´Ù.");
            }
        }
        return answer;
    }
    
    public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] mes = new String[5];
		
		for(int i = 0; i<5; i++)
		{
			mes[i] = br.readLine();
		}
		System.out.print(solution(mes));
    }
}
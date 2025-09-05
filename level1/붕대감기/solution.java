import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
        
        int sec = attacks[0][0];
        answer = health - attacks[0][1];
        for(int i=1; i<attacks.length; i++){
            if(answer <= 0) {
                answer = -1;
                break;
            }
            
            int count = attacks[i][0] - sec-1;
            answer += (count/bandage[0]*bandage[2]) + count*bandage[1];
            
            if(answer > health) answer = health;
            answer -= attacks[i][1];
            
            sec = attacks[i][0];
            
        }
        if(answer <= 0) answer = -1;
        return answer;
    }
}

import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        class finger{
            int x;
            int y;

            finger(int x, int y)
            {
                this.x = x;
                this.y = y;
            }
        }   
        String answer = "";
        ArrayList<finger> phone = new ArrayList<>();
        int cnt = 1;
        phone.add(0,new finger(3,1));
        for(int i = 0; i<3; i++)
        {
            for(int j = 0; j<3; j++)
            {
                phone.add(cnt,new finger(i,j));
                cnt++;
            }
        }
        finger left = new finger(3,0);
        finger right = new finger(3,2);

        for(int i = 0; i<numbers.length; i++)
        {
            if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7)
            {
                answer += "L";
                left = phone.get(numbers[i]);
            }
            else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){
                answer += "R";
                right = phone.get(numbers[i]);
            }
            else
            {
                finger dis = phone.get(numbers[i]);
                int ldis = Math.abs(dis.x-left.x) + Math.abs(dis.y-left.y);
                int rdis = Math.abs(dis.x-right.x) + Math.abs(dis.y-right.y);
                if(ldis<rdis)
                {
                    answer += "L";
                    left = dis;
                }
                else if(rdis<ldis)
                {
                    answer += "R";
                    right = dis;
                }
                else
                {
                    if(hand.equals("left"))
                    {
                        answer += "L";
                        left = dis;
                    }
                    else
                    {
                        answer += "R";
                        right = dis;
                    }
                }
            }
        }

        return answer;
    }
}

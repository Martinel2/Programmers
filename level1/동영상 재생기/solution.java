import java.util.*;
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        //prev -> 10초 전 -> 10초 미만인 경우 0초로
        //next -> 10초 후 -> 남은 시간이 10초 미만 -> video_len으로
        //pos: 재생위치, op_start: 오프닝 시작, op_end: 오프닝 끝나는 시각
        //pos가 op사이에 있으면 op_end로 이동
        
        //전부 초단위로 변환
        int v = Integer.parseInt(video_len.split(":")[0]) * 60 + Integer.parseInt(video_len.split(":")[1]);
        int p = Integer.parseInt(pos.split(":")[0]) * 60 + Integer.parseInt(pos.split(":")[1]);
        int ops = Integer.parseInt(op_start.split(":")[0]) * 60 + Integer.parseInt(op_start.split(":")[1]);
        int ope = Integer.parseInt(op_end.split(":")[0]) * 60 + Integer.parseInt(op_end.split(":")[1]);

        //현재 재생 위치가 오프닝 구간인 경우
        if(p >= ops && p <= ope){
            p = ope;
        }
        
        //사용자의 입력 확인
        for(String command : commands){          
            //10초 전으로 이동일 때
            if(command.equals("prev")){
                //현재 위치가 10초 미만일 때
                if(p < 10){
                    p = 0;
                }else{
                    p -= 10;
                }
            }else{
                //남은 시간이 10초 미만일 때
                if(v - p < 10){
                    p = v;
                }else{
                    p += 10;
                }
            }
            //현재 재생 위치가 오프닝 구간인 경우
            if(p >= ops && p <= ope){
                p = ope;
            }
        }
        
        String answer = String.format("%02d:%02d", p/60, p%60);
        return answer;
    }
}

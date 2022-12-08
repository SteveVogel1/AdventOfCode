package twentytwo.daySix;

import twentytwo.helper.DailyTask;

import java.util.*;

public class DaySix implements DailyTask {

    public static void main(String[] args) {
        DaySix day = new DaySix();
        day.run();
    }

    public String getFileName() {
        return "src/twentytwo/daySix/input.txt";
    }



    public String taskA(List<String> input){
        String line = input.get(0);
        return getMarkerPosition(line, 4);
    }

    private String getMarkerPosition(String line, int length) {
        for(int i = length; i < line.length(); i++){
            if(isMarker(line.substring(i-length, i))){
                return String.valueOf(i);
            }
        }
        return "";
    }

    private boolean isMarker(String marker){
        for(int i = 0; i < marker.length(); i++) {
            if(marker.indexOf(String.valueOf(marker.charAt(i)), i+1) >=0){
                return false;
            }
        }
        return true;
    }
    public String taskB(List<String> input){
        String line = input.get(0);
        return getMarkerPosition(line, 14);
    }
}

package twentythree.d11;

import twentytwo.helper.DailyTask;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DayEleven implements DailyTask {

    public static void main(String[] args) {
        DayEleven d = new DayEleven();
        d.run();
    }
    List<Integer> doubleColumns;
    List<List<Character>> universe;

    class Galaxy{
        int x;
        int y;

        int number;

        public Galaxy(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }

        public long distance(Galaxy other){
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y)
                    ;
        }
    }

    public String taskA(List<String> input){
        doubleColumns = new ArrayList<>();
        universe = new ArrayList<>();
        for (String s : input) {
            ArrayList<Character> row = new ArrayList<>();
            universe.add(row);
            for (char c : s.toCharArray()) {
                row.add(c);
            }
            if (!s.contains("#")) {
                universe.add(new ArrayList<>(Collections.nCopies(row.size(), ' ')));
            }
        }
        for(int x = 0; x < universe.get(0).size(); x++){
            boolean containsGalaxy = false;
            for (List<Character> characters : universe) {
                if (characters.get(x) == '#') {
                    containsGalaxy = true;
                    break;
                }
            }
            if(!containsGalaxy) {
                doubleColumns.add(x);
            }
        }

        int counter = 0;
        for(Integer i: doubleColumns){
            for (List<Character> characters : universe) {
                characters.add(i+counter, ' ');
            }
            counter++;
        }

        List<Galaxy> galaxies = new ArrayList<>();
        counter = 1;
        for(int y = 0; y < universe.size(); y++){
            for(int x = 0; x < universe.get(0).size(); x++){
                if(universe.get(y).get(x) == '#'){
                    galaxies.add(new Galaxy(x, y, counter++));
                }
            }
        }

        long sum = 0;

        for(int i = 0; i < galaxies.size(); i++){
            for(int j = i + 1; j < galaxies.size(); j++){
                long distance =  galaxies.get(i).distance(galaxies.get(j));
                sum += distance;
//                System.out.println("G" + galaxies.get(i).number + " - G " + galaxies.get(j).number + " - " + distance);
            }
        }


        return String.valueOf(sum);
    }

    public String taskB(List<String> input){

        return String.valueOf("");
    }
}

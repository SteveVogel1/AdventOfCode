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
    List<List<String>> universe;

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
            long additionalSum = 0;
            for(int x1 = Math.min(this.x, other.x) + 1; x1 < Math.max(this.x, other.x); x1++){
                if(!universe.get(0).get(x1).equals(".") && !universe.get(0).get(x1).equals("#")){
                    additionalSum += Long.parseLong(universe.get(0).get(x1));
                }
            }

            for(int y1 = Math.min(this.y, other.y) + 1; y1 < Math.max(this.y, other.y); y1++){
                if(!universe.get(y1).get(0).equals(".") && !universe.get(y1).get(0).equals("#")){
                    additionalSum += Long.parseLong(universe.get(y1).get(this.x));
                }
            }

            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + additionalSum;
        }
    }

    public String taskA(List<String> input){
        doubleColumns = new ArrayList<>();
        universe = new ArrayList<>();
        parseUniverse(input, 2);


        List<Galaxy> galaxies = getGalaxies();
        long sum = calculateDistances(galaxies);


        return String.valueOf(sum);
    }

    private static long calculateDistances(List<Galaxy> galaxies) {
        long sum = 0;

        for(int i = 0; i < galaxies.size(); i++){
            for(int j = i + 1; j < galaxies.size(); j++){
                long distance =  galaxies.get(i).distance(galaxies.get(j));
                sum += distance;
            }
        }
        return sum;
    }

    private List<Galaxy> getGalaxies() {
        List<Galaxy> galaxies = new ArrayList<>();
        int counter = 1;
        for(int y = 0; y < universe.size(); y++){
            for(int x = 0; x < universe.get(0).size(); x++){
                if(universe.get(y).get(x).equals("#")){
                    galaxies.add(new Galaxy(x, y, counter++));
                }
            }
        }
        return galaxies;
    }

    private void parseUniverse(List<String> input, int amountOfRows) {
        for (String line : input) {
            ArrayList<String> row = new ArrayList<>();
            universe.add(row);
            if (!line.contains("#")) {
                row.addAll(new ArrayList<>(Collections.nCopies(line.length(), String.valueOf(amountOfRows-1))));
            }else{
                row.addAll(Arrays.asList(line.split("")));
            }
        }
        for(int x = 0; x < universe.get(0).size(); x++){
            boolean containsGalaxy = false;
            for (List<String> characters : universe) {
                if (characters.get(x).equals("#")) {
                    containsGalaxy = true;
                    break;
                }
            }
            if(!containsGalaxy) {
                doubleColumns.add(x);
            }
        }


        for(Integer dc: doubleColumns){
            for (List<String> characters : universe) {
                characters.set(dc, String.valueOf(amountOfRows-1));
            }
        }
    }

    public String taskB(List<String> input){
        doubleColumns = new ArrayList<>();
        universe = new ArrayList<>();
        parseUniverse(input,1000000);


        List<Galaxy> galaxies = getGalaxies();
        long sum = calculateDistances(galaxies);


        return String.valueOf(sum);
    }
}

package twentyfour.d8;

import twentytwo.helper.DailyTask;

import java.util.*;

public class Eight implements DailyTask {
    public static void main(String[] args) {
        Eight day = new Eight();
        day.run();
    }

    public String taskA(List<String> input){
        long result = 0;

        int xMax = input.getFirst().length();
        int yMax = input.size();

        Map<String, List<Coordinate>> initMap = createInitMap(input);
        Set<Coordinate> antiNodes = createAntiNodesA(initMap);

        result = antiNodes.stream().filter(c -> c.isInbound(xMax, yMax)).count();

        return String.valueOf(result);
    }

    private Set<Coordinate> createAntiNodesA(Map<String, List<Coordinate>> initMap) {
        Set<Coordinate> antiNodes = new HashSet<>();
        for(List<Coordinate> coordinates : initMap.values()){
            for(int i = 0; i < coordinates.size(); i++){
                for(int j = 0; j < coordinates.size(); j++){
                    if(i != j) {
                        antiNodes.add(coordinates.get(j).createAntiNode(coordinates.get(i)));
                    }
                }
            }
        }
        return antiNodes;
    }

    private Set<Coordinate> createAntiNodesB(Map<String, List<Coordinate>> initMap, int xMax, int yMax) {
        Set<Coordinate> antiNodes = new HashSet<>();
        for(List<Coordinate> coordinates : initMap.values()){
            for(int i = 0; i < coordinates.size(); i++){
                for(int j = 0; j < coordinates.size(); j++){
                    antiNodes.add(coordinates.get(j));
                    if(i != j) {
                        int counter = 1;
                        Coordinate nextAntiNode;
                        do {
                            nextAntiNode = coordinates.get(j).createAntiNode(coordinates.get(i), counter++);
                            antiNodes.add(nextAntiNode);
                        }while(nextAntiNode.isInbound(xMax, yMax));
                    }
                }
            }
        }
        return antiNodes;
    }

    private Map<String, List<Coordinate>> createInitMap(List<String> input) {
        Map<String, List<Coordinate>> initMap = new HashMap<>();
        for(int y = 0; y < input.size(); y++){
            String line = input.get(y);
            for(int x = 0; x < line.length(); x++){
                String character = String.valueOf(line.charAt(x));
                if(!character.equals(".")){
                    initMap.computeIfAbsent(character, c -> new ArrayList<>()).add(new Coordinate(x, y));
                }
            }
        }
        return initMap;
    }

    public String taskB(List<String> input){
        long result = 0;

        int xMax = input.getFirst().length();
        int yMax = input.size();

        Map<String, List<Coordinate>> initMap = createInitMap(input);
        Set<Coordinate> antiNodes = createAntiNodesB(initMap, xMax, yMax);

        result = antiNodes.stream().filter(c -> c.isInbound(xMax, yMax)).count();

        return String.valueOf(result);
    }

    private record Coordinate(int x, int y){
        Coordinate createAntiNode(Coordinate c){
            return createAntiNode(c, 1);
        }

        Coordinate createAntiNode(Coordinate c, int step){
            int antiNodeX = c.x + (c.x - x) * step;
            int antiNodeY = c.y + (c.y - y) * step;
            return new Coordinate(antiNodeX, antiNodeY);
        }

        boolean isInbound(int xMax, int yMax){
            return x >= 0 && x < xMax && y >= 0 && y < yMax;
        }
    }
}
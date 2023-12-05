package twentythree.d5;

import twentytwo.helper.DailyTask;

import java.util.*;

public class DayFive implements DailyTask {

    public static void main(String[] args) {
        DayFive dayOne = new DayFive();
        dayOne.run();
    }

    public String getFileName() {
        return "src/twentythree/d5/input.txt";
    }


    Long[] initSet;
    List<Converter> seedToSoil = new ArrayList<>();
    List<Converter> soilToFertilizer = new ArrayList<>();
    List<Converter> fertilizerToWater = new ArrayList<>();
    List<Converter> waterToLight = new ArrayList<>();
    List<Converter> lightToTemperature = new ArrayList<>();
    List<Converter> temperatureToHumidity = new ArrayList<>();
    List<Converter> humidityToLocation = new ArrayList<>();
    List<List<Converter>> converterList = List.of(seedToSoil, soilToFertilizer, fertilizerToWater, waterToLight, lightToTemperature, temperatureToHumidity, humidityToLocation);

    public String taskA(List<String> input){

        parseInput(new ArrayList<>(input), true);

        for(List<Converter> converters: converterList){
            for(int i = 0; i < initSet.length; i++){
                for(Converter c : converters){
                    if(c.isInSourceRange(initSet[i])){
                        initSet[i] =  c.convert(initSet[i]);
                        break;
                    }
                }
            }
        }


        long res = Arrays.stream(initSet).min(Long::compareTo).get();
        return String.valueOf(res);
    }

    void parseInput(List<String> input, boolean taskA){
        List<Converter> currentList = new ArrayList<>();
        String[] seed = input.get(0).substring(7).trim().split(" ");
        if(taskA) {
            initSet = new Long[seed.length];
            for (int i = 0; i < seed.length; i++) {
                initSet[i] = Long.valueOf(seed[i].trim());
            }
        }else{
            List<Long> initList = new ArrayList<>();
            for (int i = 0; i < seed.length; i+=2) {
                long start = Long.parseLong(seed[i]);
                long range = Long.parseLong(seed[i+1]);
                for(int x = 0; x < range; x++){
                    initList.add(start+x);
                }
            }
            initSet = initList.toArray(new Long[]{});
        }
        input.remove(0);
        input.remove(0);
        for(String line : input){
            if(!line.isEmpty()) {
                if (isSetLine(line)) {
                    currentList = getList(line);
                } else {
                    String[] x = line.split(" ");
                    currentList.add(new Converter(x[0], x[1], x[2]));
                }
            }
        }
    }

    boolean isSetLine(String line){
        return !List.of("0", "1", "2", "3", "4","5","6","7","8", "9").contains(line.substring(0, 1));
    }

    public List<Converter> getList(String line){
        return switch (line){
            case "seed-to-soil map:" -> seedToSoil;
            case "soil-to-fertilizer map:" -> soilToFertilizer;
            case "fertilizer-to-water map:" -> fertilizerToWater;
            case "water-to-light map:" -> waterToLight;
            case "light-to-temperature map:" -> lightToTemperature;
            case "temperature-to-humidity map:" -> temperatureToHumidity;
            case "humidity-to-location map:" -> humidityToLocation;
            default -> null;
        };
    }

    class Converter{
        long startDestination;
        long startSource;
        long range;
        long dif;

        public Converter(String startDestination, String startSource, String range) {
            this.startDestination = Long.parseLong(startDestination);
            this.startSource = Long.parseLong(startSource);
            this.range = Long.parseLong(range);
            this.dif = this.startDestination - this.startSource;
        }

        public boolean isInSourceRange(long value){
            return value >= startSource && value <= startSource + range;
        }

        public Long convert(long value){
            return value + this.dif;
        }
    }

    public String taskB(List<String> input){
//
//        parseInput(input, false);
//
//        for(List<Converter> converters: converterList){
//            for(int i = 0; i < initSet.length; i++){
//                for(Converter c : converters){
//                    if(c.isInSourceRange(initSet[i])){
//                        initSet[i] =  c.convert(initSet[i]);
//                        break;
//                    }
//                }
//            }
//        }
//
//
//        long res = Arrays.stream(initSet).min(Long::compareTo).get();
//        return String.valueOf(res);
        return "";
    }

}

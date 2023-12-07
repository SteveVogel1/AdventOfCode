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
    List<IRange> initList;
    List<Converter> seedToSoil = new ArrayList<>();
    List<Converter> soilToFertilizer = new ArrayList<>();
    List<Converter> fertilizerToWater = new ArrayList<>();
    List<Converter> waterToLight = new ArrayList<>();
    List<Converter> lightToTemperature = new ArrayList<>();
    List<Converter> temperatureToHumidity = new ArrayList<>();
    List<Converter> humidityToLocation = new ArrayList<>();
    List<List<Converter>> converterList = List.of(seedToSoil, soilToFertilizer, fertilizerToWater, waterToLight, lightToTemperature, temperatureToHumidity, humidityToLocation);

    public String taskA(List<String> input){

        parseInput(new ArrayList<>(input));

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

    void parseInput(List<String> input){
        List<Converter> currentList = new ArrayList<>();
        String[] seed = input.get(0).substring(7).trim().split(" ");

        initSet = new Long[seed.length];
        for (int i = 0; i < seed.length; i++) {
            initSet[i] = Long.valueOf(seed[i].trim());
        }
        initList = new ArrayList<>();
        for (int i = 0; i < seed.length; i+=2) {
            long start = Long.parseLong(seed[i]);
            long range = Long.parseLong(seed[i+1]);
            initList.add(new Range(start, range));
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
        for(List<Converter> converters : converterList){
            converters.sort(Comparator.comparingLong(Converter::getStart));
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

    public String taskB(List<String> input){

        Stack<IRange> resultRanges = getConvertedStack(initList, converterList);

        long res = resultRanges.stream().map(c -> c.getStart()).min(Long::compareTo).get();
        return String.valueOf(res);
    }

    public Stack<IRange> getConvertedStack(List<IRange> initList, List<List<Converter>> converterList){
        Stack<IRange> currentRanges = new Stack<>();
        currentRanges.addAll(initList);
        for(List<Converter> converters: converterList){
            List<IRange> nextRanges = new ArrayList<>();

            while(!currentRanges.empty()) {
                IRange r = currentRanges.pop();
                boolean hasSplitted = false;
                for (Converter c : converters) {
                    if (hasSplitted) continue;
                    if (r.getStart() < c.getStart()) {
                        nextRanges.add(new RangeFix(r.getStart(), Math.min(r.getEnd(), c.getStart()-1)));
                        hasSplitted = true;
                    }
                    if (r.getStart() <= c.getEnd() && r.getEnd() >= c.getStart()) {
                        nextRanges.add(new RangeFix(c.convert(Math.max(r.getStart(), c.getStart())), c.convert(Math.min(r.getEnd(), c.getEnd()))));
                        hasSplitted = true;
                    }
                    if (r.getEnd() > c.getEnd() && r.getStart() <= c.getEnd()) {
                        currentRanges.push(new RangeFix(Math.max(r.getStart(), c.getEnd()+1), r.getEnd()));
                        hasSplitted = true;
                    }
                }
                if(!hasSplitted){
                    nextRanges.add(new RangeFix(r.getStart(), r.getEnd()));
                }
            }
            currentRanges.addAll(nextRanges);

        }
        return currentRanges;
    }

}

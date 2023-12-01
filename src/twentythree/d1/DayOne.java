package twentythree.d1;

import twentytwo.helper.DailyTask;

import java.util.*;

public class DayOne implements DailyTask {

    public static void main(String[] args) {
        DayOne dayOne = new DayOne();
        dayOne.run();
    }

    public String getFileName() {
        return "src/twentythree/d1/input.txt";
    }

    List<Character> number = List.of('1', '2', '3', '4','5','6','7','8','9');
    List<String> numberWritten = List.of("1", "2", "3", "4","5","6","7","8","9", "one", "two", "three", "four","five","six","seven","eight","nine");
    public String taskA(List<String> input){
        int sum = 0;
        for(String line : input){
            char[] chars = line.toCharArray();
            char firstChar = ' ';
            char lastChar = ' ';
            for(char c : chars){
                if(number.contains(c)){
                    firstChar = c;
                    break;
                }
            }

            for(int i = chars.length-1; i >= 0; i--){
                if(number.contains(chars[i])){
                    lastChar = chars[i];
                    break;
                }
            }

            sum += Integer.parseInt(String.valueOf(firstChar) + String.valueOf(lastChar));

        }

        return String.valueOf(sum);
    }

    public String taskB(List<String> input){
        int sum = 0;
        for(String line : input){
            String firstChar = null;
            String lastChar = null;
            Map<Integer, String> valuesMin = new HashMap<>();
            Map<Integer, String> valuesMax = new HashMap<>();

            for(String number : numberWritten){
                valuesMin.put(line.indexOf(number), number);
                valuesMax.put(line.lastIndexOf(number), number);
            }

            for(int i = 0; i <= line.length(); i++){
                if(valuesMin.containsKey(i)){
                    if(firstChar == null) {
                        firstChar = valuesMin.get(i);
                    }
                }
                if(valuesMax.containsKey(i)){
                        lastChar = valuesMax.get(i);
                }
            }

            sum += Integer.parseInt(getNumber(firstChar) + getNumber(lastChar));

        }

        return String.valueOf(sum);
    }

    private String getNumber(String value){
        int index = numberWritten.indexOf(value);
        if(index >= numberWritten.size()/2){
            return numberWritten.get(index -  (numberWritten.size()/2));
        }
        return value;
    }
}

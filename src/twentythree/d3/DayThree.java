package twentythree.d3;

import twentytwo.helper.DailyTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayThree implements DailyTask {

    public static void main(String[] args) {
        DayThree dayOne = new DayThree();
        dayOne.run();
    }

    public String getFileName() {
        return "src/twentythree/d3/input.txt";
    }


    List<Character> numbers = List.of('0', '1', '2', '3', '4','5','6','7','8','9');
    public String taskA(List<String> input){
        List<Number> numbersInGrid = new ArrayList<>();
        List<Symbol> symbolsInGrid = new ArrayList<>();
        int row = 0;
        for(String line : input){
            Number n = new Number();
            n.row = row;
            StringBuilder currentNumber = new StringBuilder();

            int column = 0;
            for(char c : line.toCharArray()){
                if(numbers.contains(c)){
                    if(n.columStart == -1){
                        n.columStart = column;
                    }
                    currentNumber.append(c);
                }else{
                    if(currentNumber.length() > 0) {
                        n.value = Integer.parseInt(currentNumber.toString());
                        numbersInGrid.add(n);
                        n = new Number();
                        n.row = row;
                        currentNumber.setLength(0);
                    }

                    if(c !='.'){
                        symbolsInGrid.add(new Symbol(row, column, c));
                    }
                }
                column++;
            }

            if(currentNumber.length() > 0) {
                n.value = Integer.parseInt(currentNumber.toString());
                numbersInGrid.add(n);
                n = new Number();
                n.row = row;
                currentNumber.setLength(0);
            }

            row++;
        }

        int sum = 0;
        for(Number n : numbersInGrid){
            boolean isIn = false;
            for(Symbol s : symbolsInGrid) {
                if(n.connectsToSymbol(s)){
                    sum += n.value;
//                    System.out.println(n.value + " is in");
                    isIn = true;
                    break;
                }
            }
            if(!isIn){
//                System.out.println(n.value + " is not in");
            }
        }

    return String.valueOf(sum);
    }

    class Number{
        int row = -1;
        int columStart = -1;
        int value;

        boolean connectsToSymbol(Symbol s){
//            return row -1 <= s.row && row + 1 >= s.row //
//                    && getColumStart() -1 <= s.column && getColumnEnd() + 1 >= s.column;
            return (row == s.row || row-1 == s.row || row + 1 == s.row) //
                    && getColumStart() -1 <= s.column && getColumnEnd() + 1 >= s.column;
        }

        int getColumStart(){
            return columStart;
        }

        int getColumnEnd(){
            return getColumStart() + getLength() - 1;
        }

        int getLength(){
            return String.valueOf(value).length();
        }
    }

    class Symbol{
        Symbol(int row, int column, char symbol){
            this.row = row;
            this.column = column;
            this.symbol = symbol;
        }
        int row;
        int column;
        char symbol;
    }

    public String taskB(List<String> input){
        List<Number> numbersInGrid = new ArrayList<>();
        List<Symbol> symbolsInGrid = new ArrayList<>();
        int row = 0;
        for(String line : input){
            Number n = new Number();
            n.row = row;
            StringBuilder currentNumber = new StringBuilder();

            int column = 0;
            for(char c : line.toCharArray()){
                if(numbers.contains(c)){
                    if(n.columStart == -1){
                        n.columStart = column;
                    }
                    currentNumber.append(c);
                }else{
                    if(currentNumber.length() > 0) {
                        n.value = Integer.parseInt(currentNumber.toString());
                        numbersInGrid.add(n);
                        n = new Number();
                        n.row = row;
                        currentNumber.setLength(0);
                    }

                    if(c !='.'){
                        symbolsInGrid.add(new Symbol(row, column, c));
                    }
                }
                column++;
            }

            if(currentNumber.length() > 0) {
                n.value = Integer.parseInt(currentNumber.toString());
                numbersInGrid.add(n);
                n = new Number();
                n.row = row;
                currentNumber.setLength(0);
            }

            row++;
        }


        int sum = 0;
        for(Symbol s : symbolsInGrid) {
            List<Number> neighboors = new ArrayList<>();
            for(Number n : numbersInGrid){
                if(n.connectsToSymbol(s)){
                   neighboors.add(n);
                }
            }
            if(neighboors.size() == 2){
                sum += neighboors.get(0).value * neighboors.get(1).value;
            }
        }

        return String.valueOf(sum);
    }

}

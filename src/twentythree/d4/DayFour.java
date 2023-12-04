package twentythree.d4;

import twentytwo.helper.DailyTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DayFour implements DailyTask {

    public static void main(String[] args) {
        DayFour dayOne = new DayFour();
        dayOne.run();
    }

    public String getFileName() {
        return "src/twentythree/d4/input.txt";
    }

    public String taskA(List<String> input){

        List<Card> cards = new ArrayList<>();
        for(String line : input){
            String cardValues = line.split(":")[1].replace("  ", " ");
            String[] values = cardValues.split("\\|");
            cards.add(new Card(new Values(values[0]), new Values(values[1])));
        }

        long result = cards.stream().map(c -> c.gameValue).reduce(Integer::sum).get();

    return String.valueOf(result);
    }

    class Card{
        Values expected;
        Values actual;
        int gameValue = 0;
        int index = 0;
        int amountOfNextCards = 0;

        public Card(Values expected, Values actual) {
            this.expected = expected;
            this.actual = actual;

            for(String v : expected.values){
                if(actual.values.contains(v)){
                    if(gameValue == 0){
                        gameValue = 1;
                    }else {
                        gameValue *= 2;
                    }
                }
            }

            for(String v : expected.values){
                if(actual.values.contains(v)){
                    amountOfNextCards++;
                }
            }
        }

        public Card(Values expected, Values actual, int index) {
            this(expected, actual);
            this.index = index;
        }
    }

    class Values{
        List<String> values = new ArrayList<>();
        public Values(String inputs){
            for(String v : inputs.trim().split(" ")){
                values.add(v.trim());
            }
        }
    }
    public String taskB(List<String> input){
        List<Card> initialCards = new ArrayList<>();
        int index = 0;
        for(String line : input){
            String cardValues = line.split(":")[1].replace("  ", " ");
            String[] values = cardValues.split("\\|");
            initialCards.add(new Card(new Values(values[0]), new Values(values[1]), index));
            index++;
        }

        Stack<Card> cards = new Stack<>();
        cards.addAll(initialCards);

        int sum = 0;
        while(!cards.empty()){
            sum++;
            Card c = cards.pop();
            for(int i = 0; i < c.amountOfNextCards; i++){
                cards.push(initialCards.get(1 + c.index + i));
            }
        }



        return String.valueOf(sum);
    }

}

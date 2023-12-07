package twentythree.d7;

import twentytwo.helper.DailyTask;

import java.util.*;

public class DaySeven implements DailyTask {

    public static void main(String[] args) {
        DaySeven dayOne = new DaySeven();
        dayOne.run();
    }

    public String getFileName() {
        return "src/twentythree/d7/input.txt";
    }


    enum Card{
        ACE("A", "14"),
        KING("K", "13"),
        QUEEN("Q","12"),
        JACK("J","11"),
        TEN("T","10"),
        NINE("9","09"),
        EIGHT("8","08"),
        SEVEN("7","07"),
        SIX("6","06"),
        FIVE("5","05"),
        FOUR("4","04"),
        THREE("3", "03"),
        TWO("2", "02"),
        JOKER("*", "01")
        ;

        public final String c;
        public final String value;

        Card(String c, String value) {
            this.c = c;
            this.value = value;
        }

        private static final Map<String, Card> lookup = new HashMap<>();
        static {
            for (Card c : Card.values()) {
                lookup.put(c.c, c);
            }
        }

        public static Card get(String c) {
            return lookup.get(c);
        }
    }

    class Hand{
        Map<Card, Integer> cards;
        int bid;
        String unparsedHand = "";
        String parsedHand = "";

        public Hand(String cardsAsString, int bid) {
            this.unparsedHand = cardsAsString;
            this.cards = new HashMap<>();
            String[] cardsInput = cardsAsString.split("");
            for(String c : cardsInput){
                Card card = Card.get(c);
                parsedHand = parsedHand + card.value;
                if(this.cards.containsKey(card)){
                    this.cards.put(card, this.cards.get(card) + 1);
                }else{
                    this.cards.put(card, 1);
                }
            }

            if(cards.containsKey(Card.JOKER)) {
                int i = cards.get(Card.JOKER);
                cards.remove(Card.JOKER);
                for (Card c : cards.keySet()) {
                    cards.put(c, cards.get(c) + i);
                }
            }

            this.bid = bid;
        }

        int compareHand(Hand hand){
            return Long.compare(this.getValue(), hand.getValue());
        }
        long getValue(){
            HandType ht = HandType.getType(cards);
            String typeValue = switch(ht){
                case FIVE_OF_A_KIND -> "7";
                case FOUR_OF_A_KIND -> "6";
                case FULL_HOUSE -> "5";
                case THREE_OF_A_KIND -> "4";
                case TWO_PAIR -> "3";
                case PAIR -> "2";
                case HIGHEST_CARD -> "1";
            };
            return Long.parseLong(typeValue + parsedHand);
        }
    }

    enum HandType{
        FIVE_OF_A_KIND,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        THREE_OF_A_KIND,
        TWO_PAIR,
        PAIR,
        HIGHEST_CARD;

        static HandType getType(Map<Card, Integer> cards){
            if(cards.size() == 1){
                return HandType.FIVE_OF_A_KIND;
            }else if(cards.size() == 2){
                if(cards.containsValue(4)) {
                    return HandType.FOUR_OF_A_KIND;
                }else{
                    return HandType.FULL_HOUSE;
                }
            }else if(cards.size() == 3){
                if(cards.containsValue(3)){
                    return HandType.THREE_OF_A_KIND;
                }else{
                    return HandType.TWO_PAIR;
                }
            }else if(cards.size() == 4){
                return HandType.PAIR;
            }else if(cards.size() == 5){
                return HandType.HIGHEST_CARD;
            }else{
                return HandType.FIVE_OF_A_KIND;
            }
        }
    }


    public String taskA(List<String> input){

        List<Hand> hands = new ArrayList<>();
        for(String line : input){
            String[] splitted = line.split(" ");
            hands.add(new Hand(splitted[0], Integer.parseInt(splitted[1])));
        }

        hands.sort(Hand::compareHand);

        long sum = 0;
        long rank = 1L;
        for(Hand h : hands){
            sum += rank * h.bid;
            rank++;
        }

        return String.valueOf(sum);
    }

    public String taskB(List<String> input){

        List<Hand> hands = new ArrayList<>();
        for(String line : input){
            String[] splitted = line.replace("J", "*").split(" ");
            Hand h = new Hand(splitted[0], Integer.parseInt(splitted[1]));
            h.cards.remove(Card.JOKER);

            hands.add(h);
        }

        hands.sort(Hand::compareHand);

        long sum = 0;
        long rank = 1L;
        for(Hand h : hands){
            sum += rank * h.bid;
            rank++;
        }

        return String.valueOf(sum);
    }

}

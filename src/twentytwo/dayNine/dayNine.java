package twentytwo.dayNine;

        import twentytwo.helper.DailyTask;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.HashSet;
        import java.util.List;

public class dayNine implements DailyTask {

    HashMap<String, int[]> vectors = new HashMap<>();
    public static void main(String[] args) {
        dayNine day = new dayNine();
        day.init();
        day.run();
    }

    private void init(){
        vectors.put("R", new int[]{0, 1});
        vectors.put("L", new int[]{0, -1});
        vectors.put("U", new int[]{1, 0});
        vectors.put("D", new int[]{-1, 0});
    }

    public String taskA(List<String> input){
        int size = 1000;
        boolean[][] grid = new boolean[size][size];

        int startPos = 500;
        Position head = new Position(startPos,startPos);
        Position tail = new Position(startPos, startPos);


        grid[startPos][startPos] = true;
        for(String line : input){
            System.out.println(line);
            String[] command = line.split(" ");
            for(int i = 0; i < Integer.parseInt(command[1]); i++){
                head.x += vectors.get(command[0])[0];
                head.y += vectors.get(command[0])[1];

                if(!isConnected(head, tail)){
                    tail.x = head.x - vectors.get(command[0])[0];
                    tail.y = head.y - vectors.get(command[0])[1];
                    grid[tail.x][tail.y] = true;
                }
            }
        }

        long counter = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid[i][j]){
                    counter++;
                }
            }
        }

        return String.valueOf(counter);
    }

    public String taskAV2(List<String> input){
        HashSet<String> visited = new HashSet<>();

        int startPos = 500;
        Position head = new Position(startPos,startPos);
        Position tail = new Position(startPos, startPos);

        HashMap<String, int[]> vectors = new HashMap<>();
        vectors.put("R", new int[]{0, 1});
        vectors.put("L", new int[]{0, -1});
        vectors.put("U", new int[]{-1, 0});
        vectors.put("D", new int[]{1, 0});

        visited.add(tail.x + "-" + tail.y);

        for(String line : input){
            System.out.println(line);
            String[] command = line.split(" ");
            for(int i = 0; i < Integer.parseInt(command[1]); i++){
                head.x += vectors.get(command[0])[0];
                head.y += vectors.get(command[0])[1];

                if(!isConnected(head, tail)){
                    tail.x = head.x - vectors.get(command[0])[0];
                    tail.y = head.y - vectors.get(command[0])[1];
                    visited.add(tail.x + "-" +tail.y);
                }
            }
        }

        return String.valueOf(visited.size());
    }

    private boolean isConnected(Position a, Position b){
        int difHorizontal = a.x - b.x;
        int difVertical = a.y - b.y;
        return (Math.abs(difVertical) <= 1 && Math.abs(difHorizontal) <= 1);
    }

    class Position{
        int x;
        int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public String taskB(List<String> input){
        int size = 1000;
        boolean[][] grid = new boolean[size][size];

        int startPos = size/2;
        Position head = new Position(startPos,startPos);
        Position p1 = new Position(startPos,startPos);
        Position p2 = new Position(startPos,startPos);
        Position p3 = new Position(startPos,startPos);
        Position p4 = new Position(startPos,startPos);
        Position p5 = new Position(startPos,startPos);
        Position p6 = new Position(startPos, startPos);
        Position p7 = new Position(startPos,startPos);
        Position p8 = new Position(startPos,startPos);
        Position tail = new Position(startPos,startPos);

        ArrayList<Position> knots = new ArrayList<>(List.of(head, p1, p2, p3, p4, p5, p6, p7, p8, tail));
        grid[startPos][startPos] = true;

        for(String line : input){
            System.out.println(line);
            String[] command = line.split(" ");
            for(int i = 0; i < Integer.parseInt(command[1]); i++){
                head.x += vectors.get(command[0])[0];
                head.y += vectors.get(command[0])[1];

                for(int x=0; x < 9; x++) {
                    if (!isConnected(knots.get(x), knots.get(x+1))) {
                        knots.get(x+1).x += getDif(knots.get(x).x, knots.get(x+1).x);
                        knots.get(x+1).y += getDif(knots.get(x).y, knots.get(x+1).y);
                    }
                }
                grid[tail.x][tail.y] = true;
            }
        }

        long counter = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(grid[i][j]){
                    counter++;
                }
            }
        }

        return String.valueOf(counter);
    }

    private int getDif(int a, int b){
        return Integer.compare(a, b);
    }
}



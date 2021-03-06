import java.util.Random;

public class Board {
    char[] arr = new char[9];
    Random randCpuselect = new Random();
    public void setArr(int idx, char character) {
        this.arr[idx] = character;
    }
    public char getArr(int idx) {
        return this.arr[idx];
    }
    public void clearBoard() {
        for (int i = 0; i<arr.length; i++) {
            arr[i] = '□';
        }
    }
    public void showBoard() {
        Out o = new Out();
        o.msg("┎───────────────┒");
        o.msg("┃   1   2   3   ┃");
        o.msg("┃   "+arr[0]+"   "+arr[1]+"   "+arr[2]+"   ┃");
        o.msg("┃   4   5   6   ┃");
        o.msg("┃   "+arr[3]+"   "+arr[4]+"   "+arr[5]+"   ┃");
        o.msg("┃   7   8   9   ┃");
        o.msg("┃   "+arr[6]+"   "+arr[7]+"   "+arr[8]+"   ┃");
        o.msg("┖───────────────┚");
    }
    public int winCondition() {
        // nobody wins = -1, draw = 0, X wins = 1, O wins = 2;
        boolean isDraw = true;
        // top left diagonal
        if ((arr[0] == arr[4]) && (arr[4] == arr[8])) {
            if (arr[0] == 'X') {
                return 1;
            } else if (arr[0] == 'O') {
                return 2;
            }
        }
        if ((arr[2] == arr[4]) && (arr[4] == arr[6])) {
            if (arr[2] == 'X') {
                return 1;
            } else if (arr[2] == 'O') {
                return 2;
            }
        }
        // rows
        for (int i = 0; i<9; i+=3) {
            if ((arr[i] == arr[i+1]) && (arr[i+1] == arr[i+2])) {
                if (arr[i] == 'X') {
                    return 1;
                } else if (arr[i] == 'O') {
                    return 2;
                }
            }
        }
        // columns
        for (int i = 0; i<3; i++) {
            if ((arr[i] == arr[i+3]) && (arr[i+3] == arr[i+6])) {
                if (arr[i] == 'X') {
                    return 1;
                } else if (arr[i] == 'O') {
                    return 2;
                }
            }
        }
        //checks if no empty squares exist so the game will know it's a draw
        for (char val : arr) {
            if (val == '□') isDraw = false;
        }
        if (isDraw) return 0;
        return -1;
    }
    public int cpu(){
        // this method returns a 'select' value for the computer player based on this algorithm
        // first it checks if it can win in 1 turn
        for (int i=0; i<9; i++){
            if (arr[i] == '□') {
                if (almostWin(i)) return i;
            }
        }
        // then it checks if it has to block the human player
        for (int i=0; i<9; i++) {
            if (arr[i] == '□') {
                if (playerAlmostwin(i)) return i;
            }
        } int randomSpace;
        // if it doesn't need to do either, it chooses a random empty space.
        do {
            randomSpace = randCpuselect.nextInt(9);

        } while (arr [randomSpace] !='□');
        return randomSpace;
    }
    //Computer checking if it can win in next move
    public boolean almostWin(int space){
        char original = arr [space];
        boolean output = false;
        arr [space] = 'O';
        if (winCondition()==2){
            output = true;
        } arr [space] = original;
        return output;

    }
    // computer checking if the player can win in one more turn so it knows when/where to block them
    public boolean playerAlmostwin(int space) {
        char original = arr[space];
        boolean output = false;
        arr[space] = 'X';
        if (winCondition() == 1) {
            output = true;
        }
        arr[space] = original;
        return output;
    }
}

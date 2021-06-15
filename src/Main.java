import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main (String args[]) {
        Out o = new Out();
        Board b = new Board();
        Random rand = new Random();
        b.clearBoard();
        Scanner scan = new Scanner(System.in);
        o.msg("Welcome to the Tic Tac Toe game!");
        o.msg("Please enter Player X's age.");
        int ageX = Integer.parseInt(scan.nextLine());
        o.msg("Please enter Player O's age.");
        int ageO = Integer.parseInt(scan.nextLine());
        boolean playerXturn = true;
        boolean playerXwin = true;
        boolean isValidGuess = true;
        boolean isDraw = false;
        String[] startMsg = {"Let the games begin!","Good luck!","I hope player X wins!","Don't disappoint me!","Whoever loses is a rotten egg!"};
        String[] pXwinMsg = {"Player X takes the cake!","Sweet victory for Player X!","Well played, Player X!","Player O owes you a soda because you win!"};
        String[] pOwinMsg = {"Player O takes the cake!","Sweet victory for Player O!","Well played, Player O!","Player X owes you a soda because you win!"};
        int playerXwins = 0, playerOwins = 0, select = 0;
        if (ageX > ageO) {
            o.msg("Since player O is younger, they go first!");
            playerXturn = false;
        } else if (ageX < ageO) {
            o.msg("Since player X is younger, they go first!");
            playerXturn = true;
        } else if (ageX == ageO) {
            o.msg("Since both players are the same age, I will choose who goes first!");
            if (rand.nextBoolean()) {
                o.msg("Player X gets to go first!");
                playerXturn = true;
            } else {
                o.msg("Player O gets to go first!");
                playerXturn = false;
            }
        }
        // this loop handles restarting the game
        while (true) {
            o.msg(startMsg[rand.nextInt(startMsg.length)]);
            isDraw = false;
            // this loop handles each turn
            while (true) {
                // handles invalid guesses
                if (b.winCondition() == 0) {
                    b.showBoard();
                    o.msg("Cat's game! It's a draw...");
                    isDraw = true;
                    break;
                }
                if (b.winCondition() == 1) {
                    b.showBoard();
                    o.msg(pXwinMsg[rand.nextInt(pXwinMsg.length)]);
                    playerXwin = true;
                    break;
                }
                if (b.winCondition() == 2) {
                    b.showBoard();
                    o.msg(pOwinMsg[rand.nextInt(pOwinMsg.length)]);
                    playerXwin = false;
                    break;
                }
                do {
                    isValidGuess = true;
                    b.showBoard();

                    if (playerXturn) {
                        o.msg("Player X's turn.");
                    } else {
                        o.msg("Player O's turn.");
                    }
                    o.msg("Please input a space. (1-9)");
                    select = Integer.parseInt(scan.nextLine())-1;
                    if (select > 8 || select < 0 || b.getArr(select) != '□') {
                        isValidGuess = false;
                        o.msg("Please enter a valid guess.");
                    }
                } while (!isValidGuess);
                if (playerXturn) {
                    b.setArr(select,'X');
                    playerXturn = false;
                } else {
                    b.setArr(select,'O');
                    playerXturn = true;
                }
            }
            if (!isDraw) {
                if (playerXwin) {
                    playerXturn = false;
                    playerXwins++;
                } else {
                    playerXturn = true;
                    playerOwins++;
                }
            }
            if (playerXwins != 1) {
                o.msg("Player X - "+playerXwins+" wins");
            } else {
                o.msg("Player X - "+playerXwins+" win");
            }
            if (playerOwins != 1) {
                o.msg("Player O - "+playerOwins+" wins");
            } else {
                o.msg("Player O - "+playerOwins+" win");
            }
            o.msg("Press Enter to play another game or press 'q' to quit.");
            if (scan.nextLine().equals("q")) {
                o.msg("Thanks for playing!");
                break;
            }
            if (isDraw) {
                if (rand.nextBoolean()) {
                    o.msg("Since it's a draw, I choose player X to go first this time!");
                } else {
                    o.msg("Since it's a draw, I choose player O to go first this time!");
                }
            } else {
                o.msg("Loser goes first!");
            }
            b.clearBoard();
        }
    }
}

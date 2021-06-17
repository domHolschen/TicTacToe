import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main (String args[]) {
        Out o = new Out();
        Board b = new Board();
        Random rand = new Random();
        b.clearBoard();
        Scanner scan = new Scanner(System.in);
        // this loop is so you can go back to the menu
        while (true) {
            o.msg("Welcome to the Tic Tac Toe game!");
            o.msg("Enter '1' for single player and '2' for multiplayer.");
            o.msg("Enter 'q' to quit.");
            String singlePlayer = scan.nextLine();
            boolean isOnePlayer = true;
            boolean playerXturn = true;
            boolean playerXwin = true;
            boolean isValidGuess = true;
            boolean isDraw = false;
            // random messages
            String[] startMsg = {"Let the games begin!","Good luck!","I hope player X wins!","Don't disappoint me!","Whoever loses is a rotten egg!"};
            String[] pXwinMsg = {"Player X takes the cake!","Sweet victory for Player X!","Well played, Player X!","Player O owes you a soda because you win!"};
            String[] pOwinMsg = {"Player O takes the cake!","Sweet victory for Player O!","Well played, Player O!","Player X owes you a soda because you win!"};
            int playerXwins = 0, playerOwins = 0, select = 0;
            if (singlePlayer.equals("1")) {
                isOnePlayer = true;
                o.msg("Player X Gets to go first!");
                playerXturn = true;
            } else if (singlePlayer.equals ("2")){
                isOnePlayer = false;
                o.msg("Please enter Player X's age.");
                int ageX = Integer.parseInt(scan.nextLine());
                o.msg("Please enter Player O's age.");
                int ageO = Integer.parseInt(scan.nextLine());
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
            } else if (singlePlayer.equals("q")) {
                o.msg("Thanks for playing!");
                break;
            }
            // this loop handles restarting the game
            while (true) {
                o.msg(startMsg[rand.nextInt(startMsg.length)]);
                isDraw = false;
                // this loop handles each turn
                while (true) {
                    // checks if its a draw
                    if (b.winCondition() == 0) {
                        b.showBoard();
                        o.msg("Cat's game! It's a draw...");
                        isDraw = true;
                        break;
                    }
                    // checks if x wins
                    if (b.winCondition() == 1) {
                        b.showBoard();
                        o.msg(pXwinMsg[rand.nextInt(pXwinMsg.length)]);
                        playerXwin = true;
                        break;
                    }
                    // checks if O wins
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
                        if (!playerXturn&&isOnePlayer) {
                            // if its the CPU's turn, select the space that is returned from algorithm
                            // computer doesn't need to check if the space is valid, that is already done in the method
                            select = b.cpu();
                        } else {
                            // if its a player turn, ask the player which space they want to select
                            o.msg("Please input a space. (1-9)");
                            select = Integer.parseInt(scan.nextLine())-1;
                            // checks if selected space is already occupied or out of bounds
                            if (select > 8 || select < 0 || b.getArr(select) != '□') {
                                isValidGuess = false;
                                o.msg("Please enter a valid guess.");
                            }
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
                    // adds to win counters and selects who is first next game
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
                o.msg("Press Enter to play another game or press 'q' to return to the main menu.");
                if (scan.nextLine().equals("q")) {
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
}

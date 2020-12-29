package org.kodluyoruz;

import java.util.Random;
import java.util.Scanner;

public class Game {

    //ATTRIBUTES - VARIABLES

    private char[][] table;
    private int numberOfAttempts;
    private int playerScore = 0;
    private int pcScore = 0;

    //CONSTRUCTOR

    public Game(int numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    /* METHOD THAT CREATES FIRST ROWS AND COLUMNS TO START GAME */

    public void gameTable(int finalSize) {

        int tableSize = finalSize;

        this.table = new char[tableSize][tableSize];
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                this.table[i][j] = '-';
                System.out.print("[" + this.table[i][j] + "]");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /* METHOD THAT REFRESHES TABLE AFTER EVERY STEP IN THE GAME */

    public void printTable() {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                System.out.print("[" + this.table[i][j] + "]");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /* METHOD THAT PICKS RANDOM FIRST PLAYER */

    public int who() {
        int number = (int) (Math.random() * 2) + 1;
        return number;
    }

    /* METHOD THAT CONTROLS WHO IS PLAYING */
    public String whoIsNext(int randomWho) {
        if (randomWho == 1) {
            System.out.println("Player's turn : s");
            return "s";

        } else {
            System.out.println("Computer's turn: o");
            return "o";
        }
    }

    /* METHODS THAT CHANGES PLAYER TURN AND GAME */

    int turn = who();

    public void playerTurnAndGame(){
        String next = whoIsNext(turn);
        turnControl();
        sos(next);
        playerScoreState(turn);
        winner();
    }

    public void turnControl(){
            if (turn == 2) turn-=1;
            else if (turn == 1) turn += 1;
    }


    /* METHOD THAT CONTROLS PLAYERS GAME AREA TO ENTER THEIR CHOOSES */

    public void sos(String s) {

        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        try {
            if (s.equals("s")) {
                System.out.print("Row:");
                int row = sc.nextInt();
                System.out.print("Column:");
                int column = sc.nextInt();

                if (this.table[row][column] == 'o' || this.table[row][column] == 's' || this.table[row][column] == 'O' || this.table[row][column] == 'S') {
                    numberOfAttempts -= 1;
                    System.out.println("YOU PICK EXIST PLACE PLEASE TRY AGAIN!!! REMAINING:" + numberOfAttempts);
                    printTable();
                    while (numberOfAttempts > 0) {
                        System.out.print("ROW:");
                        int satir2 = sc.nextInt();
                        System.out.print("COLUMN:");
                        int sutun2 = sc.nextInt();
                        if (this.table[satir2][sutun2] == '-') {
                            this.table[satir2][sutun2] = s.charAt(0);
                            printTable();
                            break;
                        }
                        numberOfAttempts -= 1;
                        printTable();
                        System.out.println("REMAINING:" + numberOfAttempts);
                    }

                } else {
                    this.table[row][column] = s.charAt(0);

                    printTable();
                }


            } else if (s.equals("o")) {

                System.out.println("COMPUTER İS PLAYING...");

                int str = rd.nextInt(this.table.length);
                int stn = rd.nextInt(this.table.length);

                if (this.table[str][stn] == 'o' || this.table[str][stn] == 's' || this.table[str][stn] == 'O' || this.table[str][stn] == 'S') {
                    System.out.println("IT PICKED WRONG PLACE AND IT IS TRYING AGAIN...");
                    while (this.table[str][stn] == 'o' || this.table[str][stn] == 's' || this.table[str][stn] == 'O' || this.table[str][stn] == 'S') {
                        int str2 = rd.nextInt(this.table.length);
                        int stn2 = rd.nextInt(this.table.length);
                        if (this.table[str2][stn2] == '-') {
                            this.table[str2][stn2] = s.charAt(0);
                            printTable();
                            break;
                        }
                    }
                } else {
                    this.table[str][stn] = s.charAt(0);
                    printTable();
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("INVALID RANGE: ERROR:"+e);
        }


    }


    /* METHOD THAT CONTROLS GAME STATUS */

    public boolean isGameOver() {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (this.table[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /* METHOD THAT CONTROLS ROW SOS */

    public boolean rowControl() {

        try {
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table.length; j++) {
                    if (table[j][i] == 's' && table[j][i + 1] == 'o' && table[j][i + 2] == 's') {
                        table[j][i] = 'S';
                        table[j][i + 1] = 'O';
                        table[j][i + 2] = 'S';
                        System.out.println("SOS ROW");
                        return true;
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR:"+e);
        }
        return false;
    }

    /* METHOD THAT CONTROLS DIAGONAL SOS */

    public boolean diagonalControl() {

        try {
            for (int i = 0; i < table.length - 2; i++) {
                for (int j = 0; j < table.length - 2; j++) {
                    if (table[i][j] == 's' && table[i + 1][j + 1] == 'o' && table[i + 2][j + 2] == 's') {
                        table[i][j] = 'S';
                        table[i + 1][j + 1] = 'O';
                        table[i + 2][j + 2] = 'S';
                        System.out.println("SOS DIAGONAL");
                        return true;
                    }
                    else if (table[i][j + 2] == 's' && table[i + 1][j + 1] == 'o' && table[i + 2][j] == 's') {
                        table[i][j + 2] = 'S';
                        table[i + 1][j + 1] = 'O';
                        table[i + 2][j] = 'S';
                        System.out.println("REVERSE SOS DIAGONAL");
                        return true;
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR:"+e);
        }
        return false;
    }

    /* METHOD THAT CONTROLS COLUMN SOS */

    public boolean columnControl() {

        try {
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table.length; j++) {
                    if (table[i][j] == 's' && table[i + 1][j] == 'o' && table[i + 2][j] == 's') {
                        table[i][j] = 'S';
                        table[i + 1][j] = 'O';
                        table[i + 2][j] = 'S';
                        System.out.println("SOS COLUMN");
                        return true;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ERROR:"+e);
        }

        return false;
    }

    /* METHOD THAT CONTROLS PLAYER SCORE AND PC SCORE */

    public void playerScoreState(int ilkSira) {
        if (whoIsNext(ilkSira).equals("s")) {

            if (columnControl() || rowControl() || diagonalControl()) {
                pcScore += 1;
                System.out.println("PC:" + pcScore + "SCORE");
            } else {
                System.out.println("PLAYER SCORE:" + playerScore);
                System.out.println("PC SCORE:" + pcScore);
            }

        } else if (whoIsNext(2).equals("o")) {
            if (columnControl() || rowControl() || diagonalControl()) {
                playerScore += 1;
                System.out.println("PLAYER:" + playerScore + "SCORE");
            } else {
                System.out.println("PLAYER SCORE:" + playerScore);
                System.out.println("PC SCORE:" + pcScore);
            }
        }
    }


    /* METHOD THAT CONTROLS WINNER */

    public void winner() {
        if (isGameOver()) {
            if (this.playerScore > this.pcScore) {
                System.out.println("FINISH");
                System.out.println("Winner: PLAYER!!!");
            } else if (this.pcScore > this.playerScore) {
                System.out.println("FINISH");
                System.out.println("Winner: PC!!!");
            } else {
                System.out.println("FINISH");
                System.out.println("DRAW!!!");
            }
        }
    }
}

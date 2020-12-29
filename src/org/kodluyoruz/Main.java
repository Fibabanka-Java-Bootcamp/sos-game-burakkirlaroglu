package org.kodluyoruz;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Game game;
        int tableSize;

            Scanner sc = new Scanner(System.in);
            System.out.println("SOS GAME");
            System.out.println("PLEASE SELECT NUMBER OF ATTEMPTS");
            int numberOfAttempts = sc.nextInt();
            game = new Game(numberOfAttempts);

            System.out.println("PLEASE SELECT TABLE SIZE");
            tableSize = sc.nextInt();

            game.gameTable(tableSize);

            while (tableSize < 3 || tableSize > 7) {
                System.out.println("INVALID RANGE!! TABLE MUST TO BE BETWEEN 3 AND 7 !!");
                tableSize = sc.nextInt();
            }

            while (!game.isGameOver()){
                game.playerTurnAndGame();
            }
    }
}

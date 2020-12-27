package org.kodluyoruz;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("SOS GAME");
        Game game = new Game();

        System.out.println("PLEASE SELECT TABLE SIZE");

        int tableSize = sc.nextInt();

        while (tableSize < 3 || tableSize > 7) {
            System.out.println("INVALID RANGE!! TABLE MUST TO BE BETWEEN 3 AND 7 !!");
            tableSize = sc.nextInt();
        }

        game.gameTable(tableSize);

        int firstPlay = game.who();

        while (!game.isGameOver()){

            String next = game.whoIsNext(firstPlay);
            if (firstPlay == 2) firstPlay-=1;
            else if (firstPlay == 1) firstPlay += 1;
            game.sos(next);
            game.playerScoreState(firstPlay);
            game.winner();
        }
    }
}

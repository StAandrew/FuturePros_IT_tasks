package com.yatzy.gameplay;

class Main {
    public static void main(String[] args) {
       Game game = new Game(System.in, System.out);
       Player player = new Player("");
       for (int i = 0; i < 3; i++) {
           if(!game.play(player))
               return;
       }
    }
}
package com.didgers.test.technique;

public class Main {
    public static void main(String[] args) {
       Tennis t = new Tennis();
       if(args.length == 0 ){
           throw  new IllegalArgumentException("Missing input. Please Provide inputs");
       }
       t.startGame(args[0]);
    }
}
package com.didgers.test.technique;

public class Tennis {

    private static final String SCORE_PRINTS = "Player A: %s / Player B: %s";
    private static final char PLAYER_A ='A';
    private static final char PLAYER_B ='B';

    private int scoreA;
    private int scoreB;


    public  void startGame(String sequence){
        if(sequence.length()==0||!sequence.chars().allMatch(c->c== PLAYER_A || c== PLAYER_B)){
            throw new IllegalArgumentException("Only player A and B are valid entries");
        }
        System.out.println(sequence);
        final char[] game = sequence.toCharArray();

        for(char currentPlayer: game){
            if(isPlayerA(currentPlayer)){
                scoreA++;
            } else{
                scoreB++;
            }

            if(isDeuce()){
                System.out.println("The game is in DEUCE");
                continue;
            }

            if(isAdvantageFor(currentPlayer)){
                System.out.println(String.format("ADV %c", currentPlayer));
                continue;
            }

            if(isWinner(currentPlayer)){
                System.out.println(String.format("Winner is %c", currentPlayer));
                break;
            }

            System.out.println(String.format(SCORE_PRINTS, Points.from(scoreA).getName(), Points.from(scoreB).getName()));
        }

    }


    public boolean isDeuce(){
        return scoreA == scoreB && scoreA >=3;
    }

    public boolean isAdvantageForPlayerA(){
        return isAdvantageFor(PLAYER_A);
    }

    public boolean isAdvantageForPlayerB(){
       return  isAdvantageFor(PLAYER_B);
    }

    public boolean isWinner(char playerName){
        if(playerName == PLAYER_A){
            return scoreA> 3 &&  scoreA >=scoreB+2;
        } else {
            return  scoreB>3 && scoreB>=scoreA+2;
        }
    }

    public String getScorePlayerA() {
        return Points.from(scoreA).getName();
    }

    public String getScorePlayerB() {
        return Points.from(scoreB).getName();
    }

    private boolean isAdvantageFor(char player){
        if(player == PLAYER_A) {
            return scoreA > 3 && scoreA == scoreB + 1;
        }

        return scoreB>3 && scoreB == scoreA + 1;
    }

    private boolean isPlayerA(char playerName){
        return  playerName == PLAYER_A;
    }

}

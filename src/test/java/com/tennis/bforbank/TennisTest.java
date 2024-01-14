package com.tennis.bforbank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TennisTest {


    private static Stream<Arguments> provideSequenceForDeuce() {
        return Stream.of(Arguments.of("Given score are equals and less then 40 then the game is NOT DEUCE", "AB", false), Arguments.of("Given score are both 40, then game is DEUCE", "AAABBB", true), Arguments.of("Given score are not equals Then game is NOT DEUCE", "AAABBBB", false), Arguments.of("Given score is AVD For A and B won ball Then game is DEUCE", "AAABBBAB", true), Arguments.of("Given score is AVD For B and A won ball Then game is DEUCE", "BBBAAABA", true));
    }

    private static Stream<Arguments> provideSequenceForWinner() {
        return Stream.of(Arguments.of("Given score is playerA:40/playerB: x<40 and playerA won the ball Then playerA is winner", "AABBAA", 'A', true), Arguments.of("Given score is playerA:x<40 /playerB: 40 and playerB won the ball Then playerB is winner", "BBAABB", 'B', true), Arguments.of("Given score is playerA:ADV and playerA won the ball Then playerA is winner", "AAABBBAA", 'A', true), Arguments.of("Given score is playerB:ADV and playerB won the ball Then playerB is winner", "BBBAAABB", 'B', true), Arguments.of("No winner : Given score is playerA:15/ playerB:15", "AB", 'B', false), Arguments.of("No Winner:  Given score is playerA:15/ playerB:15", "AB", 'A', false)

        );
    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("provideSequenceForDeuce")
    void test_startGame_for_DeuceGame(String testName, String input, boolean expected) {
        final Tennis tennis = new Tennis();
        tennis.startGame(input);
        assertEquals(expected, tennis.isDeuce());
    }

    @ParameterizedTest(name = "{index} {0}")
    @MethodSource("provideSequenceForWinner")
    void test_startGame_When_There_is_Winner(String testName, String input, char winner, boolean expected) {
        final Tennis tennis = new Tennis();
        tennis.startGame(input);
        assertEquals(expected, tennis.isWinner(winner));
    }

    @Test
    void testStartGame_Given_new_Game_Scores_willBe_Zero() {
        final Tennis tennis = new Tennis();
        assertEquals(Points._0.getName(), tennis.getScorePlayerA());
        assertEquals(Points._0.getName(), tennis.getScorePlayerB());
    }

    @Test
    void Test_startGame_Given_InvalidSequence() {
        final Tennis tennis = new Tennis();
        assertThrows(IllegalArgumentException.class, () -> tennis.startGame("ACBA"));
    }


    @Test
    void Test_startGame_Given_EmptySequence() {
        final Tennis tennis = new Tennis();
        assertThrows(IllegalArgumentException.class, () -> tennis.startGame(""));
    }

    @Test
    void testStartGame_Given_PlayerA_WonBall_WillIncrement_ScorePlayerA() {
        final Tennis tennis = new Tennis();
        tennis.startGame("A");
        assertEquals(Points._15.getName(), tennis.getScorePlayerA());
    }

    @Test
    void testStartGame_Given_PlayerB_WonBall_WillIncrement_ScorePlayerB() {
        final Tennis tennis = new Tennis();
        tennis.startGame("B");
        assertEquals(Points._15.getName(), tennis.getScorePlayerB());
    }


    @Test
    void testStartGame_given_players_are_both_in_40_and_playerA_wonBall_then_ADV_for_playerA() {
        final Tennis tennis = new Tennis();
        tennis.startGame("AAABBBA");
        assertTrue(tennis.isAdvantageForPlayerA());
    }

    @Test
    void testStartGame_given_players_are_both_in_40_and_playerB_wonBall_then_ADV_for_playerB() {
        final Tennis tennis = new Tennis();
        tennis.startGame("AAABBBB");
        assertTrue(tennis.isAdvantageForPlayerB());
    }


}
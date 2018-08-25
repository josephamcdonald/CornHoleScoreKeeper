package com.example.android.cornholescorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Grow with Google Android Basics Nanodegree
 * Project: 2
 * Version: 2.0
 * App Name: Cornhole Score!
 * Author: Joseph McDonald
 */

public class MainActivity extends AppCompatActivity {

    // Declare all the scoreboard views.
    private TextView viewScoreTeamA;
    private TextView viewScoreTeamB;
    private TextView viewScoreTeamAInning;
    private TextView viewScoreTeamBInning;
    private TextView viewBagsTeamA;
    private TextView viewBagsTeamB;
    private TextView viewInningCurrent;
    private TextView viewStatusGame;

    // No more bags to throw.
    private final int NO_BAGS = 0;

    // Total score for Team A.
    private int scoreTeamA;

    // Total score for Team B.
    private int scoreTeamB;

    // Bags remaining to throw for Team A.
    private int bagsTeamA;

    // Bags remaining to throw for Team B.
    private int bagsTeamB;

    // Current inning score for Team A.
    private int scoreTeamAInning;

    // Current inning score for Team B.
    private int scoreTeamBInning;

    // The game's current inning.
    private int inningCurrent;

    // The game has a winner.
    private boolean winner;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Save all the variable values.
        savedInstanceState.putInt(getResources().getString(R.string.scoreTeamA), scoreTeamA);
        savedInstanceState.putInt(getResources().getString(R.string.scoreTeamB), scoreTeamB);
        savedInstanceState.putInt(getResources().getString(R.string.bagsTeamA), bagsTeamA);
        savedInstanceState.putInt(getResources().getString(R.string.bagsTeamB), bagsTeamB);
        savedInstanceState.putInt(getResources().getString(R.string.scoreTeamAInning), scoreTeamAInning);
        savedInstanceState.putInt(getResources().getString(R.string.scoreTeamBInning), scoreTeamBInning);
        savedInstanceState.putInt(getResources().getString(R.string.inningCurrent), inningCurrent);
        savedInstanceState.putBoolean(getResources().getString(R.string.winner), winner);

        // Save all the string values.
        savedInstanceState.putString(getResources().getString(R.string.viewScoreTeamA), viewScoreTeamA.getText().toString());
        savedInstanceState.putString(getResources().getString(R.string.viewScoreTeamB), viewScoreTeamB.getText().toString());
        savedInstanceState.putString(getResources().getString(R.string.viewBagsTeamA), viewBagsTeamA.getText().toString());
        savedInstanceState.putString(getResources().getString(R.string.viewBagsTeamB), viewBagsTeamB.getText().toString());
        savedInstanceState.putString(getResources().getString(R.string.viewScoreTeamAInning), viewScoreTeamAInning.getText().toString());
        savedInstanceState.putString(getResources().getString(R.string.viewScoreTeamBInning), viewScoreTeamBInning.getText().toString());
        savedInstanceState.putString(getResources().getString(R.string.viewInningCurrent), viewInningCurrent.getText().toString());
        savedInstanceState.putString(getResources().getString(R.string.viewStatusGame), viewStatusGame.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Restore all the variable values.
        scoreTeamA = savedInstanceState.getInt(getResources().getString(R.string.scoreTeamA));
        scoreTeamB = savedInstanceState.getInt(getResources().getString(R.string.scoreTeamB));
        bagsTeamA = savedInstanceState.getInt(getResources().getString(R.string.bagsTeamA));
        bagsTeamB = savedInstanceState.getInt(getResources().getString(R.string.bagsTeamB));
        scoreTeamAInning = savedInstanceState.getInt(getResources().getString(R.string.scoreTeamAInning));
        scoreTeamBInning = savedInstanceState.getInt(getResources().getString(R.string.scoreTeamBInning));
        inningCurrent = savedInstanceState.getInt(getResources().getString(R.string.inningCurrent));
        winner = savedInstanceState.getBoolean(getResources().getString(R.string.winner));

        // Restore all the string values.
        viewScoreTeamA.setText(savedInstanceState.getString(getResources().getString(R.string.viewScoreTeamA)));
        viewScoreTeamB.setText(savedInstanceState.getString(getResources().getString(R.string.viewScoreTeamB)));
        viewBagsTeamA.setText(savedInstanceState.getString(getResources().getString(R.string.viewBagsTeamA)));
        viewBagsTeamB.setText(savedInstanceState.getString(getResources().getString(R.string.viewBagsTeamB)));
        viewScoreTeamAInning.setText(savedInstanceState.getString(getResources().getString(R.string.viewScoreTeamAInning)));
        viewScoreTeamBInning.setText(savedInstanceState.getString(getResources().getString(R.string.viewScoreTeamBInning)));
        viewInningCurrent.setText(savedInstanceState.getString(getResources().getString(R.string.viewInningCurrent)));
        viewStatusGame.setText(savedInstanceState.getString(getResources().getString(R.string.viewStatusGame)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find and initialize all the scoreboard views.
        viewScoreTeamA = findViewById(R.id.score_team_a_total);
        viewScoreTeamB = findViewById(R.id.score_team_b_total);
        viewBagsTeamA = findViewById(R.id.bags_team_a);
        viewBagsTeamB = findViewById(R.id.bags_team_b);
        viewScoreTeamAInning = findViewById(R.id.score_team_a_inning);
        viewScoreTeamBInning = findViewById(R.id.score_team_b_inning);
        viewInningCurrent = findViewById(R.id.inning_current);
        viewStatusGame = findViewById(R.id.status_game);

        // Declare, find and initialize all the Buttons.
        final Button buttonPlus3TeamA = findViewById(R.id.button_plus3_team_a);
        final Button buttonPlus1TeamA = findViewById(R.id.button_plus1_team_a);
        final Button buttonZeroTeamA = findViewById(R.id.button_zero_team_a);
        final Button buttonMinus1TeamA = findViewById(R.id.button_minus1_team_a);
        final Button buttonPlus3TeamB = findViewById(R.id.button_plus3_team_b);
        final Button buttonPlus1TeamB = findViewById(R.id.button_plus1_team_b);
        final Button buttonZeroTeamB = findViewById(R.id.button_zero_team_b);
        final Button buttonMinus1TeamB = findViewById(R.id.button_minus1_team_b);
        final Button buttonResetGame = findViewById(R.id.button_reset);
        final Button buttonNextInning = findViewById(R.id.button_next_inning);

        // Initialize the game.
        initializeGame();

        // Create OnClickListener for all the Button actions.
        OnClickListener myOnClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_plus3_team_a:
                        // If we have a no winner and
                        //    If team still has bags, add 3 points to score, decrement bag count and update views.
                        //       If we have a winner now, display scores and announce winner.
                        //    else if both teams are out of bags, prompt for next inning.
                        if (!winner) {
                            if (bagsTeamA > NO_BAGS) {
                                viewScoreTeamAInning.setText(scoreBagToString(true, 3));
                                viewBagsTeamA.setText(decrBagToString(true));
                                viewStatusGame.setText(R.string.status_three_points);
                                if (weHaveAWinner(true)) {
                                    viewScoreTeamA.setText(String.valueOf(scoreTeamA));
                                    viewScoreTeamB.setText(String.valueOf(scoreTeamB));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_a)));
                                }

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_plus1_team_a:
                        // If we have a no winner, and
                        //    If team still has bags, add 1 point to score, decrement bag count and update views.
                        //       If we have a winner now, display scores and announce winner.
                        //    else if both teams are out of bags, prompt for next inning.
                        if (!winner) {
                            if (bagsTeamA > NO_BAGS) {
                                viewScoreTeamAInning.setText(scoreBagToString(true, 1));
                                viewBagsTeamA.setText(decrBagToString(true));
                                viewStatusGame.setText(R.string.status_one_point);
                                if (weHaveAWinner(true)) {
                                    viewScoreTeamA.setText(String.valueOf(scoreTeamA));
                                    viewScoreTeamB.setText(String.valueOf(scoreTeamB));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_a)));
                                }

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_zero_team_a:
                        // If we have a no winner and
                        //    If team still has bags, decrement bag count and update views.
                        //    else if both teams are out of bags, prompt for next inning.
                        if (!winner) {
                            if (bagsTeamA > NO_BAGS) {
                                viewBagsTeamA.setText(decrBagToString(true));
                                viewStatusGame.setText(R.string.status_zero_points);

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_minus1_team_a:
                        // If we have a no winner and
                        //    If team inning score is above zero, subtract 1 point to score and update views.
                        //    else if team inning score is zero, prompt that zero score is reached.
                        if (!winner) {
                            if (scoreTeamAInning > 0) {
                                viewScoreTeamAInning.setText(scoreBagToString(true, -1));
                                viewStatusGame.setText(R.string.status_minus_point);

                            } else {
                                viewStatusGame.setText(R.string.status_zero_score);
                            }
                        }
                        break;

                    case R.id.button_plus3_team_b:
                        // If we have a no winner and
                        //    If team still has bags, add 3 points to score, decrement bag count and update views.
                        //       If we have a winner now, display scores and announce winner.
                        //    else if both teams are out of bags, prompt for next inning.
                        if (!winner) {
                            if (bagsTeamB > NO_BAGS) {
                                viewScoreTeamBInning.setText(scoreBagToString(false, 3));
                                viewBagsTeamB.setText(decrBagToString(false));
                                viewStatusGame.setText(R.string.status_three_points);
                                if (weHaveAWinner(false)) {
                                    viewScoreTeamA.setText(String.valueOf(scoreTeamA));
                                    viewScoreTeamB.setText(String.valueOf(scoreTeamB));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_b)));
                                }

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_plus1_team_b:
                        // If we have a no winner, and
                        //    If team still has bags, add 1 point to score, decrement bag count and update views.
                        //       If we have a winner now, display scores and announce winner.
                        //    else if both teams are out of bags, prompt for next inning.
                        if (!winner) {
                            if (bagsTeamB > NO_BAGS) {
                                viewScoreTeamBInning.setText(scoreBagToString(false, 1));
                                viewBagsTeamB.setText(decrBagToString(false));
                                viewStatusGame.setText(R.string.status_one_point);
                                if (weHaveAWinner(false)) {
                                    viewScoreTeamA.setText(String.valueOf(scoreTeamA));
                                    viewScoreTeamB.setText(String.valueOf(scoreTeamB));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_b)));
                                }

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_zero_team_b:
                        // If we have a no winner and
                        //    If team still has bags, decrement bag count and update views.
                        //    else if both teams are out of bags, prompt for next inning.
                        if (!winner) {
                            if (bagsTeamB > NO_BAGS) {
                                viewBagsTeamB.setText(decrBagToString(false));
                                viewStatusGame.setText(R.string.status_zero_points);

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_minus1_team_b:
                        // If we have a no winner and
                        //    If team inning score is above zero, subtract 1 point to score and update views.
                        //    else if team inning score is zero, prompt that zero score is reached.
                        if (!winner) {
                            if (scoreTeamBInning > 0) {
                                viewScoreTeamBInning.setText(scoreBagToString(false, -1));
                                viewStatusGame.setText(R.string.status_minus_point);

                            } else {
                                viewStatusGame.setText(R.string.status_zero_score);
                            }
                        }
                        break;

                    case R.id.button_next_inning:
                        // If we have no winner and
                        //    If both teams have no bags, reset bag counts, inning scores,
                        //       increment inning and update the scoreboard.
                        //    else if notify that there are still bags left to throw.
                        if (!winner) {
                            if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                bagsTeamA = 4;
                                bagsTeamB = 4;
                                scoreTeamAInning = 0;
                                scoreTeamBInning = 0;
                                ++inningCurrent;
                                updateScoreboard((getString(R.string.status_start_inning) + String.valueOf(inningCurrent)));

                            } else {
                                viewStatusGame.setText(R.string.status_bags_remain);
                            }
                        }
                        break;

                    case R.id.button_reset:
                        // Initialize the game.
                        initializeGame();
                        break;
                }
            }
        };
        // Set myOnClickListener to all the buttons.
        buttonPlus3TeamA.setOnClickListener(myOnClickListener);
        buttonPlus1TeamA.setOnClickListener(myOnClickListener);
        buttonZeroTeamA.setOnClickListener(myOnClickListener);
        buttonMinus1TeamA.setOnClickListener(myOnClickListener);
        buttonPlus3TeamB.setOnClickListener(myOnClickListener);
        buttonPlus1TeamB.setOnClickListener(myOnClickListener);
        buttonZeroTeamB.setOnClickListener(myOnClickListener);
        buttonMinus1TeamB.setOnClickListener(myOnClickListener);
        buttonNextInning.setOnClickListener(myOnClickListener);
        buttonResetGame.setOnClickListener(myOnClickListener);
    }

    /**
     * Initialize all variables and update the scoreboard.
     **/
    private void initializeGame(){
        scoreTeamA = 0;
        scoreTeamB = 0;
        bagsTeamA = 4;
        bagsTeamB = 4;
        scoreTeamAInning = 0;
        scoreTeamBInning = 0;
        inningCurrent = 1;
        winner = false;
        updateScoreboard(getString(R.string.status_init));
    }

    /**
     * Update team scores with bag points and return the String of team's current inning score.
     *
     * @param teamA  - true if teamA just threw a bag, else teamB just threw a bag.
     * @param points - number of points for bag thrown.
     * @return String.valueOf(scoreTeamAInning or scoreTeamBInning) of team's inning score.
     **/
    private String scoreBagToString(boolean teamA, int points) {
        if (teamA) {
            scoreTeamAInning += points;
            scoreTeamA += points;
            return String.valueOf(scoreTeamAInning);

        } else {
            scoreTeamBInning += points;
            scoreTeamB += points;
            return String.valueOf(scoreTeamBInning);
        }
    }

    /**
     * Decrement team bag count and return String of team's current bag count.
     *
     * @param teamA - true if teamA just threw a bag, else teamB just threw a bag.
     * @return String.valueOf(bagsTeamA or bagsTeamB) of team's bag count.
     **/
    private String decrBagToString(boolean teamA) {
        if (teamA) {
            --bagsTeamA;
            return String.valueOf(bagsTeamA);

        } else {
            --bagsTeamB;
            return String.valueOf(bagsTeamB);
        }
    }

    /**
     * Determine if team is a winner and return true or false.
     *
     * @param teamA - true if this is teamA , else this is teamB
     * @return boolean value of winning team.
     **/
    private boolean weHaveAWinner(boolean teamA) {
        // A score of 21 points wins the game.
        final int WINNING_SCORE = 21;

        // If teamA score is greater or equal than WINNING_SCORE, teamA wins!
        // else if teamB score is greater or equal than WINNING_SCORE, teamB wins!
        if (teamA && (scoreTeamA >= WINNING_SCORE)) {
            winner = true;

        } else if (scoreTeamB >= WINNING_SCORE) {
            winner = true;
        }
        return winner;
    }

    /**
     * Update the scoreboard views.
     *
     * @param statusGame - game status String displayed in the marquee.
     **/
    private void updateScoreboard(String statusGame) {
        viewScoreTeamA.setText(String.valueOf(scoreTeamA));
        viewScoreTeamB.setText(String.valueOf(scoreTeamB));
        viewBagsTeamA.setText(String.valueOf(bagsTeamA));
        viewBagsTeamB.setText(String.valueOf(bagsTeamB));
        viewScoreTeamAInning.setText(String.valueOf(scoreTeamAInning));
        viewScoreTeamBInning.setText(String.valueOf(scoreTeamBInning));
        viewInningCurrent.setText(String.valueOf(inningCurrent));
        viewStatusGame.setText(statusGame);
    }
}

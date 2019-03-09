/**
 * Author: Greg VanKampen
 * File: MainActivity
 * Date:3/8/2019
 */
package com.example.roshambo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Rochambo game = new Rochambo();
    TextView results;
    ImageView player;
    ImageView computer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        results = findViewById(R.id.text_results);
        player = findViewById(R.id.image_player);
        computer = findViewById(R.id.image_computer);
    }

    public void onRockPress(View v){
        game.playerMakesMove(Rochambo.ROCK);
        updateDisplay();
    }

    public void onPaperPress(View view) {
        game.playerMakesMove(Rochambo.PAPER);
        updateDisplay();
    }

    public void onScissorsPress(View view) {

        game.playerMakesMove(Rochambo.SCISSOR);
        updateDisplay();
    }

    /**
     * updates game display with new results
     */
    private void updateDisplay(){
        Drawable playerImage = getDrawable(getResultsImage(game.getPlayerMove()));
        Drawable computerImage = getDrawable(getResultsImage(game.getGameMove()));
        player.setImageDrawable(playerImage);
        computer.setImageDrawable(computerImage);
        results.setText(game.winLoseOrDraw());

        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(player,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(computer,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();

    }

    /**
     * returns image based on rock paper scissors result
     * @param i result
     * @return image corresponding to result
     */
    private int getResultsImage(int i){
        switch(i) {
            case Rochambo.ROCK:
                return R.drawable.rock;
            case Rochambo.PAPER:
                return R.drawable.paper;
            case Rochambo.SCISSOR:
                return R.drawable.scissors;
            default:
                return R.drawable.none;
        }
    }
}

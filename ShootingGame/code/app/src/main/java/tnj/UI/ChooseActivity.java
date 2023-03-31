package tnj.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import tnj.Framework.GameView;
import tnj.Framework.R;

public class ChooseActivity extends Activity {

    public static int check = 1;
    ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_activity);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageButton buttonLeft = (ImageButton) findViewById(R.id.buttonLeft);
        ImageButton buttonRight = (ImageButton) findViewById(R.id.buttonRight);

        imageView = (ImageView) findViewById(R.id.imageView);


        buttonLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (check == 1)
                    check = 3;
                else
                    check--;

                if (check == 1)
                    imageView.setImageResource(R.drawable.char_1);
                else if (check == 2)
                    imageView.setImageResource(R.drawable.char_2);
                else if (check == 3)
                    imageView.setImageResource(R.drawable.char_3);
            }
        });

        buttonRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (check == 3)
                    check = 1;
                else
                    check++;

                if (check == 1)
                    imageView.setImageResource(R.drawable.char_1);
                if (check == 2)
                    imageView.setImageResource(R.drawable.char_2);
                if (check == 3)
                    imageView.setImageResource(R.drawable.char_3);
            }
        });



    }


    public void GameStartClick (View v){
        setContentView(new GameView(this));
    }

    public void GoMain (View v) {
        finish();
        //setContentView(R.layout.activity_main);
    }








}
package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];

    private boolean playerOneTurn=true;
    private int count;
    private int playerOnePoints;
    private int playerTwoPoints;

    private TextView textViewPlayerOne;
    private TextView textViewPlayerTwo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewPlayerOne=findViewById(R.id.text_view_p1);
        textViewPlayerTwo=findViewById(R.id.text_view_p2);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buttonID="button_"+i+j;
                int resID=getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }

        }

        Button buttonReset=findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){

            return;
        }

        if(playerOneTurn)
        {
            ((Button)v).setText("X");
        }
        else
        {
            ((Button)v).setText("O");
        }

        count++;

        if (winnerCheck()){
            if(playerOneTurn)
            {
                playerOneWin();
            }
            else
            {
                playerTwoWin();
            }
        }
        else if (count==9)
        {
            gameDraw();
        }
        else
        {
            playerOneTurn=!playerOneTurn;
        }
    }

    private boolean winnerCheck()
    {
        String[][] field = new String[3][3];

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                field[i][j]=buttons[i][j].getText().toString();

            }
        }

        for (int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&&!field[i][0].equals(""))
            {
                return true;
            }
        }

        for (int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&&!field[0][i].equals(""))
            {
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&&!field[0][0].equals(""))
        {
            return true;
        }

        if(field[0][2].equals(field[1][1])&&field[0][0].equals(field[0][2])&&!field[0][2].equals(""))
        {
            return true;
        }

        return false;
    }

    private void playerOneWin()
    {
        playerOnePoints++;
        Toast.makeText(this,"Player One Wins!!!",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }
    private void playerTwoWin()
    {
        playerTwoPoints++;
        Toast.makeText(this,"Player Two wins!!!",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();

    }

    private void gameDraw()
    {
        Toast.makeText(this, "Draw!!!", Toast.LENGTH_SHORT).show();
        resetBoard();

    }

    private void updatePoints()
    {
        textViewPlayerOne.setText("Player One "+ playerOnePoints);
        textViewPlayerTwo.setText("Player Two "+ playerTwoPoints);

    }

    private void resetBoard()
    {
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        count=0;
        playerOneTurn=true;
    }
}

package com.example.ticktactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String[][] squares = new String[3][3];
    private boolean isPlayerOne = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        squares[0][0] = null;
        squares[0][1] = null;
        squares[0][2] = null;
        squares[1][0] = null;
        squares[1][1] = null;
        squares[1][2] = null;
        squares[2][0] = null;
        squares[2][1] = null;
        squares[2][2] = null;
    }

    public void NewGame(View newGame) {
        Toast.makeText(this, "Go!", Toast.LENGTH_SHORT).show();
        Board("clear");
    }

    public void ClickSquare(View square) {
        if (!WinDown() && !WinAcross() && !WinDiagonal()) {
            String squareText = ((Button) square).getText().toString();

            if (squareText.equals("")) {
                String squareId = square.getResources().getResourceEntryName(square.getId());
                char [] squareIdArray = squareId.toCharArray();
                int row = Integer.parseInt(String.valueOf(squareIdArray[4]));
                int col = Integer.parseInt(String.valueOf(squareIdArray[5]));

                if (isPlayerOne) {
                    ((Button) square).setText("X");
                    squares[row][col] = "X";
                    isPlayerOne = false;
                }
                else {
                    ((Button) square).setText("O");
                    squares[row][col] = "O";
                    isPlayerOne = true;
                }

                if (WinAcross()) {
                    Toast.makeText(this, "Win across...", Toast.LENGTH_SHORT).show();
                }

                if (WinDown()) {
                    Toast.makeText(this, "Win down...", Toast.LENGTH_SHORT).show();
                }

                if (WinDiagonal()) {
                    Toast.makeText(this, "Win diagonal...", Toast.LENGTH_SHORT).show();
                }

                if (!WinDown() && !WinAcross() && !WinDiagonal()) {
                    IsTieGame();
                }

            }
        }
    }

    private boolean WinAcross() {
        boolean win = false;
        if (squares[0][0] != null && squares[0][0] == squares[0][1] && squares[0][0] == squares[0][2]) {
            win = true;
        }

        if (squares[1][0] != null && squares[1][0] == squares[1][1] && squares[1][0] == squares[1][2]) {
            win = true;
        }

        if (squares[2][0] != null && squares[2][0] == squares[2][1] && squares[2][0] == squares[2][2]) {
            win = true;
        }
        return win;
    }

    private boolean WinDown() {
        boolean win = false;
        if (squares[0][0] != null && squares[0][0] == squares[1][0] && squares[0][0] == squares[2][0]) {
            win = true;
        }

        if (squares[0][1] != null && squares[0][1] == squares[1][1] && squares[0][1] == squares[2][1]) {
            win = true;
        }

        if (squares[0][2] != null && squares[0][2] == squares[1][2] && squares[0][2] == squares[2][2]) {
            win = true;
        }
        return win;
    }

    private boolean WinDiagonal() {
        boolean win = false;
        if (squares[0][0] != null && squares[0][0] == squares[1][1] && squares[0][0] == squares[2][2]) {
            win = true;
        }

        if (squares[0][2] != null && squares[0][2] == squares[1][1] && squares[0][2] == squares[2][0]) {
            win = true;
        }
        return win;
    }

    private void IsTieGame() {
        if (Board("tie")) {
            Toast.makeText(this, "It's a tie...", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean Board(String command) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (command == "clear"){
                    isPlayerOne = true;

                    int resourceId = getResources().getIdentifier("btn_" + i + j, "id", getPackageName());
                    Button square = (Button) findViewById(resourceId);
                    square.setText("");

                    squares[i][j] = null;
                }

                if (command == "tie") {
                    if (squares[i][j] == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

       final EditText playerOne = findViewById(R.id.playerOneName);
       final EditText playerTwo = findViewById(R.id.playerTwoName);
       final Button startGameBtn = findViewById(R.id.startGameBtn);

       startGameBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String getPlayer1 = playerOne.getText().toString();
               final String getPlayer2 = playerTwo.getText().toString();

               if (getPlayer1.isEmpty() || getPlayer2.isEmpty())
               {
                   Toast.makeText(MenuActivity.this, "Nhập tên người chơi", Toast.LENGTH_SHORT).show();
               }
               else {
                   Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                   intent.putExtra("Người chơi 1", getPlayer1);
                   intent.putExtra("Người chơi 2", getPlayer2);
                   startActivity(intent);
               }
           }
       });
    }
}
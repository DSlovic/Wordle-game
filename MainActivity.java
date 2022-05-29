package com.example.skocko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    Button button = (Button) findViewById(R.id.button_pokreni);
    button.setOnClickListener(new View.OnClickListener()

    {
        public void onClick (View v){
            Intent i = new Intent(v.getContext(), activity_igra.class);
            startActivity(i);

    }
    });
}
}
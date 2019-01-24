package com.kphilip.tp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialisation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On récupère nos éléments
        Button button = (Button) findViewById(R.id.button_register);
        final EditText editName = (EditText) findViewById(R.id.editText_name);
        EditText editAuthor = (EditText) findViewById(R.id.editText_author);
        EditText editDate = (EditText) findViewById(R.id.editText_date);

        // Affichage des logs pour éventuel débug
        Log.d("CREATION", "We're in the oncreate() method");

        // Listener sur le bouton
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // On créé notre toast
                Context context = getApplicationContext();
                CharSequence text = "Creation : Success" + '\n' + "Name of the book : " + editName.getText();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);

                // Affichage du toast
                toast.show();

                // Affichage des logs pour éventuel débug
                Log.d("CREATION", "We've finish onClick");
            }
        });
    }
}

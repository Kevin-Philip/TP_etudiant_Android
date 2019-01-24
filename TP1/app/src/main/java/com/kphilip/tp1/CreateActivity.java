package com.kphilip.tp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialisation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // On récupère nos éléments
        final Button button = findViewById(R.id.button_register);
        final EditText editName = findViewById(R.id.editText_name);
        final EditText editAuthor = findViewById(R.id.editText_author);
        final EditText editDate = findViewById(R.id.editText_date);

        // Affichage des logs pour éventuel débug
        Log.d("CreateActivity", "We're in the oncreate() method");

        // On disable le bouton créer tant qu'aucun nom n'est rentré
        button.setEnabled(false);

        // Listener sur le bouton
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On créé notre toast
                Context context = getApplicationContext();
                String text = getResources().getString(R.string.toast) + editName.getText().toString();
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);

                // Affichage du toast
                toast.show();

                // Affichage des logs pour éventuel débug
                Log.d("CreateActivity", "End in onClick");

                //On stock le résultat de création du livre
                Intent data = new Intent();
                String bookName = editName.getText().toString();
                data.putExtra("bookName", bookName);
                setResult(RESULT_OK, data);

                // Pour l'option 2, on vide les champs texte et on focus sur titre plutot que terminer l'activité
                // finish();
                editName.getText().clear();
                editAuthor.getText().clear();
                editDate.getText().clear();
                editName.requestFocus();

            }
        });

        // Option 1 : Enable le bouton créer si le nom est rentré
        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Merci clément pour trim() !
                if (editName.getText().toString().trim().equals("") && button.isEnabled()) {
                    button.setEnabled(false);
                } else if (!button.isEnabled()) {
                    button.setEnabled(true);
                }
            }
        });
    }
}

package com.kphilip.tp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ListActivity extends Activity {

    static final int createActivity_Request = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Button button = (Button) findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pour start l'activity sans attendre de résultat particulier
                //startActivity(new Intent(ListActivity.this,CreateActivity.class));

                startActivityForResult(new Intent(ListActivity.this, CreateActivity.class), createActivity_Request);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == createActivity_Request) {
            Log.d("ListActivity", "requestCode == createActivity_Request "+"The RESULT_CODE is : "+resultCode);
            if (resultCode == RESULT_OK) {
                String bookName = (String) data.getExtras().getString("bookName");
                Log.d("ListActivity", "resultCode == RESULT_OK"+"The name of the book is : "+bookName );
            }
        }
    }
}

package com.example.word;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Uri uri_user = Uri.parse("content://com.example.word/word");
        final ContentValues values = new ContentValues();
        FloatingActionButton fab = findViewById(R.id.fab);
        final View button_insert = findViewById(R.id.button_insert);
        final EditText edittext_en = findViewById(R.id.edittext_en);
        final EditText edittext_ch = findViewById(R.id.edittext_ch);
        final ContentResolver resolver = getContentResolver();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = resolver.query(uri_user,new String[]{"id","en","ch"},null,null,null);
                while(cursor.moveToNext()){
                    System.out.println(cursor.getInt(0)+cursor.getString(1)+cursor.getString(2));
                }
                Toast.makeText(MainActivity.this,"finish",Toast.LENGTH_SHORT).show();
            }
        });
        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"onClick",Toast.LENGTH_SHORT).show();
                values.clear();
                values.put("en",edittext_en.getText().toString());
                values.put("ch",edittext_ch.getText().toString());
                resolver.insert(uri_user,values);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

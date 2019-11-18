package com.example.word;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final Uri uri_user = Uri.parse("content://com.example.word/word");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ContentValues values = new ContentValues();
        FloatingActionButton fab = findViewById(R.id.fab);
        final ContentResolver resolver = getContentResolver();
        init();
        fab.setOnClickListener(new View.OnClickListener() {
            private String en = "";
            private String ch = "";
            private ContentResolver resolver = getContentResolver();
            @Override
            public void onClick(View view) {
                final EditText editText_input_en = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this).setTitle("请输入英文").setView(editText_input_en).setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        en = editText_input_en.getText().toString();
                        final EditText edittext_input_ch = new EditText(MainActivity.this);
                        new AlertDialog.Builder(MainActivity.this).setTitle("请输入中文").setView(edittext_input_ch).setPositiveButton("添加", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ch = edittext_input_ch.getText().toString();
                                ContentValues values = new ContentValues();
                                values.put("en",en);
                                values.put("ch",ch);
                                resolver.insert(uri_user,values);
                                init();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
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
        if (id == R.id.action_setting) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void init(){
        List<word> wordList = new ArrayList<word>();
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri_user,new String[]{"id","en","ch"},null,null,null);
        while (cursor.moveToNext()){
            word word = new word();
            word.setId(cursor.getInt(0));
            word.setEn(cursor.getString(1));
            word.setCh(cursor.getString(2));
            wordList.add(word);
        }
        WordAdapter wordAdapter = new WordAdapter(MainActivity.this,R.layout.word_item,wordList);
        ListView listView = findViewById(R.id.content_listview);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word word=(word)adapterView.getItemAtPosition(i);
                Toast.makeText(MainActivity.this, word.getEn(),Toast.LENGTH_SHORT).show();
                new MyDialog(MainActivity.this,word).show();
            }
        });
    }
}

package com.example.word;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;

import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;


public class MyDialog extends Dialog {
    word word ;
    TextView textView ;
    String url_play=null;
    int INTERNET_FINISH = 5;

    final Uri uri_user = Uri.parse("content://com.example.word/word");
    public MyDialog(Context context,word word1){
        super(context);
        word = word1;
    }

    Handler han = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==INTERNET_FINISH){
                Bundle bundle = msg.getData();
                textView.append("英文:"+word.getEn()+"\n");
                textView.append("中文:"+bundle.get("translate")+"\n");
                textView.append("网络词条："+bundle.get("web_temp")+"\n");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog);
        final TextView textview_en = findViewById(R.id.mydialog_textview_en);
        final TextView textview_ch = findViewById(R.id.mydialog_textview_ch);
        textView = findViewById(R.id.mydialog_textview);
        textview_en.setText(word.getEn());
        textview_ch.setText(word.getCh());
        Button button_delete = findViewById(R.id.mydialog_delete);
        Button button_change = findViewById(R.id.mydialog_change);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ContentResolver resolver = getContext().getContentResolver();
//                resolver.delete(uri_user,"id=?",new String[]{String.valueOf(word.getId())});
                DBHelper dbHelper = new DBHelper(getContext());
                SQLiteDatabase temp = dbHelper.getWritableDatabase();
                temp.delete(DBHelper.TABLE_NAME_FIRST,"id=?",new String[]{String.valueOf(word.getId())});
                dismiss();
            }
        });
        button_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ContentResolver resolver = getContext().getContentResolver();
                int id = word.getId();
                final EditText textview_change_en = new EditText(getContext());
                textview_change_en.setText(word.getEn());
                final EditText textview_change_ch = new EditText(getContext());
                textview_change_ch.setText(word.getCh());
                new AlertDialog.Builder(getContext()).setTitle("更改英文").setView(textview_change_en).setPositiveButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final String en = textview_change_en.getText().toString();

                        new AlertDialog.Builder(getContext()).setTitle("更改中文").setView(textview_change_ch).setPositiveButton("确定", new OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String ch = textview_change_ch.getText().toString();
                                ContentValues values = new ContentValues();
                                values.put("en",en);
                                values.put("ch",ch);
//                                resolver.update(uri_user,values,"id = ?",new String[]{String.valueOf(word.getId())});
                                DBHelper dbHelper = new DBHelper(getContext());
                                SQLiteDatabase temp = dbHelper.getWritableDatabase();
                                temp.update(DBHelper.TABLE_NAME_FIRST,values,"id=?",new String[]{String.valueOf(word.getId())});
                                dismiss();
                            }
                        }).setNegativeButton("取消",null).show();
                    }
                }).setNegativeButton("取消",null).show();
            }
        });
        new Thread(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                String json=GetJson.get(word.getEn(),"en","zh-CHS");
                Gson gson = new Gson();
                JsonWord jsonWord=gson.fromJson(json,JsonWord.class);
                String translate = jsonWord.getTranslation().get(0);
                Bundle bundle = new Bundle();
                bundle.putString("translate",translate);
                String web_temp = "";
                try{
                for(int i=0;i<jsonWord.getWeb().get(0).getValue().size();i++){
                    if(i==0){
                        web_temp=web_temp+jsonWord.getWeb().get(0).getValue().get(i);
                    }else{
                        web_temp=web_temp+","+jsonWord.getWeb().get(0).getValue().get(i);
                    }
                }
                bundle.putString("web_temp",web_temp);
                Message mag = new Message();
                mag.what=INTERNET_FINISH;
                mag.setData(bundle);
                han.sendMessage(mag);}catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
        findViewById(R.id.mydialog_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer player = new MediaPlayer();
                new Thread(){
                    @Override
                    public void run() {
                        try{
                        player.setDataSource("http://dict.youdao.com/dictvoice?audio="+word.getEn());
                        player.prepareAsync();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }.start();
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        player.start();
                    }
                });
            }
        });
    }
}

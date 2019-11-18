package com.example.word;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyDialog extends Dialog {
    word word ;
    final Uri uri_user = Uri.parse("content://com.example.word/word");
    public MyDialog(Context context,word word1){
        super(context);
        word = word1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog);
        TextView textview_en = findViewById(R.id.mydialog_textview_en);
        TextView textview_ch = findViewById(R.id.mydialog_textview_ch);
        textview_en.setText(word.getEn());
        textview_ch.setText(word.getCh());
        Button button_delete = findViewById(R.id.mydialog_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver resolver = getContext().getContentResolver();
                resolver.delete(uri_user,"id=?",new String[]{String.valueOf(word.getId())});
                dismiss();
            }
        });
    }
}

package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.controller.DataProcessor;

public class CreatePostActivity extends BaseActivity {

    private EditText textPostET;
    private ImageView createPostBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);



        textPostET = (EditText) findViewById(R.id.textPostET);
        textPostET.post(new Runnable() {
            @Override
            public void run() {
                textPostET.requestFocus();
                InputMethodManager imm = (InputMethodManager) textPostET.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(textPostET, InputMethodManager.SHOW_IMPLICIT);
            }
        });


        createPostBTN = (ImageView) findViewById(R.id.createPostBTN);


        textPostET.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                DataProcessor.getInstance().setPostText(s.toString());
            }
        });

        createPostBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DataProcessor.getInstance().getPostText() == null || DataProcessor.getInstance().getPostText().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Введите пожалуйста текст для записи", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), FeedActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
package com.example.thuchanh3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView helloTextView;
    private EditText nameEditText;
    private Button createBtn, goToListBtn;
    private SharedPreferences userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTextView = (TextView) findViewById(R.id.name_text_view);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        createBtn = (Button) findViewById(R.id.create_btn);
        goToListBtn = (Button) findViewById(R.id.go_to_list_btn);

        userInfo = getSharedPreferences("userInfo", MODE_PRIVATE);

        helloTextView.setText("Xin Chao");

        if(getHelloName() == null) {
            nameEditText.setEnabled(true);
            createBtn.setEnabled(true);
            goToListBtn.setEnabled(false);
        } else {
            nameEditText.setEnabled(false);
            createBtn.setEnabled(false);

            String name = getHelloName();
            helloTextView.setText("Xin chao: " + name);
            goToListBtn.setEnabled(true);
        }

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(nameEditText.getText())) {
                    Toast.makeText(MainActivity.this, "Cannot leave name empty", Toast.LENGTH_SHORT).show();
                } else {
                    String name = nameEditText.getText().toString();

                    boolean result = userInfo.edit().putString("name", name).commit();
                    if(result) {
                        helloTextView.setText("Xin chao: " + name);
                        nameEditText.setEnabled(false);
                        createBtn.setEnabled(false);
                        goToListBtn.setEnabled(true);
                    } else {
                        Toast.makeText(MainActivity.this, "Fail to add new name", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        goToListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListProductActivity.class);
                startActivity(intent);
            }
        });
    }

    private String getHelloName() {
        String name;
        name = userInfo.getString("name", null);

        return  name;
    }

}
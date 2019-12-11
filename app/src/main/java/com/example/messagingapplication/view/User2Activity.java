package com.example.messagingapplication.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.messagingapplication.R;
import com.example.messagingapplication.view.util.Constants;

public class User2Activity extends AppCompatActivity {

    private TextView user2_messageView;
    private EditText user2_editText;
    private Button user2_button;
    private ImageView user2_image;

    public static String user2Key = "User two";
    private int REQUEST_USER_1 = 800;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);

        String messages = getIntent().getStringExtra(user2Key);

        user2_messageView = findViewById(R.id.messageView_user2);
        user2_editText = findViewById(R.id.editText_user2);
        user2_button = findViewById(R.id.sendButton_user2);
        user2_image = findViewById(R.id.user2_imageView);

        Glide.with(this)
                .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(getString(R.string.user2_imageSrc))
                .into(user2_image);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE);

        if(messages == null)
            messages = sharedPreferences.getString(MainActivity.spKey, "");

        user2_messageView.setText(messages);

        user2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user2_messageView.setText(user2_messageView.getText().toString() + "\nDalo:\t\t\t" + user2_editText.getText());
                Intent intent = new Intent(User2Activity.this, MainActivity.class);
                intent.putExtra(MainActivity.user1Key, user2_messageView.getText());

                startActivityForResult(intent, REQUEST_USER_1);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putString(MainActivity.spKey, user2_messageView.getText().toString());
        spEditor.apply();
//        spEditor.remove(spKey);
//        spEditor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == REQUEST_USER_1) {
            user2_messageView.setText(data.getStringExtra(user2Key));
        }
    }
}

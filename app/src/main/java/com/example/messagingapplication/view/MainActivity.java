package com.example.messagingapplication.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.messagingapplication.R;
import com.example.messagingapplication.view.util.Constants;

public class MainActivity extends AppCompatActivity {

    private TextView user1_messageView;
    private EditText user1_editText;
    private Button user1_button;
    private ImageView user1_image;

    public static String user1Key = "User One";
    public static String spKey = "sp messages";
    private int REQUEST_USER_2 = 801;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);

        String messages = getIntent().getStringExtra(user1Key);

        user1_messageView = findViewById(R.id.messageView_user1);
        user1_editText = findViewById(R.id.editText_user1);
        user1_button = findViewById(R.id.sendButton_user1);
        user1_image = findViewById(R.id.user1_imageView);

        Glide.with(this)
                .applyDefaultRequestOptions(RequestOptions.circleCropTransform())
                .load(getString(R.string.user1_imageSrc))
                .into(user1_image);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFRENCES_NAME, Context.MODE_PRIVATE);

        if(messages == null)
            messages = sharedPreferences.getString(spKey, "");

        user1_messageView.setText(messages);

        user1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user1_messageView.setText(user1_messageView.getText().toString()+"\nJoel:\t\t\t"+user1_editText.getText());
                Intent intent = new Intent(MainActivity.this, User2Activity.class);
                intent.putExtra(User2Activity.user2Key, user1_messageView.getText());

                startActivityForResult(intent, REQUEST_USER_2);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putString(spKey, user1_messageView.getText().toString());
        spEditor.apply();
        //spEditor.remove(spKey);
        //spEditor.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == REQUEST_USER_2) {
            user1_messageView.setText(data.getStringExtra(user1Key));
        }
    }
}

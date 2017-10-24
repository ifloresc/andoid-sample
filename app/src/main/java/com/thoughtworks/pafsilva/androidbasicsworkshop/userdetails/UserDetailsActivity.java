package com.thoughtworks.pafsilva.androidbasicsworkshop.userdetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtworks.pafsilva.androidbasicsworkshop.R;
import com.thoughtworks.pafsilva.androidbasicsworkshop.models.UserInfo;

public class UserDetailsActivity extends AppCompatActivity {

    private TextView textInfo;

    private TextView textPoint;

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        UserInfo user = (UserInfo) getIntent().getSerializableExtra("user");

        setTitle( user.getFirstName()  + " " + user.getLastName());

        textInfo = (TextView) findViewById(R.id.textInfo);
        textPoint = (TextView) findViewById(R.id.textPoint);
        image = (ImageView) findViewById(R.id.image);

        textInfo.setText(user.getCategory());
        textPoint.setText(user.getPoints());

        if (user.getFirstName().equals("Goku")) {
            image.setImageResource(R.drawable.goku);
        } else {
            image.setImageResource(R.drawable.vegeta);
        }
    }
}

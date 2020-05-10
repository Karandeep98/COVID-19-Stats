package com.aurora.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class AboutDeveloper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);
        setupHyperlink();
        Toolbar tb=findViewById(R.id.tb);
        setSupportActionBar(tb);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Button bt=findViewById(R.id.bt);
        bt.setOnClickListener(view -> {
        Intent i=new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:karandeep041998@gmail.com"));
        startActivity(Intent.createChooser(i,"Send Email"));
        });


    }
    private void setupHyperlink() {
        TextView linkTextView = findViewById(R.id.tv);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
//        linkTextView.setLinkTextColor(Color.);
    }
}

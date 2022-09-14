package com.example.yourmusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;

public class Dehaze extends AppCompatActivity {
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dehez);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id=item.getItemId();
//                if(id==R.id.opt_account){
//                    return true;
//                }else if(id==R.id.history){
//                     return true;
//                }else if(id==R.id.local_songs){
//                    Intent in=new Intent(Dehaze.this,FileSongs.class);
//                    startActivity(in);
//                    return true;
//                }else{
//                     return true;
//                }
//
//            }
//        });
    }
}
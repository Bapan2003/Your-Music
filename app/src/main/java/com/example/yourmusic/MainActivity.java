package com.example.yourmusic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btnPlay,btnPause,btnStop,btnLocal;
     ImageButton btnDehaze;
    DrawerLayout drawerLayout;
    BottomNavigationView nv;
    Toolbar tool_bar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MediaPlayer mp = new MediaPlayer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        btnDehaze=findViewById(R.id.dehaze);
        btnDehaze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iHum = new Intent(MainActivity.this, Dehaze.class);
                startActivity(iHum);
            }
        });
        btnLocal=findViewById(R.id.btnLocal);
        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FileSongs.class);
                startActivity(intent);
            }
        });




    }
}

//        drawerLayout=findViewById(R.id.drawer_layout);
//        navigationView=findViewById(R.id.navigation_view);
//        tool_bar=findViewById(R.id.tool_bar);
//        setSupportActionBar(tool_bar);
//        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
//                this,drawerLayout,tool_bar,R.string.OpenDrawer,R.string.CloseDrawer
//        );
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//              int id= item.getItemId();
//               if(id==R.id.opt_account){
//
//               }else if(id==R.id.history){
//
//               }else if(id==R.id.local_songs){
//
//               }else {
//
//               }
//
//
//                return true;
//            }
//
//      }











//        btnLocal=findViewById(R.id.btnLocal);
//        btnLocal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(MainActivity.this,FileSongs.class);
//                startActivity(intent);
//
//            }
//        });






//        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        String aPath = "android.resource://" + getPackageName() + "/raw/beche_thakar_gaan";
//
//        Uri audioURL = Uri.parse(aPath);
//        btnPlay =findViewById(R.id.btnPlay);
//        btnPause=findViewById(R.id.btnPause);
//
////        btnStop=findViewById(R.id.btnStop);
//        try {
//            mp.setDataSource(this, audioURL);
//            mp.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        btnPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mp.start();
//            }
//        });
//        btnPause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mp.pause();
//            }
//        });


//        btnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mp.stop();
//                mp.seekTo(0);
//            }
//        });
//        mp.seekTo();
//        mp.getDuration();
//        mp.getCurrentPosition();
//        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//
//            }
//        });





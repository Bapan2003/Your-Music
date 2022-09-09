package com.example.yourmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class FileSongs extends AppCompatActivity {
    RecyclerView recycler_view;
    TextView no_song_found;
    ArrayList<AudioModel> songsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_file_songs);
        recycler_view=findViewById(R.id.recylcle_view);
        no_song_found=findViewById(R.id.no_song_found);
        if(checkPermission()==false){
            requestPermission();
            return;
        }
        String[] projection={
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        String selection = MediaStore.Audio.Media.IS_MUSIC+" !=0";
        Cursor cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null);
        while (cursor.moveToNext()){
            AudioModel songData=new AudioModel(cursor.getString(1), cursor.getString(0), cursor.getString(2) );
            if(new File(songData.getPath()).exists()) {
                songsList.add(songData);
            }
            if(songsList.size()==0){
                no_song_found.setVisibility(View.VISIBLE);
            }else{
                recycler_view.setLayoutManager(new LinearLayoutManager(this));
                recycler_view.setAdapter(new MultiListAdapter(songsList,getApplicationContext()));
            }
        }
    }


    boolean checkPermission(){
        int result= ContextCompat.checkSelfPermission(FileSongs.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }
    void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(FileSongs.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(this, "Read permission are required,Please allow from settings", Toast.LENGTH_SHORT).show();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
        }
    }
    protected void onResume() {

        super.onResume();
        if(recycler_view!=null){
            recycler_view.setAdapter(new MultiListAdapter(songsList,getApplicationContext()));
        }
    }
}
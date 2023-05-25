package com.example.yourmusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MusicPlayer extends AppCompatActivity {
   TextView song_title,currTime,totalTime;
   ImageView music_player_icon,previous,next,pause_play;
   SeekBar seek_bar;
   ArrayList<AudioModel> songList;
   AudioModel currSong;
   MediaPlayer mediaPlayer=MyMediaPlayer.getInstance();
   int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        song_title=(TextView) findViewById(R.id.song_title);
        currTime=findViewById(R.id.currTime);
        totalTime=findViewById(R.id.totalTime);
        music_player_icon=findViewById(R.id.music_player_icon);
        previous=findViewById(R.id.previous);
        next=findViewById(R.id.next);
        pause_play=findViewById(R.id.pause_play);
        seek_bar=findViewById(R.id.seek_bar);

        song_title.setSelected(true);
        songList=(ArrayList<AudioModel>) getIntent().getSerializableExtra("List");
        setResourcesMusic();

            MusicPlayer.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(mediaPlayer!=null){
                        seek_bar.setMax(mediaPlayer.getCurrentPosition());
                        currTime.setText(convertToMS(mediaPlayer.getCurrentPosition()+""));

                        if(mediaPlayer.isPlaying()){
                            pause_play.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
                            music_player_icon.setRotation(x);
                        }else{
                            pause_play.setImageResource(R.drawable.ic_baseline_play_circle_outline_24);
                        }
                    }
                    new Handler().postDelayed(this,100);
                }
            });
            seek_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if(mediaPlayer!=null && b){
                        mediaPlayer.seekTo(i);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


        pause_play.setOnClickListener(view->pausePlay());
        next.setOnClickListener(view->nextMusic());
        previous.setOnClickListener(view->previousMusic());
        playMusic();


    }
    void setResourcesMusic(){
        currSong=songList.get(MyMediaPlayer.curIndex);
        song_title.setText(currSong.getTitle());
        totalTime.setText(convertToMS(currSong.getDuration()));
    }
    public static String convertToMS(String duration){
        Long milis=Long.parseLong(duration);
       return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(milis)%TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toMinutes(milis)%TimeUnit.MINUTES.toSeconds(1));

    }
    private void playMusic(){
   mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            seek_bar.setProgress(0);
            seek_bar.setMax(mediaPlayer.getDuration());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void nextMusic(){
        if(MyMediaPlayer.curIndex==songList.size()-1){
            MyMediaPlayer.curIndex=0;
            mediaPlayer.reset();
            setResourcesMusic();
            mediaPlayer.start();
            playMusic();
        }else {

            MyMediaPlayer.curIndex += 1;
            mediaPlayer.reset();
            setResourcesMusic();
            mediaPlayer.start();
            playMusic();
        }
    }
    private void previousMusic(){
        if(MyMediaPlayer.curIndex==0){
            MyMediaPlayer.curIndex =songList.size()-1;
            mediaPlayer.reset();
            setResourcesMusic();
            playMusic();
        }else {

            MyMediaPlayer.curIndex -= 1;
            mediaPlayer.reset();
            setResourcesMusic();
            playMusic();
        }
    }
    private void pausePlay(){
    if(mediaPlayer.isPlaying()){
        mediaPlayer.pause();
    }else{
        mediaPlayer.start();
    }
    }

}
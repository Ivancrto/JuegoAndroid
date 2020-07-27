package com.example.practica1;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class GameMusic implements Music, MediaPlayer.OnCompletionListener {

    MediaPlayer mediaPlayer;
    boolean isPrepared = false;
    private AssetFileDescriptor assetDescriptor;

    public GameMusic(AssetFileDescriptor assetDescriptor){
        this.assetDescriptor = assetDescriptor;
        mediaPlayer= new MediaPlayer();
        try{
            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                    assetDescriptor.getStartOffset(),
                    assetDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared=true;
            mediaPlayer.setOnCompletionListener(this);
        }catch(Exception e){
            throw new RuntimeException("No se ha podido cargar la música");
        }
    }

    @Override
    public void play() {
        if(mediaPlayer.isPlaying()){return;}

        try{
            synchronized (this){
                if(!isPrepared){
                    mediaPlayer.prepare();
                    isPrepared=true;
                }
                mediaPlayer.start();
            }

        }catch(IllegalStateException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        synchronized (this){
            isPrepared=false;
        }
    }

    @Override
    public void pause() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    public AssetFileDescriptor getAssetDescriptor() {
        return assetDescriptor;
    }

    public void setAssetDescriptor(AssetFileDescriptor assetDescriptor) {
        this.assetDescriptor = assetDescriptor;
    }


    @Override
    public void setLooping(boolean looping) {

        mediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {

        mediaPlayer.setVolume(volume,volume);
    }

    @Override
    public boolean isPlaying() {

        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {

        return !isPrepared;
    }

    @Override
    public boolean isLooping() {

        return mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this){
            isPrepared=false;
        }
    }

    public void changeMusic(int position){

    }
}

package com.stickfighter.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;

public class MusicPlayer implements Runnable{
    private ArrayList<String> songs;
    private int song_idx;

    public MusicPlayer(String[] files){
        songs=new ArrayList<String>();
        for(String file : files){//For all files in 'files', we add them to out music playlist.
            songs.add("./res/audio/"+file+".mp3");
        }
    }

    private void playSound(String file){
        try{
            File sound=new File(file);
            AudioInputStream inStream= AudioSystem.getAudioInputStream(sound);
            AudioFormat format=inStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class,format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(inStream);
            FloatControl control=(FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-10);
            clip.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        playSound(songs.get(song_idx));
    }
}

package com.example.leaguethingsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.leaguethingsjava.adapters.ChampionAdapter;
import com.example.leaguethingsjava.data.models.Champion;
import com.example.leaguethingsjava.domain.ChampionsDataSource;

import java.util.ArrayList;

public class JunglersActivity extends AppCompatActivity {

    private MediaPlayer myMediaPlayer;
    private AudioManager myAudioManager;

    private AudioManager.OnAudioFocusChangeListener myOnAudioFocusChangeListener = (focusChange) -> {
        if(focusChange  == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
            myMediaPlayer.pause();
            myMediaPlayer.seekTo(0);
        }
        else if (focusChange ==  AudioManager.AUDIOFOCUS_GAIN){
            myMediaPlayer.start();
        }
        else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
            releaseMediaPlayer();
        }
    };

    private MediaPlayer.OnCompletionListener myCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.champions_list);

        myAudioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Champion> champions = new ArrayList<>();

        for(Champion champion: ChampionsDataSource.champions){
            if(champion.getRoles().equals("Jungler"))
                champions.add(champion);
        }

        ChampionAdapter championAdapter = new ChampionAdapter(this,champions,R.color.role_jungler);
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(championAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Champion champion = champions.get(position);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = myAudioManager.requestAudioFocus(myOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    myMediaPlayer = MediaPlayer.create(JunglersActivity.this, champion.getPhraseId());

                    // Start the audio file
                    myMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    myMediaPlayer.setOnCompletionListener(myCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (myMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            myMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            myMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            myAudioManager.abandonAudioFocus(myOnAudioFocusChangeListener);
        }
    }
}
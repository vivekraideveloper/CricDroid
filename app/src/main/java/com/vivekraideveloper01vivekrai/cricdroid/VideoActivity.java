package com.vivekraideveloper01vivekrai.cricdroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.r0adkll.slidr.Slidr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class VideoActivity extends YouTubeBaseActivity {
    private static final int MAX_LINES = 1;
    Toolbar toolbar;
    TextView textView;
    YouTubePlayerView playerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private InterstitialAd interstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

        interstitialAd = new InterstitialAd(VideoActivity.this);

        interstitialAd.setAdUnitId("ca-app-pub-2331594731643176/7073807871");

        AdRequest request = new AdRequest.Builder().build();

        interstitialAd.loadAd(request);


        Slidr.attach(this);

        textView = findViewById(R.id.description);
        Bundle bundle = getIntent().getExtras();
        final String videoId = bundle.getString("videoId");
        final String description = bundle.getString("description");
        final String name = bundle.getString("name");

        textView.setText(description);
        ResizableCustomView.doResizeTextView(textView, MAX_LINES, "Read More", true);
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(bundle.getString("name"));

        playerView = findViewById(R.id.youtubePlayerView);


        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoId);
                youTubePlayer.setPlaybackEventListener(playbackEventListener);
                youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
                if (!b){
                    youTubePlayer.cueVideo(videoId);
                }


            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                final int REQUEST_CODE = 1;
                if (youTubeInitializationResult.isUserRecoverableError()){
                    youTubeInitializationResult.getErrorDialog(VideoActivity.this, REQUEST_CODE).show();
                }else {
                    Toast.makeText(VideoActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        };

        playerView.initialize(YoutubeConfig.getApiKey(), onInitializedListener);
    }





    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }

    } ;

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
//            Toast.makeText(VideoActivity.this, "Click Ad now, make the video creator rich!", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}

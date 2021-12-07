package com.example.voicerecordingapp;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.voicerecordingapp.Voice_list_adapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class  Audio_list_F extends Fragment implements Voice_list_adapter.onItemListClick {

    private ConstraintLayout player_Sheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private RecyclerView audio_List;
    private File[] all_Files;
    private Voice_list_adapter audio_ListAdapter;
    private MediaPlayer mediaPlayer = null;
    private boolean is_Playing = false;
    private File file_ToPlay = null ;
    //UI Elements
    private ImageButton play_Btn;
    private TextView player_Header;
    private TextView player_Filename;
    private SeekBar player_seekbar;
    private Handler seekbar_handler;
    private Runnable update_seekbar;
    private ImageButton next , prev;
    private ImageView backbtn;


    public Audio_list_F() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_list_, container, false);



    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        player_Sheet = view.findViewById(R.id.player_sheet_id);
        bottomSheetBehavior = BottomSheetBehavior.from(player_Sheet);
        audio_List = view.findViewById(R.id.audio_list_view_id);

        play_Btn = view.findViewById(R.id.player_play_btn);
        player_Header = view.findViewById(R.id.player_header_title_id);
        player_Filename = view.findViewById(R.id.player_file_name_id);
        player_seekbar = view.findViewById(R.id.player_seek_id);
        next= view.findViewById(R.id.forward_btn_id);
        prev= view.findViewById(R.id.back_btn_id);
        backbtn= view.findViewById(R.id.back_bt_id);


        String path = getActivity().getExternalFilesDir("/").getAbsolutePath();
        File directory = new File(path);
        all_Files = directory.listFiles();

        audio_ListAdapter = new Voice_list_adapter(all_Files, this);

        audio_List.setHasFixedSize(true);
        audio_List.setLayoutManager(new LinearLayoutManager(getContext()));
        audio_List.setAdapter(audio_ListAdapter);

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_HIDDEN){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        play_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mediaPlayer.isPlaying())
                {
                    play_Btn.setBackgroundResource(R.drawable.pause);
                    mediaPlayer.pause();

                    //  seekbar_handler.removeCallbacks(update_seekbar);
                }else
                {
                    play_Btn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayer.start();
                    player_Header.setText("Playing");
                }

             /*   if (mediaPlayer.is_Playing)
                {
                    pause_audio();
                }else
                {
                    *//*if (file_ToPlay == null)
                    {*//*
                    resume_audio();
                }*/
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+2000);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-2000);
                }
            }
        });
       /* backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
                startActivity(intent);

            }
        });*/

        backbtn.setOnClickListener(v-> onBackPressed());

        player_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                onPause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                mediaPlayer.seekTo(progress);
                onResume();
            }
        });

    }

    private void onBackPressed() {
        Intent intent = new Intent(getActivity().getApplication(), MainActivity.class);
        startActivity(intent);
    }

  /*  private void set_toolbar() {

        toolbar =view.findViewById(R.id.toolbar_id);

      ImageView back = toolbar.findViewById(R.id.back_toolbar_id);

        back.setVisibility(View.INVISIBLE);

    }*/

    @Override
    public void onClickListener(File file, int position) {
        file_ToPlay = file;
        if(is_Playing){
            stopAudio();
            playAudio(file_ToPlay);
        } else {
            playAudio(file_ToPlay);

        }
    }

    @Override
    public void onclickListener(File file, int position) {
        file_ToPlay = file;
        if(is_Playing){
            stopAudio();
            playAudio(file_ToPlay);
        } else {
            playAudio(file_ToPlay);

        }
    }

   /* private void pause_audio()
    {
        mediaPlayer.pause();
        play_Btn.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_baseline_play_arrow_24, null));
        is_Playing= false;
    }*/

    /*private void resume_audio()
    {
        mediaPlayer.start();
        play_Btn.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.pause, null));
        is_Playing= false;
    }*/

    private void stopAudio() {
        //Stop The Audio
        //play_Btn.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
        //
        //play_Btn.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_baseline_play_arrow_24, null));
        player_Header.setText("Stopped");
        is_Playing = false;
 //       mediaPlayer.stop();
        seekbar_handler.removeCallbacks(update_seekbar);
    }

    private void playAudio(File fileToPlay) {

        mediaPlayer = new MediaPlayer();

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        try {
            mediaPlayer.setDataSource(fileToPlay.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //play_Btn.setBackgroundResource(R.drawable.pause);
        //play_Btn.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.pause, null));
        player_Filename.setText(fileToPlay.getName());
        player_Header.setText("Playing");


        is_Playing = true;


        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //stopAudio();
                player_Header.setText("Finished");
            }
        });

        //seekbar
        player_seekbar.setMax(mediaPlayer.getDuration());
        seekbar_handler=new Handler();
        update_seekbar=new Runnable() {
            @Override
            public void run() {
                player_seekbar.setProgress(mediaPlayer.getCurrentPosition());
                seekbar_handler.postDelayed(this, 200);
            }
        };
        seekbar_handler.postDelayed(update_seekbar, 0);

    }

    @Override
    public void onStop() {
        super.onStop();
        //stopAudio();


    }
}
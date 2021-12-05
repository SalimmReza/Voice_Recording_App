package com.example.voicerecordingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;


public class Audio_list_F extends Fragment {
    private ConstraintLayout player_sheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private RecyclerView audio_list;
    private File[] all_files;
    private Voice_list_adapter voice_list_adapter;



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

            player_sheet=view.findViewById(R.id.player_sheet_id);
            audio_list= view.findViewById(R.id.audio_list_view_id);
        bottomSheetBehavior = BottomSheetBehavior.from(player_sheet);


            String path= getActivity().getExternalFilesDir("/").getAbsolutePath();
            File directory = new File(path);
            all_files=directory.listFiles();
            voice_list_adapter = new Voice_list_adapter(all_files);

            audio_list.setHasFixedSize(true);
            audio_list.setLayoutManager(new LinearLayoutManager(getContext()));
            audio_list.setAdapter(voice_list_adapter);


            bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    //NO NEED TO ANYTHING
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            });

    }
    }

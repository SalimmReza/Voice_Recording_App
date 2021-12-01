package com.example.voicerecordingapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Record_Fragment extends Fragment implements View.OnClickListener {

    private NavController nav_Controller;
    private ImageView list_btn;

    public Record_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nav_Controller= Navigation.findNavController(view);
        list_btn= view.findViewById(R.id.record_list_btn_id);


        list_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.record_list_btn_id:
                nav_Controller.navigate(R.id.action_record_Fragment_to_audio_list_F2);
                break;
        }
    }
}
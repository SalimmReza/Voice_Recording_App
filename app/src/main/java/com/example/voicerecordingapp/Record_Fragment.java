package com.example.voicerecordingapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Record_Fragment extends Fragment implements View.OnClickListener {

    private NavController nav_Controller;
    private ImageButton list_btn;
    private ImageButton record_btn;
    private boolean is_recording = false;
    private String record_permission = Manifest.permission.RECORD_AUDIO;
    private int permisssion_code=22;
    private MediaRecorder mediaRecorder;
    private String record_file;
    private Chronometer chronometer;
    private TextView file_name;

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
        record_btn=view.findViewById(R.id.record_button_id);
        chronometer = view.findViewById(R.id.record_timer_id);
        file_name = view.findViewById(R.id.record_file_name);


        list_btn.setOnClickListener(this);
        record_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.record_list_btn_id:
                if (is_recording)
                {
                    AlertDialog.Builder alert_dialog = new AlertDialog.Builder(getContext());
                    alert_dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            //  stop_recording();
                            nav_Controller.navigate(R.id.action_record_Fragment_to_audio_list_F2);
                            is_recording= false;
                        }
                    });
                    alert_dialog.setNegativeButton("No", null);
                    alert_dialog.setTitle("Audio still recording");
                    alert_dialog.setMessage("Stop recording");
                    alert_dialog.create().show();
                }else
                {
                    nav_Controller.navigate(R.id.action_record_Fragment_to_audio_list_F2);
                }


                break;

            case R.id.record_button_id:
                if (is_recording)
                {
                    //stop recording
                    stop_recording();
                    record_btn.setImageDrawable(getResources().getDrawable(R.drawable.voice, null));
                    is_recording= false;
                }else {
                    //start recording

                    if (check_permision()) {

                        start_recording();
                        record_btn.setImageDrawable(getResources().getDrawable(R.drawable.microphone, null));
                        is_recording = true;
                    }
                }
                break;
        }
    }

    private void stop_recording() {
        chronometer.stop();
        file_name.setText("Recording Stopped, File saved : "+ record_file);

        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder=null;

    }

    private void start_recording() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        String record_path = getActivity().getExternalFilesDir("/").getAbsolutePath();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.CANADA);
        Date now = new Date();

        record_file= "Recording" + format.format(now)+ ".3gp";

        file_name.setText("Recording, File name : "+ record_file);


        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(record_path + "/" + record_file);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try{
            mediaRecorder.prepare();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        mediaRecorder.start();

    }

    private boolean check_permision() {
        if (ActivityCompat.checkSelfPermission(getContext(),record_permission )== PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }else
        {
            ActivityCompat.requestPermissions(getActivity(),new String[]{record_permission},permisssion_code);
            return false;
        }
    }

    public void onStop() {
        super.onStop();
        if (is_recording)
        {
            stop_recording();
        }



    }
}
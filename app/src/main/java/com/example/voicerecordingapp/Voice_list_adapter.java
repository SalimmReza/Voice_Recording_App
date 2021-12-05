package com.example.voicerecordingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

public class Voice_list_adapter extends RecyclerView.Adapter<Voice_list_adapter.VoiceViewHolder> {

    private File[] all_files;
   public Voice_list_adapter(File[] all_files){
       this.all_files=all_files;
   }
    @NonNull
    @Override
    public VoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_listt_item, parent , false);
       return new VoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoiceViewHolder holder, int position) {
        holder.list_tittle.setText(all_files[position].getName());
        holder.list_dateee.setText(all_files[position].lastModified()+ "");

    }

    @Override
    public int getItemCount() {
        return all_files.length;
    }

    public class VoiceViewHolder extends RecyclerView.ViewHolder{

       private ImageView list_imagee;
       private TextView list_tittle, list_dateee;
        public VoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            list_imagee = itemView.findViewById(R.id.list_image_view_id);
            list_tittle = itemView.findViewById(R.id.list_title_id);
            list_dateee = itemView.findViewById(R.id.list_date_id);

        }
    }
}

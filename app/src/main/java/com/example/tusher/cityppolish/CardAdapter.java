package com.example.tusher.cityppolish;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private ImageLoader imageLoader; //cardAdapter to show the contents in a feed view like interface
    private Context context;

    List<citypol> citypols; // creates a list called citypols that is an instance of the components of citypol class

    public CardAdapter(List<citypol> citypols, Context context){
        super();
        this.citypols=citypols;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.city_polish,parent, false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        citypol cpol= citypols.get(position);
        imageLoader=CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(cpol.getImageUrl(),ImageLoader.getImageListener(holder.imageView, R.drawable.image, android.R.drawable.ic_dialog_alert));// sets a default loading image in the ImageView till the image loads
        holder.imageView.setImageUrl(cpol.getImageUrl(), imageLoader); // gets imageURL
        holder.textViewName.setText(cpol.getName()); // gets Name/details
        holder.textViewPublisher.setText(cpol.getTime()); // gets time of report
        holder.textViewLocation.setText(cpol.getLocation()); // gets location


    }
    public int getItemCount(){
        return citypols.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        //Views
        public NetworkImageView imageView;
        public TextView textViewName;
        public TextView textViewPublisher;
        public TextView textViewLocation;
        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (NetworkImageView) itemView.findViewById(R.id.imageViewHero);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
            textViewLocation=(TextView) itemView.findViewById(R.id.textViewLocation);
        }
    }


}

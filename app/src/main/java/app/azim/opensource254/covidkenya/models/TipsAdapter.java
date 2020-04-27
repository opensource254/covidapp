package app.azim.opensource254.covidkenya.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.activities.TipsDetails;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {
    private List<TipsData> tipsData;
    private Context context;

    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public TipsAdapter(List<TipsData> tipsData, Context context) {

        //Initialize the List and Context
        this.tipsData = tipsData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_items,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final TipsData tips = tipsData.get(position);

        holder.tip.setText(tips.getTitle());


        Picasso.get()
                .load(tips.getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return tipsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView tip;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            tip = itemView.findViewById(R.id.tip_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

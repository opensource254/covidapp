package app.azim.opensource254.covidkenya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    List<TipsData> tipsList;
    Context context;

    public TipsAdapter(List<TipsData>list,Context context){
        tipsList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TipsData tipsData = tipsList.get(position);
        Picasso.get()
                .load(tipsData.getImageUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return tipsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageHolder);
        }
    }
}

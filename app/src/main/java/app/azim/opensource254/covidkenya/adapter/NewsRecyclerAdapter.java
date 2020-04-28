package app.azim.opensource254.covidkenya.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import app.azim.opensource254.covidkenya.R;
import app.azim.opensource254.covidkenya.models.NewsTweet;
import app.azim.opensource254.covidkenya.models.Tweet;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "NewsRecyclerAdapter";
    public List<NewsTweet> newsList;


    public NewsRecyclerAdapter(List<NewsTweet> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_recycler_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final NewsTweet tweet = newsList.get(position);
        holder.txtTwitterHead.setText(tweet.getScreen_name());
        holder.txtTwitterHandle.setText(tweet.getUsername());
        holder.txtTwitterBody.setText(tweet.getText());
        holder.txtPostTime.setText(tweet.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

//    Filter filter = new Filter() {
//        // run on a background thread
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<Tweet> filteredList = new ArrayList<>();
//
//            if (constraint.toString().isEmpty()){
//                filteredList.addAll(newsListAll);
//            } else {
//                for (Tweet tweet: newsListAll){
//                    if (tweet.getTweet().toLowerCase().contains(constraint.toString().toLowerCase())){
//                        filteredList.add(tweet);
//                    } else {
//                    }
//                }
//            }
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredList;
//            return filterResults;
//        }
//        //runs on a UI thread
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            newsList.clear();
//            newsList.addAll((Collection<? extends Tweet>) results.values);
//            notifyDataSetChanged();
//        }
//    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgTwitterImage;
        TextView txtTwitterHead, txtTwitterHandle, txtTwitterBody, txtPostTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgTwitterImage = itemView.findViewById(R.id.img_twitter_image);
            txtTwitterHead = itemView.findViewById(R.id.txt_twitter_head);
            txtTwitterHandle = itemView.findViewById(R.id.txt_twitter_handle);
            txtTwitterBody = itemView.findViewById(R.id.txt_twitter_body);
            txtPostTime = itemView.findViewById(R.id.txt_post_time);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    newsList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });

        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), (CharSequence) newsList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }
}

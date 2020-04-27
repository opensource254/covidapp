package app.azim.opensource254.covidkenya.activities;

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
import app.azim.opensource254.covidkenya.models.HealthUnit;

public class HealthUnitsRecyclerAdapter extends RecyclerView.Adapter<HealthUnitsRecyclerAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "HealthUnitsRecyclerAdapter";
    public List<HealthUnit> healthUnitsList;
    public List<HealthUnit> healthUnitsListAll;

    public HealthUnitsRecyclerAdapter(List<HealthUnit> healthUnitsList) {
        this.healthUnitsList = healthUnitsList;
        healthUnitsListAll = new ArrayList<>();
        healthUnitsListAll.addAll(healthUnitsList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.health_units_recycler_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HealthUnit healthUnit = healthUnitsList.get(position);
        holder.txtOpen.setText(healthUnit.getOpen());
        holder.txtTitle.setText(healthUnit.getTitle());
        holder.txtDescription.setText(healthUnit.getDescription());
    }

    @Override
    public int getItemCount() {
        return healthUnitsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        // run on a background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<HealthUnit> filteredList = new ArrayList<>();

            if (constraint.toString().isEmpty()){
                filteredList.addAll(healthUnitsListAll);
            } else {
                for (HealthUnit healthUnit: healthUnitsListAll){
                    if (healthUnit.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(healthUnit);
                    } else {
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }
        //runs on a UI thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            healthUnitsList.clear();
            healthUnitsList.addAll((Collection<? extends HealthUnit>) results.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtOpen, txtTitle, txtDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtOpen = itemView.findViewById(R.id.text_view_open);
            txtTitle = itemView.findViewById(R.id.text_view_title);
            txtDescription = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    healthUnitsList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });

        }

        @Override
        public void onClick(View view) {
//            Toast.makeText(view.getContext(), (CharSequence) healthUnitList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    }
}

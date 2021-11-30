package it.aton.android.ploapp.ui.statistics;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.aton.android.ploapp.data.local.converters.Converters;
import it.aton.android.ploapp.data.local.model.Poo;
import it.aton.android.ploapp.placeholder.PlaceholderContent.PlaceholderItem;
import it.aton.android.ploapp.databinding.FragmentPooItemBinding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPooRecyclerViewAdapter extends RecyclerView.Adapter<MyPooRecyclerViewAdapter.ViewHolder> {

    private List<Poo> poos;

    public MyPooRecyclerViewAdapter(List<Poo> poos) {
        this.poos = poos;
    }

    public void updateData(List<Poo> poos) {
        this.poos.clear();
        this.poos.addAll(poos);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentPooItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //  todo  riempire la view
        Poo poo= poos.get(position);

        holder.binding.pooItemColorCard.setCardBackgroundColor(poo.getColor());
        holder.binding.pooItemType.setImageResource(poo.getType());
        holder.binding.pooItemQuantity.setImageResource(poo.getQuantityImage());

        LocalDateTime dateTime =Converters.fromStringToDate(poo.getDateTime());

        holder.binding.pooItemDate.setText(dateTime.getMonth()+"/"+dateTime.getDayOfMonth()+"/"+dateTime.getYear());
        holder.binding.pooItemTime.setText(dateTime.getHour()+":"+dateTime.getMinute());

        holder.binding.pooItemSessionTime.setText(poo.getSessionTime()+" minutes");
        if(poo.isBloodPresent()){
            holder.binding.pooItemBlood.setVisibility(View.VISIBLE);
        }
        if(poo.isEnemaUsed()){
            holder.binding.pooItemEnema.setVisibility(View.VISIBLE);
        }
        if(poo.isPainful()){
            holder.binding.pooItemPain.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return poos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        FragmentPooItemBinding binding;

        public ViewHolder(FragmentPooItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
package it.aton.android.ploapp.ui.statistics;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.aton.android.ploapp.data.local.converters.Converters;
import it.aton.android.ploapp.data.local.model.Poo;
import it.aton.android.ploapp.placeholder.PlaceholderContent.PlaceholderItem;
import it.aton.android.ploapp.databinding.FragmentPooItemBinding;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPooRecyclerViewAdapter extends RecyclerView.Adapter<MyPooRecyclerViewAdapter.ViewHolder> {

    private final List<Poo> poos;

    private SelectionTracker<Long> tracker;

    public MyPooRecyclerViewAdapter(List<Poo> poos) {

        //Setting that option to true will just tell the RecyclerView that each item in the data set can be represented with a unique identifier of type Long
        setHasStableIds(true);
        this.poos = poos;
    }

    public void updateData(List<Poo> poos) {
        this.poos.clear();
        this.poos.addAll(poos);
        notifyDataSetChanged();
    }

    public void setTracker( SelectionTracker<Long> tracker){
        this.tracker=tracker;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentPooItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //  todo  riempire la view
        Poo poo= poos.get(position);
        if(tracker!=null){
            holder.bind(poo, tracker.isSelected(Integer.toUnsignedLong(position)));
        }
    }

    @Override
    public int getItemCount() {
        return poos.size();
    }

    @Override
    public long getItemId(int position) {

        return poos.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        FragmentPooItemBinding binding;

        public ViewHolder(FragmentPooItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bind(Poo poo, boolean isActivated){

            itemView.setActivated(isActivated);

            if(isActivated){
                binding.pooItem.setElevation(100);
            }

            binding.pooItemColorCard.setCardBackgroundColor(poo.getColor());
            binding.pooItemType.setImageResource(poo.getType());
            binding.pooItemQuantity.setImageResource(poo.getQuantityImage());

            LocalDateTime dateTime =Converters.fromStringToDate(poo.getDateTime());

            binding.pooItemDate.setText(MessageFormat.format("{0}/{1}/{2}", dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getYear()));
            binding.pooItemTime.setText(MessageFormat.format("{0}:{1}", dateTime.getHour(), dateTime.getMinute()));

            binding.pooItemSessionTime.setText(MessageFormat.format("{0} minutes", poo.getSessionTime()));
            if(poo.isBloodPresent()){
               binding.pooItemBlood.setVisibility(View.VISIBLE);
            }
            if(poo.isEnemaUsed()){
                binding.pooItemEnema.setVisibility(View.VISIBLE);
            }
            if(poo.isPainful()){
                binding.pooItemPain.setVisibility(View.VISIBLE);
            }
        }

        public ItemDetailsLookup.ItemDetails<Long> getDetails(){
            return new ItemDetailsLookup.ItemDetails<Long>() {
                @Override
                public int getPosition() {
                    return getLayoutPosition();
                }

                @Nullable
                @Override
                public Long getSelectionKey() {
                    Log.d("ITEM-ID", String.valueOf(getItemId()));
                    return getItemId();
                }
            };
        }

    }
}
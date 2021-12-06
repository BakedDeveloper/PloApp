package it.aton.android.ploapp.ui.statistics;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
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

public class MyPooRecyclerViewAdapter extends RecyclerView.Adapter<MyPooRecyclerViewAdapter.ViewHolder> {

    private List<Poo> poos;
    private List<Integer> activatedItemsList= new ArrayList<>();
    private Context context;

    public MyPooRecyclerViewAdapter(List<Poo> poos, Context context) {
        this.context= context;
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
        holder.arrangeViews(poos.get(position));
        holder.setCardItemBehaviour();
    }

    @Override
    public int getItemCount() {
        return poos.size();
    }

    public List<Integer> getActivatedItemsList(){
        return activatedItemsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentPooItemBinding binding;
        private boolean isActivated=false;

        public ViewHolder(FragmentPooItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void arrangeViews(Poo poo){
            binding.pooItemColorCard.setCardBackgroundColor(context.getResources().getColor(poo.getColor(), context.getTheme()));
            binding.pooItemType.setImageResource(poo.getType());
            binding.pooItemQuantity.setImageResource(poo.getQuantityImage());

            LocalDateTime dateTime =Converters.fromStringToDate(poo.getDateTime());

            binding.pooItemDate.setText(String.format("%s/%d/%d", dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getYear()));
            binding.pooItemTime.setText(String.format("%d:%d", dateTime.getHour(), dateTime.getMinute()));

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

        private void setCardItemBehaviour(){
            binding.pooItem.setOnLongClickListener(v -> {

                v.setActivated(isActivated=(!isActivated));

                if(isActivated){
                  activatedItemsList.add(getLayoutPosition());
                  binding.pooItem.setElevation(100);
                }
                return true;
            });
        }

    }
}
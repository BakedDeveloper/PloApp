package it.aton.android.ploapp.ui.statistics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.schedulers.Schedulers;
import it.aton.android.ploapp.R;
import it.aton.android.ploapp.data.local.converters.Converters;
import it.aton.android.ploapp.data.local.model.Poo;
import it.aton.android.ploapp.data.local.repositories.PooRepository;
import it.aton.android.ploapp.databinding.FragmentPooItemBinding;

public class MyPooRecyclerViewAdapter extends RecyclerView.Adapter<MyPooRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private PooRepository pooRepository;

    private final List<Poo> poos;
    private final List<Poo> activatedItemsList = new ArrayList<>();


    public MyPooRecyclerViewAdapter(List<Poo> poos, Context context) {
        this.context = context;
        pooRepository= new PooRepository(context);
        this.poos = poos;
    }

    public void updateData(List<Poo> poos) {
        this.poos.clear();
        this.poos.addAll(poos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentPooItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.arrangeViews(poos.get(position));
    }

    @Override
    public int getItemCount() {
        return poos.size();
    }

    @SuppressLint("CheckResult")
    public void removePooFromDb(RecyclerView.ViewHolder holder) {

        Poo poo= poos.get(holder.getLayoutPosition());
        poos.remove(poo);
        pooRepository.removePoo(poo)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        () -> {
                            Log.i("DATABASE", "Item with id: " + poo.getId() + " removed.");
                        }
                        , throwable -> Log.e("DATABASE", "Error removing Poo with id: " + poo.getId() + throwable.getMessage())
                );
        notifyItemRemoved(holder.getLayoutPosition());;
    }

    public List<Poo> getActivatedItemsList() {
        return activatedItemsList;
    }

    public void swapItems(int from, int to){
        Collections.swap(poos, from, to);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final FragmentPooItemBinding binding;
        private boolean isActivated = false;

        public ViewHolder(FragmentPooItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void arrangeViews(Poo poo) {


            isActivated = activatedItemsList.contains(poo);

            binding.pooItemColorCard.setCardBackgroundColor(context.getResources().getColor(poo.getColor(), context.getTheme()));
            binding.pooItemType.setImageResource(poo.getType());
            binding.pooItemQuantity.setImageResource(poo.getQuantityImage());

            LocalDateTime dateTime = Converters.fromStringToDate(poo.getDateTime());

            binding.pooItemDate.setText(String.format("%s/%d/%d", dateTime.getMonth(), dateTime.getDayOfMonth(), dateTime.getYear()));
            binding.pooItemTime.setText(String.format("%d:%d", dateTime.getHour(), dateTime.getMinute()));

            binding.pooItemSessionTime.setText(MessageFormat.format("{0} minutes", poo.getSessionTime()));

            if(!poo.hasOptions()){
                binding.optionsLayout.setVisibility(View.GONE);
            }

            if (poo.isBloodPresent()) {
                binding.pooItemBlood.setVisibility(View.VISIBLE);
            }
            if (poo.isEnemaUsed()) {
                binding.pooItemEnema.setVisibility(View.VISIBLE);
            }

            if(poo.isCramps()){
                binding.pooItemCramps.setVisibility(View.VISIBLE);
            }

            if(poo.isNausea()){
                binding.pooItemNausea.setVisibility(View.VISIBLE);
            }

            if(poo.isSwelling()){
                binding.pooItemSwelling.setVisibility(View.VISIBLE);
            }

            if(poo.isPeriod()) {
                binding.pooItemPeriod.setVisibility(View.VISIBLE);
            }

            if (isActivated) {
                binding.pooItem.setElevation(100);
                binding.pooItem.setCardBackgroundColor(getColor(R.color.activated_item_background));
            } else {
                binding.pooItem.setCardBackgroundColor(getColor(R.color.item_background));
                binding.pooItem.setElevation(0);
            }


            binding.pooItem.setOnLongClickListener(v -> {
                isActivated = (!isActivated);

                if (isActivated) {
                    activatedItemsList.add(poo);
                } else {
                    activatedItemsList.remove(poo);
                }
                arrangeViews(poo);
                return true;
            });
        }

        private int getColor(int colorId) {
            return context.getResources().getColor(colorId, context.getTheme());
        }

    }
}
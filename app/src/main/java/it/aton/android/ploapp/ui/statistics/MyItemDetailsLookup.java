package it.aton.android.ploapp.ui.statistics;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemDetailsLookup extends ItemDetailsLookup<Long> {

    private RecyclerView recyclerView;

    public MyItemDetailsLookup(RecyclerView recyclerView){
        this.recyclerView=recyclerView;
    }


    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent e) {
        View itemView = recyclerView.findChildViewUnder(e.getX(), e.getY());

        if(itemView!=null){
            return ((MyPooRecyclerViewAdapter.ViewHolder)recyclerView.getChildViewHolder(itemView)).getDetails();
        }

        return null;
    }
}

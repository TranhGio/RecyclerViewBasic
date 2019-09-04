package com.sun.simplecallback;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.Collections;

public class SwipeHelper extends ItemTouchHelper.SimpleCallback {
    CountryAdapter mCountryAdapter;
    Drawable mDeleteIcon;
    ColorDrawable background;
    Country countryDeleted;
    int mPositionDeleted;
    public SwipeHelper(int dragDirs, int swipeDirs, CountryAdapter countryAdapter) {
        super(dragDirs, swipeDirs);
    }

    public SwipeHelper(CountryAdapter mCountryAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT );
        this.mCountryAdapter = mCountryAdapter;
        //Định nghĩa icon khi vuốt qua
        mDeleteIcon = ContextCompat.getDrawable(mCountryAdapter.context, R.drawable.ic_delete_black_24dp);
        background = new ColorDrawable(Color.RED);
    }

    //Chỉ định các hướng drag và swipe
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(ItemTouchHelper.DOWN | ItemTouchHelper.UP,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
    }

    @Override
    //Cho phép swipe hay không
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    //Cho phép drag hay không
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    //Khi item di chuyển
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int position_dragged = viewHolder.getAdapterPosition();
        int position_target = target.getAdapterPosition();
        //Swap
        if (position_dragged < position_target) {
            for (int i = position_dragged; i < position_target; i++) {
                Collections.swap(mCountryAdapter.countries, i, i + 1);
            }
        } else {
            for (int i = position_dragged; i > position_target; i--) {
                Collections.swap(mCountryAdapter.countries, i, i - 1);
            }
        }
        mCountryAdapter.notifyItemMoved(position_dragged,position_target);
        for (int j = 0; j < 5; j++) {
            Log.d("onMove", mCountryAdapter.countries.get(j).getmCountryName());
        }
        return true;
    }

    @Override
    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
        mPositionDeleted = viewHolder.getAdapterPosition();
        countryDeleted = mCountryAdapter.removeItem(mPositionDeleted);
        Snackbar snackbar = Snackbar.make(viewHolder.itemView, "Item " + (direction == ItemTouchHelper.RIGHT ? "deleted" : "archived") + ".", Snackbar.LENGTH_LONG);
        snackbar.setAction(android.R.string.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mCountryAdapter.addItem(countryDeleted, mPositionDeleted);
                } catch(Exception e) {
                    Log.e("MainActivity", e.getMessage());
                }
            }
        });
        snackbar.show();
    }

    //Gọi nhiều lần khi thực hiện thao tác vuốt qua
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(ContextCompat.getColor(mCountryAdapter.context, R.color.recycler_view_item_swipe_left_background))
                .addSwipeLeftActionIcon(R.drawable.ic_archive_white_24dp)
                .addSwipeRightBackgroundColor(ContextCompat.getColor(mCountryAdapter.context, R.color.recycler_view_item_swipe_right_background))
                .addSwipeRightActionIcon(R.drawable.ic_delete_white_24dp)
                .addSwipeRightLabel(mCountryAdapter.context.getString(R.string.action_delete))
                .setSwipeRightLabelColor(Color.WHITE)
                .addSwipeLeftLabel(mCountryAdapter.context.getString(R.string.action_archive))
                .setSwipeLeftLabelColor(Color.WHITE)
                .create()
                .decorate();
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}

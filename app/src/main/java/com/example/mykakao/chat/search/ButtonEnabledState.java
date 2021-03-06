package com.example.mykakao.chat.search;

import android.util.Log;
import android.widget.Button;

import com.annimon.stream.function.BiConsumer;

/**
 * Created by 재화 on 2016-04-30.
 */
public class ButtonEnabledState {

    Button up, down;
    public static final int DEFAULT_POSITION = -1;
    public static final int DEFAULT_ITEM_POSITION_SIZE = -1;

    public ButtonEnabledState(Button down, Button up) {
        this.down = down;
        this.up = up;
    }

    BiConsumer<Button, Button> middlePositionState = (up, down) -> {
        up.setEnabled(true);
        down.setEnabled(true);
        Log.e("state", "middlePositionState");
    };

    BiConsumer<Button, Button> zeroPositionState = (up, down) -> {
        up.setEnabled(false);
        down.setEnabled(true);
        Log.e("state", "zeroPositionState");
    };

    BiConsumer<Button, Button> fullPositionState = (up, down) -> {
        up.setEnabled(true);
        down.setEnabled(false);
        Log.e("state", "fullPositionState");
    };

    BiConsumer<Button, Button> emptyItemPositionsState = (up, down) -> {
        up.setEnabled(false);
        down.setEnabled(false);
        Log.e("state", "emptyItemPositionsState");
    };

    public void setEnabledState(int position, int itemPositionsSize) {
        Log.e("po", Integer.toString(itemPositionsSize) + " / " + Integer.toString(position));
        if (itemPositionsSize == 0 || (position == DEFAULT_POSITION && itemPositionsSize == DEFAULT_ITEM_POSITION_SIZE)) {
            emptyItemPositionsState.accept(up, down);
        } else if (position < 0) {
            zeroPositionState.accept(up, down);
        } else if (position >= 0 && position < itemPositionsSize - 1) {
            middlePositionState.accept(up, down);
        } else if (position == itemPositionsSize -1) {
            fullPositionState.accept(up, down);
        }
    }

}

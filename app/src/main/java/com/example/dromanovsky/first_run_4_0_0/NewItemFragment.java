package com.example.dromanovsky.first_run_4_0_0;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by dromanovsky on 22.3.18.
 */

public class NewItemFragment extends Fragment {

    private OnNewItemAddedListener onNewItemAddedListener;

    public interface OnNewItemAddedListener {
        public void onNewItemAdded(String newItem);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SaveInstanceState) {
        View view = inflater.inflate(R.layout.new_item_fragment_unused, container, false);

        final EditText toDoListInput = (EditText)view.findViewById(R.id.toDoListInput);

        // Key handling
        toDoListInput.setOnKeyListener(
                new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                            if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)){
                                String new_item = toDoListInput.getText().toString();
                                onNewItemAddedListener.onNewItemAdded(new_item);
                                toDoListInput.setText("");
                                return true;
                            }
                        }
                        return false;
                    }
                }
        );

        return view;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            onNewItemAddedListener = (OnNewItemAddedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnNewItemAddedListener");
        }

    }
}

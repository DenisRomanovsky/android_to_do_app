package com.example.dromanovsky.first_run_4_0_0;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewItemFragment.OnNewItemAddedListener {

    private ArrayList<String> todoItems;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Fragments
        FragmentManager fragmentManager = getFragmentManager();
        ToDoListFragment toDoListFragment = (ToDoListFragment)fragmentManager.findFragmentById(R.id.ToDoListFragment);

        // Data storing
        todoItems = new ArrayList<String>();
        int fragmentID = R.layout.new_item_fragment;
        arrayAdapter = new ArrayAdapter<String>(this, fragmentID, todoItems);

        toDoListFragment.setListAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNewItemAdded(String newItem) {
        todoItems.add(newItem);
        arrayAdapter.notifyDataSetChanged();
    }
}

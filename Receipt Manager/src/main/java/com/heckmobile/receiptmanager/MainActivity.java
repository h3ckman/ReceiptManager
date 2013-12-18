package com.heckmobile.receiptmanager;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final String ARG_SECTION_NUMBER = "section_number";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    /**
     * When a menu item is selected, load that fragment. Calls {@link #selectFragment(int position)}.
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        selectFragment(position);
    }

    public void selectFragment(int position) {

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();

        switch (position+1) {
            case 1:
                /*
                args.putInt(ARG_SECTION_NUMBER, position+1);
                fragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
                */

                //ExpensesFragment frag = ExpensesFragment.newInstance(position+1);

                ReceiptsFragment receiptsFrag = new ReceiptsFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, receiptsFrag)
                        .commit();

                break;
            case 2:
                ExpensesFragment expensesFrag = new ExpensesFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, expensesFrag)
                        .commit();
                break;
            case 3:
                args.putInt(ARG_SECTION_NUMBER, position+1);
                fragment.setArguments(args);

                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                /**
                 * Used to set the action bar text for this fragment
                 */
                // mTitle = getString(R.string.title_receipts);

                mTitle = getTitle();
                Toast.makeText(this, "Receipts", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                mTitle = getString(R.string.title_expenses);
                Toast.makeText(this, "Expenses", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                mTitle = getString(R.string.title_settings);
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

package com.heckmobile.receiptmanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link ReceiptsFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ReceiptsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    Button dbButton;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sectionNum Parameter 1.
     * @return A new instance of fragment ReceiptsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReceiptsFragment newInstance(int sectionNum) {
        ReceiptsFragment fragment = new ReceiptsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, sectionNum);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public ReceiptsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_receipts, container, false);
        dbButton = (Button) rootView.findViewById(R.id.dbBtn);
        dbButton.setOnClickListener(dbButtonClick);

        // Inflate the layout for this fragment
        return rootView;
    }

    View.OnClickListener dbButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Execute async task
            AddData addData = new AddData(v.getContext());
            addData.execute();

            Toast.makeText(v.getContext(), "Adding test info to database...", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        /*
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }


    class AddData extends AsyncTask {

        Context context;

        public AddData(Context c) {
            super();
            context = c;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object... args) {
            ReceiptDbHelper dbHelper = new ReceiptDbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ReceiptEntry.COLUMN_NAME_TITLE, "Test");
            values.put(ReceiptEntry.COLUMN_NAME_DESCRIPTION, "This is just a test");

            // Insert the new row, returning the primary key value of the new row
            long newRowId;
            newRowId = db.insert(ReceiptEntry.TABLE_NAME, null, values);
            Log.d("Receipt Manager", "New item id: "+newRowId);
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }

    }
}

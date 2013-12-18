package com.heckmobile.receiptmanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.heckmobile.receiptmanager.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class ReceiptsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView receiptsListView;
    private static List<Receipt> receiptsList;
    private static Receipt testReceipt;

    // TODO: Rename and change types of parameters
    public static ReceiptsFragment newInstance(String param1, String param2) {
        ReceiptsFragment fragment = new ReceiptsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ReceiptsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        testReceipt = new Receipt(0,"Kroger","$34.85","Grocery");

        ReadData readData = new ReadData(getActivity());
        readData.execute();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_receipts, container, false);

        receiptsListView = (ListView)rootView.findViewById(R.id.receipts_listview);

        return rootView;
    }

    OnItemClickListener receiptItemClick = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Execute async task
            String x = receiptsList.get(position).store;
            Toast.makeText(view.getContext(), "You clicked "+x+"", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    class ReadData extends AsyncTask {

        Context context;
        Cursor cursor;

        public ReadData(Context c) {
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
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    Receipt._ID,
                    Receipt.COLUMN_NAME_STORE,
                    Receipt.COLUMN_NAME_PRICE,
                    Receipt.COLUMN_NAME_CATEGORY,
                    Receipt.COLUMN_NAME_DESCRIPTION
            };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    Receipt.COLUMN_NAME_STORE + " ASC";

            cursor = db.query(
                    Receipt.TABLE_NAME,                     // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            //cursor = db.rawQuery("select * from table", null);
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            receiptsList = new ArrayList<Receipt>();

            /**
             * If the database had results, add them to the list of receipts
             */
            if(!(cursor.getCount() == 0)) {
                while(cursor.moveToNext()) {
                    Receipt receipt = new Receipt();
                    receipt.id = (int)cursor.getLong(
                            cursor.getColumnIndexOrThrow(Receipt._ID)
                    );
                    receipt.store = cursor.getString(
                            cursor.getColumnIndexOrThrow(Receipt.COLUMN_NAME_STORE)
                    );
                    receipt.price = cursor.getString(
                            cursor.getColumnIndexOrThrow(Receipt.COLUMN_NAME_PRICE)
                    );
                    receipt.category = cursor.getString(
                            cursor.getColumnIndexOrThrow(Receipt.COLUMN_NAME_CATEGORY)
                    );
                    receiptsList.add(receipt);
                    Log.d("Receipt Manager", "ID: "+receipt.id);
                    Log.d("Receipt Manager", "Store: "+receipt.store);
                }
            }
            else {
                receiptsList.add(testReceipt);
                Log.d("Receipt Manager", "Database is empty");
            }

            /**
             * Set custom array list adapter with database receipt entries
             */
            ReceiptAdapter adapter = new ReceiptAdapter(receiptsList, getActivity());
            receiptsListView.setAdapter(adapter);
            receiptsListView.setOnItemClickListener(receiptItemClick);
        }

    }
}

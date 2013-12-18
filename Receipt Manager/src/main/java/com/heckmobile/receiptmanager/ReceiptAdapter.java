package com.heckmobile.receiptmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by h3ckman on 12/18/13.
 */
public class ReceiptAdapter extends ArrayAdapter<Receipt> {

    private List<Receipt> receiptList;
    private Context context;

    public ReceiptAdapter(List<Receipt> receiptList, Context ctx) {
        super(ctx, R.layout.receipt_row, receiptList);
        this.receiptList = receiptList;
        this.context = ctx;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.receipt_row, parent, false);
        TextView store = (TextView) rowView.findViewById(R.id.receipt_store);
        TextView price = (TextView) rowView.findViewById(R.id.receipt_price);
        TextView category = (TextView) rowView.findViewById(R.id.receipt_category);
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);

        Receipt receipt = receiptList.get(position);
        store.setText(receipt.store);
        price.setText(receipt.price);
        category.setText(receipt.category);

        //return super.getView(position, convertView, parent);
        return rowView;
    }
}

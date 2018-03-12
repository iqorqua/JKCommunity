package com.example.igorqua.jkcommunity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.igorqua.jkcommunity.CustomElements.PaymentAdapter;
import com.example.igorqua.jkcommunity.CustomElements.PaymentItem;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class Payment extends Fragment {


    public Payment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_payment, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SimpleAdapter(recyclerView, new String[]{"Комуналка", "Магазин", "Салон"}));
        // Inflate the layout for this fragment
        /*View result = inflater.inflate(R.layout.fragment_payment, container, false);
        ArrayList<PaymentItem> paymentItems = new ArrayList<>();
        ListView listView = (ListView) result.findViewById(R.id.payment_list);
        listView.setAdapter(new PaymentAdapter(result.getContext(), paymentItems));
        paymentItems.add(new PaymentItem("Платеж 1", "Описание платежа 1"));
        paymentItems.add(new PaymentItem("Платеж 2", "Описание платежа 2"));
        paymentItems.add(new PaymentItem("Платеж 3", "Описание платежа 3"));
        paymentItems.add(new PaymentItem("Платеж 4", "Описание платежа 4"));*/
        return rootView;
    }

    private class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
        private static final int UNSELECTED = -1;

        String [] elemets;

        private RecyclerView recyclerView;
        private int selectedItem = UNSELECTED;

        public SimpleAdapter(RecyclerView recyclerView, String [] items) {
            this.recyclerView = recyclerView;
            elemets = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.expandable_payment_item, parent, false);
            ListView  listView = (ListView) itemView.findViewById(R.id.elenentsInPaymentList);
            ArrayList<PaymentItem> elem_array = new ArrayList();
            elem_array.add(new PaymentItem("payment_name", "description", 50.5));
            elem_array.add(new PaymentItem("payment_name", "description", 2550.5));
            elem_array.add(new PaymentItem("payment_name", "description", 342550.5));
            elem_array.add(new PaymentItem("payment_name", "description", 50.5));
            elem_array.add(new PaymentItem("payment_name", "description", 530.5));
            elem_array.add(new PaymentItem("payment_name", "description", 550.5));
            elem_array.add(new PaymentItem("payment_name", "description", 250.5));
            double total_price = 0;
            for (PaymentItem p:elem_array ) {
                total_price+=p.price;
            }
            ((TextView)itemView.findViewById(R.id.total_price_tw)).setText("Всего: " + total_price + "грн.");
            PaymentAdapter listAdapter = new PaymentAdapter(itemView.getContext(), elem_array);
            listView.setAdapter(listAdapter);

            listView.setOnTouchListener(new View.OnTouchListener() {
                // Setting on Touch Listener for handling the touch inside ScrollView
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // Disallow the touch request for parent scroll on touch of child view
                    v.getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });

            setListViewHeightBasedOnChildren(listView);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return elemets.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
            private ExpandableLayout expandableLayout;
            private TextView expandButton;
            private int position;

            public ViewHolder(View itemView) {
                super(itemView);

                expandableLayout = (ExpandableLayout) itemView.findViewById(R.id.expandable_layout);
                expandableLayout.setInterpolator(new OvershootInterpolator());
                expandableLayout.setOnExpansionUpdateListener(this);
                expandButton = (TextView) itemView.findViewById(R.id.expand_button);

                expandButton.setOnClickListener(this);
            }

            public void bind(int position) {
                this.position = position;

                expandButton.setText(elemets[position]);

                expandButton.setSelected(false);
                expandableLayout.collapse(false);
            }

            @Override
            public void onClick(View view) {
                ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
                if (holder != null) {
                    holder.expandButton.setSelected(false);
                    holder.expandableLayout.collapse();
                }

                if (position == selectedItem) {
                    selectedItem = UNSELECTED;
                } else {
                    expandButton.setSelected(true);
                    expandableLayout.expand();
                    selectedItem = position;
                }
            }

            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                Log.d("ExpandableLayout", "State: " + state);
                recyclerView.smoothScrollToPosition(getAdapterPosition());
            }
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, RecyclerView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}

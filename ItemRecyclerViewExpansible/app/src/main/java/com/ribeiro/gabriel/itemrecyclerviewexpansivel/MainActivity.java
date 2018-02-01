package com.ribeiro.gabriel.itemrecyclerviewexpansivel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ribeiro.gabriel.expansiblerecyclerviewitem.adapter.BindExpansibleViewHolderHandler;
import com.ribeiro.gabriel.expansiblerecyclerviewitem.adapter.ExpansibleItemRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );

        List<TestClass> listItems = new ArrayList<>();

        for (int i = 0; i < 1000; i++)
            listItems.add(new TestClass("test"+i, "body"+i));

        //handler which will fill the items
        BindExpansibleViewHolderHandler bindExpansibleViewHolderHandler =
                new BindExpansibleViewHolderHandler() {
                    @Override
                    public void bindViewHolderHandler(
                            ExpansibleItemRecyclerViewAdapter.ExpansibleItemRecyclerViewViewHolder holder,
                            Object object,
                            int position) {

                        //cast your item
                        TestClass item = (TestClass) object;

                        //get the layout fixed
                        View fixed = holder.getFixedFrame();

                        //get the expansible layout
                        View expansible = holder.getExpansibleFrame();

                        //then fill with your class and layout

                        TextView title = fixed.findViewById(R.id.main);
                        title.setText(item.getTitle());


                        TextView body = expansible.findViewById(R.id.body);
                        body.setText(item.getBody());
                    }
                };

        recyclerView.setAdapter(ExpansibleItemRecyclerViewAdapter.newInstance(
                this, //context
                listItems, //list of items
                R.layout.fixed_layout, // layout fixed
                R.layout.expansible_layout, // dynamic layout
                bindExpansibleViewHolderHandler, // handler that will fill the view items
        true // set if the dynamic layout will start visible or not
        ));
    }
}

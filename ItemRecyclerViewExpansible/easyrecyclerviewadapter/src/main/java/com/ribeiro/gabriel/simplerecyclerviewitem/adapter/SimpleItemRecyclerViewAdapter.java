package com.ribeiro.gabriel.simplerecyclerviewitem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * This Class is a Simple Item Adapter from a RecyclerView.
 *
 * @author Gabriel Ribeiro Silva \r\n email: gabriel.silva3409@gmail.com \r\n GitHub:ribGSilva
 */
public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter {

    /**
     * Context form application
     */
    private Context mContext;
    /**
     * List of items to adapter bind
     */
    private List<?> mSimpleItems;
    /**
     * Layout that will be used by items
     */
    private int mItemLayout;
    /**
     * Binder from holders
     */
    private BindSimpleViewHolderHandler mBindSimpleViewHolderHandler;


    /**
     * Create a new instance of ExpansibleItemRecyclerViewAdapter
     *
     * @param context                         Context from application
     * @param listItems                       List of items
     * @param itemLayout                      Item layout (Ex: R.layout.item_layout)
     * @param bindSimpleViewHolderHandler     Method which will bind the views
     * @return Return instance of SimpleItemRecyclerViewAdapter
     */
    public static SimpleItemRecyclerViewAdapter newInstance(Context context,
                                                                List<?> listItems,
                                                                int itemLayout,
                                                                BindSimpleViewHolderHandler bindSimpleViewHolderHandler) {
        return new SimpleItemRecyclerViewAdapter(
                context, listItems, itemLayout, bindSimpleViewHolderHandler
        );

    }

    /**
     * Constructor from instance
     * Build the instance
     *
     * @param context                         Context from application
     * @param listItems                       List of items
     * @param itemLayout                      Item layout (Ex: R.layout.item_layout)
     * @param bindSimpleViewHolderHandler     Method which will bind the views
     */
    private SimpleItemRecyclerViewAdapter(Context context,
                                              List<?> listItems,
                                              int itemLayout,
                                              BindSimpleViewHolderHandler bindSimpleViewHolderHandler) {
        mContext = context;
        mSimpleItems = listItems;
        mItemLayout = itemLayout;
        mBindSimpleViewHolderHandler = bindSimpleViewHolderHandler;
    }

    /**
     * Create the view Holder
     * Initiate the main layout and create the view holder
     *
     * @param parent   The view group
     * @param viewType The view type
     * @return RecyclerView.ViewHolder  Return a ViewHolder customized and internal
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayout = LayoutInflater.from(mContext).inflate(mItemLayout, parent, false);

        return new SimpleItemRecyclerViewViewHolder(itemLayout);
    }

    /**
     * Bind the ViewHolders with the internal list data.
     * Uses the bindViewHolderHandler received on constructor to bind the view
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleItemRecyclerViewViewHolder simpleItemRecyclerViewViewHolder =
                (SimpleItemRecyclerViewViewHolder) holder;

        mBindSimpleViewHolderHandler.bindViewHolderHandler(
                simpleItemRecyclerViewViewHolder,
                mSimpleItems.get(position),
                position
        );
    }

    /**
     * Return the size of the internal list
     *
     * @return Size of the internal list
     */
    @Override
    public int getItemCount() {
        return mSimpleItems.size();
    }

    /**
     * The simple ViewHolder that hold the view items from recycler
     *
     * @author Gabriel Ribeiro Silva \r\n email: gabriel.silva3409@gmail.com \r\n GitHub:ribGSilva
     */
    public class SimpleItemRecyclerViewViewHolder extends RecyclerView.ViewHolder{

        /**
         * The root layout from all the item view
         */
        private View rootLayout;

        /**
         * Constructor from instance
         *
         * @param expansibleLayout The root layout from item
         */
        private SimpleItemRecyclerViewViewHolder(View expansibleLayout) {
            super(expansibleLayout);

            rootLayout = expansibleLayout;
        }

        /**
         * Return the root layout from view holder
         *
         * @return Root layout from view holder
         */
        public View getRootLayout() {
            return rootLayout;
        }
    }
}

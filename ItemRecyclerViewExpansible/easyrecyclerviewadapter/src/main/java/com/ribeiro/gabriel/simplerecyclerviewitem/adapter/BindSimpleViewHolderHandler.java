package com.ribeiro.gabriel.simplerecyclerviewitem.adapter;

/**
 * Binder from SimpleItemRecyclerViewViewHolder
 * This contract is a view binder to SimpleItemRecyclerViewViewHolder, which will put the data
 * from current index from internal list on the view
 * @author Gabriel Ribeiro Silva \r\n email: gabriel.silva3409@gmail.com \r\n GitHub:ribGSilva
 */
public interface BindSimpleViewHolderHandler {
    void bindViewHolderHandler(SimpleItemRecyclerViewAdapter.SimpleItemRecyclerViewViewHolder holder, Object object, int position);
}

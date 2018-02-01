package com.ribeiro.gabriel.expansiblerecyclerviewitem.adapter;

/**
 * Binder from ExpansibleItemRecyclerViewViewHolder
 * This contract is a view binder to ExpansibleItemRecyclerViewViewHolder, which will put the data
 * from current index from internal list on the view
 * @author Gabriel Ribeiro Silva \r\n email: gabriel.silva3409@gmail.com \r\n GitHub:ribGSilva
 */
public interface BindExpansibleViewHolderHandler {
    void bindViewHolderHandler(ExpansibleItemRecyclerViewAdapter.ExpansibleItemRecyclerViewViewHolder holder, Object object, int position);
}

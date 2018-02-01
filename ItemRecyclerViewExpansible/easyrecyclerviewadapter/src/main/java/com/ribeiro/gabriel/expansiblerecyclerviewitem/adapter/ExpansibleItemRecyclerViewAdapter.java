package com.ribeiro.gabriel.expansiblerecyclerviewitem.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.List;

/**
 * This Class is a Expansible Item Adapter from a RecyclerView.
 *
 * @author Gabriel Ribeiro Silva \r\n email: gabriel.silva3409@gmail.com \r\n GitHub:ribGSilva
 */
public class ExpansibleItemRecyclerViewAdapter extends RecyclerView.Adapter {

    /**
     * Context form application
     */
    private Context mContext;
    /**
     * List of items to adapter bind
     */
    private List<?> mExpansibleItems;
    /**
     * Layout that will be fixed
     */
    private int mFixedLayout;
    /**
     * Layout that will be hidden and shown
     */
    private int mExpansibleLayout;
    /**
     * Binder from holders
     */
    private BindExpansibleViewHolderHandler mBindExpansibleViewHolderHandler;
    /**
     * Set if the dynamic layout will start shown or hidden
     */
    private boolean mStartHidden;
    /**
     * Id from fixed frame
     */
    private static final int ID_FIXED_FRAME = 1;
    /**
     * Id from expansible frame
     */
    private static final int ID_EXPANSIBLE_FRAME = 2;


    /**
     * Create a new instance of ExpansibleItemRecyclerViewAdapter
     *
     * @param context                         Context from application
     * @param listItems                       List of items
     * @param fixedLayout                     Fixed layout of item (Ex: R.layout.fixed_layout)
     * @param expansibleLayout                Fixed layout of item (Ex: R.layout.expansible_layout)
     * @param bindExpansibleViewHolderHandler Method which will bind the views
     * @param startHidden                     Set the initial visibility from dynamic layout
     * @return Return instance of ExpansibleItemRecyclerViewAdapter
     */
    public static ExpansibleItemRecyclerViewAdapter newInstance(Context context,
                                                                List<?> listItems,
                                                                int fixedLayout,
                                                                int expansibleLayout,
                                                                BindExpansibleViewHolderHandler bindExpansibleViewHolderHandler,
                                                                boolean startHidden) {
        return new ExpansibleItemRecyclerViewAdapter(
                context, listItems, fixedLayout, expansibleLayout, bindExpansibleViewHolderHandler, startHidden
        );

    }

    /**
     * Constructor from instance
     * Build the instance
     *
     * @param context                         Context from application
     * @param listItems                       List of items
     * @param fixedLayout                     Fixed layout of item (Ex: R.layout.fixed_layout)
     * @param expansibleLayout                Fixed layout of item (Ex: R.layout.expansible_layout)
     * @param bindExpansibleViewHolderHandler Method which will bind the views
     * @param startHidden                     Set the initial visibility from dynamic layout
     */
    private ExpansibleItemRecyclerViewAdapter(Context context,
                                              List<?> listItems,
                                              int fixedLayout,
                                              int expansibleLayout,
                                              BindExpansibleViewHolderHandler bindExpansibleViewHolderHandler,
                                              boolean startHidden) {
        mContext = context;
        mExpansibleItems = listItems;
        mFixedLayout = fixedLayout;
        mExpansibleLayout = expansibleLayout;
        mBindExpansibleViewHolderHandler = bindExpansibleViewHolderHandler;
        mStartHidden = startHidden;
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

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        ViewGroup.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        linearLayout.setLayoutParams(layoutParams);

        FrameLayout frameFixed = new FrameLayout(mContext);
        frameFixed.setLayoutParams(layoutParams);
        frameFixed.setId(ID_FIXED_FRAME);

        linearLayout.addView(frameFixed);

        FrameLayout frameExpansible = new FrameLayout(mContext);
        frameExpansible.setLayoutParams(layoutParams);
        frameExpansible.setId(ID_EXPANSIBLE_FRAME);

        linearLayout.addView(frameExpansible);

        return new ExpansibleItemRecyclerViewViewHolder(linearLayout, parent);
    }

    /**
     * Bind the ViewHolders with the internal list data.
     * Uses the bindViewHolderHandler received on constructor to bind the view
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExpansibleItemRecyclerViewViewHolder expansibleItemRecyclerViewViewHolder =
                (ExpansibleItemRecyclerViewViewHolder) holder;

        if (mStartHidden) {
            if (!expansibleItemRecyclerViewViewHolder.isHidden())
                expansibleItemRecyclerViewViewHolder.getExpansibleFrame().setVisibility(View.GONE);
        } else {
            if (expansibleItemRecyclerViewViewHolder.isHidden())
                expansibleItemRecyclerViewViewHolder.getExpansibleFrame().setVisibility(View.VISIBLE);
        }

        mBindExpansibleViewHolderHandler.bindViewHolderHandler(
                expansibleItemRecyclerViewViewHolder,
                mExpansibleItems.get(position),
                position
        );
    }

    /**
     * Return if the recycler is starting hidden
     *
     * @return If the recycler is starting hidden
     */
    public boolean isStartHidden() {
        return mStartHidden;
    }

    /**
     * Set if the recycler is starting hidden
     *
     * @param startHidden If the recycler is starting hidden
     */
    public void setStartHidden(boolean startHidden) {
        this.mStartHidden = startHidden;
    }

    /**
     * Return the size of the internal list
     *
     * @return Size of the internal list
     */
    @Override
    public int getItemCount() {
        return mExpansibleItems.size();
    }

    /**
     * The custom ViewHolder that hold the view items from recycler
     *
     * @author Gabriel Ribeiro Silva \r\n email: gabriel.silva3409@gmail.com \r\n GitHub:ribGSilva
     */
    public class ExpansibleItemRecyclerViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * The root layout from all the item view
         */
        private View rootLayout;
        /**
         * The fixed layout
         */
        private View fixedFrame;
        /**
         * The expansible layout
         */
        private View expansibleFrame;

        /**
         * Constructor from instance
         *
         * @param expansibleLayout The root layout from item
         * @param parent           The view group
         */
        private ExpansibleItemRecyclerViewViewHolder(View expansibleLayout, ViewGroup parent) {
            super(expansibleLayout);

            rootLayout = expansibleLayout;

            expansibleLayout.setOnClickListener(this);
            fixedFrame = expansibleLayout.findViewById(ID_FIXED_FRAME);
            expansibleFrame = expansibleLayout.findViewById(ID_EXPANSIBLE_FRAME);

            View fixed = LayoutInflater.from(mContext).inflate(mFixedLayout, parent, false);
            ((FrameLayout) fixedFrame).addView(fixed);
            fixedFrame = fixed;

            View expansible = LayoutInflater.from(mContext).inflate(mExpansibleLayout, parent, false);
            ((FrameLayout) expansibleFrame).addView(expansible);
            expansibleFrame = expansible;

            if (mStartHidden) {
                hideLayout();
            }
        }

        /**
         * Return the root layout from view holder
         *
         * @return Root layout from view holder
         */
        public View getRootLayout() {
            return fixedFrame;
        }

        /**
         * Return the fixed frame from view item
         *
         * @return Fixed frame from view item
         */
        public View getFixedFrame() {
            return fixedFrame;
        }

        /**
         * Return the expansible frame from view item
         *
         * @return Expansible frame from view item
         */
        public View getExpansibleFrame() {
            return expansibleFrame;
        }

        /**
         * Return true if the expansible frame is hidden
         *
         * @return If the expansible frame is hidden
         */
        public boolean isHidden() {
            return expansibleFrame.getVisibility() == View.GONE;
        }

        /**
         * Hide or show the dynamic frame
         */
        @Override
        public void onClick(View v) {
            if (isHidden())
                expandLayout();
            else
                hideLayout();
        }

        /**
         * Expand the layout
         */
        private void expandLayout() {
            expansibleFrame.animate()
                    .translationY(0)
                    .alpha(1.0f)
                    .setDuration(1000)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            expansibleFrame.setVisibility(View.VISIBLE);
                        }
                    });
        }

        /**
         * Hide the layout
         */
        private void hideLayout() {
            expansibleFrame.animate()
                    .translationY(-expansibleFrame.getHeight())
                    .alpha(0.0f)
                    .setDuration(1000)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            expansibleFrame.setVisibility(View.GONE);
                        }
                    });
        }
    }
}

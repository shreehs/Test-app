package com.intplus.shoppingspace.adapters;

import android.app.Activity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.intplus.shoppingspace.R;
import com.intplus.shoppingspace.model.options.Options;

import java.util.ArrayList;

/**
 * Created by harshas on 3/25/2017.
 */

public class OptionsListAdapter extends RecyclerView.Adapter<OptionsListAdapter.OptionViewHolder> {

    private Activity activity;
    private ArrayList<Options> allOptions;
    private OptionsListAdapter.OnItemClickListner onItemClickListner;

    public OptionsListAdapter(Activity activity, ArrayList<Options> options){
        this.activity = activity;
        this.allOptions = options;
        this.onItemClickListner = (OnItemClickListner) this.activity;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.option_list_item,parent, false);
        OptionsListAdapter.OptionViewHolder optionViewHolder = new OptionsListAdapter.OptionViewHolder(view);
        return optionViewHolder;
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        String optTitle = this.allOptions.get(position).getTitle();
        holder.tvOptTitle.setText(optTitle);
        int optId = this.allOptions.get(position).getOid();
        if ((optId == 1) || (optId == 2)) {
            holder.cbOptFeatureState.setChecked(this.allOptions.get(position).isCbEnable());
        }
        // hide CheckBox for optid 3,4
        if ((optId == 3) || (optId == 4)) {
            holder.cbOptFeatureState.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.allOptions.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatTextView tvOptTitle;
        private AppCompatCheckBox cbOptFeatureState;

        public OptionViewHolder(View itemView) {
            super(itemView);
            CardView cvOptListItem = (CardView) itemView.findViewById(R.id.cvOptListItem);
            tvOptTitle = (AppCompatTextView) itemView.findViewById(R.id.tvOptTitle);
            cbOptFeatureState = (AppCompatCheckBox) itemView.findViewById(R.id.cbOptFeatureState);

            cvOptListItem.setOnClickListener(this);
            cbOptFeatureState.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            int optId;
            switch (v.getId()){
                case R.id.cvOptListItem:
                    optId = allOptions.get(position).getOid();
                    if ((optId == 3) || (optId == 4)){
                        // Rate or like
                        onItemClickListner.onOptLoveApp(optId);
                    }
                    break;
                case R.id.cbOptFeatureState:
                    // optId == 1 or 2
                    optId = allOptions.get(position).getOid();
                    boolean newFeatureState = !(allOptions.get(position)).isCbEnable();
                    onItemClickListner.onOptEnableFeature(optId, newFeatureState);
                    allOptions.get(position).setCbEnable(newFeatureState);
                    break;
            }

        }
    }

    public interface OnItemClickListner{
        public void onOptLoveApp(int optId);
        public void onOptEnableFeature(int optId, boolean featureState);
    }
}

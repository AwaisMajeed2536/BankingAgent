package com.example.saad_kamaal.bankingagent.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.saad_kamaal.bankingagent.activities.R;
import com.example.saad_kamaal.bankingagent.helper.UtilHelper;
import com.example.saad_kamaal.bankingagent.models.MessageModel;

import java.util.List;

/**
 * Created by saad_kamaal on 10/3/2017.
 */

public class ChatterBoxAdapter extends RecyclerView.Adapter<ChatterBoxAdapter.CBViewHolder> {

    private Context context;
    private List<MessageModel> dataList;
    private String userId;

    public ChatterBoxAdapter(Context context, List<MessageModel> dataList) {
        this.context = context;
        this.dataList = dataList;
        userId = UtilHelper.getUserId(context);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public CBViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.li_incomming_message, parent, false);
        return new CBViewHolder(view);
    }

    public void notifyDataSetChanged(MessageModel message) {
        this.dataList.add(message);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CBViewHolder holder, int position) {
        holder.setData(dataList.get(position));
    }

    class CBViewHolder extends RecyclerView.ViewHolder {
        TextView messageTv;

        public CBViewHolder(View itemView) {
            super(itemView);
            messageTv = (TextView) itemView.findViewById(R.id.message_tv);
        }

        public void setData(MessageModel message) {
            LinearLayout.LayoutParams params;
            messageTv.setText(message.getMessage());
            if (message.getUserId().equals(userId)) {
                params = (LinearLayout.LayoutParams) messageTv.getLayoutParams();
                params.gravity = Gravity.RIGHT;
                messageTv.setLayoutParams(params);
                messageTv.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_message_li_out));
            } else {
                params = (LinearLayout.LayoutParams) messageTv.getLayoutParams();
                params.gravity = Gravity.LEFT;
                messageTv.setLayoutParams(params);
                messageTv.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_message_li_in));
            }
        }
    }

    public enum MESSAGE_TYPE {
        INCOMMING, SENT
    }
}












package com.hx.imchat.home.chatlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hx.imchat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Hx
 * @date: 2022年04月04日 17:38
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private final List<ChatInfo> list;
    private Context mContext;
    private EvaluationListClickCallback clickCallback;

    public ChatListAdapter(ArrayList<ChatInfo> list) {
        this.list = list;
    }

    public void setClickCallback(EvaluationListClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chatlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ChatInfo info = list.get(position);
        holder.title.setText(info.getTitle());
        holder.describe.setText("描述: " + info.getDescribe());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface EvaluationListClickCallback {
        void onItemClick(View view, int position, String data);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView describe;
        CardView root;

        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.tv_item_title);
            describe = view.findViewById(R.id.tv_item_content);
            root = view.findViewById(R.id.cv_item_option_root);
        }
    }
}

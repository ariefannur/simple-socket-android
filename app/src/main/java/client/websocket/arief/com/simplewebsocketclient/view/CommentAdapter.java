package client.websocket.arief.com.simplewebsocketclient.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import client.websocket.arief.com.simplewebsocketclient.R;
import client.websocket.arief.com.simplewebsocketclient.config.ImageUtils;
import client.websocket.arief.com.simplewebsocketclient.object.Comment;

/**
 * Created by arief pc on 6/4/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    private List<Comment.Data> lsData;

    public CommentAdapter(List<Comment.Data> lsData){
        this.lsData = lsData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, null, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment.Data data = lsData.get(position);
        holder.txtComment.setText(data.message);
        holder.txtUser.setText(data.createdName);
        ImageUtils.displayImage(holder.imgUser.getContext(), holder.imgUser, data.creatorAvatarImageUrl);
    }

    @Override
    public int getItemCount() {
        return lsData.size();
    }

    public void update(List<Comment.Data> commentPost) {
        this.lsData = commentPost;
        notifyDataSetChanged();
    }

    public void addComment(Comment.Data commentPost) {
        lsData.add(commentPost);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txtComment)
        TextView txtComment;

        @BindView(R.id.txtUser)
        TextView txtUser;

        @BindView(R.id.imgUser)
        ImageView imgUser;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

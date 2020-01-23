package com.dhananjay.democreateaccount.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhananjay.democreateaccount.R;
import com.dhananjay.democreateaccount.models.Dir;
import com.dhananjay.democreateaccount.models.TreeNode;

public class DirectoryNodeBinder extends
    TreeViewBinder<DirectoryNodeBinder.ViewHolder> {
    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(ViewHolder holder, int position, TreeNode node) {

        if (node.isRoot()) {
            holder.itemView.setBackground(
                holder.itemView.getContext().getResources()
                    .getDrawable(R.drawable.parent_node_background, null));
        }

        holder.ivArrow.setRotation(0);
        holder.ivArrow.setImageResource(
            R.drawable.ic_keyboard_arrow_right_orange_24dp);
        int rotateDegree = node.isExpand() ? 90 : 0;
        holder.ivArrow.setRotation(rotateDegree);
        Dir dirNode = (Dir) node.getContent();
        holder.tvName.setText(dirNode.dirName);
        if (node.isLeaf())
            holder.ivArrow.setVisibility(View.INVISIBLE);
        else holder.ivArrow.setVisibility(View.VISIBLE);

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_dir;
    }

    public static class ViewHolder extends TreeViewBinder.ViewHolder {
        private ImageView ivArrow;
        private TextView tvName;

        public ViewHolder(View rootView) {
            super(rootView);
            this.ivArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
        }

        public ImageView getIvArrow() {
            return ivArrow;
        }

        public TextView getTvName() {
            return tvName;
        }
    }
}

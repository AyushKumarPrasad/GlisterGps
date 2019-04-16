package limited.pvt.global.stachi.glisteruser.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtPhone , txtTime , txtStatus , txtArea;

    public ItemClickListener itemClickListener;

    public ProfileViewHolder(View itemView) {
        super(itemView);

        txtPhone = (TextView) itemView.findViewById(R.id.retrieve_date);
        txtTime = (TextView) itemView.findViewById(R.id.retrieve_time);
        txtStatus = (TextView) itemView.findViewById(R.id.retrieve_status);
        txtArea = (TextView) itemView.findViewById(R.id.retrieve_targetArea);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view)
    {
        itemClickListener.onClick(view,getAdapterPosition() );
    }
}

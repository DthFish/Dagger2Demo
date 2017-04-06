package com.zlz.dagger2demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlz.dagger2demo.weight.ItemMain;
import com.zlz.dagger2demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description ${Desc}
 * Author zlz
 * Date 2016/10/19.
 */
public class TravelMainAdapter extends RecyclerView.Adapter {
    private List<ItemMain> mData = new ArrayList<>();
    private Context mContext;
    private final LayoutInflater mInflater;

    public TravelMainAdapter(Context context) {
        this.mContext = context;

        this.mInflater = LayoutInflater.from(mContext);
    }

    public TravelMainAdapter(Context context, List<ItemMain> data) {
        this.mContext = context;
        mData = data;
        this.mInflater = LayoutInflater.from(mContext);

    }

    public void setData(List<ItemMain> data) {
        if (data != null && data.size() > 0) {
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case 0:
                return new TitleViewHolder(mInflater.inflate(R.layout.item_travel_title, parent, false));
            case 1:
                return new TrafficViewHolder(mInflater.inflate(R.layout.item_travel_plane, parent, false));
            case 2:
                return new HotelViewHolder(mInflater.inflate(R.layout.item_travel_hotel, parent, false));
            case 3:
                return new DailyViewHolder(mInflater.inflate(R.layout.item_travel_daily_detail, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {

            case 0:
                handleTypeTitle((TitleViewHolder) holder, position);
                break;
            case 1:
                handleTypeTraffic((TrafficViewHolder) holder, position);
                break;
            case 2:
                handleTypeHotel((HotelViewHolder) holder, position);
                break;
            case 3:
                handleTypeTravel((DailyViewHolder) holder, position);
                break;
        }

    }

    private void handleTypeTravel(DailyViewHolder holder, int position) {

        int s = Integer.valueOf(mData.get(position).day) + 1;
        holder.mTvDate.setText("第" + s + "天");


    }

    private void handleTypeHotel(HotelViewHolder holder, int position) {


    }

    private void handleTypeTraffic(TrafficViewHolder holder, int position) {

    }

    private void handleTypeTitle(TitleViewHolder holder, int position) {
        ItemMain itemMain = mData.get(position);
        int imgId = R.mipmap.ic_launcher;
        switch (itemMain.iconType) {
            case 0:
                imgId = R.drawable.plane;
                break;
            case 1:
                imgId = R.drawable.hotel;
                break;
            case 2:
                imgId = R.drawable.travel;
                break;
        }
        holder.mIvIcon.setImageResource(imgId);
        holder.mTvTitle.setText(itemMain.title);
        holder.mDivider.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {

        return mData.get(position).type;
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {

        TextView mTvTitle;
        ImageView mIvIcon;
        View mDivider;

        TitleViewHolder(View itemView) {
            super(itemView);
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mDivider = itemView.findViewById(R.id.divider_1);
        }
    }

    private static class TrafficViewHolder extends RecyclerView.ViewHolder {


        TrafficViewHolder(View itemView) {
            super(itemView);

        }
    }

    private static class HotelViewHolder extends RecyclerView.ViewHolder {


        HotelViewHolder(View itemView) {
            super(itemView);

        }
    }

    private static class DailyViewHolder extends RecyclerView.ViewHolder {


        TextView mTvDate;

        DailyViewHolder(View itemView) {
            super(itemView);
            mTvDate = (TextView) itemView.findViewById(R.id.tv_date);

        }
    }

}

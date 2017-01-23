package com.zlz.dagger2demo;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.zlz.dagger2demo.adapter.TravelMainAdapter;
import com.zlz.dagger2demo.weight.DateRadioButton;
import com.zlz.dagger2demo.weight.ItemMain;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.generateViewId;

/**
 * Description ${Desc}
 * Author Zhaolizhi
 * Date 2016/10/19.
 */
@Deprecated
public class TravelDetailActivity2 extends AppCompatActivity {

    private RecyclerView mRvMain;
    private List<ItemMain> mItemMains;
    private TravelMainAdapter mAdapter;
    private RadioGroup mRg;
    private LinearLayoutManager mLayoutManager;
    //View绘制区域
    private Dimension mDimension = new Dimension();
    private boolean isFirstIn = true;
    //行程参考所在索引
    private int mDailyTitleIndex;
    //RadioButton 数量
    private int mRbCount;
    //需要滚动到的index
    private int mMoveToIndex;

    private boolean mShouldMove;

    private List<Integer> mRbIds = new ArrayList<>();
    private boolean isCheck;
    private ScrollView mSv;
    private RelativeLayout.LayoutParams mSvLayoutParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);
        initData();
        initView();
        initEvent();
    }

    private void initView() {
        mRvMain = (RecyclerView) findViewById(R.id.rv_main);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mSv = (ScrollView) findViewById(R.id.sv);
        initRadioButton(mRbCount);
    }

    private void initRadioButton(int rbCount) {
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 40;
        for (int i = 0; i < rbCount; i++) {
            DateRadioButton radioButton = new DateRadioButton(this, i + 1 + "", 60, 5, false);
            int id = generateViewId();
            radioButton.setId(id);
            mRbIds.add(id);
            mRg.addView(radioButton, params);
        }
        if (rbCount > 0) {
            DateRadioButton rb = (DateRadioButton) mRg.getChildAt(0);
            rb.setChecked(true);
        }

        mSvLayoutParams = (RelativeLayout.LayoutParams) mSv.getLayoutParams();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && isFirstIn) {
            mDimension = getAreaThree(this);
            mSvLayoutParams.topMargin = mDimension.mHeight;
            mSv.requestLayout();
            isFirstIn = false;
        }
    }

    private int mCurrentCheckedRbIndex;
    private boolean isScrollCheck;

    private void initEvent() {
        mAdapter = new TravelMainAdapter(this, mItemMains);
        mRvMain.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvMain.setLayoutManager(mLayoutManager);
        mRvMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItemPosition = mLayoutManager.findLastVisibleItemPosition();
                int firstItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                if (mShouldMove) {
                    mShouldMove = false;
                    int n = mMoveToIndex - firstItemPosition;
                    if (0 <= n && n < mRvMain.getChildCount()) {
                        int top = mRvMain.getChildAt(n).getTop();
                        Log.d("TAG", "-----------------smoothScrollBy----------------------/" + top);
                        mRvMain.scrollBy(0, top);
                        return;
                    }
                }

                if (!recyclerView.canScrollVertically(1)) {

                    if (mRbIds.size() > 0) {
                        mCurrentCheckedRbIndex = mRbIds.size() - 1;
                        ((RadioButton) mRg.findViewById(mRbIds.get(mCurrentCheckedRbIndex))).setChecked(true);
                    }

                }else{

                    if (firstItemPosition > mDailyTitleIndex && ((firstItemPosition - mDailyTitleIndex - 1) >= 0) && mCurrentCheckedRbIndex != (firstItemPosition - mDailyTitleIndex - 1)) {
                        mCurrentCheckedRbIndex = firstItemPosition - mDailyTitleIndex - 1;
                        if (mRbIds.size() > mCurrentCheckedRbIndex) {

                            isScrollCheck = true;
                            ((RadioButton) mRg.findViewById(mRbIds.get(mCurrentCheckedRbIndex))).setChecked(true);
                        }
                    }

                    if (firstItemPosition <= mDailyTitleIndex) {

                        if (mRbIds.size() > 0 && mCurrentCheckedRbIndex != 0) {
                            mCurrentCheckedRbIndex = 0;
                            isScrollCheck = firstItemPosition != mDailyTitleIndex;
                            ((RadioButton) mRg.findViewById(mRbIds.get(0))).setChecked(true);
                        }
                    }

                    if (firstItemPosition > mDailyTitleIndex) {
                        mSvLayoutParams.topMargin = 0;
                        mSv.requestLayout();
                        return;
                    }
                    if (lastItemPosition > mDailyTitleIndex) {

                        mSvLayoutParams.topMargin -= dy;
                        if (mSvLayoutParams.topMargin < 0) {
                            mSvLayoutParams.topMargin = 0;
                        } else if (mSvLayoutParams.topMargin > mDimension.mHeight) {
                            mSvLayoutParams.topMargin = mDimension.mHeight;
                        }
                        mSv.requestLayout();
                    } else if (lastItemPosition <= mDailyTitleIndex) {
                        mSvLayoutParams.topMargin = mDimension.mHeight;
                        mSv.requestLayout();
                    }

                }


            }
        });
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (isScrollCheck) {
                    Log.d("TAG", "-----------------checkreturn----------------------");
                    isScrollCheck = false;
                    return;
                }

                Log.d("TAG", "-----------------check----------------------");
                for (int i = 0; i < mRbIds.size(); i++) {
                    if (mRbIds.get(i) == checkedId) {
                        mCurrentCheckedRbIndex = i;
                        mMoveToIndex = mDailyTitleIndex + i + 1;
                        isCheck = true;
                        Log.d("TAG", "-----------------moveToPosition----------------------/" + mMoveToIndex);
                        moveToPosition(mMoveToIndex);

                        mSvLayoutParams.topMargin = 0;
                        mSv.requestLayout();
                        break;
                    }
                }

            }
        });


    }

    private void initData() {
        mItemMains = new ArrayList<>();

        mItemMains.add(new ItemMain(0, "交通出行", 0, null, null, null));
        mItemMains.add(new ItemMain(1, null, -1, "1", null, null));
        mItemMains.add(new ItemMain(1, null, -1, "2", null, null));
        mItemMains.add(new ItemMain(1, null, -1, "3", null, null));
        mItemMains.add(new ItemMain(0, "酒店住宿", 1, null, null, null));
        mItemMains.add(new ItemMain(2, null, -1, null, "1", null));
        mItemMains.add(new ItemMain(2, null, -1, null, "1", null));
        mItemMains.add(new ItemMain(2, null, -1, null, "1", null));

        ItemMain dailyTitle = new ItemMain(0, "行程参考", 2, null, null, null);
        mItemMains.add(dailyTitle);

        for (int i = 0; i < 20; i++) {

            mItemMains.add(new ItemMain(3, null, -1, null, null, "" + i));
        }

        mDailyTitleIndex = mItemMains.indexOf(dailyTitle);

        mRbCount = 0;

        for (int i = 0; i < mItemMains.size(); i++) {
            if (mItemMains.get(i).type == 3) {
                mRbCount++;
            }
        }

    }


    private Dimension getAreaThree(Activity activity) {
        Dimension dimen = new Dimension();
        // 用户绘制区域
        Rect outRect = new Rect();
        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
        dimen.mWidth = outRect.width();
        dimen.mHeight = outRect.height();
        // end
        return dimen;
    }

    private static class Dimension {
        public int mWidth;
        public int mHeight;


    }

    private void moveToPosition(int n) {
//        mRvMain.stopScroll();
        int firstItem = mLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLayoutManager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            Log.d("TAG", "---------moveToPosition--------smoothScrollToPosition----------------------/" + n);
            //当要置顶的项在当前显示的第一个项的前面时
            mRvMain.scrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = mRvMain.getChildAt(n - firstItem).getTop();

            Log.d("TAG", "----1-----moveToPosition--------smoothScrollBy----------------------/" + top);
            //TODO zlz 只可能往上滚所以不可能为负，但是top老是取到负值，所以临时用Math.abs解决
            mRvMain.scrollBy(0, Math.abs(top));
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            Log.d("TAG", "-----2----moveToPosition--------smoothScrollBy----------------------/" + n);
            mRvMain.scrollToPosition(n);
            //在RecyclerView滚动监听里面的
            mShouldMove = true;
        }


    }
}


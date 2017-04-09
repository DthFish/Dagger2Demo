package com.zlz.dagger2demo.dagger.view;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.zlz.dagger2demo.BaseActivity;
import com.zlz.dagger2demo.R;
import com.zlz.dagger2demo.adapter.TravelMainAdapter;
import com.zlz.dagger2demo.dagger.bean.A;
import com.zlz.dagger2demo.dagger.bean.B;
import com.zlz.dagger2demo.dagger.component.AComponent;
import com.zlz.dagger2demo.dagger.component.DaggerAComponent;
import com.zlz.dagger2demo.dagger.component.DaggerDetailComponent;
import com.zlz.dagger2demo.dagger.module.DetailModule;
import com.zlz.dagger2demo.dagger.presenter.IDetailPresenter;
import com.zlz.dagger2demo.weight.DateRadioButton;
import com.zlz.dagger2demo.weight.ItemMain;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.view.View.generateViewId;

/**
 * Description ${1、直接用@Inject注入;
 *               2、用@Module提供@Provider注入;
 *               3、局部单例@Singleton;
 *               4、Component继承方式1以及自定义Scope注解;}
 * Author zlz
 * Date 2016/11/23.
 */

public class DetailActivity extends BaseActivity implements IDetailView {
    private static final String TAG = DetailActivity.class.getSimpleName();
    @Inject
    IDetailPresenter mPresenter;
    @Inject
    A mA;
    @Inject
    A mA2;
    @Inject
    B mB;
    @Inject
    B mB2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);
        initView();

        AComponent aComponent = DaggerAComponent.create();
        DaggerDetailComponent.builder().aComponent(aComponent).detailModule(new DetailModule(this)).build().inject(this);
        mPresenter.start();
        Log.d(TAG, "mA == mA2 ? : " + (mA == mA2));//true
        Log.d(TAG, "mA == aComponent.providerA() ? : " + (mA == aComponent.providerA()));//true
        Log.d(TAG, "mB == mB2 ? : " + (mB == mB2));//false
    }


    /**-----------------------------------------分割线 以下代码和dagger2无关-----------------------------------------------*/

    private RecyclerView mRvMain;
    private TravelMainAdapter mAdapter;
    private RadioGroup mRg;
    private LinearLayoutManager mLayoutManager;
    //View绘制区域
    private Dimension mDimension = new Dimension();
    private boolean isFirstIn = true;
    //行程参考所在索引
    private int mDailyTitleIndex;
    //需要滚动到的index
    private int mMoveToIndex;
    private boolean mShouldMove;
    private List<Integer> mRbIds = new ArrayList<>();
    private boolean isCheck;
    private ScrollView mSv;
    private RelativeLayout.LayoutParams mSvLayoutParams;
    private int mCurrentCheckedRbIndex;
    private boolean isScrollCheck;

    private void initView() {
        mRvMain = (RecyclerView) findViewById(R.id.rv_main);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mSv = (ScrollView) findViewById(R.id.sv);

        mAdapter = new TravelMainAdapter(this);
        mRvMain.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvMain.setLayoutManager(mLayoutManager);

        mSvLayoutParams = (RelativeLayout.LayoutParams) mSv.getLayoutParams();
    }

    @Override
    public void initRadioButton(int rbCount) {
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
        mRvMain.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int lastItem = mLayoutManager.findLastVisibleItemPosition();
                if (lastItem > mDailyTitleIndex && mRvMain.getChildCount() > mDailyTitleIndex) {
                    int bottom = mRvMain.getChildAt(mDailyTitleIndex).getBottom();
                    mSvLayoutParams.topMargin = Math.min(bottom + 30, mDimension.mHeight);
                    mSv.requestLayout();
                    mRvMain.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
        initEvent();
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

    private void initEvent() {

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
                        ViewCompat.offsetTopAndBottom(mSv,dy);
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

    @Override
    public void setDailyTitleIndex(int index) {
        mDailyTitleIndex = index;
    }

    @Override
    public void setData(List<ItemMain> data) {
        mAdapter.setData(data);
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
            //只可能往上滚所以不可能为负，但是top老是取到负值，所以临时用Math.abs解决
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

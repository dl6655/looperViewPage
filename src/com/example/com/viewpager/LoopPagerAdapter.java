package com.example.com.viewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import java.util.ArrayList;
import java.util.List;

public class LoopPagerAdapter extends PagerAdapter {

	private List<View> mData = new ArrayList<View>();
	private Context mContext;

	private LoopImageOnClickListener loopImageOnClickListener;
	private LoopViewPager viewPager;
	int currentItem = 0;
	private boolean isDragging;
	int imgLen;
	public LoopPagerAdapter(LoopViewPager viewPager) {
		this.viewPager = viewPager;
	}

	// 切换当前显示的图�?
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				viewPager.setCurrentItem(currentItem);// 切换当前显示的图�?
				startSidling();
			}
			super.handleMessage(msg);

		};
	};

	public void setData(ArrayList<Integer> imgs, Context context) {
		this.mContext = context;
		mData.clear();
		if (imgs.size() == 2) {
			imgs.addAll(imgs);
			imgs.addAll(imgs);
		} else if (imgs.size() == 1) {
			imgs.addAll(imgs);
			imgs.addAll(imgs);
			imgs.addAll(imgs);
		}
		this.imgLen = imgs.size();
		for (int i = 0; i < imgs.size(); i++) {
			ImageView imageView = new ImageView(mContext);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageView.setImageResource(imgs.get(i));
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (loopImageOnClickListener != null) {
						loopImageOnClickListener.onImageClick();
					}
				}

			});
			mData.add(imageView);
		}
		this.notifyDataSetChanged();

	}

	@Override
	public int getCount() {
		int len;
		if (mData != null) {
			len = mData.size();
		} else {
			len = 0;
		}
		return len;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (View) arg1;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {

	}

	@Override
	public Object instantiateItem(View container, int position) {
		View view = mData.get(position);
		try {
			((LoopViewPager) container).addView(view);
		} catch (Exception e) {
		}
		return view;
	}

	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

	public void setLoopImageOnClickListener(
			LoopImageOnClickListener loopImageOnClickListener) {
		this.loopImageOnClickListener = loopImageOnClickListener;
	}
	public static interface LoopImageOnClickListener {
		public void onImageClick();
	}
	public void startSidling() {
		currentItem = (viewPager.getCurrentItem() + 1);

		Message mess = new Message();
		mess.what = 1;
		if (!isDragging) {
			stopSidling();
			handler.sendMessageDelayed(mess, 2000);
		}		
	}

	public void stopSidling() {
		if (handler.hasMessages(1)) {
			handler.removeMessages(1);
		}
	}

	// 手动滑动时停止自动滑�?
	public void isStop() {
		isDragging = true;
	}

	public void isStart() {
		isDragging = false;
		startSidling();
	}
}

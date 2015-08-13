package com.example.com.viewpager;
import java.util.ArrayList;
import java.util.Random;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
public class MainActivity extends Activity implements OnPageChangeListener{
	 private ArrayList<Integer> imageUrl;
	    private LoopViewPager viewPager;
	    int mExtraData=0;          
	    private  ViewPagerAdapter adapter;
	    
	public  void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager=(LoopViewPager)findViewById(R.id.viewPager);
		adapter=new ViewPagerAdapter(viewPager);
		viewPager.setAdapter(adapter);
		setDate();
		if(imageUrl.size()>0){
			viewPager.setVisibility(View.VISIBLE);
			
			
			WindowManager wm = (WindowManager) getBaseContext() 
	                .getSystemService(Context.WINDOW_SERVICE); 

	     int width = wm.getDefaultDisplay().getWidth(); 

			 adapter.setData(imageUrl, this);
	         viewPager.setId(new Random().nextInt(100));
	         viewPager.setOnPageChangeListener(MainActivity.this);                          
	         viewPager.getLayoutParams().height = (int) (width / 2.4);
	         viewPager.setAdapter(adapter);
	         viewPager.setCurrentItem(0);
	         
		}else{
			viewPager.setVisibility(View.GONE);
		}  
		 adapter.startSidling();
	    
		
		
		}

	 class ViewPagerAdapter extends LoopPagerAdapter{

		public ViewPagerAdapter(LoopViewPager viewPager) {
			super(viewPager);
			// TODO Auto-generated constructor stub
		}
		 
	 }

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0) {
        case ViewPager.SCROLL_STATE_DRAGGING:
        	if(adapter!=null){
        		adapter.isStop();
        	}       	
            break;           
        case ViewPager.SCROLL_STATE_IDLE:
        	if(adapter!=null){
        		 adapter.isStart();
        	}          
            break;          
        case ViewPager.SCROLL_STATE_SETTLING:
            break;
        default:
            break;
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }
    public void setDate(){
    	imageUrl=new ArrayList<Integer>();
    	imageUrl.add(R.drawable.item06);  
    }
    public void onPageSelected(int arg0) {
       
        
    }

}

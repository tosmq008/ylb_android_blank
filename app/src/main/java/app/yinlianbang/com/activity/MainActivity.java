package app.yinlianbang.com.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.yinlianbang.com.R;
import app.yinlianbang.com.utils.ScreenUtils;

public class MainActivity extends Activity {
    private ArrayList<String> data=new ArrayList<>();

    //状态栏的高度
    private int statusHeight;
    LinearLayout searchView;
    ListView listView;
    View myBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_test1);

        searchView = (LinearLayout) findViewById(R.id.index_top_view_id);
        listView= (ListView) findViewById(R.id.listview);

        statusHeight = ScreenUtils.getStatusHeight(this);
        RelativeLayout bannerFatherView = (RelativeLayout) View.inflate(this, R.layout.index_banner, null);
        myBanner=  bannerFatherView.findViewById(R.id.convenientBanner);

        initBanner(bannerFatherView);
        listView.addHeaderView(bannerFatherView);


        final listViewSimpleAdapter ll=new listViewSimpleAdapter(this,R.layout.listview_item,new String[]{"1"});
        listView.setAdapter(ll);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            //监听滑动状态的改变
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            //用于监听ListView屏幕滚动
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                int[] ints = new int[2];
                myBanner.getLocationOnScreen(ints);
                /**
                 * mImage距离屏幕顶部的距离(图片顶部在屏幕最上面，向上滑动为负数，所以取反)
                 * 如果不隐藏状态栏，需要加上状态栏的高度；隐藏状态栏就不用加了；
                 */
                int scrollY = -ints[1]+statusHeight;

                //mImage这个view的高度
                int imageHeight = myBanner.getMeasuredHeight();

                if (myBanner != null && imageHeight > 0) {
                    //如果“图片”没有向上滑动，设置为全透明
                    if (scrollY < 0) {
                        searchView.getBackground().setAlpha(0);
                    } else {
                        //“图片”已经滑动，而且还没有全部滑出屏幕，根据滑出高度的比例设置透明度的比例
                        if (scrollY < imageHeight) {
                            int progress = (int) (new Float(scrollY) / new Float(imageHeight) * 255);//255
                            searchView.getBackground().setAlpha(progress);
                        } else {
                            //“图片”全部滑出屏幕的时候，设为完全不透明
                            searchView.getBackground().setAlpha(255);
                        }
                    }
                }

            }
        });



    }


    public void initBanner(View view){
        ConvenientBanner convenientBanner= (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        data.add("http://yinlianbang.com/images/201706/goods_img/181_P_1497582537660.png");
        data.add("http://yinlianbang.com/images/201706/goods_img/159_P_1496713237365.png");
        convenientBanner.startTurning(4000);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, data)
                .setPageIndicator(new int[]{R.drawable.banneranniu, R.drawable.banneranniuhuangse})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    class NetworkImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(context).load("http://pic48.nipic.com/file/20140912/7487939_223919315000_2.jpg").into(imageView);
        }
    }



class listViewSimpleAdapter extends ArrayAdapter{

    private Integer resourceId;

    public listViewSimpleAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
        this.resourceId=resource;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //可以加holder

    View
              view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
       return view;
    }
}

}


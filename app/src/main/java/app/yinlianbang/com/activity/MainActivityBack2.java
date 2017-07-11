package app.yinlianbang.com.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import app.yinlianbang.com.R;

public class MainActivityBack2 extends Activity {
    private ArrayList<String> data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        ConvenientBanner convenientBanner= (ConvenientBanner) findViewById(R.id.convenientBanner);

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




}


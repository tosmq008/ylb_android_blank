package app.yinlianbang.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sivin.Banner;
import com.sivin.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

import app.yinlianbang.com.R;
import app.yinlianbang.com.model.BannerModel;

public class MainActivityBack extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);


        List<BannerModel>  bannerModelList=new ArrayList<>();
        BannerModel bannerModel1=new BannerModel();
        bannerModel1.setImageUrl("http://yinlianbang.com/images/201706/goods_img/159_P_1496713237365.png");
        bannerModel1.setTargetUrl("http://yinlianbang.com/mobile/index.php?m=default&c=goods&a=index&id=159");
        bannerModel1.setTips("岳阳楼竞品");

        BannerModel bannerModel2=new BannerModel();
        bannerModel2.setImageUrl("http://yinlianbang.com/images/201706/goods_img/181_P_1497582537660.png");
        bannerModel1.setTargetUrl("http://yinlianbang.com/mobile/index.php?m=default&c=goods&a=index&id=181");
        bannerModel1.setTips("岳阳楼经典");



        bannerModelList.add(bannerModel1);
        bannerModelList.add(bannerModel2);

        BannerAdapter adapter = new BannerAdapter<BannerModel>(bannerModelList) {
            @Override
            protected void bindTips(TextView tv, BannerModel bannerModel) {
                tv.setText(bannerModel.getTips());
            }

            @Override
            public void bindImage(ImageView imageView,final BannerModel bannerModel) {
                Glide.with(MainActivityBack.this)
                        .load(bannerModel
                                .getImageUrl())
                        .placeholder(R.drawable.ic_launcher)
                        .error(R.drawable.ic_launcher)
                        .into(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivityBack.this, bannerModel.getTargetUrl() , Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        Banner mBanner = (Banner) findViewById(R.id.convenientBanner);
        mBanner.setBannerAdapter(adapter);

    }
}



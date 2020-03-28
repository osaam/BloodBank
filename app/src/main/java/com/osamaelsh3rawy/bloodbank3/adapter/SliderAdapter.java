package com.osamaelsh3rawy.bloodbank3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.osamaelsh3rawy.bloodbank3.R;

public class SliderAdapter extends PagerAdapter {
    LayoutInflater LayoutInflater;
    private Context context;
    private int[] slider_img = new int[]{R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};
    private String[] slider_tx = new String[]
            {"  1اهلا ومرحبا بكم ",
                    "    2 في تطبيق بنك الدم",
                    "3 لمساعده كل محتاج"};


    public SliderAdapter(Context context) {
        this.context = context;
        LayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return slider_img.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View v = LayoutInflater.inflate(R.layout.slider_item, container, false);

        ImageView slider_image = (ImageView) v.findViewById(R.id.slider_img);
        TextView slider_text = (TextView) v.findViewById(R.id.slider_tx);

        slider_image.setImageResource(slider_img[position]);
        slider_text.setText(slider_tx[position]);

        container.addView(v);

        return v;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}



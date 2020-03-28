package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.model.Posts.post.PostData;
import com.osamaelsh3rawy.bloodbank3.data.model.myFevorits.MyFavouritesData;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeArticleSelctedFragment extends BaseFragment {


    public PostData postData;
    public MyFavouritesData myFavouritesData;
    @BindView(R.id.img_article)
    ImageView imgArticle;
    @BindView(R.id.tx_titel)
    TextView txTitel;
    @BindView(R.id.img2_article)
    ImageView img2Article;
    @BindView(R.id.tx_article)
    TextView txArticle;

    public HomeArticleSelctedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_articles_selected, container, false);
        ButterKnife.bind(this, view);
        initFragment();

        txTitel.setText(postData.getTitle());
        txArticle.setText(postData.getContent());
        Glide.with(getActivity()).load(postData.getThumbnailFullPath()).into(imgArticle);

        if (postData.getIsFavourite()) {
            img2Article.setImageResource(R.drawable.fav_fill_article);

        } else {
            img2Article.setImageResource(R.drawable.fav_article);
        }
        img2Article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (postData.getIsFavourite()) {
                    img2Article.setImageResource(R.drawable.fav_article);
                    postData.setIsFavourite(false);
                } else {
                    img2Article.setImageResource(R.drawable.fav_fill_article);
                    postData.setIsFavourite(true);
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {

        baseActivity.finish();
    }
}


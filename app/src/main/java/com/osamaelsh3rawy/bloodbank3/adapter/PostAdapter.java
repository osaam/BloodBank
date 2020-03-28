package com.osamaelsh3rawy.bloodbank3.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.model.Posts.post.PostData;
import com.osamaelsh3rawy.bloodbank3.data.model.addRemFAV.AddRemFav;
import com.osamaelsh3rawy.bloodbank3.view.activity.BaseActivity;
import com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle.HomeArticleSelctedFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private BaseActivity activity;
    private ArrayList<PostData> listPostData = new ArrayList<>();
    ApiServies apiServies;
    private String TAG="PostAdapter";


    public PostAdapter(Context context, BaseActivity activity, ArrayList<PostData> listPostData) {
        this.context = context;
        this.activity = activity;
        this.listPostData = listPostData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_article_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostData postData = listPostData.get(position);
        holder.txArticle.setText(postData.getTitle());
        Glide.with(context).load(postData.getThumbnailFullPath()).into(holder.imgArticle);

        if (postData.getIsFavourite()) {
            holder.img2Article.setImageResource(R.drawable.fav_fill_article);

        } else {
            holder.img2Article.setImageResource(R.drawable.fav_article);
        }
        holder.img2Article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (postData.getIsFavourite()) {
                    holder.img2Article.setImageResource(R.drawable.fav_article);
                    postData.setIsFavourite(false);
                } else {
                    holder.img2Article.setImageResource(R.drawable.fav_fill_article);
                    postData.setIsFavourite(true);
                //    addFav(position);
                }
            }
        });

        holder.position = position;

    }
    public void addFav(int position){
        apiServies.addRemFevorites(listPostData.get(position).getId(),"Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<AddRemFav>() {
            @Override
            public void onResponse(Call<AddRemFav> call, Response<AddRemFav> response) {
              try{
                if(response.body().getStatus()==1){
                    Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_SHORT).show(); }
                else {
                    Toast.makeText(activity,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }}
              catch (Exception e){
                  Log.e(TAG,e.getMessage());

              }
            }
            @Override
            public void onFailure(Call<AddRemFav> call, Throwable t) {
            }
        });
    }


    @Override
    public int getItemCount() {
        return listPostData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_article)
        ImageView imgArticle;
        @BindView(R.id.tx_article)
        TextView txArticle;
        @BindView(R.id.recycler_img2_article)
        ImageView img2Article;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PostData post = listPostData.get(position);
                    HomeArticleSelctedFragment homeArticleSelctedFragment = new HomeArticleSelctedFragment();
                    homeArticleSelctedFragment.postData = post;

                    replaceFragment(activity.getSupportFragmentManager(), R.id.activity_home_container, homeArticleSelctedFragment);
                }
            });
        }

        @OnClick(R.id.recycler_img2_article)
        public void onViewClicked() {
            addFav(position);
        }
    }
}

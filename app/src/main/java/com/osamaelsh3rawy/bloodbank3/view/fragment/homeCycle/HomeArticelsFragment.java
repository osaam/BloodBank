package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.adapter.FavouritesAdapter;
import com.osamaelsh3rawy.bloodbank3.adapter.PostAdapter;
import com.osamaelsh3rawy.bloodbank3.adapter.SpinnerAdapter;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.local.SharedPreferencesManger;
import com.osamaelsh3rawy.bloodbank3.data.model.Posts.post.PostData;
import com.osamaelsh3rawy.bloodbank3.data.model.Posts.post.Posts;
import com.osamaelsh3rawy.bloodbank3.data.model.myFevorits.MyFevorits;
import com.osamaelsh3rawy.bloodbank3.helper.HelperMethod;
import com.osamaelsh3rawy.bloodbank3.helper.OnEndLess;
import com.osamaelsh3rawy.bloodbank3.view.activity.BaseActivity;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.osamaelsh3rawy.bloodbank3.data.api.ApiClient.getClient;
import static com.osamaelsh3rawy.bloodbank3.data.local.constance.User_Api_Takin;
import static com.osamaelsh3rawy.bloodbank3.helper.GeneralRequest.getData;
import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.dismissProgressDialog;
import static com.osamaelsh3rawy.bloodbank3.helper.HelperMethod.replaceFragment;


public class HomeArticelsFragment extends BaseFragment {

    @BindView(R.id.recycler_articles)
    RecyclerView recyclerArticles;
    @BindView(R.id.home_articles_et_search)
    EditText homeArticlesEtSearch;
    @BindView(R.id.sp_articels)
    Spinner spArticels;

    private ArrayList<PostData> listPostData = new ArrayList<>();
    private ApiServies apiServies;

    private LinearLayoutManager linearLayoutManager;
    private PostAdapter postAdapter;
    SpinnerAdapter catigoryPostAdapter;
    private int MaxPage;
    private OnEndLess onEndLess;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_articles, container, false);
        ButterKnife.bind(this, view);
        apiServies = getClient().create(ApiServies.class);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerArticles.setLayoutManager(linearLayoutManager);

        postAdapter = new PostAdapter(getActivity(), (BaseActivity) getActivity(), listPostData);
        recyclerArticles.setAdapter(postAdapter);

        catigoryPostAdapter = new SpinnerAdapter(getActivity());

        getData(apiServies.catigoryPosts(), catigoryPostAdapter, "choose category", spArticels);


   pagination();

//        getPost(1);
        return view;
    }

    private void pagination() {

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerArticles.setLayoutManager(linearLayoutManager);
        onEndLess=new OnEndLess(linearLayoutManager,1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page<=MaxPage) {
                    if(MaxPage != 0 &&current_page!= 1){
                        onEndLess.previous_page=current_page;
                        getnotify(current_page);
                    }
                    else {
                        onEndLess.current_page=onEndLess.previous_page;
                    }
                }
            }
        };

        recyclerArticles.addOnScrollListener(onEndLess);
        postAdapter = new PostAdapter(getActivity(), (BaseActivity) getActivity(), listPostData);
        recyclerArticles.setAdapter(postAdapter);

        getnotify(1);
    }

    private void getnotify(int i) {

        apiServies.getposts("EZEeit6WHBKcuMIXPHvlGrrwGYPjPXshIRLcFi6fDlzJ7mWOih6iJvLij0vo", 1).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (response.body().getStatus() == 1) {
                    listPostData.addAll(response.body().getData().getData());
                    postAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

            }
        });
    }
    @OnClick({R.id.home_articles_et_search,R.id.home_articles_img_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_articles_et_search:


                break;
            case R.id.home_articles_img_search:
                String search = homeArticlesEtSearch.getText().toString();
                HelperMethod.showProgressDialog(getActivity(),"please wait");
                String tokin= SharedPreferencesManger.LoadData(getActivity(),User_Api_Takin);

                apiServies.searchPosts(tokin,1,search,catigoryPostAdapter.selectedId ).enqueue(new Callback<Posts>() {
                    @Override
                    public void onResponse(Call<Posts> call, Response<Posts> response) {
                      dismissProgressDialog();
                        if (response.body().getStatus()==1) {
                            MaxPage=response.body().getData().getLastPage();
                            listPostData.clear();
                            listPostData.addAll(response.body().getData().getData());
                            postAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Posts> call, Throwable t) {

                    }
                });
                break;
 }
    }

    @OnClick()
    public void onViewClicked() {
    }
}


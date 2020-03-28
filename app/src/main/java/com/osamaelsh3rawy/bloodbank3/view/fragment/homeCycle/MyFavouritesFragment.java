package com.osamaelsh3rawy.bloodbank3.view.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.osamaelsh3rawy.bloodbank3.R;
import com.osamaelsh3rawy.bloodbank3.adapter.FavouritesAdapter;
import com.osamaelsh3rawy.bloodbank3.adapter.PostAdapter;
import com.osamaelsh3rawy.bloodbank3.data.api.ApiServies;
import com.osamaelsh3rawy.bloodbank3.data.model.Posts.post.PostData;
import com.osamaelsh3rawy.bloodbank3.data.model.myFevorits.MyFavouritesData;
import com.osamaelsh3rawy.bloodbank3.data.model.myFevorits.MyFevorits;
import com.osamaelsh3rawy.bloodbank3.helper.OnEndLess;
import com.osamaelsh3rawy.bloodbank3.view.activity.BaseActivity;
import com.osamaelsh3rawy.bloodbank3.view.fragment.folder.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.osamaelsh3rawy.bloodbank3.data.api.ApiClient.getClient;


public class MyFavouritesFragment extends BaseFragment {


    @BindView(R.id.recycler_my_favourites)
    RecyclerView recyclerMyFavourites;
    private ArrayList<MyFavouritesData> listFivoriteData = new ArrayList<>();
    private int MaxPage;
    private OnEndLess onEndLess;
    private LinearLayoutManager layoutManager;
    private FavouritesAdapter myFavourietsAdapter;
    private ApiServies apiServies;

    public MyFavouritesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home_more_favoriets, container, false);
        initFragment();
        ButterKnife.bind(this, view);
        apiServies = getClient().create(ApiServies.class);
        pagination();
        return view;
    }

    private void pagination() {
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerMyFavourites.setLayoutManager(layoutManager);
        onEndLess = new OnEndLess(layoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= MaxPage) {
                    if (MaxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getnotify(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                }
            }
        };

        recyclerMyFavourites.addOnScrollListener(onEndLess);
        myFavourietsAdapter = new FavouritesAdapter(getActivity(), (BaseActivity) getActivity(), listFivoriteData);
        recyclerMyFavourites.setAdapter(myFavourietsAdapter);

        getnotify(1);
    }

    private void getnotify(int i) {

        apiServies.getMyFevorites("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<MyFevorits>() {
            @Override
            public void onResponse(Call<MyFevorits> call, Response<MyFevorits> response) {
               if(response.body().getStatus()==1){
                listFivoriteData.addAll(response.body().getData().getData());
                myFavourietsAdapter.notifyDataSetChanged();
               }}

            @Override
            public void onFailure(Call<MyFevorits> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {
        super.onBack();
      //  getActivity().onBackPressed();
    }

}


package com.osamaelsh3rawy.bloodbank3.view.fragment.folder;

import androidx.fragment.app.Fragment;

import com.osamaelsh3rawy.bloodbank3.view.activity.BaseActivity;


public class BaseFragment extends Fragment {

 public BaseActivity baseActivity;

  public  void initFragment(){
      baseActivity=(BaseActivity) getActivity();
      baseActivity.baseFragment=this;
  }

  public void onBack(){

  }
  //    baseActivity.superonBackPressed();
}
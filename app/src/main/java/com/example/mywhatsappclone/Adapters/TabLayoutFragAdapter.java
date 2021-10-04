package com.example.mywhatsappclone.Adapters;

import android.widget.Switch;

import com.example.mywhatsappclone.Fragments.CallsFragment;
import com.example.mywhatsappclone.Fragments.ChatsFragment;
import com.example.mywhatsappclone.Fragments.StatusFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabLayoutFragAdapter extends FragmentPagerAdapter {
    public TabLayoutFragAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


//    public TabLayoutFragAdapter(@NonNull FragmentManager fm, int behavior) {
//        super(fm, behavior);
//    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new ChatsFragment();
            case 1: return new StatusFragment();
            case 2: return new CallsFragment();
            default: return new ChatsFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0){
      title="Chats";
        }
        if(position==1){
            title="Status";
        }

        if(position==2){
            title="Calls";
        }
        return title;
    }
}

package com.bawei.yuchenlei20190731.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), onLayout(), null);
        onView(view);
        onData();

        return view;
    }

    protected abstract int onLayout();

    protected abstract void onView(View view);

    protected abstract void onData();
}

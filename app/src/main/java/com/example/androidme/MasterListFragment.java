package com.example.androidme;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidme.data.AndroidImageAssets;

public class MasterListFragment extends Fragment
{
    OnImageClickListener mImageClickListener;
    public  interface OnImageClickListener
    {
         void onImageSelected(int position);
    }

    public MasterListFragment()
    {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try
        {
            mImageClickListener=(OnImageClickListener)context;
        }catch(ClassCastException e)
        {
                     throw new ClassCastException(context.toString()+"Must implement interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View rootView=inflater.inflate(R.layout.fragment_master_list,container,false);
        GridView gridView=(GridView)rootView.findViewById(R.id.images_grid_view);
        MasterListAdapter masterListAdapter=new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(masterListAdapter);
       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               mImageClickListener.onImageSelected(position);
           }
       });
        return rootView;
    }
}

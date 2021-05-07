package com.example.androidme;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment  extends Fragment {
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";
    public void setImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>)mImageIds);
        currentState.putInt(LIST_INDEX,mListIndex);
    }

    private List<Integer> mImageIds;
    private int mListIndex;
    private static final String TAG = "BodyPartFragment";

    public BodyPartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if(savedInstanceState!=null)
        {
            mImageIds=savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex=savedInstanceState.getInt(LIST_INDEX);
        }
        View rootView=inflater.inflate(R.layout.fragment_body_part,container,false);
        final ImageView imageView=(ImageView)rootView.findViewById(R.id.body_part_image_view);
       if(mImageIds!=null)
       {
           imageView.setImageResource(mImageIds.get(mListIndex));
           imageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(mListIndex<mImageIds.size()-1)
                   {
                       mListIndex++;
                   }
                   else
                   {
                       mListIndex=0;
                   }
                   imageView.setImageResource(mImageIds.get(mListIndex));
               }
           });
       }
       else
       {
           Log.v(TAG, "This fragment has a null list of image id's");
       }
        return rootView;
    }
}

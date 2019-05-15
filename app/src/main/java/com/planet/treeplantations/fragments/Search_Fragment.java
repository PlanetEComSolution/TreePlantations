package com.planet.treeplantations.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.planet.treeplantations.databinding.FragmentSearchBinding;
import com.planet.treeplantations.R;


public class Search_Fragment extends Fragment {
    private  FragmentSearchBinding binding;


    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        getActivity().setTitle("Search Project");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_, container, false);
        View view = binding.getRoot();


        String[] voucher = {"2014-2015", "2015-2016", "2016-2017"};
        final ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.simple_dropdown_item, voucher);
        binding.finYer.setAdapter(adapter);
        binding.finYer.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){

                binding.finYer.setFocusable(false);
                binding.finYer.setFocusableInTouchMode(false);
                //  text.setClickable(false);

                if (!binding.finYer.getText().toString().equals(""))
                    adapter.getFilter().filter(null);
                binding.finYer.showDropDown();
                return false;
            }

        });

        return view;
    }

}

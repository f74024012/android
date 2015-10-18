package com.example.mac.test;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;



public class FragmentB extends Fragment {

    private ListView diary;

    public FragmentB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        String content = "";
        char[] buffer = new char[256];
        int count;

        diary = (ListView) view.findViewById(R.id.lv_diary);
        try{
            FileInputStream fis = getActivity().openFileInput("diary.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            while((count = isr.read(buffer)) > 0){
                String s = String.copyValueOf(buffer, 0, count);
                content += s;
                buffer = new char[256];
            }
            isr.close();

            String[] contArr = content.split("''");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, contArr);
            diary.setAdapter(adapter);

        }
        catch(IOException e){
            e.printStackTrace ();
        }


        return view;
    }


}

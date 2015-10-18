package com.example.mac.test;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;


public class FragmentC extends Fragment {

    private TextView tvTime;
    private EditText etContent;
    private String cTime, storeText;
    private Button btnStore;

    public FragmentC(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        tvTime = (TextView) view.findViewById(R.id.tv_time);
        etContent = (EditText) view.findViewById(R.id.et_cont);
        btnStore = (Button) view.findViewById(R.id.btn_store);
        btnStore.setOnClickListener(listener);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        cTime = getString(R.string.time) + c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) + "/"
                + c.get(Calendar.DATE) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
        tvTime.setText(cTime);

        return view;
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            storeText = etContent.getText().toString();

            try{
                FileOutputStream fos = getActivity().openFileOutput("diary.txt", Context.MODE_APPEND);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                osw.write(cTime + "\n" + storeText + "''");
                osw.flush();
                osw.close();
                Toast.makeText(getActivity(), R.string.store_success, Toast.LENGTH_SHORT).show();
                btnStore.setEnabled(false);
            }
            catch(IOException e){
                e.printStackTrace ();
            }
        }

    };
}

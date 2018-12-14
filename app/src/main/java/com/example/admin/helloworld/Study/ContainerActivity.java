package com.example.admin.helloworld.Study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.helloworld.R;
import com.example.admin.helloworld.fragment.AFragment;

public class ContainerActivity extends AppCompatActivity {

    private AFragment aFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        aFragment = new AFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,aFragment).commitAllowingStateLoss();
    }
}

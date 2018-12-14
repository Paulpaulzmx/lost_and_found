package com.example.admin.helloworld.fragment_course;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.helloworld.R;
import com.example.admin.helloworld.statusbar.StatusBarUtil;


public class ShopActivity extends AppCompatActivity implements View.OnClickListener{

    private int number=1;
    private ImageView imageView,minus,add,shop;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        StatusBarUtil.setColorNoTranslucent(ShopActivity.this, -94975);
        init();

    }

    public void init(){
        imageView = findViewById(R.id.imageView);
        Glide.with(this).load(getIntent().getStringExtra("url")).into(imageView);

        TextView suggest = findViewById(R.id.et_type_name);
        suggest.setText(getIntent().getStringExtra("market"));

        TextView prices = findViewById(R.id.et_type_prices);
        prices.setText(getIntent().getStringExtra("prices"));

        TextView place = findViewById(R.id.place);
        place.setText(getIntent().getStringExtra("place"));

        TextView form = findViewById(R.id.e_form);
        form.setText(getIntent().getStringExtra("form"));

        minus = findViewById(R.id.minus);
        minus.setOnClickListener(this);

        add = findViewById(R.id.add);
        add.setOnClickListener(this);

        shop = findViewById(R.id.add_more);
        shop.setOnClickListener(this);

        textView= findViewById(R.id.number);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.minus:
                number--;
                if (number<0)
                    number = 0;
                textView.setText(""+number);
                break;
            case  R.id.add_more:
                number++;
                textView.setText(""+number);
                break;
            case R.id.add:
                number++;
                textView.setText(""+number);
                break;

        }

    }
}

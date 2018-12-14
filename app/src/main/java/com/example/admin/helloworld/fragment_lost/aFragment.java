package com.example.admin.helloworld.fragment_lost;


import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;


import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.helloworld.PostGetUtil;
import com.example.admin.helloworld.R;
import com.example.admin.helloworld.UploadUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

public class aFragment extends Fragment implements View.OnClickListener {

    private Uri imageUri;
    public static final int TAKE_PHOTO = 0;
    public static final int SELECT_PHOTO=1;
    private String img_src;
    private String name;
    private String place;
    private String time;
    private String phone;
    private EditText res_name;
    private EditText  lost_place;
    private EditText  lost_time;
    private EditText  tel;
    private CheckBox  checkbox;
    private Spinner   spinner;
    private EditText  fill_text;
    private ImageView imageView;
    private Button    send_btn;
    private LinearLayout    remark_layout;
    private  String str;

    private File outputImage;
    private int way = -1;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //将布局文件转化成View对象
        View view = inflater.inflate(R.layout.lost,container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getEditTextData();
        send_btn.setOnClickListener(this);
        imageView.setOnClickListener(this);



        /*  为spinner设置监听器    */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //拿到被选择项的值
                str = (String) spinner.getSelectedItem();
                //把该值用Toast显示出来
                Toast.makeText(getActivity(), str ,Toast.LENGTH_LONG).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        /*  为checkbox添加监听器*/
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    spinner.setVisibility(View.VISIBLE);
                    remark_layout.setVisibility(View.VISIBLE);
                }else{
                    spinner.setVisibility(View.GONE);
                    remark_layout.setVisibility(View.GONE);
                }
            }
        });
    }
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.send_bnt:
                name = res_name.getText().toString().trim();
                place = lost_place.getText().toString().trim();
                time = lost_time.getText().toString().trim();
                phone = tel.getText().toString().trim();
                String fill=fill_text.getText().toString().trim();
                if(name.equals("")||place.equals("")||lost_time.equals("")||!isPhone(phone))
                    Toast.makeText(getActivity(),"请输入正确信息",Toast.LENGTH_SHORT).show();
                else
                    uploadImage();
                break;

                case R.id.picture_layout:
                    AlertDialog.Builder customizeDialog = new AlertDialog.Builder(getActivity());
                    final String[] items = {"拍照", "上传本地图片"};      //列表项
                    customizeDialog.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    takePhoto();
                                    way = 0;
                                    break;
                                case 1:
                                    selectImg();
                                    way = 1;
                                default:
                                    break;
                            }
                        }
                    });
                    customizeDialog.show(); //显示对话框
            }
    }


   public void  getEditTextData(){
       res_name=(EditText)getActivity().findViewById(R.id.name_et);
       lost_place=(EditText)getActivity().findViewById(R.id.place_et);
       lost_time=(EditText)getActivity().findViewById(R.id.time_et);
       tel=(EditText)getActivity().findViewById(R.id.tel_et);
       checkbox=(CheckBox) getActivity().findViewById(R.id.check);
       spinner=(Spinner) getActivity().findViewById(R.id.spinner_layout);
       fill_text=(EditText)getActivity().findViewById(R.id.fill_et);
       imageView=(ImageView) getActivity().findViewById(R.id.picture_layout);
       send_btn=(Button) getActivity().findViewById(R.id.send_bnt);
       remark_layout=(LinearLayout)getActivity().findViewById(R.id.remark_layout) ;

       lost_time.setInputType(InputType.TYPE_NULL); //不显示系统输入键盘
       lost_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               // TODO Auto-generated method stub
               if(hasFocus){
                   Calendar c = Calendar.getInstance();
                   new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                       @Override
                       public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                           // TODO Auto-generated method stub
                           lost_time.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                       }
                   }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
               }
           }
       });
       lost_time.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // TODO Auto-generated method stub
               Calendar c = Calendar.getInstance();
               new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                       // TODO Auto-generated method stub
                       lost_time.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                   }
               }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
           }
       });


   }

    public void selectImg() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent,SELECT_PHOTO);
    }


    public void takePhoto(){
        //outputImage用于存储拍照后的图片
        String str = String.valueOf(System.currentTimeMillis())+".jpg";
        System.out.println(str);
        outputImage = new File(getActivity().getExternalCacheDir(), str);
        try {
            Log.d("TAG", "getExternalCacheDir() : " + getActivity().getExternalCacheDir().getAbsolutePath());
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //不同安卓版本不同
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(getActivity(),
                    "com.example.cameraalbumtest.fileprovider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case SELECT_PHOTO:
                switch (resultCode){
                    case RESULT_OK:
                        Uri uri = data.getData();
                        img_src = uri.getPath();

                        System.out.println(img_src);

                        ContentResolver cr = getActivity().getContentResolver();
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                            /* 将Bitmap设定到ImageView */
                            imageView.setImageBitmap(bitmap);

                            String[] proj = {MediaStore.Images.Media.DATA};
                            CursorLoader loader = new CursorLoader(getContext(), uri, proj, null, null, null);
                            Cursor cursor = loader.loadInBackground();
                            if (cursor != null) {
                                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                                cursor.moveToFirst();

                                img_src = cursor.getString(column_index);//图片实际路径
                                System.out.println(img_src);

                            }
                            cursor.close();

                        } catch (FileNotFoundException e) {
                            Log.e("Exception", e.getMessage(), e);
                        }
                        break;
                }
                break;

            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }




    public void uploadImage() {
        System.out.println("hello wolrd and i "+img_src);
        if(way != -1)
        {
            Toast.makeText(getContext(),"提交完成",Toast.LENGTH_LONG).show();
            new Thread(new Runnable() {
                @Override
                public void run() {

                    String uploadurl = "http://47.106.146.182/pictures/serve.php";
                    try {
                        if (way == 1) {
                            File file = new File(img_src);
                            PostGetUtil.SendGetRequest("loss_name=" + name + "&user_id=2&loss_image=" + file.getName() + "&type=7&loss_time=" + time + "&loss_address=" + place
                                    + "&loss_type=2&loss_status=1&loss_phone=" + phone);       //数据库内容的上传
                            UploadUtil.uploadImage(file, uploadurl);        //图片的上传，默认系统的命名，支持相册上传
                        }else{
                            PostGetUtil.SendGetRequest("loss_name=" + name + "&user_id=2&loss_image=" + outputImage.getName() + "&type=7&loss_time=" + time + "&loss_address=" + place
                                    + "&loss_type=2&loss_status=1&loss_phone=" + phone);       //数据库内容的上传
                            UploadUtil.uploadImage(outputImage, uploadurl);        //图片的上传，时间戳的命名，支持拍照上传
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }).start();
        }


        else
            Toast.makeText(getContext(),"请选取图片",Toast.LENGTH_LONG).show();


    }


    public boolean isPhone(String inputText) {

        Pattern p = Pattern.compile("^((13[^4,\\D])" + "|(134[^9,\\D])" +
                "|(14[5,7])" +
                "|(15[^4,\\D])" +
                "|(17[3,6-8])" +
                "|(18[0-9]))\\d{8}$");
        /****Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");****/
        Matcher m = p.matcher(inputText);
        return m.matches();
    }
}

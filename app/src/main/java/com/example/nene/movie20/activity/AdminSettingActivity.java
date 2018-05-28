package com.example.nene.movie20.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.nene.movie20.R;
import com.example.nene.movie20.data.CardBean;
import com.example.nene.movie20.data.CommentBean;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AdminSettingActivity extends AppCompatActivity {
    private Intent intent;
    private LinearLayout exit_login;
    private ImageView back;
    private TimePickerView pvTime;
    private TextView admin_birth;
    private TextView admin_adddress;
    private TextView admin_sex;
    private TextView admin_nickname;
    private OptionsPickerView pvOptions;
    private CircleImageView imageView;
    private ArrayList<String> sex;
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    private BottomSheetDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //先加载数据
        getCardData();

        admin_birth = findViewById(R.id.admin_birth);
        admin_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(v);
            }
        });

        admin_adddress = findViewById(R.id.admin_address);
        admin_adddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        admin_nickname = findViewById(R.id.admin_nickname);
        admin_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameDialog();
            }
        });


        admin_sex = findViewById(R.id.admin_sex);
        admin_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });

        imageView = findViewById(R.id.user_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopueWindow();
            }
        });

        initView();
        initTime();
        initSex();
    }

    private void showPopueWindow() {
//        View popView = View.inflate(this, R.layout.popup_window, null);
//        Button bt_album = popView.findViewById(R.id.btn_pop_album);
//        Button bt_camera = popView.findViewById(R.id.btn_pop_camera);
//        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
//
//        //获取屏幕宽高
//        int weight = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels * 1/3;
//
//        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
//        popupWindow.setAnimationStyle(R.style.anim_popup_dir);
//        popupWindow.setFocusable(true);
//        //点击外部popueWindow消失
//        popupWindow.setOutsideTouchable(true);
//
//        bt_album.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent,RESULT_LOAD_IMAGE);
//            }
//        });
//
//        bt_camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                takeCamera(RESULT_CAMERA_IMAGE);
//                popupWindow.dismiss();
//            }
//        });
//        bt_cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//        //popupWindow消失屏幕变为不透明
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//                layoutParams.alpha = 1.0f;
//                getWindow().setAttributes(layoutParams);
//            }
//        });
//        //popupWindow出现屏幕变为半透明
//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.alpha = 0.5f;
//        getWindow().setAttributes(layoutParams);
//        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0,50);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK ) {
//            if (requestCode == RESULT_LOAD_IMAGE && null != data) {
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//                Cursor cursor = getContentResolver().query(selectedImage,
//                        filePathColumn, null, null, null);
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                final String picturePath = cursor.getString(columnIndex);
//                upload(picturePath);
//                cursor.close();
//            }else if (requestCode == RESULT_CAMERA_IMAGE){
//
//                SimpleTarget target = new SimpleTarget<Bitmap>() {
//
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        upload(saveMyBitmap(resource).getAbsolutePath());
//                    }
//
//                    @Override
//                    public void onLoadStarted(Drawable placeholder) {
//                        super.onLoadStarted(placeholder);
//
//                    }
//
//                    @Override
//                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                        super.onLoadFailed(e, errorDrawable);
//
//                    }
//                };
//
//                Glide.with(AdminSettingActivity.this).load(mCurrentPhotoPath)
//                        .asBitmap()
//                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                        .override(1080, 1920)//图片压缩
//                        .centerCrop()
//                        .dontAnimate()
//                        .into(target);
//            }
//        }
    }

    private void upload(String picturePath) {
//        final ProgressDialog pb= new ProgressDialog(this);
//
//        pb.setMessage("正在上传");
//        pb.setCancelable(false);
//        pb.show();
//
//        imageUpLoad(picturePath, new Response<FileUpload>() {
//
//            @Override
//            public void onSuccess(FileUpload response) {
//                super.onSuccess(response);
//                if (response.success) {
//                    myFileId = response.fileID;
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            ToastUtils.showShortToast("上传成功");
//                            pb.dismiss();
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onFaile(String e) {
//                super.onFaile(e);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        pb.dismiss();
//                        ToastUtils.showShortToast("上传失败");
//                    }
//                });
//            }
//        });
    }

//    public static void imageUpLoad(String localPath, final Response<FileUpload> callBack) {
//        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
//        OkHttpClient client = new OkHttpClient();
//
//
//        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//
//        File f = new File(localPath);
//        builder.addFormDataPart("file", f.getName(), RequestBody.create(MEDIA_TYPE_PNG, f));
//
//        final MultipartBody requestBody = builder.build();
//        //构建请求
//        final Request request = new Request.Builder()
//                .url("http://  ")//地址
//                .post(requestBody)//添加请求体
//                .build();
//
//        client.newCall(request).enqueue(new okhttp3.Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//                callBack.onFaile(e.getMessage());
//                System.out.println("上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                FileUpload resultBean = new Gson().fromJson(response.body().string(), FileUpload.class);
//                callBack.onSuccess(resultBean);
//            }
//        });

//    }

    //将bitmap转化为png格式
//    public File saveMyBitmap(Bitmap mBitmap){
//        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//        File file = null;
//        try {
//            file = File.createTempFile(
//                    UploadAccess.generateFileName(),  /* prefix */
//                    ".jpg",         /* suffix */
//                    storageDir      /* directory */
//            );
//
//            FileOutputStream out=new FileOutputStream(file);
//            mBitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
//            out.flush();
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  file;
//    }
//
//    private void takeCamera(int num) {
//
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(mContext.getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            photoFile = createImageFile();
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        Uri.fromFile(photoFile));
//            }
//        }
//
//        startActivityForResult(takePictureIntent, num);//跳转界面传回拍照所得数据
//    }
    private File createImageFile() {
//        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File image = null;
//        try {
//            image = File.createTempFile(
//                    generateFileName(),  /* prefix */
//                    ".jpg",         /* suffix */
//                    storageDir      /* directory */
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        return imageFileName;
    }

    private void showNameDialog() {
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.review_dialog, null);
        final EditText text = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button submit = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);

        View parent = (View) commentView.getParent();
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0, 0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = text.getText().toString().trim();
                admin_nickname.setText(address);
            }
        });

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!TextUtils.isEmpty(s) && s.length() > 2) {
                    submit.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    submit.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dialog.show();
    }

    private void showDialog() {
        dialog = new BottomSheetDialog(this);
        View commentView = LayoutInflater.from(this).inflate(R.layout.review_dialog, null);
        final EditText text = (EditText) commentView.findViewById(R.id.dialog_comment_et);
        final Button submit = (Button) commentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(commentView);

        View parent = (View) commentView.getParent();
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(parent);
        commentView.measure(0, 0);
        behavior.setPeekHeight(commentView.getMeasuredHeight());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = text.getText().toString().trim();
                admin_adddress.setText(address);
            }
        });

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!TextUtils.isEmpty(s) && s.length() > 2) {
                    submit.setBackgroundColor(Color.parseColor("#FFB568"));
                } else {
                    submit.setBackgroundColor(Color.parseColor("#D8D8D8"));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dialog.show();
    }

    private void initSex() {
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getPickerViewText();
                admin_sex.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);

                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .isDialog(true)
                .build();

        pvOptions.setPicker(cardItem);//添加数据


    }

    public void initTime() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                admin_birth.setText(getTime(date));
                Log.i("pvTime", "onTimeSelect");
            }

        }).build();
    }

    public String getTime(Date date){
        Log.d("getTime", "getTime: "+date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    private void getCardData() {
        int i = 1;
        cardItem.add(new CardBean(i, "男"));
        cardItem.add(new CardBean(i, "女"));
        cardItem.add(new CardBean(i, "保密"));
    }

    private void initView() {
        exit_login = findViewById(R.id.exit_login);
        exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AdminSettingActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}




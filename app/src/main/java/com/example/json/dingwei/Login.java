package com.example.json.dingwei;

import android.view.View;

import com.example.json.dingwei.base.BaseActivity;

public class Login extends BaseActivity implements View.OnClickListener {
    @Override
    public void onClick(View v) {

    }

//    @BindView(R.id.edtLoginActivity)
//    EditText edtLoginActivity;
//    @BindView(R.id.tvLoginActivityLogin)
//    TextView tvLoginActivityLogin;
//    private String edtStringUserID;
//    private long userID;
//    private boolean status;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        //第一：默认初始化
//        Bmob.initialize(this, "cab28c292ccada5f6a9740d5cdb32f77");
//        ButterKnife.bind(this);
//        tvLoginActivityLogin.setOnClickListener(this);
//        edtLoginActivity.setInputType(EditorInfo.TYPE_CLASS_PHONE);
//    }
//
//    public boolean userIDIsRegiest(){
//        BmobQuery<WeiZhiBean> query = new BmobQuery<WeiZhiBean>();
//        query.addWhereEqualTo("userID",userID);
//        query.setLimit(1);
//        query.findObjects(new FindListener<WeiZhiBean>() {
//            @Override
//            public void done(List<WeiZhiBean> list, BmobException e) {
//                if (e==null){
//                    if (list.size()>0){
//                        status = false;
//                    }else {
//                        status = true;
//                    }
//
//                }else {
//                    Log.i("aaa","失败："+e.getMessage()+","+e.getErrorCode());
//                }
//            }
//        });
//        return status;
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.tvLoginActivityLogin:
//                edtStringUserID =  edtLoginActivity.getText().toString().trim();
//                if (TextUtils.isEmpty(edtStringUserID)){
//                    ToastHelper.showShortMessage("请输入用户ID后再点击登录");
//                }else {
//
//                    userID = Long.parseLong(edtStringUserID);
//                    if ((userID == 0)){
//                        ToastHelper.showShortMessage("此用户ID已注册，请更换其他数字作为用户ID");
//                    }else {
//                        if ((userIDIsRegiest() == false)){
//                            ToastHelper.showShortMessage("此用户ID已注册，请更换其他数字作为用户ID");
//                        }else if ((userIDIsRegiest() == true)){
//                            //记录这次登陆的时间
//                            PreferenceUtil.getInstance().putLong(ConstKey.KEY_LAST_LOGIN_TIME,userID);
//                            toActivity(MainActivity.class);
//                        }
//                    }
//                }
//
//
//                break;
//        }
//
//    }
}

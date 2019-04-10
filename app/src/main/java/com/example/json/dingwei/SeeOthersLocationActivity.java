package com.example.json.dingwei;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.json.dingwei.base.BaseActivity;
import com.example.json.dingwei.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SeeOthersLocationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.edtSeeOthersLocationActivity)
    EditText edtSeeOthersLocationActivity;
    @BindView(R.id.tvSeeOthersLocationActivityChaXun)
    TextView tvSeeOthersLocationActivityChaXun;
    @BindView(R.id.llMainActivitySend)
    LinearLayout llMainActivitySend;
    @BindView(R.id.lvSeeOthersLocationActivityResult)
    ListView lvSeeOthersLocationActivityResult;
    @BindView(R.id.ivSeeOthersLocationActivityBack)
    ImageView ivSeeOthersLocationActivityBack;
    @BindView(R.id.iv_share_share_activity) ImageView ivShareShareActivity;

    private String edtStringUserID;
    private String userID;
    private List<Local> weiZhiBeanList = new ArrayList<>();
    private MyLocationAdapter myLocationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_others_loaction);
        ButterKnife.bind(this);
        edtSeeOthersLocationActivity.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        ivSeeOthersLocationActivityBack.setOnClickListener(this);
        tvSeeOthersLocationActivityChaXun.setOnClickListener(this);
        ivShareShareActivity.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSeeOthersLocationActivityBack:
                this.finish();
                break;
            case R.id.tvSeeOthersLocationActivityChaXun:
                edtStringUserID = edtSeeOthersLocationActivity.getText().toString();
                if (TextUtils.isEmpty(edtStringUserID)) {
                    ToastHelper.showShortMessage("请输入用户手机号后再点击查询");
                } else {
                    userID = edtStringUserID;
                    BmobQuery<Local> query = new BmobQuery<Local>();
                    query.addWhereEqualTo("phoneNumber", edtStringUserID);
                    query.setLimit(50);
                    query.findObjects(new FindListener<Local>() {
                        @Override
                        public void done(List<Local> list, BmobException e) {
                            if (e == null) {
                                if (list.size() > 0) {
                                    weiZhiBeanList.clear();
                                    weiZhiBeanList = list;
                                    myLocationAdapter = new MyLocationAdapter(weiZhiBeanList, SeeOthersLocationActivity.this);
                                    lvSeeOthersLocationActivityResult.setAdapter(myLocationAdapter);
                                } else {
                                    ToastHelper.showShortMessage("云服务器未存储该用户的位置信息");
                                }

                            } else {
                                ToastHelper.showShortMessage("查询失败");
                            }
                        }
                    });
                }
                break;
            case R.id.iv_share_share_activity:
                shareMyLocation();
                break;
            default:
                break;
        }
    }

    public void shareMyLocation() { /*分享查询到的用户信息*/
        if (weiZhiBeanList != null && weiZhiBeanList.size() > 0) {
            AndroidShare as = new AndroidShare(this, "查询的手机号码为：" + edtSeeOthersLocationActivity.getText().toString().trim() + "\n\n该手机号码的位置为： ：" + weiZhiBeanList.toString(), "");
            as.show();
        } else {
            ToastHelper.showShortMessage("没有位置信息，无法分享");
        }
    }
}

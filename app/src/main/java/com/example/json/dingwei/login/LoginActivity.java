package com.example.json.dingwei.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.json.dingwei.MainActivity;
import com.example.json.dingwei.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 登录
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editLoginActivityUsrName)
    TextInputEditText editLoginActivityUsrName;
    @BindView(R.id.editLoginActivityPassWord)
    TextInputEditText editLoginActivityPassWord;
    @BindView(R.id.btnLoginActivityLogin)
    AppCompatButton btnLoginActivityLogin;
    @BindView(R.id.tvLoginActivityRegister)
    TextView tvLoginActivityRegister;
    @BindView(R.id.llLoginActivityAll)
    LinearLayout llLoginActivityAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Bmob.initialize(this, "db204405c95a2dc850f65e78c6ec9457");

        btnLoginActivityLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoginOnclick();  /* 64*/
            }
        });

        tvLoginActivityRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class); /*跳转界面*/
                startActivity(intent);
            }
        });

    }

    public void setLoginOnclick() {
        String username = editLoginActivityUsrName.getText().toString();
        String password = editLoginActivityPassWord.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) { /* 判断是否为空*/
            BmobUser userInfoBean = new BmobUser();
            userInfoBean.setUsername(username);
            userInfoBean.setPassword(password);
            userInfoBean.login(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser userInfoBean, BmobException e) {
                    if (e == null) { /*是否异常*/
                        BmobUser currentUser = BmobUser.getCurrentUser(BmobUser.class);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "登录失败 : " + e, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }
}

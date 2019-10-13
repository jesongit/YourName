package xin.jeson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private InputMethodManager imm;
    private EditText mEdtUsername, mEdtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEdtUsername = findViewById(R.id.mEdtUsername);
        mEdtPassword = findViewById(R.id.mEdtPassword);

        // 优化光标闪烁问题，只有点击后才闪烁
        mEdtUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.mEdtUsername) {
                    mEdtUsername.setCursorVisible(true);
                    mEdtUsername.requestFocus();
                    mEdtUsername.findFocus();
                }
            }
        });
        mEdtUsername.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    mEdtPassword.callOnClick();
                }
                return false;
            }
        });
        mEdtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                mEdtUsername.setCursorVisible(true);
                if(event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    onClick(v);
                }
                return false;
            }
        });

        findViewById(R.id.mBtnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                // 防止返回
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    // 单击父控件收起键盘
    public void onClick(View v) {
        if (null == imm) {
            imm = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}

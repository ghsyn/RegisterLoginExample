package com.example.registerloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_id, et_password, et_name, et_age;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // activity 시작 시 처음으로 실행되는 생명주기
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // 회원 정보 값 찾아주기
        et_id = findViewById(R.id.et_id);
        et_password = findViewById(R.id.et_password);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);

        // 회원가입 버튼 클릭 시 실행
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 사용자가 입력한 정보값들을 String으로 저장해 get함
                String userID = et_id.getText().toString();
                String userPassword = et_password.getText().toString();
                String userName = et_name.getText().toString();
                int userAge = Integer.parseInt(et_age.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {

                            //회원가입에 성공할 경우
                           JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else { // 회원가입에 실패한 경우
                                Toast.makeText(getApplicationContext(), "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


            }
        });

    }
}
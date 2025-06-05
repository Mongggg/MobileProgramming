package com.example.week12;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginFragment3 extends Fragment {
    EditText tvId ;
    EditText tvPwd;
    Button btnLogin;

    /////// SharedPreferences : 다음 추가
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_login, container, false);
        tvId = (EditText) view.findViewById(R.id.tvId);
        tvPwd = (EditText) view.findViewById(R.id.tvPwd);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                //String url = "http://172.30.1.100:8080/myapp/login.jsp?id=kim&pwd=1234";
                String url = "http://10.11.83.3:8080/myapp/login_db.jsp?id="+tvId.getText().toString()+"&pwd="+tvPwd.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            public void onResponse(String response) {
                                Toast.makeText(getContext(), response.trim(), Toast.LENGTH_SHORT).show();
                                /////// SharedPreferences : LoginFragment에 다음 추가
                                preferences = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("name", response.trim());
                                editor.commit();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                requestQueue.add(stringRequest);


/*                if (tvId.getText().toString().equals(tvPwd.getText().toString())) {
                    Toast.makeText(getContext(), "로그인성공", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getContext(), "오류: ", Toast.LENGTH_SHORT).show();*/
            }
        });
        return view;
    }
}
package com.example.week12;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RegisterFragment extends Fragment {
    EditText rgId ;
    EditText rgPwd;
    EditText rgName;
    EditText rgBirth_year;
    Button btnRegister;

    SharedPreferences preferences;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        rgId = (EditText) view.findViewById(R.id.rgId);
        rgPwd = (EditText) view.findViewById(R.id.rgPwd);
        rgName = (EditText) view.findViewById(R.id.rgName);
        rgBirth_year = (EditText) view.findViewById(R.id.rgBirth_year);
        btnRegister = (Button) view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                String url = "http://10.11.83.3:8080/myapp/register_db.jsp?id=" + rgId.getText().toString()
                        + "&pwd=" + rgPwd.getText().toString()
                        + "&name=" + rgName.getText().toString()
                        + "&birth_year=" + rgBirth_year.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getContext(), response.trim(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                requestQueue.add(stringRequest);
            }
        });
        return view;
    }
}

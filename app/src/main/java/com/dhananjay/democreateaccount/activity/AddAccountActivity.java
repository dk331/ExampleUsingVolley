package com.dhananjay.democreateaccount.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dhananjay.democreateaccount.R;
import com.dhananjay.democreateaccount.adapters.AccountListAdapter;
import com.dhananjay.democreateaccount.models.Account;
import com.dhananjay.democreateaccount.models.AccountResponse;
import com.dhananjay.democreateaccount.utils.Constants;
import com.dhananjay.democreateaccount.utils.MyVolleySingleton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AddAccountActivity extends AppCompatActivity {

    private AccountResponse accountResponse;
    private ArrayList<Account> accountArrayList;
    private AccountListAdapter accountListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        TextView text_cancel = findViewById(R.id.text_cancel);
        AppCompatButton btn_continue = findViewById(R.id.btn_continue);

        accountArrayList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        accountListAdapter = new AccountListAdapter(accountArrayList);
        recyclerView.setAdapter(accountListAdapter);

        text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddAccountActivity.this,
                    getString(R.string.success),
                    Toast.LENGTH_SHORT).show();
                //finish();

                startActivity(new Intent(getApplicationContext(),
                    NavigationDrawerActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        sendAndRequestResponse(this);

    }

    private void sendAndRequestResponse(final Context context) {

        //String Request initialized
        StringRequest stringRequest = new StringRequest(
            Request.Method.GET,
            Constants.mockyDataUrl,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Gson gson = new Gson();
                    accountResponse = gson.fromJson(response,
                        AccountResponse.class);
                    accountArrayList = accountResponse.getAccount();
                    accountListAdapter.updateList(accountArrayList);
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(getResources().getString(R.string.add_account_volley),
                    String.format("%s%s", getResources()
                            .getString(R.string.error_is),
                        error.toString()));

                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet..." +
                        "Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "The server could not be found." +
                        "Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet..." +
                        "Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! " +
                        "Please try again after some time!!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! " +
                        "Please check your internet connection.";
                }

                if (message != null) {
                    Toast.makeText(AddAccountActivity.this, message,
                        Toast.LENGTH_SHORT).show();
                }

                //sendAndRequestResponse(context);
            }
        });

        stringRequest.setRetryPolicy(
            new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                1,
                1.0f));


        MyVolleySingleton.getInstance(context.getApplicationContext())
            .addToRequestQueue(stringRequest);
    }
}

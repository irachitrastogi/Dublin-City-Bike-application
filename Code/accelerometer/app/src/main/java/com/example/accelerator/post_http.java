package com.example.accelerator;
import android.util.Log;

import java.io.IOException;

public class post_http {

    public static final okhttp3.MediaType JSON
            = okhttp3.MediaType.get("application/json; charset=utf-8");

    okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();

    public String http_post_message(String url, String json) throws IOException {
        okhttp3.RequestBody body = okhttp3.RequestBody.create(JSON, json);
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (okhttp3.Response response = client.newCall(request).execute()) {
            //Log.d("server_response", response.body().string());
            String return_response = response.body().string();
            Log.d("server_response", return_response);
            //String return_response= new String(response.body());
            Log.d("server_response2", return_response);
            return return_response;
        }
    }
}

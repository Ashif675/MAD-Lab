import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String HACKER_NEWS_API_URL = "https://hacker-news.firebaseio.com/v0/";

    private OkHttpClient client;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();
        gson = new Gson();

        fetchTopStories();
    }

    private void fetchTopStories() {
        Request request = new Request.Builder()
                .url(HACKER_NEWS_API_URL + "topstories.json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Failed to fetch top stories: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    int[] storyIds = gson.fromJson(responseBody, int[].class);
                    fetchStoryDetails(storyIds);
                } else {
                    Log.e(TAG, "Failed to fetch top stories: " + response.code());
                }
            }
        });
    }

    private void fetchStoryDetails(int[] storyIds) {
        for (int id : storyIds) {
            Request request = new Request.Builder()
                    .url(HACKER_NEWS_API_URL + "item/" + id + ".json")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    Story story = gson.fromJson(responseBody, Story.class);
                    Log.d(TAG, "ID: " + story.getId() + ", Title: " + story.getTitle());
                } else {
                    Log.e(TAG, "Failed to fetch story details: " + response.code());
                }
            } catch (IOException e) {
                Log.e(TAG, "Failed to fetch story details: " + e.getMessage());
            }
        }
    }

    private static class Story {
        @SerializedName("id")
        private int id;
        @SerializedName("title")
        private String title;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }
}

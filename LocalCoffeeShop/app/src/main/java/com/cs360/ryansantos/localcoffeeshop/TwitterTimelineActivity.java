package com.cs360.ryansantos.localcoffeeshop;

import android.app.ListActivity;
import android.os.Bundle;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;


public class TwitterTimelineActivity extends ListActivity {

    // Displays the user's twitter timeline
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("Starbucks")
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);

    }

}

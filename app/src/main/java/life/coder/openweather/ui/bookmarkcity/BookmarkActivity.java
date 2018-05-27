package life.coder.openweather.ui.bookmarkcity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import life.coder.openweather.R;
import life.coder.openweather.utils.OWHelper;

public class BookmarkActivity extends AppCompatActivity {

    private LinearLayout ltMainContainer;

    private long sunset = 0;
    private long sunrise = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_activity);
        ltMainContainer = findViewById(R.id.lt_main_container);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            sunrise = intent.getExtras().getLong("sunrise");
            sunset = intent.getExtras().getLong("sunset");

            int backgroundId = OWHelper.getBackground(sunrise, sunset);
            ltMainContainer.setBackgroundResource(backgroundId);
        }
    }
}

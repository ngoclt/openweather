package life.coder.openweather.ui.bookmarkcity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import life.coder.openweather.R;
import life.coder.openweather.api.model.OWCityWeather;
import life.coder.openweather.ui.BaseActivity;
import life.coder.openweather.utils.OWCallback;
import life.coder.openweather.utils.OWHelper;

public class BookmarkActivity extends BaseActivity implements OWCallback, Observer<OWCityWeather> {

    static String TAG = "BookmarkActivity";

    static String SHARED_PREFS_FILE = "BookmarkedCities";
    static String CITIES = "Cities";

    private LinearLayout ltMainContainer;
    private EditText etSearchCity;
    private Button btnAdd;

    private BookmarkViewModel viewModel;

    private long sunset = 0;
    private long sunrise = 0;

    private List<String> cities;

    private SwipeRefreshLayout ltRefresh;
    private RecyclerView rcForecast;

    private BookmarkAdapter adapter;
    private List<OWCityWeather> weathers;

    private Boolean isAdding = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark_activity);
        ltMainContainer = findViewById(R.id.lt_main_container);
        btnAdd = findViewById(R.id.btn_add);

        etSearchCity = findViewById(R.id.et_search_city);
        rcForecast = findViewById(R.id.rc_forecast);
        ltRefresh = findViewById(R.id.lt_refresh);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        rcForecast.setLayoutManager(mLinearLayoutManager);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);

        Set<String> set = prefs.getStringSet(CITIES, new HashSet<>());
        cities = new ArrayList<>(set);
        weathers = new ArrayList<>();
        adapter = new BookmarkAdapter(weathers, this);
        rcForecast.setAdapter(adapter);
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

            viewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);

            ltRefresh.setRefreshing(true);
            refreshData();

            ltRefresh.setOnRefreshListener(() ->
                    refreshData()
            );
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideKeyboard();
    }

    private void refreshData() {
        // Clear all data here and reload
        weathers.clear();
        adapter.setData(weathers);
        adapter.notifyDataSetChanged();

        for (String cityName : cities) {
            observeViewModel(cityName, viewModel);
        }
    }

    private void observeViewModel(String query, BookmarkViewModel viewModel) {

        viewModel.getOwCityWeatherLiveData(query, this).observe(this,
                owForecast -> {
                    if (owForecast != null) {
                        insertCityWeather(owForecast);
                    }
                });
    }

    private void insertCityWeather(OWCityWeather owCityWeather) {
        if (!owCityWeather.getName().isEmpty()) {
            if (isAdding) {
                if (cities.contains(owCityWeather.getName())) {
                    showAlert("ERROR", "City is already added.");
                    ltRefresh.setRefreshing(false);
                    return;
                }

                addToSharedPreferences(owCityWeather.getName());
                isAdding = false;
                btnAdd.setEnabled(true);
            }
            weathers.add(owCityWeather);
            adapter.notifyItemInserted(weathers.size() - 1);
            if (cities.size() == weathers.size()) {
                ltRefresh.setRefreshing(false);
            }
        }
    }

    private void addToSharedPreferences(String city) {
        cities.add(city);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putStringSet(CITIES, new HashSet<>(cities));
        editor.commit();
    }

    private void hideKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(@Nullable String error) {
        Log.e(TAG, error);
        if (isAdding) {
            isAdding = false;
            btnAdd.setEnabled(true);
            ltRefresh.setRefreshing(false);

            showAlert("Error", error);
        }
    }

    @Override
    public void onChanged(@Nullable OWCityWeather owCityWeather) {
        // No need to handle for data change
    }

    public void clickOnBackBTN(View target) {
        onBackPressed();
    }

    public void clickOnAddBTN(View target) {
        if (isAdding) {
            return;
        }

        isAdding = true;
        btnAdd.setEnabled(false);

        hideKeyboard();

        ltRefresh.setRefreshing(true);
        String city = etSearchCity.getText().toString();
        observeViewModel(city, viewModel);
        etSearchCity.setText("");
    }
}

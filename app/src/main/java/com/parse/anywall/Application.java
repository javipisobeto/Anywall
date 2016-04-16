package com.parse.anywall;

import android.content.Context;
import android.content.SharedPreferences;

import com.parse.Parse;
import com.parse.ParseObject;
import android.support.multidex.MultiDexApplication;
import android.support.multidex.MultiDex;

public class Application extends android.app.Application {

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }
  // Debugging switch
  public static final boolean APPDEBUG = false;

  // Debugging tag for the application
  public static final String APPTAG = "AnyWall";

  // Used to pass location from MainActivity to PostActivity
  public static final String INTENT_EXTRA_LOCATION = "location";

  // Key for saving the search distance preference
  private static final String KEY_SEARCH_DISTANCE = "searchDistance";

  private static final float DEFAULT_SEARCH_DISTANCE = 250.0f;

  private static SharedPreferences preferences;

  private static ConfigHelper configHelper;

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();

    ParseObject.registerSubclass(AnywallPost.class);
    Parse.initialize(this, "IbXZFwvwIVOkm6DapW7MC2zBf39p27MfvhS0tMdm",
            "M9wJgBR17yRdP1Xw7PWOwdDHVYeBBrVAxHhzftmf");

    preferences = getSharedPreferences("com.parse.anywall", Context.MODE_PRIVATE);

    configHelper = new ConfigHelper();
    configHelper.fetchConfigIfNeeded();
  }

  public static float getSearchDistance() {
    return preferences.getFloat(KEY_SEARCH_DISTANCE, DEFAULT_SEARCH_DISTANCE);
  }

  public static ConfigHelper getConfigHelper() {
    return configHelper;
  }

  public static void setSearchDistance(float value) {
    preferences.edit().putFloat(KEY_SEARCH_DISTANCE, value).commit();
  }

}

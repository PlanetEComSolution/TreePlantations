package com.planet.treeplantations.Sherd_pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

public class Pref {
    private Context myContext;
    public Pref(Context context) {
        myContext = context;
    }
    public String loadPreferences_fragment(String key, Fragment fragment) {
        String strValue = "";
        @SuppressWarnings("static-access")
        SharedPreferences preferences = fragment.getActivity().getSharedPreferences("Tree_Plantations_Pref", myContext.MODE_PRIVATE);
        strValue = preferences.getString(key, "");
        return strValue;
    }

    public String loadPreferences(String key) {
        String strValue = "null";
        @SuppressWarnings("static-access")
        SharedPreferences preferences = myContext.getSharedPreferences("Tree_Plantations_Pref", myContext.MODE_PRIVATE);
        strValue = preferences.getString(key, "");
        return strValue;
    }

    public void savePreferences(String key, String value) {
        @SuppressWarnings("static-access")
        SharedPreferences preferences = myContext.getSharedPreferences("Tree_Plantations_Pref", myContext.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // private GeneralHelper genralHelper;

    // genralHelper = new GeneralHelper(myContext);
    //  genralHelper.savePreferences("UserId", user.getString("UserId").toString());


}

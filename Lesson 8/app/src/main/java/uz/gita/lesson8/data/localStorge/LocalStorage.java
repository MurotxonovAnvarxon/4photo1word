package uz.gita.lesson8.data.localStorge;

import android.content.Context;
import android.content.SharedPreferences;

import uz.gita.lesson8.app.App;

public class LocalStorage {
    private static LocalStorage storage;
    private final SharedPreferences preferences;

    public LocalStorage() {
        preferences = App.context.getSharedPreferences("PREGERENCE", Context.MODE_PRIVATE);
    }

    public static LocalStorage getStorage() {
        if (storage == null) {
            storage = new LocalStorage();

        }
        return storage;
    }



//    public int getCoin() {
//        return preferences.getInt("coin", 0);
//    }
//
//    public void setCoin(int value) {
//        preferences.edit().putInt("coin", value).apply();
//    }

    public void saveQuestionPos(int pos) {
        preferences.edit().putInt("POSITION", pos).apply();
    }

    public int getQuestionPos() {
        return preferences.getInt("POSITION", 0);
    }
}

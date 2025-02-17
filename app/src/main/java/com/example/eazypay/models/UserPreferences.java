package com.example.eazypay.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

public class UserPreferences {
    private static final String PREF_NAME = "secure_prefs";
    private SharedPreferences securePrefs;

    public UserPreferences(Context context) {
        try {
            KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                    "_androidx_security_master_key_",
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setKeySize(256)
                    .build();

            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyGenParameterSpec(spec)
                    .build();

            securePrefs = EncryptedSharedPreferences.create(
                    context,
                    PREF_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUserCredentials(String email, String token) {
        securePrefs.edit()
                .putString("user_email", email)
                .putString("user_token", token)
                .apply();
    }

    public String getUserEmail() {
        return securePrefs.getString("user_email", null);
    }

    public String getUserToken() {
        return securePrefs.getString("user_token", null);
    }

    public void clearUserData() {
        securePrefs.edit().clear().apply();
    }
}

package com.svalero.gameshop_aa1_multimedia.view;

import static com.svalero.gameshop_aa1_multimedia.db.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.gameshop_aa1_multimedia.R;
import com.svalero.gameshop_aa1_multimedia.db.GameShopDatabase;
import com.svalero.gameshop_aa1_multimedia.domain.Client;
import com.svalero.gameshop_aa1_multimedia.domain.Preferences;

public class LoginActivity extends AppCompatActivity {
    private EditText etLoginUsername;
    private EditText etLoginPassword;
    private Preferences preferences;
    private String username;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginPassword = findViewById(R.id.etLoginPassword);
        etLoginUsername = findViewById(R.id.etLoginUsername);

        checkExternalStoragePermission();

        final GameShopDatabase db = Room.databaseBuilder(this, GameShopDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();

        try {
            preferences = db.getPreferencesDAO().getPreference();
            if(preferences != null){
                if(preferences.isAutoLogin()){
                    String user = preferences.getUsername();
                    String pass = preferences.getPassword();

                    Client client = db.getClientDAO().login(user, pass);
                    if(client != null){
                        Toast.makeText(this, R.string.autoLoginCorrect, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("client_id", client.getId());
                        intent.putExtra("clientUsername", client.getUsername());
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, R.string.autoLoginIncorrect, Toast.LENGTH_LONG).show();
                    }

                }
            }
        } catch (SQLiteConstraintException sce){
            Log.i("onCreate", "Error loading preferences");
        } finally {
            db.close();
        }
    }

    public void login(View view){
        String username = etLoginUsername.getText().toString();
        String password = etLoginPassword.getText().toString();

        if(username.isEmpty()){
            Toast.makeText(this,R.string.userEmty, Toast.LENGTH_LONG).show();
        }

        final GameShopDatabase db = Room.databaseBuilder(this, GameShopDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();

        try{
            Client client = db.getClientDAO().login(username, password);
            if(client != null){
                Toast.makeText(this, R.string.loginCorrect, Toast.LENGTH_LONG).show();
                if(preferences != null){
                    Intent intent;
                    if(preferences.isAutoMarker()){
                        intent = new Intent(this, FavouritesView.class);
                        intent.putExtra("client_id", id);
                        intent.putExtra("clientUsername", username);
                    } else {
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("client_id", client.getId());
                        intent.putExtra("clientUsername", client.getUsername());
                    }
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("client_id", client.getId());
                    intent.putExtra("clientUsername", client.getUsername());
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, R.string.autoLoginIncorrect, Toast.LENGTH_LONG).show();
            }
        } catch (SQLiteConstraintException sce){
            Snackbar.make(etLoginUsername, R.string.wrong, BaseTransientBottomBar.LENGTH_LONG).show();
        } finally {
            db.close();
        }


    }

    public void create(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterClient.class);
        startActivity(intent);
    }

    private void checkExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},225);
            }
        }
    }
}
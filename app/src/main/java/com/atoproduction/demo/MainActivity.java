package com.atoproduction.demo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BlankFragment blankFragment = new BlankFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, blankFragment).commit();

        Intent intent = new Intent(this, MainActivity2.class);
        ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                            String data = result.getData().getStringExtra("Data");
                            Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivityForResult.launch(intent);
//                getSupportFragmentManager().beginTransaction().add(R.id.container, new BlankFragment()).addToBackStack(null).commit();
                getSupportFragmentManager().beginTransaction().remove(blankFragment).commit();
            }
        });
    }
}

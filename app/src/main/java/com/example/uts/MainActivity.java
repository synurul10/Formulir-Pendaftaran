package com.example.uts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editT1;
    EditText editT2;
    EditText editT3;
    RadioButton rButton1;
    RadioButton rButton2;
    CheckBox checkBox;
    CheckBox checkBox2;
    CheckBox checkBox3;
    View btnToast;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Input fields
        editT1 = findViewById(R.id.editT1);
        editT2 = findViewById(R.id.editT2);
        editT3 = findViewById(R.id.editT3);

        // Gender Radio Buttons
        rButton1 = findViewById(R.id.rButton1);
        rButton2 = findViewById(R.id.rButton2);

        // Hobbies Checkboxes
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);

        // Adjust for system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Button for Toast
        btnToast = findViewById(R.id.buttonToast);
        btnToast.setOnClickListener(view -> showToastWithDetails());
    }

    public void buttonToast(View view) {
        // Determine selected gender
        String gender = rButton1.isChecked() ? rButton1.getText().toString() : rButton2.getText().toString();

        // Collect hobbies
        StringBuilder hobbies = new StringBuilder();
        if (checkBox.isChecked()) hobbies.append(checkBox.getText().toString()).append(", ");
        if (checkBox2.isChecked()) hobbies.append(checkBox2.getText().toString()).append(", ");
        if (checkBox3.isChecked()) hobbies.append(checkBox3.getText().toString());

        // Remove trailing comma and space if present
        if (hobbies.length() > 0 && hobbies.charAt(hobbies.length() - 2) == ',') {
            hobbies.setLength(hobbies.length() - 2);
        }

        // Create intent to start HasilActivity
        Intent il = new Intent(MainActivity.this, HasilActivity.class);
        il.putExtra("nama", editT1.getText().toString());
        il.putExtra("email", editT2.getText().toString());
        il.putExtra("nomortelepon", editT3.getText().toString());
        il.putExtra("jeniskelamin", gender);
        il.putExtra("hobby", hobbies.toString());

        startActivity(il);
    }

    private void showToastWithDetails() {
        // Retrieve input values
        String name = editT1.getText().toString();
        String email = editT2.getText().toString();
        String phoneNumber = editT3.getText().toString();
        String gender = rButton1.isChecked() ? rButton1.getText().toString() : rButton2.getText().toString();

        // Collect selected hobbies
        StringBuilder hobbies = new StringBuilder();
        if (checkBox.isChecked()) hobbies.append(checkBox.getText().toString()).append(", ");
        if (checkBox2.isChecked()) hobbies.append(checkBox2.getText().toString()).append(", ");
        if (checkBox3.isChecked()) hobbies.append(checkBox3.getText().toString());

        // Remove trailing comma and space if present
        if (hobbies.length() > 0 && hobbies.charAt(hobbies.length() - 2) == ',') {
            hobbies.setLength(hobbies.length() - 2);
        }

        // Create the message for the Toast
        String toastMessage = "Nama: " + name +
                "\nEmail: " + email +
                "\nNo Telepon: " + phoneNumber +
                "\nJenis Kelamin: " + gender +
                "\nHobby: " + hobbies.toString();

        // Show the Toast
        Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
    }
}

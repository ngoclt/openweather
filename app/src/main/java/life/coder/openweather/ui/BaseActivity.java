package life.coder.openweather.ui;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    AlertDialog.Builder builder;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showAlert(String title, String message) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(title.toUpperCase())
                .setMessage(message.toUpperCase())
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        builder = null;
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}

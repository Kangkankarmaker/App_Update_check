package kangkan.developer.app_update_check;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements update_helper.onUpdatteCheckListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        update_helper.with(this)
                .onUpdateCheck(this)
                .check();


    }

    @Override
    public void onUpdatteCheckListener(final String urlApp) {

        AlertDialog alertDialog=new AlertDialog.Builder(this)
                .setTitle("Update")
                .setMessage("Update your app from play store")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, urlApp, Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        alertDialog.show();
    }
}

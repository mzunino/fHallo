package uy.dazapps.fotohalloween;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class FotoHalloween extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_halloween);

        // Create the layout
        RelativeLayout layout = new RelativeLayout(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);


        Button btnAction = (Button)findViewById(R.id.btnFoto);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    Uri output = Uri.fromFile(new File(name));
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, output);


                startActivityForResult(intent,0);
            }
        });
    }


    /**
     * Funci�n que se ejecuta cuando concluye el intent en el que se solicita una imagen
     * ya sea de la c�mara o de la galer�a
     */
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /**
         * Si se reciben datos en el intent tenemos una vista previa (thumbnail)
         */
        if (data != null) {
            /**
             * En el caso de una vista previa, obtenemos el extra �data� del intent y
             * lo mostramos en el ImageView
             */
            if (data.hasExtra("data")) {


                Intent intent = new Intent(this, EditActivity.class);
                intent.putExtra("imagenPasar", data.getParcelableExtra("data"));
                startActivityForResult(intent, 0);

            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_foto_halloween, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

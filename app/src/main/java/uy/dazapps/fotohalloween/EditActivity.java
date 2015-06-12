package uy.dazapps.fotohalloween;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uy.dazapps.fotohalloween.util.ZoomView;

import static android.graphics.Bitmap.Config.ARGB_8888;


public class EditActivity extends ActionBarActivity {

    List<Drawable> listaDibujos;
    HashMap<View, Delta> deltas;

    private RelativeLayout gridImgsEditar;


    @Override
    public MenuInflater getMenuInflater() {
        return super.getMenuInflater();
    }

    public static Bitmap rotateGear(Bitmap source, int angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        matrix.invert(matrix);
        Bitmap foto = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

//        return getRoundedCornerBitmap(foto);
        return foto;

    }


    private View obtenerBackground(Bundle bundle) {

        Bitmap fotoBase = rotateGear((Bitmap) bundle.get("imagenPasar"), 90);

        Drawable clone = new BitmapDrawable(fotoBase).getConstantState().newDrawable();
        clone.setBounds(0, 0, 2500, 1600);

        ZoomView imBkg = new ZoomView(getApplicationContext(), clone);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(2500, 1600);
        imBkg.setLayoutParams(layoutParams);

        imBkg.setBackground(clone);

//        imBkg.setOnTouchListener(crearListener());
//
//            imBkg.setMaxWidth(,fotoBase.getWidth(),fotoBase.getHeight()));
        return imBkg;



    }

    private View crearImageView(Drawable d, int w, int h) {

//        Drawable d = new BitmapDrawable(fotoBase);
        Drawable clone = d.getConstantState().newDrawable();
        clone.setBounds(0, 0, w, h);

        ImageView imBkg = new ImageView(getApplicationContext());
        imBkg.setBackground(clone);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(w, h);
        imBkg.setLayoutParams(layoutParams);

        imBkg.setBackground(clone);

        imBkg.setOnTouchListener(crearListener());
//
//            imBkg.setMaxWidth(,fotoBase.getWidth(),fotoBase.getHeight()));
        return imBkg;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        deltas = new HashMap<>();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        // Inicializamos la lista de dibujos que voy a estampar sobre la foto base
        listaDibujos = new ArrayList<Drawable>();

        Bundle bundle = getIntent().getExtras();

        if (bundle.get("imagenPasar")!= null) {

//            fotoBase = ((Bitmap)bundle.get("imagenPasar")).copy(Bitmap.Config.ARGB_8888,true);


            gridImgsEditar = (RelativeLayout)findViewById(R.id.edit_foto_layout);

            gridImgsEditar.addView(obtenerBackground( bundle ));

        }

        final ImageButton btnAction = (ImageButton)findViewById(R.id.imgEdicion);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                GridLayout iv = (GridLayout)findViewById(R.id.imagenHalloween);
//
//                iv.
//                ImageView img = new ImageView(v.getContext());
//
//                img.

//                try {
//
                    ImageButton iv = (ImageButton)findViewById(R.id.imgEdicion);


                    gridImgsEditar.addView(crearImageView(iv.getBackground(), 200, 200));

//                    Canvas lienzo = new Canvas(bitmap);
//                    //
//                    Drawable drawable1 = new BitmapDrawable(fotoBase);
//                    drawable1.setBounds(0, 0, fotoBase.getWidth(), fotoBase.getHeight());
//                    drawable1.draw(lienzo);
//
//                    // Agregar imagen seleccionada al view de la imagen principal
//
//                    ImageButton imgBtn = (ImageButton) findViewById(R.id.imgEdicion);
//                    Drawable d2 = imgBtn.getBackground();
////                    d2.setBounds(0,0,fotoBase.getWidth(),fotoBase.getHeight());
//                    listaDibujos.add(d2);
//                    //
//                    //
//                    //                Canvas lienzo = new Canvas(fotoBase);
//                    //
//                    for (Drawable d : listaDibujos) {
//
//                        d.setBounds(0, 0, 100, 100); // Seteo donde quiero que aparezca por primera vez
//                        d.draw(lienzo); // Dibujo sobre el lienzo
//
//                    }
//                    ImageView iv = (ImageView) findViewById(R.id.imagenHalloween);
//                    iv.setImageBitmap(bitmap);
//
//
//                } catch (Exception e) {
//                    Log.e("EXCEPTION", e.getMessage(), e);
//                }

            }
        });



    }



    public View.OnTouchListener crearListener(){

        ImageView view = new ImageView(this);

        //Añadimos el Listener de la clase
//        view.setOnTouchListener(new View.OnTouchListener() {
        View.OnTouchListener listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //Recogemos las coordenadas del dedo
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();

                Delta delta = deltas.get(view);
                if(delta == null ){
                    delta = new Delta();
                }


                final int action = event.getAction();
                final int fingersCount = event.getPointerCount();



//                if ((action == MotionEvent.ACTION_DOWN) && (fingersCount == 2)) {
//
//                    if(event.get
////                    if(keyCode== KeyEvent.KEYCODE_DPAD_UP){
//                    // zoom in
////                        zoomControler+=10;
//                    view.getLayoutParams().width += 10; //setBounds(new Float(this.getX()).intValue(), new Float(this.getY()).intValue(),getWidth()+10, this.getHeight()+10);
//                    view.getLayoutParams().height += 10;
////                    }
////                    if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN){
////                        // zoom out
////                        zoomControler-=10;
////                        image.setBounds(new Float(this.getX()).intValue(), new Float(this.getY()).intValue(),getWidth()-10, this.getHeight()-10);
////                    }
////                    if(zoomControler<10){
////                        zoomControler=10;
////                        image.setBounds(new Float(this.getX()).intValue(), new Float(this.getY()).intValue(),getWidth(), this.getHeight());
////                    }
//                    return true;
//                }


                //Dependiendo de la accion recogida..
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    //Al tocar la pantalla
                    case MotionEvent.ACTION_DOWN:
                        //Recogemos los parametros de la imagen que hemo tocado
                        RelativeLayout.LayoutParams Params =
                                (RelativeLayout.LayoutParams) view.getLayoutParams();
                        delta.xDelta = X - Params.leftMargin;
                        delta.yDelta = Y - Params.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        //Al levantar el dedo simplemento mostramos un mensaje
//                        Toast.makeText(this, "Soltamos", Toast.LENGTH_LONG).show();
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        //No hace falta utilizarlo
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        //No hace falta utilizarlo
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //Al mover el dedo vamos actualizando
                        //los margenes de la imagen para
                        //crear efecto de arrastrado
                        RelativeLayout.LayoutParams layoutParams =
                                (RelativeLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = X - delta.xDelta;
                        layoutParams.topMargin = Y - delta.yDelta;
                        //Qutamos un poco de margen para
                        //que la imagen no se deforme
                        //al llegar al final de la pantalla y pueda ir más allá
                        //probar también el codigo omitiendo estas dos líneas
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        //le añadimos los nuevos
                        //parametros para mover la imagen
                        view.setLayoutParams(layoutParams);
                        break;
                }
                //Se podría decir que 'dibujamos'
                //la posición de la imagen en el marco.
//                marco.invalidate();
                deltas.put(view, delta);

                return true;
            }
        };

        return listener;

    }


    public Bitmap mezclarImagenes(Drawable drawable1, Drawable drawable2, float x, float y, int w, int h ){

        Bitmap bitmap =  Bitmap.createBitmap(w, h, ARGB_8888);
        Canvas c = new Canvas(bitmap);


//            Drawable drawable1 = new BitmapDrawable(img1);
//            Drawable drawable2 = new BitmapDrawable(img2);


        drawable1.setBounds(0, 0, 100, 100);
//            drawable2.setHotspot(x,y);

        drawable1.draw(c);
        drawable2.draw(c);

        return bitmap;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_edit, menu);
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

    private class Delta {

        public Integer xDelta = 0;
        public Integer yDelta = 0;
    }
}

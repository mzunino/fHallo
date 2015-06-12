package uy.dazapps.fotohalloween.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by mauricio on 08/06/15.
 */
public class ZoomView extends View {

        public Drawable image;
        ImageButton img,img1;
        private int zoomControler=50;

        public ZoomView(Context context, Drawable image){
            super(context);

            this.image = image;
//            image=context.getResources().getDrawable(R.drawable.j);
            //image=context.getResources().getDrawable(R.drawable.icon);

            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

//            //here u can control the width and height of the images........ this line is very important
//            image.setBounds((getWidth()/2)-zoomControler, (getHeight()/2)-zoomControler, (getWidth()/2)+zoomControler, (getHeight()/2)+zoomControler);
//            image.draw(canvas);
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {

            if(keyCode== KeyEvent.KEYCODE_DPAD_UP){
                // zoom in
                zoomControler+=10;
                image.setBounds(new Float(this.getX()).intValue(), new Float(this.getY()).intValue(),getWidth()+10, this.getHeight()+10);
            }
            if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN){
                // zoom out
                zoomControler-=10;
                image.setBounds(new Float(this.getX()).intValue(), new Float(this.getY()).intValue(),getWidth()-10, this.getHeight()-10);
            }
            if(zoomControler<10){
                zoomControler=10;
                image.setBounds(new Float(this.getX()).intValue(), new Float(this.getY()).intValue(),getWidth(), this.getHeight());
            }

            invalidate();
            return true;
        }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }
}


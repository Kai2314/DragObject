package com.example.kai.myapplication;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;                    //bitmap 設置圖片尺寸，避免 記憶體溢出 OutOfMemoryError的優化方法
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import static com.example.kai.myapplication.R.id.imageView1;


public class MainActivity extends Activity {
    private ImageView img;  //ImageView類別
    private int intScreenX, intScreenY;           //宣告儲存螢幕的解析度變數
    private int picturesizeH, picturesizeW;       //宣告圖片大小
    @Override
    protected void onCreate(Bundle savedInstanceState) {   //當 Android 應用程式啟動、換到背景等待、關閉時，都會用到 「savedInstanceState」 這個實體來處理記憶體相關的事宜。
        super.onCreate(savedInstanceState);                //類別的上層類別(Activity)
        setContentView(R.layout.activity_main);            //Activity 會呼叫它用來設定 View 的 「setContentView」 方法，並傳入想引用的 XML 描述文件。
        img =(ImageView)findViewById(R.id.imageView1);
        img.setOnTouchListener(imgListener);              //按下的事件

        DisplayMetrics dis = new DisplayMetrics();             // DisplayMetrics類別取得手機螢幕尺寸大小，以改變控制元件
        getWindowManager().getDefaultDisplay().getMetrics(dis);   /* 取得螢幕解析像素 */

        intScreenX = dis.widthPixels;
        intScreenY = dis.heightPixels;


        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.ab1);      //產生縮圖要利用 BitmapFactory 類別來處理      //res	The resources object containing the image data   id	The resource id of the image data

        picturesizeH = bmp.getHeight();
        picturesizeW = bmp.getWidth();
        Log.e("bmp", String.valueOf(bmp.getHeight()) + "~~" + String.valueOf(bmp.getWidth()));
    }


    private View.OnTouchListener imgListener = new OnTouchListener(){           //觸碰圖片時的監聽事件
        private float a,b;
        private int mx,my;                        //移動後的座標位置
        private float rowx,rowy;


        @Override
    public boolean onTouch(View v,MotionEvent event){

            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:  //這個是OnTouchEvent事件的開始，任何事件都必須手指按下去才行。這個事件是一個從無觸摸螢幕狀態到有觸摸狀態的轉換。
                    Log.e("getTop", String.valueOf(img.getTop()) + "~~" + String.valueOf(picturesizeW));
                    rowx=event.getRawX();
                    rowy=event.getRawY();
                    a = event.getX();
                    b = rowy-img.getTop();
                case MotionEvent.ACTION_MOVE:   //緊接著的Move事件，只要手指在營幕上，即使不動也會持續使用。

                    mx =(int)(event.getRawX()-a);
                    my =(int)(event.getRawY()-b);
                    v.layout(mx,my,mx+v.getWidth(),my+v.getHeight());
                    break;
            }
            Log.e("address", String.valueOf(mx) + "~~" + String.valueOf(my));
            return true;
        };
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

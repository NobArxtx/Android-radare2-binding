package com.r2.pancake;
 
import android.app.Activity;
import android.os.Bundle;
import org.radare.r2pipe.R2PipeJNI;
import android.widget.TextView;
import android.graphics.Typeface;
import android.widget.Toast;

public class MainActivity extends Activity { 
    String mString = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		TextView tv = (TextView)findViewById(R.id.activitymainTextView1);
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
		tv.setTypeface(tf);
		
		try {
			R2PipeJNI r = new R2PipeJNI("/sdcard/x/Vo/Ex/jni/libr2.so");
			r.cmd("aaa");
			//mString += "R2 OUTPUT\n";
			mString += "===========================";
			mString += "afl";
			mString += "===========================\n";
			mString += r.cmd("afl"); // Get Output as it is
			mString += "\n";
			mString += "===========================";
			mString += "pd 20 String Json";
			mString += "===========================\n";
			mString += r.cmdj("pd 10"); // get Json String
			mString += "\n";
			mString += "===========================";
			mString += "pd 20 formatted String Json";
			mString += "===========================\n";
			r.cmd("e scr.color=0"); // disabling extra colors 
			mString += r.cmdfj("pd 10"); // get formatted Json"
			
			r.quit(); // Important
			// after quit you can't use R2PipeJNI object
			
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), 2).show();
		}
		tv.setText(mString);

		
        
    }
	
} 

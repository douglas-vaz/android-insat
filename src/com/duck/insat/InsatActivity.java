package com.duck.insat;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class InsatActivity extends Activity implements OnClickListener {
	
	private ImageView satImg;
	private Button show_visible, show_ir, show_vapour, show_composite;
	
    final String img_irc = "http://www.imd.gov.in/section/satmet/img/sector-irc.jpg";
    final String img_ir = "http://www.imd.gov.in/section/satmet/img/sector-ir.jpg";
    final String img_wv = "http://www.imd.gov.in/section/satmet/img/secto	r-wv.jpg";
    final String img_vis = "http://www.imd.gov.in/section/satmet/img/sector-vis.jpg";

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        satImg = (ImageView)findViewById(R.id.imageViewImg);
        show_visible = (Button)findViewById(R.id.buttonVisible);
        show_ir = (Button)findViewById(R.id.buttonIr);
        show_vapour = (Button)findViewById(R.id.buttonWv);
        show_composite = (Button)findViewById(R.id.buttonComposite);
        
        show_visible.setOnClickListener(InsatActivity.this);
        show_vapour.setOnClickListener(InsatActivity.this);
        show_ir.setOnClickListener(InsatActivity.this);
        show_composite.setOnClickListener(InsatActivity.this);
        
    }
    
    public Object fetch(String address) throws MalformedURLException,
    IOException {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }
    
    private void setSatImage(String url)
    {
    	try{
            Drawable image =ImageOperations(this,url);
            satImg.setImageDrawable(image);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }


        int width = 100;
        int height = 100;
        
		satImg.setMinimumWidth(width);
		satImg.setMinimumHeight(height);

        satImg.setMaxWidth(width);
        satImg.setMaxHeight(height);
    }

    private Drawable ImageOperations(Context ctx, String url) {
        try {
            InputStream is = (InputStream) this.fetch(url);
            Drawable d = Drawable.createFromStream(is, "src");
            return d;
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

	public void onClick(View arg0) {
		switch(arg0.getId())
		{
		case R.id.buttonVisible:
			setSatImage(img_vis);
			break;
		case R.id.buttonIr:
			setSatImage(img_ir);
			break;
		case R.id.buttonWv:
			setSatImage(img_wv);
			break;
		case R.id.buttonComposite:
			setSatImage(img_irc);
			break;
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater = getMenuInflater();
		    inflater.inflate(R.menu.settings_menu, menu);
		    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.menuStub:		makeToast("Stub1");
								break;
		case R.id.menuStub2:	makeToast("Stub2");
								break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void makeToast(String foo)
	{
		Toast.makeText(InsatActivity.this, foo, Toast.LENGTH_SHORT);
	}
    
}
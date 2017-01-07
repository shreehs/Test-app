package com.intplus.shoppingspace.commons;

import android.app.Activity;
import android.util.Log;

import com.intplus.shoppingspace.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * Created by harshas on 12/26/2016.
 */
public class JSONResourceReader {
    Activity activity;

    public JSONResourceReader(Activity activity) {
        this.activity = activity;
    }

    /*
    parms
        resourceInt : Android resource int of json file.
     */
    public String readJSONFile(int resourceInt) throws IOException {
        InputStream is = this.activity.getResources().openRawResource(resourceInt);
        StringBuilder total = new StringBuilder();
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        // Using BufferedReader.
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        }
        finally {
            is.close();
        }
        // Log.d("Shop", total.toString());
        return total.toString();
        // Using Reader
        /*
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                Log.d("Shop", "buffer writing..");
                System.out.println("Shop n = " + n);
                System.out.println("Shop bufr = "+ buffer);
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

        String jsonString = writer.toString();
        Log.d("Shop jsonToString : ", jsonString);
        return jsonString;
        */
    }
}

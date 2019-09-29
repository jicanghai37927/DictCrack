package com.haiyunshan.dictcrack;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.haiyunshan.dictcrack.dict.PlainDict;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import cn.dictcn.android.dcalc.LocalDict;

public class PlainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plain);

        test();
    }

    void test() {
        PlainDict[] array = new PlainDict[3];
        for (int i = 0; i < array.length; i++) {
            PlainDict dict = new PlainDict(new File(getDir(), getName(i)), i, hasWid(i));
            array[i] = dict;
        }

//        testDict(array[2]);

        {
            testDict1(array[0], "sccs_dict.txt");
//            testDict1(array[1], "sccs_index.txt");
//            testDict1(array[2], "sccs_sugg.txt");
        }

        testIndex(array[1]);
    }

    void testDict1(PlainDict dict, String name) {
        int count = dict.getCount();
        Log.w("AA", "dict count = " + count);

        StringBuilder sb = new StringBuilder(1024000);
        for (int i = 0; i < dict.getCount(); i++) {

//            sb.append(dict.searchByIndex(i));

            sb.append(dict.searchByIndexForKey(i));
            sb.append('\n');
        }

        try {
            writeToFile(sb.toString(), name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void testIndex(PlainDict dict) {
        int count = dict.getCount();
        Log.w("AA", "index count = " + count);

        String a = dict.searchForKeys();
        String b = dict.searchForKeyUwids();

        try {
            writeToFile(b, "KeyUwids.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> keys = new ArrayList<>();
        {
            for (int i = 0; i < count; i++) {
                String value = null;
                value = dict.searchByIndexForKey(i);
                if (value.indexOf("sccs") >= 0) {
                    keys.add(value);
                }
            }

            Log.w("AA", "key count = " + keys.size());
        }

//        {
//            for (int i = 0; i < keys.size(); i++) {
//                byte[] data = dict.searchByKeyForVal(keys.get(i));
//                int[] array = toShortArray(data);
//                String text = Arrays.toString(array);
//                Log.w("AA", keys.get(i) + " = " + text);
//                Log.w("AA", "length = " + data.length);
//
//                break;
//            }
//        }

        {
            for (int i = 0; i < keys.size(); i++) {
                String text = dict.search(keys.get(i));
                Log.w("AA", keys.get(i) + " = " + text);

                break;
            }
        }
    }

    void writeToFile(String text, String name) throws IOException {
        byte[] data = text.getBytes("utf-8");
        File file = new File(Environment.getExternalStorageDirectory(), name);
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        fos.close();
    }

    int[] toIntArray(byte[] data) {
        int[] array = new int[data.length / 4];
        for (int i = 0; i < array.length; i++) {
            int pos = i * 4;

            int value = ((data[pos + 0] << 24)  & 0xff000000)
                    | ((data[pos + 1]   << 16)  & 0x00ff0000)
                    | ((data[pos + 2]   << 8)   & 0x0000ff00)
                    | ((data[pos + 3]   << 0)   & 0x000000ff);
            array[i] = value;
        }

        return array;
    }

    int[] toShortArray(byte[] data) {
        int[] array = new int[data.length / 2];
        for (int i = 0; i < array.length; i++) {
            int pos = i * 2;

            int value = ((data[pos + 0] << 8)  & 0xff00)
                    | ((data[pos + 1]   << 0)  & 0x00ff);

            array[i] = value;
        }

        return array;
    }

    void testDict(PlainDict dict) {
        int count = dict.getCount();
        Log.w("AA", "dict = " + count);

        String keys = dict.searchForKeys();
        String keyuwids = dict.searchForKeyUwids();

        if (true) {
            for (int i = 0; i < count; i++) {
                String value = null;
                value = dict.searchByIndex(i);
//            value = dict.searchByIndexForKey(i);

                Log.w("AA", value);

                if (i == 2) {
                    break;
                }
            }
        }

        if (false) {
            for (int i = 0; i < count; i++) {
                byte[] data = dict.searchByIndexForVal(i);
//            data = dict.searchByKeyForVal("一不");

                Log.w("AA", "" + data.length);

                break;
            }
        }

        if (true) {
            String value = dict.search("一不");
            Log.w("AA", "value = " + value);
        }
    }

    File getDir() {
        File file = Environment.getExternalStorageDirectory();
        file = new File(file, ".dictcn-sccs_xxhcycd");
        file = new File(file, "ciku");

        return file;
    }

    String getName(int id) {
        String name = "sccs_xxhcycd";

        switch (id) {
            case 0: {
                name += ".dict";
                break;
            }
            case 1: {
                name += ".index";
                break;
            }
            case 2: {
                name += ".sugg";
                break;

            }
            default: {
                name += ".dict";
            }
        }

        return name;
    }

    boolean hasWid(int id) {
        return (id == 0);
    }
}

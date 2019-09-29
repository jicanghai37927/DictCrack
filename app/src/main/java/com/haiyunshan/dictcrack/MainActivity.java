package com.haiyunshan.dictcrack;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.UnsupportedEncodingException;

import cn.dictcn.android.dcalc.LocalDict;
import cn.dictcn.android.digitize.dictionary.LocalDigitDict;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    void test2() {

//        convert();
//        test();

//        readData();

        LocalDict dict = readData(0, true, "sccs_xxhcycd.dict.dgz");
        LocalDict indexDict = readData(1, false, "sccs_xxhcycd.index.dgz");
        LocalDict suggDict = readData(2, false, "sccs_xxhcycd.sugg.dgz");

        {
            indexDict = indexDict;
            byte[] data = indexDict.searchByIndexForKey(0);
//            data = indexDict.searchByIndexForVal(0);
            data = indexDict.searchForKeyUwids();
//            data = indexDict.searchByIndexForUwid(0)
            data = indexDict.searchForKeys();
//            data = indexDict.searchByIndexForVal(0);
//            for (byte b : data) {
//                Log.w("AA", Integer.toHexString(b));
//            }

            for (int i = 0; i < indexDict.getCdbCount(); i++) {
                data = indexDict.searchByIndexForKey(i);
                int id = indexDict.searchByIndexForUwid(i);
//                data = indexDict.searchByIndexForVal(i);

//                Log.w("AA", "" + id);
//            int size = indexDict.getCdbCount();
//            int count = ((data[0] << 8) & 0xff00) | (data[1] & 0xff);
//            Log.w("AA", "count = " + count);

                try {
                    String str = new String(data, "utf-8");
                    int offset = 0;
                    str = new String(data, offset, data.length - offset, "utf-8");
//                str = new String(data, 0, offset, "utf-8");
                    Log.w("AA", str);

//                    Log.w("AA", "str length = " + str.length());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            Log.w("AA", indexDict.getCdbCount() + "");
        }


    }

    LocalDict readData(int dictId, boolean hasWid, String name) {
        LocalDict dict = null;

        File file = Environment.getExternalStorageDirectory();
        file = new File(file, "ciku");

        File srcFile = new File(file, name);
        File destFile = new File(file, name + ".txt");

        if (!destFile.exists()) {
            convertDict(srcFile, destFile);
        }

        if (destFile.exists()) {
            dict = load(dictId, hasWid, destFile);
            if (dict != null) {
                Log.w("AA", name);
                Log.w("AA", "count = " + dict.getCdbCount());
            }

            if (dict != null) {
//                int count = dict.getCdbCount();
//                for (int i = 0; i < count; i++) {
//                    byte[] data = dict.searchByIndex(i);
//                    if (data != null) {
//                        for (byte b : data) {
//                            Log.w("AA", Integer.toHexString(b));
//                        }
//
//                        try {
//                            String str = new String(data, "utf-8");
//                            Log.w("AA", str);
//
//                            Log.w("AA", "length = " + data.length);
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    if (i >= 0) {
//                        break;
//                    }
//                }
            }
        }

        return dict;
    }

    void test() {
        File file = Environment.getExternalStorageDirectory();
        file = new File(file, "ciku");

        LocalDigitDict[] array = new LocalDigitDict[] {
                new LocalDigitDict(0),
                new LocalDigitDict(1),
                new LocalDigitDict(2)
        };

        for (LocalDigitDict name : array) {
            File srcFile = new File(file, name.srcName + ".txt");

            LocalDict dict = load(name.dictId, name.hasWid, srcFile);
            if (dict != null) {
                Log.w("AA", name.srcName);
                Log.w("AA", "count = " + dict.getCdbCount());
            }
        }
    }

    void convert() {
        File file = Environment.getExternalStorageDirectory();
        file = new File(file, "ciku");

        String[] array = new String[] {
                "sw_gjcydcd.dict.dgz",
                "sw_gjcydcd.index.dgz",
                "sw_gjcydcd.sugg.dgz"
        };

        for (String name : array) {
            File srcFile = new File(file, name);
            if (!srcFile.exists()) {
                Log.w("AA", name + " no exist");
                continue;
            }

            File destFile = new File(file, name + ".txt");

            convertDict(srcFile, destFile);
        }

    }

    static LocalDict load(int dictId, boolean hasWid, File file) {

        LocalDict localDict = new LocalDict(dictId);
        String str = file.getAbsolutePath();
        if (!localDict.initDict(str, hasWid)) {
            localDict = null;
            Log.w("LocalDigitDict", String.format("%s load failed", str));

        }
        return localDict;

    }

    static boolean convertDict(File srcFile, File destFile) {

        long l = System.currentTimeMillis();


        String str1 = srcFile.getAbsolutePath();
        String str2 = destFile.getAbsolutePath();

        Log.w("LocalDigitDict", str1);
        Log.w("LocalDigitDict", str2);

        boolean bool2 = LocalDict.cryptDict2CDB(str1, str2);

        boolean bool1 = bool2;

        if (bool1) {
            Log.w("LocalDigitDict", String.format("%s convertDict success", destFile.getAbsolutePath()));
            Log.w("LocalDigitDict", "生成e2c词库包耗时：" + (System.currentTimeMillis() - l));
        } else {
            Log.w("LocalDigitDict", String.format("%s convertDict failed", destFile.getAbsolutePath()));
        }

        return bool1;

    }
}

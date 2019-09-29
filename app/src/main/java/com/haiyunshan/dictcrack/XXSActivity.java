package com.haiyunshan.dictcrack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.haiyunshan.dictcrack.dict.CnDict;

import cn.dictcn.android.dcalc.LocalDict;

public class XXSActivity extends AppCompatActivity {

    static final String FOLDER = "xxs_ciku";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xxs);

        test();
    }

    void test() {
        CnDict dict = getDict(0);
        CnDict index = getDict(1);
        CnDict sugg = getDict(2);

//        testDict(dict);
        testIndex(index);
//        testSugg(sugg);
    }

    void testDict(CnDict dict) {
        int count = dict.getCount();
        Log.w("AA", "dict = " + count);
    }

    void testIndex(CnDict dict) {
        int count = dict.getCount();
        Log.w("AA", "dict = " + count);

        String keys = dict.searchForKeys();
        String keyuwids = dict.searchForKeyUwids();

        for (int i = 0; i < count; i++) {
            String value = null;
//            value = dict.searchByIndex(i);
            value = dict.searchByIndexForKey(i);

            Log.w("AA", value);

            if (i == 2) {
                break;
            }
        }
    }

    void testSugg(CnDict dict) {
        int count = dict.getCount();
        Log.w("AA", "dict = " + count);
    }

    CnDict getDict(int id) {
        CnDict dict = new CnDict(FOLDER, id);
        dict.init();
        return dict;
    }
}

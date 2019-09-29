package com.haiyunshan.dictcrack.dict;

import android.os.Environment;

import java.io.File;

import cn.dictcn.android.dcalc.LocalDict;
import cn.dictcn.android.digitize.dictionary.LocalDigitDict;

public class CnDict {

    public static final int DICT_dict   = 0;
    public static final int DICT_index  = 1;
    public static final int DICT_sugg   = 2;

    int mDictId;
    boolean hasWid = false;

    File mFile;
    LocalDict mDict;
    LocalDict localDict;

    public CnDict(String folder, int dictId) {
        this.mDictId = dictId;
        this.hasWid = (dictId == 0);

        String name = getName(folder, dictId);

        this.mFile = new File(Environment.getExternalStorageDirectory(), name);
    }

    public boolean init() {
        File file = new File(mFile.getAbsolutePath() + ".txt");
        if (!file.exists()) {
            LocalDict.cryptDict2CDB(mFile.getAbsolutePath(), file.getAbsolutePath());
        }

        LocalDict dict = new LocalDict(mDictId);
        boolean result = dict.initDict(file.getAbsolutePath(), hasWid);

        this.mDict = dict;
        this.localDict = dict;
        return result;
    }

    public int getCount() {
        return mDict.getCdbCount();
    }

    public String searchByIndex(int paramInt) {
        return searchByIndex(paramInt, paramInt);
    }

    public String searchByIndex(int paramInt1, int paramInt2) {

        if (this.localDict != null) {
            try {
                byte[] localObject = this.localDict.searchByIndex(paramInt1, paramInt2);
                if ((localObject != null) && (localObject.length != 0)) {
                    String localObject2 = new String((byte[]) localObject, "UTF-8");
                    return (String) localObject2;
                }
            } catch (Exception localException) {
                LocalDigitDict.w.b("LocalDigitDict", localException);
            }
        }
        return null;
    }

    public String searchByIndexForKey(int paramInt) {
        if (this.localDict != null) {
            try {
                byte[] localObject = this.localDict.searchByIndexForKey(paramInt);
                if ((localObject != null) && (localObject.length != 0)) {
                    String localObject2 = new String((byte[]) localObject, "UTF-8");
                    return (String) localObject2;
                }
            } catch (Exception localException) {
                LocalDigitDict.w.b("LocalDigitDict", localException);
            }
        }
        return null;
    }

    public String searchForKeyUwids() {
        if (this.localDict != null) {
            try {
                byte[] localObject = this.localDict.searchForKeyUwids();
                if ((localObject != null) && (localObject.length != 0)) {
                    String localObject2 = new String((byte[]) localObject, "UTF-8");
                    return (String) localObject2;
                }
            } catch (Exception localException) {
                LocalDigitDict.w.b("LocalDigitDict", localException);
            }
        }
        return null;
    }

    public String searchForKeys() {
        if (this.localDict != null) {
            try {
                byte[] localObject = this.localDict.searchForKeys();
                if ((localObject != null) && (localObject.length != 0)) {
                    String localObject2 = new String((byte[]) localObject, "UTF-8");
                    return (String) localObject2;
                }
            } catch (Exception localException) {
                LocalDigitDict.w.b("LocalDigitDict", localException);
            }
        }
        return null;
    }

    String getName(String folder, int dictId) {

        String name = "sccs_xxhcycd.dict.dgz";
        if (dictId == 1) {
            name = "sccs_xxhcycd.index.dgz";
        } else if (dictId == 2) {
            name = "sccs_xxhcycd.sugg.dgz";
        }

        return folder + "/" + name;
    }
}

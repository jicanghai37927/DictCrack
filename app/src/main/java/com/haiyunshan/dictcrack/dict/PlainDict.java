package com.haiyunshan.dictcrack.dict;

import android.util.Log;

import java.io.File;

import cn.dictcn.android.dcalc.LocalDict;
import cn.dictcn.android.digitize.dictionary.LocalDigitDict;

public class PlainDict {

    LocalDict localDict;

    public PlainDict(File file, int id, boolean hasWid) {
        localDict = new LocalDict(id);
        if (file.exists()) {
            localDict.initDict(file.getAbsolutePath(), hasWid);
        } else {
            Log.w("AA", file.getAbsolutePath() + " no exist");
        }
    }

    public int getCount() {
        return localDict.getCdbCount();
    }

    public String search(String key) {
        if (this.localDict != null) {
            try {
                byte[] paramString = this.localDict.searchByKey(key);
                if ((paramString != null) && (paramString.length != 0)) {
                    String paramString2 = new String(paramString, "UTF-8");
                    return paramString2;
                }
            } catch (Exception paramString) {
                LocalDigitDict.w.b("LocalDigitDict", paramString);
            }
        }
        return null;
    }

    public int searchByIndexForUwid(int paramInt) {
        if (this.localDict != null) {
            return this.localDict.searchByIndexForUwid(paramInt);
        }
        return -1;
    }

    public byte[] searchByKeyForVal(String paramString) {
        if (this.localDict != null) {
            return this.localDict.searchByKeyForVal(paramString);
        }
        return null;
    }

    public byte[] searchByIndexForVal(int paramInt) {
        if (this.localDict != null) {
            return this.localDict.searchByIndexForVal(paramInt);
        }
        return null;
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

}

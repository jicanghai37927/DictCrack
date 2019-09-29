package cn.dictcn.android.dcalc;

import android.util.Log;

import java.io.File;

public class LocalDict {
    private static final String TAG = "LocalDict";
    private int dictId;

    static {
        try {
            System.loadLibrary("dcalc");
        } catch (Error localError) {
            Log.w("LocalDict", localError.toString());
        }
    }

    public LocalDict(int paramInt) {
        this.dictId = paramInt;
    }

    private native void close(int paramInt);

    private static native int convertDict2CDB(String paramString1, String paramString2);

    public static boolean cryptDict2CDB(String paramString1, String paramString2) {
        return convertDict2CDB(paramString1, paramString2) != -1;
    }

    private native int getNumberWords(int paramInt);

    private native int loadDict(int paramInt1, int paramInt2, String paramString);

    private native byte[] search(int paramInt, String paramString);

    private native byte[] searchByIndex(int paramInt1, int paramInt2, int paramInt3);

    private native byte[] searchByIndexForKey(int paramInt1, int paramInt2);

    private native int searchByIndexForUwid(int paramInt1, int paramInt2);

    private native byte[] searchByIndexForVal(int paramInt1, int paramInt2);

    private native byte[] searchByKeyForVal(int paramInt, String paramString);

    private native byte[] searchByUwid(int paramInt, String paramString);

    private native byte[] searchByUwidForKey(int paramInt, String paramString);

    private native byte[] searchByUwidForVal(int paramInt, String paramString);

    private native byte[] searchForKeyUwids(int paramInt);

    private native byte[] searchForKeys(int paramInt);

    public void close() {
        close(this.dictId);
    }

    public int getCdbCount() {
        return getNumberWords(this.dictId);
    }

    public native byte[] getLocalSuggestion(int paramInt1, String paramString, int paramInt2);

    public byte[] getLocalSuggestion(String paramString, int paramInt) {
        return getLocalSuggestion(this.dictId, paramString, paramInt);
    }

    public boolean initDict(String paramString, boolean paramBoolean) {
        if (new File(paramString).exists()) {
            int j = this.dictId;
            if (paramBoolean) {
            }
            for (int i = 1; loadDict(j, i, paramString) != -1; i = 0) {
                return true;
            }
        }
        return false;
    }

    public byte[] searchByIndex(int paramInt) {
        return searchByIndex(paramInt, paramInt);
    }

    public byte[] searchByIndex(int paramInt1, int paramInt2) {
        return searchByIndex(this.dictId, paramInt1, paramInt2);
    }

    public byte[] searchByIndexForKey(int paramInt) {
        return searchByIndexForKey(this.dictId, paramInt);
    }

    public int searchByIndexForUwid(int paramInt) {
        return searchByIndexForUwid(this.dictId, paramInt);
    }

    public byte[] searchByIndexForVal(int paramInt) {
        return searchByIndexForVal(this.dictId, paramInt);
    }

    public byte[] searchByKey(String paramString) {
        return search(this.dictId, paramString);
    }

    public byte[] searchByKeyForVal(String paramString) {
        return searchByKeyForVal(this.dictId, paramString);
    }

    public byte[] searchByUwid(String paramString) {
        return searchByUwid(this.dictId, paramString);
    }

    public byte[] searchByUwidForKey(String paramString) {
        return searchByUwidForKey(this.dictId, paramString);
    }

    public byte[] searchByUwidForVal(String paramString) {
        return searchByUwidForVal(this.dictId, paramString);
    }

    public byte[] searchForKeyUwids() {
        return searchForKeyUwids(this.dictId);
    }

    public byte[] searchForKeys() {
        return searchForKeys(this.dictId);
    }
}


/* Location:              /Users/elevennnn/Documents/android/dex2jar-2.0/cn.dictcn.android.digitize.sw_gjcydcd_10011_72323300-dex2jar.jar!/cn/dictcn/android/dcalc/LocalDict.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
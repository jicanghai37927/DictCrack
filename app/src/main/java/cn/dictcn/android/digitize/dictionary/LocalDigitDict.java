package cn.dictcn.android.digitize.dictionary;

import cn.dictcn.android.dcalc.LocalDict;

public class LocalDigitDict {
    public static final int ERROR_ID = -1;
    public static final String TAG = "LocalDigitDict";
    public String destName = null;
    public int dictId = -1;
    public boolean hasWid = false;
    public boolean isAssets = false;
    public LocalDict localDict = null;
    public String srcName = null;

    public LocalDigitDict(int paramInt) {
        this.dictId = paramInt;
        this.isAssets = true;
        switch (paramInt) {
            case -2:
            default:
                this.srcName = "sw_gjcydcd.offline.dgz";
                this.destName = "sw_gjcydcd.offline";
                this.hasWid = true;
                return;
            case 0:
                this.srcName = "sw_gjcydcd.dict.dgz";
                this.destName = "sw_gjcydcd.dict";
                this.hasWid = true;
                return;
            case -1:
                this.srcName = "sw_gjcydcd.dict.dgz";
                this.destName = "sw_gjcydcd.dict";
                this.hasWid = true;
                return;
            case 1:
                this.srcName = "sw_gjcydcd.sugg.dgz";
                this.destName = "sw_gjcydcd.sugg";
                this.hasWid = false;
                return;
            case 2:
                this.srcName = "sw_gjcydcd.index.dgz";
                this.destName = "sw_gjcydcd.index";
                this.hasWid = false;
                return;
            case 3:
                this.srcName = "sw_gjcydcd.kv.dgz";
                this.destName = "sw_gjcydcd.kv";
                this.hasWid = false;
                return;

        }

    }

    public LocalDigitDict(int paramInt, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2) {
        this.dictId = paramInt;
        this.srcName = paramString1;
        this.destName = paramString2;
        this.hasWid = paramBoolean1;
        this.isAssets = paramBoolean2;
    }

    private boolean convertDict() {
        boolean bool2 = isPrepared();
        long l = System.currentTimeMillis();
        boolean bool1 = bool2;
        if (bool2) {
            w.a("LocalDigitDict", String.format("%s copyAssert success", new Object[]{this.destName}));
            String str1 = getSrcPath();
            String str2 = getDestPath();
            bool2 = LocalDict.cryptDict2CDB(str1, str2);
            s.c(str1);
            bool1 = bool2;
            if (!bool2) {
                s.c(str2);
                bool1 = bool2;
            }
        }
        if (bool1) {
            w.a("LocalDigitDict", String.format("%s convertDict success", new Object[]{this.destName}));
            w.a("LocalDigitDict", "生成e2c词库包耗时：" + (System.currentTimeMillis() - l));
            return bool1;
        }
        w.b("LocalDigitDict", String.format("%s convertDict failed", new Object[]{this.destName}));
        return bool1;
    }

    private void deleteDict() {
        s.c(getDestPath());
    }

    private String getDestPath() {
        if (this.isAssets) {
            return s.p() + this.destName;
        }
        return this.destName;
    }

    private String getSrcPath() {
        if (this.isAssets) {
            return s.p() + this.srcName;
        }
        return this.srcName;
    }

    private boolean initDict() {
        if (this.dictId > -1) {
            this.localDict = new LocalDict(this.dictId);
            String str = getDestPath();
            if (!this.localDict.initDict(str, this.hasWid)) {
                this.localDict = null;
                w.b("LocalDigitDict", String.format("%s load failed", new Object[]{this.destName}));
                return false;
            }
        }
        return true;
    }

    private boolean isExist() {
        return s.e(getDestPath());
    }

    private boolean isPrepared() {
        if (this.isAssets) {
            return s.a("ciku", this.srcName, s.p());
        }
        return s.e(this.srcName);
    }

    public void close() {
        if (this.localDict != null) {
            this.localDict.close();
            this.localDict = null;
        }
    }

    public int getCdbCount() {
        if (this.localDict != null) {
            return this.localDict.getCdbCount();
        }
        return 0;
    }

    public String getLocalSuggestion(String key, int paramInt) {
        if (this.localDict != null) {
            try {
                byte[] paramString = this.localDict.getLocalSuggestion(key, paramInt);
                if ((paramString != null) && (paramString.length != 0)) {
                    String paramString2 = new String(paramString, "UTF-8");
                    return paramString2;
                }
            } catch (Exception paramString) {
                w.b("LocalDigitDict", paramString);
            }
        }
        return null;
    }

    public boolean isLoaded() {
        if (this.dictId <= -1) {
        }
        while (this.localDict != null) {
            return true;
        }
        return false;
    }

    public boolean loadDict() {
        boolean bool2 = true;
        if (this.dictId <= -1) {
            bool2 = true;
            return bool2;
        }

        boolean bool1 = true;
        if (!isExist()) {
            bool1 = convertDict();
            if (!bool1) {
                deleteDict();
            }
        }

        {
            deleteDict();
            bool1 = initDict();
        }

        return bool1;
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
                w.b("LocalDigitDict", paramString);
            }
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
                w.b("LocalDigitDict", localException);
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
                w.b("LocalDigitDict", localException);
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

    public byte[] searchByIndexForVal(int paramInt) {
        if (this.localDict != null) {
            return this.localDict.searchByIndexForVal(paramInt);
        }
        return null;
    }

    public byte[] searchByKeyForVal(String paramString) {
        if (this.localDict != null) {
            return this.localDict.searchByKeyForVal(paramString);
        }
        return null;
    }

    public String searchByUwid(String uwid) {
        if (this.localDict != null) {
            try {
                byte[] paramString = this.localDict.searchByUwid(uwid);
                if ((paramString != null) && (paramString.length != 0)) {
                    String paramString2 = new String(paramString, "UTF-8");
                    return paramString2;
                }
            } catch (Exception paramString) {
                w.b("LocalDigitDict", paramString);
            }
        }
        return null;
    }

    public String searchByUwidForKey(String uwid) {
        if (this.localDict != null) {
            try {
                byte[] paramString = this.localDict.searchByUwidForKey(uwid);
                if ((paramString != null) && (paramString.length != 0)) {
                    String paramString2 = new String(paramString, "UTF-8");
                    return paramString2;
                }
            } catch (Exception paramString) {
                w.b("LocalDigitDict", paramString);
            }
        }
        return null;
    }

    public byte[] searchByUwidForVal(String paramString) {
        byte[] arrayOfByte = null;
        if (this.localDict != null) {
        }
        try {
            arrayOfByte = this.localDict.searchByUwidForVal(paramString);
            return arrayOfByte;
        } catch (Exception e) {
            w.b("LocalDigitDict", e);
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
                w.b("LocalDigitDict", localException);
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
                w.b("LocalDigitDict", localException);
            }
        }
        return null;
    }

    public static class w {
        public static void a(String tag, String msg) {

        }

        public static void b(String tag, Exception e) {

        }

        public static void b(String tag, String e) {

        }


    }

    public static class s {
        public static boolean e(String path) {
            return true;
        }

        public static String p() {
            return "";
        }

        public static void c(String path) {

        }

        public static boolean a(String name, String src, String dest) {
            return true;
        }
    }
}


/* Location:              /Users/elevennnn/Documents/android/dex2jar-2.0/cn.dictcn.android.digitize.sw_gjcydcd_10011_72323300-dex2jar.jar!/cn/dictcn/android/digitize/dictionary/LocalDigitDict.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
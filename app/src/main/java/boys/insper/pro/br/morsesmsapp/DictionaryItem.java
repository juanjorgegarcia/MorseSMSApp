package boys.insper.pro.br.morsesmsapp;

public class DictionaryItem {

    private String lAZ;
    private String mAZ;
    private String lCodes;
    private String mCodes;

    public DictionaryItem (String lAZ, String mAZ, String lCodes, String mCodes) {
        this.lAZ = lAZ;
        this.mAZ = mAZ;
        this.lCodes = lCodes;
        this.mCodes = mCodes;
    }

    public String getlAZ() {
        return lAZ;
    }

    public String getlCodes() {
        return lCodes;
    }

    public String getmAZ() {
        return mAZ;
    }

    public String getmCodes() {
        return mCodes;
    }
}

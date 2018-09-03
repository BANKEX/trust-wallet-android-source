package com.bankex.pay.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author Denis Anisimov.
 */
public class KeyStore {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("crypto")
    private CryptoBean mCrypto;
    @SerializedName("id")
    private String mId;
    @SerializedName("version")
    private int mVersion;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public CryptoBean getCrypto() {
        return mCrypto;
    }

    public void setCrypto(CryptoBean crypto) {
        mCrypto = crypto;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public int getVersion() {
        return mVersion;
    }

    public void setVersion(int version) {
        mVersion = version;
    }

    public static class CryptoBean {
        @SerializedName("cipher")
        private String mCipher;
        @SerializedName("ciphertext")
        private String mCiphertext;
        @SerializedName("cipherparams")
        private CipherparamsBean mCipherparams;
        @SerializedName("kdf")
        private String mKdf;
        @SerializedName("kdfparams")
        private KdfparamsBean mKdfparams;
        @SerializedName("mac")
        private String mMac;

        public String getCipher() {
            return mCipher;
        }

        public void setCipher(String cipher) {
            mCipher = cipher;
        }

        public String getCiphertext() {
            return mCiphertext;
        }

        public void setCiphertext(String ciphertext) {
            mCiphertext = ciphertext;
        }

        public CipherparamsBean getCipherparams() {
            return mCipherparams;
        }

        public void setCipherparams(CipherparamsBean cipherparams) {
            mCipherparams = cipherparams;
        }

        public String getKdf() {
            return mKdf;
        }

        public void setKdf(String kdf) {
            mKdf = kdf;
        }

        public KdfparamsBean getKdfparams() {
            return mKdfparams;
        }

        public void setKdfparams(KdfparamsBean kdfparams) {
            mKdfparams = kdfparams;
        }

        public String getMac() {
            return mMac;
        }

        public void setMac(String mac) {
            mMac = mac;
        }

        public static class CipherparamsBean {
            @SerializedName("iv")
            private String mIv;

            public String getIv() {
                return mIv;
            }

            public void setIv(String iv) {
                mIv = iv;
            }
        }

        public static class KdfparamsBean {
            @SerializedName("dklen")
            private int mDklen;
            @SerializedName("n")
            private int mN;
            @SerializedName("p")
            private int mP;
            @SerializedName("r")
            private int mR;
            @SerializedName("salt")
            private String mSalt;

            public int getDklen() {
                return mDklen;
            }

            public void setDklen(int dklen) {
                mDklen = dklen;
            }

            public int getN() {
                return mN;
            }

            public void setN(int n) {
                mN = n;
            }

            public int getP() {
                return mP;
            }

            public void setP(int p) {
                mP = p;
            }

            public int getR() {
                return mR;
            }

            public void setR(int r) {
                mR = r;
            }

            public String getSalt() {
                return mSalt;
            }

            public void setSalt(String salt) {
                mSalt = salt;
            }
        }
    }
}

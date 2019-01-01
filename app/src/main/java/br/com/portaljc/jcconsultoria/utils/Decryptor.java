package br.com.portaljc.jcconsultoria.utils;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;

class Decryptor {

    private KeyStore keyStore;

    Decryptor() throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
            IOException {
        initKeyStore();
    }

    void deleteSecretKey(String alias) {
        try {
            keyStore.deleteEntry(alias);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    private void initKeyStore() throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
            IOException{
        keyStore = KeyStore.getInstance(Constants.ANDROID_KEY_STORE);
        keyStore.load(null);
    }

    String decryptData(final String alias, final byte[] encryptedData, final byte[] encryptionIv)
            throws UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, NoSuchPaddingException,
            InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException,
            InvalidAlgorithmParameterException {
        final Cipher cipher = Cipher.getInstance(Constants.TRANSFORMATION);
        //final GCMParameterSpec spec = new GCMParameterSpec(128, encryptionIv);
        final IvParameterSpec spec = new IvParameterSpec(encryptionIv);
        SecretKey secretKey = getSecretKey(alias);
        if (secretKey != null) {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
            return new String(cipher.doFinal(encryptedData), "UTF-8");
        } else {
            return "";
        }
    }

    private SecretKey getSecretKey(String alias) throws NoSuchAlgorithmException,
        UnrecoverableEntryException, KeyStoreException {
        KeyStore.Entry entry = keyStore.getEntry(alias, null);
        return entry != null ? ((KeyStore.SecretKeyEntry) entry).getSecretKey() : null;
    }
}

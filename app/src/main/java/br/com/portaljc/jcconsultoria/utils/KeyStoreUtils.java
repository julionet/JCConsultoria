package br.com.portaljc.jcconsultoria.utils;

import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.security.auth.x500.X500Principal;

public class KeyStoreUtils {

    private KeyStore keyStore;

    private byte[] encryption;
    private byte[] iv;

    private Context context;

    public KeyStoreUtils(Context context) throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
            IOException {
        this.context = context;

        initKeyStore();
    }

    private void initKeyStore() throws CertificateException, NoSuchAlgorithmException, KeyStoreException,
            IOException{
        keyStore = KeyStore.getInstance(Constants.ANDROID_KEY_STORE);
        keyStore.load(null);
    }

    public String decryptData(final String alias, final byte[] encryptedData, final byte[] encryptionIv)
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

    public byte[] encryptText(final String alias, final String textToEncrypt) throws NoSuchAlgorithmException,
            NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException,
            InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        final Cipher cipher = Cipher.getInstance(Constants.TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey(alias));

        iv = cipher.getIV();

        return (encryption = cipher.doFinal(textToEncrypt.getBytes("UTF-8")));
    }

    @NonNull
    private SecretKey generateSecretKey(final String alias) throws NoSuchAlgorithmException, NoSuchProviderException,
            InvalidAlgorithmParameterException {
        final KeyGenerator keyGenerator;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, Constants.ANDROID_KEY_STORE);
            keyGenerator.init(new KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
        } else {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            endDate.add(Calendar.YEAR, 20);

            keyGenerator = KeyGenerator.getInstance("RSA", Constants.ANDROID_KEY_STORE);
            keyGenerator.init(new KeyPairGeneratorSpec.Builder(context)
                    .setAlias(alias)
                    .setSerialNumber(BigInteger.ONE)
                    .setSubject(new X500Principal(String.format("CN=%s CA Certificate", alias)))
                    .setStartDate(startDate.getTime())
                    .setEndDate(endDate.getTime())
                    .build());
        }
        return keyGenerator.generateKey();
    }

    public void deleteSecretKey(String alias) {
        try {
            keyStore.deleteEntry(alias);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }

    public byte[] getEncryption() {
        return encryption;
    }

    public byte[] getIv() {
        return iv;
    }
}

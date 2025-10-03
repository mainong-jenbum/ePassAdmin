package com.jenbumapps.e_passbordumsa_admin.qrcode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.jenbumapps.core.api.ApiManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class QRCodeGenerator {

    public interface Listener{
        default void onFileUploaded(){}
        void onQRCodeUrlUpdated();

    }

    private static Listener mListener;

    //generate qr code using generateQR method
    public static void generateQR(final Activity mContext, final int ePassId, Listener listener) {
        Log.i(TAG, "generateQR: " + ePassId);
        mListener = listener;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final int colorQR = Color.BLACK;
                final int colorBackQR = Color.WHITE;
                final int width = 500;
                final int height = 500;

                try {
                    final Bitmap bitmapQR = generateBitmap(String.valueOf(ePassId), width, height,
                            MARGIN_AUTOMATIC, colorQR, colorBackQR);

                    File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File file = null;

                    try {
                        file = File.createTempFile("QRCODE",  ".png", storageDir);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try (FileOutputStream out = new FileOutputStream(file)) {
                        bitmapQR.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                        // PNG is a lossless format, the compression factor (100) is ignored
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    uploadFile(file, ePassId);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static int MARGIN_AUTOMATIC = -1;

    // generate qr code bitmap using width ,height and color code
    private static Bitmap generateBitmap(@NonNull String contentsToEncode,
                                         int imageWidth, int imageHeight,
                                         int marginSize, int color, int colorBack)
            throws WriterException, IllegalStateException {

        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Should not be invoked from the UI thread");
        }

        Map<EncodeHintType, Object> hints = null;
        if (marginSize != MARGIN_AUTOMATIC) {
            hints = new EnumMap<>(EncodeHintType.class);
            // We want to generate with a custom margin size
            hints.put(EncodeHintType.MARGIN, marginSize);
        }

        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result = writer.encode(contentsToEncode, BarcodeFormat.QR_CODE, imageWidth, imageHeight, hints);

        final int width = result.getWidth();
        final int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? color : colorBack;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static void uploadFile(File file, final int id) {

        Log.d("QRCodeGenerator", ""+file.getAbsolutePath());

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file",file.getName() , RequestBody.create(MediaType.parse("image/*"), file));

        ApiManager.file().saveImage(filePart).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        mListener.onFileUploaded();
                        updateQrUrl(response.body(), id);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                Log.e("ERROR", "Error uploading file");
            }
        });

    }

    private static void updateQrUrl(String url, int id) {

        Log.d("QRCodeGenerator", "url: "+url+"\nId: "+id);

        ApiManager.form().updateQRCode(id, url).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    Log.d("QR GENERATOR", "SUCCESS");
                    mListener.onQRCodeUrlUpdated();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                Log.d("QR GENERATOR", "FAILED");
            }
        });
    }

}

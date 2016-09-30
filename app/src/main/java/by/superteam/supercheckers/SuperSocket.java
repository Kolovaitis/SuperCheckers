package by.superteam.supercheckers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by Никита on 30.09.2016.
 */
public class SuperSocket {
    static Socket socket;
    public final static int WHITE = 0xFFFFFFFF;
    public final static int BLACK = 0xFF000000;
    public final static int WIDTH = 600;
    public final static int HEIGHT = 600;
    final int port = 6666;
    private GoogleApiClient client;
    public void conectAsClient(Intent intent,Context context){
        IntentIntegrator integrator = new IntentIntegrator((Activity) context);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan QR code");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(context).addApi(AppIndex.API).build();
    }
    public void host(final Intent intent, final Context context,ImageView forQRcode){
         // случайный порт (может быть любое число от 1025 до 65535)
        try {
            forQRcode.setImageBitmap(encodeAsBitmap(getIpAddress())); (new Thread(new Runnable() {
            @Override
            public void run() {



                try {
                    ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
                    System.out.println("Waiting for a client...");

                    socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
                    System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
                    System.out.println();

                    context.startActivity(intent);

                } catch(Exception x) { x.printStackTrace();
                }
            }})).start();
        } catch (WriterException e) {
        e.printStackTrace();
    }
    }
public static void forActivityResult(String result, final Intent intent, final Context context){
    final String address = result; // это IP-адрес компьютера, где исполняется наша серверная программа.
    // Здесь указан адрес того самого компьютера где будет исполняться и клиент.
    (new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
                System.out.println("Any of you heard of a socket with IP address " + address + " and port " + port + "?");
                socket = new Socket(ipAddress, port); // создаем сокет используя IP-адрес и порт сервера.
                System.out.println("Yes! I just got hold of the program.");



                context.startActivity(intent);

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    })).start();


}

    public static String getIpAddress() {
        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&&inetAddress instanceof Inet4Address) {
                        String ipAddress=inetAddress.getHostAddress().toString();
                        System.out.println("IP address"+ipAddress);
                        return ipAddress;
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("Socket exception in GetIP Address of Utilities"+ ex.toString());
        }
        return null;
    }
    Bitmap encodeAsBitmap(String str) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, HEIGHT, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, WIDTH, 0, 0, w, h);
        return bitmap;
    }

}

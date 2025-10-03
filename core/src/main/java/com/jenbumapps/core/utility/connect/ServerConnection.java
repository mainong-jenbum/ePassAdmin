package com.jenbumapps.core.utility.connect;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ServerConnection extends AsyncTask<Void, Void, Boolean> {

    private ServerConnectionListener mHelper;

    private ServerConnection(){}

    public static ServerConnection getInstance(){

        return new ServerConnection();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            Socket sock = new Socket();
            sock.connect(new InetSocketAddress(NetworkService.getHost()
                    , Integer.parseInt(NetworkService.getServerPort())), 1500);
            sock.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean status) {
        mHelper.isActive(status);
    }

    public ServerConnection setConnectionHelper(ServerConnectionListener mHelper) {
        this.mHelper = mHelper;
        return this;
    }

    public interface ServerConnectionListener {
        void isActive(boolean status);
    }

}

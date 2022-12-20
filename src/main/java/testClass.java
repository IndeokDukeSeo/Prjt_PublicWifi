import importclass.ConnectWifiApi;
import importclass.jdbcController;

import java.io.IOException;
import java.util.ArrayList;

public class testClass {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<ConnectWifiApi.WifiData> data = ConnectWifiApi.loadWifiObjectList();
        System.out.println(data.size());
    }
}

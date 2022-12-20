package importclass;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;

public class ConnectWifiApi {

    public static ArrayList<WifiData> loadWifiObjectList() throws IOException {
        ArrayList<WifiData> wifiData = new ArrayList<>();
        String baseUrlString = "http://openapi.seoul.go.kr:8088/796753424f6472693833556f437652/json/TbPublicWifiInfo";
        ArrayList<String> wifiList = new ArrayList<>(); // X500Principal;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 20000; i += 1000) {
            StringBuilder urlBuilder = new StringBuilder(baseUrlString);

            urlBuilder.append("/" + URLEncoder.encode(Integer.toString(i), "UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(Integer.toString(i + 999), "UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
//            System.out.println("url = " + url);
//            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            boolean isEnd = false;
            while ((line = rd.readLine()) != null) {
                if (line.substring(2, 8).equals("RESULT")) {
                    isEnd = true;
                    break;
                }
                wifiData.addAll(jsonToWifiData(line));
            }
            rd.close();
            conn.disconnect();
            if (isEnd) break;
        }
        return wifiData;
    }

    public static ArrayList<WifiData> jsonToWifiData(String jsonRawString) {
        ArrayList<WifiData> wifiDataArrayList = new ArrayList<>();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonRawString, JsonObject.class);
        JsonObject element = jsonObject.getAsJsonObject("TbPublicWifiInfo");
        JsonArray jsonArray = element.get("row").getAsJsonArray();
//        System.out.println(jsonArray);
        for (int i = 0; i < jsonArray.size(); i++) {
            wifiDataArrayList.add(gson.fromJson(jsonArray.get(i), WifiData.class));
        }
        return wifiDataArrayList;
    }
    public class WifiData {
        public String X_SWIFI_MGR_NO;
        public String X_SWIFI_WRDOFC;
        public String X_SWIFI_MAIN_NM;
        public String X_SWIFI_ADRES1;
        public String X_SWIFI_ADRES2;
        public String X_SWIFI_INSTL_FLOOR;
        public String X_SWIFI_INSTL_TY;
        public String X_SWIFI_INSTL_MBY;
        public String X_SWIFI_SVC_SE;
        public String X_SWIFI_CMCWR;
        public String X_SWIFI_CNSTC_YEAR;
        public String X_SWIFI_INOUT_DOOR;
        public String X_SWIFI_REMARS3;
        public String LAT;
        public String LNT;
        public String WORK_DTTM;

        public String getX_SWIFI_MGR_NO() {
            return X_SWIFI_MGR_NO;
        }

        public String getX_SWIFI_WRDOFC() {
            return X_SWIFI_WRDOFC;
        }

        public String getX_SWIFI_MAIN_NM() {
            return X_SWIFI_MAIN_NM;
        }

        public String getX_SWIFI_ADRES1() {
            return X_SWIFI_ADRES1;
        }

        public String getX_SWIFI_ADRES2() {
            return X_SWIFI_ADRES2;
        }

        public String getX_SWIFI_INSTL_FLOOR() {
            return X_SWIFI_INSTL_FLOOR;
        }

        public String getX_SWIFI_INSTL_TY() {
            return X_SWIFI_INSTL_TY;
        }

        public String getX_SWIFI_INSTL_MBY() {
            return X_SWIFI_INSTL_MBY;
        }

        public String getX_SWIFI_SVC_SE() {
            return X_SWIFI_SVC_SE;
        }

        public String getX_SWIFI_CMCWR() {
            return X_SWIFI_CMCWR;
        }

        public String getX_SWIFI_CNSTC_YEAR() {
            return X_SWIFI_CNSTC_YEAR;
        }

        public String getX_SWIFI_INOUT_DOOR() {
            return X_SWIFI_INOUT_DOOR;
        }

        public String getX_SWIFI_REMARS3() {
            return X_SWIFI_REMARS3;
        }

        public String getLAT() {
            return LAT;
        }

        public String getLNT() {
            return LNT;
        }

        public String getWORK_DTTM() {
            return WORK_DTTM;
        }

        public WifiData() {
        }

        public WifiData(String x_SWIFI_MGR_NO, String x_SWIFI_WRDOFC, String x_SWIFI_MAIN_NM, String x_SWIFI_ADRES1, String x_SWIFI_ADRES2, String x_SWIFI_INSTL_FLOOR, String x_SWIFI_INSTL_TY, String x_SWIFI_INSTL_MBY, String x_SWIFI_SVC_SE, String x_SWIFI_CMCWR, String x_SWIFI_CNSTC_YEAR, String x_SWIFI_INOUT_DOOR, String x_SWIFI_REMARS3, String LAT, String LNT, String WORK_DTTM) {
            this.X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
            this.X_SWIFI_WRDOFC = x_SWIFI_WRDOFC;
            this.X_SWIFI_MAIN_NM = x_SWIFI_MAIN_NM;
            this.X_SWIFI_ADRES1 = x_SWIFI_ADRES1;
            this.X_SWIFI_ADRES2 = x_SWIFI_ADRES2;
            this.X_SWIFI_INSTL_FLOOR = x_SWIFI_INSTL_FLOOR;
            this.X_SWIFI_INSTL_TY = x_SWIFI_INSTL_TY;
            this.X_SWIFI_INSTL_MBY = x_SWIFI_INSTL_MBY;
            this.X_SWIFI_SVC_SE = x_SWIFI_SVC_SE;
            this.X_SWIFI_CMCWR = x_SWIFI_CMCWR;
            this.X_SWIFI_CNSTC_YEAR = x_SWIFI_CNSTC_YEAR;
            this.X_SWIFI_INOUT_DOOR = x_SWIFI_INOUT_DOOR;
            this.X_SWIFI_REMARS3 = x_SWIFI_REMARS3;
            this.LAT = LAT;
            this.LNT = LNT;
            this.WORK_DTTM = WORK_DTTM;
        }

        @Override
        public String toString() {
            return "WifiData{" +
                    "X_SWIFI_MGR_NO='" + X_SWIFI_MGR_NO + '\'' +
                    ", X_SWIFI_WRDOFC='" + X_SWIFI_WRDOFC + '\'' +
                    ", X_SWIFI_MAIN_NM='" + X_SWIFI_MAIN_NM + '\'' +
                    ", X_SWIFI_ADRES1='" + X_SWIFI_ADRES1 + '\'' +
                    ", X_SWIFI_ADRES2='" + X_SWIFI_ADRES2 + '\'' +
                    ", X_SWIFI_INSTL_FLOOR='" + X_SWIFI_INSTL_FLOOR + '\'' +
                    ", X_SWIFI_INSTL_TY='" + X_SWIFI_INSTL_TY + '\'' +
                    ", X_SWIFI_INSTL_MBY='" + X_SWIFI_INSTL_MBY + '\'' +
                    ", X_SWIFI_SVC_SE='" + X_SWIFI_SVC_SE + '\'' +
                    ", X_SWIFI_CMCWR='" + X_SWIFI_CMCWR + '\'' +
                    ", X_SWIFI_CNSTC_YEAR='" + X_SWIFI_CNSTC_YEAR + '\'' +
                    ", X_SWIFI_INOUT_DOOR='" + X_SWIFI_INOUT_DOOR + '\'' +
                    ", X_SWIFI_REMARS3='" + X_SWIFI_REMARS3 + '\'' +
                    ", LAT='" + LAT + '\'' +
                    ", LNT='" + LNT + '\'' +
                    ", WORK_DTTM='" + WORK_DTTM + '\'' +
                    '}';
        }
    }
}
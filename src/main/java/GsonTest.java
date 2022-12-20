import com.google.gson.*;

public class GsonTest {

    public static void gsonTest() {

        String rawString = "{\"TbPublicWifiInfo\":{\"list_total_count\":17769,\"RESULT\":{\"CODE\":\"INFO-000\",\"MESSAGE\":\"정상 처리되었습니다\"},\"row\":[{\"X_SWIFI_MGR_NO\":\"ARI00001\",\"X_SWIFI_WRDOFC\":\"서대문구\",\"X_SWIFI_MAIN_NM\":\"상수도사업본부\",\"X_SWIFI_ADRES1\":\"서소문로 51\",\"X_SWIFI_ADRES2\":\"본관 1F\",\"X_SWIFI_INSTL_FLOOR\":\"\",\"X_SWIFI_INSTL_TY\":\"7-1. 공공 - 행정\",\"X_SWIFI_INSTL_MBY\":\"서울시(AP)\",\"X_SWIFI_SVC_SE\":\"공공WiFi\",\"X_SWIFI_CMCWR\":\"수도사업소자가망\",\"X_SWIFI_CNSTC_YEAR\":\"2014\",\"X_SWIFI_INOUT_DOOR\":\"실내\",\"X_SWIFI_REMARS3\":\"\",\"LAT\":\"37.561924\",\"LNT\":\"126.96675\",\"WORK_DTTM\":\"2022-12-07 10:58:08.0\"}]}}";
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(rawString, JsonObject.class);
        JsonObject element =  jsonObject.getAsJsonObject("TbPublicWifiInfo");
//        JsonArray arr =  element.get("row").getAsJsonArray();
        JsonObject jsonElement =  element.get("row").getAsJsonArray().get(0).getAsJsonObject();
        WifiData wifiData = new Gson().fromJson(jsonElement.toString(), WifiData.class);
        System.out.println(wifiData.X_SWIFI_ADRES1);

//        WifiData strObj = new Gson().fromJson(str,WifiData.class);



//        WifiData wifiData = gson.fromJson(arr,WifiData.class);
//        strObj.LAT.toString();

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
    }

}

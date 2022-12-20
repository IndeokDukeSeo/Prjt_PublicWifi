//import com.google.gson.Gson;
//
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//
//import javax.security.auth.x500.X500Principal;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//
//public class wifiAPI {
//
//    public static ArrayList<String> LoadWifi() throws IOException {
//        String baseUrl = "http://openapi.seoul.go.kr:8088/796753424f6472693833556f437652/json/TbPublicWifiInfo";
//        StringBuilder urlBuilder = new StringBuilder(baseUrl);
//
//        /* 인증키 (sample사용시에는 호출시 제한됩니다.) */
////        urlBuilder.append("/" + URLEncoder.encode("796753424f6472693833556f437652", "UTF-8"));
//
//        /* 요청파일타입 (xml,xmlf,xls,json) */
////        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
//
//        /* 서비스명 (대소문자 구분 필수입니다.) */
////        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
//
//        /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
//        urlBuilder.append("/" + URLEncoder.encode("15001", "UTF-8"));
//        urlBuilder.append("/" + URLEncoder.encode("16000", "UTF-8"));
//
//        /* 서비스별 추가 요청인자들 */
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//
//        /* 연결 자체에 대한 확인이 필요하므로 추가합니다. */
//        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
//        System.out.println("url = " + url);
//        System.out.println("Response code: " + conn.getResponseCode());
////        conn.setRequestProperty("Content-type", "application/xml");
//        BufferedReader rd;
//        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        ArrayList<String> wifiList = new ArrayList<>(); // X500Principal;
//
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//            wifiList.add(line);
//        }
//        rd.close();
//        conn.disconnect();
//        return wifiList;
//    }
//
//    public static void main(String[] args) throws IOException {
//        ArrayList tempArrayList = LoadWifi();
//        System.out.println(tempArrayList.size());
//        System.out.println(tempArrayList);
//    }
//}
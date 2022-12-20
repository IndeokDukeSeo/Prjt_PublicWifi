package importclass;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class jdbcController {

    public static int createRawTables(ArrayList<ConnectWifiApi.WifiData> dto) throws ClassNotFoundException {
        int idx = 0;
        Class.forName("org.sqlite.JDBC");
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/sid/IdeaProjects/PublicWifi/DB_WIFILIST.db");
            PreparedStatement preparedStatement = conn.prepareStatement(getWifiInsertQuery());
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            for (ConnectWifiApi.WifiData data : dto) {
                idx++;
                preparedStatement.setString(1, data.X_SWIFI_MGR_NO);
                preparedStatement.setString(2, data.X_SWIFI_WRDOFC);
                preparedStatement.setString(3, data.X_SWIFI_MAIN_NM);
                preparedStatement.setString(4, data.X_SWIFI_ADRES1);
                preparedStatement.setString(5, data.X_SWIFI_ADRES2);
                preparedStatement.setString(6, data.X_SWIFI_INSTL_FLOOR);
                preparedStatement.setString(7, data.X_SWIFI_INSTL_TY);
                preparedStatement.setString(8, data.X_SWIFI_INSTL_MBY);
                preparedStatement.setString(9, data.X_SWIFI_SVC_SE);
                preparedStatement.setString(10, data.X_SWIFI_CMCWR);
                preparedStatement.setString(11, data.X_SWIFI_CNSTC_YEAR);
                preparedStatement.setString(12, data.X_SWIFI_INOUT_DOOR);
                preparedStatement.setString(13, data.X_SWIFI_REMARS3);
                preparedStatement.setString(14, data.LAT);
                preparedStatement.setString(15, data.LNT);
                preparedStatement.setString(16, data.WORK_DTTM);
                preparedStatement.addBatch();
                System.out.println(idx);

                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
            preparedStatement.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idx;
    }

    public void updateDistance(Double lat, Double lng) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/sid/IdeaProjects/PublicWifi/DB_WIFILIST.db");
            PreparedStatement preparedStatement = conn.prepareStatement(getDistanceUpdateQuery());
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            preparedStatement.setDouble(1, lat);
            preparedStatement.setDouble(2, lng);
            preparedStatement.setDouble(3, lat);

            System.out.println("setting Statement Completed");
            System.out.println("preparedStatement = " + preparedStatement);
            preparedStatement.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
            System.out.println("setting Statement Completed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createUserHistory(Double lnt, Double lat) throws ClassNotFoundException {
        System.out.println("Creating User History");
        Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


        Class.forName("org.sqlite.JDBC");
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/sid/IdeaProjects/PublicWifi/DB_WIFILIST.db");
            PreparedStatement preparedStatement = conn.prepareStatement(getUserInsertQuery());
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            preparedStatement.setString(1, lat.toString());
            preparedStatement.setString(2, lnt.toString());
            preparedStatement.setString(3, sqlDate.toString());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
            conn.commit();
            conn.setAutoCommit(true);
            System.out.println("Saved database successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserHistory> selectUserHistory() throws ClassNotFoundException {
        System.out.println("reading UserHistory");
        List<UserHistory> userHistoryList = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/sid/IdeaProjects/PublicWifi/DB_WIFILIST.db");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(getUserSelectQuery());

            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String histX = resultSet.getString("HIST_X");
                String histY = resultSet.getString("HIST_Y");
                String col = resultSet.getString("COL");

                userHistoryList.add(new UserHistory(id, histX, histY, col));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userHistoryList;
    }

    public List<WifiData> selectWifiList() throws ClassNotFoundException {
        System.out.println("reading WifiList");
        List<WifiData> wifiDataList = new ArrayList<>();
        Class.forName("org.sqlite.JDBC");
        ConnectWifiApi connectWifiApi = new ConnectWifiApi();

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/sid/IdeaProjects/PublicWifi/DB_WIFILIST.db");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(getSelectWifiDbQuery());

            while (resultSet.next()) {
                String distance = resultSet.getString("DISTANCE");
                String mgr_No = resultSet.getString("MGR_NO");
                String wrdodc = resultSet.getString("WRDOFC");
                String main_Nm = resultSet.getString("MAIN_NM");
                String adres1 = resultSet.getString("ADRES1");
                String adres2 = resultSet.getString("ADRES2");
                String instl_Floor = resultSet.getString("INSTL_FLOOR");
                String instl_Ty = resultSet.getString("INSTL_TY");
                String instl_Mby = resultSet.getString("INSTL_MBY");
                String svc_Se = resultSet.getString("SVC_SE");
                String cmcwr = resultSet.getString("CMCWR");
                String cnstc_Year = resultSet.getString("CNSTC_YEAR");
                String inout_Door = resultSet.getString("INOUT_DOOR");
                String remars3 = resultSet.getString("REMARS3");
                String lat = resultSet.getString("LAT");
                String lnt = resultSet.getString("LNT");
                String work_Dttm = resultSet.getString("WORK_DTTM");


                wifiDataList.add(new WifiData(mgr_No, wrdodc,
                        main_Nm, adres1, adres2, instl_Floor, instl_Ty, instl_Mby,
                        svc_Se, cmcwr, cnstc_Year, inout_Door, remars3,
                        lat, lnt, work_Dttm,distance));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wifiDataList;
    }

    public static void deleteUserHistory(int id) throws ClassNotFoundException {
        System.out.println("Deleting UserHistory");
        Class.forName("org.sqlite.JDBC");
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/sid/IdeaProjects/PublicWifi/DB_WIFILIST.db");
            PreparedStatement preparedStatement = conn.prepareStatement(getDeleteUserHistoryQuery());
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Delete finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getUserSelectQuery() {
        return "SELECT ID, HIST_X, HIST_Y, COL FROM USER_HISTORY";
    }

    public static String getWifiInsertQuery() {
        return "INSERT INTO PUBLIC_WIFI_SEOUL_FOR_ENTIRE (" +
                "MGR_NO, WRDOFC,MAIN_NM,ADRES1,ADRES2,INSTL_FLOOR,INSTL_TY," +
                "INSTL_MBY,SVC_SE,CMCWR,CNSTC_YEAR,INOUT_DOOR," +
                "REMARS3,LAT,LNT,WORK_DTTM) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    public static String getUserInsertQuery() {
        return "INSERT INTO USER_HISTORY (" +
                "HIST_X, HIST_Y, COL) " +
                "VALUES (?,?,?)";
    }

    public static String getDistanceUpdateQuery() {
        return "UPDATE PUBLIC_WIFI_SEOUL_FOR_ENTIRE SET DISTANCE = " +
                "(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)" +
                "-radians(?))+sin(radians(?))*sin(radians(lat)))) where 1 = 1";
    }


    public static String getDeleteUserHistoryQuery() {
        return "delete from USER_HISTORY where ID = ?";
    }

    public static String getSelectWifiDbQuery() {
        return "SELECT DISTANCE, MGR_NO,WRDOFC,MAIN_NM,ADRES1,ADRES2,INSTL_FLOOR," +
                "INSTL_MBY,INSTL_TY,SVC_SE,CMCWR,CNSTC_YEAR,INOUT_DOOR," +
                "REMARS3,LAT,LNT,WORK_DTTM " +
                "FROM PUBLIC_WIFI_SEOUL_FOR_ENTIRE " +
                "ORDER BY DISTANCE ASC " +
                "LIMIT 20";
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
        public String DISTANCE;

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

        public WifiData(String x_SWIFI_MGR_NO, String x_SWIFI_WRDOFC, String x_SWIFI_MAIN_NM, String x_SWIFI_ADRES1, String x_SWIFI_ADRES2, String x_SWIFI_INSTL_FLOOR, String x_SWIFI_INSTL_TY, String x_SWIFI_INSTL_MBY, String x_SWIFI_SVC_SE, String x_SWIFI_CMCWR, String x_SWIFI_CNSTC_YEAR, String x_SWIFI_INOUT_DOOR, String x_SWIFI_REMARS3, String LAT, String LNT, String WORK_DTTM, String DISTANCE) {
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
            this.DISTANCE = DISTANCE;
        }
    }

    public class UserHistory {
        String id;
        String histX;
        String histY;
        String col;

        public UserHistory(String id, String histX, String histY, String col) {
            this.id = id;
            this.histX = histX;
            this.histY = histY;
            this.col = col;
        }

        public UserHistory() {
        }

        public String getId() {
            return this.id;
        }

        public String getHistX() {
            return this.histX;
        }

        public String getHistY() {
            return this.histY;
        }

        public String getCol() {
            return this.col;
        }
    }


    public static void main(String[] args) throws ClassNotFoundException {
    }
}
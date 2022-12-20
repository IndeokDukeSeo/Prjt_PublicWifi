<%@ page import="importclass.jdbcController" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script>
        function clickBtn() {
            // BOM의 navigator객체의 하위에 geolocation객체가 새로 추가되었음.
            window.navigator.geolocation.getCurrentPosition(function (position) { //OK
                    var lat = position.coords.latitude;
                    var lng = position.coords.longitude;

                    document.getElementById('target').innerHTML = lat + ", " + lng;
                },
                function (error) { //error
                    switch (error.code) {
                        case error.PERMISSION_DENIED:
                            str = "사용자 거부";
                            break;
                        case error.POSITION_UNAVAILABLE:
                            str = "지리정보 없음";
                            break;
                        case error.TIMEOUT:
                            str = "시간 초과";
                            break;
                        case error.UNKNOWN_ERROR:
                            str = "알수없는 에러";
                            break;
                    }
                    document.getElementById('target').innerHTML = str;
                });
        }

        var id;

        function clickBtn2() {
            id = navigator.geolocation.watchPosition(function (position) {
                var lat = position.coords.latitude;
                var lng = position.coords.longitude;
                document.getElementById("lat").value = lat;
                document.getElementById("lng").value = lng;
            });
        }

    </script>

    <style>
        @import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);

        #customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #customers td, #customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #customers tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        #customers tr:hover {
            background-color: #ddd;
        }

        #customers th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }

        #navi span:nth-child(n+2) {
            position: relative;
            margin-left: 5px;
            padding-left: 10px;
        }

        #navi span:nth-child(n+2)::after {
            position: absolute;
            left: 0;
            top: 3px;
            content: "";
            width: 1px;
            height: 15px;
            background-color: black;
        }

        @font-face {
            font-family: 'SDSamliphopangche_Outline';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        body {
            font-family: SDSamliphopangche_Outline, serif;
        }

    </style>
    <%!
        importclass.jdbcController jdbcController = new jdbcController();
        List<jdbcController.WifiData> wifiList;

        {
            try {
                wifiList = jdbcController.selectWifiList();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    %>

</head>
<body>

<h1>서울시 와이파이 정보구하기</h1>

<div id="navi">
    <span onclick="location.href='index.jsp'" style="cursor:pointer;">홈</span>
    <span onclick="location.href='history.jsp'" style="cursor:pointer;">위치 히스토리 목록</span>
    <span onclick="window.open('load-wifi.jsp')" style="cursor:pointer;">OpenN API 와이파이 정보 가져오기</span>
</div>
<br>
<form action="insertHistoryData.jsp" method="post" target="param" onsubmit="return clickBtn2()"
      style="width:420px;float:left;">
    <input type='text' id='lat' name="lat" placeholder='현재위치를 업데이트 하세요'/>
    <input type='text' id='lng' name="lng" placeholder='현재위치를 업데이트 하세요'/>
    <input type='submit' onclick="clickBtn2()" value='내 위치 가져오기'/>
</form>
<form style="width:600px;float:left;">
    <input type='button' onclick="location.href='index_wifiLoaded.jsp'" value='근처 WIFI 정보보기'/>
</form>

<br>
<iframe id="iframe" name="param" style="display:none"></iframe>


<table id="customers">
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>지역구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    <tr>
        <% for (importclass.jdbcController.WifiData wifiData : wifiList) { %>
        <td><%=wifiData.DISTANCE%>
        </td>
        <td><%= wifiData.getX_SWIFI_MGR_NO() %>
        </td>
        <td><%= wifiData.getX_SWIFI_WRDOFC() %>
        </td>
        <td><%= wifiData.getX_SWIFI_MAIN_NM() %>
        </td>
        <td><%= wifiData.getX_SWIFI_ADRES1() %>
        </td>
        <td><%= wifiData.getX_SWIFI_ADRES2() %>
        </td>
        <td><%= wifiData.getX_SWIFI_INSTL_FLOOR() %>
        </td>
        <td><%= wifiData.getX_SWIFI_INSTL_TY() %>
        </td>
        <td><%= wifiData.getX_SWIFI_INSTL_MBY() %>
        </td>
        <td><%= wifiData.getX_SWIFI_SVC_SE() %>
        </td>
        <td><%= wifiData.getX_SWIFI_CMCWR() %>
        </td>
        <td><%= wifiData.getX_SWIFI_CNSTC_YEAR() %>
        </td>
        <td><%= wifiData.getX_SWIFI_INOUT_DOOR() %>
        </td>
        <td><%= wifiData.getX_SWIFI_REMARS3() %>
        </td>
        <td><%= wifiData.getLAT() %>
        </td>
        <td><%= wifiData.getLNT() %>
        </td>
        <td><%= wifiData.getWORK_DTTM() %>
        </td>
    </tr>
    <%
        }
    %>

</table>


</body>
</html>
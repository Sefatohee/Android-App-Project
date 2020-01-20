package com.example.healthrecord;

public class Constant {
    private static  final String root_url = "http://192.168.31.149/HealthRecord/";
    public  static  final  String url_register = root_url+"Connect.php";
    public  static  final  String url_search = root_url+"bottomQuery.php";
    public static final String url_login = root_url+"userLogin.php";

    public static final String url_updateinfo = root_url+"UpdateInfo.php";


    public static final String url_personalInfo = root_url+"Personal_info.php";


    public static String html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "table, th, td {\n" +
            "  border: 1px solid black;\n" +
            "  border-collapse: collapse;\n" +
            "}\n" +
            "th, td {\n" +
            "  padding: 0px;\n" +
            "  text-align: center;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "\n" +
            "<table style=\"width:100%\">\n" +
            "   <tr>\n" +
            "    <th>Year</th>\n" +
            "    <th>Month</th>\n" +
            "    <th>Date</th>\n" +
            "    <th>SL</th>\n" +
            "    <th>CC</th>\n" +
            "    <th>SR</th>\n" +
            "    <th>DR</th>\n" +
            "    <th>Weight</th>\n" +
            "  </tr>\n" ;
            public static String htmlLast =
            " \n" +
            "</table>\n" +
            "\n" +
            "</body>\n" +
            "</html>\n";



    public static String htmltest = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "table, th, td {\n" +
            "  border: 1px solid black;\n" +
            "  border-collapse: collapse;\n" +
            "}\n" +
            "th, td {\n" +
            "  padding: 0px;\n" +
            "  text-align: center;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "\n" +
            "<table style=\"width:100%\">\n" +
            "   <tr>\n" +
            "    <th>Year</th>\n" +
            "    <th>Month</th>\n" +
            "    <th>Date</th>\n" +
            "    <th>SL/N</th>\n" +
            "    <th>CC/N</th>\n" +
            "    <th>SR/N</th>\n" +
            "    <th>DR/N</th>\n" +
            "    <th>Weight</th>\n" +
            "  </tr>\n" ;

    public static String htmlFinalLast =
            "</table>\n" +
            "</body>\n" +
            "</html>\n";

}

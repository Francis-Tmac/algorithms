package com.frank;


import com.frank.jvm.SoutUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : yangfk5
 * @description :
 * @since : 2023-03-02 11:53
 **/
public class DataTest {

    public static final String PLACEHOLDER_STRING_FORMAT = "\\$\\{%s}";

    private static final String REPLACE_DATABASE_NAME = "\\$\\{databaseName}";
    private static final String REPLACE_TABLE_NAME = "${tableName}";
    private static final String REPLACE_URL = "\\$\\{url}";
    private static final String REPLACE_USERNAME = "\\$\\{username}";
    private static final String REPLACE_PASSWORD = "{password}";
    private static final String REPLACE_QUEUE_NAME = "\\$\\{queueName}";
    private static final String REPLACE_DRIVER = "(?<=(.{1,200}?:[0-9]{1,10}/.{1,200}\\\\?))(.*)\")";
    private static final String EXE_KEY = "(?<=(.+?:[0-9]+/.+?\\?))(.*)";

    private static Pattern IP_PORT_PATTERN = Pattern.compile("(?<=(,|/))(.{1,300}):([0-9]{1,10})|(.+):([0-9]{1,10})");


    private static Pattern IP = Pattern.compile("(.+?:[0-9]+)");

    private static Pattern suffix_param = Pattern.compile("(?<=((.+)?:([0-9]+)?/(.+)?\\?))(.*)");

    private static Pattern PATTERN =
            Pattern.compile("(?<=((password|passWord)(\\s{1,10})))(.+?)(?=\\s)|(?<=(password|passWord)\":\")(.+?)(?=\")");


    public static void main(String[] args) throws URISyntaxException {
        String txt = "/sqoop-1.4.7/bin/sqoop import -Dmapreduce.job.queuename=datamax_sit --connect 'jdbc:mysql://3306-W-T-KGYR-SIT-01-MYC1.service.dc_sd.consul:3306/datamax?useUnicode=true&characterEncoding=utf-8' --username 'datamax' --password '}EB4Ody6XutF' --query 'SELECT `id`,`create_time`,`create_user_name`,`create_user_code`,`update_user_name`,`update_time`,`update_user_code`,\"1\",\"test-234234\",\"453453\",\"frank\",\"4\",\"3\",\"insert\",\"batch_import\",\"batch_import\",\"this is desc\",\"0\",\"1\",\"ds\" from datamax.ops_chain_instance'\" WHERE \\$CONDITIONS\" --m 1  --delete-target-dir --target-dir /tmp/datamax/datamax_sit_hive_transfer_node_partition --null-string '\\\\N' --null-non-string '\\\\N' --fields-terminated-by \",\" --hive-drop-import-delims --fetch-size 10000 --driver com.mysql.cj.jdbc.Driver --mapreduce-job-name 6_datamax_ops_chain_instance:sqoop ";

        String conn = "{\"jdbcUrl\":\"localhost:3306\",\"username\":\"root\",\"password\":\"root\"}";

        String dec = "{\"jdbcUrl\":\"127.0.0.1:3306\",\"username\":\"root\",\"password\":\"WF1xQCz65b9C0vJ+peoN0fwDkxOCK0VScnPda5xXUSthOq6CwRCx5Pyfb7qPEAjkWynnJi10rEedUiKpL78Va2M9C675BV/nY4MhxDSx4KEGp8OM1c/umSyt8Wqy0b9ClpMmje1WaURXQ7FwRl2O1lvZxiJA57FNG6nSKda3hblpqVAwyPu5Rat6h6WMGvlk5z+ycoQuy+Q4P1NSTwEdETUGGBVvkIJBkXKQ+QgpPC1lqNutXx5n2z7os/NQ25jE8WOtjQg4N62fm1p71D6HlDMVzDyYZiLtoRxghyy1Sy6Az/lYw/1B+fQbLZzxCvUISvo+d3ooR2EQgvfstxBEOw==\"}";

        String dd = "wegergedger-2203:3306/mrp_extData?useUnicode=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&rewriteBatchedStatements=true&useAffectedRows=true";

        String ff = "wegergedger-2203:3306/mrp_extData?serverTimezone=Asia/Shanghai";

        System.out.println(getConnKey("10.74.48.75:3306/datamax"));
        String connKey ="10.74.48.75:3306".split("/")[0];
        System.out.println(connKey);
        System.out.println("测试连接失败: " + "开发环境" + ", 连接: " + connKey);
//        StringBuilder coll = new StringBuilder(conn);
//        coll.setCharAt(coll.length() - 1, '\"');
//        System.out.println(coll);

//        String url = getUrlSuffixParam(suffix_param,ff);
//        System.out.println(url);
    }

    static String getIpAndPort(Pattern pattern, String url){
        Matcher matcher = pattern.matcher(url);
        if(matcher.find()){
            System.out.println(matcher.group());
            String param = matcher.group();
        }
        return "";
    }
    public static String getConnKey(String contextKey){
        Matcher matcher = IP_PORT_PATTERN.matcher(contextKey);
        List<String> connKeyList = new ArrayList<>();
        while (matcher.find()){
            connKeyList.add(matcher.group());
        }
        return StringUtils.join(connKeyList,",");
    }

    static String getUrlSuffixParam(Pattern pattern, String url)  {
        Matcher matcher = pattern.matcher(url);
        String useUnicode = "useUnicode";
        String characterEncoding = "characterEncoding";
        Boolean unicodeFlag = Boolean.FALSE;
        Boolean encodingFlag = Boolean.FALSE;
        if(matcher.find()){
            System.out.println(matcher.group());
            String suffix = matcher.group();
            List<String> arr = Lists.newArrayList(suffix.split("&"));
            for(String param : arr){
                if(unicodeFlag && encodingFlag){
                    break;
                }
                if(param.contains(useUnicode)){
                    unicodeFlag = Boolean.TRUE;
                } else if (param.contains(characterEncoding)) {
                    encodingFlag = Boolean.TRUE;
                }
            }
            if(!unicodeFlag){
                arr.add("useUnicode=true");
            }
            if(!encodingFlag){
                arr.add("characterEncoding=utf-8");
            }
            return StringUtils.join(arr,"&");
        }
        return "";
    }

    static String passwordHandler(Pattern pwdPattern, String logMsg) {

        Matcher matcher = pwdPattern.matcher(logMsg);

        StringBuffer sb = new StringBuffer(logMsg.length());

        while (matcher.find()) {


            String maskPassword = "******";

            matcher.appendReplacement(sb, maskPassword);
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    private static String replacePlaceholders(String parameterStr, Map<String, String> scheduleParameters) {
        String finalStr = parameterStr;

        for (Map.Entry<String, String> entry : scheduleParameters.entrySet()) {
            String key = String.format(PLACEHOLDER_STRING_FORMAT, entry.getKey());
            String value = entry.getValue();

            finalStr = finalStr.replace(key, value);
        }
        return finalStr;
    }
}

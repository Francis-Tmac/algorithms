package com.frank;

import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author :
 * @description :
 * @since : 2023/11/10 15:45
 */

public class MatchSql {

    public static final char START = '(';
    public static final char END = ')';

    private final static Pattern PARTITION_SQL = Pattern.compile("(PARTITION)(\\s|\\n){1,100}(BY)(\\s|\\n)([\\w\\W]+?)(?=((COMMENT(\\s|\\n)|(STORED(\\s|\\n){1,100}AS(\\s|\\n){1,100}KUDU))))"
            , Pattern.CASE_INSENSITIVE);

    private final static Pattern PARTITION_SQL_1 = Pattern.compile("(PARTITION)(\\s)+?(BY)(\\s)([\\w\\W]+?)(?=(COMMENT\\s|STORED(\\s)+?AS(\\s)+?KUDU))"
            , Pattern.CASE_INSENSITIVE);


    public static void main(String[] args) {
        String sql = "CREATE TABLE `datamax`.`EXPORT3_INV_INBOUND_NO_KUDU`(\n" +
                "  `tenant_code` STRING,\n" +
                "  `id` BIGINT,\n" +
                "  `partition_time` STRING,\n" +
                "  `receipt_no` STRING,\n" +
                "  `io_type` STRING,\n" +
                "  `receipt_status` INT,\n" +
                "  `warehouse_code` STRING,\n" +
                "  `warehouse_name` STRING,\n" +
                "  `receiver_full_address` STRING,\n" +
                "  `seller_merchant_name` STRING,\n" +
                "  `seller_merchant_code` STRING,\n" +
                "  `receiver_name` STRING,\n" +
                "  `sender_name` STRING,\n" +
                "  `sender_mobile` STRING,\n" +
                "  `create_time` STRING,\n" +
                "  `update_time` STRING,\n" +
                "  `delivery_carrier_code` STRING,\n" +
                "  `driver_name` STRING,\n" +
                "  `contact_number` STRING,\n" +
                "  `remark` STRING,\n" +
                "  `push_status` INT,\n" +
                "  `sale_center_name` STRING,\n" +
                "  `sale_center_code` STRING,\n" +
                "  `upstream_bill_no` STRING,\n" +
                "  `source_bill_no` STRING,\n" +
                "  `way_bill_no` STRING,\n" +
                "  `external_logistics_no` STRING,\n" +
                "  `item_name` STRING,\n" +
                "  `item_code` STRING,\n" +
                "  `sale_main_name` STRING,\n" +
                "  `item_qty` INT,\n" +
                "  `total_receipt_qty` INT,\n" +
                "  `wait_to_wh_qty` INT,\n" +
                "  `total_reject_qty` INT,\n" +
                "  `damage_qty` INT,\n" +
                "  `total_short_qty` INT,\n" +
                "  `total_cancel_qty` INT,\n" +
                "  `head_delete_flag` INT,\n" +
                "  `line_delete_flag` INT,\n" +
                "  `address_delete_flag` INT,\n" +
                "  `source_bill_type` STRING,\n" +
                "  `wait_handle_flag` INT,\n" +
                "  `user_nickname` STRING COMMENT '用户昵称',\n" +
                "  `seller_tenant_code` STRING COMMENT '发货方租户编码',\n" +
                "  `seller_tenant_name` STRING COMMENT '发货方租户名称',\n" +
                "  `store_code` STRING COMMENT '门店编码',\n" +
                "  `store_name` STRING COMMENT '门店名称',\n" +
                "  `deliver_warehouse_code` STRING COMMENT '源发货仓编码',\n" +
                "  `deliver_warehouse_name` STRING COMMENT '源发货仓名称',\n" +
                "  `ext_info` STRING COMMENT '扩展字段',\n" +
                "  `line_ext_info` STRING COMMENT '行扩展字段',\n" +
                "  `inner_trade_flag` STRING COMMENT '是否关联交易',\n" +
                "  `post_flag` INT COMMENT '是否过账',\n" +
                "  `push_fail_reason` STRING COMMENT '推送失败原因',\n" +
                "  `out_platform_no` STRING COMMENT '外部平台单号',\n" +
                "  `logistics_type` INT COMMENT '物流方式：1-物流，2自提',\n" +
                "  `cancel_reason_desc` STRING COMMENT '取消原因描述',\n" +
                "  `line_cancel_reason_desc` STRING COMMENT '行取消原因描述',\n" +
                "  PRIMARY KEY (`tenant_code`, `id`, `partition_time`, `receipt_no`)\n" +
                ") PARTITION BY HASH (tenant_code) PARTITIONS 4,\n" +
                "RANGE (partition_time)(\n" +
                "  PARTITION\n" +
                "    PARTITION '2022-05' <=\n" +
                "  VALUES\n" +
                "    < '2021-11',\n" +
                "    PARTITION '2021-11' <=\n" +
                "  VALUES\n" +
                "    < '2021-12',\n" +
                "    PARTITION '2021-12' <=\n" +
                "  VALUES\n" +
                "    < '2022-01',\n" +
                "    PARTITION '2022-01' <=\n" +
                "  VALUES\n" +
                "    < '2022-02',\n" +
                "    PARTITION '2022-02' <=\n" +
                "  VALUES\n" +
                "    < '2022-03',\n" +
                "    PARTITION '2022-03' <=\n" +
                "  VALUES\n" +
                "    < '2022-04',\n" +
                "    PARTITION '2022-04' <=\n" +
                "  VALUES\n" +
                "    < '2022-05',\n" +
                "    PARTITION '2022-05' <=\n" +
                "  VALUES\n" +
                "    < '2022-06',\n" +
                "    PARTITION '2022-06' <=\n" +
                "  VALUES\n" +
                "    < '2022-07',\n" +
                "    PARTITION '2022-07' <=\n" +
                "  VALUES\n" +
                "    < '2022-08',\n" +
                "    PARTITION '2022-08' <=\n" +
                "  VALUES\n" +
                "    < '2022-09',\n" +
                "    PARTITION '2022-09' <=\n" +
                "  VALUES\n" +
                "    < '2022-10',\n" +
                "    PARTITION '2022-10' <=\n" +
                "  VALUES\n" +
                "    < '2022-11',\n" +
                "    PARTITION '2022-11' <=\n" +
                "  VALUES\n" +
                "    < '2022-12',\n" +
                "    PARTITION '2022-12' <=\n" +
                "  VALUES\n" +
                "    < '2023-01',\n" +
                "    PARTITION '2023-01' <=\n" +
                "  VALUES\n" +
                "    < '2023-02',\n" +
                "    PARTITION '2023-02' <=\n" +
                "  VALUES\n" +
                "    < '2023-03',\n" +
                "    PARTITION '2023-03' <=\n" +
                "  VALUES\n" +
                "    < '2023-04',\n" +
                "    PARTITION '2023-04' <=\n" +
                "  VALUES\n" +
                "    < '2023-05',\n" +
                "    PARTITION '2023-05' <=\n" +
                "  VALUES\n" +
                "    < '2023-06',\n" +
                "    PARTITION '2023-06' <=\n" +
                "  VALUES\n" +
                "    < '2023-07',\n" +
                "    PARTITION '2023-07' <=\n" +
                "  VALUES\n" +
                "    < '2023-08',\n" +
                "    PARTITION '2023-08' <=\n" +
                "  VALUES\n" +
                "    < '2023-09',\n" +
                "    PARTITION '2023-09' <=\n" +
                "  VALUES\n" +
                "    < '2023-10',\n" +
                "    PARTITION '2023-10' <=\n" +
                "  VALUES\n" +
                "    < '2023-11',\n" +
                "    PARTITION '2023-11' <=\n" +
                "  VALUES\n" +
                "  VALUES\n" +
                "    < '2021-11',\n" +
                "    PARTITION '2021-11' <=\n" +
                "  VALUES\n" +
                "    < '2021-12',\n" +
                "    PARTITION '2021-12' <=\n" +
                "  VALUES\n" +
                "    < '2022-01',\n" +
                "    PARTITION '2022-01' <=\n" +
                "  VALUES\n" +
                "    < '2022-02',\n" +
                "    PARTITION '2022-02' <=\n" +
                "  VALUES\n" +
                "    < '2022-03',\n" +
                "    PARTITION '2022-03' <=\n" +
                "  VALUES\n" +
                "    < '2022-04',\n" +
                "    PARTITION '2022-04' <=\n" +
                "  VALUES\n" +
                "    < '2022-05',\n" +
                "    PARTITION '2022-05' <=\n" +
                "  VALUES\n" +
                "    < '2022-06',\n" +
                "    PARTITION '2022-06' <=\n" +
                "  VALUES\n" +
                "    < '2022-07',\n" +
                "    PARTITION '2022-07' <=\n" +
                "  VALUES\n" +
                "    < '2022-08',\n" +
                "    PARTITION '2022-08' <=\n" +
                "  VALUES\n" +
                "    < '2022-09',\n" +
                "    PARTITION '2022-09' <=\n" +
                "  VALUES\n" +
                "    < '2022-10',\n" +
                "    PARTITION '2022-10' <=\n" +
                "  VALUES\n" +
                "    < '2022-11',\n" +
                "    PARTITION '2022-11' <=\n" +
                "  VALUES\n" +
                "    < '2022-12',\n" +
                "    PARTITION '2022-12' <=\n" +
                "  VALUES\n" +
                "    < '2023-01',\n" +
                "    PARTITION '2023-01' <=\n" +
                "  VALUES\n" +
                "    < '2023-02',\n" +
                "    PARTITION '2023-02' <=\n" +
                "  VALUES\n" +
                "    < '2023-03',\n" +
                "    PARTITION '2023-03' <=\n" +
                "  VALUES\n" +
                "    < '2023-04',\n" +
                "    PARTITION '2023-04' <=\n" +
                "  VALUES\n" +
                "    < '2023-05',\n" +
                "    PARTITION '2023-05' <=\n" +
                "  VALUES\n" +
                "    < '2023-06',\n" +
                "    PARTITION '2023-06' <=\n" +
                "  VALUES\n" +
                "    < '2023-07',\n" +
                "    PARTITION '2023-07' <=\n" +
                "  VALUES\n" +
                "    < '2023-08',\n" +
                "    PARTITION '2023-08' <=\n" +
                "  VALUES\n" +
                "    < '2023-09',\n" +
                "    PARTITION '2023-09' <=\n" +
                "  VALUES\n" +
                "    < '2023-10',\n" +
                "    PARTITION '2023-10' <=\n" +
                "  VALUES\n" +
                "    < '2023-11',\n" +
                "    PARTITION '2023-11' <=\n" +
                "  VALUES\n" +
                "    < '2023-12',\n" +
                "    PARTITION '2023-12' <=\n" +
                "  VALUES\n" +
                "    < '2024-01'\n" +
                ") comMENT" +
                "\n 'dsfsdf' STORED AS KUDU";

        Matcher matcher = PARTITION_SQL_1.matcher(sql);

        if(matcher.find()){
            String partitionInfo = matcher.group();
            sql = sql.replace(partitionInfo, "");
            System.out.println(sql);
            System.out.println(partitionInfo);
        }

//        Stack<Character> stack = new Stack<>();
//        char[] charArr = sql.toCharArray();
//        int index = 0;
//        boolean haveChar = false;
//        for(int i = 0 ; i < charArr.length ; i ++){
//            if(START == charArr[i]){
//                haveChar = true;
//                stack.push(START);
//            }else if (END == charArr[i]){
//                stack.pop();
//            }
//            if(haveChar && stack.isEmpty()){
//                index = i;
//                break;
//            }
//        }
//        if(index > 0){
//            System.out.println(sql.substring(0, index+1));
//        }

    }



}

package com.frank;

/**
 * @author :
 * @description :
 * @since : 2023/11/10 14:48
 */
import org.apache.calcite.rel.rel2sql.SqlImplementor;
import org.apache.calcite.sql.dialect.HiveSqlDialect;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlDialect;
//import org.apache.calcite.sql.SqlImplementor;
//import org.apache.calcite.sql.SqlImplementor.Context;
//import org.apache.calcite.sql.SqlImplementor.ResultColumnMapping;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.sql.validate.SqlValidator;
import org.apache.calcite.sql.validate.SqlValidatorImpl;
import org.apache.calcite.sql.validate.SqlValidatorScope;
import org.apache.calcite.sql.validate.SqlValidatorUtil;
import org.apache.calcite.sql.validate.SqlValidatorWithHints;

public class ImpalaToHiveConverter {
    public static void main(String[] args) throws SqlParseException {
        String impalaSql = "SELECT col1, col2 FROM mytable WHERE col3 = 'value'";


    }
}

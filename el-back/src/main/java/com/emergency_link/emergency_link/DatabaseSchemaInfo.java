package com.emergency_link.emergency_link;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseSchemaInfo {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Emergency_Link";
        String username = "root";
        String password = "dorida12";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            // "test3"

            // Hospital_Info 테이블의 열 정보 가져오기
            ResultSet columns = metaData.getColumns(null, null, "patient_info", null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                int columnSize = columns.getInt("COLUMN_SIZE");
                boolean isNullable = columns.getInt("NULLABLE") == DatabaseMetaData.columnNullable;

                System.out.printf("Column Name: %s, Data Type: %s, Column Size: %d, Is Nullable: %s%n",
                        columnName, dataType, columnSize, isNullable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
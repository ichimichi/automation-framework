package com.ichimichi.automation.selenium.framework.utils;

import java.util.Hashtable;

public class TestDataProvider {

    public static Object[][] getTestData(String dataFileName, String sheetName, String testName) {

        ExcelUtil excelUtil = new ExcelUtil(
                System.getProperty("user.dir") + "/src/test/java/com/ichimichi/automation/selenium/framework/data/" + dataFileName);

        int startRowNum = 0;
        while (!excelUtil.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
            startRowNum++;
        }

        int startTestColumn = startRowNum + 1;
        int startTestRow = startRowNum + 2;

        int rows = 0;
        while (!excelUtil.getCellData(sheetName, 0, startTestRow + rows).equals("")) {
            rows++;
        }

        int columns = 0;
        while (!excelUtil.getCellData(sheetName, columns, startTestColumn).equals("")) {
            columns++;
        }

        Object[][] dataSet = new Object[rows][1];
        Hashtable<String, String> dataTable;
        int dataRowNumber=0;
        for (int rowNumber = startTestRow; rowNumber <= startTestColumn + rows; rowNumber++) {
            dataTable = new Hashtable<String, String>();
            for (int columnNumber = 0; columnNumber < columns; columnNumber++) {
                String key = excelUtil.getCellData(sheetName, columnNumber, startTestColumn);
                String value = excelUtil.getCellData(sheetName, columnNumber, rowNumber);
                dataTable.put(key, value);
            }
            dataSet[dataRowNumber][0]=dataTable;
            dataRowNumber++;
        }
        return dataSet;
    }
}

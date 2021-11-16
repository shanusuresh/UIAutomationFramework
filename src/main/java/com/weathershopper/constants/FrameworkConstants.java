package com.weathershopper.constants;

import com.weathershopper.enums.ConfigProperties;
import com.weathershopper.utils.PropertyFileUtil;

public final class FrameworkConstants {
    private static final int EXPLICITWAIT = 30;
    private static final String RESOURCESPATH = System.getProperty("user.dir")+"/src/test/resources";
    private static final String CONFIGFILEPATH = RESOURCESPATH+"/config/config.properties";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir")+"/extent-test-output/";
    private static final String EXCELPATH = RESOURCESPATH+"/excel/testdata.xlsx";
    private static final String RUNMANGERSHEET = "RUNMANAGER";
    private static final String ITERATIONDATASHEET = "DATA";
    private static String extentReportFilePath = "";
    /**
     * Framework Constants holds all the constant values used within the framework. If some value needs to be changed
     *  or modified often, then it should be stored in the property files
     */
    private FrameworkConstants() {}

    public static String getRunmangerDatasheet() {
        return RUNMANGERSHEET;
    }

    public static String getIterationDatasheet() {
        return ITERATIONDATASHEET;
    }

    public static String getConfigFilePath() {
        return CONFIGFILEPATH;
    }

    public static String getExcelFilePath() {
        return EXCELPATH;
    }

    public static int getExplicitWait() {
        return EXPLICITWAIT;
    }


    public static String getExtentReportFilePath()  {
        if(extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }

    private static String createReportPath()  {
        if(PropertyFileUtil.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")) {
            return EXTENTREPORTFOLDERPATH+System.currentTimeMillis()+"/index.html";
        }
        else {
            return EXTENTREPORTFOLDERPATH+"/index.html";
        }
    }
}

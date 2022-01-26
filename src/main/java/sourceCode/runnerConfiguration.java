package sourceCode;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class runnerConfiguration {
        public static String screenshot_dir_path;
        public static String project_path = System.getProperty("user.dir");
        static DateFormat date_format = new SimpleDateFormat("MMM-dd-yyyy");
        static DateFormat time_format = new SimpleDateFormat("HH-mm-ss");
        public static String date;
        public static String time;
        public static String reports_dir;

        public static String get_date()
        {
            return runnerConfiguration.date;
        }

        public static String  set_get_date()
        {
            Date d = new Date();
            runnerConfiguration.date = date_format.format(d);
            return runnerConfiguration.date;
        }

        public static String get_time()
        {
            return runnerConfiguration.time;
        }

        public static String set_get_time()
        {
            Date d = new Date();
            runnerConfiguration.time = time_format.format(d);
            return runnerConfiguration.time;
        }

        public static String  create_and_set_reports_directory()
        {

            String date = get_date();
            String reports_dir = project_path + "/" + "reports" + "/" + date;
            File dir = new File(reports_dir);
            try
            {
                if (!dir.exists()){
                    dir.mkdirs();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            runnerConfiguration.reports_dir = reports_dir;
            return reports_dir;
        }

    public static void create_and_set_screenshot_directory(String test_case_name)
    {

        String reports_dir = runnerConfiguration.reports_dir;
        String time = runnerConfiguration.get_time();
        String path = reports_dir + "/" + test_case_name +"_Screenshots_"+time;
        File dir = new File(path);
        try
        {
            if (!dir.exists()){
                dir.mkdirs();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        runnerConfiguration.screenshot_dir_path = path;
    }




    }

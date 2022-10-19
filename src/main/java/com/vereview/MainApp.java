package com.vereview;

import com.aspose.imaging.License;
import com.vereview.context.ApplicationContext;
import com.vereview.csv.*;
import com.vereview.dao.ConnectionManager;
import com.vereview.dao.PageInfoDao;
import com.vereview.dao.PageType;
import com.vereview.error.ErrorManager;
import com.vereview.export.ErrorDto;
import com.vereview.export.ImageCallable;
import com.vereview.export.RowDto;
import com.vereview.message.ImageMessage;
import com.vereview.model.PageInfo;
import com.vereview.process.PngConvert;
import com.vereview.utils.ExportUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by mjmangan on 9/16/17.
 */
public class MainApp {
    private static final Logger logger = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args){
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("log4j.properties")){
            Path logDir = Paths.get(".." + File.separator + "log");
            if(!Files.exists(logDir.toAbsolutePath())){
                Files.createDirectory(logDir);
            }
            String strPath = logDir.toAbsolutePath().toString();
            System.out.println("Log Directory: " +  strPath);
            Properties prop = new Properties();
            prop.load(in);
            prop.replace("log4j.appender.imageExport.file", Paths.get(strPath, "image-export.log").toAbsolutePath().toString());
            LogManager.resetConfiguration();
            PropertyConfigurator.configure(prop);
            setLicense();
            Boolean isLicensed = License.isLicensed();
            ApplicationContext context = ApplicationContext.getInstance();
            String dbName = getDbName(args);
            Environment environment = getEnv(args);
            context.init(dbName, environment);
            ConnectionManager mgr = ConnectionManager.getInstance();
            mgr.init(context);
            Path exportDir = getExportDir(args);
            PageType pageType = getPageType(args);
            ErrorManager errorManager = ErrorManager.getInstance();
            PngConvert pngConvert = PngConvert.getInstance();

            PageInfoDao dao = new PageInfoDao(mgr);
            List<PageInfo> pageInfos = dao.getPageInfos(pageType);
            int folderNum = 1;
            int count = 0;
            int exportFileCount = 0;

            ExecutorService threadPool = Executors.newFixedThreadPool(context.getThreadCount());
            List<ImageCallable> imageCallables = new ArrayList<>();
            Map<Integer, OptData> d = new HashMap<>();

            for (PageInfo info : pageInfos){
                if(info.getPageLocation() != null) {
                    RowDto dto = new RowDto(info, folderNum);
                    OptRow row = dto.getOptRow();
                    ImageMessage message = new ImageMessage();
                    message.setPageInfo(info);
                    message.setExportFile(Paths.get(exportDir.toAbsolutePath().toString(), row.getRelativePath()));
                    message.setExtension(ExportUtils.getExtension(info.getPageLocation()));
                    imageCallables.add(new ImageCallable(message));
                    if(count % context.getOptMaxRecords() == 0){
                        exportFileCount++;
                        OptData data = new OptData();
                        List<OptRow> rows = new ArrayList<>();
                        rows.add(row);
                        data.setRows(rows);
                        d.put(exportFileCount, data);
                    }else{
                        d.get(exportFileCount).getRows().add(row);
                    }

                    count++;
                    folderNum = ExportUtils.incrementFolder(count, folderNum);
                }else {
                    ErrorDto dto = new ErrorDto(info);
                    String docId = dto.createDocumentId();
                    errorManager.getErrorMap().put(docId, new ErrorRow(docId, "No file Pointer to Page File"));
                }
            }
            System.out.println("Image Count: " + imageCallables.size());
            List<Future<ImageMessage>> imageFutures = threadPool.invokeAll(imageCallables);
            System.out.println("Opt File Count: " + d.size());


            for(Integer optNumber : d.keySet()){
                Path exportOpt = getOptFile(exportDir, optNumber);
                OptData optData = d.get(optNumber);
                OptWriter writer = new OptWriter(optData);
                writer.createOpt(exportOpt);
            }

            ErrorWriter errorWriter = new ErrorWriter(errorManager.getErrorMap());
            Path errorFile = Paths.get(exportDir.toAbsolutePath().toString(), "ERROR.csv");
            errorWriter.createOpt(errorFile);

            if(!threadPool.isShutdown()){
                threadPool.shutdown();
            }

        }catch (Throwable t){
            logger.error("error in main app", t);
        }
    }

    public static Path getOptFile(Path exportDir, Integer optNumber){
        return Paths.get(exportDir.toAbsolutePath().toString(), "EXPORT_" + optNumber + ".opt");
    }

    public static void setLicense() throws IOException{
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("Aspose.Total.Java.lic")){
            License license = new License();
            license.setLicense(in);
        }catch (IOException ioe){
            throw ioe;
        }
    }

    public static Environment getEnv(String[] args){
        for(String arg : args){
            if(arg.startsWith(Arguments.ENVIRONMENT.getAttribute())){
                String[] parts = arg.split("=");
                if(parts.length == 2){
                    return Environment.getEnvironment(parts[1]);
                }else {
                    throwIllegalArgumentException(arg);
                }
            }
        }
        throw new IllegalArgumentException("no env attr");
    }

    public static String getDbName(String[] args){
        for(String arg : args){
            if(arg.startsWith(Arguments.DB_NAME.getAttribute())){
                String[] parts = arg.split("=");
                if(parts.length == 2){
                    return parts[1];
                }else {
                    throwIllegalArgumentException(arg);
                }
            }
        }
        throw new IllegalArgumentException("no database name directory");
    }

    public static Path getExportDir(String[] args){
        for(String arg : args){
            if(arg.startsWith(Arguments.EXPORT_DIR.getAttribute())){
                String[] parts = arg.split("=");
                if(parts.length == 2){
                    return Paths.get(parts[1]);
                }else {
                    throwIllegalArgumentException(arg);
                }
            }
        }
        throw new IllegalArgumentException("no export directory");
    }

    public static PageType getPageType(String[] args){
        for(String arg : args){
            if(arg.startsWith(Arguments.IMAGE_TYPE.getAttribute())){
                String[] parts = arg.split("=");
                if(parts.length == 2){
                    return PageType.getPageType(parts[1]);
                }else {
                    throwIllegalArgumentException(arg);
                }
            }
        }
        throw new IllegalArgumentException("No Image Type");
    }

    public static void throwIllegalArgumentException(String arg){
        throw new IllegalArgumentException("invalid argument | " + arg);
    }

    public enum Arguments{
        EXPORT_DIR("exportDir"),
        DB_NAME("dbName"),
        IMAGE_TYPE("imageType"),
        ENVIRONMENT("env");

        private String attribute;

        Arguments(String attribute) {
            this.attribute = attribute;
        }

        public String getAttribute() {
            return attribute;
        }
    }

    public enum Environment {
        DEV("production/conf/application.properties"),
        PROD("../conf/application.properties");

        private String propertiesFileName;

        Environment(String propertiesFileName) {
            this.propertiesFileName = propertiesFileName;
        }

        public String getPropertiesFileName() {
            return propertiesFileName;
        }

        public static Environment getEnvironment(String env){
            return Environment.valueOf(env.toUpperCase());
        }
    }
}

package com.vereview.export;

import com.vereview.context.ApplicationContext;
import com.vereview.csv.ErrorRow;
import com.vereview.error.ErrorManager;
import com.vereview.message.ImageMessage;
import com.vereview.process.Processor;
import com.vereview.process.ProcessorFactory;
import com.vereview.utils.ExportUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import java.util.concurrent.Callable;

/**
 * Created by mjmangan on 10/2/17.
 */
public class ImageCallable implements Callable<ImageMessage>{
    private ApplicationContext context = ApplicationContext.getInstance();
    private ErrorManager errorManager = ErrorManager.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(ImageMessage.class);
    private ImageMessage message;

    public ImageCallable(ImageMessage message) {
        this.message = message;
    }

    @Override
    public ImageMessage call() throws Exception{
        try {
            //Thread.sleep(1000);
            Processor processor = ProcessorFactory.getProcessor(message.getExtension());
            processor.copy(message.getPageInfo().getPageLocation(), message.getExportFile());
            System.out.println("DEBUG | Document Id: " + message.getPageInfo().getDocumentId() + " | Image File Export | From: " + message.getPageInfo().getPageLocation() + " | To: " + message.getExportFile());

        }catch (NoSuchFileException nsfe){
            ErrorDto dto = new ErrorDto(message.getPageInfo());
            String docId = dto.createDocumentId();
            errorManager.getErrorMap().put(docId, new ErrorRow(docId, "No File At End Of The Pointer"));
            logger.error("No File at end of the pointer: " + context.getDbName() + " | " + message.toString(), nsfe);
        }catch (FileSystemException fse){
            ErrorDto dto = new ErrorDto(message.getPageInfo());
            String docId = dto.createDocumentId();
            errorManager.getErrorMap().put(docId, new ErrorRow(docId, fse.getMessage()));
            logger.error("No File at end of the pointer: " + context.getDbName() + " | " + message.toString(), fse);
        }catch (Throwable t){
            logger.error("Error in image copy for Case: " + context.getDbName() + " | " + message.toString(), t);
        }
        return message;
    }

}

package com.vereview;

import com.vereview.context.ApplicationContext;
import com.vereview.csv.OptData;
import com.vereview.csv.OptRow;
import com.vereview.csv.OptWriter;
import com.vereview.dao.ConnectionManager;
import com.vereview.dao.PageInfoDao;
import com.vereview.dao.PageType;
import com.vereview.export.RowDto;
import com.vereview.model.PageInfo;
import com.vereview.utils.ExportUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjmangan on 10/1/17.
 */
public class FullTest {

    /*
    @Test
    public void createOptTest(){
        try {
            ApplicationContext context = ApplicationContext.getInstance();
            context.init("case_13_3413", MainApp.Environment.DEV);
            ConnectionManager mgr = ConnectionManager.getInstance();
            mgr.init(context);

            PageInfoDao dao = new PageInfoDao(mgr);
            List<PageInfo> pageInfos = dao.getPageInfos(PageType.ORIGINAL);
            List<OptRow> rows = new ArrayList<>();


            int folderNum = 1;
            int count = 0;
            for (PageInfo info : pageInfos){
                if(info.getPageLocation() != null) {
                    RowDto dto = new RowDto(info, folderNum);
                    OptRow row = dto.getOptRow();
                    rows.add(row);
                    count++;
                    folderNum = ExportUtils.incrementFolder(count, folderNum);
                }else {
                    System.out.println(info.toString());
                }
            }
            OptData data = new OptData();
            data.setRows(rows);
            Path exportFile = Files.createTempFile("VE_OPT_", ".opt");
            OptWriter writer = new OptWriter(data);
            writer.createOpt(exportFile);
            System.out.println(exportFile.toAbsolutePath().toString());

        }catch (Throwable t){
            Assert.fail(ExceptionUtils.getStackTrace(t));
        }
    }
    */
}

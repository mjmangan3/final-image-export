package com.vereview.dao;

import com.vereview.model.PageInfo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjmangan on 9/16/17.
 */
public class PageInfoDao {
    private ConnectionManager mgr;

    public PageInfoDao(ConnectionManager mgr) {
        this.mgr = mgr;
    }

    public List<PageInfo> getPageInfos(PageType pageType) throws SQLException{
        List<PageInfo> pageInfos = new ArrayList<>();
        try (Connection c = mgr.getDataSource().getConnection()){
            PreparedStatement ps = c.prepareStatement("select f.file_id, f.number, p.number, pl.location, x.page_count from [file] f join page p on(f.file_id = p.file_id) left join page_location pl on(p.page_id = pl.page_id and pl.type = ?) join (select file_id as file_id, MAX(number) as page_count from page group by FILE_ID) as x on(f.file_id = x.file_id) where f.viewable = 1 order by f.number, p.number");
            ps.setString(1, pageType.getType());
            ResultSet r = ps.executeQuery();
            while (r.next()){
                pageInfos.add(marshall(r));
            }
        }catch (SQLException se){
            throw se;
        }
        return pageInfos;
    }

    public PageInfo marshall(ResultSet r) throws SQLException{
        Path file = null;
        PageInfo i = new PageInfo();
        i.setFileId(r.getLong(1));
        i.setDocumentId(r.getString(2));
        i.setPageNumber(r.getLong(3));
        String l = r.getString(4);
        if(r.wasNull()){
            i.setPageLocation(file);
        }else {
            i.setPageLocation(Paths.get(l));
        }
        i.setPageCount(r.getLong(5));
        return i;
    }
}

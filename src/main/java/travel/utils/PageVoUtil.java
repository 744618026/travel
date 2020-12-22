package travel.utils;

import travel.vo.PageVo;

import java.util.List;

public class PageVoUtil {
    public static PageVo getPage(Integer page, Integer size, List list, List voList){
        PageVo pageVo = new PageVo<>();
        pageVo.setPage(page);
        pageVo.setSize(size);
        Double d = Math.ceil((list.size()*1.0)/(size*1.0));
        pageVo.setTotal(d.intValue());
        pageVo.setData(voList);
        return pageVo;
    }
}

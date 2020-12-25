package travel.vo;

import java.io.Serializable;
import java.util.List;

public class PageVo<T> implements Serializable{
    private static final long serialVersionUID = -5378752619990244124L;
    Integer size;

    Integer page;

    Integer total;

    List<T> data;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    public void setTotal(List list) {
        this.total = list.size();
    }
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

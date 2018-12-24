package ${package};

import javax.persistence.Transient;

public class BaseEntity {
    @Transient //不在数据库映射
    private String order;
    @Transient
    private String sort;
    @Transient
    private String group;
    @Transient
    private Integer pageSize;
    @Transient
    private Integer pageNum;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
    this.sort = sort;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}

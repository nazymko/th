/**
 * This class is generated by jOOQ
 */
package org.nazymko.th.parser.autodao.tables.pojos;


import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.7.2"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
@Entity
@Table(name = "task_schedule", schema = "thehomeland")
public class TaskSchedule implements Serializable {

    private static final long serialVersionUID = -464974952;

    private Integer id;
    private Integer siteid;
    private String startPage;
    private String pageType;
    private Timestamp startAt;
    private String cron;
    private Boolean isEnabled;

    public TaskSchedule() {
    }

    public TaskSchedule(TaskSchedule value) {
        this.id = value.id;
        this.siteid = value.siteid;
        this.startPage = value.startPage;
        this.pageType = value.pageType;
        this.startAt = value.startAt;
        this.cron = value.cron;
        this.isEnabled = value.isEnabled;
    }

    public TaskSchedule(
            Integer id,
            Integer siteid,
            String startPage,
            String pageType,
            Timestamp startAt,
            String cron,
            Boolean isEnabled
    ) {
        this.id = id;
        this.siteid = siteid;
        this.startPage = startPage;
        this.pageType = pageType;
        this.startAt = startAt;
        this.cron = cron;
        this.isEnabled = isEnabled;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false, precision = 10)
    @NotNull
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "siteId", precision = 10)
    public Integer getSiteid() {
        return this.siteid;
    }

    public void setSiteid(Integer siteid) {
        this.siteid = siteid;
    }

    @Column(name = "start_page", nullable = false, length = 256)
    @NotNull
    @Size(max = 256)
    public String getStartPage() {
        return this.startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }

    @Column(name = "page_type", nullable = false, length = 64)
    @NotNull
    @Size(max = 64)
    public String getPageType() {
        return this.pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    @Column(name = "start_at", nullable = false)
    @NotNull
    public Timestamp getStartAt() {
        return this.startAt;
    }

    public void setStartAt(Timestamp startAt) {
        this.startAt = startAt;
    }

    @Column(name = "cron", length = 64)
    @Size(max = 64)
    public String getCron() {
        return this.cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    @Column(name = "is_enabled")
    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TaskSchedule (");

        sb.append(id);
        sb.append(", ").append(siteid);
        sb.append(", ").append(startPage);
        sb.append(", ").append(pageType);
        sb.append(", ").append(startAt);
        sb.append(", ").append(cron);
        sb.append(", ").append(isEnabled);

        sb.append(")");
        return sb.toString();
    }
}
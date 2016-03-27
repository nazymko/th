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
@Table(name = "th_page", schema = "thehomeland")
public class ThPage implements Serializable {

    private static final long serialVersionUID = -2108902469;

    private Integer id;
    private String url;
    private Integer siteId;
    private Timestamp visitedAt;
    private String type;
    private Integer version;
    private Timestamp registeredAt;
    private Integer sourcepage;
    private Integer taskRunId;
    private String siteUrl;

    public ThPage() {
    }

    public ThPage(ThPage value) {
        this.id = value.id;
        this.url = value.url;
        this.siteId = value.siteId;
        this.visitedAt = value.visitedAt;
        this.type = value.type;
        this.version = value.version;
        this.registeredAt = value.registeredAt;
        this.sourcepage = value.sourcepage;
        this.taskRunId = value.taskRunId;
        this.siteUrl = value.siteUrl;
    }

    public ThPage(
            Integer id,
            String url,
            Integer siteId,
            Timestamp visitedAt,
            String type,
            Integer version,
            Timestamp registeredAt,
            Integer sourcepage,
            Integer taskRunId,
            String siteUrl
    ) {
        this.id = id;
        this.url = url;
        this.siteId = siteId;
        this.visitedAt = visitedAt;
        this.type = type;
        this.version = version;
        this.registeredAt = registeredAt;
        this.sourcepage = sourcepage;
        this.taskRunId = taskRunId;
        this.siteUrl = siteUrl;
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

    @Column(name = "url", length = 512)
    @Size(max = 512)
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "site_id", precision = 10)
    public Integer getSiteId() {
        return this.siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Column(name = "visited_at")
    public Timestamp getVisitedAt() {
        return this.visitedAt;
    }

    public void setVisitedAt(Timestamp visitedAt) {
        this.visitedAt = visitedAt;
    }

    @Column(name = "type", length = 32)
    @Size(max = 32)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "version", nullable = false, precision = 10)
    @NotNull
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "registered_at")
    public Timestamp getRegisteredAt() {
        return this.registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    @Column(name = "sourcePage", nullable = false, precision = 10)
    @NotNull
    public Integer getSourcepage() {
        return this.sourcepage;
    }

    public void setSourcepage(Integer sourcepage) {
        this.sourcepage = sourcepage;
    }

    @Column(name = "task_run_id", nullable = false, precision = 10)
    @NotNull
    public Integer getTaskRunId() {
        return this.taskRunId;
    }

    public void setTaskRunId(Integer taskRunId) {
        this.taskRunId = taskRunId;
    }

    @Column(name = "site_url")
    public String getSiteUrl() {
        return this.siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ThPage (");

        sb.append(id);
        sb.append(", ").append(url);
        sb.append(", ").append(siteId);
        sb.append(", ").append(visitedAt);
        sb.append(", ").append(type);
        sb.append(", ").append(version);
        sb.append(", ").append(registeredAt);
        sb.append(", ").append(sourcepage);
        sb.append(", ").append(taskRunId);
        sb.append(", ").append(siteUrl);

        sb.append(")");
        return sb.toString();
    }
}

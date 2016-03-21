package com.renison.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "progress")
public class Progress extends BaseModel {
    @OneToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull
    private Category category;

    @Column(name = "start_at", columnDefinition = "timestamp with time zone", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startAt;

    @Column(name = "end_at", columnDefinition = "timestamp with time zone", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endAt;

    @ManyToOne
    @JoinColumn(name = "test_session_id")
    private TestSession testSession;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    // public TestSession getTestSession() {
    //     return testSession;
    // }

    // public void setTestSession(TestSession testSession) {
    //     this.testSession = testSession;
    // }

}

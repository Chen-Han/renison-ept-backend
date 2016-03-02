package com.renison.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.apache.log4j.Logger;
import com.renison.model.BaseModel;

public class BaseModelListener {

    private static Logger logger = Logger.getLogger(BaseModelListener.class);

    @PrePersist
    public void prePersist(BaseModel model) {
        System.out.println("================works ============");
    }

    @PreUpdate
    public void preUpdate(BaseModel model) {
        System.out.println("================pre update works ============");
    }
}

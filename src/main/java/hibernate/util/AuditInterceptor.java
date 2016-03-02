package hibernate.util;

import java.io.Serializable;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class AuditInterceptor extends EmptyInterceptor {

    private int updates;
    private int creates;
    private int loads;

    @Override
    public void onDelete(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        // do nothing
    }

    @Override
    public boolean onLoad(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        return false;
    }

    @Override
    public boolean onSave(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        System.out.println("it works ...");
        return false;
    }

}
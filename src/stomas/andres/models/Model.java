package stomas.andres.models;

import java.util.Vector;

public abstract class Model {
    public static String table;
    protected Vector<Object> selectAll(){
        return select(new String[]{"*"}, null, null);
    }
    protected abstract Vector<Object> select(String[] select, String[] where, Object[] values);
    protected abstract void insert(Object object, Class<?> type);
}

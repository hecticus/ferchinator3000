package util;

import io.ebean.Ebean;

import io.ebean.Finder;
import io.ebean.Model;
import models.BaseModel;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 *
 * @param <K>
 * @param <E>
 */
public abstract class AbstractDao<K, E> {

    public Finder<K, E> find;
    private Class<E> type;
    private String ID = "id";

    public AbstractDao(Class<E> type){
        this.type = type;
        find = new Finder<K, E>(type);
    }

    public E create(E entity){
        ((BaseModel) entity).setId(null);
        ((Model) entity).save();
        return entity;
    }

    public E update(E entity){
        //warm: si no exite el <id> pero tampoco se le manda actualizar campos, no genera una excepción
        ((Model) entity).update();
        return entity;
    }

    public boolean delete(K id) {
        E entity = find.byId(id);
        ((Model) entity).delete();
        return true;
    }

    public boolean delete(List<K> ids) {
        // con esta sentencia no se puede borrar por cascada
        /*String sql = "DELETE FROM " + this.type.getAnnotation(Table.class).name() + " WHERE id IN ( :ids ) ";
        SqlUpdate upd = Ebean.createSqlUpdate(sql);
        upd.setParameter("ids", ids);
        upd.execute();*/

        //List<E> entities = find.where().idIn(ids).findList();
        List<E> entities = find.query().filterMany(ID).idIn(ids).findList();

        if(entities.size() < ids.size())
            throw new EntityNotFoundException("Some element not found");
        Ebean.deleteAll(entities);
        return true;
    }

    public E findById(K id){
        E entity = find.byId(id);
        return entity;
    }

    public List<E> findAll(){
        List<E> entities = find.all();
        return entities;
    }

/*    public ListPager findAll(Integer pageIndex, Integer pageSize){
        if(pageIndex < 0 || pageSize < 0)
            return new ListPager(find.all(), find.findRowCount(), pageIndex, pageSize);
        return new ListPager(find.findPagedList(pageIndex, pageSize).getList(), find.findRowCount(), pageIndex, pageSize);
    }

 */

}
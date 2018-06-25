package pst.asu.beans.task;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@LocalBean
@Stateless
public class TaskDAOBean {

    @PersistenceContext (unitName = "reminder-app")
    private EntityManager entityManager;

    public TblTaskEntity read(int id){
        return entityManager.find(TblTaskEntity.class, id);
    }


    public List<TblTaskEntity> readList() {

        TypedQuery<TblTaskEntity> query = entityManager.createQuery(
                "from TblTaskEntity entity",
                TblTaskEntity.class);

        List<TblTaskEntity> tblTaskEntities = query.getResultList();

        return tblTaskEntities;
    }

    public int getTotalCount(){
        Long rez = entityManager.createQuery(
                "select count (entity.idTask) from TblTaskEntity entity",
                Long.class)
                .getSingleResult();
        return rez.intValue();
    }
}

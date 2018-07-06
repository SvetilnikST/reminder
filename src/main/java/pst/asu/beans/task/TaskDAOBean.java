package pst.asu.beans.task;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Date;


@LocalBean
@Stateless
public class TaskDAOBean {

    @PersistenceContext(unitName = "reminder-app")
    private EntityManager entityManager;

    public TblTaskEntity read(int idTask) {
        return entityManager.find(TblTaskEntity.class, idTask);
    }

    public List<TblTaskEntity> readTaskList() {

        TypedQuery<TblTaskEntity> query = entityManager.createQuery(
                "from TblTaskEntity entity",
                TblTaskEntity.class);

        List<TblTaskEntity> tblTaskEntities = query.getResultList();

        return tblTaskEntities;
    }

    public int getTotalCount() {
        Long rez = entityManager.createQuery(
                "select count (entity.idTask) from TblTaskEntity entity",
                Long.class)
                .getSingleResult();
        return rez.intValue();
    }
}

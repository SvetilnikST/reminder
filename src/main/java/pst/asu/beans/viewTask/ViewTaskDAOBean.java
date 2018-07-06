package pst.asu.beans.viewTask;

import pst.asu.beans.task.TblTaskEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@LocalBean
@Stateless
public class ViewTaskDAOBean {

    @PersistenceContext (unitName = "reminder-app")
    private EntityManager entityManager;

    public TblViewTaskEntity read(int id){
        return entityManager.find(TblViewTaskEntity.class, id);
    }

    public List<TblViewTaskEntity> readViewTaskList() {

        TypedQuery<TblViewTaskEntity> query = entityManager.createQuery(
                "from TblViewTaskEntity entity",
                TblViewTaskEntity.class);

        List<TblViewTaskEntity> tblViewTaskEntities = query.getResultList();

        return tblViewTaskEntities;
    }

    public int getTotalCount(){
        Long rez = entityManager.createQuery(
                "select count (entity.idViewTask) from TblViewTaskEntity entity",
                Long.class)
                .getSingleResult();
        return rez.intValue();
    }
}

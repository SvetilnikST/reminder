package pst.asu.entity.auth;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import java.util.List;

@LocalBean
@Stateless
public class RolesDAOBean {
    @PersistenceContext(unitName = "reminder-app")
    private EntityManager entityManager;

    //CRUD
    public RolesEntity read(int id) {
        return entityManager.find(RolesEntity.class, id);
    }

    public RolesEntity read(String roleName) {
        TypedQuery<RolesEntity> query = entityManager.createQuery(
                "select entity from RolesEntity entity where entity.role=:role",
                RolesEntity.class);

        RolesEntity rolesEntities = query
                .setParameter("role",roleName)
                .getSingleResult();
        return rolesEntities;
    }

    public List<RolesEntity> readRolesList() {
        TypedQuery<RolesEntity> query = entityManager.createQuery(
                "select entity from RolesEntity entity",
                RolesEntity.class);
        List<RolesEntity> rolesEntities = query.getResultList();
        return rolesEntities;
    }

    public boolean create(RolesEntity rolesEntity) {

        RolesEntity existingRole = entityManager.find(RolesEntity.class, rolesEntity.getIdRole());
        if (existingRole == null) {
            entityManager.persist(rolesEntity);
            return true;
        }
        return false;
    }


    public boolean update(RolesEntity rolesEntity) {
            entityManager.merge(rolesEntity);
            return true;
    }

    public boolean delete(int id) {

        RolesEntity existingRole = entityManager.find(RolesEntity.class, id);
        if (existingRole == null) {
            return false;
        }
        entityManager.remove(existingRole);
        return true;
    }

    private String getTableName() {
        Table table = UserEntity.class.getAnnotation(Table.class);
        return table.name();
    }
}

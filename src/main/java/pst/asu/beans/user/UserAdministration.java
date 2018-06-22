package pst.asu.beans.user;

import pst.asu.beans.department.DepartmentDAOBean;
import pst.asu.beans.department.TblDepartmentEntity;
import pst.asu.entity.auth.RolesDAOBean;
import pst.asu.entity.auth.RolesEntity;
import pst.asu.entity.auth.UserEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@ManagedBean(name = "userAdministration")
@ViewScoped
public class UserAdministration implements Serializable {
    private UserEntity userEntity;
    private List<UserAdministration> userAdministrations;
    private List<RolesEntity> roles;
    private String[] rolesToSet;
    private String[] rolesToSet1;
    private List<RolesEntity> rolesEntityList;
    private List<String> myTest;
    private List<TblDepartmentEntity> departmentList;
    private String stope;
    private Integer myid;

    @EJB
    private UserDAOBean userDAOBean;

    @EJB
    private RolesDAOBean rolesDAOBean;

    @EJB
    private DepartmentDAOBean departmentDAOBean;

    @Inject
    private UserBean userBean;
    private int id;
    private String username;
    private String auth_key;
    private String password_hash;
    private String password_reset_token;
    private String email;
    private short status;
    private Timestamp created_at;
    private Timestamp updated_at;
    TblDepartmentEntity departmentEntity;
    private String password;
    private Boolean updatePassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(Boolean updatePassword) {
        this.updatePassword = updatePassword;
    }

    public Integer getMyid() {
        return myid;
    }

    public void setMyid(Integer myid) {
        this.myid = myid;
    }

    public String getStope() {
        return stope;
    }

    public void setStope(String stope) {
        this.stope = stope;
    }

    public String[] getRolesToSet1() {
        return rolesToSet1;
    }

    public void setRolesToSet1(String[] rolesToSet1) {
        this.rolesToSet1 = rolesToSet1;
    }

    private List<UserEntity> userEntitiesList;

    public List<String> getMyTest() {
        return myTest;
    }

    public void setMyTest(List<String> myTest) {
        this.myTest = myTest;
    }

    public List<UserEntity> getUserEntitiesList() {
        return userEntitiesList;
    }

    public void setUserEntitiesList(List<UserEntity> userEntitiesList) {
        this.userEntitiesList = userEntitiesList;
    }

    public List<RolesEntity> getRolesEntityList() {
        return rolesEntityList;
    }

    public void setRolesEntityList(List<RolesEntity> rolesEntityList) {
        this.rolesEntityList = rolesEntityList;
    }

    public List<TblDepartmentEntity> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<TblDepartmentEntity> departmentList) {
        this.departmentList = departmentList;
    }


    @PostConstruct
    void start() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("idUser");
        String paramMode = parameterMap.get("mode");//mode=new

        myTest = new ArrayList<>();
        if (param != null) {
            id = Integer.parseInt(param);
            this.userEntity = userDAOBean.read(id);
            if (userEntity == null) {
                String message = "Bad request. Unknown user.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(userEntity);

        } else {
            this.username = "";
            this.email = "";
            this.status = 10;
            TblDepartmentEntity dep = departmentDAOBean.read(20);
            this.departmentEntity = dep;
            rolesToSet = new String[0];
        }


        userEntitiesList = userDAOBean.readUserList();
        rolesEntityList = userDAOBean.readRolesList();

        for (RolesEntity onerol : rolesEntityList) {
            myTest.add(onerol.getRole());
        }

        departmentList = departmentDAOBean.readList();
    }

    public void load(UserEntity userEntity) {
        this.setId(userEntity.getId());
        this.setUsername(userEntity.getUsername());
        this.setAuth_key(userEntity.getAuth_key());
        this.setPassword_hash(userEntity.getPassword_hash());
        this.setPassword_reset_token(userEntity.getPassword_reset_token());
        this.setEmail(userEntity.getEmail());
        this.setStatus(userEntity.getStatus());
        this.setCreated_at(timstampFromInt(userEntity.getCreated_at()));
        this.setUpdated_at(timstampFromInt(userEntity.getUpdated_at()));
        this.setDepartmentEntity(userEntity.getDepartmentEntity());

        roles = new ArrayList<>();
        List<String> tmpUserRoleList = new ArrayList<>();
        for (RolesEntity oneUserRoleEntity : userEntity.getUserRoleEntitySet()) {
            roles.add(oneUserRoleEntity);
            tmpUserRoleList.add(oneUserRoleEntity.getRole());
        }
        rolesToSet = new String[tmpUserRoleList.size()];
        rolesToSet = tmpUserRoleList.toArray(rolesToSet);
        int a = 0;
    }


    public String save() {
        Set<RolesEntity> userRoleEntitySet = new HashSet<>();
        RolesEntity tmpRole;
        int a = rolesToSet.length;
        for (int i = 0; i < a; i++) {
            tmpRole = rolesDAOBean.read(rolesToSet[i]);
            userRoleEntitySet.add(tmpRole);
        }

        userEntity = userDAOBean.read(this.id);
        if (userEntity == null) {
            userEntity = new UserEntity();
        }

        userEntity.setUserRoleEntitySet(userRoleEntitySet);

        userEntity.setUsername(this.username);
        userEntity.setEmail(this.email);
        userEntity.setStatus(this.status);
        userEntity.setDepartmentEntity(departmentEntity);
        if (updatePassword) {
            userEntity.setPassword_hash(userBean.generateHashPass(password));
            userEntity.setAuth_key("Переделать");
        }

        if (userEntity.getId() == 0) {
            userEntity.setStatus((short) 10);
            userDAOBean.create(userEntity);
        } else {
            userDAOBean.update(userEntity);
        }
        return "viewUser.xhtml?faces-redirect=true&idUser=" + String.valueOf(userEntity.getId());
    }

    public String[] getRolesToSet() {
        //int a = rolesToSet.length;
        return rolesToSet;
    }

    public void setRolesToSet(String[] rolesToSet) {
        this.rolesToSet = rolesToSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getPassword_reset_token() {
        return password_reset_token;
    }

    public void setPassword_reset_token(String password_reset_token) {
        this.password_reset_token = password_reset_token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public TblDepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(TblDepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    private Timestamp timestampFromDate(Date fromDate) {
        return new Timestamp(fromDate.getTime());
    }

    public Timestamp timstampFromInt(int valueToTimestamp) {
        Long temp = valueToTimestamp * 1000L;
        return new Timestamp(temp);
    }

    public List<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEntity> roles) {
        this.roles = roles;
    }
}

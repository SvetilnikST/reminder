package pst.asu.beans.user;

import pst.asu.beans.department.TblDepartmentEntity;
import pst.asu.beans.task.TblTaskEntity;
import pst.asu.entity.auth.RolesEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column (name = "auth_key",length = 32,nullable = false)
    private     String auth_key;

    @Column(name = "password_hash",nullable = false)
    private String password_hash;

    @Column(name = "password_reset_token")
    private String password_reset_token;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "status",nullable = false)
    private short status;

    @Column(name = "created_at",nullable = false)
    private int created_at;

    @Column(name = "updated_at",nullable = false)
    private int updated_at;

    @ManyToMany ( fetch = FetchType.EAGER)
    @JoinTable(name = "tblUserRole",
            //foreign key for CarsEntity in employee_car table
            joinColumns = @JoinColumn(name = "idUser"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Set<RolesEntity> userRoleEntitySet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "department")
    private TblDepartmentEntity departmentEntity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
    private Set<TblTaskEntity> tblTaskEntitySet = new HashSet<>();

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

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }

    public Set<RolesEntity> getUserRoleEntitySet() {
        return userRoleEntitySet;
    }

    public void setUserRoleEntitySet(Set<RolesEntity> userRoleEntitySet) {
        this.userRoleEntitySet = userRoleEntitySet;
    }

    public TblDepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(TblDepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    public Set<TblTaskEntity> getTblTaskEntitySet() {
        return tblTaskEntitySet;
    }

    public void setTblTaskEntitySet(Set<TblTaskEntity> tblTaskEntitySet) {
        this.tblTaskEntitySet = tblTaskEntitySet;
    }
}

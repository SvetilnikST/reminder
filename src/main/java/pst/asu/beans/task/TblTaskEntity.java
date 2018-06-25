package pst.asu.beans.task;

import pst.asu.entity.auth.UserEntity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "tblTask")

public class TblTaskEntity {

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column(name = "idTask", nullable = false)
  private int idTask;

  @Column(name = "name", length = 320, nullable = false)
  private String name;

  @Column(name = "dateStarted", nullable = true)
  private Timestamp dateStarted;

  @Column(name = "dateEnd", nullable = true)
  private Timestamp dateEnd;

  @Column(name = "executor", nullable = false)
  private String executor;

  @ManyToOne
  @JoinColumn(name = "viewTask")
  private TblTaskEntity tblTaskEntity;

  @ManyToOne
  @JoinColumn(name = "user")
  private UserEntity userEntity;

  public int getIdTask() {
    return idTask;
  }

  public void setIdTask(int idTask) {
    this.idTask = idTask;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Timestamp getDateStarted() {
    return dateStarted;
  }

  public void setDateStarted(Timestamp dateStarted) {
    this.dateStarted = dateStarted;
  }

  public Timestamp getDateEnd() {
    return dateEnd;
  }

  public void setDateEnd(Timestamp dateEnd) {
    this.dateEnd = dateEnd;
  }

  public String getExecutor() {
    return executor;
  }

  public void setExecutor(String executor) {
    this.executor = executor;
  }

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public void setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

  public TblTaskEntity getTblTaskEntity() {
    return tblTaskEntity;
  }

  public void setTblTaskEntity(TblTaskEntity tblTaskEntity) {
    this.tblTaskEntity = tblTaskEntity;
  }
}

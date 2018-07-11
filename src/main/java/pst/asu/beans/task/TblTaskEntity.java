package pst.asu.beans.task;

import pst.asu.beans.viewTask.TblViewTaskEntity;
import pst.asu.beans.user.UserEntity;
import javax.persistence.*;
import java.sql.Timestamp;

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
  @JoinColumn(name = "idViewTask")
  private TblViewTaskEntity tblViewTaskEntity;

  @ManyToOne
  @JoinColumn(name = "idUser")
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

    public TblViewTaskEntity getTblViewTaskEntity() {
        return tblViewTaskEntity;
    }

    public void setTblViewTaskEntity(TblViewTaskEntity tblViewTaskEntity) {
        this.tblViewTaskEntity = tblViewTaskEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}

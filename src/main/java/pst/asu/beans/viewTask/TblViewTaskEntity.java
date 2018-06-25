package pst.asu.beans.viewTask;

import pst.asu.beans.task.TblTaskEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tblViewTask")
public class TblViewTaskEntity {

  @Id
  @Column (name = "idViewTask", nullable = false)
  private long idViewTask;
  @Column(name="viewTask", nullable = false, length = 20)
  private String viewTask;

  public long getIdViewTask() {
    return idViewTask;
  }

  public void setIdViewTask(long idViewTask) {
    this.idViewTask = idViewTask;
  }

  public String getViewTask() {
    return viewTask;
  }

  public void setViewTask(String viewTask) {
    this.viewTask = viewTask;
  }

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "viewTaskEntity")
  private Set<TblTaskEntity> taskEntitySet = new HashSet<>();

    public Set<TblTaskEntity> getTaskEntitySet() {
        return taskEntitySet;
    }

    public void setTaskEntitySet(Set<TblTaskEntity> taskEntitySet) {
        this.taskEntitySet = taskEntitySet;
    }
}
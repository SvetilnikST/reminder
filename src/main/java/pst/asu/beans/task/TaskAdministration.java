package pst.asu.beans.task;

import pst.asu.beans.department.DepartmentDAOBean;
import pst.asu.beans.department.TblDepartmentEntity;
import pst.asu.beans.user.UserBean;
import pst.asu.beans.viewTask.TblViewTaskEntity;
import pst.asu.beans.viewTask.ViewTaskDAOBean;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@ManagedBean(name = "taskAdministration")
@ViewScoped
public class TaskAdministration implements Serializable {
    private TblTaskEntity tblTaskEntity;
    private List<TaskAdministration> taskAdministrations;
    private List<TblDepartmentEntity> departmentList;
    private List<TblViewTaskEntity> viewTaskList;
    private List<String> myTest;
    private String stope;
    private Integer myid;

    @EJB
    private TaskDAOBean taskDAOBean;

    @EJB
    private DepartmentDAOBean departmentDAOBean;

    @EJB
    private ViewTaskDAOBean viewTaskDAOBean;

    @Inject
    private UserBean userBean;
    private int idTask;
    private String name;
    private Timestamp dateStarted;
    private Timestamp dateEnd;
    private String executor;
    TblDepartmentEntity departmentEntity;
    TblViewTaskEntity viewTaskEntity;

    private List<TblTaskEntity> tblTaskEntitysList;

    @PostConstruct
    void start() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        Map<String, String> parameterMap = (Map<String, String>) externalContext.getRequestParameterMap();
        String param = parameterMap.get("idTask");
        myTest = new ArrayList<>();
        if (param != null) {
            idTask = Integer.parseInt(param);
            this.tblTaskEntity = taskDAOBean.read(idTask);
            if (tblTaskEntity == null) {
                String message = "Bad request. Unknown task.";
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            }
            load(tblTaskEntity);

        } else {
            this.name = "";
            this.dateStarted = null;
            this.dateEnd = null;
            this.executor = "";
            TblViewTaskEntity view = viewTaskDAOBean.read(2);
            this.viewTaskEntity = view;
            TblDepartmentEntity dep = departmentDAOBean.read(20);
            this.departmentEntity = dep;

        }
        tblTaskEntitysList = taskDAOBean.readTaskList();
        viewTaskList = viewTaskDAOBean.readViewTaskList();
        departmentList = departmentDAOBean.readList();
    }

    public void load(TblTaskEntity taskEntity){
        this.setIdTask(taskEntity.getIdTask());
        this.setName(taskEntity.getName());
        this.setDateStarted(taskEntity.getDateStarted());
        this.setDateEnd(taskEntity.getDateEnd());
        this.setExecutor(taskEntity.getExecutor());
        this.setViewTaskEntity(tblTaskEntity.getTblViewTaskEntity());
        this.setDepartmentEntity(userBean.getDepartmentEntity());

    }




    public TblTaskEntity getTblTaskEntity() {
        return tblTaskEntity;
    }

    public void setTblTaskEntity(TblTaskEntity tblTaskEntity) {
        this.tblTaskEntity = tblTaskEntity;
    }

    public List<TaskAdministration> getTaskAdministrations() {
        return taskAdministrations;
    }

    public void setTaskAdministrations(List<TaskAdministration> taskAdministrations) {
        this.taskAdministrations = taskAdministrations;
    }

    public List<TblDepartmentEntity> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<TblDepartmentEntity> departmentList) {
        this.departmentList = departmentList;
    }

    public List<TblViewTaskEntity> getViewTaskList() {
        return viewTaskList;
    }

    public void setViewTaskList(List<TblViewTaskEntity> viewTaskList) {
        this.viewTaskList = viewTaskList;
    }

    public List<String> getMyTest() {
        return myTest;
    }

    public void setMyTest(List<String> myTest) {
        this.myTest = myTest;
    }

    public String getStope() {
        return stope;
    }

    public void setStope(String stope) {
        this.stope = stope;
    }

    public Integer getMyid() {
        return myid;
    }

    public void setMyid(Integer myid) {
        this.myid = myid;
    }

    public TaskDAOBean getTaskDAOBean() {
        return taskDAOBean;
    }

    public void setTaskDAOBean(TaskDAOBean taskDAOBean) {
        this.taskDAOBean = taskDAOBean;
    }

    public DepartmentDAOBean getDepartmentDAOBean() {
        return departmentDAOBean;
    }

    public void setDepartmentDAOBean(DepartmentDAOBean departmentDAOBean) {
        this.departmentDAOBean = departmentDAOBean;
    }

    public ViewTaskDAOBean getViewTaskDAOBean() {
        return viewTaskDAOBean;
    }

    public void setViewTaskDAOBean(ViewTaskDAOBean viewTaskDAOBean) {
        this.viewTaskDAOBean = viewTaskDAOBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

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

    public TblDepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(TblDepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    public TblViewTaskEntity getViewTaskEntity() {
        return viewTaskEntity;
    }

    public void setViewTaskEntity(TblViewTaskEntity viewTaskEntity) {
        this.viewTaskEntity = viewTaskEntity;
    }

    public List<TblTaskEntity> getTblTaskEntitysList() {
        return tblTaskEntitysList;
    }

    public void setTblTaskEntitysList(List<TblTaskEntity> tblTaskEntitysList) {
        this.tblTaskEntitysList = tblTaskEntitysList;
    }
}

package action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import dao.i.CourseScheduleDaoI;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.i.*;
import team.jiangtao.entity.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by tose on 2017/4/12.
 */
@Namespace("/teacher")
@ParentPackage("ssmis-default")
@Controller
@Scope(value = "prototype")
public class TeacherAction extends ActionSupport {
    private CommentServiceI commentServiceI;
    private AppealServiceI appealServiceI;
    private Teacher teacher;
    private Appeal appeal;
    private Comment comment;
    private Integer operation;
    private String rsp;
    private TeacherServiceI teacherServiceI;
    private Map<String,Object> session=new HashMap<>();
    private String isRememberPsw;
    private long date;
    private String tid;
    private CourseScheduleServiceI courseScheduleServiceI;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Resource(name = "commentService")
    public void setCommentServiceI(CommentServiceI commentServiceI) {
        this.commentServiceI = commentServiceI;
    }

    @Resource(name = "appealService")
    public void setAppealServiceI(AppealServiceI appealServiceI) {
        this.appealServiceI = appealServiceI;
    }

    @Resource(name="csService")
    public void setCourseScheduleServiceI(CourseScheduleServiceI courseScheduleServiceI) {
        this.courseScheduleServiceI = courseScheduleServiceI;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setAppeal(Appeal appeal) {
        this.appeal = appeal;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }


    public String getRsp() {
        return rsp;
    }

    public void setRsp(String rsp) {
        this.rsp = rsp;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getIsRememberPsw() {
        return isRememberPsw;
    }

    public void setIsRememberPsw(String isRememberPsw) {
        this.isRememberPsw = isRememberPsw;
    }

    @Resource(name = "TeacherService")
    public void setTeacherServiceI(TeacherServiceI teacherServiceI) {
        this.teacherServiceI = teacherServiceI;
    }

    @Action(value = "login",results = @Result(type = "json",params={"root","session"}))
    public String teacherLogin(){
        //write to test.
        isRememberPsw="0";
        rsp="0";
        String tid=teacher.getTchId();
        String tpw=teacher.getPassword();
        Teacher teacher2=new Teacher();
        teacher=teacherServiceI.findTeacherbuid(tid);
        teacher2.setTchId(teacher.getTchId());
        teacher2.setDpmId(teacher.getDpmId());
        teacher2.setPassword(teacher.getPassword());
        teacher2.setName(teacher.getName());
        teacher2.setDepartmentByDpmId(teacher.getDepartmentByDpmId());
        if(tpw.equals(teacher2.getPassword())){
            session.put("tch", teacher2);
            rsp="1";
            session.put("rsp",rsp);
        };
        return SUCCESS;
    }

    @Action(value = "selfInfo",results = @Result(type = "json",params={"root","teacher"}))
    public String  getSelfInfo(){
        teacher =  teacherServiceI.findTeacherbuid(tid);
        return SUCCESS;
    }

    @Action(value = "updateInfo", results = @Result(type = "json",params={"root",""}))
    public String updateTeacherInfo(){
        teacherServiceI.updateTeacherInfo(teacher);
        return SUCCESS;
    }


    public String teahcerLogout(){
        //TODO
        return SUCCESS;

    }


    public String modifyTeacher(){
        //TODO
        return SUCCESS;

    }

    public String addCourses(){
        //TODO
        return SUCCESS;

    }

    public String addCourse(){
        //TODO
        return SUCCESS;

    }

    public String modifyCourses(){
        //TODO
        return SUCCESS;

    }

    public String modifyCourse(){
        //TODO
        return SUCCESS;

    }

    public String deleteCourses(){
        //TODO
        return SUCCESS;

    }

    public String deleteCourse(){
        //TODO
        return SUCCESS;
    }

    public String addExams(){
        //TODO
        return SUCCESS;
    }

    public String addExam(){
        //TODO
        return SUCCESS;
    }

    public String modifyExams(){
        //TODO
        return SUCCESS;
    }

    public String modifyExam(){
        //TODO
        return SUCCESS;
    }

    /**
     * @author Jiang Tao
     * @return SUCCESS
     */
    @Action(value = "addAppeals",results = @Result(type = "json",params={"root","rsp"}))
    public String addAppeal(){
        //TODO

        return  SUCCESS;
    }

    /**
     * @author Jiang Tao
     * @return SUCCESS
     */
    @Action(value = "updateAppeals",results = @Result(type = "json",params={"root","rsp"}))
    public String updateAppeal(){
        Date dd = new Date(date);
        appeal.setDate(new java.sql.Date(dd.getTime()));
//        System.out.println(appeal.toString());
        List<Appeal> appeals = new ArrayList<>();
        appeals.add(appeal);
        boolean flag = appealServiceI.updateAppeals(appeals);
        rsp = "{\"result\" : \""+flag+"\"}";
        return SUCCESS;
    }

    public Appeal getAppeal() {
        return appeal;
    }

    @Action(value = "pullTchCrs",results = @Result(type = "json", params = {"root","rsp"}))
    public String pullTchCrs(){
        List list = courseScheduleServiceI.findCSbytwo("00001");
        return SUCCESS;
    }

    /**
     * @author Jiang Tao
     * @return SUCCESS

     */
    @Action(value = "getAppeals",results = @Result(type = "json",params={"root","rsp"}))
    public String getAppealByType() throws Exception {
        //TO FINISH
        Map<String,Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("tch","00001");//Instead of Session
        stringObjectMap.put("type",operation);
//        teacher.setName("aaaaa");
        List list = appealServiceI.getAppealsByCondition(stringObjectMap,true);
        List<Appeal> temp =(List<Appeal>) list;
        List<AppealInfo> appealInfos = new ArrayList<>();
        for(Appeal ap:temp){
            AppealInfo appealInfo = new AppealInfo();
            appealInfo.setDpmId(ap.getDpmId());
            appealInfo.setCrsId(ap.getCrsId());
            appealInfo.setTchId(ap.getTchId());
            appealInfo.setStuId(ap.getStuId());
            appealInfo.setDate(ap.getDate());
            appealInfo.setContent(ap.getContent());
            appealInfo.setResponse(ap.getResponse());
            appealInfo.setStatus(ap.getStatus());
            appealInfo.setDepartmentName(ap.getDepartmentByDpmId().getDpmName());
            appealInfo.setTeacherName("tchname");
            appealInfo.setStudentName(ap.getStudentByStuId().getName());
            appealInfo.setStuGender(ap.getStudentByStuId().getGender());
            appealInfo.setStuGrade(ap.getStudentByStuId().getGrade());
            appealInfo.setStuClassNo(ap.getStudentByStuId().getClassNo());

            appealInfos.add(appealInfo);
        }
        rsp = JSON.toJSONString(appealInfos);
//        System.out.println(rsp);
        return SUCCESS;
    }

    /**
     * @author Jiang Tao
     * @return SUCCESS
     */
    public String getComments(){
       //TODO

        return SUCCESS;
    }

    /**
     * @author Jiang Tao
     * @return SUCCESS
     */
    public String addCommnets(){
        //TODO

        return SUCCESS;
    }

    /**
     * @author Jiang Tao
     * @return SUCCESS
     */
    public String updateComments(){
        //TODO
        return SUCCESS;
    }

    /**
     * @author Jiang Tao
     * @return SUCCESS
     */
    public String deleteCommnets(){
        //TODO

        return SUCCESS;
    }

    /**
     * @author Jiang Tao
     * @return SUCCESS
     */
    public String getStatic(){
        //TODO
        return SUCCESS;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}

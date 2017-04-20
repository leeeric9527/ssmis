package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.i.CourseServiceI;
import team.jiangtao.entity.CourseSchedule;
import team.jiangtao.entity.CoursesTable;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lihuibo on 4/14/17.
 */
@Namespace("/course")
@ParentPackage("ssmis-default")
@Controller
@Scope(value = "prototype")
public class CourseAction extends ActionSupport {
    private CourseServiceI courseService;
    private CourseSchedule courseSchedule;
    private List<CourseSchedule> courseSchedules;
    private List<CoursesTable> coursesTableList;

    @Action(value = "getCourseInfo", results = @Result(type = "json", params = {"root", "courseSchedule"}))
    public String getCourseInfo() {
        courseSchedule=courseService.getCourseInfo(courseSchedule.getCrsId(),courseSchedule.getDpmId(),courseSchedule.getTchId());
        return SUCCESS;
    }

    @Action(value = "getCoursesInfo", results = @Result(type = "json", params = {"root", "courseList"}))
    public String getCoursesInfo() {
        return SUCCESS;
    }

    @Action(value = "getCourseTable", results = @Result(type = "json", params = {"root", "coursesTableList"}))
    public String getCourseTable() {

        return SUCCESS;
    }

    @Resource(name = "courseService")
    public void setCourseService(CourseServiceI courseService) {
        this.courseService = courseService;
    }
}

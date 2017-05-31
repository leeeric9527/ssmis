package service.impl;

import dao.i.CommentDaoI;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import service.i.CommentServiceI;
import team.jiangtao.entity.Comment;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by tose on 2017/5/23.
 */
@Service(value = "commnetService")
public class CommentServiceImpl implements CommentServiceI{
    private CommentDaoI commentDaoI;

    public CommentDaoI getCommentDaoI() {
        return commentDaoI;
    }

    @Resource(name = "appealDao")
    public void setCommentDaoI(CommentDaoI commentDaoI) {
        this.commentDaoI = commentDaoI;
    }

    @Override
    @Transactional(readOnly = true,isolation = Isolation.READ_COMMITTED)
    public List<Comment> getCommentsByConditions(Map<String, Object> condition, boolean... equalCondition) throws Exception {
        return commentDaoI.getCommentsByConditions(condition);
    }

    @Override
    @Transactional(readOnly = true,isolation = Isolation.READ_COMMITTED)
    public Comment getCommentByPK(Comment comment) throws Exception {
        return null;
    }

    @Override
    @Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED)
    public boolean addComments(List<Comment> comments) {
        return false;
    }

    @Override
    @Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED)
    public boolean updateComments(List<Comment> comments) {
        return false;
    }

    @Override
    @Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED)
    public boolean deleteComments(List<Comment> comments) {
        return false;
    }
}

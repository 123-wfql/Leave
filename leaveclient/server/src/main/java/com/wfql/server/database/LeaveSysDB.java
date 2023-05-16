package com.wfql.server.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.wfql.server.dao.AdminDao;
import com.wfql.server.dao.DepartmentDao;
import com.wfql.server.dao.FeedbackDao;
import com.wfql.server.dao.InstitutionDao;
import com.wfql.server.dao.LeaveDao;
import com.wfql.server.dao.LeaveTypeDao;
import com.wfql.server.dao.LoginDao;
import com.wfql.server.dao.NewsDao;
import com.wfql.server.dao.RuleDao;
import com.wfql.server.dao.UserDao;
import com.wfql.server.dao.UserTypeDao;
import com.wfql.server.entity.Admin;
import com.wfql.server.entity.Department;
import com.wfql.server.entity.Feedback;
import com.wfql.server.entity.Institution;
import com.wfql.server.entity.Leave;
import com.wfql.server.entity.LeaveType;
import com.wfql.server.entity.Login;
import com.wfql.server.entity.News;
import com.wfql.server.entity.Rule;
import com.wfql.server.entity.User;
import com.wfql.server.entity.UserType;

@Database(entities = {Login.class, Admin.class, Department.class, Feedback.class, Institution.class,
        Leave.class, LeaveType.class, News.class, Rule.class, User.class, UserType.class},
        version = 2, exportSchema = true)
public abstract class LeaveSysDB extends RoomDatabase {
    public abstract LoginDao loginDao();
    public abstract AdminDao adminDao();
    public abstract DepartmentDao departmentDao();
    public abstract FeedbackDao feedbackDao();
    public abstract InstitutionDao institutionDao();
    public abstract LeaveDao leaveDao();
    public abstract LeaveTypeDao leaveTypeDao();
    public abstract NewsDao newsDao();
    public abstract RuleDao ruleDao();
    public abstract UserDao userDao();
    public abstract UserTypeDao userTypeDao();
}

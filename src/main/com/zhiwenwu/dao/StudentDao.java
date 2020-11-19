package main.com.zhiwenwu.dao;
import main.com.zhiwenwu.entity.Student;
import main.com.zhiwenwu.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentDao {

    public void add(Student student) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            sqlSession.insert("Student.insert", student);
            sqlSession.commit();//提交才有事务
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }
    public Student find(int id) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            return sqlSession.selectOne("Student.findID", id);
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }
    public List<Student> findAll() throws Exception{
        //得到连接对象
        SqlSession sqlsession = MybatisUtil.getSqlSession();
        return sqlsession.selectList("Student.findAll");
    }

    public void deleteByID(int id) throws Exception{
        //得到连接对象
        SqlSession sqlsession = MybatisUtil.getSqlSession();
        try {
            sqlsession.delete("Student.deleteByID",id);
            sqlsession.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            sqlsession.rollback();
        }
    }

    //先取出对象，再修改，然后更新提交。
    public void update(Student student ) throws Exception {
        //得到连接对象
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        try{
            //映射文件的命名空间.SQL片段的ID，就可以调用对应的映射文件中的SQL
            sqlSession.update("Student.update", student);
            sqlSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            sqlSession.rollback();
            throw e;
        }finally{
            MybatisUtil.closeSqlSession();
        }
    }

    public static void main(String[] args) throws Exception {
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.find(2);
        student.setName("fucheng");
        student.setSal(2000);
        studentDao.update(student);



    }

}

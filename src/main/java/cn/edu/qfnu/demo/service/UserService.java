package cn.edu.qfnu.demo.service;

import cn.edu.qfnu.demo.entity.User;
import cn.edu.qfnu.demo.model.RequestPageResult;

import java.util.List;

public interface UserService {
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 分页查询
     * @param pageNum 页码
     * @param pageSize 分页大小
     * @return
     */
    RequestPageResult<User> findPage(Integer pageNum, Integer pageSize);

    /**
     * 分页查询
     * @param pageNum 页码
     * @param pageSize 分页大小
     * @param user 查询参数
     * @return 查询结果
     */
    RequestPageResult<User> findPage(Integer pageNum, Integer pageSize, User user);

    /**
     * 保存或更新
     * @param user
     * @return
     */
    void saveOrUpdate(User user);

    /**
     * 根据Id删除
     * @param id
     */
    void removeById(Integer id);

}

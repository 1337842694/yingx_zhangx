package com.zhangxin;

import com.zhangxin.dao.AdminDao;
import com.zhangxin.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroSpringbootApplicationTests {

    @Autowired
    AdminDao adminDao;

    /*
     * Admin(
     * id=1,
     * username=zhangsan,
     * password=b8d63fc060e2b5651e8cb4e71ba61e6f,
     * salt=asdfaf,
     * roles=[
     *   Role(
     *       id=2,
     *       roleName=superAdmin,
     *       authorities=[
     *           Authority(id=1, name=admin:query),
     *           Authority(id=2, name=admin:delete),
     *           Authority(id=3, name=admin:update),
     *           Authority(id=4, name=admin:insert)]),
     *   Role(
     *       id=1,
     *       roleName=common,
     *       authorities=[]),
     *   Role(
     *       id=4,
     *       roleName=user,
     *       authorities=[]),
     *   Role(
     *       id=3,
     *       roleName=admin,
     *       authorities=[
     *           Authority(id=5, name=user:select),
     *           Authority(id=6, name=user:update)])])

     * */

    @Test
    public void contextLoads() {
        Admin admin = adminDao.queryAllMessage("zhangsan");
        System.out.println(admin);
    }

}

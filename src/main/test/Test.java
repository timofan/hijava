import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.Session;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.system.SystemUtil;

import java.sql.SQLException;

/**
 * Demo class
 *
 * @author felfan
 * @date 2019/12/09
 */
public class Test {
    public static void main(String[] args) throws SQLException {

        //昨天
        DateUtil.yesterday();
//明天
        DateUtil.tomorrow();
//上周
        DateUtil.lastWeek();
//下周
        DateUtil.nextWeek();
//上个月
        DateUtil.lastMonth();
//下个月
        DateUtil.nextMonth();



        System.out.printf("" + DateUtil.isLeapYear('1'));
        //默认数据源
        Session session = Session.create();

        TimeInterval timer = DateUtil.timer();

//---------------------------------
//-------这是执行过程
//---------------------------------


//        timer.intervalRestart();//返回花费时间，并重置开始时间
//        timer.intervalMinute();//花费分钟数

        try {
            session.beginTransaction();
            // 增，生成SQL为 INSERT INTO `table_name` SET(`字段1`, `字段2`) VALUES(?,?)
            for (int i=0;i<50000;i++){
                Entity entity = Entity.create("t_test").set("name", "值").set("code", i);
//                if (i==100){
//                    entity = Entity.create("t_test").set("name", "值").set("code", "2a");
//                }

                session.insert(entity);
            }

            session.commit();
        } catch (SQLException e) {
            System.out.println(e);
            session.quietRollback();
        }

        System.out.printf("插入花费时间" +timer.interval()+""); //花费毫秒数


    }



}

//package demo;
//
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author
// * @since 2018/12/13
// */
//public class CodeGenerator {
//    /**
//     * 请自行修改一下配置
//     */
////    输出路径，修改为自己项目的路径，不用加src/main
//    public static final String OUTPUTPATH = "D:\\Code\\baima";
//    //    作者名称
//    public static final String AUTHOR = "WJX";
//    //    数据库用户名
//    public static final String DataSourceUSERNAME = "root";
//    //    数据库密码
//    public static final String DataSourceUSERPASSWORD = "190112";
//    //    逆向生成的数据库表
//    public static final String TABLENAME = "trial_lesson";
//
//    @Test
//    public void run() {
//
//        // 1、创建代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 2、全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(OUTPUTPATH + "/src/main/java");
//        gc.setAuthor(AUTHOR);
//        gc.setOpen(false); //生成后是否打开资源管理器
//        gc.setFileOverride(false); //重新生成时文件是否覆盖
//        gc.setServiceName("%sService");    //去掉Service接口的首字母I
//        gc.setIdType(IdType.ID_WORKER_STR); //主键策略
//        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
//        gc.setSwagger2(true);//开启Swagger2模式
//        mpg.setGlobalConfig(gc);
//
//        // 3、数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/baima?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&allowMultiQueries=true");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername(DataSourceUSERNAME);
//        dsc.setPassword(DataSourceUSERPASSWORD);
//        dsc.setDbType(DbType.MYSQL);
//        mpg.setDataSource(dsc);
//
//
//        // 4、包配置
//        PackageConfig pc = new PackageConfig();
//
//        pc.setParent("com.gcu");
//        pc.setModuleName("baima"); //模块名
//        pc.setController("controller"); //生成controller包
//        pc.setEntity("entity"); //生成entity包
//        pc.setService("service"); //生成service包
//        pc.setMapper("mapper"); //生成mapper包
//        mpg.setPackageInfo(pc);
//
//        // 5.自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//        String templatePath = "/templates/mapper.xml";
//        //5.1 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        //5.2 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return projectPath + "/src/main/resources/mybatis/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//        // 6、策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setInclude("article_category","chat_log","course","customer","manager","registration","trial_lesson","trial_lesson_comment","trial_lesson_customer");
//        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略
//        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀
//
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
//        strategy.setEntityLombokModel(true); // lombok 模型 @Accessors(chain = true) setter链式操作
//        strategy.setRestControllerStyle(true); //restful api风格控制器
//        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
//        mpg.setStrategy(strategy);
//        // 6、执行
//        mpg.execute();
//    }
//}

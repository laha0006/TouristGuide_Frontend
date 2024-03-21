//package dev.tolana.touristguide_frontend.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.ResourceBundle;
//
//
//@Controller
//@RequestMapping("/test")
//public class DBTestController {
//    private JdbcTemplate jdbcTemplate;
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String user;
//
//    @Value("${spring.datasource.password}")
//    private String password;
////
////    @Value("${mydb.test}")
////    private String mydbTest;
//
//    public DBTestController(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    private List<String> getEmployees() {
////        List<Map<String, Object>> employees = jdbcTemplate.queryForList("SELECT * FROM emp");
////        return employees;
//        List<String> employees = new ArrayList<>();
//        String SQL = "SELECT ename FROM emp";
//
//        try(Connection con = DriverManager.getConnection(url,user,password)) {
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(SQL);
//            while(rs.next()) {
//                employees.add(rs.getString("ename"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return employees;
//
//    }
//
//    @GetMapping("")
//    public String test(Model model) {
//        model.addAttribute("employees",getEmployees());
//        return "test";
//    }
//
//    @GetMapping("/debug")
//    public String debug(Model model) {
//        model.addAttribute("url",url);
//        model.addAttribute("user",user);
//        model.addAttribute("password",password);
////        model.addAttribute("mydb",mydbTest);
//        return "debug";
//    }
//
//
//}

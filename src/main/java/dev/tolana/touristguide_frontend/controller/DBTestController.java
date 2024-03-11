package dev.tolana.touristguide_frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/test")
public class DBTestController {
    private JdbcTemplate jdbcTemplate;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String pwd;

    @Value("${spring.datasource.url}")
    private String url;

    public DBTestController(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private List<Map<String, Object>> getEmployees() {
        List<Map<String, Object>> employees = jdbcTemplate.queryForList("SELECT * FROM emp");
        return employees;
    }

    @GetMapping("")
    public String test(Model model) {
        System.out.println(user);
        System.out.println(pwd);
        System.out.println(url);
        model.addAttribute("employees",getEmployees());
        return "test";
    }


}

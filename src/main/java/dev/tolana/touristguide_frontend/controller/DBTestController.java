package dev.tolana.touristguide_frontend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(DBTestController.class);

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
        logger.info(url);
        logger.info(user);
        logger.info(pwd);
        model.addAttribute("employees",getEmployees());
        return "test";
    }


}

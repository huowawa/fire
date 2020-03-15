package com.soft.fire;

import com.soft.fire.platform.emp.mapper.EmpMapper;
import com.soft.fire.platform.emp.model.Emp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FireApplicationTests {

    @Resource
    private EmpMapper empMapper;



    @Test
    public void querypage() {



    }

}


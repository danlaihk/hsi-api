package io.danlaihk.hsiapi.database;

import io.danlaihk.hsiapi.HsiApiApplication;
import io.danlaihk.hsiapi.Underlying;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DatabaseServices {


    private final JdbcTemplate jdbcTemplate;

    public DatabaseServices(JdbcTemplate temp){
        this.jdbcTemplate = temp;
    }
    public void updateData(List<Underlying> list){



        List<Integer> result = jdbcTemplate.query(
                "SELECT count(*) as total from constituent;",
                (rs, rowNum) -> rs.getInt("total"));


        int total = result.get(0);


        if(total > 0){

            String statement = "UPDATE constituent SET " +
                    "stime = NOW(), index_type= 'HSI', name=?, cname=? " +
                    "WHERE ?";
            for(Underlying u: list){
                jdbcTemplate.update(statement,  u.getName(),u.getCname(), u.getCode());
            }
        }else {

            String statement = "INSERT INTO constituent (stime, index_type, code, name, cname) VALUES (NOW(), 'HSI', ?,?,?)";

            for(Underlying u: list){
                jdbcTemplate.update(statement, u.getCode(), u.getName(),u.getCname());
            }
        }







    }

}

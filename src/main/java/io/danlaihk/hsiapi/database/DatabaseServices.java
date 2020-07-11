package io.danlaihk.hsiapi.database;

import io.danlaihk.hsiapi.HsiApiApplication;
import io.danlaihk.hsiapi.Underlying;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseServices {


    private final JdbcTemplate jdbcTemplate;
    private final Logger log = LoggerFactory.getLogger(HsiApiApplication.class);
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
                    "WHERE code =?;";
            for(Underlying u: list){
                jdbcTemplate.update(statement,  u.getName(),u.getCname(), u.getCode());
            }
        }else {

            String statement = "INSERT INTO constituent (stime, index_type, code, name, cname) VALUES (NOW(), 'HSI', ?,?,?)";


                int[] resultList = jdbcTemplate.batchUpdate(statement, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    Underlying u = list.get(i);
                    preparedStatement.setInt(1, Integer.parseInt(u.getCode()));
                    preparedStatement.setString(2, u.getName());
                    preparedStatement.setString(3, u.getCname());
                }

                @Override
                public int getBatchSize() {
                    return list.size();
                }

            });

            for(int value : resultList){
                System.out.println(value);

            }
            log.info("insert complete");

        }







    }

}

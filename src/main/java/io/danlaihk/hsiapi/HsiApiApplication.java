package io.danlaihk.hsiapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.danlaihk.hsiapi.apiProcess.API;
import io.danlaihk.hsiapi.database.DatabaseServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@SpringBootApplication
public class HsiApiApplication {
	private ObjectMapper mapper = new ObjectMapper();
	private static final Logger log = LoggerFactory.getLogger(HsiApiApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(HsiApiApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	//main
	public CommandLineRunner run(RestTemplate temp){


		return (args) -> {

			List<Underlying> ulist = new ArrayList<>();
			API api = new API();

			Map<String, Underlying> uMap = api.getJdata(temp);
			for(Map.Entry<String, Underlying> entry: uMap.entrySet()){

				ulist.add(entry.getValue());

			}

			log.info("-----------------------");
			log.info("Update DB");


			DatabaseServices conn = new DatabaseServices(jdbcTemplate);
			//updateData(ulist);
			conn.updateData(ulist);
			System.out.println();
		};
	}




	public void updateData(List<Underlying> list){
		List<Integer> result = jdbcTemplate.query(
				"SELECT count(*) as total from constituent;",
				(rs, rowNum) -> rs.getInt("total"));

		int total = result.get(0);
		if(total > 0){
			log.info("update");
			String statement = "UPDATE constituent SET " +
					"stime = NOW(), index_type= 'HSI', name=?, cname=? " +
					"WHERE ?";
			for(Underlying u: list){
				jdbcTemplate.update(statement,  u.getName(),u.getCname(), u.getCode());
			}
		}else {
			log.info("insert");
			String statement = "INSERT INTO constituent (stime, index_type, code, name, cname) VALUES (NOW(), 'HSI', ?,?,?)";

			for(Underlying u: list){
				jdbcTemplate.update(statement, u.getCode(), u.getName(),u.getCname());
			}
		}



	}

}



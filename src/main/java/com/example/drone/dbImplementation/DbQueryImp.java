package com.example.drone.dbImplementation;

import com.example.drone.dbInterface.DbQueryInf;
import com.example.drone.model.MedicationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DbQueryImp implements DbQueryInf {

    private JdbcTemplate jdbcTemplate;

    DbQueryImp(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MedicationModel> findAllMedicationbyDrone(long droneId, String status) {
        String sql = "SELECT m.* FROM drone_load dl left join medication m on dl.medication_id = m.id where dl.drone_id = ? AND dl.status = ?";

        return jdbcTemplate.query(sql, new Object[] { droneId, status }, new BeanPropertyRowMapper(MedicationModel.class));
    }
}

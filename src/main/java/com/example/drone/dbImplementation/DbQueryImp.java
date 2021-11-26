package com.example.drone.dbImplementation;

import com.example.drone.dbInterface.DbQueryInf;
import com.example.drone.model.DroneLoad;
import com.example.drone.model.MedicationModel;
import com.example.drone.util.Const;
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

    @Override
    public void updateMedicationAndDroneLoadStatus(long droneId){
        findAllMedicationbyDrone( droneId, Const.ACTIVE).forEach( medication ->
        {
            jdbcTemplate.update(
                    "UPDATE medication SET status = ? WHERE id = ?", Const.MEDICATION_STATE.OFFLOAD.toString(), medication.getId());

            jdbcTemplate.update("UPDATE drone_load SET status = ? WHERE drone_id = ? and medication_id = ? and status = ?",
                    new Object[]{Const.INACTIVE, droneId, medication.getId(), Const.ACTIVE});
        });




    }
}

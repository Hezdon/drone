package com.example.drone;

import com.example.drone.controller.DispatchController;
import com.example.drone.service.DispatchService;
import com.example.drone.util.Const;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@AutoConfigureMockMvc
@SpringBootTest
class DroneApplicationTests {

	@Autowired
	private MockMvc mvc;
	@Autowired
	DispatchController dispatchController;
	@Autowired
	DispatchService dispatchService;
	@Test
	void contextLoads() {
		assertThat(dispatchController).isNotNull();
	}

	@Test
	void findAllDrone() throws Exception{
		this.mvc.perform(get("/drone/findall")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	void findAllMedication() throws Exception{
		this.mvc.perform(get("/medication/findall")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void loadedMedicationOnDrone(){
		assertEquals(Const.SUCCESSFUL, dispatchService.loadedMedicationOnDrone("123456").getStatus());

	}

	@Test
	public void getDroneByState(){
		assertEquals(Const.SUCCESSFUL, dispatchService.getDroneByState(Const.DRONE_STATE.IDLE.toString()).getStatus());

	}

	@Test
	public void getDroneBatteryLevel(){
		assertNotNull(dispatchService.getDroneBatteryLevel("123456").getData());

	}
}

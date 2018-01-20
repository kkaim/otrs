package biz.kaim.restaurantservice.otrs.controller;

import biz.kaim.restaurantservice.otrs.RestaurantApp;
import biz.kaim.restaurantservice.otrs.domain.Table;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)

public class RestaurantControllerIntegrationTests extends AbstractRestaurantControllerTests {


    public static final ObjectMapper objectMapper = new ObjectMapper();
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    @Value("${local.server.port}")
    private int port;

    @Test
    public void testGetById() {
        Map<String, Object> response
                = restTemplate.getForObject("http://localhost:" + port + "/v1/restaurants/1", Map.class);

        assertNotNull(response);
        //Asserting API Response
        String id = response.get("id").toString();
        assertNotNull(id);
        assertEquals("1", id);
        String name = response.get("name").toString();
        assertNotNull(name);
        assertEquals("Le Meurice", name);
        boolean isModified = (boolean) response.get("isModified");
        assertEquals(false, isModified);
        List<Table> tableList = (List<Table>) response.get("tables");
        assertNull(tableList);

    }

    @Test
    public void testGetById_NoContent() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> responseE = restTemplate.exchange("http://localhost:" + port + "/v1/restaurants/99", HttpMethod.GET, entity, Map.class);
        assertNotNull(responseE);
        assertEquals(HttpStatus.NO_CONTENT, responseE.getStatusCode());
    }

    @Test
    public void testGetByName() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("name", "Meurice");
        ResponseEntity<Map[]> responseE = restTemplate.exchange("http://localhost:" + port + "/v1/restaurants?name={name}", HttpMethod.GET, entity, Map[].class, uriVariables);
        assertNotNull(responseE);
        assertEquals(HttpStatus.OK, responseE.getStatusCode());
        Map<String, Object>[] responses = responseE.getBody();
        assertNotNull(responses);
        assertTrue(responses.length == 1);
        Map<String, Object> response = responses[0];
        String id = response.get("id").toString();
        assertNotNull(id);
        assertEquals("1", id);
        String name = response.get("name").toString();
        assertNotNull(name);
        assertEquals("Le Meurice", name);
        boolean isModified = (boolean) response.get("isModified");
        assertEquals(false, isModified);
        List<Table> tableList = (List<Table>) response.get("tables");
        assertNull(tableList);
    }


    @Test
    public void testAdd() throws JsonProcessingException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "La Plaza Restaurant");
        requestBody.put("id", "11");
        requestBody.put("address", "address of La Plaza Restaurant");
        Map<String, Object> table1 = new HashMap<>();
        table1.put("name", "Table 1");
        table1.put("id", BigInteger.ONE);
        table1.put("capacity", Integer.valueOf(6));
        Map<String, Object> table2 = new HashMap<>();
        table2.put("name", "Table 2");
        table2.put("id", BigInteger.valueOf(2));
        table2.put("capacity", Integer.valueOf(4));
        Map<String, Object> table3 = new HashMap<>();
        table3.put("name", "Table 3");
        table3.put("id", BigInteger.valueOf(3));
        table3.put("capacity", Integer.valueOf(2));
        List<Map<String, Object>> tableList = new ArrayList();
        tableList.add(table1);
        tableList.add(table2);
        tableList.add(table3);
        requestBody.put("tables", tableList);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), headers);

        ResponseEntity<Map> responseE = restTemplate.exchange("http://localhost:" + port + "/v1/restaurants", HttpMethod.POST, entity, Map.class, Collections.EMPTY_MAP);

        assertNotNull(responseE);
        assertEquals(HttpStatus.CREATED, responseE.getStatusCode());
        Map<String, Object> response
                = restTemplate.getForObject("http://localhost:" + port + "/v1/restaurants/11", Map.class);
        assertNotNull(response);
        String id = response.get("id").toString();
        assertNotNull(id);
        assertEquals("11", id);
        String name = response.get("name").toString();
        assertNotNull(name);
        assertEquals("La Plaza Restaurant", name);
        String address = response.get("address").toString();
        assertNotNull(address);
        assertEquals("address of La Plaza Restaurant", address);
        boolean isModified = (boolean) response.get("isModified");
        assertEquals(false, isModified);
        List<Map<String, Object>> tableList2 = (List<Map<String, Object>>) response.get("tables");
        assertNotNull(tableList2);
        assertEquals(tableList2.size(), 3);
        tableList2.stream().forEach((table) -> {
            assertNotNull(table);
            assertNotNull(table.get("name"));
            assertNotNull(table.get("id"));
            assertTrue((Integer) table.get("capacity") > 0);
        });
    }
}
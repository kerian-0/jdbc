package com.jdc.ps.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.jdc.ps.entity.District;
import com.jdc.ps.service.DistrictService;
import com.jdc.ps.service.StateService;

@TestMethodOrder(OrderAnnotation.class)
public class DistrictSerivceTest {

	DistrictService districtService = new DistrictService();
	static StateService stateService = new StateService();

@Order(3)
@ParameterizedTest
@CsvSource({
	"2,7","4,6"
})
	void test_for_delete(int id,int expectCount) {
	districtService.delete(id);
	assertEquals(expectCount,districtService.count());
	}
	
	
@Order(4)
@ParameterizedTest
@CsvSource("1")
void test_for_select_by_id(int id) {
    var rs = districtService.selectById(id);
    
    assertEquals(id, rs.getId());
}


	@Order(2)
	@ParameterizedTest
	@MethodSource(value = "getDistrictEntity")
	void test_for_insert_entities(List<District> entities) {
		var insertCount = districtService.insert(entities);
		assertEquals(3, insertCount);
		assertEquals(8, districtService.count());

	}

	static Stream<Arguments> getDistrictEntity() {
		List<District> entities = new ArrayList<District>();
		District myanaung = new District();
		myanaung.setName("MyanAung");
		myanaung.setBurmese("မြန်အောင်");
		myanaung.setState(stateService.selectById(1));

		District myaungmya = new District();
		myaungmya.setName("myaungmya");
		myaungmya.setBurmese("မြောင်းမြ");
		myaungmya.setState(stateService.selectById(1));

		District pyapon = new District();
		pyapon.setName("pyapon");
		pyapon.setBurmese("ဖျာပုံ");
		pyapon.setState(stateService.selectById(1));
		entities.add(myanaung);
		entities.add(myaungmya);
		entities.add(pyapon);

		return Stream.of(Arguments.of(entities));
	}

	
	@Order(5)
	@ParameterizedTest
	@CsvSource(delimiter = '\t',value="pyapon	ဖျာပုံ	1	4")
	void test_for_update(String name,String burmese,int state_id, int idForUpdate ) {
		
		District district=new District();
		district.setName(name);
		district.setBurmese(burmese);
		district.setState(stateService.selectById(state_id));
		
		districtService.update(idForUpdate, district);
		
		var districtUpdated = districtService.selectById(idForUpdate);
		assertNotNull(districtUpdated);
		assertEquals(name, districtUpdated.getName());
		assertEquals(burmese, districtUpdated.getBurmese());
		assertEquals(state_id, districtUpdated.getState());
		
		
		
	}

	@Order(1)
	@ParameterizedTest
	@CsvSource({ "Pathein, ပုသိမ်, 1, 1", "Kyonpyaw, ကျုံပျော်, 1, 2", "Hinthada, ဟင်္သာတ, 1, 3",
			"Labutta, လပွတ္တာ, 1, 4", "Maubin, မအူပင်, 1, 5",

	})
	void test_for_insert(String name, String burmese, int stateId, int expectedId) {
		var district = new District();
		district.setName(name);
		district.setBurmese(burmese);
		district.setState(stateService.selectById(stateId));

		var generatedId = districtService.insert(district);
		assertEquals(expectedId, generatedId);
	}

}
package com.manahotel.be;

import com.manahotel.be.model.dto.*;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.InventoryCheck;
import com.manahotel.be.model.entity.InventoryCheckDetail;
import com.manahotel.be.service.GoodsService;
import com.manahotel.be.service.InventoryCheckResponse;
import com.manahotel.be.service.InventoryCheckService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
class CoreManaHotelBeApplicationTests {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private InventoryCheckService inventoryCheckService;

	@Test
	void loadGoods() {
		List<Goods> goods = goodsService.getAll();
		assertTrue(goods.size() >= 0);
		assertNotNull(goods);
		assertFalse(goods.isEmpty());
	}

	@Test
	void loadInventoryCheck() {
		List<InventoryCheck> checks = inventoryCheckService.getAll();
		assertTrue(checks.size() >= 0);
		assertNotNull(checks);
		assertFalse(checks.isEmpty());
	}

	@Test
	void getGoodsById() {
		Goods good = goodsService.findGoodsById("SP000001");
		assertNotNull(good);
		assertEquals("SP000001", good.getGoodsId());
	}

	@Test
	void getInventoryCheckById() {
		InventoryCheck ic = inventoryCheckService.findInventoryCheckById("KK000001");
		assertNotNull(ic);
		assertEquals("KK000001", ic.getInventoryCheckId());
	}

	@Test
	void addGoods() {
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsName("Thịt trâu khô");
		dto.setGoodsCategory(true);
		dto.setInventory(100000L);
		dto.setMinInventory(100L);
		dto.setMaxInventory(100000L);
		dto.setNote("");
		dto.setDescription("");

		GoodsUnitDTO dto2 = new GoodsUnitDTO();
		dto2.setGoodsUnitName("Gói");
		dto2.setCost(97000F);
		dto2.setPrice(100000F);

		String message = goodsService.createGoods(dto, dto2);
		assertEquals("Tạo hàng hóa thành công", message);
	}

	@Test
	void addInventoryCheck() {
		InventoryCheckDTO ic = new InventoryCheckDTO();
		ic.setStatus(4L); // TEMPORARY
		ic.setNote("");

		List<InventoryCheckDetailDTO> listIcd = new ArrayList<>();

		InventoryCheckDetailDTO icd = new InventoryCheckDetailDTO();

		icd.setGoodsId("SP000001");
		icd.setActualInventory(120000L);

		listIcd.add(icd);

		InventoryCheckDetailDTO icd2 = new InventoryCheckDetailDTO();

		icd2.setGoodsId("SP000002");
		icd2.setActualInventory(115000L);

		listIcd.add(icd2);

		String message = inventoryCheckService.createInventoryCheck(ic, listIcd);
		assertEquals("Tạo kiểm kho thành công", message);
	}

	@Test
	void updateGoods() {
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsName("Thịt trâu khô");
		dto.setGoodsCategory(true);
		dto.setInventory(100000L);
		dto.setMinInventory(100L);
		dto.setMaxInventory(100000L);
		dto.setNote("");
		dto.setDescription("");

		GoodsUnitDTO dto2 = new GoodsUnitDTO();
		dto2.setGoodsUnitName("Gói");
		dto2.setCost(97000F);
		dto2.setPrice(100000F);

		String message = goodsService.updateGoods("SP000004", dto, dto2);
		assertEquals("Cập nhật hàng hóa thành công", message);
	}

	@Test
	void updateInventoryCheck() {
		InventoryCheckDTO ic = new InventoryCheckDTO();
		ic.setStatus(4L); // TEMPORARY
		ic.setNote("");

		List<InventoryCheckDetailDTO> listIcd = new ArrayList<>();

		InventoryCheckDetailDTO icd = new InventoryCheckDetailDTO();

		icd.setGoodsId("SP000001");
		icd.setActualInventory(120000L);

		listIcd.add(icd);

		InventoryCheckDetailDTO icd2 = new InventoryCheckDetailDTO();

		icd2.setGoodsId("SP000002");
		icd2.setActualInventory(115000L);

		listIcd.add(icd2);

		String message = inventoryCheckService.updateInventoryCheck("KK000003", ic, listIcd);
		assertEquals("Cập nhật kiểm kho thành công", message);
	}

	@Test
	void deleteGoods() {
		String message = goodsService.deleteGoods("SP000004");
		assertEquals("Xóa hàng hóa thành công", message);
	}

	@Test
	void cancelCheck() {
		String message = inventoryCheckService.cancelCheck("KK000003");
		assertEquals("Hủy kiểm kho thành công", message);
	}

	@Test
	void findListInventoryCheckDetailById() {
		List<InventoryCheckDetail> listDetails = inventoryCheckService.findListInventoryCheckDetailByInventoryCheckId("KK000003");
		assertTrue(listDetails.size() >= 0);
		assertNotNull(listDetails);
		assertFalse(listDetails.isEmpty());
	}

	@Test
	void getInventoryCheckSummary() {
		InventoryCheckResponse response = inventoryCheckService.getInventoryCheckSummary("KK000003");
		assertNotNull(response);
	}
}

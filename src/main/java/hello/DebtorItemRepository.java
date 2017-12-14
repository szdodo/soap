package hello;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.spring.guides.gs_producing_web_service.DebtorItem;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import io.spring.guides.gs_producing_web_service.GetDebtorItemResponse;

@Component
public class DebtorItemRepository {

	@PostConstruct
	public DebtorItem initData() {
		Random rand = new Random();
		DebtorItem randomDebtorItem = new DebtorItem();
		randomDebtorItem.setId(randomString());
		randomDebtorItem.setAmount(rand.nextInt(50) + 1);
		randomDebtorItem.setDueDate((rand.nextInt(30) + 1) + "/" + (rand.nextInt(12) + 1) + "/" + (rand.nextInt(2050) + 1500));
		randomDebtorItem.setReferenceNumber(rand.nextInt(5000) + 1);

		return randomDebtorItem;
	}

	public DebtorItem returnDebtorItem(String customerId) {
		DebtorItem item=initData();
		item.setCustomerId(customerId);
		return item;
	}

	private String randomString(){
		int count = 20;
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}

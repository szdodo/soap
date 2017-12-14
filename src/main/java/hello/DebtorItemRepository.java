package hello;

import com.stc.caseless.soap_service.DebtorItem;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class DebtorItemRepository {

	@PostConstruct
	public DebtorItem initData() {
		Random rand = new Random();
		DebtorItem randomDebtorItem = new DebtorItem();
		randomDebtorItem.setId(randomString());
		randomDebtorItem.setAmount(rand.nextInt(50) + 1);

		try {
			Date date = new Date((rand.nextInt(150) + 1), (rand.nextInt(12) + 1), (rand.nextInt(30) + 1));
			String FORMATER = "yyyy-MM-dd";
			DateFormat format = new SimpleDateFormat(FORMATER);
			XMLGregorianCalendar gDateFormatted = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(format.format(date));
			randomDebtorItem.setDueDate(gDateFormatted);
		}
		catch(DatatypeConfigurationException x){}

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

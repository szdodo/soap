package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.stc.caseless.soap_service.GetDebtorItemRequest;
import com.stc.caseless.soap_service.GetDebtorItemResponse;

import java.util.Random;

@Endpoint
public class DebtorItemEndpoint {
	private static final String NAMESPACE_URI = "http://stc.com/caseless/soap-service";

	private DebtorItemRepository debtorItemRepository;

	@Autowired
	public DebtorItemEndpoint(DebtorItemRepository debtorItemRepository) {
		this.debtorItemRepository = debtorItemRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDebtorItemRequest")
	@ResponsePayload
	public GetDebtorItemResponse getDebtorItem(@RequestPayload GetDebtorItemRequest request) {
		GetDebtorItemResponse response = new GetDebtorItemResponse();
		Random rand=new Random();
		for (int i=0;i<(rand.nextInt(10) + 1);i++) {
			response.getDebtorItem().add(debtorItemRepository.returnDebtorItem(request.getCustomerId()));
		}
		return response;
	}
}

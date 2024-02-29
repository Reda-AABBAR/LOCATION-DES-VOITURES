package com.locatioVoiture.mappers.reponderMapper;

import java.util.Collection;
import java.util.stream.Collectors;

import com.locatioVoiture.dtos.ReponserDtos.ReponderRequest;
import com.locatioVoiture.dtos.ReponserDtos.ReponderResponse;
import com.locatioVoiture.entities.Reponder;
import com.locatioVoiture.mappers.serviceClientMapper.ServiceClientMapper;

public class ReponderMapper {
	
	public static Reponder toReponder(ReponderRequest reponderRequest) {
		Reponder reponder = null;
		if(reponderRequest != null) {
			reponder = Reponder.builder()
					.question(null)
					.serviceClient(ServiceClientMapper.toServiceClient(reponderRequest.serviceClientRequest()))
					.reponse(reponderRequest.reponse())
					.build();
					
		}
		return reponder;
	}

	public static ReponderResponse toReponderResponse(Reponder reponder) {
		ReponderResponse reponderResponse = null;
		if(reponder != null) {
			reponderResponse = ReponderResponse.builder()
					.id(reponder.getId())
					.reponse(reponder.getReponse())
					.serviceClientCIN(reponder.getServiceClient() != null ?
							reponder.getServiceClient().getCIN()
							: null)
					.build();
		}
		return reponderResponse;
	}

	public static Collection<ReponderResponse> toReponderResponses(Collection<Reponder> reponses) {
		return reponses != null ?
				reponses.stream().map(r->toReponderResponse(r)).collect(Collectors.toList())
				: null;
	}

}

package com.project.dairyproject.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.dairyproject.Entities.AddressDetails;
import com.project.dairyproject.Repository.AddressRepository;

@Service
@Transactional
@Component
public class AddressServices {

	@Autowired
	private AddressRepository addRepo;

	public AddressDetails getAddressDetailsByPincode(String pincode) {
		return addRepo.findAddressDetailsByPincode(pincode);
	}

	public List<AddressDetails> getAddressDetailsByDistrict(String district) {
		return addRepo.findAddressDetailsByDistrict(district);
	}

	public AddressDetails getAddressDetailsByTown(String town) {
		return addRepo.findAddressDetailsByTown(town);
	}

}

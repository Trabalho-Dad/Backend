package com.dad.sales_api.address.service;

import com.dad.sales_api.address.dto.input.CreateAddressInputDTO;
import com.dad.sales_api.address.dto.input.DeleteAddressInputDTO;
import com.dad.sales_api.address.dto.input.FindMyAddressesInputDTO;
import com.dad.sales_api.address.dto.input.UpdateAddressInputDTO;
import com.dad.sales_api.address.dto.output.CreateAddressOutputDTO;
import com.dad.sales_api.address.dto.output.FindMyAddressesOutputDTO;
import com.dad.sales_api.address.dto.output.UpdateAddressOutputDTO;
import com.dad.sales_api.shared.SalesConstants;
import com.dad.sales_api.shared.exceptions.ConflictException;
import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.helpers.NormalizeOutput;
import com.dad.sales_api.shared.helpers.services.MessageService;
import com.dad.sales_api.shared.mappers.AddressMapper;
import com.dad.sales_api.shared.persistence.postgres.entities.AddressEntity;
import com.dad.sales_api.shared.persistence.postgres.entities.UserEntity;
import com.dad.sales_api.shared.persistence.postgres.repositories.AddressRepository;
import com.dad.sales_api.shared.persistence.postgres.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
  private final AddressRepository addressRepository;
  private final UserRepository userRepository;
  private final MessageService messageService;

  public FindMyAddressesOutputDTO findMyAddresses(
      FindMyAddressesInputDTO input
  ){
    List<AddressEntity> addresses = findAll(input.userId());

    return new FindMyAddressesOutputDTO(
        addresses.stream().map(AddressMapper::convertEntityToSimpleDTO).toList(),
        addresses.size(),
        SalesConstants.LIMIT_ADDRESS
    );
  }

  public CreateAddressOutputDTO createAddress(
      CreateAddressInputDTO input
  ){
    List<AddressEntity> addresses = findAll(input.userId());

    if (addresses.size() == SalesConstants.LIMIT_ADDRESS) throw new ConflictException(
        messageService.getMessage(
            "exception.address.limit",
            SalesConstants.LIMIT_ADDRESS
        )
    );

    UserEntity user = userRepository.findById(input.userId())
        .orElseThrow(() -> new NotFoundException(
            messageService.getMessage("exception.user.not-found")
        ));

    AddressEntity address = new AddressEntity();

    address.setComplement(input.complement());
    address.setCep(input.cep());
    address.setState(input.state());
    address.setCity(input.city());
    address.setNeighborhood(input.neighborhood());
    address.setStreet(input.street());
    address.setNumber(input.number());
    address.setUser(user);

    address = addressRepository.save(address);

    return new CreateAddressOutputDTO(
        address.getId(),
        NormalizeOutput.cep(address.getCep()),
        NormalizeOutput.addressInfos(address.getState()),
        NormalizeOutput.addressInfos(address.getCity()),
        NormalizeOutput.addressInfos(address.getNeighborhood()),
        NormalizeOutput.addressInfos(address.getStreet()),
        address.getNumber(),
        address.getComplement()
    );
  }

  public UpdateAddressOutputDTO update(
      UpdateAddressInputDTO input
  ) {
    AddressEntity address = find(input.id(), input.userId());

    if (input.complement() != null) address.setComplement(input.complement());

    if (input.cep() != null) address.setCep(input.cep());

    if (input.state() != null) address.setState(input.state());

    if (input.city() != null) address.setCity(input.city());

    if (input.neighborhood() != null) address.setNeighborhood(input.neighborhood());

    if (input.street() != null) address.setStreet(input.street());

    if (input.number() != null) address.setNumber(input.number());

    address = addressRepository.save(address);

    return new UpdateAddressOutputDTO(
        address.getId(),
        address.getComplement(),
        NormalizeOutput.cep(address.getCep()),
        address.getState(),
        address.getCity(),
        address.getNeighborhood(),
        address.getStreet(),
        address.getNumber()
    );
  }
  public void delete(
      DeleteAddressInputDTO input
  ){
    find(input.id(), input.userId());

    addressRepository.deleteById(input.id());
  }

  private List<AddressEntity> findAll(Integer userId){
    return addressRepository.findAllByUserId(userId);
  }
  private AddressEntity find(Integer id, Integer userId){
    return addressRepository.findByIdAndUserId(id, userId).orElseThrow(
        () -> new NotFoundException(
            messageService.getMessage("exception.address.not-found")
        )
    );
  }
}
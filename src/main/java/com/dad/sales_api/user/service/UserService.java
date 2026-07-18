package com.dad.sales_api.user.service;

import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.mappers.AddressMapper;
import com.dad.sales_api.shared.mappers.ContactMapper;
import com.dad.sales_api.shared.persistence.postgres.entities.UserEntity;
import com.dad.sales_api.shared.persistence.postgres.repositories.UserRepository;
import com.dad.sales_api.user.dto.input.FindMyUserInputDTO;
import com.dad.sales_api.user.dto.input.UpdateUserInputDTO;
import com.dad.sales_api.user.dto.output.FindMyUserOutputDTO;
import com.dad.sales_api.user.dto.output.UpdateUserOutputDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("publicUserService")
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  @Transactional
  public FindMyUserOutputDTO findMyUser(FindMyUserInputDTO input){
    UserEntity user = find(input.id());

    return new FindMyUserOutputDTO(
        user,
        user.getContacts().stream().map(ContactMapper::convertEntityToSimpleDTO).toList(),
        user.getAddresses().stream().map(AddressMapper::convertToSimpleDTO).toList()
    );
  }

  public UpdateUserOutputDTO update(
      UpdateUserInputDTO input
  ){
    UserEntity user = find(input.id());

    if (input.email() != null && !input.email().isEmpty() && !user.getEmail().equals(input.email())) user.setEmail(input.email());
    if (input.name() != null && !input.name().isEmpty() && !user.getName().equals(input.name())) user.setName(input.name());

    userRepository.save(user);

    return new UpdateUserOutputDTO(
        user.getId(),
        user.getName(),
        user.getCpf(),
        user.getEmail()
    );
  }

  private UserEntity find(Integer id){
    return userRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Usuário não encontrado.")
    );
  }
}
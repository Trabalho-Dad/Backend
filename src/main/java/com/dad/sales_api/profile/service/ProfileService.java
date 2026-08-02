package com.dad.sales_api.profile.service;

import com.dad.sales_api.shared.exceptions.NotFoundException;
import com.dad.sales_api.shared.mappers.AddressMapper;
import com.dad.sales_api.shared.mappers.ContactMapper;
import com.dad.sales_api.shared.persistence.postgres.entities.UserEntity;
import com.dad.sales_api.shared.persistence.postgres.repositories.UserRepository;
import com.dad.sales_api.profile.dto.input.FindMyProfileInputDTO;
import com.dad.sales_api.profile.dto.input.UpdateProfileInputDTO;
import com.dad.sales_api.profile.dto.output.FindMyProfileOutputDTO;
import com.dad.sales_api.profile.dto.output.UpdateProfileOutputDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("publicUserService")
@RequiredArgsConstructor
public class ProfileService {
  private final UserRepository userRepository;

  @Transactional
  public FindMyProfileOutputDTO findMyUser(FindMyProfileInputDTO input){
    UserEntity user = find(input.id());

    return new FindMyProfileOutputDTO(
        user,
        user.getContacts().stream().map(ContactMapper::convertEntityToSimpleDTO).toList(),
        user.getAddresses().stream().map(AddressMapper::convertEntityToSimpleDTO).toList()
    );
  }

  public UpdateProfileOutputDTO update(
      UpdateProfileInputDTO input
  ){
    UserEntity user = find(input.id());

    if (input.email() != null && !input.email().isEmpty() && !user.getEmail().equals(input.email())) user.setEmail(input.email());
    if (input.name() != null && !input.name().isEmpty() && !user.getName().equals(input.name())) user.setName(input.name());

    userRepository.save(user);

    return new UpdateProfileOutputDTO(
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
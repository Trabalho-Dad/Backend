package com.dad.sales_api.auth.service;

import com.dad.sales_api.auth.dto.input.*;
import com.dad.sales_api.auth.dto.output.SendCodeOutputDTO;
import com.dad.sales_api.auth.dto.output.ValidateCodeOutputDTO;
import com.dad.sales_api.shared.enums.RoleEnum;
import com.dad.sales_api.shared.exceptions.BadRequestException;
import com.dad.sales_api.shared.exceptions.UnauthorizedException;
import com.dad.sales_api.shared.helpers.services.CpfValidatorService;
import com.dad.sales_api.shared.helpers.services.MessageService;
import com.dad.sales_api.shared.persistence.mongo.documents.PasswordResetCode;
import com.dad.sales_api.shared.persistence.mongo.repositories.PasswordResetCodeRepository;
import com.dad.sales_api.shared.helpers.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dad.sales_api.auth.dto.output.LoginOutputDTO;
import com.dad.sales_api.auth.dto.output.RegisterOutputDTO;
import com.dad.sales_api.shared.config.CustomUserDetails;
import com.dad.sales_api.shared.config.JwtUtils;
import com.dad.sales_api.shared.persistence.postgres.entities.UserEntity;
import com.dad.sales_api.shared.exceptions.ConflictException;
import com.dad.sales_api.shared.persistence.postgres.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordResetCodeRepository passwordResetCodeRepository;
  private final UserDetailsService userDetailsService;
  private final EmailService emailService;
  private final CpfValidatorService cpfValidatorService;
  private final MessageService messageService;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;
  private final Random random;


  public LoginOutputDTO login(LoginInputDTO input) {
    String email = input.email();
    String senha = input.password();

    UserDetails userDetails =
      userDetailsService.loadUserByUsername(email);

    CustomUserDetails user =
        (CustomUserDetails) userDetails;

    if (!passwordEncoder.matches(senha, userDetails.getPassword())) throw new UnauthorizedException(
        messageService.getMessage("exception.login.unhautorized"));

    String token = jwtUtils.generateToken(
      user.getUsername(),
      user.getRole(),
      user.getId()
    );

    return new LoginOutputDTO(
      token,
      messageService.getMessage("login.realized")
    );
  }

  public RegisterOutputDTO register(RegisterInputDTO input) {
    if (!cpfValidatorService.validateCpf(input.cpf()).valid()) throw new BadRequestException(
        messageService.getMessage("validation.cpf.exists")
    );

    UserEntity emailExists =
        userRepository.findByEmail(input.email());

    if (emailExists != null) throw new BadRequestException(
        messageService.getMessage("validation.email.invalid")
    );

    UserEntity cpfExists =
        userRepository.findByCpf(input.cpf());

    if (cpfExists != null) throw new BadRequestException(
        messageService.getMessage("validation.cpf.invalid")
    );

    UserEntity user = new UserEntity();

    user.setName(input.name());
    user.setEmail(input.email());
    user.setCpf(input.cpf());
    user.setRole(RoleEnum.CUSTOMER);

    user.setPassword(
      passwordEncoder.encode(input.password())
    );

    userRepository.save(user);

    return new RegisterOutputDTO(
      user.getId(),
      user.getName(),
      user.getCpf(),
      user.getEmail()
    );
  }

  public SendCodeOutputDTO sendCode(
      SendCodeInputDTO input
  ) throws MessagingException {
    String email = input.email();
    UserEntity user = userRepository.findByEmail(email);

    if (user != null) {
      if (passwordResetCodeRepository.findByEmail(email) == null) {
        int code = 100000 + random.nextInt(900000);

        PasswordResetCode passwordResetCode = new PasswordResetCode(
            email,
            user.getRole(),
            String.valueOf(code),
            LocalDateTime.now().plusMinutes(15)
        );

        passwordResetCodeRepository.save(passwordResetCode);

        emailService.send(
            email,
            "Recuperação de senha",
            String.format(
                "<h2>Código de recuperação</h2>" +
                    "<p>Segue o código de recuperação de senha:</p>" +
                    "<h3>%06d</h3>" +
                    "<p>Ele estará disponível pelos próximos 15 minutos.</p>",
                code
            )
        );
      }
    }

    return new SendCodeOutputDTO(
        input.email()
    );
  }

  public ValidateCodeOutputDTO validateCode(ValidateCodeInputDTO input){
    PasswordResetCode passwordResetCode = passwordResetCodeRepository.findByEmailAndUsed(input.email(), Boolean.FALSE);

    if (passwordResetCode == null) throw new UnauthorizedException(
        messageService.getMessage("validation.change-password.code.incorrect")
    );

    if (passwordResetCode.getCode().equals(input.code())) {
      passwordResetCode.setUsed(true);

      passwordResetCodeRepository.save(passwordResetCode);

      return new ValidateCodeOutputDTO(
          input.code(),
          true
      );
    } else throw new UnauthorizedException(
        messageService.getMessage("validation.change-password.code.incorrect")
    );
  }

  public void changePassword(
      ChangePasswordInputDTO input
  ){
    PasswordResetCode passwordResetCode = passwordResetCodeRepository.findByEmailAndUsed(input.email(), Boolean.TRUE);

    if (!passwordResetCode.getCode().equals(input.code())) throw new UnauthorizedException(
        messageService.getMessage("validation.change-password.code.incorrect")
    );

    if (!input.newPassword().equals(input.confirmPassword())) throw new BadRequestException(
        messageService.getMessage("validation.change-password.incorrect")
    );

    UserEntity user = userRepository.findByEmail(input.email());

    user.setPassword(input.newPassword());

    userRepository.save(user);
  }
}

package com.dad.sales_api.user.controller;

import com.dad.sales_api.shared.config.JwtUtils;
import com.dad.sales_api.user.dto.input.FindMyUserInputDTO;
import com.dad.sales_api.user.dto.input.UpdateUserInputDTO;
import com.dad.sales_api.user.dto.output.FindMyUserOutputDTO;
import com.dad.sales_api.user.dto.output.UpdateUserOutputDTO;
import com.dad.sales_api.user.dto.request.UpdateUserRequestDTO;
import com.dad.sales_api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("publicUserController")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
  private final UserService userService;
  private final JwtUtils jwtUtils;

  @GetMapping
  public ResponseEntity<FindMyUserOutputDTO> findMyUser(
      @RequestHeader("Authorization")
      String authHeader
  ){
    String token = authHeader.replace("Bearer ", "");

    Integer id = jwtUtils.getIdFromToken(token);

    return new ResponseEntity<>(
        this.userService.findMyUser(
            new FindMyUserInputDTO(
                id
            )
        ),
        HttpStatus.OK
    );
  }

  @PutMapping
  public ResponseEntity<UpdateUserOutputDTO> update(
      @RequestHeader("Authorization")
      String authHeader,

      @RequestBody
      UpdateUserRequestDTO input
  ){
    String token = authHeader.replace("Bearer ", "");

    Integer id = jwtUtils.getIdFromToken(token);

    return new ResponseEntity(
        this.userService.update(
            new UpdateUserInputDTO(
                id,
                input
            )
        ),
        HttpStatus.OK
    );
  }
}

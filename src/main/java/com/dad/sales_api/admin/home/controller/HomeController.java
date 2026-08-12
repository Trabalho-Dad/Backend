package com.dad.sales_api.admin.home.controller;

import com.dad.sales_api.admin.home.dto.HomeKpisOutputDTO;
import com.dad.sales_api.admin.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/home")
@RequiredArgsConstructor
public class HomeController {
  private final HomeService homeService;

  @GetMapping("/kpis")
  public ResponseEntity<HomeKpisOutputDTO> getKpis() {
    HomeKpisOutputDTO kpis = homeService.getKpis();
    return ResponseEntity.ok(kpis);
  }
}
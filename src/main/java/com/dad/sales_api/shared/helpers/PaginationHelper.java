package com.dad.sales_api.shared.helpers;

import org.springframework.data.domain.PageRequest;

public class PaginationHelper {
  public static PageRequest calculatePage(int page, int totalPages, int take){
    return PageRequest.of(
        (
            page <= totalPages
                ? page
                : 1
        ) - 1,
        take
    );
  }
}

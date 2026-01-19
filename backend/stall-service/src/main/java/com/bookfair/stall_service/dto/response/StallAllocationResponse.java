package com.bookfair.stall_service.dto.response;

import com.bookfair.stall_service.enums.StallAllocationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StallAllocationResponse {

  private Long id;
  private Long bookFairId;
  private Long hallStallID;
  private Long stallId;
  private Long price;
  private StallAllocationStatus stallAllocationStatus;
  private Long userId;
  private String reservationToken;

}

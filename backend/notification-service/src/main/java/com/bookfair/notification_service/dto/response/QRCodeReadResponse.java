package com.bookfair.notification_service.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRCodeReadResponse {

  private boolean success;
  private String message;
  private QrReadResponse data;
}

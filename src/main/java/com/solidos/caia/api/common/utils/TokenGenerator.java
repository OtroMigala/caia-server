package com.solidos.caia.api.common.utils;

import java.util.UUID;

public class TokenGenerator {
  public static String generate() {
    // Generar un UUID
    UUID uuid = UUID.randomUUID();
    // Convertir a cadena y tomar los primeros 15 caracteres
    return uuid.toString().replace("-", "");
  }
}

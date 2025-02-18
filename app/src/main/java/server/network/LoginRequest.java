package server.network;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotEmpty
        String login,
        @NotBlank
        @Size(min = 5)
        String password
) {}

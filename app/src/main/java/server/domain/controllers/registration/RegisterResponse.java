package server.domain.controllers.registration;

public class RegisterResponse {
    private String status; // "success" или "error"
    private String error;  // сообщение об ошибке

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}

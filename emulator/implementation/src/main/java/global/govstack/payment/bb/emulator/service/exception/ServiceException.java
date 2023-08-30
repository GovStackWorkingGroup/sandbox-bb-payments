package global.govstack.payment.bb.emulator.service.exception;

public class ServiceException extends RuntimeException {
  private final Object response;

  public ServiceException(String message) {
    this(message, null);
  }

  public ServiceException(String message, Object response) {
    super(message);
    this.response = response;
  }

  public Object getResponse() {
    return response;
  }
}

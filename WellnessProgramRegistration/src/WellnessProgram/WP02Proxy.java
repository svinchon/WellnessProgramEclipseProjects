package WellnessProgram;

public class WP02Proxy implements WellnessProgram.WP02 {
  private String _endpoint = null;
  private WellnessProgram.WP02 wP02 = null;
  
  public WP02Proxy() {
    _initWP02Proxy();
  }
  
  public WP02Proxy(String endpoint) {
    _endpoint = endpoint;
    _initWP02Proxy();
  }
  
  private void _initWP02Proxy() {
    try {
      wP02 = (new WellnessProgram.WP02ServiceLocator()).getWP02Port();
      if (wP02 != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wP02)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wP02)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wP02 != null)
      ((javax.xml.rpc.Stub)wP02)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public WellnessProgram.WP02 getWP02() {
    if (wP02 == null)
      _initWP02Proxy();
    return wP02;
  }
  
  public WellnessProgram.DefaultResponse submitRegistration(WellnessProgram.SubmitRegistrationRequest parameters) throws java.rmi.RemoteException{
    if (wP02 == null)
      _initWP02Proxy();
    return wP02.submitRegistration(parameters);
  }
  
  
}
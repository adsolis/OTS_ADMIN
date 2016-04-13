
/**
 * CorreoControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  CorreoControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CorreoControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CorreoControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CorreoControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for enviarCorreo method
            * override this method for handling normal response from enviarCorreo operation
            */
           public void receiveResultenviarCorreo(
                    com.datacode.avon_ots_ws.CorreoControllerStub.EnviarCorreoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from enviarCorreo operation
           */
            public void receiveErrorenviarCorreo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerCorreosNoEnviados method
            * override this method for handling normal response from obtenerCorreosNoEnviados operation
            */
           public void receiveResultobtenerCorreosNoEnviados(
                    com.datacode.avon_ots_ws.CorreoControllerStub.ObtenerCorreosNoEnviadosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerCorreosNoEnviados operation
           */
            public void receiveErrorobtenerCorreosNoEnviados(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaLiquidacionesMail method
            * override this method for handling normal response from obtenerListaLiquidacionesMail operation
            */
           public void receiveResultobtenerListaLiquidacionesMail(
                    com.datacode.avon_ots_ws.CorreoControllerStub.ObtenerListaLiquidacionesMailResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaLiquidacionesMail operation
           */
            public void receiveErrorobtenerListaLiquidacionesMail(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizaEnviadoReporteSubBodega method
            * override this method for handling normal response from actualizaEnviadoReporteSubBodega operation
            */
           public void receiveResultactualizaEnviadoReporteSubBodega(
                    com.datacode.avon_ots_ws.CorreoControllerStub.ActualizaEnviadoReporteSubBodegaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizaEnviadoReporteSubBodega operation
           */
            public void receiveErroractualizaEnviadoReporteSubBodega(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for registrarCorreoNoEnviado method
            * override this method for handling normal response from registrarCorreoNoEnviado operation
            */
           public void receiveResultregistrarCorreoNoEnviado(
                    com.datacode.avon_ots_ws.CorreoControllerStub.RegistrarCorreoNoEnviadoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registrarCorreoNoEnviado operation
           */
            public void receiveErrorregistrarCorreoNoEnviado(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for registrarEnvioMail method
            * override this method for handling normal response from registrarEnvioMail operation
            */
           public void receiveResultregistrarEnvioMail(
                    com.datacode.avon_ots_ws.CorreoControllerStub.RegistrarEnvioMailResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registrarEnvioMail operation
           */
            public void receiveErrorregistrarEnvioMail(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarStatusLiquidacionesMail method
            * override this method for handling normal response from actualizarStatusLiquidacionesMail operation
            */
           public void receiveResultactualizarStatusLiquidacionesMail(
                    com.datacode.avon_ots_ws.CorreoControllerStub.ActualizarStatusLiquidacionesMailResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarStatusLiquidacionesMail operation
           */
            public void receiveErroractualizarStatusLiquidacionesMail(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCorreoNoEnviado method
            * override this method for handling normal response from eliminarCorreoNoEnviado operation
            */
           public void receiveResulteliminarCorreoNoEnviado(
                    com.datacode.avon_ots_ws.CorreoControllerStub.EliminarCorreoNoEnviadoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCorreoNoEnviado operation
           */
            public void receiveErroreliminarCorreoNoEnviado(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for registrarEnvioMailReporteSubbodega method
            * override this method for handling normal response from registrarEnvioMailReporteSubbodega operation
            */
           public void receiveResultregistrarEnvioMailReporteSubbodega(
                    com.datacode.avon_ots_ws.CorreoControllerStub.RegistrarEnvioMailReporteSubbodegaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from registrarEnvioMailReporteSubbodega operation
           */
            public void receiveErrorregistrarEnvioMailReporteSubbodega(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerDatosCorreoCuentaMaestra method
            * override this method for handling normal response from obtenerDatosCorreoCuentaMaestra operation
            */
           public void receiveResultobtenerDatosCorreoCuentaMaestra(
                    com.datacode.avon_ots_ws.CorreoControllerStub.ObtenerDatosCorreoCuentaMaestraResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerDatosCorreoCuentaMaestra operation
           */
            public void receiveErrorobtenerDatosCorreoCuentaMaestra(java.lang.Exception e) {
            }
                


    }
    
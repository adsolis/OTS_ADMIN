
/**
 * UtilsCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  UtilsCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class UtilsCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public UtilsCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public UtilsCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for obtieneDescripcionLDC method
            * override this method for handling normal response from obtieneDescripcionLDC operation
            */
           public void receiveResultobtieneDescripcionLDC(
                    com.datacode.avon_ots_ws.UtilsStub.ObtieneDescripcionLDCResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtieneDescripcionLDC operation
           */
            public void receiveErrorobtieneDescripcionLDC(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarLogMensajeParams method
            * override this method for handling normal response from guardarLogMensajeParams operation
            */
           public void receiveResultguardarLogMensajeParams(
                    com.datacode.avon_ots_ws.UtilsStub.GuardarLogMensajeParamsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarLogMensajeParams operation
           */
            public void receiveErrorguardarLogMensajeParams(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregaComillas method
            * override this method for handling normal response from agregaComillas operation
            */
           public void receiveResultagregaComillas(
                    com.datacode.avon_ots_ws.UtilsStub.AgregaComillasResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregaComillas operation
           */
            public void receiveErroragregaComillas(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerListaLDC method
            * override this method for handling normal response from obtenerListaLDC operation
            */
           public void receiveResultobtenerListaLDC(
                    com.datacode.avon_ots_ws.UtilsStub.ObtenerListaLDCResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerListaLDC operation
           */
            public void receiveErrorobtenerListaLDC(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtieneParametro method
            * override this method for handling normal response from obtieneParametro operation
            */
           public void receiveResultobtieneParametro(
                    com.datacode.avon_ots_ws.UtilsStub.ObtieneParametroResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtieneParametro operation
           */
            public void receiveErrorobtieneParametro(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerFechaActual method
            * override this method for handling normal response from obtenerFechaActual operation
            */
           public void receiveResultobtenerFechaActual(
                    com.datacode.avon_ots_ws.UtilsStub.ObtenerFechaActualResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerFechaActual operation
           */
            public void receiveErrorobtenerFechaActual(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cargarConfiguracion method
            * override this method for handling normal response from cargarConfiguracion operation
            */
           public void receiveResultcargarConfiguracion(
                    com.datacode.avon_ots_ws.UtilsStub.CargarConfiguracionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cargarConfiguracion operation
           */
            public void receiveErrorcargarConfiguracion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for agregaComillasFecha method
            * override this method for handling normal response from agregaComillasFecha operation
            */
           public void receiveResultagregaComillasFecha(
                    com.datacode.avon_ots_ws.UtilsStub.AgregaComillasFechaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from agregaComillasFecha operation
           */
            public void receiveErroragregaComillasFecha(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarLogMensajeBD method
            * override this method for handling normal response from guardarLogMensajeBD operation
            */
           public void receiveResultguardarLogMensajeBD(
                    com.datacode.avon_ots_ws.UtilsStub.GuardarLogMensajeBDResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarLogMensajeBD operation
           */
            public void receiveErrorguardarLogMensajeBD(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for probarConexion method
            * override this method for handling normal response from probarConexion operation
            */
           public void receiveResultprobarConexion(
                    com.datacode.avon_ots_ws.UtilsStub.ProbarConexionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from probarConexion operation
           */
            public void receiveErrorprobarConexion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for validarUsuario method
            * override this method for handling normal response from validarUsuario operation
            */
           public void receiveResultvalidarUsuario(
                    com.datacode.avon_ots_ws.UtilsStub.ValidarUsuarioResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from validarUsuario operation
           */
            public void receiveErrorvalidarUsuario(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerMensajeBD method
            * override this method for handling normal response from obtenerMensajeBD operation
            */
           public void receiveResultobtenerMensajeBD(
                    com.datacode.avon_ots_ws.UtilsStub.ObtenerMensajeBDResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerMensajeBD operation
           */
            public void receiveErrorobtenerMensajeBD(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for guardarLogReplicacionMensajeParams method
            * override this method for handling normal response from guardarLogReplicacionMensajeParams operation
            */
           public void receiveResultguardarLogReplicacionMensajeParams(
                    com.datacode.avon_ots_ws.UtilsStub.GuardarLogReplicacionMensajeParamsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from guardarLogReplicacionMensajeParams operation
           */
            public void receiveErrorguardarLogReplicacionMensajeParams(java.lang.Exception e) {
            }
                


    }
    
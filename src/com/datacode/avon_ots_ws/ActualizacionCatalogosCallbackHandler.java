
/**
 * ActualizacionCatalogosCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  ActualizacionCatalogosCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ActualizacionCatalogosCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ActualizacionCatalogosCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ActualizacionCatalogosCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getTransaccionesCatalogo method
            * override this method for handling normal response from getTransaccionesCatalogo operation
            */
           public void receiveResultgetTransaccionesCatalogo(
                    com.datacode.avon_ots_ws.ActualizacionCatalogosStub.GetTransaccionesCatalogoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTransaccionesCatalogo operation
           */
            public void receiveErrorgetTransaccionesCatalogo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checarDisponibilidadServicio method
            * override this method for handling normal response from checarDisponibilidadServicio operation
            */
           public void receiveResultchecarDisponibilidadServicio(
                    com.datacode.avon_ots_ws.ActualizacionCatalogosStub.ChecarDisponibilidadServicioResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checarDisponibilidadServicio operation
           */
            public void receiveErrorchecarDisponibilidadServicio(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIdLDC method
            * override this method for handling normal response from getIdLDC operation
            */
           public void receiveResultgetIdLDC(
                    com.datacode.avon_ots_ws.ActualizacionCatalogosStub.GetIdLDCResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIdLDC operation
           */
            public void receiveErrorgetIdLDC(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for consultarEstatusActualizacion method
            * override this method for handling normal response from consultarEstatusActualizacion operation
            */
           public void receiveResultconsultarEstatusActualizacion(
                    com.datacode.avon_ots_ws.ActualizacionCatalogosStub.ConsultarEstatusActualizacionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from consultarEstatusActualizacion operation
           */
            public void receiveErrorconsultarEstatusActualizacion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for obtenerEstatusReplicacion method
            * override this method for handling normal response from obtenerEstatusReplicacion operation
            */
           public void receiveResultobtenerEstatusReplicacion(
                    com.datacode.avon_ots_ws.ActualizacionCatalogosStub.ObtenerEstatusReplicacionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from obtenerEstatusReplicacion operation
           */
            public void receiveErrorobtenerEstatusReplicacion(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for isStoreProcedure method
            * override this method for handling normal response from isStoreProcedure operation
            */
           public void receiveResultisStoreProcedure(
                    com.datacode.avon_ots_ws.ActualizacionCatalogosStub.IsStoreProcedureResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isStoreProcedure operation
           */
            public void receiveErrorisStoreProcedure(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIdUser method
            * override this method for handling normal response from getIdUser operation
            */
           public void receiveResultgetIdUser(
                    com.datacode.avon_ots_ws.ActualizacionCatalogosStub.GetIdUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIdUser operation
           */
            public void receiveErrorgetIdUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarFechaEjecucion method
            * override this method for handling normal response from actualizarFechaEjecucion operation
            */
           public void receiveResultactualizarFechaEjecucion(
                    com.datacode.avon_ots_ws.ActualizacionCatalogosStub.ActualizarFechaEjecucionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarFechaEjecucion operation
           */
            public void receiveErroractualizarFechaEjecucion(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getCatalogo method
            * override this method for handling normal response from getCatalogo operation
            */
           public void receiveResultgetCatalogo(
                    com.datacode.avon_ots_ws.ActualizacionCatalogosStub.GetCatalogoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCatalogo operation
           */
            public void receiveErrorgetCatalogo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for realizarActualizacionCatalogo method
            * override this method for handling normal response from realizarActualizacionCatalogo operation
            */
           public void receiveResultrealizarActualizacionCatalogo(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from realizarActualizacionCatalogo operation
           */
            public void receiveErrorrealizarActualizacionCatalogo(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                


    }
    
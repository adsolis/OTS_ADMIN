
/**
 * ConsultarCatalogosControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  ConsultarCatalogosControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ConsultarCatalogosControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ConsultarCatalogosControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ConsultarCatalogosControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for actualizarRepresentante method
            * override this method for handling normal response from actualizarRepresentante operation
            */
           public void receiveResultactualizarRepresentante(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.ActualizarRepresentanteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarRepresentante operation
           */
            public void receiveErroractualizarRepresentante(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOrdenesExistentes method
            * override this method for handling normal response from getOrdenesExistentes operation
            */
           public void receiveResultgetOrdenesExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetOrdenesExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOrdenesExistentes operation
           */
            public void receiveErrorgetOrdenesExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRazonesDevolucionExistentes method
            * override this method for handling normal response from getRazonesDevolucionExistentes operation
            */
           public void receiveResultgetRazonesDevolucionExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetRazonesDevolucionExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRazonesDevolucionExistentes operation
           */
            public void receiveErrorgetRazonesDevolucionExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRepresentantesExistentes method
            * override this method for handling normal response from getRepresentantesExistentes operation
            */
           public void receiveResultgetRepresentantesExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetRepresentantesExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRepresentantesExistentes operation
           */
            public void receiveErrorgetRepresentantesExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCampaniasExistentes method
            * override this method for handling normal response from getCampaniasExistentes operation
            */
           public void receiveResultgetCampaniasExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetCampaniasExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCampaniasExistentes operation
           */
            public void receiveErrorgetCampaniasExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDivisionesExistentes method
            * override this method for handling normal response from getDivisionesExistentes operation
            */
           public void receiveResultgetDivisionesExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetDivisionesExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDivisionesExistentes operation
           */
            public void receiveErrorgetDivisionesExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getProgramacionRepartoExistentes method
            * override this method for handling normal response from getProgramacionRepartoExistentes operation
            */
           public void receiveResultgetProgramacionRepartoExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetProgramacionRepartoExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getProgramacionRepartoExistentes operation
           */
            public void receiveErrorgetProgramacionRepartoExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTiposSiniestroExistentes method
            * override this method for handling normal response from getTiposSiniestroExistentes operation
            */
           public void receiveResultgetTiposSiniestroExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetTiposSiniestroExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTiposSiniestroExistentes operation
           */
            public void receiveErrorgetTiposSiniestroExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getZonasExistentes method
            * override this method for handling normal response from getZonasExistentes operation
            */
           public void receiveResultgetZonasExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetZonasExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getZonasExistentes operation
           */
            public void receiveErrorgetZonasExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTiposLiquidacionExistentes method
            * override this method for handling normal response from getTiposLiquidacionExistentes operation
            */
           public void receiveResultgetTiposLiquidacionExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetTiposLiquidacionExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTiposLiquidacionExistentes operation
           */
            public void receiveErrorgetTiposLiquidacionExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getEstadosOrdenExistentes method
            * override this method for handling normal response from getEstadosOrdenExistentes operation
            */
           public void receiveResultgetEstadosOrdenExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetEstadosOrdenExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getEstadosOrdenExistentes operation
           */
            public void receiveErrorgetEstadosOrdenExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTiposPagoExistentes method
            * override this method for handling normal response from getTiposPagoExistentes operation
            */
           public void receiveResultgetTiposPagoExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetTiposPagoExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTiposPagoExistentes operation
           */
            public void receiveErrorgetTiposPagoExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getBancosExistentes method
            * override this method for handling normal response from getBancosExistentes operation
            */
           public void receiveResultgetBancosExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetBancosExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getBancosExistentes operation
           */
            public void receiveErrorgetBancosExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPremiosExistentes method
            * override this method for handling normal response from getPremiosExistentes operation
            */
           public void receiveResultgetPremiosExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetPremiosExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPremiosExistentes operation
           */
            public void receiveErrorgetPremiosExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUnitariosExistentes method
            * override this method for handling normal response from getUnitariosExistentes operation
            */
           public void receiveResultgetUnitariosExistentes(
                    com.datacode.avon_ots_ws.ConsultarCatalogosControllerStub.GetUnitariosExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUnitariosExistentes operation
           */
            public void receiveErrorgetUnitariosExistentes(java.lang.Exception e) {
            }
                


    }
    
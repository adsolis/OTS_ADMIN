
/**
 * MantenimientoElementoControllerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  MantenimientoElementoControllerCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MantenimientoElementoControllerCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MantenimientoElementoControllerCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MantenimientoElementoControllerCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for actualizarCatalog12 method
            * override this method for handling normal response from actualizarCatalog12 operation
            */
           public void receiveResultactualizarCatalog12(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog12Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog12 operation
           */
            public void receiveErroractualizarCatalog12(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getLineasTransporteExistentes method
            * override this method for handling normal response from getLineasTransporteExistentes operation
            */
           public void receiveResultgetLineasTransporteExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetLineasTransporteExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getLineasTransporteExistentes operation
           */
            public void receiveErrorgetLineasTransporteExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAsignacionesRutaChoferExistentes method
            * override this method for handling normal response from getAsignacionesRutaChoferExistentes operation
            */
           public void receiveResultgetAsignacionesRutaChoferExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetAsignacionesRutaChoferExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAsignacionesRutaChoferExistentes operation
           */
            public void receiveErrorgetAsignacionesRutaChoferExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog11 method
            * override this method for handling normal response from insertarCatalog11 operation
            */
           public void receiveResultinsertarCatalog11(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog11Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog11 operation
           */
            public void receiveErrorinsertarCatalog11(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog21 method
            * override this method for handling normal response from actualizarCatalog21 operation
            */
           public void receiveResultactualizarCatalog21(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog21Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog21 operation
           */
            public void receiveErroractualizarCatalog21(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarRutaAlterna method
            * override this method for handling normal response from actualizarRutaAlterna operation
            */
           public void receiveResultactualizarRutaAlterna(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarRutaAlternaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarRutaAlterna operation
           */
            public void receiveErroractualizarRutaAlterna(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTipoDestinatarioExistentes method
            * override this method for handling normal response from getTipoDestinatarioExistentes operation
            */
           public void receiveResultgetTipoDestinatarioExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetTipoDestinatarioExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTipoDestinatarioExistentes operation
           */
            public void receiveErrorgetTipoDestinatarioExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog15 method
            * override this method for handling normal response from eliminarCatalog15 operation
            */
           public void receiveResulteliminarCatalog15(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog15Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog15 operation
           */
            public void receiveErroreliminarCatalog15(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRepresentantesExistentes method
            * override this method for handling normal response from getRepresentantesExistentes operation
            */
           public void receiveResultgetRepresentantesExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetRepresentantesExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRepresentantesExistentes operation
           */
            public void receiveErrorgetRepresentantesExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog6 method
            * override this method for handling normal response from actualizarCatalog6 operation
            */
           public void receiveResultactualizarCatalog6(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog6Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog6 operation
           */
            public void receiveErroractualizarCatalog6(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog14 method
            * override this method for handling normal response from actualizarCatalog14 operation
            */
           public void receiveResultactualizarCatalog14(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog14Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog14 operation
           */
            public void receiveErroractualizarCatalog14(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDispositivosMovilesExistentes method
            * override this method for handling normal response from getDispositivosMovilesExistentes operation
            */
           public void receiveResultgetDispositivosMovilesExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetDispositivosMovilesExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDispositivosMovilesExistentes operation
           */
            public void receiveErrorgetDispositivosMovilesExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog14 method
            * override this method for handling normal response from insertarCatalog14 operation
            */
           public void receiveResultinsertarCatalog14(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog14Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog14 operation
           */
            public void receiveErrorinsertarCatalog14(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog10 method
            * override this method for handling normal response from insertarCatalog10 operation
            */
           public void receiveResultinsertarCatalog10(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog10Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog10 operation
           */
            public void receiveErrorinsertarCatalog10(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog1 method
            * override this method for handling normal response from actualizarCatalog1 operation
            */
           public void receiveResultactualizarCatalog1(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog1Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog1 operation
           */
            public void receiveErroractualizarCatalog1(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getObtenerRutasExistentes method
            * override this method for handling normal response from getObtenerRutasExistentes operation
            */
           public void receiveResultgetObtenerRutasExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetObtenerRutasExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getObtenerRutasExistentes operation
           */
            public void receiveErrorgetObtenerRutasExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog17 method
            * override this method for handling normal response from actualizarCatalog17 operation
            */
           public void receiveResultactualizarCatalog17(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog17Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog17 operation
           */
            public void receiveErroractualizarCatalog17(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog4 method
            * override this method for handling normal response from insertarCatalog4 operation
            */
           public void receiveResultinsertarCatalog4(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog4Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog4 operation
           */
            public void receiveErrorinsertarCatalog4(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getModelosExistentes method
            * override this method for handling normal response from getModelosExistentes operation
            */
           public void receiveResultgetModelosExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetModelosExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getModelosExistentes operation
           */
            public void receiveErrorgetModelosExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog19 method
            * override this method for handling normal response from eliminarCatalog19 operation
            */
           public void receiveResulteliminarCatalog19(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog19Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog19 operation
           */
            public void receiveErroreliminarCatalog19(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog16 method
            * override this method for handling normal response from insertarCatalog16 operation
            */
           public void receiveResultinsertarCatalog16(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog16Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog16 operation
           */
            public void receiveErrorinsertarCatalog16(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog17 method
            * override this method for handling normal response from insertarCatalog17 operation
            */
           public void receiveResultinsertarCatalog17(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog17Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog17 operation
           */
            public void receiveErrorinsertarCatalog17(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog18 method
            * override this method for handling normal response from actualizarCatalog18 operation
            */
           public void receiveResultactualizarCatalog18(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog18Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog18 operation
           */
            public void receiveErroractualizarCatalog18(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog7 method
            * override this method for handling normal response from eliminarCatalog7 operation
            */
           public void receiveResulteliminarCatalog7(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog7Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog7 operation
           */
            public void receiveErroreliminarCatalog7(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog9 method
            * override this method for handling normal response from insertarCatalog9 operation
            */
           public void receiveResultinsertarCatalog9(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog9Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog9 operation
           */
            public void receiveErrorinsertarCatalog9(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog22 method
            * override this method for handling normal response from actualizarCatalog22 operation
            */
           public void receiveResultactualizarCatalog22(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog22Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog22 operation
           */
            public void receiveErroractualizarCatalog22(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog10 method
            * override this method for handling normal response from eliminarCatalog10 operation
            */
           public void receiveResulteliminarCatalog10(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog10Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog10 operation
           */
            public void receiveErroreliminarCatalog10(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog16 method
            * override this method for handling normal response from eliminarCatalog16 operation
            */
           public void receiveResulteliminarCatalog16(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog16Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog16 operation
           */
            public void receiveErroreliminarCatalog16(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog3 method
            * override this method for handling normal response from eliminarCatalog3 operation
            */
           public void receiveResulteliminarCatalog3(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog3Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog3 operation
           */
            public void receiveErroreliminarCatalog3(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAsignacionesUnidadRepartoExistentes method
            * override this method for handling normal response from getAsignacionesUnidadRepartoExistentes operation
            */
           public void receiveResultgetAsignacionesUnidadRepartoExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetAsignacionesUnidadRepartoExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAsignacionesUnidadRepartoExistentes operation
           */
            public void receiveErrorgetAsignacionesUnidadRepartoExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDenominacionesExistentes method
            * override this method for handling normal response from getDenominacionesExistentes operation
            */
           public void receiveResultgetDenominacionesExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetDenominacionesExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDenominacionesExistentes operation
           */
            public void receiveErrorgetDenominacionesExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog2 method
            * override this method for handling normal response from insertarCatalog2 operation
            */
           public void receiveResultinsertarCatalog2(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog2Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog2 operation
           */
            public void receiveErrorinsertarCatalog2(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog3 method
            * override this method for handling normal response from actualizarCatalog3 operation
            */
           public void receiveResultactualizarCatalog3(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog3Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog3 operation
           */
            public void receiveErroractualizarCatalog3(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog9 method
            * override this method for handling normal response from eliminarCatalog9 operation
            */
           public void receiveResulteliminarCatalog9(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog9Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog9 operation
           */
            public void receiveErroreliminarCatalog9(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog8 method
            * override this method for handling normal response from eliminarCatalog8 operation
            */
           public void receiveResulteliminarCatalog8(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog8Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog8 operation
           */
            public void receiveErroreliminarCatalog8(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarRutaAlterna method
            * override this method for handling normal response from eliminarRutaAlterna operation
            */
           public void receiveResulteliminarRutaAlterna(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarRutaAlternaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarRutaAlterna operation
           */
            public void receiveErroreliminarRutaAlterna(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTipoInformantesExistentes method
            * override this method for handling normal response from getTipoInformantesExistentes operation
            */
           public void receiveResultgetTipoInformantesExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetTipoInformantesExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTipoInformantesExistentes operation
           */
            public void receiveErrorgetTipoInformantesExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUnidadesRepartoExistentes method
            * override this method for handling normal response from getUnidadesRepartoExistentes operation
            */
           public void receiveResultgetUnidadesRepartoExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetUnidadesRepartoExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUnidadesRepartoExistentes operation
           */
            public void receiveErrorgetUnidadesRepartoExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog15 method
            * override this method for handling normal response from actualizarCatalog15 operation
            */
           public void receiveResultactualizarCatalog15(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog15Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog15 operation
           */
            public void receiveErroractualizarCatalog15(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog6 method
            * override this method for handling normal response from eliminarCatalog6 operation
            */
           public void receiveResulteliminarCatalog6(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog6Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog6 operation
           */
            public void receiveErroreliminarCatalog6(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarRutaAlterna method
            * override this method for handling normal response from insertarRutaAlterna operation
            */
           public void receiveResultinsertarRutaAlterna(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarRutaAlternaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarRutaAlterna operation
           */
            public void receiveErrorinsertarRutaAlterna(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog8 method
            * override this method for handling normal response from actualizarCatalog8 operation
            */
           public void receiveResultactualizarCatalog8(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog8Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog8 operation
           */
            public void receiveErroractualizarCatalog8(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getInformantesExistentes method
            * override this method for handling normal response from getInformantesExistentes operation
            */
           public void receiveResultgetInformantesExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetInformantesExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getInformantesExistentes operation
           */
            public void receiveErrorgetInformantesExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUsuariosExistentes method
            * override this method for handling normal response from getUsuariosExistentes operation
            */
           public void receiveResultgetUsuariosExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetUsuariosExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUsuariosExistentes operation
           */
            public void receiveErrorgetUsuariosExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSubBodegasDeAlmacenExistentes method
            * override this method for handling normal response from getSubBodegasDeAlmacenExistentes operation
            */
           public void receiveResultgetSubBodegasDeAlmacenExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetSubBodegasDeAlmacenExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSubBodegasDeAlmacenExistentes operation
           */
            public void receiveErrorgetSubBodegasDeAlmacenExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog1 method
            * override this method for handling normal response from insertarCatalog1 operation
            */
           public void receiveResultinsertarCatalog1(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog1Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog1 operation
           */
            public void receiveErrorinsertarCatalog1(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog13 method
            * override this method for handling normal response from actualizarCatalog13 operation
            */
           public void receiveResultactualizarCatalog13(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog13Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog13 operation
           */
            public void receiveErroractualizarCatalog13(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPerfilesExistentes method
            * override this method for handling normal response from getPerfilesExistentes operation
            */
           public void receiveResultgetPerfilesExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetPerfilesExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPerfilesExistentes operation
           */
            public void receiveErrorgetPerfilesExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTipoUsuariosExistentes method
            * override this method for handling normal response from getTipoUsuariosExistentes operation
            */
           public void receiveResultgetTipoUsuariosExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetTipoUsuariosExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTipoUsuariosExistentes operation
           */
            public void receiveErrorgetTipoUsuariosExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog2 method
            * override this method for handling normal response from eliminarCatalog2 operation
            */
           public void receiveResulteliminarCatalog2(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog2Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog2 operation
           */
            public void receiveErroreliminarCatalog2(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog2 method
            * override this method for handling normal response from actualizarCatalog2 operation
            */
           public void receiveResultactualizarCatalog2(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog2Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog2 operation
           */
            public void receiveErroractualizarCatalog2(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog19 method
            * override this method for handling normal response from insertarCatalog19 operation
            */
           public void receiveResultinsertarCatalog19(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog19Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog19 operation
           */
            public void receiveErrorinsertarCatalog19(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog4 method
            * override this method for handling normal response from eliminarCatalog4 operation
            */
           public void receiveResulteliminarCatalog4(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog4Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog4 operation
           */
            public void receiveErroreliminarCatalog4(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMarcasExistentes method
            * override this method for handling normal response from getMarcasExistentes operation
            */
           public void receiveResultgetMarcasExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetMarcasExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMarcasExistentes operation
           */
            public void receiveErrorgetMarcasExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUsuariosResponsableSubBodega method
            * override this method for handling normal response from getUsuariosResponsableSubBodega operation
            */
           public void receiveResultgetUsuariosResponsableSubBodega(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetUsuariosResponsableSubBodegaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUsuariosResponsableSubBodega operation
           */
            public void receiveErrorgetUsuariosResponsableSubBodega(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarPermisos method
            * override this method for handling normal response from insertarPermisos operation
            */
           public void receiveResultinsertarPermisos(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarPermisosResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarPermisos operation
           */
            public void receiveErrorinsertarPermisos(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog11 method
            * override this method for handling normal response from eliminarCatalog11 operation
            */
           public void receiveResulteliminarCatalog11(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog11Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog11 operation
           */
            public void receiveErroreliminarCatalog11(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog20 method
            * override this method for handling normal response from actualizarCatalog20 operation
            */
           public void receiveResultactualizarCatalog20(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog20Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog20 operation
           */
            public void receiveErroractualizarCatalog20(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog19 method
            * override this method for handling normal response from actualizarCatalog19 operation
            */
           public void receiveResultactualizarCatalog19(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog19Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog19 operation
           */
            public void receiveErroractualizarCatalog19(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog22 method
            * override this method for handling normal response from eliminarCatalog22 operation
            */
           public void receiveResulteliminarCatalog22(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog22Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog22 operation
           */
            public void receiveErroreliminarCatalog22(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog5 method
            * override this method for handling normal response from actualizarCatalog5 operation
            */
           public void receiveResultactualizarCatalog5(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog5Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog5 operation
           */
            public void receiveErroractualizarCatalog5(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUsuariosChoferExistentes method
            * override this method for handling normal response from getUsuariosChoferExistentes operation
            */
           public void receiveResultgetUsuariosChoferExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetUsuariosChoferExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUsuariosChoferExistentes operation
           */
            public void receiveErrorgetUsuariosChoferExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDestinatariosExistentes method
            * override this method for handling normal response from getDestinatariosExistentes operation
            */
           public void receiveResultgetDestinatariosExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetDestinatariosExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDestinatariosExistentes operation
           */
            public void receiveErrorgetDestinatariosExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog21 method
            * override this method for handling normal response from insertarCatalog21 operation
            */
           public void receiveResultinsertarCatalog21(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog21Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog21 operation
           */
            public void receiveErrorinsertarCatalog21(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog5 method
            * override this method for handling normal response from eliminarCatalog5 operation
            */
           public void receiveResulteliminarCatalog5(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog5Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog5 operation
           */
            public void receiveErroreliminarCatalog5(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog10 method
            * override this method for handling normal response from actualizarCatalog10 operation
            */
           public void receiveResultactualizarCatalog10(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog10Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog10 operation
           */
            public void receiveErroractualizarCatalog10(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog12 method
            * override this method for handling normal response from eliminarCatalog12 operation
            */
           public void receiveResulteliminarCatalog12(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog12Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog12 operation
           */
            public void receiveErroreliminarCatalog12(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog18 method
            * override this method for handling normal response from insertarCatalog18 operation
            */
           public void receiveResultinsertarCatalog18(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog18Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog18 operation
           */
            public void receiveErrorinsertarCatalog18(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPuestosExistentes method
            * override this method for handling normal response from getPuestosExistentes operation
            */
           public void receiveResultgetPuestosExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetPuestosExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPuestosExistentes operation
           */
            public void receiveErrorgetPuestosExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getReportesTipoDestinatarioExistentes method
            * override this method for handling normal response from getReportesTipoDestinatarioExistentes operation
            */
           public void receiveResultgetReportesTipoDestinatarioExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetReportesTipoDestinatarioExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getReportesTipoDestinatarioExistentes operation
           */
            public void receiveErrorgetReportesTipoDestinatarioExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog9 method
            * override this method for handling normal response from actualizarCatalog9 operation
            */
           public void receiveResultactualizarCatalog9(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog9Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog9 operation
           */
            public void receiveErroractualizarCatalog9(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog12 method
            * override this method for handling normal response from insertarCatalog12 operation
            */
           public void receiveResultinsertarCatalog12(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog12Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog12 operation
           */
            public void receiveErrorinsertarCatalog12(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRepresentantesPorRutaExistentes method
            * override this method for handling normal response from getRepresentantesPorRutaExistentes operation
            */
           public void receiveResultgetRepresentantesPorRutaExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetRepresentantesPorRutaExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRepresentantesPorRutaExistentes operation
           */
            public void receiveErrorgetRepresentantesPorRutaExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog17 method
            * override this method for handling normal response from eliminarCatalog17 operation
            */
           public void receiveResulteliminarCatalog17(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog17Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog17 operation
           */
            public void receiveErroreliminarCatalog17(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPermisosPorModulo method
            * override this method for handling normal response from getPermisosPorModulo operation
            */
           public void receiveResultgetPermisosPorModulo(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetPermisosPorModuloResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPermisosPorModulo operation
           */
            public void receiveErrorgetPermisosPorModulo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog11 method
            * override this method for handling normal response from actualizarCatalog11 operation
            */
           public void receiveResultactualizarCatalog11(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog11Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog11 operation
           */
            public void receiveErroractualizarCatalog11(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getReportesExistentes method
            * override this method for handling normal response from getReportesExistentes operation
            */
           public void receiveResultgetReportesExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetReportesExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getReportesExistentes operation
           */
            public void receiveErrorgetReportesExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRutasAlternasExistentes method
            * override this method for handling normal response from getRutasAlternasExistentes operation
            */
           public void receiveResultgetRutasAlternasExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetRutasAlternasExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRutasAlternasExistentes operation
           */
            public void receiveErrorgetRutasAlternasExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog22 method
            * override this method for handling normal response from insertarCatalog22 operation
            */
           public void receiveResultinsertarCatalog22(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog22Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog22 operation
           */
            public void receiveErrorinsertarCatalog22(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog5 method
            * override this method for handling normal response from insertarCatalog5 operation
            */
           public void receiveResultinsertarCatalog5(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog5Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog5 operation
           */
            public void receiveErrorinsertarCatalog5(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog13 method
            * override this method for handling normal response from insertarCatalog13 operation
            */
           public void receiveResultinsertarCatalog13(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog13Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog13 operation
           */
            public void receiveErrorinsertarCatalog13(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog4 method
            * override this method for handling normal response from actualizarCatalog4 operation
            */
           public void receiveResultactualizarCatalog4(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog4Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog4 operation
           */
            public void receiveErroractualizarCatalog4(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTiposRutaExistentes method
            * override this method for handling normal response from getTiposRutaExistentes operation
            */
           public void receiveResultgetTiposRutaExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetTiposRutaExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTiposRutaExistentes operation
           */
            public void receiveErrorgetTiposRutaExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog8 method
            * override this method for handling normal response from insertarCatalog8 operation
            */
           public void receiveResultinsertarCatalog8(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog8Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog8 operation
           */
            public void receiveErrorinsertarCatalog8(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog15 method
            * override this method for handling normal response from insertarCatalog15 operation
            */
           public void receiveResultinsertarCatalog15(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog15Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog15 operation
           */
            public void receiveErrorinsertarCatalog15(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getEmpleadosExistentes method
            * override this method for handling normal response from getEmpleadosExistentes operation
            */
           public void receiveResultgetEmpleadosExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetEmpleadosExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getEmpleadosExistentes operation
           */
            public void receiveErrorgetEmpleadosExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog20 method
            * override this method for handling normal response from insertarCatalog20 operation
            */
           public void receiveResultinsertarCatalog20(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog20Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog20 operation
           */
            public void receiveErrorinsertarCatalog20(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getParamsLDCExistentes method
            * override this method for handling normal response from getParamsLDCExistentes operation
            */
           public void receiveResultgetParamsLDCExistentes(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.GetParamsLDCExistentesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getParamsLDCExistentes operation
           */
            public void receiveErrorgetParamsLDCExistentes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog20 method
            * override this method for handling normal response from eliminarCatalog20 operation
            */
           public void receiveResulteliminarCatalog20(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog20Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog20 operation
           */
            public void receiveErroreliminarCatalog20(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog16 method
            * override this method for handling normal response from actualizarCatalog16 operation
            */
           public void receiveResultactualizarCatalog16(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog16Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog16 operation
           */
            public void receiveErroractualizarCatalog16(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog13 method
            * override this method for handling normal response from eliminarCatalog13 operation
            */
           public void receiveResulteliminarCatalog13(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog13Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog13 operation
           */
            public void receiveErroreliminarCatalog13(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog18 method
            * override this method for handling normal response from eliminarCatalog18 operation
            */
           public void receiveResulteliminarCatalog18(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog18Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog18 operation
           */
            public void receiveErroreliminarCatalog18(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog1 method
            * override this method for handling normal response from eliminarCatalog1 operation
            */
           public void receiveResulteliminarCatalog1(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog1Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog1 operation
           */
            public void receiveErroreliminarCatalog1(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for actualizarCatalog7 method
            * override this method for handling normal response from actualizarCatalog7 operation
            */
           public void receiveResultactualizarCatalog7(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ActualizarCatalog7Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from actualizarCatalog7 operation
           */
            public void receiveErroractualizarCatalog7(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog7 method
            * override this method for handling normal response from insertarCatalog7 operation
            */
           public void receiveResultinsertarCatalog7(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog7Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog7 operation
           */
            public void receiveErrorinsertarCatalog7(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog21 method
            * override this method for handling normal response from eliminarCatalog21 operation
            */
           public void receiveResulteliminarCatalog21(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog21Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog21 operation
           */
            public void receiveErroreliminarCatalog21(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog3 method
            * override this method for handling normal response from insertarCatalog3 operation
            */
           public void receiveResultinsertarCatalog3(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog3Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog3 operation
           */
            public void receiveErrorinsertarCatalog3(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for eliminarCatalog14 method
            * override this method for handling normal response from eliminarCatalog14 operation
            */
           public void receiveResulteliminarCatalog14(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.EliminarCatalog14Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from eliminarCatalog14 operation
           */
            public void receiveErroreliminarCatalog14(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for insertarCatalog6 method
            * override this method for handling normal response from insertarCatalog6 operation
            */
           public void receiveResultinsertarCatalog6(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.InsertarCatalog6Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from insertarCatalog6 operation
           */
            public void receiveErrorinsertarCatalog6(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for validaRutaAlternaDuplicada method
            * override this method for handling normal response from validaRutaAlternaDuplicada operation
            */
           public void receiveResultvalidaRutaAlternaDuplicada(
                    com.datacode.avon_ots_ws.MantenimientoElementoControllerStub.ValidaRutaAlternaDuplicadaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from validaRutaAlternaDuplicada operation
           */
            public void receiveErrorvalidaRutaAlternaDuplicada(java.lang.Exception e) {
            }
                


    }
    
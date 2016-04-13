
/**
 * ActualizacionCatalogosDesdeArchivoCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  ActualizacionCatalogosDesdeArchivoCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ActualizacionCatalogosDesdeArchivoCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ActualizacionCatalogosDesdeArchivoCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ActualizacionCatalogosDesdeArchivoCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for iniciarActualizacionViaMonitoreo method
            * override this method for handling normal response from iniciarActualizacionViaMonitoreo operation
            */
           public void receiveResultiniciarActualizacionViaMonitoreo(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from iniciarActualizacionViaMonitoreo operation
           */
            public void receiveErroriniciarActualizacionViaMonitoreo(java.lang.Exception e) {
            }
                


    }
    
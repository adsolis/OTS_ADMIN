
/**
 * BO_CU001CallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.avon_ots_ws;

    /**
     *  BO_CU001CallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class BO_CU001CallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public BO_CU001CallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public BO_CU001CallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for saludar method
            * override this method for handling normal response from saludar operation
            */
           public void receiveResultsaludar(
                    com.datacode.avon_ots_ws.BO_CU001Stub.SaludarResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saludar operation
           */
            public void receiveErrorsaludar(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for despedir method
            * override this method for handling normal response from despedir operation
            */
           public void receiveResultdespedir(
                    com.datacode.avon_ots_ws.BO_CU001Stub.DespedirResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from despedir operation
           */
            public void receiveErrordespedir(java.lang.Exception e) {
            }
                


    }
    
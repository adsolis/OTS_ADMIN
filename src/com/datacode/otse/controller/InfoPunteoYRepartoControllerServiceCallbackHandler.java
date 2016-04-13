
/**
 * InfoPunteoYRepartoControllerServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */

    package com.datacode.otse.controller;

    /**
     *  InfoPunteoYRepartoControllerServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class InfoPunteoYRepartoControllerServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public InfoPunteoYRepartoControllerServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public InfoPunteoYRepartoControllerServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for infoSmsPendientes method
            * override this method for handling normal response from infoSmsPendientes operation
            */
           public void receiveResultinfoSmsPendientes(
                    com.datacode.otse.controller.InfoPunteoYRepartoControllerServiceStub.InfoSmsPendientesResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from infoSmsPendientes operation
           */
            public void receiveErrorinfoSmsPendientes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for infoPunteoYReparto method
            * override this method for handling normal response from infoPunteoYReparto operation
            */
           public void receiveResultinfoPunteoYReparto(
                    com.datacode.otse.controller.InfoPunteoYRepartoControllerServiceStub.InfoPunteoYRepartoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from infoPunteoYReparto operation
           */
            public void receiveErrorinfoPunteoYReparto(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for prueba method
            * override this method for handling normal response from prueba operation
            */
           public void receiveResultprueba(
                    com.datacode.otse.controller.InfoPunteoYRepartoControllerServiceStub.PruebaResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from prueba operation
           */
            public void receiveErrorprueba(java.lang.Exception e) {
            }
                


    }
    
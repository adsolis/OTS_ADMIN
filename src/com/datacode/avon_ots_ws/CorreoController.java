

/**
 * CorreoController.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */

    package com.datacode.avon_ots_ws;

    /*
     *  CorreoController java interface
     */

    public interface CorreoController {
          

        /**
          * Auto generated method signature
          * 
                    * @param enviarCorreo0
                
         */

         
                     public com.datacode.avon_ots_ws.EnviarCorreoResponse enviarCorreo(

                        com.datacode.avon_ots_ws.EnviarCorreo enviarCorreo0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param enviarCorreo0
            
          */
        public void startenviarCorreo(

            com.datacode.avon_ots_ws.EnviarCorreo enviarCorreo0,

            final com.datacode.avon_ots_ws.CorreoControllerCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param obtenerCorreosNoEnviados2
                
         */

         
                     public com.datacode.avon_ots_ws.ObtenerCorreosNoEnviadosResponse obtenerCorreosNoEnviados(

                        com.datacode.avon_ots_ws.ObtenerCorreosNoEnviados obtenerCorreosNoEnviados2)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param obtenerCorreosNoEnviados2
            
          */
        public void startobtenerCorreosNoEnviados(

            com.datacode.avon_ots_ws.ObtenerCorreosNoEnviados obtenerCorreosNoEnviados2,

            final com.datacode.avon_ots_ws.CorreoControllerCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param obtenerListaLiquidacionesMail4
                
         */

         
                     public com.datacode.avon_ots_ws.ObtenerListaLiquidacionesMailResponse obtenerListaLiquidacionesMail(

                        com.datacode.avon_ots_ws.ObtenerListaLiquidacionesMail obtenerListaLiquidacionesMail4)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param obtenerListaLiquidacionesMail4
            
          */
        public void startobtenerListaLiquidacionesMail(

            com.datacode.avon_ots_ws.ObtenerListaLiquidacionesMail obtenerListaLiquidacionesMail4,

            final com.datacode.avon_ots_ws.CorreoControllerCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param actualizaEnviadoReporteSubBodega6
                
         */

         
                     public com.datacode.avon_ots_ws.ActualizaEnviadoReporteSubBodegaResponse actualizaEnviadoReporteSubBodega(

                        com.datacode.avon_ots_ws.ActualizaEnviadoReporteSubBodega actualizaEnviadoReporteSubBodega6)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param actualizaEnviadoReporteSubBodega6
            
          */
        public void startactualizaEnviadoReporteSubBodega(

            com.datacode.avon_ots_ws.ActualizaEnviadoReporteSubBodega actualizaEnviadoReporteSubBodega6,

            final com.datacode.avon_ots_ws.CorreoControllerCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param registrarCorreoNoEnviado8
                
         */

         
                     public com.datacode.avon_ots_ws.RegistrarCorreoNoEnviadoResponse registrarCorreoNoEnviado(

                        com.datacode.avon_ots_ws.RegistrarCorreoNoEnviado registrarCorreoNoEnviado8)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param registrarCorreoNoEnviado8
            
          */
        public void startregistrarCorreoNoEnviado(

            com.datacode.avon_ots_ws.RegistrarCorreoNoEnviado registrarCorreoNoEnviado8,

            final com.datacode.avon_ots_ws.CorreoControllerCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param registrarEnvioMail10
                
         */

         
                     public com.datacode.avon_ots_ws.RegistrarEnvioMailResponse registrarEnvioMail(

                        com.datacode.avon_ots_ws.RegistrarEnvioMail registrarEnvioMail10)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param registrarEnvioMail10
            
          */
        public void startregistrarEnvioMail(

            com.datacode.avon_ots_ws.RegistrarEnvioMail registrarEnvioMail10,

            final com.datacode.avon_ots_ws.CorreoControllerCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param actualizarStatusLiquidacionesMail12
                
         */

         
                     public com.datacode.avon_ots_ws.ActualizarStatusLiquidacionesMailResponse actualizarStatusLiquidacionesMail(

                        com.datacode.avon_ots_ws.ActualizarStatusLiquidacionesMail actualizarStatusLiquidacionesMail12)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param actualizarStatusLiquidacionesMail12
            
          */
        public void startactualizarStatusLiquidacionesMail(

            com.datacode.avon_ots_ws.ActualizarStatusLiquidacionesMail actualizarStatusLiquidacionesMail12,

            final com.datacode.avon_ots_ws.CorreoControllerCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param eliminarCorreoNoEnviado14
                
         */

         
                     public com.datacode.avon_ots_ws.EliminarCorreoNoEnviadoResponse eliminarCorreoNoEnviado(

                        com.datacode.avon_ots_ws.EliminarCorreoNoEnviado eliminarCorreoNoEnviado14)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param eliminarCorreoNoEnviado14
            
          */
        public void starteliminarCorreoNoEnviado(

            com.datacode.avon_ots_ws.EliminarCorreoNoEnviado eliminarCorreoNoEnviado14,

            final com.datacode.avon_ots_ws.CorreoControllerCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param registrarEnvioMailReporteSubbodega16
                
         */

         
                     public com.datacode.avon_ots_ws.RegistrarEnvioMailReporteSubbodegaResponse registrarEnvioMailReporteSubbodega(

                        com.datacode.avon_ots_ws.RegistrarEnvioMailReporteSubbodega registrarEnvioMailReporteSubbodega16)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param registrarEnvioMailReporteSubbodega16
            
          */
        public void startregistrarEnvioMailReporteSubbodega(

            com.datacode.avon_ots_ws.RegistrarEnvioMailReporteSubbodega registrarEnvioMailReporteSubbodega16,

            final com.datacode.avon_ots_ws.CorreoControllerCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param obtenerDatosCorreoCuentaMaestra18
                
         */

         
                     public com.datacode.avon_ots_ws.ObtenerDatosCorreoCuentaMaestraResponse obtenerDatosCorreoCuentaMaestra(

                        com.datacode.avon_ots_ws.ObtenerDatosCorreoCuentaMaestra obtenerDatosCorreoCuentaMaestra18)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param obtenerDatosCorreoCuentaMaestra18
            
          */
        public void startobtenerDatosCorreoCuentaMaestra(

            com.datacode.avon_ots_ws.ObtenerDatosCorreoCuentaMaestra obtenerDatosCorreoCuentaMaestra18,

            final com.datacode.avon_ots_ws.CorreoControllerCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    
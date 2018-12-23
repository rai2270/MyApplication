package com.example.tr.myapplication.utility;

import com.example.tr.myapplication.domain.event.bus.MainThreadBus;

public class BusUtils {

    public static void unregisterBusIfRegistered(Object registeredObject){
        try{
            MainThreadBus.getInstance().unregister(registeredObject);
        } catch (IllegalArgumentException iae){
            LumberJack.logGeneric("Bus unregister on " + registeredObject.getClass().getCanonicalName()
                    + " failed. " + iae.toString());
        } catch (NullPointerException npe){
            LumberJack.logGeneric("Bus unregister on null detected");
        }
    }

    public static void registerBusIfNotRegistered(Object unregisteredObject){
        try{
            MainThreadBus.getInstance().register(unregisteredObject);
        } catch (IllegalArgumentException iae){
            LumberJack.logGeneric("Bus register on " + unregisteredObject.getClass().getCanonicalName()
                    + " failed. " + iae.toString());
        } catch (NullPointerException npe){
            LumberJack.logGeneric("Bus register on null detected");
        }
    }

}

## Device (Model and Controller)
There are some typical device kinds.
* Common Device
    > real common device, maybe just a light or fan, but the device have some traits

    **Trait**
    > Below constrains is common to All the *Device*
    * Ability to Message In/Out
        > That means the device is no-statelessness, self-control, self-monitor, behavior-defined
          
    * The Way to Message In/Out
        > the method to make it, using TCP or MQTT based on internet or Bluetooth Serial,
          does it make the upper layer a bit different
          For example, if the device use Bluetooth or Local-Network TCP, then the device couldn't communicate with cloud directly,
          so something like proxy will be necessary in this case.

    * The Message Format
        > Different device's service or state is not all the same, so need some extra message to specify the message format or version
          By the way, device description is necessary

    * Driver
        > It evenly can be a file like json or yaml, can be restored and deserialized (like installing the driver)
          It can't be better if there is a plugin system, it means once the important system like cloud was deployed, if there are new
          devices it's unnecessary to make new `switch case` items, but use the unit interface to control different device.
          But it's inevitable to write the less support for the system, judging the difficulty to code and benefit once the code finished.

* High-End Device
  > this kind of device often perform better on computation, it could offer extra function for the system,
  to embedded with AI like Yolo, GPT, voice-recognize, face-recognize and so on,
  This kind device could offer api

* Controller Device
    > means the device on the one hand it serves like common device,
      on the other hand it could control other device acting like switch/router on the internet
  
    * 'Switch / Router' Device
        > the host have ability to switch message, just like the plug-board, maybe it could control the device plugged in it.
          it will be useful to gather the device have the same protocol such as the Local-Network or Serial-Switch,
          This kind device could offer proxy
      
    * based on user's message sending

## View 
To have a view of the hole system's work, it comes.
* Web-ControlPanel
    > Web-Page Based

* Phone-ControlPanel
    > Android Based
  
## Cloud as Model
> considering the control message would be a huge number with the user or device becoming more and more
  so it's impossible to store all the control message or device state in the database, 

What can be stored in the database?
* device meta info
* already paired device
* cloud control log
* account info
* user info

What can be stored in the memory?
* connect session
    > if the communication is based on connection
      like serial connection or http keep-alive
* device current cached state
    > this task maybe only available to local-control-device


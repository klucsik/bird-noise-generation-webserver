# Using the Webapplication
# Using the mp3 player device
## Device workflow on startup
At startup the device enter setup mode (signaled by the *Started setup* voice message), if the preconfigured WiFi network is available, it will try to connect, if sucessfull, the *Connected* audio message will be palyed. After this, the device will connect to the webserver, and attempt to synchronize clock, tracklength data, playparams, and firmware. If Logcollector network is detected, instead of the synchronization, it will send the logs to the logcollector, see about that in the logcollector section.

NOTE: If the connection is not made, the device will not attempt to synchronize.

At the end os setup the time settings will be checked, if the time isn't setted correctly, the *error* voice message will be played, and the device will restart after 15 seconds.
If no error detected, *finished setup* voice message will be played, and the device will change into working mode.

# Using the logcollector device
The logcollector device collects logs from devices without permanent internet access.
It works by supplying WiFi connection to the mp3 player device, and save the logs to an SD card or to internal storage (if SD card is not present).
The mp3 player devices will not try to access the server through the logcollector (so they will not update playparams, tracklist informations, and firmware).
## Collection of logs
* **Power the device**
The logcollector device needs to be powered from the USB port. It's advised to plug an SD card in the device, so it can store more logs.
There is no warning when the device runs out of storage space, and it can loose data if it does run out of storage space, so use it without SD card for only small amount of logs (around 10 days worth of logs).
The device should create a WiFi access point named *logcollector-access-point* in a minute, you can check this with a mobile phone, search for wifi connections, you should see it. If not, contact support (probably the firmware is broken).

* **Restart the mp3 player device** The mp3 player will try to connect to the *logcollector-access-point* and the *birdspot* accesspoints. If both are present, it will choose randomly. 
If its connected to the *logcollector-access-point*, it will play the *connected to logcollector* sound. After that, it will send the logs to the logcollector device.
When all the logs are sent, the mp3 player device will play the *setup finished* sound. 

The two devices should be in WiFi range in the sending process (around 10 meter). 

The logcollector is capable to connect up to 8 mp3 player devices at the same time (but the transfer rate will be slower).

## Send logs to the webserver
* **Bring the device to birdspot range** The logcollector device continuously search for the preconfigured access points to connect to the internet.
The default WiFi access point is SSID: birdspot, password: Jelszo11, if you want to set up another network connection, consult with the dev readme.
If it is connected, it will automatically send the logs to the webserver. 
Currently there is no onboard indication about when the sending starts, or it is successful or not, but you can check the appearing logs on the server.

# PUSH-UP Counter App
It does count the push-up each time you touch the button with your chin.
 
> For Development Purpose Only

As of now, if you want to deploy the app to any device, 
your app needs to have signed certificates which you can do easily by running a series of command as below.

```sh
$ keytool -genkey -v -keystore my-release-key.keystore -alias alias_name -keyalg RSA -keysize 2048 -validity 10000
$ jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore my-release-key.keystore ./bin/PushUp_Counter-release-unsigned.apk alias_name
$ jarsigner -verify -verbose -certs bin/PushUp_Counter-release-unsigned.apk
```
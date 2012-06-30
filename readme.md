io2012hackathon
===============

Google IO 2012 Hackathon to solve social challenges: Go Green - Crowd source real MPG for vehicles depending on location

The idea behind this application is to provide a simple tool to calculate vehicle fuel efficiency.  Using an app on their Android device, a person keeps track of mileage and amount of fuel each time he fill up.  The app calculates fuel efficiency.  It's simple and convenient.

This in itself isn't very powerful.  But as people use this app it fuels a growing corpus of data about fuel efficiency per model year, make, model, date, and location.  This data can then be used by consumers to make purchasing decisions using real world fuel efficiency information; by governments to refine policies such as required or regulated fuel additives and taxes, and by engineers when designing and refining vehicles.

The application is live at http://truefuelefficiency.appspot.com.  The Android application can be found at https://github.com/shellum/io2012hackathon.  A compiled APK is hiding in there in androidApp/bin.

For the hackathon, the project started with three people, previously strangers, at Google I/O Extended 2012 Utah.


This project is made up of three parts:
A server backend running on AppEngine, using Objectify and Jersey. This is the main datastore.
A frontend written in GWT, that allows data to be entered probably from desktop/laptop computers.
A frontend that can be run from Android devices, that allows data to be entered from mobile devices like phones.

The applications are currently very rough.  It is more a proof of concept than anything else.  But we believe the idea has merit and aims to affect real change with the biggest consumer of fossil fuels - the cars we drive.
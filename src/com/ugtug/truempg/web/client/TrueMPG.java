package com.ugtug.truempg.web.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.ugtug.truempg.web.shared.Vehicle;
import com.ugtug.truempg.web.shared.VehicleList;

/**
 * Main entry point for application.
 * 
 * <p>Title: True MPG.</p>
 * <p>Description: Calculates your true MPG for your vehicles.</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * @author Rene Dupre / Jarrod Ribble
 * @version 1.0
 */
public class TrueMPG implements EntryPoint, ChangeHandler, ClickHandler {
    private TabLayoutPanel mainMenu = new TabLayoutPanel(1.5, Unit.EM);     // main menu panel

    // Login panel
    private VerticalPanel loginVP = new VerticalPanel();                    // login panel
    private TextBox tbUser = new TextBox();                                 // user name text box
    private TextBox tbPassword = new TextBox();                             // password text box
    private Button loginButton = new Button("Login");                       // send button for posting login
    private Button logoutButton = new Button("Logout");                     // send button for posting logout
    private HTML loggedIn = new HTML("");                                   // logged in message
    private Grid gLogin;                                                    // grid for login data

    // Vehicle panel
    private VerticalPanel vehicleOuterVP = new VerticalPanel();             // outer vehicle panel
    private VerticalPanel vehicleVP = new VerticalPanel();                  // vehicle panel list
    private VerticalPanel addVehicleVP = new VerticalPanel();               // add vehicle panel
    private TextBox tbYear = new TextBox();                               
    private TextBox tbMake = new TextBox();  
    private TextBox tbModel = new TextBox();                               
    private TextBox tbVIN = new TextBox();  
    private Grid gVehicleList;                                              // grid for vehicle list
    private Button addVehicleButton = new Button("Add Vehicle");            // add vehicle button
    private Button postVehicleButton = new Button("Save Vehicle");          // post vehicle button

    // Fill up panel
    private VerticalPanel fillOuterVP = new VerticalPanel();                // outer fillup panel
    private VerticalPanel fillupVP = new VerticalPanel();                   // fillup panel
    private HTML hdgFillup = new HTML("Enter your Fillup Information:");    // heading for fill up panel
    private TextBox tbGallons = new TextBox();                              // gallons text box
    private TextBox tbOdometer = new TextBox();                             // odometer text box
    private TextBox tbFillDate = new TextBox();                             // fill date
    private ListBox lbVehicle = new ListBox();                              // vehicle list
    private Button postFillupButton = new Button("Send");                         // send button for posting fill up
    private Button againButton = new Button("Fillup Again");                // again button for another fill up
    private VerticalPanel mpgVP = new VerticalPanel();                      // MPG results panel

    // About panel
    private FlowPanel aboutFP = new FlowPanel();                            // about panel   
    
    private HTML hdgYear = new HTML("Year");
    private HTML hdgMake = new HTML("Make");
    private HTML hdgModel = new HTML("Model");
    private HTML hdgVIN = new HTML("VIN Number");
    
    private HTML hdgYearNew = new HTML("Year");
    private HTML hdgMakeNew = new HTML("Make");
    private HTML hdgModelNew = new HTML("Model");
    private HTML hdgVINNew = new HTML("VIN Number");

    private String chosenVehicle;                                           // chosen vehicle name
    private String userName;                                                // user name
    private VehicleList myVehicles;                                         // list of vehicles for this user
    private String vehiclePost;                                             // vehicle post/form statement
    private String fillupPost;                                              // post/form for fillup
    private String todaysDate;                                              // todays date yyyy-mm-dd format
    private String myMPG;                                                   // my car's mpg
    private String avgMPG;                                                  // average mpg


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        // get today's date
        Date date = new Date();        
        todaysDate = DateTimeFormat.getFormat("yyyy-MM-dd").format(date);
        
        // set up main panel
        DockLayoutPanel appPanel = new DockLayoutPanel(Unit.EM);
        RootLayoutPanel.get().add(appPanel);
        HTML hdgMain = new HTML("True RPG<br><br>");    
        hdgMain.setStyleName("bigText");
        appPanel.addNorth(hdgMain, 2);
        appPanel.addWest(new HTML(" "), 2);
        appPanel.add(mainMenu);

        // set up individual panels for each tab

        // login panel
        HTML hdgUser = new HTML("Username:");
        HTML hdgPassword = new HTML("Password:");

        gLogin = new Grid(2, 2);
        gLogin.setWidget(0, 0, hdgUser);
        gLogin.setWidget(0, 1, tbUser);
        gLogin.setWidget(1, 0, hdgPassword);
        gLogin.setWidget(1, 1, tbPassword);
        loginButton.addStyleName("sendButton");
        loginButton.addClickHandler(this);
        logoutButton.addStyleName("sendButton");
        logoutButton.addClickHandler(this);

        loginVP.add(gLogin);
        loginVP.add(loggedIn);
        loginVP.add(loginButton);
        
        // vehicle panel
        vehicleOuterVP.add(vehicleVP);
        vehicleOuterVP.add(addVehicleVP);
        postVehicleButton.addStyleName("sendButton");
        postVehicleButton.addClickHandler(this);      
        addVehicleButton.addStyleName("sendButton");
        addVehicleButton.addClickHandler(this);     
        
        // Fill up panel        
        HTML hdgVehicle = new HTML("Vehicle:");
        HTML hdgDate = new HTML("Fillup Date:");
        HTML hdgGallons = new HTML("Gallons:");
        HTML hdgOdometer = new HTML("Odometer:");
        tbFillDate.setText(todaysDate);

        lbVehicle.setTitle("Pick a vehicle.");
        lbVehicle.addChangeHandler(this);

        // load fillup grid
        Grid gFillup = new Grid(4, 3);
        //gFillup.setWidget(0, 0, hdgFillup);
        gFillup.setWidget(0, 1, hdgVehicle);
        gFillup.setWidget(0, 2, lbVehicle);
        gFillup.setWidget(1, 1, hdgDate);
        gFillup.setWidget(1, 2, tbFillDate);
        gFillup.setWidget(2, 1, hdgOdometer);
        gFillup.setWidget(2, 2, tbOdometer);
        gFillup.setWidget(3, 1, hdgGallons);
        gFillup.setWidget(3, 2, tbGallons);

        postFillupButton.addStyleName("sendButton");
        postFillupButton.addClickHandler(this);
        againButton.addStyleName("sendButton");
        againButton.addClickHandler(this);        
        
        fillupVP.add(hdgFillup);
        fillupVP.add(gFillup);
        fillupVP.add(postFillupButton);

        fillOuterVP.add(fillupVP);
        fillOuterVP.add(mpgVP);

        // about panel
        HTML aboutText = new HTML(getAboutText());
        aboutFP.add(aboutText);

        // set up panels in tabs
        mainMenu.add(loginVP, "Login");       
        mainMenu.add(aboutFP, "About");
    }

    /**
     * Fill the vehicle list with vehicles for user.
     */
    private void fillVehicleLists()
    {
    	fillVehicleList();
    	fillVehicleDropDown();
    }
    
    private void fillVehicleList()
    {
        vehicleVP.clear();
        gVehicleList = new Grid (myVehicles.getVehicleCount()+2, 4);
        gVehicleList.setWidget(0, 0, hdgYear);
        gVehicleList.setWidget(0, 1, hdgMake);
        gVehicleList.setWidget(0, 2, hdgModel);
        gVehicleList.setWidget(0, 3, hdgVIN);

        int carCtr = 0;
        for (int i=1; i<=myVehicles.getVehicleCount(); i++)
        {
            gVehicleList.setHTML(i, 0, myVehicles.getMyList().get(carCtr).getVehicleYear());
            gVehicleList.setHTML(i, 1, myVehicles.getMyList().get(carCtr).getMake());
            gVehicleList.setHTML(i, 2, myVehicles.getMyList().get(carCtr).getModel());
            gVehicleList.setHTML(i, 3, myVehicles.getMyList().get(carCtr).getVin());
            carCtr++;
        }

        vehicleVP.add(gVehicleList);
        vehicleVP.add(addVehicleButton);
    }
    

    private void fillVehicleDropDown()
    {
        // set drop down for fill up
    	lbVehicle.clear();
        for (int i=0; i<myVehicles.getVehicleCount(); i++) {
        	Vehicle v = myVehicles.getMyList().get(i);
            lbVehicle.addItem(v.getVehicleYear() + " " + v.getMake() + " " + v.getModel(), v.getVehicleID());
        }

        lbVehicle.setVisibleItemCount(1);
        lbVehicle.setSelectedIndex(0);
        chosenVehicle = myVehicles.getVehicleCount() == 0 ? null : myVehicles.getMyList().get(0).getVehicleName();
    }

    
    /**
     * Display Add new vehicle panel.
     */
    private void addNewVehicle()
    {
        Grid gAddNew = new Grid(4, 3);
        gAddNew.setWidget(0, 1, hdgYearNew);
        gAddNew.setWidget(0, 2, tbYear);
        gAddNew.setWidget(1, 1, hdgMakeNew);
        gAddNew.setWidget(1, 2, tbMake);
        gAddNew.setWidget(2, 1, hdgModelNew);
        gAddNew.setWidget(2, 2, tbModel);
        gAddNew.setWidget(3, 1, hdgVINNew);
        gAddNew.setWidget(3, 2, tbVIN);
        
        HTML hdgAddNew = new HTML("Add new vehicle");
        addVehicleVP.clear();
        addVehicleVP.addStyleName("dialogVPanel");
        addVehicleVP.add(hdgAddNew);
        addVehicleVP.add(gAddNew);
       
        addVehicleVP.add(postVehicleButton);
        addVehicleVP.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);        
        addVehicleVP.setVisible(true);
        vehicleVP.setVisible(false);        
    }
    
    /**
     * Show results screen for MPG.
     */
    private void showMPGResults()
    {
        HTML hdgResults = new HTML("Your True MPG for "+chosenVehicle+":");
        mpgVP.clear();
        mpgVP.addStyleName("dialogVPanel");
        mpgVP.add(hdgResults);
        mpgVP.add(new HTML("<b>This fillup: "+myMPG+" MPG</b>"));
        mpgVP.add(new HTML("<br><b>Crowd Average: "+ avgMPG+" MPG</b>"));
        mpgVP.add(againButton);
        mpgVP.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);        
        mpgVP.setVisible(true);
        fillupVP.setVisible(false);
        postFillupButton.setEnabled(true);
    }

    /**
     * Handles when drop down values are changed.
     * @param ChangeEvent the incoming event.
     */
    public void onChange(com.google.gwt.event.dom.client.ChangeEvent event)
    {
        Widget sender = (Widget) event.getSource();
        if (sender == lbVehicle)
        {
            chosenVehicle = lbVehicle.getItemText(lbVehicle.getSelectedIndex());
        }   
    }

    /**
     * Handles when filters are clicked.
     * @param ClickEvent the incoming object that was clicked.
     */
    public void onClick(ClickEvent event)
    {
        Widget sender = (Widget) event.getSource();
        // check which button is pressed
        if (sender == loginButton)
        {
            //TODO need to hook up to google authentication
            userName = tbUser.getText();
            loginVP.remove(loginButton);        // remove login button 
            loginVP.add(logoutButton);          // add logout button
            gLogin.setVisible(false);           // hide login info
            loggedIn.setHTML("You are logged in as "+userName);

            // reset any fillup
            mpgVP.setVisible(false);
            tbGallons.setText("");
            tbOdometer.setText("");
            fillupVP.setVisible(true);
            
            // add all other tabs now that they are logged in
            mainMenu.remove(aboutFP);
            mainMenu.add(vehicleOuterVP, "Vehicles");
            mainMenu.add(fillOuterVP, "Fill-up");
            mainMenu.add(aboutFP, "About");

            readVehicleForUser();       // get list of vehicles for user
        }
        else if (sender == logoutButton)
        {
            //TODO need to hook up to google authentication
            loginVP.add(loginButton);        // remove login button 
            loginVP.remove(logoutButton);          // add logout button
            loggedIn.setHTML("");

            // add all other tabs now that they are logged in
            mainMenu.remove(vehicleOuterVP);
            mainMenu.remove(fillOuterVP);
            gLogin.setVisible(true);           // hide login info
        }
        else if (sender == addVehicleButton)
        {
            addNewVehicle();
        }
        else if (sender == postVehicleButton)
        {
            // get data from form and save it
            postVehicleButton.setEnabled(false);
            vehiclePost = setVehicleFormPost(userName, tbYear.getText(), tbMake.getText(), tbModel.getText(), tbVIN.getText());     
            postVehicleForUser(vehiclePost);        // post/save it            
        }  
        else if (sender == postFillupButton)
        {
            // post fill up to database
            postFillupButton.setEnabled(false);
            fillupPost = setFillupForm(myVehicles.getMyList().get(lbVehicle.getSelectedIndex()).getVehicleID(), 
                    tbFillDate.getText(), tbGallons.getText(), tbOdometer.getText(), "0", "0");
            postFillup(fillupPost);
        	postNewFillup();
        }
        else if (sender == againButton)
        {
            // get ready for another fill up
            mpgVP.setVisible(false);
            tbGallons.setText("");
            tbOdometer.setText("");
            fillupVP.setVisible(true);
        } 
    }
    
    private void postNewVehicle()
    {
    	RequestBuilder builder = new RequestBuilder ( RequestBuilder.POST, "/rest/vehicles" );
        builder.setHeader("Content-Type","application/x-www-form-urlencoded"); 
    	String vehicleString = setVehicleFormPost(userName, tbYear.getValue(), tbMake.getValue(), tbModel.getValue(), tbVIN.getValue());

		try {
			builder.sendRequest(vehicleString,
					new RequestCallback() {
						public void onError(Request request,
								Throwable exception) {
							// Couldn't connect to server (could be timeout,
							// SOP violation, etc.)
							displayError("Couldn't create vehicle.");
						}

						public void onResponseReceived(Request request,
								Response response) {
							if (200 == response.getStatusCode()) {			            
					            // go back to list
					            addVehicleVP.setVisible(false);
					            //TODO need to add new car to list
					            vehicleVP.setVisible(true);
					            readVehicleForUser();
							} else {
								displayError("Error creating vehicle ("
										+ response.getStatusText() + ")");
							}
						}
					});
		} catch (RequestException e) {
			displayError("Couldn't retrieve JSON - " + e.getMessage()
					+ e.getStackTrace());
		}
    }
    
    private void postNewFillup()
    {
    	RequestBuilder builder = new RequestBuilder ( RequestBuilder.POST, "/rest/fillups" );
        builder.setHeader("Content-Type","application/x-www-form-urlencoded"); 
        int vidx = lbVehicle.getSelectedIndex();
        if ( vidx == -1 ) return;
    	String fillupString = setFillupForm(lbVehicle.getValue(vidx), null, tbGallons.getValue(), tbOdometer.getValue(), null, null);

		try {
			builder.sendRequest(fillupString,
					new RequestCallback() {
						public void onError(Request request,
								Throwable exception) {
							// Couldn't connect to server (could be timeout,
							// SOP violation, etc.)
							displayError("Couldn't create fillup.");
						}

						public void onResponseReceived(Request request,
								Response response) {
							if (200 == response.getStatusCode()) {	
								// show MPG
					            showMPGResults();
							} else {
								displayError("Error creating fillup ("
										+ response.getStatusText() + ")");
							}
						}
					});
		} catch (RequestException e) {
			displayError("Couldn't retrieve JSON - " + e.getMessage()
					+ e.getStackTrace());
		}

    }

    /**
     * Puts together form string for post for Fill up.
     * @return String the string of data to post for a fill up.
     */

    private String setFillupForm(String inVehicleID, String inFillDate, String inGallons, String inMileage, 
            String inLatitude, String inLongitude)
    {
        String formString;

        formString = "vehicleId=" + inVehicleID;
        if ( inFillDate != null ) formString = formString + "&date=" + inFillDate;
        formString = formString + "&quantity=" + inGallons + "&mileage="+inMileage;
        if ( inLatitude != null ) formString = formString + "&latitude="+inLatitude;
        if ( inLongitude != null ) formString = formString + "&longitude=" + inLongitude;

        return formString;
    }  
    
    /**
     * Parses the JSOn back from reading all vehicles.
     */
    private void parseVehicleJSON(JSONArray array) {

        JSONValue jsonValue;
        myVehicles = new VehicleList();
        
        for (int i=0; i<array.size(); i++) {
          JSONObject jsCar;
          JSONString jsMake, jsModel, jsVIN;
          JSONNumber jsYear, jsVehicleID;
          
          if ((jsCar = array.get(i).isObject()) == null) continue;
          
          if ((jsonValue = jsCar.get("make")) == null) continue;
          jsMake = jsonValue.isString();
          
          if ((jsonValue = jsCar.get("model")) == null) continue;
          jsModel = jsonValue.isString();
          
          if ((jsonValue = jsCar.get("vin")) == null) continue;
          jsVIN = jsonValue.isString();
          
          if ((jsonValue = jsCar.get("year")) == null) continue;
          jsYear = jsonValue.isNumber();
          
          if ((jsonValue = jsCar.get("vehicleId")) == null) continue;

          jsVehicleID = jsonValue.isNumber();

          Vehicle myCar = new Vehicle();
          myCar.setMake(jsMake == null ? null : jsMake.stringValue());
          myCar.setVehicleYear(jsYear == null ? null : jsYear.toString());
          myCar.setVehicleID(jsVehicleID == null ? null : jsVehicleID.toString());
          myCar.setModel(jsModel == null ? null : jsModel.stringValue());
          myCar.setVin(jsVIN == null ? null : jsVIN.stringValue());

          myVehicles.addVehicle(myCar);
        }
      }
    
    /**
     * Parses the response from getting MPG for a vehicle.
     */
    private void parseMPGJSON(JSONValue array) {
        JSONValue jsonValue;
        myVehicles = new VehicleList();
        
          JSONObject jsCar = array.isObject();
          JSONNumber jsMPG, msAvgMPG;
          
          jsonValue = jsCar.get("lastMpg");
          jsMPG = jsonValue.isNumber();
          
          jsonValue = jsCar.get("vehicleAverageMpg");
          msAvgMPG = jsonValue.isNumber();          
         
          myMPG =  Double.toString(jsMPG.doubleValue());
          avgMPG = Double.toString(msAvgMPG.doubleValue());

      }
    
    /**
     * Puts together form string for post for new vehicle.
     * @return String the string of data to post for a new vehicle.
     */
    private String setVehicleFormPost(String inUserID, String inYear, String inMake, String inModel, 
            String inVIN)
    {
        final String formString;

        formString = "userId=" + inUserID  + "&year=" + inYear +
        "&make=" + inMake + "&model="+inModel + "&vin="+inVIN;

        return formString;
    } 
    
    /**
     * Reads vehicles for a user.
     */
    private void readVehicleForUser()
    {

        String url = "/rest/vehicles?userId=" + userName;

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
        //builder.setHeader("Content-Type","application/x-www-form-urlencoded"); 

        try {
          builder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
               // Couldn't connect to server (could be timeout, SOP violation, etc.)  
                displayError("Couldn't retrieve JSON - oh no");   
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode()) {
                  try {
                      // parse the response text into JSON
                      JSONValue jsonValue = JSONParser.parseStrict(response.getText());
                      JSONArray jsonArray = jsonValue.isArray();
                      
                      if (jsonArray != null) {
                        parseVehicleJSON(jsonArray);
                        fillVehicleLists();
                        if (myVehicles.getVehicleCount() > 0)
                        {        
                            mainMenu.selectTab(fillOuterVP);       // go to this tab if user has vehicles
                        }
                        else
                        {
                            mainMenu.selectTab(vehicleOuterVP);      // else show vehicle list
                        }
                      } else {
                        throw new JSONException(); 
                      }
                    } catch (JSONException e) {
                      displayError("Could not parse JSON");
                    }
                  } else {
                    displayError("Couldn't retrieve JSON (" + response.getStatusText() + ")");
                  }
                }       
              });
            } catch (RequestException e) {
              displayError("Couldn't retrieve JSON - "+e.getMessage()+e.getStackTrace());         
            }
    }
    
    /**
     * Saves new vehicle for a user.
     * @param String vehicle data to post.
     */
    private void postVehicleForUser(String inPostData)
    {
        String url = "/rest/vehicles";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
        builder.setHeader("Content-Type","application/x-www-form-urlencoded"); 

        try {
          Request request = builder.sendRequest(inPostData, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
               // Couldn't connect to server (could be timeout, SOP violation, etc.)  
                displayError("postVehicle Couldn't retrieve JSON - oh no");   
            }

            public void onResponseReceived(Request request, Response response) {
                    //System.out.print("vehicle saved\n");
                    addVehicleVP.setVisible(false);
                    postVehicleButton.setEnabled(true);
                    vehicleVP.setVisible(true);
                    readVehicleForUser();               // just reread all vehicles for user
                }       
              });
            } catch (RequestException e) {
              displayError("postVehicle Couldn't retrieve JSON - "+e.getMessage()+e.getStackTrace());         
            }
    }
    
    /**
     * Saves new vehicle fill up for a user.
     * @param String fill up data to post.
     */
    private void postFillup(String inPostData)
    {
        String url = "/rest/fillups";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
        builder.setHeader("Content-Type","application/x-www-form-urlencoded"); 

        try {
          Request request = builder.sendRequest(inPostData, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
               // Couldn't connect to server (could be timeout, SOP violation, etc.)  
                displayError("postFillup on error on post - oh no");   
            }

            public void onResponseReceived(Request request, Response response) {
                    //System.out.print("fillup saved\n");
                    addVehicleVP.setVisible(false);
                    vehicleVP.setVisible(true);
                    readVehicleMPG(myVehicles.getMyList().get(lbVehicle.getSelectedIndex()).getVehicleID());
                }       
              });
            } catch (RequestException e) {
              displayError("postFillup post error - "+e.getMessage()+e.getStackTrace());         
            }
    }
    
    /**
     * Reads mpg for a vehicle.
     */
    private void readVehicleMPG(String inVehicleID)
    {
        String url = "/rest/vehicles/"+inVehicleID+"/mpg";
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
        //builder.setHeader("Content-Type","application/x-www-form-urlencoded"); 

        try {
          Request request = builder.sendRequest(null, new RequestCallback() {
            public void onError(Request request, Throwable exception) {
               // Couldn't connect to server (could be timeout, SOP violation, etc.)  
                displayError("mpg: Couldn't retrieve JSON - oh no");   
            }

            public void onResponseReceived(Request request, Response response) {
              if (200 == response.getStatusCode()) {
                  try {
                      // parse the response text into JSON
                      JSONValue jsonValue = JSONParser.parseLenient(response.getText());
                      
                        parseMPGJSON(jsonValue);
                        showMPGResults();

                    } catch (JSONException e) {
                      displayError("mpg:Could not parse JSON - "+e.getMessage());
                    }
                  } else {
                    displayError("mpg:Couldn't retrieve JSON (" + response.getStatusText() + ")");
                  }
                }       
              });
            } catch (RequestException e) {
              displayError("mpg:other JSON error - "+e.getMessage()+e.getStackTrace());         
            }
    }
    
    private String getAboutText()
    {
        String aboutTxt = "Google IO 2012 Hackathon to solve social challenges: Go Green - Croud source real MPG for vehicles "+
        "depending on location.  <br><br> For the hackathon, the project started with three people at an extended google io event. "+
        "The purpose of this code is to allow participants to see what the average person in their area really gets for a MPG given "+
        "their vehicle. It's an idea similar to benchmarking devices. The more people contributing real live information, the more "+
        "accurate the information will be. This is especially true given that driving habits and conditions differ greatly depending"+
        "on location. <br><br>"+
        "  Use case: Someone with an android phone fills up their gas tank. They open up the android app and login"+
        "just using their email address as an identifier. They then enter their mileage, number of gallons they filled up with, and "+
        "an auto-calculated date/GPS coordinate pair. The app then sends this data to the main datastore. Later, after many people "+
        "have contributed this kind of data, they load the website, and can browse for a given city what the average MPG is for a "+
        "make/model/year/etc of vehicle. Reports could be run to show MPG differences for cities that are mostly city vs those with "+
        "highways, and these costs could be factored into cost of living expenses. This data could also be used to show which cities "+
        "really are greener than others.<br> This project is made up of three parts: A server backend (that can run on Google's AppEngine)"+
        ". This is the main datastore. A frontend, that allows data to be entered probably from desktop/laptop computers. A frontend" +
        " (that can be run from Android devices), that allows data to be entered from mobile devices like phones.<br><br>"+
        "The initial work took place during the hackathon which lasted roughly four hours. The base apps exist, with communication"+
        "taking place between them. There are however 1-2 more web service calls that must be added in order for the apps to "+
        "work/become useful. The infrastructure however, is there and functioning. Each of the three participants worked on one "+
        "of the three items listed above such that work could be done in parallel. This is the reason that they are not starting "+
        "out completely integrated, and have some redundancy.<br><br>"+
        "Overall, there are two main domain model objects: Vehicle (make, model, year, vin) Fillup data (gallons, mileage, date"+
        ", gps coordinates)";
        return aboutTxt;
    }
    
    private void displayError(String error) {
        //errorMsgLabel.setText(messages.errorMsg(error));
        //errorMsgLabel.setVisible(true);
        System.out.print(error);
      }
}
